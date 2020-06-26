package com.api.testcases;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class CreateCustomerTest {

	@Test
	public void validateCreateCustomerAPIWithValidSecretKey() {

		RestAssured.baseURI="https://api.stripe.com";
		RestAssured.basePath="/v1";

		Response response=given().auth().basic("sk_test_51GwOcBEfDaDbVM7GZQW0dpgCvhTKdUSTtdL8HUysqxbgeNMaXkJPtcJAbZp6c3IB7WZszCP3u5iVXY0DggHLMvpK00tsaj0VaA", "")
				.formParam("email","rahulg1234@gmail.com")
				.formParam("name","Rahul Kumar Gupta")
				.formParam("address[line1]","#34 main road Bangalore")
				.formParam("description","This is post request - customer Creation")
				.post("/customers");

		response.prettyPrint();
		System.out.println("Status Code : "+response.statusCode());
	}

}
