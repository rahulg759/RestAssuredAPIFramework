package com.api.frameworkapis;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;

import com.api.base.BaseTest;

import io.restassured.response.Response;

public class DeleteCustomerAPI extends BaseTest{

	public static Response sendDeleteRequesttoDeleteAPIWithValidID(Hashtable<String,String> data) {
		Response response=given().auth().basic(prop.getProperty("validSecretKey"), "")
				.delete(prop.getProperty("customerAPIEndPoint")+"/"+data.get("id"));

		return response;
	}
}
