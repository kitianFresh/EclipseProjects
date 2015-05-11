import java.awt.Graphics;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.text.Document;

import org.apache.poi.hslf.usermodel.SlideShow;

import com.aspose.cells.ImageSaveOptions;
import com.aspose.cells.SaveFormat;
import com.aspose.words.DocumentBase;
import com.aspose.words.DocumentBuilder;

public class DocParser implements Parser {
	Document doc = null;
	int step = 0;

	SlideShow ppt = null;
	boolean stoped = false;
	Player player = null;

	public DocParser(FileInputStream fis, Player player) {

		try {
			Document doc = new Document("Rendering.doc");

			ImageSaveOptions options = new ImageSaveOptions(SaveFormat.PNG);
			options.setResolution(300);
			options.setPageCount(1);

			doc.save(getMyDir() + "Rendering.SaveToImageResolution Out.png", options);
			 
			doc.save("C:\\Temp\\image.jpeg", options);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}

	@Override
	public void draw(Graphics g) {

	}

	@Override
	public int getWidth() {
		return 0;
	}

	@Override
	public int getHeight() {
		return 0;
	}

	@Override
	public void setStoped(boolean stoped) {

	}

	@Override
	public boolean isStoped() {
		return false;
	}

}
