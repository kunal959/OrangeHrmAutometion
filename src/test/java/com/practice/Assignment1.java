package com.practice;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.OrangeHRM.BaseClass;

public class Assignment1 extends BaseClass{

	public static void main(String[] args) throws AWTException {
		
		launchBrowser("chrome");
		
		driver.get("https://www.amazon.in/");
		
		WebElement bestSeller=driver.findElement(By.xpath("//a[@data-csa-c-content-id=\"nav_cs_bestsellers\"]"));
		
		Actions act = new Actions(driver);
		act.moveToElement(bestSeller).contextClick().build().perform();
		
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		String parentTabId=driver.getWindowHandle();
		System.out.println(parentTabId);
		
		Set<String> windowId=driver.getWindowHandles();
		System.out.println(windowId);
		
		for(String id:windowId)
		{
			if(!id.equals(parentTabId))
			{
				System.out.println(id);
				driver.switchTo().window(id);
				System.out.println(driver.getTitle());
				driver.close();
				driver.switchTo().window(parentTabId);
				
			}
		}
		System.out.println(driver.getTitle());
		driver.quit();

	}

}
