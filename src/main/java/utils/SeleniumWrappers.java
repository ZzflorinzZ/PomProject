package utils;

import java.io.IOException;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestException;

/**
 * SeleniumWrappers class containing methods based on <By locator> usage
 */
public class SeleniumWrappers extends BaseTest {

	public SeleniumWrappers(WebDriver driver) {
		this.driver = driver;
	}
	
	//================================================================================================================================		
	// JS ALERT METHODS
	//============================================	
	/**
	 * Wrapped method over Selenium default Alert accept() method, enhanced with:</br>
	 * 1. catch General exception</br>
	 */
	public void jsAlertAccept() {
		Log.info(runningBrowser.get() + " Called method <jsAlertAccept()>");
		try {
			driver.switchTo().alert().accept();
		} catch (Exception e) {
			Log.error(runningBrowser.get() + " Caught Exception error in method <jsAlertAccept()>");
			Log.error(runningBrowser.get() + " " + e.getMessage());
			throw new TestException(runningBrowser.get() + " Exception error in method <jsAlertAccept()>");
		}
	}
	
	//============================================	
	/**
	 * Wrapped method over Selenium default Alert dismiss() method, enhanced with:</br>
	 * 1. catch General exception</br>
	 */
	public void jsAlertDismiss() {
		Log.info(runningBrowser.get() + " Called method <jsAlertDismiss()>");
		try {
			driver.switchTo().alert().dismiss();
		} catch (Exception e) {
			Log.error(runningBrowser.get() + " Caught Exception error in method <jsAlertDismiss()>");
			Log.error(runningBrowser.get() + " " + e.getMessage());
			throw new TestException(runningBrowser.get() + " Exception error in method <jsAlertDismiss()>");
		}
	}
	
	//============================================	
	/**
	 * Wrapped method over Selenium default Alert getText() method, enhanced with:</br>
	 * 1. catch General exception</br>
	 * 
	 * @return text of jsAlert 	(String value)
	 */
	public String jsAlertGetText() {
		Log.info(runningBrowser.get() + " Called method <jsAlertGetText()>");
		try {
			if(driver.switchTo().alert().getText() == null) { 
				return null;
			}else {
				return driver.switchTo().alert().getText();
			}
		} catch (Exception e) {
			Log.error(runningBrowser.get() + " Caught Exception error in method <jsAlertGetText()>");
			Log.error(runningBrowser.get() + " " + e.getMessage());
			return e.getMessage(); 
		}
	}
	
	//============================================	
	/**
	 * Wrapped method over Selenium default Alert sendKeys() method, enhanced with:</br>
	 * 1. catch General exception</br>
	 * 
	 * @param textToSend 	(String value)
	 */
	public void jsAlertSendKeys(String textToSend) {
		Log.info(runningBrowser.get() + " Called method <jsAlertSendKeys()>");
		try {
			driver.switchTo().alert().sendKeys(textToSend);
		} catch (Exception e) {
			Log.error(runningBrowser.get() + " Caught Exception error in method <jsAlertSendKeys()>");
			Log.error(runningBrowser.get() + " " + e.getMessage());
			throw new TestException(runningBrowser.get() + " Exception error in method <jsAlertSendKeys()>");
		}
	}

	//================================================================================================================================
	//RETURN WEBELEMENT METHOD	
	//============================================	
	/**
	 * Wrapped method over Selenium default driver.findElement() functionality, enhanced with:</br>
	 * 1. Logging mechanism</br>
	 * 2. catch NoSuchElementException</br>
	 * 3. catch StaleElementReferenceException</br>
	 * 4. Retry mechanism</br>
	 * 5. catch General exception</br>
	 * 
	 * @param locator (By locator)
	 * @return WebElement
	 */
	public WebElement returnElement(By locator) {
		Log.info(runningBrowser.get() + " Called method <returnElement()> on element" + locator.toString());
		try {
			return driver.findElement(locator);
		} catch (NoSuchElementException e) {
			Log.error(runningBrowser.get() + " Caught NoSuchElementException in method <returnElement()> on: " + locator.toString());
			Log.error(runningBrowser.get() + " " + e.getMessage());
			return null;
		} catch (StaleElementReferenceException e) {
			Log.error(runningBrowser.get() + " Caught StaleElementReferenceException in method <returnElement()> on: " + locator.toString());
			Log.error(runningBrowser.get() + " " + e.getMessage());
			return driver.findElement(locator);
		}catch (Exception e) {
			Log.error(runningBrowser.get() + " Caught Exception error in method <returnElement()> on: " + locator.toString());
			Log.error(runningBrowser.get() + " " + e.getMessage());
			throw new TestException(runningBrowser.get() + " Exception error in method <returnElement()>");
		}
	}

	//================================================================================================================================	
	//CLICK METHODS	
	//============================================
	/**
	 * Wrapped method over Selenium default click() method, enhanced with:</br>
	 * 1. Logging mechanism</br>
	 * 2. waitForElementToBeClickable() method, before any action to be performed on WebElement</br>
	 * 3. returnElement() method</br>
	 * 4. Retry mechanism</br>
	 * 5. catch General exception</br>
	 * 
	 * @param locator (By locator)
	 */
	public void click(By locator) {
		Log.info(runningBrowser.get() + " Called method <click()> on element" + locator.toString());
		try {
			waitForElementToBeClickable(locator);
			returnElement(locator).click();
		} catch (StaleElementReferenceException e) {
			Log.warn(runningBrowser.get() + " Caught StaleElementReferenceException in method <click()> on: " + locator.toString());
			Log.warn(runningBrowser.get() + " " + e.getMessage());
			returnElement(locator).click();
		} catch (Exception e) {
			Log.error(runningBrowser.get() + " Caught Exception error in method <click()> on: " + locator.toString());
			Log.error(runningBrowser.get() + " " + e.getMessage());
			throw new TestException(runningBrowser.get() + " Exception error in method <click()>");
		}
	}

	//============================================
	/**
	 * Wrapped method over Selenium default click() method, enhanced with:</br>
	 * 1. Logging mechanism</br>
	 * 2. waitForElementToBePresent() method, before any action to be performed on WebElement</br>
	 * 3. returnElement() method</br>
	 * 4. catch General exception</br>
	 * 
	 * @param locator (By locator)
	 */
	public void clickOnElementPresent(By locator) {
		Log.info(runningBrowser.get() + " Called method <clickOnElementPresent()> on element" + locator.toString());
		try {
			waitForElementToBePresent(locator);
			returnElement(locator).click();
		} catch (StaleElementReferenceException e) {
			Log.warn(runningBrowser.get() + " Caught StaleElementReferenceException in method <clickOnElementPresent()> on: " + locator.toString());
			Log.warn(runningBrowser.get() + " " + e.getMessage());
			returnElement(locator).click();
		} catch (Exception e) {
			Log.error(runningBrowser.get() + " Caught Exception error in method <clickOnElementPresent()> on: " + locator.toString());
			Log.error(runningBrowser.get() + " " + e.getMessage());
			throw new TestException(runningBrowser.get() + " Exception error in method <clickOnElementPresent()>");
		}
	}

	//============================================
	/**
	 * Wrapped method over Selenium Actions default doubleClick() method, enhanced with:</br>
	 * 1. Logging mechanism</br>
	 * 2. waitForElementToBeClickable() method, before any action to be performed on WebElement</br>
	 * 3. returnElement() method</br>
	 * 4. catch General exception</br>
	 * 
	 * @param locator (By locator)
	 */
	public void doubleClick(By locator) {
		Log.info(runningBrowser.get() + " Called method <doubleClick()> on element" + locator.toString());
		try {
			waitForElementToBeClickable(locator);
			Actions action = new Actions(driver);
			action.doubleClick(returnElement(locator)).perform();
		} catch (StaleElementReferenceException e) {
			Log.warn(runningBrowser.get() + " Caught StaleElementReferenceException in method <doubleClick()> on: " + locator.toString());
			Log.warn(runningBrowser.get() + " " + e.getMessage());
			Actions action = new Actions(driver);
			action.doubleClick(returnElement(locator)).perform();
		} catch (Exception e) {
			Log.error(runningBrowser.get() + " Caught Exception error in method <doubleClick()> on: " + locator.toString());
			Log.error(runningBrowser.get() + " " + e.getMessage());
			throw new TestException(runningBrowser.get() + " Exception error in method <doubleClick()>");
		}
	}

	//================================================================================================================================		
	//WAIT METHODS
	//============================================
	/**
	 * Wrapped method over Selenium default elementToBeClickable() condition, enhanced with:</br>
	 * 1. Logging mechanism</br>
	 * 2. Explicit wait</br>
	 * 3. catch NoSuchElementException</br>
	 * 4. catch General exception</br>
	 * 
	 * @param locator (By locator)
	 */
	public void waitForElementToBeClickable(By locator) {
		Log.info(runningBrowser.get() + " Called method <waitForElementToBeClickable()> on element" + locator.toString());
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(returnElement(locator)));
		} catch (NoSuchElementException e) {
			Log.error(runningBrowser.get() + " Caught NoSuchElementException in method <waitForElementToBeClickable()> on: " + locator.toString());
			Log.error(runningBrowser.get() + " " + e.getMessage());
			throw new TestException(runningBrowser.get() + " Element not found in method <waitForElementToBeClickable()>");
		} catch (Exception e) {
			Log.error(runningBrowser.get() + " Caught Exception error in method <waitForElementToBeClickable()> on: " + locator.toString());
			Log.error(runningBrowser.get() + " " + e.getMessage());
			throw new TestException(runningBrowser.get() + " Exception error in method <waitForElementToBeClickable()>");
		}
	}

	//============================================
	/**
	 * Wrapped method over Selenium default visibilityOf() condition, enhanced with:</br>
	 * 1. Logging mechanism</br>
	 * 2. Explicit wait</br>
	 * 3. catch NoSuchElementException</br>
	 * 4. catch General exception</br>
	 * 
	 * @param locator (By locator)
	 */
	public void waitForElementToBeVisible(By locator) {
		Log.info(runningBrowser.get() + " Called method <waitForElementToBeVisible()> on element" + locator.toString());
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(returnElement(locator)));
		} catch (NoSuchElementException e) {
			Log.error(runningBrowser.get() + " Caught NoSuchElementException in method <waitForElementToBeVisible()> on: " + locator.toString());
			Log.error(runningBrowser.get() + " " + e.getMessage());
			throw new TestException(runningBrowser.get() + " Element not found in method <waitForElementToBeVisible()>");
		} catch (Exception e) {
			Log.error(runningBrowser.get() + " Caught Exception error in method <waitForElementToBeVisible()> on: " + locator.toString());
			Log.error(runningBrowser.get() + " " + e.getMessage());
			throw new TestException(runningBrowser.get() + " Exception error in method <waitForElementToBeVisible()>");
		}
	}

	//============================================
	/**
	 * Wrapped method over Selenium default presenceOfElementLocated() condition, enhanced with:</br>
	 * 1. Logging mechanism</br>
	 * 2. Explicit wait</br>
	 * 3. catch NoSuchElementException</br>
	 * 4. catch General exception</br>
	 * 
	 * @param locator (By locator)
	 */
	public void waitForElementToBePresent(By locator) {
		Log.info(runningBrowser.get() + " Called method <waitForElementToBePresent()> on element " + locator.toString());
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		} catch (NoSuchElementException e) {
			Log.error(runningBrowser.get() + " Caught NoSuchElementException in method <waitForElementToBePresent()> on: " + locator.toString());
			Log.error(runningBrowser.get() + " " + e.getMessage());
			throw new TestException(runningBrowser.get() + " Element not found in method <waitForElementToBePresent()>");
		} catch (Exception e) {
			Log.error(runningBrowser.get() + " Caught Exception error in method <waitForElementToBePresent()> on: " + locator.toString());
			Log.error(runningBrowser.get() + " " + e.getMessage());
			throw new TestException(runningBrowser.get() + " Exception error in method <waitForElementToBePresent()>");
		}
	}

	//============================================
	/**
	 * Wrapped method over Selenium default textToBePresentInElement() condition, enhanced with:</br>
	 * 1. Logging mechanism</br>
	 * 2. Explicit wait</br>
	 * 3. catch NoSuchElementException</br>
	 * 4. catch General exception</br>
	 * 
	 * @param locator         (By locator)
	 * @param textToBePresent (String value)
	 */
	public void waitForTextToBePresentInElement(By locator, String textToBePresent) {
		Log.info(runningBrowser.get() + " Called method <waitForTextToBePresentInElement()> on element " + locator.toString());
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, textToBePresent));
		} catch (NoSuchElementException e) {
			Log.error(runningBrowser.get() + " Caught NoSuchElementException in method <waitForTextToBePresentInElement()> on: " + locator.toString());
			Log.error(runningBrowser.get() + " " + e.getMessage());
			throw new TestException(runningBrowser.get() + " Element not found in method <waitForTextToBePresentInElement()>");
		} catch (Exception e) {
			Log.error(runningBrowser.get() + " Caught Exception error in method <waitForTextToBePresentInElement()> on: " + locator.toString());
			Log.error(runningBrowser.get() + " " + e.getMessage());
			throw new TestException(runningBrowser.get() + " Exception error in method <waitForTextToBePresentInElement()>");
		}
	}

	//================================================================================================================================	
	//SEND KEYS METHOD
	//============================================
	/**
	 * Wrapped method over Selenium default sendKeys() method, enhanced with:</br>
	 * 1. Logging mechanism</br>
	 * 2. waitForElementToBeVisible() method, before any action to be performed on WebElement</br>
	 * 3. returnElement() method</br>
	 * 4. Clear() method before sending text</br>
	 * 5. catch General exception</br>
	 * 
	 * @param locator    (By locator)
	 * @param textToSend (String value)
	 */
	public void sendKeys(By locator, String textToSend) {
		Log.info(runningBrowser.get() + " Called method <sendKeys()> on element " + locator.toString());
		try {
			waitForElementToBeVisible(locator);
			returnElement(locator).clear();
			returnElement(locator).sendKeys(textToSend);
		} catch (Exception e) {
			Log.error(runningBrowser.get() + " Caught Exception error in method <sendKeys()> on: " + locator.toString());
			Log.error(runningBrowser.get() + " " + e.getMessage());
			throw new TestException(runningBrowser.get() + " Exception error in method <sendKeys()>");
		}
	}

	//================================================================================================================================		
	//IS DISPLAYED METHOD
	//============================================	
	/**
	 * Wrapped method over Selenium default isDisplayed() method, enhanced with:</br>
	 * 1. Logging mechanism</br>
	 * 2. returnElement() method</br>
	 * 3. catch General exception</br>
	 * 
	 * @param locator (By locator)
	 * @return boolean
	 */
	public boolean checkElementIsDisplayed(By locator) {
		Log.info(runningBrowser.get() + " Called method <checkElementIsDisplayed()> on element " + locator.toString());
		try {
			returnElement(locator).isDisplayed();
			return true;
		} catch (Exception e) {
			Log.error(runningBrowser.get() + " Caught Exception error in method <checkElementIsDisplayed()> on: " + locator.toString());
			Log.error(runningBrowser.get() + " " + e.getMessage());
			return false;
		}
	}

	//================================================================================================================================	
	//HOOVER METHODS
	//============================================
	/**
	 * Wrapped method over Selenium Actions default moveToElement() method, enhanced with:</br>
	 * 1. Logging mechanism</br>
	 * 2. waitForElementToBeVisible() method, before any action to be performed on WebElement</br>
	 * 3. returnElement() method</br>
	 * 4. catch General exception</br>
	 * 
	 * @param locator (By locator)
	 */
	public void hooverOnElementVisible(By locator) {
		Log.info(runningBrowser.get() + " Called method <hooverOnElementVisible()> on element " + locator.toString());
		Actions hoover = new Actions(driver);
		try {
			waitForElementToBeVisible(locator);
			hoover.moveToElement(returnElement(locator)).perform();
		} catch (Exception e) {
			Log.error(runningBrowser.get() + " Caught Exception error in method <hooverOnElementVisible()> on: " + locator.toString());
			Log.error(runningBrowser.get() + " " + e.getMessage());
			throw new TestException(runningBrowser.get() + " Exception error in method <hooverOnElementVisible()>");
		}
	}

	//============================================
	/**
	 * Wrapped method over Selenium Actions default moveToElement() method, enhanced with:</br>
	 * 1. Logging mechanism</br>
	 * 2. waitForElementToBePresent() method, before any action to be performed on WebElement</br>
	 * 3. returnElement() method</br>
	 * 4. catch General exception</br>
	 * 
	 * @param locator (By locator)
	 */
	public void hooverOnElementPresent(By locator) {
		Log.info(runningBrowser.get() + " Called method <hooverOnElementPresent()> on element " + locator.toString());
		Actions hoover = new Actions(driver);
		try {
			waitForElementToBePresent(locator);
			hoover.moveToElement(returnElement(locator)).perform();
		} catch (Exception e) {
			Log.error(runningBrowser.get() + " Caught Exception error in method <hooverOnElementPresent()> on: " + locator.toString());
			Log.error(runningBrowser.get() + " " + e.getMessage());
			throw new TestException(runningBrowser.get() + " Exception error in method <hooverOnElementPresent()>");
		}
	}

	//================================================================================================================================		
	//DROPDOWN LISTS METHODS
	//============================================
	/**
	 * Wrapped method over Selenium Select default selectByIndex() method, enhanced with:</br>
	 * 1. Logging mechanism</br>
	 * 2. returnElement() method</br>
	 * 3. catch General exception</br>
	 * 
	 * @param locator (By locator)
	 * @param index   (Integer value)
	 */
	public void selectByIndex(By locator, int index) {
		Log.info(runningBrowser.get() + " Called method <selectByIndex()> on element " + locator.toString());
		try {
			Select select = new Select(returnElement(locator));
			select.selectByIndex(index);
		} catch (Exception e) {
			Log.error(runningBrowser.get() + " Caught Exception error in method <selectByIndex()> on: " + locator.toString());
			Log.error(runningBrowser.get() + " " + e.getMessage());
			throw new TestException(runningBrowser.get() + " Exception error in method <selectByIndex()>");
		}
	}

	//============================================
	/**
	 * Wrapped method over Selenium Select default selectByValue() method, enhanced with:</br>
	 * 1. Logging mechanism</br>
	 * 2. returnElement() method</br>
	 * 3. catch General exception</br>
	 * 
	 * @param locator (By locator)
	 * @param value   (String value)
	 */
	public void selectByValue(By locator, String value) {
		Log.info(runningBrowser.get() + " Called method <selectByValue()> on element " + locator.toString());
		try {
			Select select = new Select(returnElement(locator));
			select.selectByValue(value);
		} catch (Exception e) {
			Log.error(runningBrowser.get() + " Caught Exception error in method <selectByValue()> on: " + locator.toString());
			Log.error(runningBrowser.get() + " " + e.getMessage());
			throw new TestException(runningBrowser.get() + " Exception error in method <selectByValue()>");
		}
	}

	//============================================
	/**
	 * Wrapped method over Selenium Select default selectByVisibleText() method, enhanced with:</br>
	 * 1. Logging mechanism</br>
	 * 2. returnElement() method</br>
	 * 3. catch General exception</br>
	 * 
	 * @param locator (By locator)
	 * @param text    (String value)
	 */
	public void selectByVisibleText(By locator, String text) {
		Log.info(runningBrowser.get() + " Called method <selectByVisibleText()> on element " + locator.toString());
		try {
			Select select = new Select(returnElement(locator));
			select.selectByVisibleText(text);
		} catch (Exception e) {
			Log.error(runningBrowser.get() + " Caught Exception error in method <selectByVisibleText()> on: " + locator.toString());
			Log.error(runningBrowser.get() + " " + e.getMessage());
			throw new TestException(runningBrowser.get() + " Exception error in method <selectByVisibleText()>");
		}
	}

	//============================================
	/**
	 * Wrapped method over Selenium Select default getFirstSelectedOption() method, enhanced with:</br>
	 * 1. Logging mechanism</br>
	 * 2. returnElement() method</br>
	 * 3. catch General exception</br>
	 * 
	 * @param locator (By locator)
	 * @return String (String value) --> value of the selected option
	 */
	public String getSelectedOption(By locator) {
		Log.info(runningBrowser.get() + " Called method <getSelectedOption()> on element " + locator.toString());
		try {
			Select select = new Select(returnElement(locator));
			return select.getFirstSelectedOption().getText();
		} catch (Exception e) {
			Log.error(runningBrowser.get() + " Caught Exception error in method <getSelectedOption()> on: " + locator.toString());
			Log.error(runningBrowser.get() + " " + e.getMessage());
			return null;
		}
	}

	//================================================================================================================================	
	//DRAG & DROP METHODS
	//============================================
	/**
	 * Wrapped method over Selenium Actions default dragAndDrop() method, enhanced with:</br>
	 * 1. Logging mechanism</br>
	 * 2. returnElement() method</br>
	 * 3. catch General exception</br>
	 * 
	 * @param locator1 (By locator) --> element to be moved
	 * @param locator2 (By locator) --> element on which the first element is moved over
	 */
	public void dragAndDropElementToElement(By locator1, By locator2) {
		Log.info(runningBrowser.get() + " Called method <dragAndDropElementToElement()> on element " + locator1.toString() + " and "+ locator2.toString());
		try {
			Actions action = new Actions(driver);
			action.dragAndDrop(returnElement(locator1), returnElement(locator2)).perform();
		} catch (Exception e) {
			Log.error(runningBrowser.get() + " Caught Exception error in method <dragAndDropElementToElement()>"); 
			Log.error(runningBrowser.get() + " " + e.getMessage());
			throw new TestException(runningBrowser.get() + " Exception error in method <dragAndDropElementToElement()>");
		}
	}

	//============================================
	/**
	 * Wrapped method over Selenium Actions default dragAndDropBy() method, enhanced with:</br>
	 * 1. Logging mechanism</br>
	 * 2. returnElement() method</br>
	 * 3. catch General exception</br>
	 * 
	 * @param locator (By locator) --> element to be moved
	 * @param x       (Integer value) --> horizontal move
	 * @param y       (Integer value) --> vertical move
	 */
	public void dragAndDrop(By locator, int x, int y) {
		Log.info(runningBrowser.get() + " Called method <dragAndDrop()> on element " + locator.toString());
		try {
			Actions action = new Actions(driver);
/*			action 
					.moveToElement(element) 
					.clickAndHold() 
					.moveByOffset(x, y) 
					.release()
					.perform();
*/			action.dragAndDropBy(returnElement(locator), x, y).perform();
		} catch (Exception e) {
			Log.error(runningBrowser.get() + " Caught Exception error in method <dragAndDrop()> on: " + locator.toString());
			Log.error(runningBrowser.get() + " " + e.getMessage());
			throw new TestException(runningBrowser.get() + " Exception error in method <dragAndDrop()>");
		}
	}

	//================================================================================================================================	
	//REDIRECTED URL METHOD	
	//============================================	
	/**
	 * Wrapped method over Selenium default driver.switchTo().window() functionality, enhanced with:</br>
	 * 1. Logging mechanism</br>
	 * 2. click() custom method</br>
	 * 3. driver.close() functionality</br>
	 * 4. catch General exception</br>
	 * 
	 * @param locator (By locator)
	 * @return String (String value of current URL)
	 */
	public String checkRedirectedUrlAndReturnToInitialPage(By locator) {
		Log.info(runningBrowser.get() + " Called method <checkRedirectedUrlAndReturnToInitialPage()> on element " + locator.toString());
		try {
			click(locator);
			List<String> browserTabs = new ArrayList<>(driver.getWindowHandles());
			driver.switchTo().window(browserTabs.get(1));
			String currentURL = driver.getCurrentUrl();
			driver.close();
			driver.switchTo().window(browserTabs.get(0));
			return currentURL;
		} catch (Exception e) {
			Log.error(runningBrowser.get() + " Caught Exception error in method <checkRedirectedUrlAndReturnToInitialPage()>");
			Log.error(runningBrowser.get() + " " + e.getMessage());
			return e.getMessage();
		}
	}

}
