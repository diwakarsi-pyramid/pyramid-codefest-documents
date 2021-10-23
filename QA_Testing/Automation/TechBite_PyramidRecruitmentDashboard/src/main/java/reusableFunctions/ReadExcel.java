package reusableFunctions;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

    public static String filename 	= System.getProperty("user.dir") + "\\Resources\\TestData.xlsx";
    public  String path;
    public  FileInputStream fis = null;
    public  FileOutputStream fileOut =null;
    private static XSSFWorkbook workbook = null;
    private static XSSFSheet sheet = null;
    private static XSSFRow row   =null;
    private static XSSFCell cell = null;
     
    public ReadExcel(String path) {
         
        this.path=path;
        try {
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheetAt(0);
            fis.close();
        } catch (Exception e) {
             
        } 
    }
    
    // function is to return total rows in specific sheet
    public static int getRowCount(String sheetName){
        int id = workbook.getSheetIndex(sheetName);
        if(id==-1)
            return 0;
        else{
        sheet = workbook.getSheetAt(id);
        int number=sheet.getLastRowNum()+1;
        return number;
        }
         
    }
    
    public ArrayList<String> getAllSheetNames()
    {
    	ArrayList<String> lstSheets = new ArrayList<String>();
    	String SheetName = "";
    	try
    	{
    	int SheetsCount = workbook.getNumberOfSheets();
    	for(int SheetNum=0; SheetNum < SheetsCount; SheetNum++)
    	{
    		SheetName = workbook.getSheetName(SheetNum);
    		lstSheets.add(SheetName);
    	}
    	}
    	catch(Exception e)
    	{
    		 
    	}
    	return lstSheets;
    }
   
    // this function is used to read cell value by passing sheetName ,colName for specific row
    public static String getCellData(String sheetName,String colName,int rowNum){
        try{
            if(rowNum <=0)
                return "";
        int id = workbook.getSheetIndex(sheetName);
        int col_Num=-1;
        if(id==-1)
            return "";
        sheet = workbook.getSheetAt(id);
        row=sheet.getRow(0);
        for(int i=0;i<row.getLastCellNum();i++){
             if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
                col_Num=i;
        }
        if(col_Num==-1)
            return "";
         
        sheet = workbook.getSheetAt(id);
        row = sheet.getRow(rowNum-1);
        if(row==null)
            return "";
        cell = row.getCell(col_Num);
         
        if(cell==null)
            return "";
        //System.out.println(cell.getCellType());
        if(cell.getCellType()== CellType.STRING)
              return cell.getStringCellValue();
        else if(cell.getCellType()==CellType.NUMERIC || cell.getCellType()==CellType.FORMULA ){
               
              String cellText  = String.valueOf(cell.getNumericCellValue());
              if (DateUtil.isCellDateFormatted(cell)) {
                   // format in form of M/D/YY
                  double d = cell.getNumericCellValue();

                  Calendar cal =Calendar.getInstance();
                  cal.setTime(DateUtil.getJavaDate(d));
                    cellText =
                     (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
                   cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" +
                              cal.get(Calendar.MONTH)+1 + "/" + 
                              cellText;
                 }
           return cellText;
          }else if(cell.getCellType()==CellType.BLANK)
              return ""; 
          else
              return String.valueOf(cell.getBooleanCellValue());
         
        }
        catch(Exception e){
          return "row "+rowNum+" or column "+colName +" not found in excel sheet";
        }
    }
     
    public String getCellData(String sheetName,int colNum,int rowNum){
        try{
            if(rowNum <=0)
                return "";
         
        int id = workbook.getSheetIndex(sheetName);

        if(id==-1)
            return "";
         
     
        sheet = workbook.getSheetAt(id);
        row = sheet.getRow(rowNum-1);
        if(row==null)
            return "";
        cell = row.getCell(colNum);
        if(cell==null)
            return "";
         
      if(cell.getCellType()==CellType.STRING)
          return cell.getStringCellValue();
      else if(cell.getCellType()==CellType.NUMERIC || cell.getCellType()==CellType.FORMULA ){
           
          String cellText  = String.valueOf(cell.getNumericCellValue());
          if (DateUtil.isCellDateFormatted(cell)) {
               // format in form of M/D/YY
              double d = cell.getNumericCellValue();

              Calendar cal =Calendar.getInstance();
              cal.setTime(DateUtil.getJavaDate(d));
                cellText =
                 (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
               cellText = cal.get(Calendar.MONTH)+1 + "/" +
                          cal.get(Calendar.DAY_OF_MONTH) + "/" +
                          cellText;
              }
          return cellText;
      }else if(cell.getCellType()==CellType.BLANK)
          return "";
      else
          return String.valueOf(cell.getBooleanCellValue());
        }
        catch(Exception e){
             
            e.printStackTrace();
            return "row "+rowNum+" or column "+colNum +" not found in excel sheet";
        }
    }
     
   
  // find whether sheets exists 
    public boolean isSheetExist(String sheetName){
        int index = workbook.getSheetIndex(sheetName);
        if(index==-1){
            index=workbook.getSheetIndex(sheetName.toUpperCase());
                if(index==-1)
                    return false;
                else
                    return true;
        }
        else
            return true;
    }
     
    // returns number of columns in a sheet 
    public int getColumnCount(String sheetName){
        // check if sheet exists
        if(!isSheetExist(sheetName))
         return -1;
         
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(0);
         
        if(row==null)
            return -1;
         
        return row.getLastCellNum();
        
    }
   
    public static int getCellRowNum(String sheetName,String colName,String cellValue){
         
        for(int i=2;i<=getRowCount(sheetName);i++){
            if(getCellData(sheetName,colName , i).equalsIgnoreCase(cellValue)){
                return i;
            }
        }
        return -1;
         
    }
    
 	
	
}
