package Annotiations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentTest;

import Annotiations.Test.EZakupyPage;
import Annotiations.Test.MainPage;
import driver.DriverGenerator;
import extentReports.ReportManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTest {

	protected WebDriver driver;
	protected final Logger Log = LoggerFactory.getLogger(getClass());
	public MainPage mPage;
	public EZakupyPage eZakupy;
	public ReportManager rM;
	
	
	public WebDriver gimiDriver() {
        return driver;
    }

	@BeforeClass
	public void initialiseDriver() throws IOException {
		driver = DriverGenerator.getDriver();
		Log.info("Driver initialised");
		mPage = new MainPage(driver);
		eZakupy = new EZakupyPage(driver);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

	}

	@BeforeMethod
	public void beforeMethod() {

		System.out.println("We are in before method");
	}

	
	@AfterClass
	public void quitDriver() {
		driver.quit();
		Log.info("Driver terminated");
				
	}

}