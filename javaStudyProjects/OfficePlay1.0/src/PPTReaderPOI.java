import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.usermodel.SlideShow;

public class PPTReaderPOI extends Frame {

	public static final int PLAYER_WIDTH = 800;
	public static final int PLAYER_HEIGHT = 600;

	private static Toolkit tk = Toolkit.getDefaultToolkit();

	Slide[] slide = null;
	Dimension pgsize = null;
	int step = 0;
	BufferedImage img = null;

	public void launchFrame() {
		this.setTitle("PPTPlayer");
		this.setBounds(100, 100, PLAYER_WIDTH, PLAYER_HEIGHT);
		this.setResizable(false);
		this.setBackground(Color.white);
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

		});
		// this.addKeyListener(new myListener());

		this.setVisible(true);
		new Thread(new PaintThread()).start();
	}

	@Override
	public void paint(Graphics g) {
		
		//g.drawString("current page: " + step, 20, 580);
	}

	public static void main(String args[]) {
		PPTReaderPOI ptr = new PPTReaderPOI();
		ptr.init();
		ptr.launchFrame();

	}

	public void init() {
		FileInputStream is = null;
		SlideShow ppt = null;
		try {
			is = new FileInputStream("E:\\学习文档\\大二课程\\上\\宛章齐\\第5章.ppt");
			ppt = new SlideShow(is);
			is.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		pgsize = ppt.getPageSize();

		slide = ppt.getSlides();

	}

	@Override
	public void update(Graphics g) {
		
		if (img == null) {
			img = new BufferedImage(pgsize.width, pgsize.height,
					BufferedImage.TYPE_INT_RGB);
		}
		if (step >= slide.length) {
			step = 0;
		}

		Graphics2D graphics = img.createGraphics();
		// clear the drawing area
		graphics.setPaint(Color.white);
		graphics.fillRect(0, 0, PLAYER_WIDTH, PLAYER_HEIGHT);
		//graphics.drawString("current page: " + step, 20, 580);
		// render
		slide[step].draw(graphics);
		step ++;
		
		paint(graphics);
		
		//g.drawString("current page: " + step, 20, 580);
		g.drawImage(img, PLAYER_WIDTH/2-pgsize.width/2, PLAYER_HEIGHT/2-pgsize.height/2, null);
		
	}

	private class PaintThread implements Runnable {

		@Override
		public void run() {
			while (true) {
				repaint();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}

	}
}
