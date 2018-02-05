package Annotiations.Test;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import properties.GetProperties;

public class MainPage extends BasePage{
	
	public MainPage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(className="tescoce-logo")
	private WebElement tescoLogo;
	@FindBy(className="e-zakupy")
	private WebElement eZakupyBtn;
	@FindBy(className="user-greeting--message")
	private WebElement greetLogo;
	
	public void openPage() throws IOException {
		
		String url=GetProperties.getUrl();
		driver.get(url);
		//Wait for Logo to be loaded
		waitElement(tescoLogo);
		//Check if Logo is displayed
		waitForPageLoad();
		
	}
	
	public boolean isTescoLogo(){
		boolean isTescoLogo = false;
		isTescoLogo=isElementOnPage(tescoLogo);
		if(isTescoLogo=true) {
			Log.info("User is on Page");
		}else {
			Log.info("User is not on Page");
		}
		return isTescoLogo;
			
						
	}
	//PRzejscie na stronę e-zakupy
	public void goToEzakupy() {
		clickElement(eZakupyBtn);		
		//Przełączanie na nowego taba
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));	
		waitForPageLoad();
		
	}
	
	public boolean eZakupyVerif() {
		boolean eZakupyVerif=false;
		eZakupyVerif=isElementOnPage(greetLogo);
		if(eZakupyVerif=true) {
			Log.info("User is on eZakupy");
		}else {
			Log.info("User is not on eZakupy");
		}
		return eZakupyVerif;
	
	}
	
	
}
