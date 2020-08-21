package project1.testCases;

import org.testng.annotations.Test;

import project1.base.TestBase;



public class OpenNewAccountTest extends TestBase {
  @Test
  public void openNewAccountTest()throws Throwable {
	  
	  
	  
	  click("OpenNewAccount_LINKTEXT");
	  
       log.debug("link clicked");
	  
	 select("type_CSS","SAVINGS");
	  
	  Thread.sleep(1000);
	  
	 // select("fromaccount_CSS",accountNo);
	  
	  click("openNewAccountbutton_CSS");
	 
	  
	  
	  
	  
  }
}
