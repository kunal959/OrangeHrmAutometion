package com.OrangeHRM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ForgotPasswordTastCase extends BaseClass
{	public static void validation()
	{
	try {
		if(driver.findElement(By.xpath("//div[@class='message warning fadable']")).isDisplayed()) 
		{
			String errormessage = driver.findElement(By.xpath("//div[@class='message warning fadable']")).getText();
			System.out.println(errormessage);
		}
	}
	catch(Exception e)
	{
		System.out.println("validation message is not display");
	}
	}
	public static void cancelValidation()
	{
		try
		{
		if(driver.findElement(By.linkText("LOGIN Panel")).isDisplayed())
		{
			System.out.println("Homepage is display");
		}
		}
		catch(Exception e)
		{
			System.out.println("Homepage is not display");
		}
	}
	public static void forgot(String username)
	{
		driver.findElement(By.id("securityAuthentication_userName")).sendKeys(username);
		driver.findElement(By.id("btnSearchValues")).click();
	}
	public static void forgotCancel(String username)
	{
		driver.findElement(By.id("securityAuthentication_userName")).sendKeys(username);
		driver.findElement(By.id("btnCancel")).click();
	}
	public static void main(String[] args) {
		launchBrowser("chrome");
		driver.navigate().to("https://opensource-demo.orangehrmlive.com/");
		driver.findElement(By.linkText("Forgot your password?")).click();
		forgot("Admin");
		validation();
		
		driver.findElement(By.id("securityAuthentication_userName")).clear();
		forgot("Tester");
		validation();
		
		driver.findElement(By.id("securityAuthentication_userName")).clear();
		forgot("");
		validation();
		
		//WebElement homePage = driver.findElement(By.linkText("LOGIN Panel"));
		forgotCancel("Admin");
		cancelValidation();
		
		driver.findElement(By.linkText("Forgot your password?")).click();
		forgotCancel("Tester");
		cancelValidation();
		
		driver.findElement(By.linkText("Forgot your password?")).click();
		forgotCancel("");
		cancelValidation();
		
		
	}

}
