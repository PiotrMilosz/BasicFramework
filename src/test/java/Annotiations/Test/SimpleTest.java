package Annotiations.Test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import extentReports.ExtentTestManager;

public class SimpleTest extends BaseTest {

	@Test(testName = "OpenFirstPage", priority = 1, description = "Uruchomienie strony Tesco.pl")
	public void OpenMainPage() throws IOException {
		mPage.openPage();
		Assert.assertTrue(mPage.isTescoLogo(),"Main page was not loaded correctly - cannot find logo icon: ");
	}

	@Test(priority = 2, description = "Przejście na strone eZakupy")
	public void GoToEZakupyPage() {
		mPage.goToEzakupy();
		Assert.assertTrue(mPage.eZakupyVerif(),"EZakupy page was not loaded correctly - cannot find greetLogo ");
	}

	@Test(priority = 3, description = "Zalogowanie do eZakupy")
	public void LoginToEZakupy() {
		eZakupy.logIn();
		Assert.assertTrue(eZakupy.isUserLogged(),"User was not logged in to service, logout option is not displayed");
	}
}
