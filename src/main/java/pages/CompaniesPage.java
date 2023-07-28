package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.SeleniumWrappers;

public class CompaniesPage extends SeleniumWrappers{

	public CompaniesPage(WebDriver driver) {
		super(driver);
	}

	public By searchField = By.id("company_filter_search");
	public By searchButton = By.cssSelector("button[class^='btn-top-filter']");
	public By coinTracker = By.linkText("CoinTracker");
	
}
