import com.aspose.cells.ImageFormat;
import com.aspose.cells.ImageOrPrintOptions;
import com.aspose.cells.SheetRender;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
public class ExcelReader {
	public static void main(String[] args) {

		//Instantiate a new workbook with path to an Excel file
		Workbook book;
		try {
			book = new Workbook("e:\\学生信息表.xlsx");
			//Create an object for ImageOptions
			ImageOrPrintOptions imgOptions = new ImageOrPrintOptions();
			//Set the image type
			imgOptions.setImageFormat(ImageFormat.getJpeg());
			//Get the first worksheet.
			Worksheet sheet = book.getWorksheets().get(0);
			imgOptions.setOnePagePerSheet(true);
			imgOptions.setWarningCallback(null);
			//Create a SheetRender object for the target sheet
			SheetRender sr = new SheetRender(sheet, imgOptions);
			for (int j = 0; j < sr.getPageCount(); j++)
			{
			    //Generate an image for the worksheet
			    sr.toImage(j, "e:\\mysheetimg_" + j + ".jpeg");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		}
}
