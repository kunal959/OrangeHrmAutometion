package com.OrangeHRM;

import java.util.Map;

import com.Orangehrmpages.EmpPageList;

public class AddEmpTestcase extends BaseClass{

	public static void main(String[] args) throws Exception {
		launchBrowser("chrome");
		driver.navigate().to("https://opensource-demo.orangehrmlive.com/");
		LoginPage loginPage = new LoginPage();
		EmpPageList empPageList = new EmpPageList();
		loginPage.logIn("Admin", "admin123");
		String empId1=empPageList.addEmp("cyber", "success", false, null, null);
		empPageList.addEmp("","success", false, null, null);
		empPageList.addEmp("cyber","", false, null, null);
		String empId2=empPageList.addEmp("kuanl","sonawane", true, "kunal@123","kunal123");
		empPageList.addEmp("","sonawane", true, "kunal@123","kunal123");
		empPageList.addEmp("kuanl","", true, "kunal@123","kunal123");
		empPageList.addEmp("kuanl","sonawane", true, "","kunal123");
		empPageList.addEmp("kuanl","sonawane", true, "kunal@123","");
		empPageList.addEmp("","", true, "","");
		
		empPageList.searchEmpById(empId1);
		
		empPageList.editEmp(empId2, "Single", "Indian");
		
		empPageList.deleteEmp(empId2);
		
		loginPage.logOut();

	}

}
