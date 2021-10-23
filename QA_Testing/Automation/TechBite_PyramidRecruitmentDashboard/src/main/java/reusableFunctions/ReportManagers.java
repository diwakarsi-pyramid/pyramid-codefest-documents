package reusableFunctions;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Properties;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportManagers {
	static ExtentReports extent;
	public static   ExtentReports getReportObjects() throws IOException {
		 
		HashMap<String,String> dt = new HashMap<>();
		dt=Configurations.getConfig();
		ExtentSparkReporter htmlReporter ;
		//System.out.println("Get Report started");
		//dt=Configurations.getConfig();	
		LocalDateTime myObj = LocalDateTime.now();
		String strtime =myObj.toString().replace(":", "_").replace(".", "");
		//+dt.get("ProjectName").replace(" ", "_")
		//System.out.println("Report Path" + System.getProperty("user.dir")+"\\TestResults"+"\\" + dt.get("ProjectName").replace(" ", "_")+ strtime +"_Reports.html" );
		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"\\TestResults"+"\\"  + strtime +"_Reports.html" );
		//System.out.println(dt.get("ProjectName"));
		htmlReporter.config().setDocumentTitle(dt.get("ProjectName"));  // dt.get("ProjectName")
		htmlReporter.config().setReportName(dt.get("ProjectName"));
		 
		htmlReporter.config().setTheme(Theme.STANDARD);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("NAME",dt.get("ProjectName")); 
		extent.setSystemInfo("OS", dt.get("OS"));
		//extent.setSystemInfo("API Host Name", strprop.getProperty("JIRAHOST")); 
		 
		extent.setSystemInfo("Environment", dt.get("strEnvironment")  ); //dt.get("strEnvironment")
		extent.setSystemInfo("Tester",  dt.get("ExecutedBy"));
		
		return extent;
	}
//	public static ExtentTest getTest(){
//		return test; 
//	}


	
	
		
		

}
