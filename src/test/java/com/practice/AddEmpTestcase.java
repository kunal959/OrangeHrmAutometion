package com.practice;

import java.util.Map;

import com.OrangeHRM.BaseClass;
import com.Orangehrmpages.EmpPageList;
import com.Orangehrmpages.LoginPage;

public class AddEmpTestcase extends BaseClass{

	public static void main(String[] args) throws Exception {
		Map<String,Object> empDetails;
		launchBrowser("chrome");
		driver.navigate().to("https://opensource-demo.orangehrmlive.com");
	
		LoginPage loginPage = new LoginPage();
		EmpPageList empPageList = new EmpPageList();
		loginPage.logIn("Admin", "admin123");
		 empDetails=empPageList.addEmp("cyber", "success", false, "", "");

		empDetails=empPageList.addEmp("","success", false,"","");

		empDetails=empPageList.addEmp("cyber","", false, "", "");

		empDetails=empPageList.addEmp("","sonawane", true, "kunal@123","kunal123");

		empDetails=empPageList.addEmp("kunal","", true, "kunal@123","kunal123");
		
		empDetails=empPageList.addEmp("kunal","sonawane", true, "","kunal123");
	
		empDetails=empPageList.addEmp("kunal","sonawane", true, "kunal@123","");
		
		empDetails=empPageList.addEmp("","", true, "","");
		
		empDetails=empPageList.addEmp("kunal","sonawane", true, "kunal@123","kunal123");

		
		empPageList.searchEmpById(empDetails);
		
		empPageList.editEmp(empDetails, "Single", "Indian");
		
		empPageList.deleteEmp(empDetails);
		
		loginPage.logOut();
		driver.quit();

	}

}
