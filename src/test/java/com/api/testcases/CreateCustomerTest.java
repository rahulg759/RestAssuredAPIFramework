package com.api.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.base.BaseTest;
import com.api.frameworkapis.CreateCustomerAPI;
import com.api.listeners.ExtentListeners;
import com.api.utilities.DataUtil;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.Hashtable;

public class CreateCustomerTest extends BaseTest{

	@Test(dataProviderClass=DataUtil.class,dataProvider="readdata")
	public void validateCreateCustomerAPIWithValidSecretKey(Hashtable<String,String> data) {

		Response response=CreateCustomerAPI.sendPostRequesttoCreateCustomerAPIWithValidAuthKey(data);
		
		//Added customized log from extent report class
		//ExtentListeners.testReport.get().info(data.toString());
		
		response.prettyPrint();
		System.out.println("Status Code : "+response.statusCode());

		//Validation
		Assert.assertEquals(response.statusCode(), 200);
	}


	@Test(dataProviderClass=DataUtil.class,dataProvider="readdata")
	public void validateCreateCustomerAPIWithInValidSecretKey(Hashtable<String,String> data) {

		Response response=CreateCustomerAPI.sendPostRequesttoCreateCustomerAPIWithInValidAuthKey(data);

		response.prettyPrint();
		System.out.println("Status Code : "+response.statusCode());

		//Validation
		Assert.assertEquals(response.statusCode(), 200);
	}



}
