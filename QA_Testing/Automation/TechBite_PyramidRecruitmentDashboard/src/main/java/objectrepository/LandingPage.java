package objectrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {
	
	
	WebDriver driver ;
	public LandingPage(WebDriver driver) {
		this.driver = driver;
	}
	By signin = By.xpath("//*[@class ='signin']");
	public WebElement signin() {
		return driver.findElement(signin);
	}
	By homebutton = By.xpath("//button[text()='Home']");
	By Practicebutton = By.xpath("//button[text()='Practice']");
	By Loginbutton = By.xpath("//button[text()='Login']");
	By Signupbutton = By.xpath("//button[text()='Signup']");
	
	
	public WebElement ClickOnLandingPageButton(String buttonname) {
		switch(buttonname) {
		case "Home":
			return driver.findElement(homebutton);
		case "Practice":
			return driver.findElement(Practicebutton);
		case "Login":
			return driver.findElement(Loginbutton);
		case "Signup":
			return driver.findElement(Signupbutton);	
		 default:
		  return null;
		}
		 
		
		

	}
	
}
