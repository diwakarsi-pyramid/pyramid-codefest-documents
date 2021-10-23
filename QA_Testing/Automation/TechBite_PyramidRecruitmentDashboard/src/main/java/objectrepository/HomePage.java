package objectrepository;

 
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	
	WebDriver driver ;
	public HomePage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy	(linkText ="JD Processing")
	WebElement lnkJDProcessing;
	
	@FindBy	(linkText ="Resume Processing")
	WebElement lnkResumeProcessing;
	
	@FindBy	(linkText ="Matching Resumes")
	WebElement lnkMatchingResumes;
	
	@FindBy	(linkText ="Candidate Details")
	WebElement lnkCandidateDetails;    
	
	//            
	
	@FindBy	(xpath  ="//button[@class='btn btn-success']")
	WebElement btnAddJDButton;
	
	@FindBy	(xpath  ="//div[@class='content-header']/child::h3")
	WebElement divJDHeader;
	
	@FindBy	(xpath  ="//div[@class='content-header']/child::div[4]/div[2]")
	WebElement divcurrweek;
	
	@FindBy	(xpath  ="//div[@class='col-5']/div[1]/child::div[1]/a")
	WebElement addJDWindow;
	
	@FindBy	(xpath  ="//div[text()='Add new JDWindow']")
	WebElement getJDWindow;
	
	@FindBy	(name ="JDWindowName")
	WebElement editJDWindowName;
	
	@FindBy	(name ="JDWindowComment")
	WebElement editJDWindowComment;
	@FindBy	(xpath  ="//button[text()='Save']")
	WebElement getSavebutton;
	
	
	@FindBy	(xpath  ="//div[@class='col-5']/app-project-JDWindow[1]/child::div[1]/div[1]")
	WebElement divJDWindow;
	
	@FindBy	(xpath  ="//div[@class='col-5']/app-project-JDWindow[1]/child::div[1]/div[2]")
	WebElement divplanneddate;
	
	@FindBy	(xpath  ="//div[@class='col-5']/app-project-JDWindow[1]/child::div[1]/div[3]")
	WebElement divactualdate;
	
	@FindBy	(xpath  ="//div[@class='col-5']/app-project-JDWindow[1]/child::div[1]/div[4]")
	WebElement divcomment;
	
	@FindBy	(xpath  = "//div[@class='col-5']/app-project-JDWindow[1]/child::div[2]/div[1]")
	WebElement divJDWindow1;
	
	@FindBy	(xpath  ="//div[@class='col-5']/app-project-JDWindow[1]/child::div[2]/div[2]")
	WebElement divplanneddate1;
	
	@FindBy	(xpath  ="//div[@class='col-5']/app-project-JDWindow[1]/child::div[2]/div[3]")
	WebElement divactualdate1;
	
	@FindBy	(xpath  = "//div[@class='col-5']/app-project-JDWindow[1]/child::div[2]/div[4]")
	WebElement divcomment1;
	
 
	
	public WebElement getlnkJDProcessinglink() {
		// TODO Auto-generated method stub
		return lnkJDProcessing;
	}

	public WebElement getResumeProcessinglink() {
		// TODO Auto-generated method stub
		return lnkResumeProcessing;
	}
	
	public WebElement getMatchingResumeslink() {
		// TODO Auto-generated method stub
		return lnkMatchingResumes;
	}
	
	public WebElement getCandidateDetailslink() {
		// TODO Auto-generated method stub
		return lnkCandidateDetails;
	}
	
	public WebElement getJDHeader() {
		// TODO Auto-generated method stub
		return divJDHeader;
	}
	
	public WebElement getAddJDButton() {
		// TODO Auto-generated method stub
		return btnAddJDButton;
	}
	
	public WebElement getCurrWeek() {
		// TODO Auto-generated method stub
		return divcurrweek;
	}
	
	public WebElement getJDWindow() {
		// TODO Auto-generated method stub
		return getJDWindow;
	}
	
	public WebElement getPlanneddate() {
		// TODO Auto-generated method stub
		return divplanneddate;
	}
	
	public WebElement getActualdate() {
		// TODO Auto-generated method stub
		return divactualdate;
	}
	
	public WebElement getComment() {
		// TODO Auto-generated method stub
		return divcomment;
	}
	
	
	public WebElement getJDWindowValue1() {
		// TODO Auto-generated method stub
		return divJDWindow1;
	}
	
	public WebElement getPlanneddateValue1() {
		// TODO Auto-generated method stub
		return divplanneddate1;
	}
	
	public WebElement getActualdateValue1() {
		// TODO Auto-generated method stub
		return divactualdate1;
	}
	
	public WebElement getCommentValue1() {
		// TODO Auto-generated method stub
		return divcomment1;
	}
	public List<WebElement> getJDTable_RowCouont() {
	//	 "//div[@class='col-5']/app-project-JDWindow[1]/child::div[2]"
		List<WebElement> ele;
		ele = driver.findElements(By.xpath("//div[@class='p-datatable-wrapper']/table/tbody/tr"));
		return ele;
		 
	}	
	public WebElement ClickAddLink() {
		// TODO Auto-generated method stub
		return addJDWindow;
	}
	
	public WebElement GetJDWindowWindow() {
		// TODO Auto-generated method stub
		return getJDWindow;
	}
	
	public WebElement GetJDWindowTextbox() {
		// TODO Auto-generated method stub
		return editJDWindowName;
	}
	public WebElement GetJDWindowCommentTextbox() {
		// TODO Auto-generated method stub
		return editJDWindowComment;
	}
	public WebElement GetSaveButton() {
		// TODO Auto-generated method stub
		return getSavebutton;
	}
	
	 
	 
	public WebElement getJDTable_Columnvalue(int row_num ,String colname) {
		int rown = row_num +1 ;
		WebElement ele;
		switch(colname) {
		case "Job Name":
			 ele = driver.findElement(By.xpath("//div[@class='p-datatable-wrapper']/table/tbody/tr[" + rown +" ]/td[1]"));
			return ele;
		case "Mandatory Skills":
			ele = driver.findElement(By.xpath("//div[@class='p-datatable-wrapper']/table/tbody/tr[" + rown +" ]/td[2]"));
			return ele;
		case "Good to have Skills":
			ele = driver.findElement(By.xpath("//div[@class='p-datatable-wrapper']/table/tbody/tr[" + rown +" ]/td[3]"));
			return ele;
		case "Job Status":
			ele = driver.findElement(By.xpath("//div[@class='p-datatable-wrapper']/table/tbody/tr[" + rown +" ]/td[4]"));
			return ele;
		 default:
		  return null;
		}
	}	
	
	public WebElement ClickOnLandingPageMenuOption(String Menuname) {
		switch(Menuname) {
		case "JD Processing":
			return lnkJDProcessing;
		case "ResumeProcessing":
			return lnkResumeProcessing;
		case "MatchingResumes":
			return lnkMatchingResumes;
		case "CandidateDetails":
			return lnkCandidateDetails;	
		 default:
		  return null;
		}
	
	}		
}
