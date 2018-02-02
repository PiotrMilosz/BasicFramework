package Annotiations.Test;

import java.io.IOException;
import java.util.ArrayList;

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
	@FindBy(className="e-zakupy")
	private WebElement eZakupyBtn;
	
	public boolean openPage() throws IOException {
		
		boolean elements = false;
		String url=Constants.getUrl();
		driver.get(url);
		//Wait for Logo to be loaded
		waitElement(tescoLogo);
		//Check if Logo is displayed
		waitForPageLoad();
		
		if(tescoLogo.isDisplayed()) {
			elements=true;
			Log.info(url + " is correctly displayed");
		}else {
			elements=false;
			Log.warn(url + " is not displayed correctly");
		}
		return elements;
				
	}
	//PRzejscie na stronę e-zakupy
	public void goToEzakupy() {
		clickElement(eZakupyBtn);		
		//Przełączanie na nowego taba
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));	
		waitForPageLoad();
		Log.info("Przejście na strone e-zakupy");
	}
	
	
}
