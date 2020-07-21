package com.api.testcases.stripeapis;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.base.BaseTest;
import com.api.listeners.ExtentListeners;
import com.api.stripeapis.RetrieveCustomerAPI;
import com.api.utilities.DataUtil;
import com.api.utilities.TestUtil;

import io.restassured.response.Response;

public class RetrieveCustomerAPITest extends BaseTest{

	@Test(dataProviderClass=DataUtil.class,dataProvider="readdata")
	public void getIDCustomerDetails(Hashtable<String,String> data) {

		Response response=RetrieveCustomerAPI.sendGetRequesttoRetrieveCustomerIDAPIWithValidAuthKey(data);

		//Added customized log from extent report class
		ExtentListeners.testReport.get().info(data.toString());

		response.prettyPrint();
		System.out.println("Status Code : "+response.statusCode());

		//Validation
		Assert.assertEquals(response.statusCode(), 200);

		System.out.println("Presence check of Object Key Value : "+TestUtil.getJsonKeyValue(response.asString(), "object"));
		System.out.println("Presence check of name Key Value : "+TestUtil.getJsonKeyValue(response.asString(), "name"));

		TestUtil.jsonHashKey(response.asString(), "id");

		String actual_id=TestUtil.getJsonKeyValue(response.asString(), "id");
		System.out.println("Actual id : "+actual_id);		

		System.out.println("Object Key Value : "+TestUtil.getJsonKeyValue(response.asString(), "object"));
		System.out.println("Name Key Value : "+TestUtil.getJsonKeyValue(response.asString(), "name"));
	}
}
