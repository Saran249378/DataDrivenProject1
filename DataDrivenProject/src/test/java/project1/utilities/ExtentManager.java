package project1.utilities;

import java.io.File;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	
	public static ExtentReports Extent;
	
public static ExtentReports getInstance() {
	
	if(Extent==null) {
	
	Extent = new ExtentReports(System.getProperty("user.dir")+"/target/surefire-reports/html/Extent.html",true,DisplayOrder.OLDEST_FIRST);
	
	Extent.loadConfig(new File(System.getProperty("user.dir")+ "/src/test/resources/extentconfig/ReportsConfig.xml"));
	
}
	
return Extent;

}
}
