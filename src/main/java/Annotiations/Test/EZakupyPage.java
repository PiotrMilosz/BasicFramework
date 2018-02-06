package Annotiations.Test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class EZakupyPage extends BasePage {

	public EZakupyPage(WebDriver driver) {

		super(driver);
	}

	// Obiekty ze strony Tesco eZakupy
	@FindBy(className = "utility-header__login")
	private WebElement zalogujBtn;
	@FindBy(id = "email")
	private WebElement loginFld;
	@FindBy(id = "password")
	private WebElement passwordFld;
	@FindBy(className = "smart-submit-button")
	private WebElement submitBtn;
	@FindBy(className = "utility-header__logout-button")
	private WebElement wylogujBtn;
	@FindBy(id = "search-input")
	public WebElement search;
	@FindBy(xpath = ".//*[@id='search-form']/button")
	public WebElement searchBtn;

	// Logowanie do Tesco e-zakupy
	public void logIn() {
		clickElement(zalogujBtn);
		waitForPageLoad();
		loginFld.sendKeys(properties.GetProperties.getLogin());
		passwordFld.sendKeys(properties.GetProperties.getPassword());
		clickElement(submitBtn);

	}

	// Weryfikacja zalogowania do Tesco-ezakupy
	public boolean isUserLogged() {
		boolean isUserLoogged = false;
		isUserLoogged = isElementOnPage(wylogujBtn);
		if (isUserLoogged = true) {
			Log.info("User is loged in to service");
		} else {
			Log.info("User is not loged in to service");
		}
		return isUserLoogged;
	}

	// Product searcher and price verifier, adding to cart cheapest product

	public void cheepestProductFinder(String whatToSearch) {
		search.sendKeys(whatToSearch);
		Log.info("Search Item podane");
		searchBtn.click();
		Log.info("Search kliknięte");
		// Time for reload
		waitForPageLoad();
		// Price Verifier
		List<WebElement> listOfPrices = driver.findElements(By.className("tile-content"));
		double priceDouble = 10000.00;
		String itemPrice = null;
		String butterName = null;
		WebElement thisKupBtn = null;
		for (int i = 0; i < listOfPrices.size(); i++) {
			// Cena za kg
			WebElement pricePerKgWE = listOfPrices.get(i).findElement(By.className("price-per-quantity-weight"));
			WebElement priceValueWE = pricePerKgWE.findElement(By.className("value"));
			String pricePerKgStr = priceValueWE.getText();
			String filteredString = pricePerKgStr.replaceAll(",", ".");
			double pricePerKgDouble = Double.parseDouble(filteredString);
			// Cena detaliczna
			WebElement pricePerItemWE = listOfPrices.get(i).findElement(By.className("price-control-wrapper"));
			WebElement priceValueItemWE = pricePerItemWE.findElement(By.className("value"));
			String pricePerItemStr = priceValueItemWE.getText();
			// Nazwa masała
			WebElement nameWE = listOfPrices.get(i).findElement(By.className("product-details--content"));
			WebElement nameTitle = nameWE.findElement(By.className("product-tile--title"));
			String nameStr = nameTitle.getText();
			// Przechwytywanie kupBtn
			WebElement kupBtn = listOfPrices.get(i).findElement(By.tagName("button"));
			// Porównywarka
			if (priceDouble > pricePerKgDouble) {
				priceDouble = pricePerKgDouble;
				butterName = nameStr;
				itemPrice = pricePerItemStr;
				thisKupBtn = kupBtn;
			}
		}
		thisKupBtn.click();
		Log.info("Wynik dla wyszukiwania " + whatToSearch + " to " + butterName + " w cenie " + itemPrice
				+ " zl, co wychodzi za sztuke/kg/litr " + priceDouble + " zl BIEREJ!!");
		// Clear search
		search.clear();
		// Add to cart
	}

	// Add to cart verifier
	public boolean isAddToCart() {
		boolean isAddToCart = false;

		List<WebElement> listOfPrices = driver.findElements(By.className("tile-content"));
		double priceDouble = 10000.00;
		String itemPrice = null;
		String butterName = null;
		for (int i = 0; i < listOfPrices.size(); i++) {
			// Cena za kg
			WebElement pricePerKgWE = listOfPrices.get(i).findElement(By.className("price-per-quantity-weight"));
			WebElement priceValueWE = pricePerKgWE.findElement(By.className("value"));
			String pricePerKgStr = priceValueWE.getText();
			String filteredString = pricePerKgStr.replaceAll(",", ".");
			double pricePerKgDouble = Double.parseDouble(filteredString);
			// Cena detaliczna
			WebElement pricePerItemWE = listOfPrices.get(i).findElement(By.className("price-control-wrapper"));
			WebElement priceValueItemWE = pricePerItemWE.findElement(By.className("value"));
			String pricePerItemStr = priceValueItemWE.getText();
			// Nazwa masała
			WebElement nameWE = listOfPrices.get(i).findElement(By.className("product-details--content"));
			WebElement nameTitle = nameWE.findElement(By.className("product-tile--title"));
			String nameStr = nameTitle.getText();
			// porównywarka
			if (priceDouble > pricePerKgDouble) {
				priceDouble = pricePerKgDouble;
				butterName = nameStr;
				itemPrice = pricePerItemStr;
			}
		}

		List<WebElement> listOfTroleyArtifacts = driver.findElements(By.className("mini-tile__product-info"));
		double priceTroley;
		String itemTroleyPriceMach = null;
		String itemTroleyName = null;
		for (int i2 = 0; i2 < listOfTroleyArtifacts.size(); i2++) {
			// Name of Item in Troley
			WebElement nameWETr = listOfTroleyArtifacts.get(i2).findElement(By.className("mini-tile__title"));
			WebElement nameTitleTr = nameWETr.findElement(By.className("mini-tile__title-wrapper"));
			itemTroleyName = nameTitleTr.getText();
			System.out.println(itemTroleyName);
			if (itemTroleyName.equals(butterName)) {
				itemTroleyPriceMach = "1";
				Log.info("Mach was found");
			}else {
				Log.info("Mach was not Found");
			}
			if (itemTroleyPriceMach.equals("1")) {
				isAddToCart = true;
			}
		}
		List<WebElement> listOfTroleyArtifactsOverall = driver.findElements(By.className("mini-tile__content"));
		for (int i3 = 0; i3 < listOfTroleyArtifactsOverall.size(); i3++) {
			WebElement deleteItemBtn = listOfTroleyArtifactsOverall.get(i3).findElement(By.className("delete-single-item"));
			deleteItemBtn.click();
		}

		return isAddToCart;
	}

}
