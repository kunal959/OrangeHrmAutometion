package com.OrangeHRM;

import org.openqa.selenium.By;

public class LoginPage extends BaseClass{

	public void logIn(String username, String password) {
		driver.findElement(By.id("txtUsername")).sendKeys(username);

		driver.findElement(By.id("txtPassword")).sendKeys(password);

		driver.findElement(By.id("btnLogin")).click();
	}
	public void logOut()
	{
		driver.findElement(By.id("welcome")).click();
		Util.waitForElementToClick(By.linkText("Logout"));
		driver.findElement(By.linkText("Logout")).click();
	}
}
