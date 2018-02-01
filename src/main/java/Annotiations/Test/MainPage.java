package Annotiations.Test;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import Constants.Constants;

public class MainPage extends BasePage{
	
	public MainPage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(className="tescoce-logo")
	private WebElement tescoLogo;
	
	public boolean openPage() throws IOException {
		
		boolean elements = false;
		String url=Constants.getUrl();
		driver.get(url);
		//Wait for Logo to be loaded
		waitElement(tescoLogo);
		//Check if Logo is displayed
		if(tescoLogo.isDisplayed()) {
			elements=true;
			Log.info(url + " is correctly displayed");
		}else {
			elements=false;
			Log.warn(url + " is not displayed correctly");
		}
		return elements;
				
	}
	
}
