package com.orangehrm;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.OrangeHRM.BaseClass;
import com.OrangeHRM.PropertyHandling;
import com.Orangehrmpages.EmpPageList;
import com.Orangehrmpages.LoginPage;

public class AddEmpTestCase extends BaseClass {
	PropertyHandling prop;
	EmpPageList empPageList;
	LoginPage loginPage = new LoginPage();
	Map<String,Object> empDetails;
	@BeforeClass
	@Parameters("browser")
	public void beforeclass(String browser) throws IOException 
	{
		String configfilePath=System.getProperty("user.dir")+"/config.properties";
		prop= new PropertyHandling(configfilePath);
		launchBrowser(browser);
		  driver.navigate().to(prop.getProperty("orangeHrmUrl"));
		  loginPage = new LoginPage();
		  String username =prop.getProperty("orangeHrmUsername");
		  String password =prop.getProperty("orangeHrmPassword");
		  loginPage.logIn(username,password);
	}
	@AfterClass
	public void afterclass() {
		loginPage.logOut();
		driver.quit();
	}
  @Test(priority=1)
  public void addEmp() throws Exception {
	  empPageList=new EmpPageList();
	  
	    empDetails=empPageList.addEmp("cyber", "success", false, "", "");

		empDetails=empPageList.addEmp("","success", false,"","");

		empDetails=empPageList.addEmp("cyber","", false, "", "");

		empDetails=empPageList.addEmp("","sonawane", true, "kunal@123","kunal123");

		empDetails=empPageList.addEmp("kunal","", true, "kunal@123","kunal123");
		
		empDetails=empPageList.addEmp("kunal","sonawane", true, "","kunal123");
	
		empDetails=empPageList.addEmp("kunal","sonawane", true, "kunal@123","");
		
		empDetails=empPageList.addEmp("","", true, "","");
		
		empDetails=empPageList.addEmp("kunal","sonawane", true, "kunal@123","kunal123");
  }
  @Test(priority=2)
  public void searchEmp() throws Exception {
	  empPageList.searchEmpById(empDetails);
  }
  @Test(priority=3)
  public void editEmp() throws Exception {
	  empPageList.editEmp(empDetails, "Single", "Indian");
  }
  @Test(priority=4)
  public void deleteEmp() throws Exception {
	  empPageList.deleteEmp(empDetails);
  }
 
  
}
