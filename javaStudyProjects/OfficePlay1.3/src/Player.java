import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Player extends Frame {
	GraphicsEnvironment graphEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
	GraphicsDevice graphDevice = graphEnv.getDefaultScreenDevice();
	GraphicsConfiguration graphicConf = graphDevice.getDefaultConfiguration();
	int SCREEN_WIDTH = graphicConf.getBounds().width;
	int SCREEN_HEIGHT = graphicConf.getBounds().height;
	int PLAYER_WIDTH = SCREEN_WIDTH/3*2;
	int PLAYER_HEIGHT = SCREEN_HEIGHT;
	Image img = null;
	Parser parser = null;
	Graphics gOffScreen  = null;
	boolean stoped = false;
	public Player(File file){
		try {
			FileInputStream fis = new FileInputStream(file);
System.out.println(file.getName());
			String filename = file.getName();
			if(filename.endsWith(".ppt") ||filename.endsWith("pptx")){
				parser = new PPTParser(fis,this);
			}
			if(filename.endsWith(".xls") ||filename.endsWith(".xlsx")){
				parser = new ExcelParser(fis,this);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Player(Parser parser) {
		this.parser = parser;	
	}
	
	public void lauchFrame(){
		
		this.setTitle("Player");
		this.setBounds(0,0,
				PLAYER_WIDTH,PLAYER_HEIGHT);
		this.setResizable(true);
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
		File file = new File("E:\\学习文档\\大二课程\\上\\宛章齐\\第5章.ppt");
		File file1 = new File("e:\\学生信息表.xlsx");
		Player p = new Player(file);
		p.lauchFrame();
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
	
	public int getPLAYER_WIDTH() {
		return PLAYER_WIDTH;
	}

	public void setPLAYER_WIDTH(int pLAYER_WIDTH) {
		PLAYER_WIDTH = pLAYER_WIDTH;
	}

	public int getPLAYER_HEIGHT() {
		return PLAYER_HEIGHT;
	}

	public void setPLAYER_HEIGHT(int pLAYER_HEIGHT) {
		PLAYER_HEIGHT = pLAYER_HEIGHT;
	}
}
