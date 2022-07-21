package com.OrangeHRM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class EmpListPage extends BaseClass{
	private static String empId;
	
	public static void addEmp(String firstname, String lastname, boolean isUser, String username, String password)
	{ 
		Util.waitForElementToClick(By.id("menu_pim_addEmployee"));
		driver.findElement(By.id("menu_pim_addEmployee")).click();
		driver.findElement(By.id("firstName")).sendKeys(firstname);
		driver.findElement(By.id("lastName")).sendKeys(lastname);
		empId=driver.findElement(By.id("employeeId")).getAttribute("value");
		if(isUser) {
			driver.findElement(By.id("chkLogin")).click();
			driver.findElement(By.id("user_name")).sendKeys(username);
			driver.findElement(By.id("user_password")).sendKeys(password);
			driver.findElement(By.id("re_password")).sendKeys(password);
		}
		driver.findElement(By.id("btnSave")).click();
	}
	public static void validation()
	{try {
		if(driver.findElement(By.id("pdMainContainer")).isDisplayed()) {
			System.out.println("Personal Details page is display ");
		}
	}
		catch(Exception e)
		{System.out.println("validation message is display");
		}
	}
	public static void clean()
	{	driver.findElement(By.id("firstName")).clear();
		driver.findElement(By.id("lastName")).clear();
		driver.findElement(By.id("user_name")).clear();
		driver.findElement(By.id("user_password")).clear();
		driver.findElement(By.id("re_password")).clear();	
	}
	public static void searchEmpById(String empId)
	{	driver.findElement(By.id("empsearch_id")).clear();
		driver.findElement(By.id("empsearch_id")).sendKeys(empId);
		driver.findElement(By.id("searchBtn")).click();
	}
	public static void editEmp(String empId, String status, String nationality)
	{searchEmpById(empId);
	driver.findElement(By.xpath("//a[contains(@href,'/index.php/pim/viewEmployee/empNumber/')]")).click();
	driver.findElement(By.id("btnSave")).click();
	
	WebElement maritalDropdown = driver.findElement(By.id("personal_cmbMarital"));
	Select select = new Select(maritalDropdown);
	select.selectByVisibleText(status);
	
	WebElement nationalityDropdown=driver.findElement(By.id("personal_cmbNation"));
	Select select1 = new Select(nationalityDropdown);
	select1.selectByVisibleText(nationality);
	
	driver.findElement(By.id("btnSave")).click();
	}
	public static void main(String[] args) {
		launchBrowser("chrome");
		System.out.println("browser successfully launch");
		
		driver.navigate().to("https://opensource-demo.orangehrmlive.com/index.php/pim/viewEmployeeList");
		
		LoginTestcase.logIn("Admin","admin123");
		//first name is empty
		addEmp("","sonawane",true,"kuna@123","kunal123");
		validation();
		clean();
		
		//last name is empty
		addEmp("kunal","",true,"kunal@456","kunal123");
		validation();
		clean();
		
		//username is empty
		addEmp("kuanl","sonawane",true,"kunal@456","kunal123");
		validation();
		clean();
		
		//password is empty
		addEmp("kunal","sonawane",true,"kunal@456","");
		validation();
		clean();
		
		//confermed password is empty
		addEmp("kunal","sonawane",true,"kunal@456","kunal123");
		validation();
		clean();
		
		//Add Employee with valid credentials
		addEmp("ruchita","chinchole",true,"sonawane@123","kunal123");
		Util.waitForElementToClick(By.id("pdMainContainer"));
		validation();
		
		
		driver.findElement(By.id("menu_pim_viewEmployeeList")).click();
		//search Employee 
		searchEmpById(empId);
		
		//Edit Employee 
		editEmp(empId,"Single","Indian");
		try {
		if(driver.findElement(By.xpath("//div[@class='message success fadable']")).isDisplayed())
		{	String errorMessage=driver.findElement(By.xpath("//div[@class='message success fadable']")).getText();
			System.out.println("Employee Edit and "+errorMessage);
		}
		}
		catch(Exception e) 
		{
			System.out.println("Employee is not edited");
		}
		
		//Delete Employee
		driver.findElement(By.id("menu_pim_viewPimModule")).click();
		WebElement deletebutton = driver.findElement(By.id("btnDelete"));
		if (deletebutton.isEnabled())
		{System.out.println("Delete Button is enabled");
		}
		else
		{System.out.println("Delete Button is disabled");
		}
		WebElement checkbox =driver.findElement(By.id("ohrmList_chkSelectAll"));
		//checkbox.click();
		if(checkbox.isSelected())
		{System.out.println("check box is selected");
		}	
		else
		{System.out.println("check box is not selected");
		}
		searchEmpById(empId);
		driver.findElement(By.id("ohrmList_chkSelectAll")).click();
//		if(checkbox.isSelected())
//		{System.out.println("check box is selected");
//		}	
//		else
//		{System.out.println("check box is not selected");
//		}
//		if (deletebutton.isEnabled())
//		{System.out.println("Delete Button is enabled");
//		}
//		else
//		{System.out.println("Delete Button is disabled");
//		}
		driver.findElement(By.id("btnDelete")).click();
		driver.findElement(By.id("dialogDeleteBtn")).click();
		try {
			if(driver.findElement(By.xpath("//div[@class='message success fadable']")).isDisplayed())
			{String confmsg=driver.findElement(By.xpath("//div[@class='message success fadable']")).getText();
			System.out.println("User "+confmsg);
			}
		}	
		catch(Exception e)
		{
			System.out.println("user not deleted");
		}
	}

}
