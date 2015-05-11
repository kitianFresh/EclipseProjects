import java.awt.*;
public class Explode {
	private boolean live = true;
	private int x;
	private int y;
	private TankClient tc = null;
	private int step = 0;
	int[] diameter = {4,8,16,32,64,80,80,32,16,8,4};
	
	public Explode(int x, int y, TankClient tc){
		this.x = x;
		this.y = y;
		this.tc = tc;
	}
	
	public void draw(Graphics g){
		if(!live) {
			tc.explodes.remove(this);
			return;
		}
		if(step>=diameter.length){
			live = false;
			step = 0;
			tc.explodes.remove(this);
			return;
		}
		Color c = g.getColor();
		g.setColor(Color.ORANGE);
		g.fillOval(x, y, diameter[step], diameter[step]);
		g.setColor(c);
		step ++;
	}
}
