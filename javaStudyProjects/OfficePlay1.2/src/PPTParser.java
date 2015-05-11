import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.usermodel.SlideShow;

public class PPTParser implements Parser {

	Slide[] slide = null;
	Dimension pgsize = null;
	int step = 0;
	BufferedImage img = null;
	SlideShow ppt = null;
	boolean stoped = false;
	public PPTParser(FileInputStream fis) {
		
		try {
			ppt = new SlideShow(fis);
			fis.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		pgsize = ppt.getPageSize();
		slide = ppt.getSlides();
	}

	public void draw(Graphics g) {
		
		if (step >= slide.length) {
			step = 0;
		}
System.out.println("step: " + step);
		slide[step].draw((Graphics2D) g);
		if(!stoped) step++;
	}

	public int getWidth() {
		return pgsize.width;
	}
	
	public int getHeight() {
		return pgsize.height;
	}

	@Override
	public void setStoped(boolean stoped) {
		this.stoped = stoped;
		
	}

	@Override
	public boolean isStoped() {
		return this.stoped;
	}
}
