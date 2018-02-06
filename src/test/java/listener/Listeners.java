package listener;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.LogStatus;

import Annotiations.Test.BaseTest;
import driver.DriverGenerator;
import extentReports.ExtentManager;
import extentReports.ExtentTestManager;

public class Listeners extends BaseTest implements ITestListener {

	public void onTestStart(ITestResult result) {
		Log.info("I am in onTestStart method " + result.getMethod().getMethodName() + " start");
        //Start operation for extentreports.
        ExtentTestManager.startTest(result.getMethod().getMethodName(),"");
	
	}
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		Log.info("Test " + result.getMethod()+" Passed!");
		//Extentreports log operation for passed tests.
        ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
	}

	public void onTestFailure(ITestResult result) {
		Log.error("Test " + result.getMethod()+" Failed!");
		
		Object testClass = result.getInstance();
        WebDriver webDriver = driver;
 
        //Take base64Screenshot screenshot.
        String base64Screenshot = "data:image/png;base64,"+((TakesScreenshot)webDriver).
                getScreenshotAs(OutputType.BASE64);
 
        //Extentreports log and screenshot operations for failed tests.
        ExtentTestManager.getTest().log(LogStatus.FAIL,"Test Failed",
                ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		Log.warn("Test " + result.getMethod()+" Skipped!");	
		//Extentreports log operation for skipped tests.
        ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		Log.info("I am in onStart method " + context.getName());
        context.setAttribute("WebDriver", this.driver);
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("I am in onFinish method " + context.getName());
        //Do tier down operations for extentreports reporting!
        ExtentTestManager.endTest();
        ExtentManager.getReporter().flush();
	}
	
}