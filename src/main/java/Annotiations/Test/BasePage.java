package Annotiations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasePage {

	protected WebDriver driver;
	protected WebDriverWait wait;
	protected final Logger Log = LoggerFactory.getLogger(getClass());

	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public void waitElement(WebElement toWait) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(toWait));

	}

	public void clickElement(WebElement toClick) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(toClick));
		toClick.click();
	}

	public void waitForPageLoad() {
		wait = new WebDriverWait(driver, 10);
		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver wdriver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		});
	}
	protected boolean isElementOnPage(WebElement element) {
		driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        boolean isElementOnPage = true;
        try {
            // Get location on WebElement is rising exception when element is not present
            element.getLocation();
        } catch (WebDriverException ex) {
            isElementOnPage = false;
        } finally {
        	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        }
        return isElementOnPage;
}
}