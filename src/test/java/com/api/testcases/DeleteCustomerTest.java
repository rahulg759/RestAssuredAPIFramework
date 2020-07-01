package com.api.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.base.BaseTest;
import com.api.frameworkapis.DeleteCustomerAPI;
import com.api.listeners.ExtentListeners;
import com.api.utilities.DataUtil;
import com.api.utilities.TestUtil;

import io.restassured.response.Response;

import java.util.Hashtable;

public class DeleteCustomerTest extends BaseTest{

	@Test(dataProviderClass=DataUtil.class,dataProvider="readdata")
	public void deleteCustomer(Hashtable<String,String> data) {

		Response response=DeleteCustomerAPI.sendDeleteRequesttoDeleteAPIWithValidID(data);

		//Added customized log from extent report class
		ExtentListeners.testReport.get().info(data.toString());

		response.prettyPrint();
		System.out.println("Status Code : "+response.statusCode());

		//Validation
		Assert.assertEquals(response.statusCode(), 200);

		//Data validation from excel id(actual) and expected id and display the id on console
		/*	String actual_id=response.jsonPath().get("id").toString();

		System.out.println("Getting ID from json path : "+actual_id);

		Assert.assertEquals(actual_id, data.get("id"),"ID is not matched");*/

		/*JSONObject jsonobject=new JSONObject(response.asString());
		System.out.println("id : "+jsonobject.has("id"));
		Assert.assertTrue(jsonobject.has("id"),"id key is not present in json response");*/

		System.out.println("Presence check of Object Key Value : "+TestUtil.getJsonKeyValue(response.asString(), "object"));
		System.out.println("Presence check of deleted Key Value : "+TestUtil.getJsonKeyValue(response.asString(), "deleted"));
		
		TestUtil.jsonHashKey(response.asString(), "id");

		String actual_id=TestUtil.getJsonKeyValue(response.asString(), "id");
		System.out.println("Actual id : "+actual_id);
		Assert.assertEquals(actual_id,data.get("id"),"ID is not matched");
		
		System.out.println("Object Key Value : "+TestUtil.getJsonKeyValue(response.asString(), "object"));
		System.out.println("deleted Key Value : "+TestUtil.getJsonKeyValue(response.asString(), "deleted"));
	}
}
