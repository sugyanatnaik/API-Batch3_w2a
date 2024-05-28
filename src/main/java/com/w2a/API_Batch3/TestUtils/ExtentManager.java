package com.w2a.API_Batch3.TestUtils;

import java.io.IOException;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.w2a.API_Batch3.setUp.APISetUp;

public class ExtentManager extends APISetUp {

	private static ExtentReports extent;
	// private static ExtentTest test;
	private static ExtentSparkReporter sparkReporter;
	// private static String filePath = "./extentreport.html";

	public static ExtentReports GetExtent(String filePath) {
		if (extent != null) {
			return extent;
		} else {
			extent = new ExtentReports();
			extent.attachReporter(getHtmlReporter(filePath));
			extent.setSystemInfo("Host Name", "Sugyan@Java");

			extent.setAnalysisStrategy(AnalysisStrategy.CLASS);
			return extent;
		}
	}

	public static ExtentSparkReporter getHtmlReporter(String filePath) {

		sparkReporter = new ExtentSparkReporter(filePath);
		/*
		 * htmlReporter.config().setChartVisibilityOnOpen(true);
		 * htmlReporter.config().setDocumentTitle(" Automation Report");
		 * htmlReporter.config().setReportName("Automation");
		 * htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		 */

//		 htmlReporter.setAppendExisting(false);
		try {
			sparkReporter.loadXMLConfig(
					System.getProperty("user.dir") + "/src/test/resources/configXMLFiles/ReportsConfig.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sparkReporter;
	}

}
