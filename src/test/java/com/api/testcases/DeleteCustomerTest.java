package com.api.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.base.BaseTest;
import com.api.frameworkapis.CreateCustomerAPI;
import com.api.frameworkapis.DeleteCustomerAPI;
import com.api.listeners.ExtentListeners;
import com.api.utilities.DataUtil;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.Hashtable;

public class DeleteCustomerTest extends BaseTest{

	@Test(dataProviderClass=DataUtil.class,dataProvider="readdata")
	public void deleteCustomer(Hashtable<String,String> data) {

		Response response=DeleteCustomerAPI.sendDeleteRequesttoDeleteAPIWithValidID(data);

		//Added customized log from extent report class
		//ExtentListeners.testReport.get().info(data.toString());

		response.prettyPrint();
		System.out.println("Status Code : "+response.statusCode());

		//Validation
		Assert.assertEquals(response.statusCode(), 200);
	}
}
