package com.api.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.api.utilities.ExcelReader;

import io.restassured.RestAssured;

public class BaseTest {

	public Properties prop=new Properties();;
	private FileInputStream fis;
	public static ExcelReader excel=new ExcelReader(".\\src\\test\\resources\\excel\\testdata.xlsx");

	@BeforeSuite
	public void setup() {

		try {
			fis=new FileInputStream(".\\src\\test\\resources\\properties\\config.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RestAssured.baseURI = prop.getProperty("baseURI");
		RestAssured.basePath =prop.getProperty("basePath");
	}

	@AfterSuite
	public void teardown() {

	}
}
