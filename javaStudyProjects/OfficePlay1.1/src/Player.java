import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Player extends Frame {

	public static final int PLAYER_WIDTH = 800;
	public static final int PLAYER_HEIGHT = 600;

	Image img = null;
	Parser parser = null;
	Graphics gOffScreen  = null;
	boolean stoped = false;
	public Player(Parser parser) {
		this.parser = parser;	
	}
	
	public void lauchFrame(){
		this.setTitle("Player");
		this.setBounds(100, 100, PLAYER_WIDTH, PLAYER_HEIGHT);
		this.setResizable(false);
		this.setBackground(Color.white);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

		});
		this.addKeyListener(new myListener());
		this.setVisible(true);
		new Thread(new PaintThread()).start();
	}

	@Override
	public void paint(Graphics g) {
		
		parser.draw(g);
	}

	public static void main(String args[]) {
		try {
			FileInputStream fis = new FileInputStream("E:\\学习文档\\大二课程\\上\\宛章齐\\第5章.ppt");
			FileInputStream fis1 = new FileInputStream("e:\\学生信息表.xlsx");
			//ExcelParser exParser = new ExcelParser(fis1);
			PPTParser ptParser = new PPTParser(fis);
			Player p = new Player(ptParser);
			
			p.lauchFrame();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void update(Graphics g) {
		
		if (img == null) {
			img = this.createImage(PLAYER_WIDTH, PLAYER_WIDTH);
		}
		if(gOffScreen == null ){
			gOffScreen = img.getGraphics();
		}
		
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.white);
		gOffScreen.fillRect(0,0,PLAYER_WIDTH,PLAYER_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		
		//g.drawImage(img, PLAYER_WIDTH/2-parser.getWidth()/2, PLAYER_HEIGHT/2-parser.getHeight()/2, null);
		g.drawImage(img, 0, 0, null);
	}

	private class PaintThread implements Runnable {

		@Override
		public void run() {
			while (true) {
				repaint();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}

	}
	
	private class myListener extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_CONTROL){
				/*if(stoped == false) stoped = true;
				else stoped = true;*/
				if(parser.isStoped())  parser.setStoped(false);
				else parser.setStoped(true);
			}
		}
		
	}
}
