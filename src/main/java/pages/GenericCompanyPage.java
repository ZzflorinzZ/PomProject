package pages;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.SeleniumWrappers;

public class GenericCompanyPage extends SeleniumWrappers{

	public GenericCompanyPage(WebDriver driver) {
		super(driver);
	}

	
	public By follow = By.linkText("Follow");
	public By visitWebsite = By.linkText("Visit website");
	public By sendMessage = By.linkText("Send message");
	
	public By categories = By.xpath("(//p[@class='title-info'])[1]");
	public By categories2 = By.xpath("(//p[contains(@class,'title-info')])[1]");
	public By b2bSaas = By.cssSelector("div[class='list-cate'] a[href*='saas']");
	public By webDevelopment = By.linkText("Web Development");
	public By companySize = By.xpath("(//p[@class='title-info'])[2]");
	public By sizeNumber = By.xpath("//p[text()='Company size']/following-sibling::div[@class='list-cate']");
	public By foundedIn = By.xpath("(//p[@class='title-info'])[3]");
	public By foundationYear = By.xpath("//p[text()='2017']");
	public By location = By.xpath("//(//p[@class='title-info'])[4]");
	public By locationCity = By.xpath("//span[text()='San Francisco']");
	
	
	public Map<By, By> initInformation() {
		Map<By, By> information = new HashMap<>();
		information.put(categories, b2bSaas);
		information.put(categories2, webDevelopment);
		information.put(companySize, sizeNumber);
		information.put(foundedIn, foundationYear);
		information.put(location, locationCity);
		return information;	
	}

}
