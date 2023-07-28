package tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utils.BaseTest;

public class ApplyToJobAsCandidateTest extends BaseTest{
	
	@Parameters({"user", "pass"})
	@Test(priority = 1)
	public void login(String user, String pass) {
		app.click(app.menu.loginLink);
		app.login.loginInApp(user, pass);
	}
	
	@Test(priority = 2)
	public void searchForAJob() {
		app.click(app.candidates.findJobs);
		app.sendKeys(app.jobs.jobSearchField, "Test Engineer");
		app.click(app.jobs.jobSearchButton);
		app.click(app.jobs.testEngineerJob);
	}
	
	@Test(priority = 3)
	public void applyForAJob() {
		app.checkElementIsDisplayed(app.genericJob.applyNowButton);
		app.checkElementIsDisplayed(app.genericJob.companySection);
		app.click(app.genericJob.applyNowButton);
		app.checkElementIsDisplayed(app.genericJob.applyPopup);
		app.click(app.genericJob.callIcon);
		
	}
}
