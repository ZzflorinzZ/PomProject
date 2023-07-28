package tests;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import utils.BaseTest;

public class SearchAndValidateCompanyDetailsTest extends BaseTest {

	@Test(priority = 1)
	public void searchCompany() {
		app.click(app.menu.companies);
		app.sendKeys(app.companies.searchField, "Coin");
		app.click(app.companies.searchButton);
		app.click(app.companies.coinTracker);			//pica din cand in cand cu  <<Exception error in method <click()>> / <<ERROR utils.Log - stale element reference: stale element not found>>, 
	}													//nu il prinde in metoda returnElement, ci intra pe General Exception din click()

	@Test(priority = 2, dependsOnMethods = "searchCompany")
	public void validateRedirectButtons() {
		app.click(app.genericCompany.follow);
		assertEquals(app.returnElement(app.login.loginTab).getText(), "Log in");
		app.click(app.login.closeButton);
		assertEquals(app.checkRedirectedUrlAndReturnToInitialPage(app.genericCompany.visitWebsite), "https://www.cointracker.io/"); 
		app.click(app.genericCompany.sendMessage);
		assertEquals(app.returnElement(app.login.loginTab).getText(), "Log in");
		app.click(app.login.closeButton);
	}
		
	@Test(priority = 3, dependsOnMethods = "validateRedirectButtons")
	public void validateCompanyDetails() {
		System.out.println(app.genericCompany.initInformation());
		System.out.println(app.genericCompany.initInformation().get(app.genericCompany.categories));
		System.out.println(app.genericCompany.b2bSaas);
		System.out.println(app.genericCompany.initInformation().size());
		
		assertEquals(app.genericCompany.initInformation().get(app.genericCompany.categories), app.genericCompany.b2bSaas);		// pica asserturile; 		
		assertEquals(app.genericCompany.initInformation().get(app.genericCompany.categories2), app.genericCompany.webDevelopment);
		assertEquals(app.genericCompany.initInformation().get(app.genericCompany.companySize), app.genericCompany.sizeNumber);
		assertEquals(app.genericCompany.initInformation().get(app.genericCompany.foundedIn), app.genericCompany.foundationYear);
		assertEquals(app.genericCompany.initInformation().get(app.genericCompany.location), app.genericCompany.locationCity);
	}

}
