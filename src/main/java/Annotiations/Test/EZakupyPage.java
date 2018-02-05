package Annotiations.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class EZakupyPage extends BasePage {
	
	public EZakupyPage(WebDriver driver) {
		
		super(driver);
	}
	
	@FindBy(className="utility-header__login")
	private WebElement zalogujBtn;
	@FindBy(id="email")
	private WebElement loginFld;
	@FindBy(id="password")
	private WebElement passwordFld;
	@FindBy(className="smart-submit-button")
	private WebElement submitBtn;
	@FindBy(className="utility-header__logout-button")
	private WebElement wylogujBtn;
	
	public void logIn() {
		clickElement(zalogujBtn);
		waitForPageLoad();
		loginFld.sendKeys(properties.GetProperties.getLogin());
		passwordFld.sendKeys(properties.GetProperties.getPassword());
		clickElement(submitBtn);
		
	}
	
	public boolean isUserLogged() {
		boolean isUserLoogged = false;
		isUserLoogged=isElementOnPage(wylogujBtn);
		if(isUserLoogged=true) {
			Log.info("User is loged in to service");
		}else {
			Log.info("User is not loged in to service");
		}
		return isUserLoogged;
		}
		
		
		
	}
	
	


