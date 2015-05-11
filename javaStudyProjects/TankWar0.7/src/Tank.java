
import java.awt.*;
import java.awt.event.KeyEvent;
public class Tank {
	private int location_x = 50;
	private int location_y = 50;
	public static final int Tank_Velocity = 5;
	
	public Tank(int location_x, int location_y) {
		super();
		this.location_x = location_x;
		this.location_y = location_y;
	}
	
	public void draw(Graphics g){
		g.setColor(Color.red);
		g.fillOval(location_x, location_y, 50, 50);
		location_y+=Tank_Velocity;
	}
	
	public void keyPressed(KeyEvent e){
		switch(e.getKeyCode()){
		case KeyEvent.VK_UP:
			location_y-=Tank_Velocity;
			break;
		case KeyEvent.VK_DOWN:
			location_y+=Tank_Velocity;
			break;
		case KeyEvent.VK_RIGHT:
			location_x+=Tank_Velocity;
			break;
		case KeyEvent.VK_LEFT:
			location_x-=Tank_Velocity;
			break;
		}
	}
	
}
