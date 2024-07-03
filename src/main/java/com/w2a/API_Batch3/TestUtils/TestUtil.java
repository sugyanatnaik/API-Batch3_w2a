package com.w2a.API_Batch3.TestUtils;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.w2a.API_Batch3.setUp.APISetUp;

public class TestUtil extends APISetUp {

	public static void archiveTestReport() {

		String reportName = configProperty.getTestReportName();

		String lastTestReportFilePath = System.getProperty("user.dir") + "/src/test/resources/testReports/";
		String archiveReportPath = System.getProperty("user.dir") + "/src/test/resources/archivedTestReport/";

		Date date = new Date();
		SimpleDateFormat dateFormate = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		String formatedDate = dateFormate.format(date);
		String archiveTestReportName = formatedDate + "_" + reportName;

		File oldReport = new File(lastTestReportFilePath + reportName);

		File newFile = new File(archiveReportPath + archiveTestReportName);

		System.out.println(oldReport.exists());

		if (oldReport.exists()) {
			System.out.println("inside if");
			oldReport.renameTo(newFile);
			oldReport.delete();
		}

	}
}
