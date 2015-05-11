
import java.awt.*;
import java.awt.event.KeyEvent;
public class Tank {
	private int location_x = 50;
	private int location_y = 50;
	public static final int Velocity = 5;
	private boolean bL=false,bU=false,bR=false,bD=false;
	enum Direction {L,LU,U,RU,R,RD,D,LD,STOP} ;
	private Direction dir = Direction.STOP;
	
	public Tank(int location_x, int location_y) {
		super();
		this.location_x = location_x;
		this.location_y = location_y;
	}
	
	public void draw(Graphics g){
		Color c = g.getColor();
		g.setColor(Color.red);
		g.fillOval(location_x, location_y, 50, 50);
		g.setColor(c);
		move();
	}
	
	public void move(){
		switch(dir){
		case L:
			location_x-=Velocity;
			break;
		case LU:
			location_x-=Velocity;
			location_y-=Velocity;
			break;
		case U:
			location_y-=Velocity;
			break;
		case RU:
			location_x+=Velocity;
			location_y-=Velocity;
			break;
		case R:
			location_x+=Velocity;
			break;
		case RD:
			location_x+=Velocity;
			location_y+=Velocity;
			break;
		case D:
			location_y+=Velocity;
			break;
		case LD:
			location_x-=Velocity;
			location_y+=Velocity;
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
