package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import utils.SeleniumWrappers;

public class MenuPage extends SeleniumWrappers{

	public MenuPage(WebDriver driver) {
		super(driver);
	}
	
	public By companies = By.xpath("//span[text()='Companies']");
	public By loginLink = By.linkText("Login");
	

}
