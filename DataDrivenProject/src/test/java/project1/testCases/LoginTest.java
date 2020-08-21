package project1.testCases;

import org.junit.Assert;
import org.testng.annotations.Test;



import project1.base.TestBase;
import project1.utilities.TestUtil;



public class LoginTest extends TestBase{
	
	
	@Test(dataProviderClass=TestUtil.class,dataProvider ="dp")
	  
	   public void loginTest(String Username,String Password) {
		
		log.debug("logging in");
		
		type("username_XPATH",Username);
		type("password_XPATH",Password);
		click("loginbutton_XPATH");
		
		
		log.debug("logged in");
		
		Assert.fail();
		
		
		
	}
	
	
	
	
}
	
	
	
	
	
	

	
	
  
  
 


