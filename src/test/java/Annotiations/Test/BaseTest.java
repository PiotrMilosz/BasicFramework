package Annotiations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Driver.DriverGenerator;

@Listeners(Listener.Listeners.class)
public class BaseTest {

	protected WebDriver driver;
	protected final Logger Log = LoggerFactory.getLogger(getClass());
	public MainPage mPage;
	

	@BeforeClass
	public void initialiseDriver() throws IOException {
		driver = DriverGenerator.getDriver();
		Log.info("Driver initialised");
		mPage = new MainPage(driver);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

	}

	@BeforeMethod
	public void beforeMethod() {

		System.out.println("We are in before method");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("We are in after method");
	}
	@AfterClass
	public void quitDriver() {
		driver.quit();
		Log.info("Driver terminated");
				
	}

}