package com.w2a.API_Batch3.TestUtils;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({ "file:src/test/resources/propertyFiles/config.properties" })
public interface ConfigProperty extends Config {

	@Key("baseURI")
	String getBaseURI();

	@Key("basePath")
	String getBasePath();

	@Key("secretKey")
	String getSecretKey();

	@Key("testReportPath")
	String getTestReportFilePath();

	@Key("testReportName")
	String getTestReportName();
	
	@Key("testDataSheetName")
	String getTestDataSheetName();


}
