package Annotiations.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasePage {

	protected WebDriver driver;
	protected WebDriverWait wait;
	protected final Logger Log = LoggerFactory.getLogger(getClass());
	
	
	
	public BasePage(WebDriver driver) {
		this.driver=driver;
				PageFactory.initElements(this.driver, this);
	}
	
	
	public void waitElement(WebElement toWait) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(toWait));
		
	}
}
