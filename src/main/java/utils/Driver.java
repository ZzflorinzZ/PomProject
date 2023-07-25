package utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Driver {
	
	//for parallel execution purpose
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
	public WebDriver initBrowser(String browser) throws MalformedURLException {
		
		RemoteWebDriver rwd;
		
		if(browser.equalsIgnoreCase("chrome")) {	
//    		WebDriver driver = new ChromeDriver();
//			driver.set(new ChromeDriver(getChromeOptions()));
//			driver.set(new ChromeDriver());
			System.out.println("Chrome" + Thread.currentThread().getId());
			
			//setup for SeleniumGrid
			rwd = new RemoteWebDriver(new URL("http://localhost:4444/"), getChromeOptions());
			driver.set(rwd);
			
			return driver.get();	
			
		}else if(browser.equalsIgnoreCase("firefox")) {
//			driver.set(new FirefoxDriver(getFirefoxOptions()));
			System.out.println("Firefox" + Thread.currentThread().getId());
			
			//setup for SeleniumGrid
			rwd = new RemoteWebDriver(new URL("http://localhost:4444/"), getFirefoxOptions());
			driver.set(rwd);
			
			return driver.get();
		}else if(browser.equalsIgnoreCase("edge")) {
//			driver.set(new EdgeDriver(getEdgeOptions()));
			System.out.println("Edge" + Thread.currentThread().getId());
			
			//setup for SeleniumGrid
			rwd = new RemoteWebDriver(new URL("http://localhost:4444/"), getEdgeOptions());
			driver.set(rwd);
			
			return driver.get();
		}
		return driver.get(); 	
	}
	
	public ChromeOptions getChromeOptions() {
		ChromeOptions options = new ChromeOptions();
	//	options.addArguments("--headless");
	//	options.addArguments("--window-size=1580, 1280");
		return options; 
	}
	
	public FirefoxOptions getFirefoxOptions() {
		File pathToBinary = new File("D:\\Utile\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary fbinary = new FirefoxBinary(pathToBinary);
//		FirefoxBinary fbinary = new FirefoxBinary();
		fbinary.addCommandLineOptions("--headless");
		FirefoxOptions foptions = new FirefoxOptions();
		foptions.setBinary(fbinary);
		return foptions;
	}
	
	public EdgeOptions getEdgeOptions() {
		EdgeOptions options = new EdgeOptions();
//		options.addArguments("--headless");
//		options.addArguments("--window-size=1580, 1280");
		return options; 
	}

}
