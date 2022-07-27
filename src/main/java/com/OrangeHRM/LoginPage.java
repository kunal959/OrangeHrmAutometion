package com.OrangeHRM;

import org.openqa.selenium.By;

public class LoginPage extends BaseClass{

	public void logIn(String username, String password) {
		driver.findElement(By.id("txtUsername")).sendKeys(username);

		driver.findElement(By.id("txtPassword")).sendKeys(password);

		driver.findElement(By.id("btnLogin")).click();
		try {
			if (driver.findElement(By.id("spanMessage")).isDisplayed()) {
				String errorMessage = driver.findElement(By.id("spanMessage")).getText();
				System.out.println(errorMessage);
			}
		}
			catch (Exception e) {
			System.out.println("the error message in not available on login screen");
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
