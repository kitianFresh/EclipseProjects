import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.aspose.slides.ISlide;
import com.aspose.slides.Presentation;

public class PPTParser implements Parser {

	public static final int MaxInitImage = 6;
	ISlide[] slides = null;

	int step = 0;
	List<BufferedImage> imgs = new ArrayList<BufferedImage>();
	BufferedImage image = null;
	Presentation ppt = null;
	boolean stoped = false;
	public PPTParser(FileInputStream fis) {
		
		try {
			ppt = new Presentation(fis);
			fis.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		slides = ppt.getSlides().toArray();
		if(slides.length>0) loopLoadImages(0);		
	}
	public void loopLoadImages(int PosAtSlides){
		imgs.clear();
		int slidesNum = slides.length - PosAtSlides;
		for(int i=PosAtSlides;i<(slidesNum>=MaxInitImage?MaxInitImage:slidesNum);i++){
			imgs.add(slides[i].getThumbnail(1f,1f));
		}
	}
	
	public void draw(Graphics g) {
		
		if (step >= slides.length) {
			step = 0;
		}
		//image = slides[step].getThumbnail(1f, 1f);
		if(step % MaxInitImage == 0){
			loopLoadImages(step);
		}
System.out.println("step: " + step);
		g.drawImage(imgs.get(step % MaxInitImage),0,0,null);
		if(!stoped) step++;
	}

	public int getWidth() {
		return imgs.get(0).getWidth();
	}
	
	public int getHeight() {
		return imgs.get(0).getHeight();
	}

	@Override
	public void setStoped(boolean stoped) {
		this.stoped = stoped;
		
	}

	@Override
	public boolean isStoped() {
		return this.stoped;
	}
	
	private class ImageWorker implements Runnable {
		List<BufferedImage> imgs = null;
		public ImageWorker(List<BufferedImage> imgs){
			this.imgs = imgs;
		}
		@Override
		public void run() {
			
		}
		
	}
}
