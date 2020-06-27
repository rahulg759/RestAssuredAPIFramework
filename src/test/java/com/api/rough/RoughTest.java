package com.api.rough;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class RoughTest {

	public static void main(String[] args) throws IOException {
		
		Properties prop=new Properties();
		
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\properties\\config.properties");
		
		prop.load(fis);
		
		System.out.println("Get the value of key: "+prop.getProperty("baseURI"));
	}
}
