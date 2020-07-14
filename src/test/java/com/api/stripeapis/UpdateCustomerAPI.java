package com.api.stripeapis;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;

import com.api.base.BaseTest;

import io.restassured.response.Response;

public class UpdateCustomerAPI extends BaseTest{

	public static Response sendPostRequesttoUpdateCustomerAPIWithValidAuthKey(Hashtable<String,String> data) {
		Response response=given().auth().basic(prop.getProperty("validSecretKey"), "")
				.formParam("email",data.get("email"))
				.formParam("name",data.get("name"))
				.formParam("description",data.get("description"))
				.post(prop.getProperty("customerAPIEndPoint"));

		return response;
	}
}
