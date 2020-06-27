package com.api.utilities;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import com.api.base.BaseTest;

public class DataUtil extends BaseTest {

	@DataProvider(name="data")
	public Object[][] getData(Method m) {

		String sheetName=m.getName();
		System.out.println("Method and sheet name which is similar : "+sheetName);
		int rowNum=excel.getRowCount(sheetName);
		int colNum=excel.getColumnCount(sheetName);

		Object[][] data=new Object[rowNum-1][colNum];

		//excel.getCellData(sheetName, colNum, rowNum);
		System.out.println("Total rows : "+rowNum+" Total coloms : "+colNum);		

		for (int rows = 2; rows <= rowNum; rows++) {
			for (int cols = 0; cols < colNum; cols++) {
				data[rows-2][cols]=excel.getCellData(sheetName, cols, rows);
			}
		}
		return data;
	}

}
