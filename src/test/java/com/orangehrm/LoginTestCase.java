package com.orangehrm;

import org.testng.annotations.Test;

import com.OrangeHRM.BaseClass;
import com.OrangeHRM.LoginPage;
import com.practice.LoginTestcase;

public class LoginTestCase extends BaseClass{
	LoginPage loginpage;
  @Test
  public void aLogInWithValid() {
	  launchBrowser("chrome");
	  driver.navigate().to("https://opensource-demo.orangehrmlive.com/");
	  loginpage=new LoginPage();
	  loginpage.logIn("Admin", "admin123");
	  loginpage.logOut();
	 
  }
  @Test
  public void bLogInWithInvalid(){
	  
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
