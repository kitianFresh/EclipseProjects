import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.usermodel.SlideShow;

public class PPTParser implements Parser {

	Slide[] slide = null;
	Dimension pgsize = null;
	int step = 0;
	
	SlideShow ppt = null;
	boolean stoped = false;
	Player player = null;
	public PPTParser(FileInputStream fis, Player player) {
		
		try {
			ppt = new SlideShow(fis);
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				if(fis != null) fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		pgsize = new Dimension(player.getPLAYER_WIDTH(),player.getPLAYER_HEIGHT());
		ppt.setPageSize(pgsize);
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
