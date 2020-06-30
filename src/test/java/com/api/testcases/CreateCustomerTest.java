package com.api.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.base.BaseTest;
import com.api.utilities.DataUtil;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.Hashtable;

public class CreateCustomerTest extends BaseTest{

	@Test(dataProviderClass=DataUtil.class,dataProvider="readdata")
	public void validateCreateCustomerAPIWithValidSecretKey(Hashtable<String,String> data) {

		Response response=given().auth().basic(prop.getProperty("validSecretKey"), "")
				.formParam("email",data.get("email"))
				.formParam("name",data.get("name"))
				.formParam("address[line1]",data.get("address"))
				.formParam("description",data.get("description"))
				.post(prop.getProperty("customerAPIEndPoint"));

		response.prettyPrint();
		System.out.println("Status Code : "+response.statusCode());

		//Validation
		Assert.assertEquals(response.statusCode(), 200);
	}


	/*@Test(dataProviderClass=DataUtil.class,dataProvider="data")
	public void invalidateCreateCustomerAPI(String email,String name,String address,String description) {

		Response response=given().auth().basic(prop.getProperty("invalidSecretKey"), "")
				.formParam("email",email)
				.formParam("name",name)
				.formParam("address[line1]",address)
				.formParam("description",description)
				.post("/customers");

		response.prettyPrint();
		System.out.println("Status Code : "+response.statusCode());

		//Validation
		Assert.assertEquals(response.statusCode(), 200);
	}*/



}
