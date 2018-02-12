package Annotiations.Test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LogInToShopAndAdd2ItemsVerifyPrice extends BaseTest {

	@Test(testName = "OpenFirstPage", priority = 1, description = "Opening page Tesco.pl")
	public void OpenMainPage() throws IOException {
		mPage.openPage();
		Assert.assertTrue(mPage.isTescoLogo(), "Main page was not loaded correctly - cannot find logo icon: ");
	}

	@Test(priority = 2, description = "Go to eZakupy page")
	public void GoToEZakupyPage() {
		mPage.goToEzakupy();
		Assert.assertTrue(mPage.eZakupyVerif(), "EZakupy page was not loaded correctly - cannot find greetLogo ");
	}

	@Test(priority = 3, description = "Loging to eZakupy application")
	public void LoginToEZakupy() {
		eZakupy.logIn();
		Assert.assertTrue(eZakupy.isUserLogged(), "User was not logged in to service, logout option is not displayed");
	}

	@Test(priority = 4, description = "Searching and adding products to the trolley")
	public void AddToCart(){
		eZakupy.cleanTrolley();
		eZakupy.cheepestProductFinder("cola");
		eZakupy.cheepestProductFinder("chipsy");
		eZakupy.cheepestProductFinder("ciastka");
		Assert.assertTrue(eZakupy.isSumPriceRight(), "Pice sum calculated is not eqal with sum calculated by trolley");
		
	}
}
