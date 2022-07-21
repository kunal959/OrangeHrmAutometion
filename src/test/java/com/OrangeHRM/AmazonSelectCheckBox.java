package com.OrangeHRM;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AmazonSelectCheckBox extends BaseClass{

	public static void main(String[] args) {
		launchBrowser("chrome");
		driver.navigate().to("https://www.amazon.in/");
		
		WebElement dropDown = driver.findElement(By.id("searchDropdownBox"));
	
		Select select = new Select(dropDown);
		//select the dropdown value by index
		select.selectByIndex(4);
		
		//select the dropdown value by visible text
		//select.selectByVisibleText("Appliances");
		
		//select the dropdown value by value
		//select.selectByValue("search-alias=amazon-pharmacy");
		
		//this method will de-select all the selected list
		//select.deselectAll();
		
		//select.deselectByIndex(4);
		
		//select.deselectByValue(null);
		
		//select.deselectByVisibleText(null);
		
		List<WebElement> allSelectedOpstion = select.getAllSelectedOptions();
		for (WebElement element:allSelectedOpstion)
		{
			System.out.println(element.getText());
		}
		List<WebElement> allDropDownValues=select.getOptions();
		for(WebElement element:allDropDownValues)
		{
			System.out.println(element.getText());
		}
	}

	
		
	

}
