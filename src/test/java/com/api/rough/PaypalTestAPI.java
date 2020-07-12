package com.api.rough;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.pojo.Orders;
import com.api.pojo.PurchaseUnit;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;

public class PaypalTestAPI {


	static String access_token;
	static String client_key="Afic0HZxhUasEWKWWgUQbXkklbklYxVpH-us6OeqxcnHHHAwyA2Pku2Liokv46MrWzoDMGTkBOJENrf9";
	static String secret="ELiwlIPGx8KN5lh35X2M18gOnteL-lg-Gwl7H2ab-_oS6aOy30hfQGN0jBI9OLIYFGVCPTqSKJDUqRIU";
	static String baseURI="https://api.sandbox.paypal.com/";
	static String orderId;


	@Test(priority=1)
	public void getAuthKey() { 	

		System.out.println("\n\n\n\n=============##### Get Authorization Key #####==========================");

		RestAssured.baseURI=baseURI;

		Response response=given().param("grant_type", "client_credentials").auth().preemptive().basic(client_key, secret).post("/v1/oauth2/token");

		//print json response
		response.prettyPrint();

		access_token=response.jsonPath().get("access_token").toString();
		System.out.println("\n\nAccess Token is : "+access_token);
	}

	@Test(priority=2, dependsOnMethods="getAuthKey")
	public void createOrder() {

		System.out.println("\n\n\n\n=============##### Get Order Creation #####==========================");

		ArrayList<PurchaseUnit> list=new ArrayList<PurchaseUnit>();
		list.add(new PurchaseUnit("USD", "500.00"));
		Orders orders=new Orders("CAPTURE",list);

		/*String jsonBody="{\r\n" + 
				"  \"intent\": \"CAPTURE\",\r\n" + 
				"  \"purchase_units\": [\r\n" + 
				"    {\r\n" + 
				"      \"amount\": {\r\n" + 
				"        \"currency_code\": \"USD\",\r\n" + 
				"        \"value\": \"100.00\"\r\n" + 
				"      }\r\n" + 
				"    }\r\n" + 
				"  ]\r\n" + 
				"}";*/

		RestAssured.baseURI="https://api.sandbox.paypal.com/";
		Response response=given()
				.contentType(ContentType.JSON)
				.auth().oauth2(access_token)
				.body(orders)
				.post("/v2/checkout/orders");

		response.prettyPrint();

		String status=response.jsonPath().get("status").toString();
		System.out.println("Status: "+status);

		orderId=response.jsonPath().get("id").toString();
		System.out.println("Order id is : "+orderId);

		Assert.assertEquals(status,"CREATED");
	}

	@Test(priority=3, dependsOnMethods={"getAuthKey","createOrder"})
	public void getOrderDetails() {

		System.out.println("\n\n\n\n=============##### Get Order Details #####==========================");
		RestAssured.baseURI="https://api.sandbox.paypal.com/";
		Response response=given()
				.contentType(ContentType.JSON)
				.auth().oauth2(access_token)
				.get("/v2/checkout/orders"+"/"+orderId);

		response.prettyPrint();

		String status=response.jsonPath().get("status").toString();
		System.out.println("Status: "+status);
		Assert.assertEquals(status,"CREATED");

		//Get status code
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("Status Code is : "+response.getStatusCode());

	}
}
