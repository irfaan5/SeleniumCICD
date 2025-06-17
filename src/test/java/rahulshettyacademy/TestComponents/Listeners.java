package rahulshettyacademy.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacademy.resources.ExtentReporterNG;


	public class Listeners extends BaseTest implements ITestListener{
		
		ExtentReports extent=ExtentReporterNG.getReportObject();
		ExtentTest test;
		ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();   //Thread Safe
		// Using ThreadLocal you will make this object Thread Safe
		// Each Object Creation has its own Thread, it won't interrupt the other overriding variable
		@Override
		public void onTestStart(ITestResult result) {
			test=extent.createTest(result.getMethod().getMethodName());
			extentTest.set(test); // Assign one unique thread id & mapped it with test
		}

		@Override
		public void onTestSuccess(ITestResult result) {
			extentTest.get().log(Status.PASS, "Test PASSED!");
		}

		@Override
		public void onTestFailure(ITestResult result) {
			
			extentTest.get().fail(result.getThrowable());
			
			
			try {
				driver = (WebDriver) result.getTestClass().getRealClass().
						getField("driver").get(result.getInstance());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			
			String filePath = null;
			try {
				filePath = getScreenshot(result.getMethod().getMethodName(), driver);
			} catch (IOException e) {
				e.printStackTrace();
			}
			extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		}

		@Override
		public void onTestSkipped(ITestResult result) {
			
		}

		@Override
		public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
			
		}

		@Override
		public void onTestFailedWithTimeout(ITestResult result) {
			
		}

		@Override
		public void onStart(ITestContext context) {
			
		}

		@Override
		public void onFinish(ITestContext context) {
			extent.flush();
			
		}


}
