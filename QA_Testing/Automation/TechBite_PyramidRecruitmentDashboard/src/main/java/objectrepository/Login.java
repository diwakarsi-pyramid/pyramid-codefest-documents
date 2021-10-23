package objectrepository;

 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {

	
	WebDriver driver ;
	public Login(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy	(xpath ="//*[contains(text(),'Pyramid Recruitment Dashboard')]")
	WebElement welcomeheader;
	public WebElement getWelcomeHeader() {
		// TODO Auto-generated method stub
		return welcomeheader;
	} 
	
	@FindBy	(name ="UserName")
	WebElement txtUserName;
	public WebElement getUserName() {
		// TODO Auto-generated method stub
		return txtUserName;
	}
	@FindBy	(name ="Password")
	WebElement txtPassword;
	public WebElement getPassword() {
		// TODO Auto-generated method stub
		return txtPassword;
	}
	
	@FindBy	(xpath ="//button[contains(text(),'Login')]")
	WebElement btnLogin;
	public WebElement getLoginButton() {
		// TODO Auto-generated method stub
		return btnLogin;
	}
	
	
		
}
