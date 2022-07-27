package com.Orangehrmpages;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.OrangeHRM.BaseClass;
import com.OrangeHRM.Util;

public class EmpPageList extends BaseClass {
	Map<String, Object> empDetails;

	public Map<String, Object> addEmp(String firstname, String lastname, boolean isUser, String username,
			String password) throws Exception {
		empDetails = new HashMap<>();

		driver.findElement(By.id("menu_pim_viewPimModule")).click();
		Util.waitForElementToClick(By.id("menu_pim_addEmployee"));
		driver.findElement(By.id("menu_pim_addEmployee")).click();
		driver.findElement(By.id("firstName")).sendKeys(firstname);
		driver.findElement(By.id("lastName")).sendKeys(lastname);
		String empId = driver.findElement(By.id("employeeId")).getAttribute("value");
		empDetails.put("empId", empId);
		empDetails.put("firstName", firstname);
		empDetails.put("lastName", lastname);
		empDetails.put("userName", username);
		empDetails.put("passWord", password);
		empDetails.put("isUser", isUser);
		if (isUser) {
			driver.findElement(By.id("chkLogin")).click();
			driver.findElement(By.id("user_name")).sendKeys(username);
			driver.findElement(By.id("user_password")).sendKeys(password);
			driver.findElement(By.id("re_password")).sendKeys(password);
		}
		driver.findElement(By.id("btnSave")).click();
		empVerification(empDetails);
		return empDetails;
	}

	public void empVerification(Map<String, Object> empDetails) throws Exception {
		if (empDetails.get("firstName").toString().isEmpty() || empDetails.get("lastName").toString().isEmpty()) {
			String errorMessage = driver.findElement(By.xpath("//span[@generated='true']")).getText();
			Util.validation(errorMessage, "Required");
		} else if (empDetails.get("userName").toString().isEmpty() && empDetails.get("isUser").toString().isEmpty() ) {
			String errorMessage = driver.findElement(By.xpath("//span[@generated='true']")).getText();
			Util.validation(errorMessage, "Should have at least 5 characters");
		} else if (empDetails.get("passWord").toString().isEmpty() && empDetails.get("isUser").toString().isEmpty()) {
			String errorMessage = driver.findElement(By.xpath("//span[@generated='true']")).getText();
			Util.validation(errorMessage, "Should have at least 8 characters");
		} else {
			System.out.println("Employee is successfully crated");
			Util.waitForElementToClick(By.xpath("//div[@id='pdMainContainer']"));
			if (driver.findElement(By.xpath("//div[@id='pdMainContainer']")).isDisplayed()) {
				System.out.println("Personal Details page is display ");
			} else {
				System.out.println("validation message is display");
			}
		}
	}

	public void searchEmpById(Map<String, Object> empDetails) throws Exception {
		driver.findElement(By.id("menu_pim_viewPimModule")).click();
		driver.findElement(By.id("empsearch_id")).clear();
		driver.findElement(By.id("empsearch_id")).sendKeys(empDetails.get("empId").toString());
		driver.findElement(By.id("searchBtn")).click();
		WebElement searchResult1 = driver.findElement(By.xpath("//tr[@class='odd']/td[2]/a"));
		Util.validation(searchResult1.getText(), empDetails.get("empId").toString());
	}

	public void editEmp(Map<String, Object> empId, String status, String nationality) throws Exception {
//		searchEmpById(empDetails);
		driver.findElement(By.xpath("//a[contains(@href,'/index.php/pim/viewEmployee/empNumber/')]")).click();
		driver.findElement(By.id("btnSave")).click();

		WebElement maritalDropdown = driver.findElement(By.id("personal_cmbMarital"));
		Select select = new Select(maritalDropdown);
		select.selectByVisibleText(status);

		WebElement nationalityDropdown = driver.findElement(By.id("personal_cmbNation"));
		Select select1 = new Select(nationalityDropdown);
		select1.selectByVisibleText(nationality);

		driver.findElement(By.id("btnSave")).click();
		if (driver.findElement(By.xpath("//div[@class='message success fadable']")).isDisplayed()) {
			String errorMessage = driver.findElement(By.xpath("//div[@class='message success fadable']")).getText();
			System.out.println("Employee Edit and " + errorMessage);
		}

		else {
			System.out.println("Employee is not edited");
		}
	}

	public void deleteEmp(Map<String, Object> empDetails) throws Exception {
		searchEmpById(empDetails);
		driver.findElement(By.id("ohrmList_chkSelectAll")).click();
		driver.findElement(By.id("btnDelete")).click();
		driver.findElement(By.id("dialogDeleteBtn")).click();
		if (driver.findElement(By.xpath("//div[@class='message success fadable']")).isDisplayed()) {
			String confmsg = driver.findElement(By.xpath("//div[@class='message success fadable']")).getText();
			System.out.println("User " + confmsg);
		} else {
			System.out.println("user not deleted");
		}
	}

}
