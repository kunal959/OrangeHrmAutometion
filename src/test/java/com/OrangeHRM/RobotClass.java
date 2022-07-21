package com.OrangeHRM;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RobotClass extends BaseClass{

	public static void main(String[] args) throws AWTException {
		launchBrowser("chrome");
		driver.navigate().to("https://realestateautomation.agilecrm.com/login#contacts");
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("automation@yopmail.com");
		driver.findElement(By.name("password")).sendKeys("Test1234");
		driver.findElement(By.xpath("//input[@value='Sign In']")).submit();
		driver.findElement(By.id("dealsmenu")).click();
		
		Util.waitForElementToClick(By.id("4772450216247296"));
		WebElement deal=driver.findElement(By.id("4772450216247296"));
		WebElement prospect=driver.findElement(By.id("Prospect-list-container"));
		
		Robot robot=new Robot();
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

	}

}
