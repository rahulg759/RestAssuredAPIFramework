package com.api.utilities;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.api.base.BaseTest;

public class DataUtil extends BaseTest {

	@DataProvider(name="readdata")
	public static Object[][] getData(Method m) {

		int row=excel.getRowCount(prop.getProperty("testdatasheet"));
		System.out.println("Total rows : "+row);

		String testName=m.getName();
		System.out.println("Method name : "+testName);

		// Find the test case start row
		int testcaseNum = 1;

		for (testcaseNum = 1; testcaseNum <= row; testcaseNum++) {

			String testcaseName = excel.getCellData(prop.getProperty("testdatasheet"), 0, testcaseNum);
			System.out.println("hi"+testcaseName);
			if (testcaseName.equalsIgnoreCase(testName)) {
				break;
			}
		}
		System.out.println("Row number : " + testcaseNum);


		// checking total rows in test case
		int testStartRowNum = testcaseNum + 2;
		int testRows = 0;
		while (!excel.getCellData(prop.getProperty("testdatasheet"), 0, testStartRowNum + testRows).equals("")) {
			testRows++;
		}
		System.out.println("Total rows of data : " + testRows);


		// checking total cols in test case
		int testCols = 0;
		int colsStartColNum = testcaseNum + 1;
		while (!excel.getCellData(prop.getProperty("testdatasheet"), testCols, colsStartColNum).equals("")) {
			testCols++;
		}
		System.out.println("Total cols of data : " + testCols);

		Object[][] object = new Object[testRows][1];
		int i=0;

		// printing data
		for (int rNum = testStartRowNum; rNum < (testStartRowNum + testRows); rNum++) {
			
			Hashtable<String,String> table=new Hashtable<String, String>(); 
			
			for (int cNum = 0; cNum < testCols; cNum++) {
				// System.out.print(readdata.getCellData(Constants.Sheet_Name, cNum,rNum)+"\t");
				//object[rNum - testStartRowNum][cNum] = readdata.getCellData(Constants.Sheet_Name, cNum, rNum);
				String testdata = excel.getCellData(prop.getProperty("testdatasheet"), cNum, rNum);
				String colName=excel.getCellData(prop.getProperty("testdatasheet"), cNum, colsStartColNum);

				table.put(colName, testdata);
			}
			object[i][0]=table;
			i++;
		}
		return object;
	}

}
