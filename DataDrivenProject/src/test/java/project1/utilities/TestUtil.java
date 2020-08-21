package project1.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;

import project1.base.TestBase;





public class TestUtil extends TestBase{
	
	public static String screenshotPath;
	
	public static String screenshotName;
	
	
	public static void captureScreenshot() throws IOException {
		
		
		
		
		//returns a file
		File srcFile =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		//copy file to a location
		
		//screenshotName ="error.png";
		
		Date d = new Date();
		
	
		
		String screenshotName =d.toString().replace(":", "_").replace(" ", "_") + ".png";
		
		
		
		FileUtils.copyFile(srcFile, new File("/Users/sathiyananddharmaraj/Desktop/eclipse-workspace/DataDrivenProject/target/surefire-reports/html/"+ screenshotName));
		//
		
	}
	
	@DataProvider(name ="dp")

	public Object [][]getdata(Method M){
		
		//Basic method to get dataprovider data from excel
		
		String sheetname =M.getName();
		
		int rows =Excel.getRowCount(sheetname);//sheetname
		
		System.out.println(rows);
		
		int cols=Excel.getColumnCount(sheetname);
		
		System.out.print(rows);
		
		System.out.print(cols);
		
		Object	[][]data =new Object[rows-1][cols];
		
		//Hashtable<String,String> table =null;
		
		for(int rowNum =2;rowNum<=rows;rowNum++) {
			
		//table = new Hashtable<String,String> ();
			
			for(int colNum=0;colNum<cols;colNum++) {
				
				//table.put(Excel.getCellData(sheetname, colNum, 1), Excel.getCellData(sheetname, colNum, rowNum));
				
				//Data will be stored in the form of data[0][0]
				
				data[rowNum-2][colNum]=Excel.getCellData(sheetname,colNum,rowNum);
				
			}
		}
		
		return data;
	

}
	
	public static boolean isTestRunnable(String testname,ExcelReader Excel) {
		
		String sheetname ="testsuite";
		
		int rows =Excel.getRowCount(sheetname);
		
		for(int rowNum = 2;rowNum<=rows;rowNum++) {
		
		String testcase =Excel.getCellData(sheetname, "TCID", rowNum);
		
		if(testcase ==testname);
		
		String runmode =Excel.getCellData(sheetname, "Runmode", rowNum);
		
		if(runmode.equalsIgnoreCase("Yes")) {
			 
			return true;
		}else {
			
			return false;
		}
			
		}
		return false;
	}
	
}
