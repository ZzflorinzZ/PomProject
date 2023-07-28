package pages;

import org.openqa.selenium.WebDriver;

import utils.SeleniumWrappers;

public class BasePage extends SeleniumWrappers{

	public BasePage(WebDriver driver) {
		super(driver);
	}
	
	public MenuPage menu= new MenuPage(driver);
	public CompaniesPage companies = new CompaniesPage(driver);
	public GenericCompanyPage genericCompany = new GenericCompanyPage(driver);
	public LoginPage login = new LoginPage(driver);
	public JobsPage jobs = new JobsPage(driver);
	public CandidatesPage candidates = new CandidatesPage(driver);
	public GenericJobPage genericJob = new GenericJobPage(driver);
	
	

}
