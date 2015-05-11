import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.aspose.cells.ImageFormat;
import com.aspose.cells.ImageOrPrintOptions;
import com.aspose.cells.SheetRender;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;

public class ExcelParser implements Parser{
	int step = 0;
	List<BufferedImage> imgs = new ArrayList<BufferedImage>();
	boolean stoped = false;
	Workbook book = null;
	Player player = null;
	public ExcelParser(FileInputStream  fis, Player player) {
		
			//Instantiate a new workbook with path to an Excel file
			
			this.player = player;
			try {
				book = new Workbook(fis);
				load();
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				try {
					if(fis != null) fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
	}
	
	private void load() throws Exception {
			//Create an object for ImageOptions
			ImageOrPrintOptions imgOptions = new ImageOrPrintOptions();
			//Set the image type
			imgOptions.setImageFormat(ImageFormat.getJpeg());
			
			for (int j = 0; j < book.getWorksheets().getCount(); j++)
			{
				Worksheet sheet = book.getWorksheets().get(j);
				imgOptions.setOnePagePerSheet(true);
				imgOptions.setCellAutoFit(true);

				//Create a SheetRender object for the target sheet
				SheetRender sr = new SheetRender(sheet, imgOptions);
			    //Generate an image for the worksheet
System.out.println(sr.getPageCount());
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
			    sr.toImage(0, baos);
			    imgs.add(ImageIO.read(new ByteArrayInputStream(baos.toByteArray())));
			}
			
	}
	
	public void draw(Graphics g) {
		if(step>=imgs.size()) step = 0;
		g.drawImage(imgs.get(step),0,0,player.getPLAYER_WIDTH(), player.getPLAYER_HEIGHT(),null);
		//g.drawImage(imgs.get(step),0,0,null);
System.out.println("step: " + step);		
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
}
