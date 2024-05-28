package com.w2a.API_Batch3.setUp;

import static io.restassured.RestAssured.given;

import java.lang.reflect.Method;

import org.aeonbits.owner.ConfigFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.w2a.API_Batch3.TestUtils.ConfigProperty;
import com.w2a.API_Batch3.TestUtils.ExcelReader;
import com.w2a.API_Batch3.TestUtils.ExtentManager;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class APISetUp {
	public static ConfigProperty configProperty;
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "/src/test/resources/testData/TestData.xlsx");
	public static ExtentReports extentReport;
	public static ThreadLocal<ExtentTest> classLevelLog = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<ExtentTest> testLevelLog = new ThreadLocal<ExtentTest>();

	@BeforeSuite
	public void beforeSuite() {

		configProperty = ConfigFactory.create(ConfigProperty.class);

		RestAssured.baseURI = configProperty.getBaseURI();
		RestAssured.basePath = configProperty.getBasePath();

		extentReport = ExtentManager
				.GetExtent(configProperty.getTestReportFilePath() + configProperty.getTestReportName());
	}

	@BeforeClass
	public void beforeClass() {
		ExtentTest classLevelTest = extentReport.createTest(getClass().getSimpleName());
		classLevelLog.set(classLevelTest);
	}

	@BeforeMethod
	public void beforeMethod(Method method) {

		ExtentTest test = classLevelLog.get().createNode(method.getName());
		testLevelLog.set(test);
		testLevelLog.get().info("Testcase :- " + method.getName() + " : execution started");

//		System.out.println("Testcase :- " + method.getName() + " : execution started");
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {

		if (result.getStatus() == ITestResult.SUCCESS) {
			testLevelLog.get().pass("This testcase is PASSED");
		} else if (result.getStatus() == ITestResult.FAILURE) {
			testLevelLog.get().fail("This testcase is FAILED");
		} else if (result.getStatus() == ITestResult.SKIP) {
			testLevelLog.get().skip("This testcase is SKIPPED");
		}

		extentReport.flush();
	}

	@AfterSuite
	public void afterSuite() {

	}

	public static RequestSpecification setRequestSpecification() {
		RequestSpecification spec = given().auth().basic(configProperty.getSecretKey(), "");
		testLevelLog.get().info("Request Specification set");
		return spec;
	}

}
