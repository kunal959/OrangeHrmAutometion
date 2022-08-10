package com.practice;

import org.openqa.selenium.JavascriptExecutor;

import com.OrangeHRM.BaseClass;
import com.Orangehrmpages.LoginPage;

public class PractiseJavaScriptExecuter extends BaseClass{

	public static void main(String[] args) {
		launchBrowser("chrome");
		driver.navigate().to("https://opensource-demo.orangehrmlive.com");
		LoginPage loginPage = new LoginPage();
		loginPage.logIn("Admin", "admin123");
		JavascriptExecutor js = (JavascriptExecutor)driver;
		//get domain name
		System.out.println(js.executeScript("return document.domain;"));
		
		//get current page URL
		System.out.println(js.executeScript("return document.URL;"));
		
		//current page scroll down
		js.executeScript("window.scrollBy(0,1000);");
		

	}

}
