package sampleTests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;

import reusableFunctions.Configurations;
import reusableFunctions.ReportManagers;

 
public  class Listeners extends Configurations implements ITestListener{

	//ThreadLocal<ExtentTest> extent =new ThreadLocal<ExtentTest>();
	 //extends Configurations
		
	
	public Listeners() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	public void onTestStart(ITestResult result) {
	
	}
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		//extentTest.get().log(Status.PASS, "Test Passed");
		test.log(Status.PASS, "Test Passed");
    	//System.out.println("Listener success");
		//test.log(Status.PASS, "Test Passed");
	}
	public void onTestFailure(ITestResult result) {

	if (result.getStatus() ==  ITestResult.FAILURE){

		String testMethodName =result.getMethod().getMethodName();
		System.out.println( "Method Name: " + testMethodName);
		String filenm;
		try {
			WebDriver driver =null;
			driver =(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
			filenm = getScreenShotCapturedPath(testMethodName,driver);
			System.out.println(filenm);
			test.addScreenCaptureFromPath(filenm , testMethodName);
			 
		} catch (IOException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}	
 	}
  
//	@Override


	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}

}
