package com.api.stripeapis;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;

import com.api.base.BaseTest;

import io.restassured.response.Response;

public class RetrieveCustomerAPI extends BaseTest{

	public static Response sendGetRequesttoRetrieveCustomerIDAPIWithValidAuthKey(Hashtable<String,String> data) {
		Response response=given().auth().basic(prop.getProperty("validSecretKey"), "")
				.get(prop.getProperty("customerAPIEndPoint")+"/"+data.get("id"));

		return response;
	}
}
