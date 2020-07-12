package com.api.paypalapis;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;

import com.api.base.BaseTest;
import com.api.pojo.Orders;
import com.api.pojo.PurchaseUnit;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class OrderAPI extends BaseTest{

	static String accesstoken;
	static String client_id=prop.getProperty("paypalClientID");
	static String secret=prop.getProperty("paypalSecret");
	static String orderId;

	public static String getAccessToken() {

		System.out.println("\n\n\n\n=============##### Get Authorization Key #####==========================\n");

		accesstoken=given()
				.param("grant_type", "client_credentials")
				.auth()
				.preemptive()
				.basic(client_id, secret)
				.post("/v1/oauth2/token")
				.jsonPath()
				.get("access_token")
				.toString();

		System.out.println("AccessToken : "+accesstoken);
		return accesstoken;
	}


	public static Response createOrder(String access_token) {

		System.out.println("\n\n\n\n=============##### Get Create Order #####==========================");
		
		ArrayList<PurchaseUnit> list=new ArrayList<PurchaseUnit>();
		list.add(new PurchaseUnit("USD", "500.00"));
		Orders orders=new Orders("CAPTURE",list);

		Response response=given()
				.contentType(ContentType.JSON)
				.auth().oauth2(access_token)
				.body(orders)
				.post("/v2/checkout/orders");

		orderId=response.jsonPath().get("id").toString();
		System.out.println("Order id is : "+orderId);
		
		return response;
	}

	public static Response getOrder(String access_token) {

		System.out.println("\n\n\n\n=============##### Get Order Details #####==========================");
		
		Response response=given()
				.contentType(ContentType.JSON)
				.auth().oauth2(access_token)
				.get("/v2/checkout/orders"+"/"+orderId);

		return response;
	}
}
