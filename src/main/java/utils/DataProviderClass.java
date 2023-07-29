package utils;

import java.sql.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;

public class DataProviderClass {

	@DataProvider(name = "genericName")
	public Object[][] genericName(){
		Object[][] data = new Object[3][2];
		
		data[0][0] = By.linkText("");
		data[0][1] = By.xpath("");
		
		data[1][0] = By.linkText("");
		data[1][1] = By.cssSelector("");
		
		data[2][0] = By.linkText("");
		data[2][1] = By.xpath("");

		return data;		
	}
}
