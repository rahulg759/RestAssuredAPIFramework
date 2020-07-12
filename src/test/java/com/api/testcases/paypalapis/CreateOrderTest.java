package com.api.testcases.paypalapis;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.base.BaseTest;
import com.api.paypalapis.OrderAPI;

import io.restassured.response.Response;

public class CreateOrderTest extends BaseTest{

	@Test
	public void createOrder() {

		String access_token=OrderAPI.getAccessToken();
		
		Response response=OrderAPI.createOrder(access_token);
		Assert.assertEquals(response.jsonPath().get("status"),"CREATED");

		response.prettyPrint();
	}
}
