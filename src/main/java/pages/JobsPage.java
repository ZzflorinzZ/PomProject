package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.SeleniumWrappers;

public class JobsPage extends SeleniumWrappers{

	public JobsPage(WebDriver driver) {
		super(driver);
	}

	public By jobSearchField = By.cssSelector("input[name='jobs_filter_search']");
	public By jobSearchButton = By.cssSelector("button[class^='btn-top-filter']");
	public By testEngineerJob = By.linkText("Test Engineer at KeyTest");
	
}
