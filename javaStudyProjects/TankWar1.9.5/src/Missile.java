import java.awt.*;
import java.util.List;

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
		Color c = g.getColor();
		g.setColor(benemy?colorE:colorI);
		g.fillOval(x, y, width, height);
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
		if(x < 0 || y < 0 || x > TankClient.GAME_WIDTH || y > TankClient.GAME_HEIGHT){
			live = false;
			
		}
	}

	public Rectangle getRect(){
		return new Rectangle(x, y,width, height);
	}
	
	public boolean hitTank(Tank t){
		if(this.live && t.getRect().intersects(this.getRect()) && t.isLive() && benemy!=t.isbEnemy()){
			t.setLive(false);
			live = false;
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
}
