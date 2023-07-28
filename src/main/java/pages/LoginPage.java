package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.SeleniumWrappers;

public class LoginPage extends SeleniumWrappers{

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	public By loginTab = By.linkText("Log in");
	public By closeButton = By.cssSelector("i[class='la la-times large']");
	
	public By username = By.id("ip_email");
	public By password = By.id("ip_password");
	public By signInButton = By.xpath("//button[text()='Sign in']");
	
	
	public void loginInApp(String user, String pass) {
		sendKeys(username, user);
		sendKeys(password, pass);
		click(signInButton);
	}	
}
