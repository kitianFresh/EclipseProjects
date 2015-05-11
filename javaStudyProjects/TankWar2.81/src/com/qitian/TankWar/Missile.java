package com.qitian.TankWar;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Missile {
	
	public static final int width = 10;
	public static final int height = 10;
	private static final int XVelocity = 20;
	private static final int YVelocity = 20;
	private int x;
	private int y;
	private Direction dir;
	private boolean live = true;
	private TankClient tc = null;
	private boolean benemy = false;
	private Color colorI = Color.YELLOW;
	private Color colorE = Color.BLACK;
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] missileImages = null;
	private static Map<String, Image> imgs = new HashMap<String, Image>(); 
	static {
		missileImages = new Image[] {
				tk.getImage(Tank.class.getClassLoader().getResource("images/missileL.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("images/missileLU.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("images/missileU.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("images/missileRU.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("images/missileR.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("images/missileRD.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("images/missileD.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("images/missileLD.gif")),
		};	
		
		imgs.put("L", missileImages[0]);
		imgs.put("LU", missileImages[1]);
		imgs.put("U", missileImages[2]);
		imgs.put("RU", missileImages[3]);
		imgs.put("R", missileImages[4]);
		imgs.put("RD", missileImages[5]);
		imgs.put("D", missileImages[6]);
		imgs.put("LD", missileImages[7]);
		
		System.out.println(missileImages[0].getWidth(null));
	}
	
	public boolean isLive() {
		return live;
	}

	
	public Missile(int x, int y, Direction dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	
	public Missile(int x, int y, Direction dir, TankClient tc, boolean benemy) {
		this(x,y,dir);
		this.tc = tc;
		this.benemy = benemy;
	}
	
	public void draw(Graphics g){
		if(!live){
			tc.missiles.remove(this);
			return;
		}
		switch(dir){
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
		move();
	}
	
	public void move(){
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
		if(x < 0 || y < 0 || x > TankClient.GAME_WIDTH || y > TankClient.GAME_HEIGHT){
			live = false;
			
		}
	}

	public Rectangle getRect(){
		return new Rectangle(x, y,width, height);
	}
	
	public boolean hitTank(Tank t){
		if(this.live && t.getRect().intersects(this.getRect()) && t.isLive() && benemy!=t.isbEnemy()){
			if(t.isbEnemy()){
				t.setLive(false);	
			}
			else{
				t.setLife((t.getLife())-1);
				if(t.getLife() <= 0){
					t.setLive(false);
				}
			}
			this.live = false;
			Explode e = new Explode(x,y,tc);
			tc.explodes.add(e);
			return true;
			
		}
		return false;
	}
	
	public boolean hitTanks(List<Tank> tanks){
		for(int i =0;i < tanks.size();i++){
			if(hitTank(tanks.get(i))) {
				tc.etanks.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public boolean hitWall(Wall w){
		if(this.live && w.getRect().intersects(this.getRect()) ){
			live = false;
			//Explode e = new Explode(x,y,tc);
			//tc.explodes.add(e);
			return true;
		}
		return false;
	}
}
