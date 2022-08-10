package com.practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;

import com.OrangeHRM.BaseClass;
import com.Orangehrmpages.LoginPage;

public class DeleteEmployeeTestcase extends BaseClass
{

	public static void main(String[] args) {
		LoginPage loginPage = new LoginPage();
		DeleteEmployeeTestcase obj = new DeleteEmployeeTestcase();
		launchBrowser("chrome");
		//obj.launchBrowser("mozilla");
		//obj.launchBrowser("edge");
		
		driver.navigate().to("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
		loginPage.logIn("Admin","admin123");
		
		driver.findElement(By.id("menu_pim_viewPimModule")).click();
		
		WebElement deletebutton = driver.findElement(By.id("btnDelete"));
		if (deletebutton.isEnabled())
		{
			System.out.println("Delete Button is enabled");
		}
		else
		{
			System.out.println("Delete Button is disabled");
		}
		WebElement checkbox =driver.findElement(By.id("ohrmList_chkSelectAll"));
		//checkbox.click();
		if(checkbox.isSelected())
		{
			System.out.println("check box is selected");
		}	
		else
		{
			System.out.println("check box is not selected");
		}
		//deletebutton.click();
		driver.findElement(By.id("empsearch_employee_name_empName")).sendKeys("kunal sonawane");
		WebElement serachButton=driver.findElement(By.id("searchBtn"));
		serachButton.click();
		//Util.waitForElementToClick(By.id("ohrmList_chkSelectAll"));
	
		driver.findElement(By.id("ohrmList_chkSelectAll")).click();
		//checkbox.click();
		//Util.waitForElementToClick(By.id("btnDelete"));
		driver.findElement(By.id("btnDelete")).click();
		//deletebutton.click();
		driver.findElement(By.id("dialogDeleteBtn")).click();

	}

}
