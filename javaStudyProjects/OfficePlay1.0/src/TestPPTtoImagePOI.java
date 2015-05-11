import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.usermodel.SlideShow;


public class TestPPTtoImagePOI extends Frame {
	Image offScreenImage = null;
	public static final int PLAYER_WIDTH = 800;
	public static final int PLAYER_HEIGHT = 600;
	public static String imagesDir = null;
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	List<Image> images = new ArrayList<Image>();
	
	int step = 0;
	public void launchFrame(){
		this.setTitle("PPTPlayer");
		this.setBounds(100, 100, PLAYER_WIDTH, PLAYER_HEIGHT);
		this.setResizable(false);
		this.setBackground(Color.white);
		this.addWindowListener(new WindowAdapter(){

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		//this.addKeyListener(new myListener());
		
		this.setVisible(true);
		new Thread(new PaintThread()).start();
	}
	
	@Override
	public void paint(Graphics g) {
		if(step >= images.size()){
			step = 0;
		}
		g.drawImage(images.get(step),0,0,null);
		g.drawString("current page: "+step, 20,750 );
		step ++;
	}

	public static void main(String args[]){
		TestPPTtoImagePOI t = new TestPPTtoImagePOI();
		t.init();
		t.launchFrame();
		
	}
	
	public void init(){
		imagesDir = this.getClass().getResource("/").getPath();
		//imagesDir = this.getClass().getResource("/").getPath() +"../../";
		//imagesDir = "images/";
		FileInputStream is = null;
		SlideShow ppt = null;
		try {
			is = new FileInputStream("E:\\学习文档\\大二课程\\上\\宛章齐\\第5章.ppt");
			ppt= new SlideShow(is);
		     is.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        
        Dimension pgsize = ppt.getPageSize();

        Slide[] slide = ppt.getSlides();
        for (int i = 0; i < slide.length; i++) {

            BufferedImage img = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = img.createGraphics();
            //clear the drawing area
            graphics.setPaint(Color.white);
            graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));

            //render
            slide[i].draw(graphics);

            //save the output
            FileOutputStream out;
           File newFile = new File(imagesDir+"images/slide-"  + i + ".png");
           if(!newFile.exists()){
        	   try {
   				out = new FileOutputStream(newFile);
   				 javax.imageio.ImageIO.write(img, "png", out);
   		            out.close();
   			} catch (FileNotFoundException  e) {
   				e.printStackTrace();
   			}catch (IOException e) {
   				e.printStackTrace();
   			}
           }
			
			for(int i1 =0;i1<slide.length;i1++){
				images.add(tk.getImage(TestPPTtoImagePOI.class.getClassLoader().getResource("images/slide-"  + i1 + ".png")));
			}
			
           
        }

	}
	
	@Override
	public void update(Graphics g) {

		if(offScreenImage == null){
			offScreenImage = this.createImage(PLAYER_WIDTH,PLAYER_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.white);
		gOffScreen.fillRect(0,0,PLAYER_WIDTH,PLAYER_HEIGHT);
		gOffScreen.setColor(c);
		
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0,null);
	}
	
	
	private class PaintThread implements Runnable {

		@Override
		public void run() {
			while(true){
				repaint();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}
}
