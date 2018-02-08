package listener;

import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;

import Annotiations.Test.BaseTest;
import extentReports.ReportManager;


public class Listeners extends BaseTest implements ITestListener {
	private ReportManager rM;

	public void onStart(ITestContext context) {
		ExtentTest parent = rM.getReporter().createTest(context.getName());
        rM.getParentTest().set(parent);
		Log.info("I am in onStart method " + context.getName());
        context.setAttribute("WebDriver", this.driver);
	}

	public void onTestStart(ITestResult result) {
		Log.info("I am in onTestStart method " + result.getMethod().getMethodName() + " start");
        //Start operation for extentreports.
		ExtentTest child = rM.getParentTest().get().createNode(result.getMethod().getDescription());
        rM.getTest().set(child);
	}
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		Log.info("Test " + result.getMethod()+" Passed!");
		//Extentreports log operation for passed tests.
		rM.getTest().get().pass("Test passed");
	}

	public void onTestFailure(ITestResult result) {
		Log.error("Test " + result.getMethod()+" Failed!");
		
		Object testClass = result.getInstance();
        WebDriver webDriver = ((BaseTest) testClass).gimiDriver();
 
        //Take base64Screenshot screenshot.
        String base64Screenshot = "data:image/png;base64,"+((TakesScreenshot)webDriver).
                getScreenshotAs(OutputType.BASE64);
 
        //Extentreports log and screenshot operations for failed tests.
        rM.getTest().get().fail("Test Failed");
              try {
				rM.getTest().get().addScreencastFromPath(base64Screenshot);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	public void onTestSkipped(ITestResult result) {

		Log.warn("Test " + result.getMethod()+" Skipped!");	
		//Extentreports log operation for skipped tests.
		  rM.getTest().get().skip(result.getThrowable());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("I am in onFinish method " + context.getName());
        //Do tier down operations for extentreports reporting!
		rM.getReporter().flush();
	}
}