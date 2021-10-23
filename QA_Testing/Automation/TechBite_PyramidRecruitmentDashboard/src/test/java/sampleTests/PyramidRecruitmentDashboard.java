package sampleTests;


import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
 

import objectrepository.HomePage;
import objectrepository.LandingPage;
import objectrepository.Login;

import reusableFunctions.Configurations;
import reusableFunctions.ReportManagers;
import reusableFunctions.GenericFunctions;
import org.testng.annotations.Test;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import sampleTests.Listeners;
import java.io.*;
import java.lang.*;
import java.util.*;
public class PyramidRecruitmentDashboard extends Configurations  {
	public WebDriver driver;
	boolean blnPage = false;
	
	@BeforeSuite
	public void setData() throws IOException{
 		 getGenerateReport();
  	}
	@AfterSuite
	public void endReport(){
		extent.flush();
	}
	@BeforeTest 
	public void Initialize() throws IOException {
		 driver =InitializeDriver();
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
		//driver.close();
	}
	
	@AfterMethod
	public void teardown1(ITestResult testresult) throws IOException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException{

		if (testresult.getStatus() ==  ITestResult.FAILURE){
			test.log(Status.FAIL, "TEST CASE FAILED : '"+ testresult.getName() + "'");
			//test.log(Status.FAIL, "TEST CASE FAILED : '"+ testresult.getThrowable()+ "'");
		}
		else if (testresult.getStatus() ==  ITestResult.SUCCESS){
		}
		String testMethodName =testresult.getMethod().getMethodName();
		driver =(WebDriver)testresult.getTestClass().getRealClass().getDeclaredField("driver").get(testresult.getInstance());
		String filenm = getScreenShotCapturedPath(testMethodName,driver);
		test.addScreenCaptureFromPath(filenm , "OpeningBrowser");
	}
	
	@DataProvider(name = "GetLoginTest")
	public Object [] getLoginTest(){
		return new Object [] {"TC1_1"};
	}
		
	//@Test (priority = 1 , dataProvider ="GetLoginTest")
	public void VefifyMenuOptions(String testcasename) throws InterruptedException, IOException {
		try {
			  String strHost = dicConfig.get("HOST");
			  driver.get(strHost);
			  
			  getTestData("LoginTest", testcasename);
			  test = extent.createTest("Verify Dashboard and Verify Menu Options", "login Application");
			  Login lognPage = new Login(driver); 
			  blnPage = WaitForElement(lognPage.getUserName() );
 			  if (lognPage.getUserName().isDisplayed() )
			  {
				  test.log(Status.PASS, "Log in page is opened");
 				  getTestData("LoginTest", testcasename);
				  if (lognPage.getWelcomeHeader().getText().trim().contains(dicTestData.get("IP_strLoginWelcomeHeader")) ){
	  
					  test.log(Status.PASS, "Welcome Header displayed succesfully as:" + dicTestData.get("IP_strLoginWelcomeHeader")); 
				  }
				  else{
				  
					  test.log(Status.FAIL, "Welcome Header displayed succesfully as:" + dicTestData.get("IP_strLoginWelcomeHeader"));
				  }
				  lognPage.getUserName().sendKeys(dicConfig.get("UserName"));
				  lognPage.getPassword().sendKeys(dicConfig.get("Password"));
				  lognPage.getLoginButton().click();
			  }
			  else
			  {
				  test.log(Status.FAIL, "Login page is NOT opened");
			  }
 
			  HomePage hp = new HomePage(driver);
			  blnPage = WaitForElement(hp.getlnkJDProcessinglink());
			  if (blnPage )
			  {
				  test.log(Status.PASS, "Home page is opened");
			  }
			  else
			  {
				  test.log(Status.FAIL, "Home page is NOT opened");
			  }
			 
			  if (hp.getlnkJDProcessinglink().isDisplayed() ) {
				  test.log(Status.PASS, "Dashboard Menu is displayed on HOME page");
			 
			  }
 		      else{
 		    	 test.log(Status.FAIL, "Dashboard Menu is NOT displayed on HOME page");
			  }
			  
//			  if (hp.getadminlink().isDisplayed() ) {
//				  test.log(Status.PASS, "Admin Menu is displayed on HOME page");
//			 
//			  }
// 		      else{
// 		    	 test.log(Status.FAIL, "Admin Menu is NOT displayed on HOME page");
//			  }
		 }
		catch(Exception e){
			test.log(Status.FAIL, "Error Occured:" + e.getMessage() );
					
		}
	}
		
	@Test (priority =2 , dataProvider ="GetLoginTest")
	public void VeifyHomePage(String testcasename) throws InterruptedException, IOException {
		try {
			  String strHost = dicConfig.get("HOST");
			  driver.get(strHost);

			  getTestData("Verify Dashboard", testcasename);
			  test = extent.createTest("Verify Home Page", "Verify Home Page Dashboad Functionality");

//			  Login lognPage = new Login(driver); 
//			  blnPage = WaitForElement(lognPage.getUserName()  );
// 			  if (lognPage.getUserName().isDisplayed() )
//			  {
//				  test.log(Status.PASS, "Log page is opened");
// 				  getTestData("LoginTest", testcasename);
//				  if (lognPage.getWelcomeHeader().getText().trim().contains(dicTestData.get("IP_strLoginWelcomeHeader")) ){
//	  
//					  test.log(Status.PASS, "Welcome Header displayed succesfully as:" + dicTestData.get("IP_strLoginWelcomeHeader")); 
//				  }
//				  else{
//				  
//					  test.log(Status.FAIL, "Welcome Header displayed succesfully as:" + dicTestData.get("IP_strLoginWelcomeHeader"));
//				  }
//				  lognPage.getUserName().sendKeys(dicConfig.get("UserName"));
//				  lognPage.getPassword().sendKeys(dicConfig.get("Password"));
//				  lognPage.getLoginButton().click();
//			  }
//			  else
//			  {
//				  test.log(Status.FAIL, "Login page is NOT opened");
//			  }
			  
			  
			  Login lognPage = new Login(driver); 
			  blnPage = WaitForElement(lognPage.getWelcomeHeader()  );
 			  if (lognPage.getUserName().isDisplayed() )
			  {
				  test.log(Status.PASS, "Pyramid Recruitment Dashboard Application is opened");
 				  getTestData("LoginTest", testcasename);
				  if (lognPage.getWelcomeHeader().getText().trim().contains(dicTestData.get("IP_strLoginWelcomeHeader")) ){
	  
					  test.log(Status.PASS, "Welcome Header displayed succesfully as:" + dicTestData.get("IP_strLoginWelcomeHeader")); 
				  }
				  else{
				  
					  test.log(Status.FAIL, "Welcome Header displayed succesfully as:" + dicTestData.get("IP_strLoginWelcomeHeader"));
				  }
	
			  }
			  else
			  {
				  test.log(Status.FAIL, "Pyramid Recruitment Dashboard Application is Not opened");
			  }
			  
			  HomePage hp = new HomePage(driver);
			  blnPage = WaitForElement(hp.getlnkJDProcessinglink());
			  if (blnPage )
			  {
				  test.log(Status.PASS, "Home page is opened");
			  }
			  else
			  {
				  test.log(Status.FAIL, "Home page is NOT opened");
			  }
			  String strmenuOptionSearch = dicTestData.get("IP_strMenuSearch").trim();
			  String strmenuOptionname1 = dicTestData.get("IP_strMenuOption1").trim();
			  String strmenuOptionname2 = dicTestData.get("IP_strMenuOption2").trim();
			  String strmenuOptionname3 = dicTestData.get("IP_strMenuOption3").trim();
			  String strmenuOptionname4 = dicTestData.get("IP_strMenuOption4").trim();
			  if (hp.getlnkJDProcessinglink().isDisplayed() ) {
				  test.log(Status.PASS, "JD Processing Menu is displayed on HOME page");
							  }
 		      else{
 		    	 test.log(Status.FAIL, "MenuOption '" + strmenuOptionname1 + "' link is Not displayed");
			  }
			  
			  if (hp.getResumeProcessinglink().isDisplayed() ) {
				  test.log(Status.PASS, "Resume Processing Menu is displayed on HOME page");
				 			  }
 		      else{
 		    	 test.log(Status.FAIL, "MenuOption '" + strmenuOptionname2 + "' link is Not displayed");
			  }
			  
			  if (hp.getMatchingResumeslink().isDisplayed() ) {
				  test.log(Status.PASS, "Matching Resumes Menu is displayed on HOME page");
				 			  }
 		      else{
 		    	 test.log(Status.FAIL, "MenuOption '" + strmenuOptionname3 + "' link is Not displayed");
			  }
			  
			  if (hp.getCandidateDetailslink().isDisplayed() ) {
				  test.log(Status.PASS, "JD Processing Menu is displayed on HOME page");
				 			  }
 		      else{
 		    	 test.log(Status.FAIL, "MenuOption '" + strmenuOptionname4 + "' link is Not displayed");
			  }
			  
			  if (hp.getlnkJDProcessinglink().isDisplayed() ) {
				  test.log(Status.PASS, "Dashboard Menu option is displayed on HOME page");
				  hp.ClickOnLandingPageMenuOption(strmenuOptionSearch).click();
				  
				  test.log(Status.PASS, "MenuOption '" + strmenuOptionSearch + "' link is clicked");
			  }
 		      else{
 		    	 test.log(Status.FAIL, "MenuOption '" + strmenuOptionSearch + "' link is Not clicked");
			  }
			   
			  
			  test.log(Status.PASS, " <b> Verify Header values:</b> " );
			  test.log(Status.PASS, " Verify Job Descriptions Page Header: " + "<b>'" +  hp.getJDHeader().getText() + "'</b>" );
			  
			  test.log(Status.PASS, " Verify Add New Job Button text value: " + "<b>'" +  hp.getAddJDButton().getText() + "'</b>" );
			 
			  
//			  test.log(Status.PASS, " Current Week Header value: " + "<b>'" +  hp.getCurrWeek().getText()  + "'</b>");
//			  
//			  test.log(Status.PASS, " JD Header value: " + "<b>'" +  hp.getJD().getText() + "'</b>");
//			  test.log(Status.PASS, " Planned Date Header value: " + "<b>'" +  hp.getPlanneddate().getText() + "'</b>");
//			  test.log(Status.PASS, " Actual Date Header value: " + "<b>'" +  hp.getActualdate().getText() + "'</b>");
//			  test.log(Status.PASS, " Comment Header value: " + "<b>'" +  hp.getComment().getText() + "'</b>");
			  
			 // test.log(Status.PASS, " JD Column 1 value: " + "<b>'" +  hp.getJDValue1().getText() + "'</b>");
			 // test.log(Status.PASS, " Planned Date Column 1 value: " + "<b>'" +  hp.getPlanneddateValue1().getText() + "'</b>");
			 // test.log(Status.PASS, " Actual Date Column 1 value: " + "<b>'" +  hp.getActualdateValue1().getText() + "'</b>");
			 // test.log(Status.PASS, " Comment Column 1 value: " + "<b>'" +  hp.getCommentValue1().getText() + "'</b>");
			  
			  int totalrows = hp.getJDTable_RowCouont().size()-2;
			  test.log(Status.PASS, " Total JD table records are : " + "<b>'" +  totalrows  + "'</b>");
			  for (int i = 1 ; i<= totalrows; i++ ) {
			 
			  test.log(Status.PASS, " Job Name Column value at row "+ i  + " is " + "<b>'" +  hp.getJDTable_Columnvalue(i,"Job Name").getText() + "'</b>");
			  test.log(Status.PASS, " Mandatory Skills Column value at row "+ i  + " is " + "<b>'" +  hp.getJDTable_Columnvalue(i,"Mandatory Skills").getText() + "'</b>");
			  test.log(Status.PASS, " Good to have Skills Column value at row "+ i + " is " + "<b>'" +  hp.getJDTable_Columnvalue(i,"Good to have Skills").getText() + "'</b>");
			  test.log(Status.PASS, " Job Status Column value at row "+ i + " is " + "<b>'" +  hp.getJDTable_Columnvalue(i,"Job Status").getText() + "'</b>");
			  }
			  
			  hp.getAddJDButton().click();
			  
			  boolean blnJDwindow = false;
			  blnJDwindow = WaitForElement(hp.getJDWindow());
			  if (blnJDwindow )
			  {
				  test.log(Status.PASS, "JD window is opened to import JD");
				  
				  
				  hp.GetJDWindowTextbox().sendKeys(dicTestData.get("IP_strJDName"));
				 // hp.GetJDWindowCommentTextbox().sendKeys(dicTestData.get("IP_strMileStonComment"));
				   
				    String cwd = System.getProperty("user.dir");
					LocalDateTime myObj = LocalDateTime.now();
					String strtime =myObj.toString().replace(":", "_").replace(".", "");
					
					String path = cwd+"\\Import\\" ;
					//ProcessBuilder ( path + "Upload_FileRun.exe", path + dicTestData.get("IP_sJDFileName") );
					//Runtime.getRuntime().exec(path);
					
				    hp.GetSaveButton().click();
				  
			  }
			  else
			  {
				  test.log(Status.FAIL, "JD window is NOT opened");
			  }
			  
//			 int updatedtotalrows = hp.getJDTable_RowCouont().size()-2;
//			 if (updatedtotalrows> totalrows) {
//				 test.log(Status.PASS, "New Row is added"); 
//				 String strJD  = hp.getJD_Sprint_Columnvalue(updatedtotalrows,"JD").getText().toString().trim() ;
//				 String strExpJD  = dicTestData.get("IP_strJDName").toString().trim();
//				 String strJDComment  = hp.getJD_Sprint_Columnvalue(updatedtotalrows,"Comments").getText().toString().trim() ;
//				 String strExpJDComment  = dicTestData.get("IP_strMileStonComment").toString().trim();
//				 if(strJD.equals(strExpJD)   )
//				 {
//				 test.log(Status.PASS, " JD Name Column value at row "+ updatedtotalrows  + " is " + "<b>'" + strExpJD+ "'</b>");
//				
//				 }
//				 else {
//					 test.log(Status.FAIL, " JD Name Column value at row "+ updatedtotalrows  + " is NOT " + "<b>'" +  strExpJD + "'</b>"); 
//				 }
//				 
//				 // test.log(Status.PASS, " Planned Date Column value at row "+ updatedtotalrows  + " is " + "<b>'" +  hp.getJD_Sprint_Columnvalue(updatedtotalrows,"Planned Date").getText() + "'</b>");
//				//  test.log(Status.PASS, " Actual Date Column "+ updatedtotalrows + " is " + "<b>'" +  hp.getJD_Sprint_Columnvalue(updatedtotalrows,"Actual Date").getText() + "'</b>");
//				 
//				  if(strJDComment.equals(strExpJDComment)  )
//					 {
//					 test.log(Status.PASS, " JD Comment Column value at row "+ updatedtotalrows  + " is " + "<b>'" + strExpJDComment + "'</b>");
//					
//					 }
//					 else {
//						 test.log(Status.FAIL, " JD Comment Column value at row "+ updatedtotalrows  + " is NOT " + "<b>'" +  strExpJDComment+ "'</b>"); 
//					 }
//			 
//				  if(strJDComment.equals(strExpJDComment +"zFailTest")   )
//					 {
//					 test.log(Status.PASS, " JD Comment Column value at row "+ updatedtotalrows  + " is " + "<b>'" + strExpJDComment +"zFailTest" + "'</b>");
//					
//					 }
//					 else {
//						 test.log(Status.FAIL, " JD Comment Column value at row "+ updatedtotalrows  + " is NOT " + "<b>'" +  strExpJDComment +"zFailTest"+ "'</b>"); 
//			
//					 }
//				  
//			 }
//			 else
//			 {
//				 test.log(Status.FAIL, "New Row is NOT added"); 
//			 }
			  
		
		 }
		catch(Exception e){
			test.log(Status.FAIL, "Error Occured:" + e.getMessage() );
					
		}
		
	}
	
	
	


}
