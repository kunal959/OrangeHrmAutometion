package com.orangehrm;

import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.OrangeHRM.BaseClass;
import com.OrangeHRM.LoginPage;
import com.Orangehrmpages.EmpPageList;

public class AddEmpTestCase extends BaseClass {
	EmpPageList empPageList;
	LoginPage loginPage = new LoginPage();
	Map<String,Object> empDetails;
	@BeforeClass
	public void beforeclass() {
		launchBrowser("chrome");
		  driver.navigate().to("https://opensource-demo.orangehrmlive.com/");
		  loginPage = new LoginPage();
		  loginPage.logIn("Admin", "admin123");
	}
	@AfterClass
	public void afterclass() {
		loginPage.logOut();
		driver.quit();
	}
  @Test
  public void addEmp() throws Exception {
	  empPageList=new EmpPageList();
	  
//		Map<String,Object> empDetails1=empPageList.addEmp("cyber", "success", false, null, null);
		 empDetails=empPageList.addEmp("","success", false, null, null);
		
		 empDetails=empPageList.addEmp("cyber","", false, null, null);
		
		 empDetails=empPageList.addEmp("","sonawane", true, "kunal@123","kunal123");
		
		 empDetails=empPageList.addEmp("kuanl","", true, "kunal@123","kunal123");
		
		 empDetails=empPageList.addEmp("kuanl","sonawane", true, "","kunal123");
	
		 empDetails=empPageList.addEmp("kuanl","sonawane", true, "kunal@123","");
		
		 empDetails=empPageList.addEmp("","", true, "","");	
		 
		 empDetails=empPageList.addEmp("kuanl","sonawane", true, "kunal@123","kunal123");
  }
  @Test
  public void searchEmp() throws Exception {
	  empPageList.searchEmpById(empDetails);
  }
  @Test
  public void editEmp() throws Exception {
	  empPageList.editEmp(empDetails, "Single", "Indian");
  }
  @Test
  public void deleteEmp() throws Exception {
	  empPageList.deleteEmp(empDetails);
  }
  
  
}
