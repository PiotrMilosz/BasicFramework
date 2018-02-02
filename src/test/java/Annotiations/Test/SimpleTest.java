package Annotiations.Test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import ExtentReports.ExtentTestManager;

public class SimpleTest extends BaseTest {

	@Test(testName = "OpenFirstPage", priority = 1, description = "Uruchomienie strony Tesco.pl")
	public void OpenMainPage() throws IOException {
		// Extent Report description
		ExtentTestManager.getTest().setDescription("Uruchomienie strony Tesco.pl");
		Assert.assertTrue(mPage.openPage());

	}

	@Test(priority = 2, description = "Przejście na strone eZakupy")
	public void GoToEZakupyPage() {
		ExtentTestManager.getTest().setDescription("Przejście na strone eZakupy");
		mPage.goToEzakupy();
	}

	@Test(priority = 3, description = "Zalogowanie do eZakupy")
	public void LoginToEZakupy() {
		ExtentTestManager.getTest().setDescription("Zalogowanie się do eZakupy");
		Assert.assertTrue(eZakupy.logIn());
	}
}
