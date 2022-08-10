package com.practice;

import org.openqa.selenium.By;

import com.OrangeHRM.BaseClass;
import com.Orangehrmpages.LoginPage;

public class EmpSearchTastcase extends BaseClass{

	public static void main(String[] args) {
		LoginPage loginPage = new LoginPage();
		EmpSearchTastcase obj = new EmpSearchTastcase();
		launchBrowser("chrome");
		driver.navigate().to("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
		loginPage.logIn("Admin","admin123");
		driver.findElement(By.id("menu_pim_viewPimModule")).click();
		
		//search employee by name
		/*driver.findElement(By.id("empsearch_employee_name_empName")).sendKeys("kunal sonawane");
		driver.findElement(By.id("searchBtn")).click();*/
		
		//search employee by ID
		driver.findElement(By.id("empsearch_id")).sendKeys("959");
		driver.findElement(By.id("searchBtn")).click();

	}

}
