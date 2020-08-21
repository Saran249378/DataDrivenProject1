package project1.listerners;


import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.relevantcodes.extentreports.LogStatus;

import project1.base.TestBase;
import project1.utilities.MonitoringMail;
import project1.utilities.TestConfig;
import project1.utilities.TestUtil;


public class CustomListeners extends TestBase implements ITestListener{
	
	String messageBody;

	public void onTestStart(ITestResult arg0) {
		
		//getting name of the teststeps which is stored in arg0
		
		test =Rep.startTest(arg0.getName());
	
	
	}

	public void onTestSuccess(ITestResult arg0) {
		
		test.log(LogStatus.PASS, arg0.getName().toUpperCase(),"PASS");
		
		Rep.endTest(test);
		Rep.flush();

		
	}

	public void onTestFailure(ITestResult arg0) {
		
		System.setProperty("org.uncommons.reportng.escape-output","false");
		
		try {
			TestUtil.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
         test.log(LogStatus.FAIL, arg0.getName().toUpperCase(),"Failed with Exception:" +arg0.getThrowable());
         
         test.addScreenCapture(TestUtil.screenshotName);
		
	

		Reporter.log("<a target =\"_blank\"href="+TestUtil.screenshotName+">Screenshot</a>");
		
		Reporter.log("<br>)");	
		
		//To show the image of the screenshot as Thumbnail
	  
		Reporter.log("<a target =\"_blank\"href="+TestUtil.screenshotName+"><img src="+TestUtil.screenshotName+"height=200 width=200></img></a>");
		
		Rep.endTest(test);
		Rep.flush();
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
	
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		
		MonitoringMail mail = new MonitoringMail();
		
		try {
			messageBody = "http://" + InetAddress.getLocalHost().getHostAddress()
					+ ":8080/job/DataDrivenLiveProject/Extent_Reports/";
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated method stub
		
	}

	

	
	
	
}