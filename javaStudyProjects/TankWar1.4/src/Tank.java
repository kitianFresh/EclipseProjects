
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
public class Tank {
	public int getLocation_x() {
		return x;
	}

	public void setLocation_x(int location_x) {
		this.x = location_x;
	}

	public int getLocation_y() {
		return y;
	}

	public void setLocation_y(int location_y) {
		this.y = location_y;
	}

	public Direction getDir() {
		return dir;
	}

	public void setDir(Direction dir) {
		this.dir = dir;
	}

	private int x = 0;
	private int y = 0;
	public static final int width = 50;
	public static final int height = 50;
	public static final int XVelocity = 10;
	public static final int YVelocity = 10;
	private boolean bL=false,bU=false,bR=false,bD=false;
	enum Direction {L,LU,U,RU,R,RD,D,LD,STOP} ;
	private Direction dir = Direction.STOP;
	private Direction ptDir = Direction.D;
	private int missileNum = 10;
	TankClient tc = null;
	
	public Tank(int location_x, int location_y) {
		super();
		this.x = location_x;
		this.y = location_y;
	}
	
	public Tank(int location_x, int location_y, TankClient tc) {
		super();
		this.x = location_x;
		this.y = location_y;
		this.tc = tc;
	}
	
	public void draw(Graphics g){
		Color c = g.getColor();
		g.setColor(Color.red);
		g.fillOval(x, y, width, height);
		g.setColor(c);
		
		switch(ptDir){
		case L:
			g.drawLine(x, y+height/2, x+width/2, y+height/2);
			break;
		case LU:
			g.drawLine(x, y, x+width/2, y+height/2);
			break;
		case U:
			g.drawLine(x+width/2, y, x+width/2, y+height/2);
			break;
		case RU:
			g.drawLine(x+width, y, x+width/2, y+height/2);
			break;
		case R:
			g.drawLine(x+width, y+height/2, x+width/2, y+height/2);
			break;
		case RD:
			g.drawLine(x+width, y+height, x+width/2, y+height/2);
			break;
		case D:
			g.drawLine(x+width/2, y+height, x+width/2, y+height/2);
			break;
		case LD:
			g.drawLine(x, y+height, x+width/2, y+height/2);
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
		
		if(x < 0) x = 0;
		if(y < 30) y = 30;
		if(x + Tank.width > TankClient.GAME_WIDTH) x = TankClient.GAME_WIDTH - Tank.width;
		if(y + Tank.width > TankClient.GAME_HEIGHT) y = TankClient.GAME_HEIGHT - Tank.height;
		
		if(this.dir != Direction.STOP) {
			this.ptDir = this.dir;
		}
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
		int x = this.x +width/2-Missile.width/2;
		int y = this.y+height/2-Missile.height/2;
		Missile m = new Missile(x,y,ptDir,this.tc);
		tc.missiles.add(m);
		return m;
	}
}
