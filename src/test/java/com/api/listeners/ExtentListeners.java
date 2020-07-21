package com.api.listeners;

import java.net.InetAddress;
import java.util.Arrays;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.api.utilities.MonitoringMail;
import com.api.utilities.TestConfig;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;



public class ExtentListeners implements ITestListener, ISuiteListener {

	static String messageBody;

	static Date d = new Date();
	static String fileName = "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";

	private static ExtentReports extent = ExtentManager
			.createInstance(System.getProperty("user.dir") + "\\extentreports\\" + fileName);

	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) {

		ExtentTest test = extent
				.createTest(result.getTestClass().getName() + "     @TestCase : " + result.getMethod().getMethodName());
		testReport.set(test);

	}

	public void onTestSuccess(ITestResult result) {

		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		testReport.get().pass(m);

	}

	public void onTestFailure(ITestResult result) {

		// Will print the log on extent report
		testReport.get().fail(result.getThrowable().getMessage().toString());

		String excepionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		testReport.get()
		.fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
				+ "</font>" + "</b >" + "</summary>" + excepionMessage.replaceAll(",", "<br>") + "</details>"
				+ " \n");

		/*
		 * try {
		 * 
		 * ExtentManager.captureScreenshot(); testReport.get().fail("<b>" +
		 * "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
		 * MediaEntityBuilder.createScreenCaptureFromPath(ExtentManager.screenshotName)
		 * .build()); } catch (IOException e) {
		 * 
		 * }
		 */

		String failureLogg = "TEST CASE FAILED";
		Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
		testReport.get().log(Status.FAIL, m);

	}

	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "Test Case:- " + methodName + " Skipped" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		testReport.get().skip(m);

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {

		if (extent != null) {

			extent.flush();
		}

	}

	public void onStart(ISuite suite) {

	}

	public void onFinish(ISuite suite) {

		try {
			System.out.println("System local host address : "+InetAddress.getLocalHost().getHostAddress());

			//Complete address of jenkins extent report
			//http://localhost:8080/job/CarsSearchGuideCucumber/Extent_20Report/

			System.out.println("Jenkins Extent Report Path : ==============>>>>>>");
			messageBody="http://"+InetAddress.getLocalHost().getHostAddress()+":8080/job/CarsSearchGuideCucumber/Extent_20Report/";
		} catch (Exception e) {
			e.printStackTrace();
		}

		MonitoringMail moniter=new MonitoringMail();
		try {
			moniter.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
