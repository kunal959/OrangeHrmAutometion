package com.orangehrm;

import java.io.IOException;

import org.testng.annotations.*;

import com.OrangeHRM.BaseClass;
import com.OrangeHRM.PropertyHandling;
import com.Orangehrmpages.LoginPage;

public class LoginTestCase extends BaseClass{
	PropertyHandling prop;
	LoginPage loginpage;
	String username;
	String password;
	@BeforeClass
	@Parameters({"browser"})
	public void beforeClass(String browser) {
		String configFilePath=System.getProperty("user.dir")+"/config.properties";
		 try {
			prop=new PropertyHandling(configFilePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		launchBrowser(browser);
		 username = prop.getProperty("orangeHrmUsername");
		 password = prop.getProperty("orangeHrmPassword");
		  driver.navigate().to(prop.getProperty("orangeHrmUrl"));
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
  @Test(priority=1)
  public void logInWithValid() {
	  
	  loginpage=new LoginPage();
	  loginpage.logIn(username, password);
	  loginpage.logOut();
	 
  }
  @Test(priority=2)
  public void logInWithInvalid(){
	  
	  // valid username invalid password
	  loginpage.logIn("Admin", "kunal123");
	  loginpage.clean();
	  
	  // invalid username valid password
	  loginpage.logIn("Tester", "admin123");
	  loginpage.clean();
	  
	  // invalid username invalid password
	  loginpage.logIn("Tester", "kunal123");
	  loginpage.clean();
	  
	  // blank username blank password
	  loginpage.logIn("", "");
	  loginpage.clean();
	  
	  // valid username blank password
	  loginpage.logIn("Admin", "");
	  loginpage.clean();
	  
	  // invalid username blank password
	  loginpage.logIn("Tester", "");
	  loginpage.clean();
	  
	  // blank username valid password
	  loginpage.logIn("", "admin123");
	  loginpage.clean();
	  
	  // blank username invalid password
	  loginpage.logIn("", "kunal123");
	  
	  
  }
}
