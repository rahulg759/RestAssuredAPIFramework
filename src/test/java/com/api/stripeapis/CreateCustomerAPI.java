package com.api.stripeapis;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;
import com.api.base.BaseTest;
import io.restassured.response.Response;

public class CreateCustomerAPI extends BaseTest{

	public static Response sendPostRequesttoCreateCustomerAPIWithValidAuthKey(Hashtable<String,String> data) {
		Response response=given().auth().basic(prop.getProperty("validSecretKey"), "")
				.formParam("email",data.get("email"))
				.formParam("name",data.get("name"))
				.formParam("address[line1]",data.get("address"))
				.formParam("description",data.get("description"))
				.post(prop.getProperty("customerAPIEndPoint"));

		return response;
	}

	public static Response sendPostRequesttoCreateCustomerAPIWithInValidAuthKey(Hashtable<String,String> data) {
		Response response=given().auth().basic(prop.getProperty("invalidSecretKey"), "")
				.formParam("email",data.get("email"))
				.formParam("name",data.get("name"))
				.formParam("address[line1]",data.get("address"))
				.formParam("description",data.get("description"))
				.post(prop.getProperty("customerAPIEndPoint"));

		return response;
	}
}
