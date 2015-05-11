import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class TankClient extends Frame {
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 600;
	Image offScreenImage = null;
	Tank tank = new Tank(50,50);
	
	
	@Override
	public void paint(Graphics g) {
		if(tank.missiles != null){
			Iterator<Missile> it = tank.missiles.iterator();
			while(it.hasNext()){
				Missile m =  it.next();
				m.draw(g);
			}
		}
		tank.draw(g);
	}
	//双缓冲技术解决闪烁，使用内存中的虚拟图片
	@Override
	public void update(Graphics g) {
		if(offScreenImage == null){
			offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.green);
		gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
		gOffScreen.setColor(c);
		
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0,null);
	}

	
	public static void main(String[] args) {
		new TankClient().launchFrame();
	}

	public void launchFrame(){
		this.setTitle("TankWar");
		this.setBounds(100, 100, GAME_WIDTH, GAME_HEIGHT);
		this.setResizable(false);
		this.setBackground(Color.GREEN);
		this.addWindowListener(new WindowAdapter(){

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		this.addKeyListener(new myListener());
		
		this.setVisible(true);
		new Thread(new PaintThread()).start();
	}
	
	private class PaintThread implements Runnable {

		@Override
		public void run() {
			while(true){
				repaint();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
	private class myListener extends KeyAdapter {

		@Override
		public void keyReleased(KeyEvent e) {
			tank.keyReleased(e);
		}

		@Override
		public void keyPressed(KeyEvent e) {
			tank.keyPressed(e);
		}
		
	}
	
}
