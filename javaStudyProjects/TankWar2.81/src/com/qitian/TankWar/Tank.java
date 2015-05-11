package com.qitian.TankWar;
import java.util.List;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
public class Tank {
	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}


	public Direction getDir() {
		return dir;
	}

	public void setDir(Direction dir) {
		this.dir = dir;
	}
	
	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}
	

	
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] tankImages = null;
	private static Map<String, Image> imgs = new HashMap<String, Image>(); 
	static {
		tankImages = new Image[] {
				tk.getImage(Tank.class.getClassLoader().getResource("images/tankL.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("images/tankLU.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("images/tankU.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("images/tankRU.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("images/tankR.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("images/tankRD.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("images/tankD.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("images/tankLD.gif")),
		};	
		
		imgs.put("L", tankImages[0]);
		imgs.put("LU", tankImages[1]);
		imgs.put("U", tankImages[2]);
		imgs.put("RU", tankImages[3]);
		imgs.put("R", tankImages[4]);
		imgs.put("RD", tankImages[5]);
		imgs.put("D", tankImages[6]);
		imgs.put("LD", tankImages[7]);
		
		System.out.println(tankImages[0].getWidth(null));
	}
	
	private int x = 0;
	private int y = 0;
	private int pre_x = 0;
	private int pre_y = 0;
	private int life = 10;
	private BloodBar bb = new BloodBar();
	public static final int width = 40;
	public static final int height = 40;
	public static final int XVelocity = 10;
	public static final int YVelocity = 10;
	private boolean bL=false,bU=false,bR=false,bD=false;
	private Direction dir = Direction.STOP;
	private Direction ptDir = Direction.D;
	private boolean bEnemy = false;
	private Color colorE = Color.BLUE;
	private Color colorI = Color.RED;
	private boolean live = true;
	private Random r = new Random();
	private int step = r.nextInt(10)+3;
	TankClient tc = null;
	
	public Tank(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Tank(int x, int y, TankClient tc) {
		this(x,y);
		this.tc = tc;
	}
	
	public Tank(int x, int y, TankClient tc, Direction dir, boolean bEnemy) {
		this(x,y,tc);
		this.bEnemy = bEnemy;
		this.dir = dir;
	}
	
	public void draw(Graphics g){
		if(!live){
			life = 10;
			return;
		}
		
		switch(ptDir) {
		case L:
			g.drawImage(imgs.get("L"), x, y, null);
			break;
		case LU:
			g.drawImage(imgs.get("LU"), x, y, null);
			break;
		case U:
			g.drawImage(imgs.get("U"), x, y, null);
			break;
		case RU:
			g.drawImage(imgs.get("RU"), x, y, null);
			break;
		case R:
			g.drawImage(imgs.get("R"), x, y, null);
			break;
		case RD:
			g.drawImage(imgs.get("RD"), x, y, null);
			break;
		case D:
			g.drawImage(imgs.get("D"), x, y, null);
			break;
		case LD:
			g.drawImage(imgs.get("LD"), x, y, null);
			break;
		}
		
		
		if(!bEnemy){
			bb.draw(g);
		}

		move();
	}
	
	public void move(){
		pre_x = x;
		pre_y = y;//这两句必须写在最前面，因为要先记录以前的，再改变
		
		switch(dir){
		case L:
			x-=XVelocity;
			break;
		case LU:
			x-=XVelocity;
			y-=YVelocity;
			break;
		case U:
			y-=YVelocity;
			break;
		case RU:
			x+=XVelocity;
			y-=YVelocity;
			break;
		case R:
			x+=XVelocity;
			break;
		case RD:
			x+=XVelocity;
			y+=YVelocity;
			break;
		case D:
			y+=YVelocity;
			break;
		case LD:
			x-=XVelocity;
			y+=YVelocity;
			break;
		case STOP:
			break;
		}
		
		if(x < 0) x = 0;
		if(y < 30) y = 30;
		if(x + Tank.width > TankClient.GAME_WIDTH) x = TankClient.GAME_WIDTH - Tank.width;
		if(y + Tank.width > TankClient.GAME_HEIGHT) y = TankClient.GAME_HEIGHT - Tank.height;
		
		if(this.dir != Direction.STOP) {
			this.ptDir = this.dir;
		}
		
		if(bEnemy){
			Direction[] dirs = dir.values();
			if(step == 0){
				int rn = r.nextInt(dirs.length);
				dir = dirs[rn];
				step = r.nextInt(10)+3;
				//fire();
			}
			step --;
		}
		
		/*if(hitWall(tc.wall)){
			x = pre_x;
			y = pre_y;
		}*/
		
	
	}
	
	public void stay(){
		x = pre_x;
		y = pre_y;
	}
	
	
	
	public void keyPressed(KeyEvent e){
		switch(e.getKeyCode()){
		case KeyEvent.VK_UP:
			bU = true;
			break;
		case KeyEvent.VK_DOWN:
			bD = true;
			break;
		case KeyEvent.VK_RIGHT:
			bR = true;
			break;
		case KeyEvent.VK_LEFT:
			bL = true;
			break;
		case KeyEvent.VK_S:
			fire();
			break;
		case KeyEvent.VK_CONTROL:
			superFire();
		}
		locateDirection();
	}
	
	public void keyReleased(KeyEvent e){
		switch(e.getKeyCode()){
		case KeyEvent.VK_UP:
			bU = false;
			break;
		case KeyEvent.VK_DOWN:
			bD = false;
			break;
		case KeyEvent.VK_RIGHT:
			bR = false;
			break;
		case KeyEvent.VK_LEFT:
			bL = false;
			break;
		}
		locateDirection();
	}
	
	public void locateDirection(){
		if(bL && !bU && !bR && !bD){dir = Direction.L;}
		else if(bL && bU && !bR && !bD){dir = Direction.LU;}
		else if(!bL && bU && !bR && !bD){dir = Direction.U;}
		else if(!bL && bU && bR && !bD){dir = Direction.RU;}
		else if(!bL && !bU && bR && !bD){dir = Direction.R;}
		else if(!bL && !bU && bR && bD){dir = Direction.RD;}
		else if(!bL && !bU && !bR && bD){dir = Direction.D;}
		else if(bL && !bU && !bR && bD){dir = Direction.LD;}
		else{dir = Direction.STOP;}
	}
	
	public Missile fire(){
		if(live){
			int x = this.x +width/2-Missile.width/2;
			int y = this.y+height/2-Missile.height/2;
			Missile m = new Missile(x, y, ptDir, this.tc, this.bEnemy);
			tc.missiles.add(m);
			return m;
		}
		return null;
	}
	
	public Missile fire(Direction dir){
		if(live){
			int x = this.x +width/2-Missile.width/2;
			int y = this.y+height/2-Missile.height/2;
			Missile m = new Missile(x, y, dir, this.tc, this.bEnemy);
			tc.missiles.add(m);
			return m;
		}
		return null;
	}
	public void superFire(){
		Direction[] dirs = Direction.values();
		for(int i=0;i<8;i++){
			fire(dirs[i]);
		}
	}
	
	public boolean isbEnemy() {
		return bEnemy;
	}

	public Rectangle getRect(){
		return new Rectangle(x, y, width, height);
	}
	
	/**
	 * 撞墙
	 * @param w  撞上的墙
	 * @return   装上返回true，否则false
	 */
	
	public boolean hitWall(Wall w){
		if(this.live && w.getRect().intersects(this.getRect()) ){
			
			stay();
			return true;
		}
		return false;
	}
	
	public boolean collidesWithTank(Tank t){
		if(this != t && t.isLive() && this.live && t.getRect().intersects(this.getRect()) ){
			return true;
		}
		return false;
	}
	
	public boolean collidesWithTanks(List<Tank> tanks){
		for(int i =0;i<tanks.size();i++){
			Tank t = tanks.get(i);
			if(collidesWithTank(t)){
				this.stay();
				t.stay();
				return true;
			}
		}
		return false;
	}
	
	public boolean eatBlood(Blood b){
		if(this.live && b.isLive() && b.getRect().intersects(this.getRect()) ){
			this.life = 10;
			b.setLive(false);
			return true;
		}
		return false;
	}
	
	private class BloodBar {
		public void draw(Graphics g){
			Color c = g.getColor();
			g.setColor(Color.RED);
			g.drawRect(x, y-10, width, height/5);
			g.fillRect(x, y-10, width*life/10, height/5);
			g.setColor(c);
		}
	}
}
