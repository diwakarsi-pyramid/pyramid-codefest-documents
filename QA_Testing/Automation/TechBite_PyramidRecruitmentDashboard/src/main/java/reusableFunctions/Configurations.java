package reusableFunctions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.utils.FileUtil;
public class Configurations extends GenericFunctions {
 
	public static ExtentReports extent;
	public static ExtentTest test;
	
 
	
	protected static void getGenerateReport() throws IOException {
		dicConfig = getConfig();
		 extent = ReportManagers.getReportObjects();	    
		 //test = extent.createTest("Set Data Execution Started", "Execution is Started");
	}
	public  WebDriver InitializeDriver () throws IOException {
		
//		 Properties prop;
		  String cwd = System.getProperty("user.dir");
//		 // WebDriver driver = null;
		  
		
		//String browser_name = 	prop.getProperty("BrowserType").toString().toUpperCase();
		String browser_name = 	dicConfig.get("strBrowserType").toLowerCase().toUpperCase();
	
		switch(browser_name) {
		
		case "CHROME":
			KillObjectInstances("chrome.exe");
			System.setProperty("webdriver.chrome.driver", cwd + "\\Resources\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			System.out.println("Chrome");
			//test = extent.createTest("Chrome Browser Open", "Chrome Browser is opened");
			break;
			 
		case "IE":
			KillObjectInstances("iexplore.exe");
			System.setProperty("webdriver.ie.driver", cwd + "\\Resources\\Drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			//test = extent.createTest("IE Browser Open", "IE Browser is opened");
			break;
		case "FIREFOX":
			 KillObjectInstances("firefox.exe");
			System.setProperty("webdriver.gecko.driver", cwd + "\\Resources\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			//test = extent.createTest("Firefox Browser Open", "Firefox Browser is opened");
			break;
	//	default :
			//
			}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
		return driver;
	}

public String getScreenShotCapturedPath(String testCaseName,WebDriver driver) throws IOException {
	String cwd = System.getProperty("user.dir");
	LocalDateTime myObj = LocalDateTime.now();
	String strtime =myObj.toString().replace(":", "_").replace(".", "");
	String path = cwd+"\\CaptureScreenprints\\"+testCaseName +strtime+".png";
	//System.out.println(filename);
	File objFile = new File(path);
	//Convert web driver object to TakeScreenshot
	TakesScreenshot ts = ((TakesScreenshot)driver);
	//Call getScreenshotAs method to create image file
	File capFile = ts.getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile( capFile,objFile);	
	return path;
}





}
