package com.Orangehrmpages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.OrangeHRM.BaseClass;
import com.OrangeHRM.Util;

public class EmpPageList extends BaseClass {
	public EmpPageList()
	{PageFactory.initElements(driver, this);
	}
	Map<String, Object> empDetails;
	@FindBy(id="menu_pim_viewPimModule")
	public WebElement pimTab;
	
	@FindBy(id="menu_pim_addEmployee")
	public WebElement addEmpTab;
	
	@FindBy(id="firstName")
	public WebElement firstnameTextBox;
	
	@FindBy(id="lastName")
	public WebElement lastnameTextBox;
	
	@FindBy(id="employeeId")
	public WebElement empId;
	
	@FindBy(id="chkLogin")
	public WebElement checkBox;
	
	@FindBy(id="user_name")
	public WebElement username;
	
	@FindBy(id="user_password")
	public WebElement password;
	
	@FindBy(id="re_password")
	public WebElement repassword;
	
	@FindBy(id="btnSave")
	public WebElement saveButton;
	
	@FindBy(id="empsearch_id")
	public WebElement empSearchId;
	
	@FindBy(id="searchBtn")
	public WebElement searchButton;
	
	@FindBy(id="ohrmList_chkSelectAll")
	public WebElement allSelectcheckBox;
	
	@FindBy(id="btnDelete")
	public WebElement deleteButton;

	public Map<String, Object> addEmp(String firstname, String lastname, boolean isUser, String username,
			String password) throws Exception {
		empDetails = new HashMap<>();

		pimTab.click();
		Util.waitForElementToClick(By.id("menu_pim_addEmployee"));
		addEmpTab.click();
		firstnameTextBox.sendKeys(firstname);
		lastnameTextBox.sendKeys(lastname);
		String empId = this.empId.getAttribute("value");
		empDetails.put("empId", empId);
		empDetails.put("firstName", firstname);
		empDetails.put("lastName", lastname);
		empDetails.put("userName", username);
		empDetails.put("passWord", password);
		empDetails.put("isUser", isUser);
		if (isUser) {
			checkBox.click();
			this.username.sendKeys(username);
			this.password.sendKeys(password);
			repassword.sendKeys(password);
		}
		saveButton.click();
		empVerification(empDetails);
		return empDetails;
	}

	public void empVerification(Map<String, Object> empDetails) throws Exception {
		if (empDetails.get("firstName").toString().isEmpty() || empDetails.get("lastName").toString().isEmpty()) {
			String errorMessage = driver.findElement(By.xpath("//span[@generated='true']")).getText();
			Util.validation(errorMessage, "Required");
		} else if (empDetails.get("userName").toString().isEmpty() && (boolean)empDetails.get("isUser")) {
			String errorMessage = driver.findElement(By.xpath("//span[@generated='true']")).getText();
			Util.validation(errorMessage, "Should have at least 5 characters");
			//Boolean.parseBoolean(empDetails.get("isUser").toString())
			//(boolean)empDetails.get("isUser")
		} else if (empDetails.get("passWord").toString().isEmpty() && (boolean)empDetails.get("isUser")) {
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
		pimTab.click();
		empSearchId.clear();
		empSearchId.sendKeys(empDetails.get("empId").toString());
		searchButton.click();
		WebElement searchResult1 = driver.findElement(By.xpath("//tr[@class='odd']/td[2]/a"));
		Util.validation(searchResult1.getText(), empDetails.get("empId").toString());
	}

	public void editEmp(Map<String, Object> empId, String status, String nationality) throws Exception {
		searchEmpById(empDetails);
		driver.findElement(By.xpath("//a[contains(@href,'/index.php/pim/viewEmployee/empNumber/')]")).click();
		saveButton.click();

		WebElement maritalDropdown = driver.findElement(By.id("personal_cmbMarital"));
		Select select = new Select(maritalDropdown);
		select.selectByVisibleText(status);

		WebElement nationalityDropdown = driver.findElement(By.id("personal_cmbNation"));
		Select select1 = new Select(nationalityDropdown);
		select1.selectByVisibleText(nationality);

		saveButton.click();
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
		allSelectcheckBox.click();
		deleteButton.click();
		driver.findElement(By.id("dialogDeleteBtn")).click();
		if (driver.findElement(By.xpath("//div[@class='message success fadable']")).isDisplayed()) {
			String confmsg = driver.findElement(By.xpath("//div[@class='message success fadable']")).getText();
			System.out.println("User " + confmsg);
		} else {
			System.out.println("user not deleted");
		}
	}
	public Object[][] getAllEmpData()
	{
		pimTab.click();
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,1000);");
		List<WebElement> totalRows=driver.findElements(By.xpath("//table[@id='resultTable']/descendant::tr"));
		int rowSize=totalRows.size();
		List<WebElement> totalColumn=driver.findElements(By.xpath("//table[@id='resultTable']/descendant::tr/th"));
		int columnSize= totalColumn.size();
		Object [][] empData= new Object[rowSize][columnSize];
		for(int i =1;i<rowSize;i++)
		{
			for(int j=2;j<columnSize;j++)
			{
				String value=driver.findElement(By.xpath("//table[@id='resultTable']/descendant::tbody/tr["+i+"]/td["+j+"]")).getText();
				empData[i][j]=value;
			}
		}
		return empData;
	}
	
	
	
	
	
	
	
	
	
	
	

}
