package com.orangehrm;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.OrangeHRM.BaseClass;
import com.OrangeHRM.ExcelUtil;
import com.Orangehrmpages.EmpPageList;
import com.Orangehrmpages.LoginPage;

public class ExcelHandling extends BaseClass {
	String filePath = "E:\\Sample File.xlsx";
//	String filePath1="E:\\Java progrming\\selenium.xlsx";
	ExcelUtil excelUtil;

	@DataProvider
	public Object[][] getTestData() throws IOException {

		excelUtil = new ExcelUtil();
		return excelUtil.getExcelData(filePath, "Sheet1");
	}

	@Test(dataProvider = "getTestData", priority = 1,enabled=true)
	public void test1(String username, String password) {
		System.out.println(username + " : " + password);
	}

	@Test(priority = 2)
	public void test2() throws IOException {
		excelUtil = new ExcelUtil();
		excelUtil.getWorkBookInstance(filePath);
		excelUtil.setExcelData(filePath, "Chintoo", 3, 0, "selenium");
	}

	

	
}
