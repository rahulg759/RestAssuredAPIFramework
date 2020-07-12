package com.api.testcases.paypalapis;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.paypalapis.OrderAPI;

import io.restassured.response.Response;

public class GetOrderTest {

	
	@Test
	public void getOrder() {
		
		String access_token=OrderAPI.getAccessToken();
		Response response=OrderAPI.getOrder(access_token);		

		response.prettyPrint();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
