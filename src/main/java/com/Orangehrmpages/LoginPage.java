package com.Orangehrmpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.OrangeHRM.BaseClass;
import com.OrangeHRM.Util;

public class LoginPage extends BaseClass{
	public LoginPage()
	{
		PageFactory.initElements(driver, this);
	}
//	Page Factory Model
	@FindBy(id="txtUsername")
	public WebElement username; 
	
	@FindBy(id="txtPassword")
	public WebElement password;
	
	@FindBy(id="btnLogin")
	public WebElement loginButton;
	
//	Page Object model
//	By username = By.id("txtUsername");
//	By password = By.id("txtPassword");
//	By loginButton = By.id("btnLogin");

	public void logIn(String username, String password) {
//		Project Object Model
//		driver.findElement(this.username).sendKeys(username);
//		driver.findElement(this.password).sendKeys(password);
//		driver.findElement(this.loginButton).click();
		
//		Page Factory Model
		this.username.sendKeys(username);
		this.password.sendKeys(password);
		this.loginButton.click();
		
		
		try {
			if (driver.findElement(By.id("spanMessage")).isDisplayed()) {
				String errorMessage = driver.findElement(By.id("spanMessage")).getText();
				System.out.println(errorMessage);
			}
		}
			catch (Exception e) {
			System.out.println("User is successfully login");
		}
	}
	public static void clean() {
		driver.findElement(By.id("txtUsername")).clear();
		driver.findElement(By.id("txtPassword")).clear();

	}
	public void logOut()
	{
		driver.findElement(By.id("welcome")).click();
		Util.waitForElementToClick(By.linkText("Logout"));
		driver.findElement(By.linkText("Logout")).click();
	}
	
}
