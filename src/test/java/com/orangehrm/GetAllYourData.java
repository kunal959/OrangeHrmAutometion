package com.orangehrm;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.OrangeHRM.BaseClass;
import com.OrangeHRM.ExcelUtil;
import com.Orangehrmpages.EmpPageList;
import com.Orangehrmpages.LoginPage;

public class GetAllYourData extends BaseClass {

	String filePath = "E:\\Java progrming\\selenium.xlsx";
	ExcelUtil excelUtil;
	LoginPage loginPage;

	@BeforeClass

	public void beforeClass() {
		launchBrowser("chrome");
		loginPage = new LoginPage();
		driver.navigate().to("https://opensource-demo.orangehrmlive.com/");
		loginPage.logIn("Admin", "admin123");
		excelUtil = new ExcelUtil();
	}
	@Test
	public void hrmEmpData() throws IOException {
		String filePath1 = "E:\\Java progrming\\selenium.xlsx";
		EmpPageList empPageList = new EmpPageList();
		Object[][] empData = empPageList.getAllEmpData();
		int rowSize = empData.length;
		int columnSize = empData[0].length;
		excelUtil.getWorkBookInstance(filePath1);
		for (int i = 1; i < rowSize; i++) {
			for (int j = 0; j < columnSize; j++) {
				excelUtil.setExcelData(filePath1, "TestData", i, j, empData[i][j]);
			}

		}
		excelUtil.closeWBInstance();
	}

}
