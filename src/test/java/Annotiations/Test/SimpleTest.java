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
}
