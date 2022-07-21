package com.OrangeHRM;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SearchAppliances extends BaseClass{

	public static void main(String[] args) {
		String listOfElement = null;
		String priceOfElement = null;
		launchBrowser("chrome");
		driver.navigate().to("https://www.amazon.in/");
		
		WebElement searchdropdown=driver.findElement(By.id("searchDropdownBox"));
		Select select= new Select(searchdropdown);
		select.selectByVisibleText("Appliances");
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("washing machine");
		driver.findElement(By.id("nav-search-submit-button")).click();
		Util.waitForElementToClick(By.xpath("//div[@data-component-type='s-search-result']/descendant::h2/a/span"));
		//list of all washing machine name 
		List<WebElement> result=driver.findElements(By.xpath("//div[@data-component-type='s-search-result']/descendant::h2/a/span"));
		/*for(WebElement element:result)
		{
			listOfElement = element .getText();
			//System.out.println(listOfElement);
		}*/
		Util.waitForElementToClick(By.xpath("//div[@data-component-type='s-search-result']/descendant::a/span[@class='a-price']"));
		List<WebElement> price=driver.findElements(By.xpath("//div[@data-component-type='s-search-result']/descendant::a/span[@class='a-price']"));
		/*for(WebElement element:price)
		{
			priceOfElement=element.getText();
			//System.out.println(priceOfElement);
		}*/
		
		//System.out.println(listOfElement+""+priceOfElement);
		
		
		for(int i=0;i<=result.size();i++)
		{
			WebElement listOfElement1=result.get(i);
			WebElement priceOfElement1=price.get(i);
			
			System.out.println(listOfElement1.getText()+ ":" +priceOfElement1.getText());
		}	
	}

}
