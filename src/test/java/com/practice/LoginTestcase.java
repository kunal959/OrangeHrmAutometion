package com.practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.OrangeHRM.BaseClass;
import com.OrangeHRM.Util;
import com.Orangehrmpages.LoginPage;

public class LoginTestcase extends BaseClass {

	

	
	
	public static void main(String[] args) throws Exception {
		launchBrowser("chrome");
		// System.setProperty("webdriver.chrome.driver","https://opensource-demo.orangehrmlive.com/index.php/auth/validateCredentials");
		// driver = new ChromeDriver();

		driver.navigate().to("https://opensource-demo.orangehrmlive.com/");
		LoginPage loginPage = new LoginPage();
		// valid username invalid password
		
		loginPage.logIn("Admin", "tester123");
		loginPage.clean();
		

		// invalid username valid password
		loginPage.logIn("Tester", "admin123");
		loginPage.clean();

		// invalid username invalid password
		loginPage.logIn("Tester", "kunal123");
		loginPage.clean();

		// blank username blank password
		loginPage.logIn("", "");
		loginPage.clean();

		// valid username blank password
		loginPage.logIn("Admin", "");
		loginPage.clean();

		// invalid username blank password
		loginPage.logIn("Tester", "");
		loginPage.clean();
		// blank username valid password
		loginPage.logIn("", "admin123");
		loginPage.clean();

		// blank username invalid password
		loginPage.logIn("", "kunal123");
		loginPage.clean();

		// valid username valid password
		loginPage.logIn("Admin", "admin123");
		loginPage.logOut();
	}

}
