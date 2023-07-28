package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.SeleniumWrappers;

public class CandidatesPage extends SeleniumWrappers{

	public CandidatesPage(WebDriver driver) {
		super(driver);
	}
	
	public By findJobs = By.xpath("//span[text()='Find jobs']");

}
