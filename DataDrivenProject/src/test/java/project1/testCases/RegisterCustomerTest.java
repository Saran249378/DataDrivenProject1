package project1.testCases;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import project1.base.TestBase;
import project1.utilities.TestUtil;

public class RegisterCustomerTest extends TestBase {
  
	@Test(dataProviderClass=TestUtil.class,dataProvider="dp")
  public void registerCustomerTest(String firtsName,String LastName,String Address,String city,String state,String Zipcode,
		  String phonenumber,String SSN,String Username,String Password,String confirmpassword,String WelcomeText ) throws InterruptedException {
		
		SoftAssert softassert = new SoftAssert();
		
		type("FirstName_ID",firtsName);
		type("LastName_ID",LastName);
		type("Address_ID", Address);
		type("city_ID",city);
		type("state_ID",state);
		type("Zipcode_ID",Zipcode);
		type("phonenumber_XPATH",phonenumber);
		type("SSN_XPATH",SSN);
		type("Username_ID",Username);
		type("Password_ID",Password);
		type("confirmpassword_ID",confirmpassword);
		click("Registerbutton_XPATH");
	
		
		/*driver.findElement(By.id(OR.getProperty("FirstName"))).sendKeys(firtsName);
		driver.findElement(By.id(OR.getProperty("LastName"))).sendKeys(LastName);;
		driver.findElement(By.id(OR.getProperty("Address"))).sendKeys(Address);;
		driver.findElement(By.id(OR.getProperty("city"))).sendKeys(city);;
		driver.findElement(By.id(OR.getProperty("state"))).sendKeys(state);;
		driver.findElement(By.id(OR.getProperty("Zipcode"))).sendKeys(Zipcode);;
		driver.findElement(By.xpath(OR.getProperty("phonenumber"))).sendKeys(phonenumber);
		driver.findElement(By.xpath(OR.getProperty("SSN"))).sendKeys(SSN);;
		driver.findElement(By.id(OR.getProperty("Username"))).sendKeys(Username);;
		driver.findElement(By.id(OR.getProperty("Password"))).sendKeys(Password);;
		driver.findElement(By.id(OR.getProperty("confirmpassword"))).sendKeys(confirmpassword);;
		driver.findElement(By.xpath(OR.getProperty("Registerbutton"))).click();*/
		
		
		
		softassert.assertTrue(driver.getTitle().contains(WelcomeText));
	
		log.debug("customer Registered succesfully");
		
		softassert.assertAll();
		
		
		
		
		
	}
	

		
}



	
	
	  
	  
	  
	  
  
