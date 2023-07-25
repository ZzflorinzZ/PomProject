package utils;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestException;



public class SeleniumWrappers extends BaseTest {
	
	public SeleniumWrappers(WebDriver driver) {
		this.driver = driver;
	}

//RETURN WEBELEMENT METHOD	
	/**
	 * Wrapped method over Selenium default driver.findElement() functionality, enhanced with:</br>
	 * 1. Logging mechanism</br>
	 * 2. catch NoSuchElementException</br>
	 * 
	 * @param locator (By locator)
	 * @return WebElement
	 */
	public WebElement returnElement(By locator) {
		Log.info("Called method <returnElement> on element" + locator.toString());
		try {
			return driver.findElement(locator);
		} catch (NoSuchElementException e) {
			Log.error("Element not found in method <returnElement()>");
			Log.error(e.getMessage());
			return null;
		}
	}

//CLICK METHODS	
	/**
	 * Wrapped method over Selenium default click() method, enhanced with:</br>
	 * 1. Logging mechanism</br>
	 * 2. waitForElementToBeClickable() method, before any action to be performed on webElement</br>
	 * 3. catch NoSuchElementException</br>
	 * 4. catch StaleElementReferenceException</br>
	 * 5. Retry mechanism</br>
	 * 
	 * @param locator (By locator)
	 */
	public void click(By locator) {
		Log.info("Called method <click> on element" + locator.toString());
		try {
			waitForElementToBeClickable(locator);
			returnElement(locator).click();
		} catch (NoSuchElementException e) {
			Log.error("Element not found in method <click()>");
			Log.error(e.getMessage());
			throw new TestException("Element not found in method <click()>");
		} catch (StaleElementReferenceException e) {
			Log.error("Catch StaleElementReferenceException in method <click> on: " + locator.toString());
			returnElement(locator);
			returnElement(locator).click();
		}
	}

//exceptii: element not interactible
//general exception	
	
	/**
	 * Wrapped method over Selenium Actions default doubleClick() method, enhanced with:</br>
	 * 1. Logging mechanism</br>
	 * 2. waitForElementToBeClickable() method, before any action to be performed on webElement</br>
	 * 3. returnElement() method, for using a WebElement
	 * 4. catch NoSuchElementException</br>
	 * 5. catch StaleElementReferenceException</br>
	 * 6. Retry mechanism</br>
	 * 
	 * @param locator (By locator)
	 */
	public void doubleClick(By locator) {
		Log.info("Called method <doubleClick> on element" + locator.toString());
		try {
			waitForElementToBeClickable(locator);
			Actions action = new Actions(driver);
			action.doubleClick(returnElement(locator)).perform();			
		} catch (NoSuchElementException e) {
			Log.error("Element not found in method <doubleClick()>");
			Log.error(e.getMessage());
			throw new TestException("Element not found in method <doubleClick()>");
		} catch (StaleElementReferenceException e) {
			Log.error("Catch StaleElementReferenceException in method <doubleClick> on: " + locator.toString());
			returnElement(locator);
			Actions action = new Actions(driver);
			action.doubleClick(returnElement(locator)).perform();	
		}
	}
	
//WAIT METHODS
	/**
	 * Wrapped method over Selenium default elementToBeClickable() condition, enhanced with:</br>
	 * 1. Logging mechanism</br>
	 * 2. Explicit wait</br>
	 * 3. catch NoSuchElementException</br>
	 * 
	 * @param locator (By locator)
	 */
	public void waitForElementToBeClickable(By locator) {
		Log.info("Called method <waitForElementToBeClickable> on element" + locator.toString());
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(locator));
		} catch (NoSuchElementException e) {
			Log.error("Element not found in method <waitForElementToBeClickable()>");
			Log.error(e.getMessage());
			throw new TestException("Element not found in method <waitForElementToBeClickable()>");
		}
	}

	/**
	 * Wrapped method over Selenium default visibilityOf() condition, enhanced with:</br>
	 * 1. Logging mechanism</br>
	 * 2. Explicit wait</br>
	 * 3. catch NoSuchElementException</br>
	 * 
	 * @param locator (By locator)
	 */
	public void waitForElementToBeVisible(By locator) {
		Log.info("Called method <waitForElementToBeVisible> on element" + locator.toString());
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (NoSuchElementException e) {
			Log.error("Element not found in method <waitForElementToBeVisible()>");
			Log.error(e.getMessage());
			throw new TestException("Element not found in method <waitForElementToBeVisible()>");
		}
	}
	
	/**
	 * Wrapped method over Selenium default presenceOfElementLocated() condition, enhanced with:</br>
	 * 1. Logging mechanism</br>
	 * 2. Explicit wait</br>
	 * 3. catch NoSuchElementException</br>
	 * 
	 * @param locator (By locator)
	 */
	public void waitForElementToBePresent(By locator) {
  		Log.info("Called method <waitForElementToBePresent> on element " + locator.toString());
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));		
		} catch (NoSuchElementException e) {
			Log.error("Element not found in method <waitForElementToBePresent()>");
			Log.error(e.getMessage());
			throw new TestException("Element not found!");
		}
	}
	
	/**
	 * Wrapped method over Selenium default textToBePresentInElement() condition, enhanced with:</br>
	 * 1. Logging mechanism</br>
	 * 2. Explicit wait</br>
	 * 3. catch NoSuchElementException</br>
	 * 
	 * @param locator (By locator)
	 * @param textToBePresent (String value)
	 */
	public void waitForTextToBePresentInElement(By locator, String textToBePresent) {
		Log.info("Called method <waitForTextToBePresentInElement> on element " + locator.toString());
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, textToBePresent));
		} catch (NoSuchElementException e) {
			Log.error("Element not found in method <waitForTextToBePresentInElement()>");
			Log.error(e.getMessage());
			throw new TestException("Element not found in method <waitForTextToBePresentInElement()>");
		}
	}

//SEND KEYS METHOD	
	/**
	 * Wrapped method over Selenium default sendKeys() method, enhanced with:</br>
	 * 1. Logging mechanism</br>
	 * 2. waitForElementToBeVisible() method, before any action to be performed on webElement</br>
	 * 3. returnElement() method, for using a WebElement
	 * 4. Clear() method before sending text</br>
	 * 5. catch NoSuchElementException</br>
	 * 
	 * @param locator (By locator)
	 * @param textToSend (String value)
	 */
	public void sendKeys(By locator, String textToSend) {
		Log.info("Called method <sendKeys()> on element " + locator.toString());
		try {
			waitForElementToBeVisible(locator);
			returnElement(locator).clear();
			returnElement(locator).sendKeys(textToSend);
		} catch (NoSuchElementException e) {
			Log.error("Element not found in method <sendKeys()>");
			Log.error(e.getMessage());
			throw new TestException("Element not found in method <sendKeys()>");
		}
	}

//IS DISPLAYED METHOD	
	/**
	 * Wrapped method over Selenium default isDisplayed() method, enhanced with:</br>
	 * 1. Logging mechanism</br>
	 * 2. returnElement() method, for using a WebElement
	 * 3. catch NoSuchElementException</br>
	 * 
	 * @param locator (By locator)
	 * @return boolean
	 */
	public boolean checkElementIsDisplayed(By locator) {
		Log.info("Called method <checkElementIsDisplayed()> on element " + locator.toString());
		try {
			returnElement(locator).isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			Log.error("Element not found in method <checkElementIsDisplayed()>");
			Log.error(e.getMessage());
			return false;					
		}
	}

//HOOVER METHODS
	/**
	 * Wrapped method over Selenium Actions default moveToElement() method, enhanced with:</br>
	 * 1. Logging mechanism</br>
	 * 2. waitForElementToBeVisible() method, before any action to be performed on webElement</br>
	 * 3. returnElement() method, for using a WebElement
	 * 4. catch NoSuchElementException</br>
	 * 
	 * @param locator (By locator)
	 */
	public void hooverOnElementVisible(By locator) {
		Log.info("Called method <hooverOnElementVisible()> on element " + locator.toString());
		Actions hoover = new Actions(driver);
		try {
			waitForElementToBeVisible(locator);
			hoover.moveToElement(returnElement(locator)).perform();
		} catch (NoSuchElementException e) {
			Log.error("Element not found in method <hooverOnElementVisible()>");
			Log.error(e.getMessage());
			throw new TestException("Element not found in method <hooverOnElementVisible()>");
		}	
	}

	/**
	 * Wrapped method over Selenium Actions default moveToElement() method, enhanced with:</br>
	 * 1. Logging mechanism</br>
	 * 2. waitForElementToBePresent() method, before any action to be performed on webElement</br>
	 * 3. returnElement() method, for using a WebElement
	 * 4. catch NoSuchElementException</br>
	 * 
	 * @param locator (By locator)
	 */
	public void hooverOnElementPresent(By locator) {
		Log.info("Called method <hooverOnElementPresent()> on element " + locator.toString());
		Actions hoover = new Actions(driver);
		try {
			waitForElementToBePresent(locator);
			hoover.moveToElement(returnElement(locator)).perform();
		} catch (NoSuchElementException e) {
			Log.error("Element not found in method <hooverOnElementPresent()>");
			Log.error(e.getMessage());
			throw new TestException("Element not found in method <hooverOnElementPresent()>");
		}	
	}
	

//DROPDOWN LISTS METHODS
	/**
	 * Wrapped method over Selenium Select default selectByIndex() method, enhanced with:</br>
	 * 1. Logging mechanism</br>
	 * 2. returnElement() method, for using a WebElement
	 * 3. catch NoSuchElementException</br>
	 * 
	 * @param locator (By locator)
	 * @param index (Integer value)
	 */
	public void selectByIndex(By locator, int index) {
		Log.info("Called method <selectByIndex()> on element " + locator.toString());
		try {
			Select select = new Select(returnElement(locator));
			select.selectByIndex(index);
		} catch (NoSuchElementException e) {
			Log.error("Element not found in method <selectByIndex()>");
			Log.error(e.getMessage());
			throw new TestException("Element not found in method <selectByIndex()>");
		}
	}

	/**
	 * Wrapped method over Selenium Select default selectByValue() method, enhanced with:</br>
	 * 1. Logging mechanism</br>
	 * 2. returnElement() method, for using a WebElement
	 * 3. catch NoSuchElementException</br>
	 * 
	 * @param locator (By locator)
	 * @param value (String value)
	 */
	public void selectByValue(By locator, String value) {
		Log.info("Called method <selectByValue()> on element " + locator.toString());
		try {
			Select select = new Select(returnElement(locator));
			select.selectByValue(value);
		} catch (NoSuchElementException e) {
			Log.error("Element not found in method <selectByValue()>");
			Log.error(e.getMessage());
			throw new TestException("Element not found in method <selectByValue()>");
		}
	}
	
	/**
	 * Wrapped method over Selenium Select default selectByVisibleText() method, enhanced with:</br>
	 * 1. Logging mechanism</br>
	 * 2. returnElement() method, for using a WebElement
	 * 3. catch NoSuchElementException</br>
	 * 
	 * @param locator (By locator)
	 * @param text (String value)
	 */
	public void selectByVisibleText(By locator, String text) {
		Log.info("Called method <selectByVisibleText()> on element " + locator.toString());
		try {
			Select select = new Select(returnElement(locator));
			select.selectByVisibleText(text);
		} catch (NoSuchElementException e) {
			Log.error("Element not found in method <selectByVisibleText()>");
			Log.error(e.getMessage());
			throw new TestException("Element not found in method <selectByVisibleText()>");
		}
	}
	
	/**
	 * Wrapped method over Selenium Select default getFirstSelectedOption() method, enhanced with:</br>
	 * 1. Logging mechanism</br>
	 * 2. returnElement() method, for using a WebElement
	 * 3. catch NoSuchElementException</br>
	 * 
	 * @param locator (By locator)
	 * @return String (String value) --> value of the selected option
	 */
	public String getSelectedOption(By locator) {
		Log.info("Called method <getSelectedOption()> on element " + locator.toString());
		try {
			Select select = new Select(returnElement(locator));
			return select.getFirstSelectedOption().getText();
		} catch (NoSuchElementException e) {
			Log.error("Element not found in method <getSelectedOption()>");
			Log.error(e.getMessage());
			return null;
		}
	}

//DRAG & DROP METHODS
	/**
	 * Wrapped method over Selenium Actions default dragAndDrop() method, enhanced with:</br>
	 * 1. Logging mechanism</br>
	 * 2. returnElement() method, for using a WebElement
	 * 3. catch NoSuchElementException</br>
	 * 
	 * @param locator1 (By locator) --> element to be moved
	 * @param locator2 (By locator) --> element on which the first element is moved over
	 */
	public void dragAndDropElementToElement(By locator1, By locator2) {
		Log.info("Called method <dragAndDropElementToElement()> on element " + locator1.toString() + " and " + locator2.toString());
		try {
			Actions action = new Actions(driver);
			action.dragAndDrop(returnElement(locator1), returnElement(locator2)).perform();
		} catch (NoSuchElementException e) {
			Log.error("Element not found in method <dragAndDropElementToElement()>");			//ar trebui sa diferentiez NoSuchElementException pe element1 si element2; ma gandesc sa folosesc un if, dar nu-mi dai seama cum ar trebui sa pun conditia
			Log.error(e.getMessage());
			throw new TestException("Element not found in method <dragAndDropElementToElement()>");
		}
	}

	/**
	 * Wrapped method over Selenium Actions default dragAndDropBy() method, enhanced with:</br>
	 * 1. Logging mechanism</br>
	 * 2. returnElement() method, for using a WebElement
	 * 3. catch NoSuchElementException</br>
	 * 
	 * @param locator (By locator) --> element to be moved
	 * @param x (Integer value) --> horizontal move
	 * @param y (Integer value) --> vertical move
	 */
	public void dragAndDrop(By locator, int x, int y) {
		Log.info("Called method <dragAndDrop()> on element " + locator.toString());
		try {
			Actions action = new Actions(driver);
/*			action 
					.moveToElement(element)
			  		.clickAndHold()
			  		.moveByOffset(x, y)
			  		.release()
			  		.perform();
*/			action.dragAndDropBy(returnElement(locator), x, y).perform();
		} catch (NoSuchElementException e) {
			Log.error("Element not found in method <dragAndDrop()>");
			Log.error(e.getMessage());
			throw new TestException("Element not found in method <dragAndDrop()>");
		}
	}
	
	/**
	 * Wrapped method over Selenium default driver.switchTo().window() functionality, enhanced with:</br>
	 * 1. Logging mechanism</br>
	 * 2. click() custom method</br>
	 * 3. driver.close() functionality</br>
	 * 4. catch Exception</br>
	 * 
	 * @param locator (By locator)
	 * @return String (String value of current URL)
	 */
	public String checkRedirectedUrlAndReturnToInitialPage(By locator) {
		Log.info("Called method <checkRedirectedUrlAndReturnToInitialPage()> on element " + locator.toString());
		try {
			click(locator);
			List<String> browserTabs = new ArrayList<>(driver.getWindowHandles());
			driver.switchTo().window(browserTabs.get(1));
			String currentURL = driver.getCurrentUrl();		
			driver.close();
			driver.switchTo().window(browserTabs.get(0));
			return currentURL;
		} catch (Exception e) {
			Log.error("Element not found in method <checkRedirectedUrlAndReturnToInitialPage()>");
			Log.error(e.getMessage());
			return e.getMessage(); 
		}
	}

}
