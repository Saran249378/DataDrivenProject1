package project1.base;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import project1.utilities.ExcelReader;
import project1.utilities.ExtentManager;
import project1.utilities.TestUtil;


public class TestBase {
	
	//Initialization will be done here
	
	/* WebDriver-done
	 *logs-log 4j,log 4j.properties-done
	 *excel
	 *properties-done
	 *DB
	 *Extent Reports
	 *Mail
	 *
	
	 */
	
	public static WebDriver driver;
	
	public static Properties config = new Properties();
	
	public static Properties OR = new Properties();
	
	public static FileInputStream fis;
	
	public static Logger log = Logger.getLogger("devpinoyLogger");
	
	public static ExcelReader Excel = new ExcelReader((System.getProperty("user.dir")+"/src/test/resources/excel/project1.xlsx"));
	
	public static WebDriverWait wait;
	
	public static ExtentReports Rep = ExtentManager.getInstance();
	
	public static ExtentTest test;
	
	public static String browser;
		
	@BeforeSuite
	public void setUp(){
		
		if(driver==null){
			
			
			//user.dir fetches the file path upto DataDrivenproject
			
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/properties/config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				config.load(fis);
				log.debug("config file loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
	
			
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/properties/OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			try {
				OR.load(fis);
				log.debug("OR file loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
           if(System.getenv("browser")!=null && !System.getenv("browser").isEmpty()){
				
				browser = System.getenv("browser");
			}else{
				
				browser = config.getProperty("browser");
				
			}
			
			config.setProperty("browser", browser);
			
			
			
		    if(config.getProperty("browser").equals("chrome")) {
			
			System.setProperty("webdriver.chrome.driver","/Users/sathiyananddharmaraj/Desktop/eclipse-workspace/chromedriver");
			
			driver = new ChromeDriver();
			
			log.debug("chrome launced");
	
		}else if(config.getProperty("browser").equals("Safari")) {
			

			driver = new SafariDriver();
		}
		
		
		
		driver.get(config.getProperty("testsiteURL"));
		log.debug("NAvigated to" +config.getProperty("testsiteURL"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),TimeUnit.SECONDS);
		wait = new WebDriverWait(driver,5);
		}
	}
	
	public void click(String locator){
		
		if(locator.endsWith("_XPATH")) {
		
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		
	}else if(locator.endsWith("_CSS")) {
		
		driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		
	}else if(locator.endsWith("_ID")) {
		
		driver.findElement(By.id(OR.getProperty(locator))).click();
		
	}else if(locator.endsWith("_LINKTEXT")) {
		
		driver.findElement(By.linkText(OR.getProperty(locator))).click();
	}
		test.log(LogStatus.INFO, "clicking on :" +locator);
		
	}
	
	
	public void type(String locator,String value) {
		
	if(locator.endsWith("_ID")) {
		
		driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		
	}else if(locator.endsWith("_XPATH")) {
		
		driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		
	}
	test.log(LogStatus.INFO, " typing in:" +locator + value);
	}
	
	static WebElement dropdown;
	
	public void select(String locator,String value) {
		
		if(locator.endsWith("_ID")) {
			
			dropdown =driver.findElement(By.id(OR.getProperty(locator)));
			
		}else if(locator.endsWith("_XPATH")) {
			
			dropdown =driver.findElement(By.xpath(OR.getProperty(locator)));
			
		}else if(locator.endsWith("_CSS")) {
			
			dropdown=driver.findElement(By.cssSelector(OR.getProperty(locator)));
			
		}
		
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
		
		
		
		test.log(LogStatus.INFO, "Selecting from dropdown:" +locator + value);
		
	
	}
	
	
		
	
	
	
	public boolean isElementPresent(By by) {
		
		try {
			
			driver.findElement(by);
			return true;
			
		}catch(NoSuchElementException e) {
			
			return false;
		}
		
	}
	
	public static void verifyEquals(String actual,String expected) throws IOException {
		
		try {
			
			Assert.assertEquals(actual, expected);
			
			
		}catch(Throwable t) {
			
			TestUtil.captureScreenshot();
			
			//TestNG
			
			Reporter.log("<br>)"+"Verification failure :"+t.getMessage() +"<br>");	
			
			Reporter.log("<a target =\"_blank\"href="+TestUtil.screenshotName+"><img src="+TestUtil.screenshotName+"height=200 width=200></img></a>");
			
			Reporter.log("<br>)");	
			
			Reporter.log("<br>)");	
		  
			//ExtentReports
			
			  test.log(LogStatus.FAIL,"Failed with Exception:" +t.getMessage());
		         
		      test.addScreenCapture(TestUtil.screenshotName);
			
		}
		
	}
	

		
	
	
	@AfterSuite
	
	public void teardown() {
		
		if(driver!=null) {
			driver.quit();
		}
			
		log.debug("Test execution completed");
		
	}

}
