package com.w2a.API_Batch3.listeners;

import java.util.Arrays;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.w2a.API_Batch3.setUp.APISetUp;

public class CustomListeners extends APISetUp implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) { // This is same as @BeforeMethod

		System.out.println("Inside the TestStart method");
//		ExtentTest test = classLevelLog.get().createNode(result.getName());
//		testLevelLog.set(test);
		testLevelLog.get().info("<b>" + "Testcase :- " + result.getName() + " : execution started" + "</b>");
	}

	@Override
	public void onTestSuccess(ITestResult result) { // This is same as @AfterMethod

		System.out.println("Inside the TestSuccess method");
		testLevelLog.get().pass("<b>" + "This Test Case got Passed" + "</b>");
		extentReport.flush();
	}

	@Override
	public void onTestFailure(ITestResult result) {

		System.out.println("Inside the TestFailure method");
		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		testLevelLog.get()
				.fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
						+ "</font>" + "</b >" + "</summary>" + exceptionMessage.replaceAll(",", "<br>") + "</details>"
						+ " \n");
		String failureLogg = "This Test case got Failed";
		Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
		testLevelLog.get().log(Status.FAIL, m);

		extentReport.flush();

	}

	@Override
	public void onTestSkipped(ITestResult result) {

		System.out.println("Inside the TestSkipped method");

		/*
		 * test = classLevelLog.get().createNode(result.getName());
		 * testLevelLog.set(test);
		 */

		testLevelLog.get().skip("<b>" + "This Test Case got Skipped - " + "</b>" + result.getName());
		extentReport.flush();
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
	}

}
