
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

	private int x = 50;
	private int y = 50;
	public static final int XVelocity = 5;
	public static final int YVelocity = 5;
	private boolean bL=false,bU=false,bR=false,bD=false;
	enum Direction {L,LU,U,RU,R,RD,D,LD,STOP} ;
	private Direction dir = Direction.STOP;
	private int missileNum = 10;
	Set<Missile> missiles = new HashSet<Missile>(10);
	
	public Tank(int location_x, int location_y) {
		super();
		this.x = location_x;
		this.y = location_y;
	}
	
	public void draw(Graphics g){
		Color c = g.getColor();
		g.setColor(Color.red);
		g.fillOval(x, y, 50, 50);
		g.setColor(c);
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
			missiles.add(new Missile(x,y,dir));
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
	
}
