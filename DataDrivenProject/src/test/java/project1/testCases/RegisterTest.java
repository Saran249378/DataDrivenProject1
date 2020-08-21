package project1.testCases;

import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.By;
import project1.base.TestBase;

public class RegisterTest extends TestBase {
	
	

	@Test
	
	public void registerTest() throws InterruptedException, IOException {
		
		//This System property is to show screenshotimage in html reports
		
		//System.setProperty("org.uncommons.reportng.escape-output","false");
		
		String actual = driver.getTitle();
		
		verifyEquals(actual,"ParaBank | Welcome | Online Banking");
		
		click("Register_XPATH");
		
		Thread.sleep(1000);
		
		log.debug("Register clicked");
		
		String actualtitle =driver.getTitle();
		
		System.out.println(actualtitle);
		
		Assert.assertTrue("Not navigated to Register", isElementPresent(By.id(OR.getProperty("FirstName_ID"))));
		
		log.debug("Register clicked succesfully");
		
		//customizing reportng message

		Reporter.log("Register clicked succesfully");
		
		//To report Screenshot in reportng html 
		
		//target is to get the screenshot in new tab
		
		//Reporter.log("<a target =\"_blank\"href=\"/Users/sathiyananddharmaraj/Desktop/eclipse-workspace/Screen Shot 2020-08-08 at 5.38.36 PM.png\">Screenshot</a>");
				
		//Reporter.log("<br>)");	
		
		//To show the image of the screenshot as Thumbnail
	  
		//Reporter.log("<a target =\"_blank\"href=\"/Users/sathiyananddharmaraj/Desktop/eclipse-workspace/Screen Shot 2020-08-08 at 5.38.36 PM.png\"><img src=\"/Users/sathiyananddharmaraj/Desktop/eclipse-workspace/Screen Shot 2020-08-08 at 5.38.36 PM.png\"height=200 width=200></img></a>");
	
		
		//To fail the Testcase
		
	}	
		
	
	}
		
		
		
	
	



	
  
  
		
  

	