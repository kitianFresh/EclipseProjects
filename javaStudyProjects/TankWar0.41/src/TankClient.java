import java.awt.*;
import java.awt.event.*;


public class TankClient extends Frame {
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 600;
	public static final int Tank_Velocity = 5;
	private int location_x = 50;
	private int location_y = 50;
	Image offScreenImage = null;
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(location_x, location_y, 50, 50);
		location_y+=Tank_Velocity;
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
		public void keyPressed(KeyEvent e) {
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
	
}
