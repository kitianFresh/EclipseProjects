import java.awt.*;
import java.awt.event.*;


public class TankClient extends Frame {
	private int location_x = 400;
	private int location_y = 300;
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(location_x, location_y, 50, 50);
		location_y+=5;
	}

	public static void main(String[] args) {
		new TankClient().launchFrame();
	}

	public void launchFrame(){
		this.setTitle("TankWar");
		this.setBounds(100, 100, 800, 600);
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
				location_y-=10;
				repaint();
				break;
			case KeyEvent.VK_DOWN:
				location_y+=10;
				repaint();
				break;
			case KeyEvent.VK_RIGHT:
				location_x+=10;
				repaint();
				break;
			case KeyEvent.VK_LEFT:
				location_x-=10;
				repaint();
				break;
			}
		}
		
	}
	
}
