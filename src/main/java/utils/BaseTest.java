package utils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.google.common.io.Files;

import pages.BasePage;

public class BaseTest extends Driver{
	
	public WebDriver driver;
	public BasePage app;

	@Parameters({"url", "browser"})
	@BeforeClass(alwaysRun = true)
	public void setup(String url, String browser) throws MalformedURLException {
		
		driver = initBrowser(browser);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(url);
		app = new BasePage(driver);
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		Thread.sleep(4000);
//		driver.close();	// inchide tab-ul curent
		driver.quit();  // inchide browser-ul
	}
	
	@AfterMethod
	public void recordFailure(ITestResult result) throws IOException {
		//verificam statusul executiei
		if(ITestResult.FAILURE == result.getStatus()) {
			//instantieim TakeScreenshot
			TakesScreenshot poza = (TakesScreenshot)driver;
			File picture = poza.getScreenshotAs(OutputType.FILE);
			//salvam poza
			Files.copy(picture, new File("poze/failure.png"));
		}

	}

}
