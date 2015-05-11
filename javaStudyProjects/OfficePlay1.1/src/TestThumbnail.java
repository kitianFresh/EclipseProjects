import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.aspose.slides.ISlide;
import com.aspose.slides.Presentation;


public class TestThumbnail {
	public static void main(String args[]){
		//Instantiate a Presentation class that represents the presentation file
		
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("E:\\学习文档\\大二课程\\上\\宛章齐\\第5章.ppt");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		long start = System.currentTimeMillis();
		Presentation pres = new Presentation(fis);
		
		//Access the first slide
		ISlide sld = pres.getSlides().get_Item(0);
		long end = System.currentTimeMillis();
		System.out.println("Loading Time: " + (end-start));
		//Create a full scale image
		BufferedImage image = sld.getThumbnail(1f, 1f);

		//Getting the image of desired window inside generated slide thumnbnail
		//BufferedImage window = image.getSubimage(windowX, windowY, windowsWidth, windowHeight);

		BufferedImage windowImage = image.getSubimage(100, 100, 200, 200);
		
		//Save the image to disk in JPEG format
		try
		{
		ImageIO.write(image,"jpeg",new File("e:\\ContentBG_tnail.jpg"));
		}
		catch(IOException e){}
	}
}
