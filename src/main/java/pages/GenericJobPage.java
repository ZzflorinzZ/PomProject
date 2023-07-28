package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.SeleniumWrappers;

public class GenericJobPage extends SeleniumWrappers{

	public GenericJobPage(WebDriver driver) {
		super(driver);
	}

	
	public By applyNowButton = By.linkText("Apply now");
	public By companySection = By.cssSelector("div[class^='jobs-company-sidebar']");
	public By applyPopup = By.cssSelector("div[class='apply-popup']");
	public By callIcon = By.cssSelector("i[class='fal fa-phone-alt']");
}
