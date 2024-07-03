package com.w2a.API_Batch3.testCases;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.w2a.API_Batch3.TestUtils.DataProviderClass;
import com.w2a.API_Batch3.setUp.APISetUp;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestCreateCustomerAPI extends APISetUp {

	@Test(dataProvider = "dp", dataProviderClass = DataProviderClass.class, priority = 1, enabled = true)
	public void validateCreateCustomerAPI(Hashtable<String, String> data) {
		// Stripe URL for web : https://docs.stripe.com/api/customers/list
		// Stripe URL for API Keys : https://dashboard.stripe.com/test/apikeys

//		ConfigProperty configProperty= ConfigFactory.create(ConfigProperty.class);
//
//		RestAssured.baseURI = configProperty.getBaseURI();
//		RestAssured.basePath = configProperty.getBasePath();

		testLevelLog.get().assignAuthor("Sugyan");
		testLevelLog.get().assignCategory("Regression");
		RequestSpecification spec = setRequestSpecification().formParam("email", data.get("email"))
				.formParam("description", data.get("description")).formParam("balance", 100).log().all();

		System.out.println("=========================================================");

//		Response response = spec.post("customers");
		Response response = spec.request("post", "customers");

		testLevelLog.get().info(response.asString());

		// Fetch the email from the response
		String emailInTheResponse = response.path("email");
		System.out.println("Email in the response --> " + emailInTheResponse);

		// Fetch the description from the response
		String descriptionInTheResponse = response.path("description");
		System.out.println("description in the response --> " + descriptionInTheResponse);

		// Fetch Footer
		System.out.println("Footer in the response --> " + response.path("invoice_settings.footer"));
		System.out.println("Url in the response --> " + response.path("subscriptions.url"));

		JsonPath responseJson = new JsonPath(response.asString());
		System.out.println("Email using JsonPath --> " + responseJson.get("email"));

		response.prettyPrint();

		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 0, enabled = false)
	public void m1() {
		Assert.fail();
	}

}
