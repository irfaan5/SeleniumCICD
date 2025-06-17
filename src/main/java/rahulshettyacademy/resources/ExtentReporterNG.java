package rahulshettyacademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentReports getReportObject() {
		// ExtentReports(),  ExtentSparkReporter()
				String path=System.getProperty("user.dir")+"\\reports\\extentReport.html";
				ExtentSparkReporter reporter=new ExtentSparkReporter(path);
				reporter.config().setDocumentTitle("Web Automation Report");
				reporter.config().setReportName("Extent Report");
				
				ExtentReports extent=new ExtentReports();
				extent.attachReporter(reporter);
				extent.setSystemInfo("AutomationQA", "Muhmmed Irfaan");
				return extent;
	}
}
