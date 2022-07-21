package com.Orangehrmpages;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.OrangeHRM.BaseClass;
import com.OrangeHRM.Util;

public class EmpPageList extends BaseClass{
	public String addEmp(String firstname, String lastname, boolean isUser, String username, String password) throws Exception
	{ 	//Map<String,Object> empDetails = new HashMap<>();
		
		driver.findElement(By.id("menu_pim_viewPimModule")).click();
		Util.waitForElementToClick(By.id("menu_pim_addEmployee"));
		driver.findElement(By.id("menu_pim_addEmployee")).click();
		driver.findElement(By.id("firstName")).sendKeys(firstname);
		driver.findElement(By.id("lastName")).sendKeys(lastname);
		String empId=driver.findElement(By.id("employeeId")).getAttribute("value");
//		empDetails.put(empId, empId);
//		empDetails.put("firstName",firstname);
//		empDetails.put("lastName", lastname);
		if(isUser) {
			driver.findElement(By.id("chkLogin")).click();
			driver.findElement(By.id("user_name")).sendKeys(username);
			driver.findElement(By.id("user_password")).sendKeys(password);
			driver.findElement(By.id("re_password")).sendKeys(password);
		}
		driver.findElement(By.id("btnSave")).click();
		WebElement searchResult=driver.findElement(By.xpath("//ol[@class='fieldsInLine']/descendant::input[1]"));
		Util.validation(searchResult.getAttribute("value"),firstname);
//		public static void validation()
		try {if(driver.findElement(By.id("pdMainContainer")).isDisplayed()) {
				System.out.println("Personal Details page is display ");}
		}
			catch(Exception e)
			{System.out.println("validation message is display");
			}
		return empId;
//		return empDetails;
		
	}
	public void searchEmpById(String empId) throws Exception
	{	driver.findElement(By.id("menu_pim_viewPimModule")).click();
		driver.findElement(By.id("empsearch_id")).clear();
		driver.findElement(By.id("empsearch_id")).sendKeys(empId);
		driver.findElement(By.id("searchBtn")).click();
		WebElement searchResult1 = driver.findElement(By.xpath("//tr[@class='odd']/td[2]/a"));
		Util.validation(searchResult1.getText(), empId);
	}
	public void editEmp(String empId, String status, String nationality) throws Exception
	{	searchEmpById(empId);
	driver.findElement(By.xpath("//a[contains(@href,'/index.php/pim/viewEmployee/empNumber/')]")).click();
	driver.findElement(By.id("btnSave")).click();
	
	WebElement maritalDropdown = driver.findElement(By.id("personal_cmbMarital"));
	Select select = new Select(maritalDropdown);
	select.selectByVisibleText(status);
	
	WebElement nationalityDropdown=driver.findElement(By.id("personal_cmbNation"));
	Select select1 = new Select(nationalityDropdown);
	select1.selectByVisibleText(nationality);
	
	driver.findElement(By.id("btnSave")).click();
	try {if(driver.findElement(By.xpath("//div[@class='message success fadable']")).isDisplayed())
		{	String errorMessage=driver.findElement(By.xpath("//div[@class='message success fadable']")).getText();
			System.out.println("Employee Edit and "+errorMessage);
		}
		}
		catch(Exception e) 
		{System.out.println("Employee is not edited");
		}
	}
	public void deleteEmp(String empId) throws Exception
	{	searchEmpById(empId);
		driver.findElement(By.id("ohrmList_chkSelectAll")).click();
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
