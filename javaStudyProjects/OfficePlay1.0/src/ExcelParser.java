import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
	public ExcelParser(FileInputStream  fis) {
		
			//Instantiate a new workbook with path to an Excel file
			Workbook book;
			
			try {
				book = new Workbook(fis);
				//Create an object for ImageOptions
				ImageOrPrintOptions imgOptions = new ImageOrPrintOptions();
				//Set the image type
				imgOptions.setImageFormat(ImageFormat.getJpeg());
				
				for (int j = 0; j < book.getWorksheets().getCount(); j++)
				{
					Worksheet sheet = book.getWorksheets().get(j);
					imgOptions.setOnePagePerSheet(true);
					imgOptions.setImageFitToPage(true);
					imgOptions.setCellAutoFit(true);

					//Create a SheetRender object for the target sheet
					SheetRender sr = new SheetRender(sheet, imgOptions);
				    //Generate an image for the worksheet
System.out.println(sr.getPageCount());
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
				    sr.toImage(0, baos);
				    imgs.add(ImageIO.read(new ByteArrayInputStream(baos.toByteArray())));

				}
				fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	public void draw(Graphics g) {
		if(step>=imgs.size()) step = 0;
		g.drawImage(imgs.get(step),0,0,null);
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
