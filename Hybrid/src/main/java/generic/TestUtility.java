package generic;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TestUtility {

	public static String getProperty(String path,String key) {
		String v="";
		try {
			Properties p=new Properties();
			p.load(new FileInputStream(path));
			v=p.getProperty(key);
		}
		catch (Exception e) {
			
		}
		return v;
	}
	
	public static void getPhoto(WebDriver driver,String folder,String fileName) 
	 {
		try {
			String dateTime =new Date().toString().replaceAll(":","_");
			String path=folder+fileName+dateTime+".png";
			TakesScreenshot t=(TakesScreenshot)driver;
			File srcFile= t.getScreenshotAs(OutputType.FILE);
			File destFile=new File(path);
			FileUtils.copyFile(srcFile, destFile);  
		}
		catch (Exception e) {
		}
	 }
	
	public static int getRowCount(String path,String sheet) {
		int rc=0;
		try {
			Workbook wb=WorkbookFactory.create(new FileInputStream(path));
			rc = wb.getSheet(sheet).getLastRowNum();
		}
		catch (Exception e) {
		}
		return rc;
	}
	
	public static int getColumnCount(String path,String sheet,int r) {
		int cc=0;
		try {
			Workbook wb=WorkbookFactory.create(new FileInputStream(path));
			cc=wb.getSheet(sheet).getRow(r).getLastCellNum();
		}
		catch (Exception e) {
		}
		return cc;
	}
	
	public static String getValue(String path,String sheet,int r,int c) {
		String v="";
		try {		
			Workbook wb = WorkbookFactory.create(new FileInputStream(path));
			v=wb.getSheet(sheet).getRow(r).getCell(c).toString();
		}
		catch (Exception e) {
		}
		return v;
	}
	
}
