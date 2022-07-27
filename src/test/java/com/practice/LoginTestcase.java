package com.practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.OrangeHRM.BaseClass;
import com.OrangeHRM.Util;

public class LoginTestcase extends BaseClass {

	public static void verifyLogin(String errorMsg) throws Exception {
		switch (errorMsg) {
		case "Invalid Credentials":
			System.out.println();
			break;
		case "":
		default:
			throw new Exception("Failed to validate error message for invalid cred: "+ errorMsg);
		}
	}

	// static WebDriver driver;
	public static void logIn(String username, String password) {
		driver.findElement(By.id("txtUsername")).sendKeys(username);

		driver.findElement(By.id("txtPassword")).sendKeys(password);

		driver.findElement(By.id("btnLogin")).click();
		
	}

	public static void clean() {
		driver.findElement(By.id("txtUsername")).clear();
		driver.findElement(By.id("txtPassword")).clear();

	}

	public static void validationMsg() {
		try {
			if (driver.findElement(By.id("spanMessage")).isDisplayed()) {
				String errorMessage = driver.findElement(By.id("spanMessage")).getText();
				//verifyLogin(errorMessage);
				System.out.println(errorMessage);
			}
		} catch (Exception e) {
			System.out.println("the error message in not available on login screen");
		}
	}

	public static void main(String[] args) throws Exception {
		launchBrowser("chrome");
		// System.setProperty("webdriver.chrome.driver","https://opensource-demo.orangehrmlive.com/index.php/auth/validateCredentials");
		// driver = new ChromeDriver();

		driver.navigate().to("https://opensource-demo.orangehrmlive.com/");

		// valid username invalid password
		logIn("Admin", "kunal123");
		validationMsg();
		clean();

		// invalid username valid password
		logIn("Tester", "admin123");
		validationMsg();
		clean();

		// invalid username invalid password
		logIn("Tester", "kunal123");
		validationMsg();
		clean();

		// blank username blank password
		logIn("", "");
		validationMsg();
		clean();

		// valid username blank password
		logIn("Admin", "");
		validationMsg();
		clean();

		// invalid username blank password
		logIn("Tester", "");
		validationMsg();
		clean();

		// blank username valid password
		logIn("", "admin123");
		validationMsg();
		clean();

		// blank username invalid password
		logIn("", "kunal123");
		validationMsg();
		clean();

		// valid username valid password
		logIn("Admin", "admin123");
		validationMsg();
		driver.findElement(By.id("txtUsername")).clear();

		driver.findElement(By.id("welcome")).click();
		Util.waitForElementToClick(By.linkText("Logout"));
		driver.findElement(By.linkText("Logout")).click();

	}

}
