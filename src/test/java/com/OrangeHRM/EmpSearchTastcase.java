package com.OrangeHRM;

import org.openqa.selenium.By;

public class EmpSearchTastcase extends BaseClass{

	public static void main(String[] args) {
		EmpSearchTastcase obj = new EmpSearchTastcase();
		obj.launchBrowser("chrome");
		driver.navigate().to("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
		LoginTestcase.logIn("Admin","admin123");
		driver.findElement(By.id("menu_pim_viewPimModule")).click();
		
		//search employee by name
		/*driver.findElement(By.id("empsearch_employee_name_empName")).sendKeys("kunal sonawane");
		driver.findElement(By.id("searchBtn")).click();*/
		
		//search employee by ID
		driver.findElement(By.id("empsearch_id")).sendKeys("959");
		driver.findElement(By.id("searchBtn")).click();

	}

}
