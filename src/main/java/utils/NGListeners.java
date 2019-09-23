package utils;
import java.io.File;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class NGListeners implements ITestListener{

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;

	public void onTestStart(ITestResult result) {
	}

	public void onTestSuccess(ITestResult result) {
		logger = extent.createTest(result.getName()); // create new entry in the report
		logger.log(Status.PASS,result.getName());
	}
	public void onTestFailure(ITestResult result) {
		logger=extent.createTest(result.getName()); // create new entry in the report
		logger.log(Status.FAIL,result.getName());

		String screenshotPath=System.getProperty("user.dir")+"\\Screenshots\\"+result.getName()+".png";

		File f = new File(screenshotPath); 

		if(f.exists())
		{
			try {
				logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}

	}	

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// Create Object of ExtentHtmlReporter and provide the path where you want to generate the report 
				// I used (.) in path where represent the current working directory
				htmlReporter=new ExtentHtmlReporter("./Reports/learn_automation1.html");

				// Create object of ExtentReports class- This is main class which will create report
				extent = new ExtentReports();

				// attach the reporter which we created in Step 1
				extent.attachReporter(htmlReporter);

				extent.setSystemInfo("Environment", "QA");
				extent.setSystemInfo("user", "Keerthana");

				htmlReporter.config().setDocumentTitle("Selenium automation");
				htmlReporter.config().setReportName("Functional Test Report");
				htmlReporter.config().setTheme(Theme.STANDARD);		
			}
	

	public void onFinish(ITestContext context) {
		extent.flush();

	}

}
