package com.api.testcases.stripeapis;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.base.BaseTest;
import com.api.listeners.ExtentListeners;
import com.api.stripeapis.CreateCustomerAPI;
import com.api.utilities.DataUtil;
import com.api.utilities.TestUtil;

import io.restassured.response.Response;

import java.util.Hashtable;

public class CreateCustomerAPITest extends BaseTest{


	// We have to given same name in testdata excel sheet --->>>validateCreateCustomerAPIWithValidSecretKey

	@Test(dataProviderClass=DataUtil.class,dataProvider="readdata")
	public void validateCreateCustomerAPIWithValidSecretKey(Hashtable<String,String> data) {

		Response response=CreateCustomerAPI.sendPostRequesttoCreateCustomerAPIWithValidAuthKey(data);

		//Added customized log from extent report class
		//ExtentListeners.testReport.get().info(data.toString());

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


	/*@Test(dataProviderClass=DataUtil.class,dataProvider="readdata")
	public void validateCreateCustomerAPIWithInValidSecretKey(Hashtable<String,String> data) {

		Response response=CreateCustomerAPI.sendPostRequesttoCreateCustomerAPIWithInValidAuthKey(data);

		response.prettyPrint();
		System.out.println("Status Code : "+response.statusCode());

		//Validation
		Assert.assertEquals(response.statusCode(), 401);
	}*/

}
