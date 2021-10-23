package reusableFunctions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.Statement;

public class GenericFunctions {
	public  WebDriver driver = null;
	public static HashMap<String, String> dicTestData = new HashMap<String, String>();
	public static HashMap<String,String> dicConfig = new HashMap<>();
	public static HashMap<String,String> dicCommon = new HashMap<>();
	//This function is fetching configuration parameters from config sheet.
	public static  HashMap<String,String> getConfig() throws IOException {
		HashMap<String,String> dicConfig = new HashMap<>();
		//Fetch configuration from excel file config
		String strpath	= System.getProperty("user.dir") + "\\Resources\\config.xlsx";
		//System.out.println(strpath);
		FileInputStream fis = new FileInputStream(new File(strpath));
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("Config");
	 	Iterator<Row> iterator = sh.iterator();
		while(iterator.hasNext()){
			Row currentrow = iterator.next();
			Iterator<Cell> celliterator = currentrow.iterator();
				Cell currentcell1 = celliterator.next();
				Cell currentcell2 = celliterator.next();
				if (currentcell2.getCellType()== CellType.STRING  ) {
				dicConfig.put(currentcell1.getStringCellValue(),currentcell2.getStringCellValue());
				}
				else if(currentcell2.getCellType()==CellType.NUMERIC ){
				
				dicConfig.put(currentcell1.getStringCellValue(),String.valueOf(currentcell2.getNumericCellValue() ));
				}
		}
		wb.close();
		
		return dicConfig;
}
	
	public static  HashMap<String,String> getCommon() throws IOException {
		HashMap<String,String> dicCommon = new HashMap<>();
		//Fetch configuration from excel file Common
		String strpath	= System.getProperty("user.dir") + "\\Resources\\config.xlsx";
		FileInputStream fis = new FileInputStream(new File(strpath));
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("Common");
	 	Iterator<Row> iterator = sh.iterator();
		while(iterator.hasNext()){
			Row currentrow = iterator.next();
			Iterator<Cell> celliterator = currentrow.iterator();
				Cell currentcell1 = celliterator.next();
				Cell currentcell2 = celliterator.next();
				if (currentcell2.getCellType()== CellType.STRING  ) {
					dicCommon.put(currentcell1.getStringCellValue(),currentcell2.getStringCellValue());
				}
				else if(currentcell2.getCellType()==CellType.NUMERIC ){
				
					dicCommon.put(currentcell1.getStringCellValue(),String.valueOf(currentcell2.getNumericCellValue() ));
				}
		}
		wb.close();
		
		return dicCommon;
	}	
	
	public static  HashMap<String,String> getDB(String sql) throws IOException, SQLException {
		dicCommon = getCommon();
		HashMap<String,String> dicDB = new HashMap<>();
		
		
		String ConnectionString = dicCommon.get("ConnectionString");
		//	Connection con=DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/demo", "root", "root");
		Connection con= DriverManager.getConnection(ConnectionString);
		Statement s=con.createStatement();

		//ResultSet rs=s.executeQuery("select * from credentials where scenario ='rewardscard'");
		ResultSet rs=s.executeQuery(sql);
		while(rs.next())
		{
		System.out.println( rs.getString("username"))  ; 
		System.out.println( rs.getString("password")) ; 

		dicDB.put("username", rs.getString("username"));
		dicDB.put("password", rs.getString("password"));
		
		return dicDB;

		}	
		

		//Fetch configuration from excel file Common
		String strpath	= System.getProperty("user.dir") + "\\Resources\\config.xlsx";
		FileInputStream fis = new FileInputStream(new File(strpath));
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("Common");
	 	Iterator<Row> iterator = sh.iterator();
		while(iterator.hasNext()){
			Row currentrow = iterator.next();
			Iterator<Cell> celliterator = currentrow.iterator();
				Cell currentcell1 = celliterator.next();
				Cell currentcell2 = celliterator.next();
				if (currentcell2.getCellType()== CellType.STRING  ) {
					dicCommon.put(currentcell1.getStringCellValue(),currentcell2.getStringCellValue());
				}
				else if(currentcell2.getCellType()==CellType.NUMERIC ){
				
					dicCommon.put(currentcell1.getStringCellValue(),String.valueOf(currentcell2.getNumericCellValue() ));
				}
		}
		wb.close();
		
		return dicCommon;
	}	
	//This function is fetching Test Data parameters from Test data file and sheet.
	public final void getTestData(String SheetName, String testcasename)
	{String DataSheetPath = "";
	//String SheetName = "CodeIgnite";   
	String ColumnName = "";
	String CellValue = "";	
	int RowNumber = -1;
	ReadExcel reader;
	try
	{   
		DataSheetPath = System.getProperty("user.dir") + "\\Resources\\TestData.xlsx";
		reader = new  ReadExcel(DataSheetPath);      
		RowNumber = ReadExcel.getCellRowNum(SheetName, "TestCaseName", testcasename); // Getting row number of the required testCase
		if(RowNumber != -1)  // Execute only if there is a row for the current testCase
		{
			int ColumnsCount = reader.getColumnCount(SheetName); // Getting number of columns in the sheet.
			for(int i = 0; i<=ColumnsCount; i++)                    
			{	// Fetching Column name of the i'th column in front of the testCase's row
				ColumnName = reader.getCellData(SheetName, i, 1) ; 
				 // Fetching value of the above column for the required testCase row
				CellValue = reader.getCellData(SheetName, i, RowNumber);   
				// Putting the above key value pair in dicTestData to be used in the testScripts
				dicTestData.put(ColumnName, CellValue);                                       
				
			}
		}
	}
	catch (RuntimeException e)
	{
		 
	}
	
	}	
	// This function is used to convert string value in specific date formats : yyyy/MM/dd H:mm | MM/dd/yyyy | MM-dd-yyyy | M-d-yyyy | H:mm:ss | H:mm
	public final String FetchDateTimeInSpecificFormat(String format)
	{
		String dateTime = "";
		try
		{
			DateFormat dateFormat = new SimpleDateFormat(format);
			Calendar objCalendar = Calendar.getInstance();
			dateTime = dateFormat.format(objCalendar.getTime());
		}
		catch(Exception e)
		{
			
		}
		return dateTime;
	}
	//This function is used to synchronize for WebElement.
	public final boolean WaitForElement(WebElement WebObject)
	{
		return WaitForElement(WebObject, 60);
	}
	//This function is used to synchronize for WebElement.
	public final boolean WaitForElement(WebElement WebObject, int iTimeout)
	{
		boolean flag = false;
		try
		{     
			for (int i = 0; i <= iTimeout; i++)
			{
				if (WebObject.isDisplayed())
				{
					flag = true;
					break;
				}
				else
				{    System.out.println(dicConfig.get("WaitTime"));
					 Thread.sleep(Integer.parseInt(  dicConfig.get("WaitTime")) * 1000);
					 System.out.println("FALSE" + Integer.toString(iTimeout) + WebObject.isDisplayed());
				}
			}
		}
		catch (Exception e)
		{
			flag = false;
		}
		return flag;
	}
	//This function is used to check checkbox
	public final boolean SelectCheckbox(WebElement element)
	{
		boolean flag = false;
		try
		{
			if (!element.isSelected())
			{
				element.click();
				flag = true;
			}
			else if (element.isSelected())
			{
				flag = true;
			}
			else
			{
				throw new RuntimeException("Could not select Checkbox.");
			}
		}
		catch (RuntimeException e)
		{
			flag = false;
			 
		}
		return flag;
	}
	//This function is used to uncheck checkbox
	public final boolean DeSelectCheckbox(WebElement element)
	{
		boolean flag = false;
		try
		{
			if (element.isSelected())
			{
				element.click();
				if (!element.isSelected())
				{
					flag = true;
				}
				else
				{
					throw new RuntimeException("Could not uncheck checkbox.");
				}
			}
			else if (!element.isSelected())
			{
				flag = true;
			}
			else
			{
				throw new RuntimeException("Could not get the check-status of checkbox");
			}
		}
		catch (RuntimeException e)
		{
			flag = false;
			 
		}
		return flag;
	}
	//This function is to read celldata
	public final String GetCellData(WebElement WebtableElement, Integer irow, Integer icol)
 	{
		String strcellvalue = "";
		try  {
			
			Integer rowcount = 0;
			Integer columncount = 0;
			 String strclass=WebtableElement.getAttribute(("class"));
			 rowcount= driver.findElements(By.xpath("//table[@class='"+ strclass+ "']/tbody/tr")).size();
			System.out.println(rowcount);
			for (int i = 2; i <= rowcount; i++)
				{
					if (i == irow)
					{
						columncount = driver.findElements(By.xpath("//table[@class='"+ strclass+ "']/tbody/tr[" + i + "]/td")).size();
						System.out.println(columncount);
						for (int j = 1; j <= columncount; j++)
						{
							if (j == icol)
							{
								strcellvalue = driver.findElement(By.xpath("//table[@class='"+ strclass+ "']/tbody/tr[" + i + "]/td[" + j + "]")).getText() ;
								System.out.println(strcellvalue);
								break;
							}
						}
					}
	
				} 
			return strcellvalue;
		}
		catch(Exception e){
			//test.log(Status.FAIL, "Error:" + e.getMessage() );
		}
			return strcellvalue;
  	}	
	//This function is used to count row count
 	public int GetRowCount(WebElement WebtableElement)
 	{
 		Integer rowcount = 0;
 		try
 		{   String strclass=WebtableElement.getAttribute(("class"));
 			rowcount= driver.findElements(By.xpath("//table[@class='"+ strclass+ "']/tbody/tr")).size();
 			return rowcount;
 		}
 		catch (Exception e)
 		{
 			return rowcount;
 		}
 	}	
 	// This function is used to get column header name by passing column seq
	public String GetColumnHeaderName(WebElement WebtableElement, Integer icol)
	{
		String strColumnHeaderName = "";
		try
		{
			Integer columncount = 0;
			String strclass=WebtableElement.getAttribute(("class"));
			 
			columncount = driver.findElements(By.xpath("//table[@class='"+ strclass+ "']/tbody/tr[1]/th")).size();
			for (int i = 1; i <= columncount; i++)
			{
				if (i == icol)
				{
					strColumnHeaderName = driver.findElement(By.xpath("//table[@class='"+ strclass+ "']/tbody/tr[1]/th[" + i + "]")).getText();
					break;
				}
			}
			return strColumnHeaderName;
		}
		catch (Exception e)
		{
			return strColumnHeaderName;
		}

	}
	// This function is used to get column header seq by passing column name
	public int GetColumnHeaderSequenceNumer(WebElement WebtableElement, String strheader)
	{
		String strColumnHeaderName = "";
		Integer strColumnHeaderSeq = 0;
		try
		{
			Integer columncount = 0;
			String strclass=WebtableElement.getAttribute(("class"));
			 
			columncount = driver.findElements(By.xpath("//table[@class='"+ strclass+ "']/tbody/tr[1]/th")).size();
			for (int icounter = 1; icounter <= columncount; icounter++)
			{
				strColumnHeaderName = driver.findElement(By.xpath("//table[@class='"+ strclass+ "']/tbody/tr[1]/th[" + icounter + "]")).getText();
				if (strColumnHeaderName.equals(strheader.trim()))
				{
					strColumnHeaderSeq = icounter;
					break;
				}
			}

			return strColumnHeaderSeq;
		}
		catch (Exception e)
		{
			 
			return strColumnHeaderSeq;
		}

	}
	
	public final void KillObjectInstances(String ProcessName)
	{
		try 
		{
			Runtime.getRuntime().exec("taskkill /F /IM " + ProcessName);
		}
		catch (IOException e)
		{
			 
		}
	}
	
	
	
}
