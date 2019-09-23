package com.webappsecurity.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.webappsecurity.pages.HomePage;
import com.webappsecurity.pages.LoginPage;

public class ViewAccountSummary extends TestBase {
	
	@Test
	public void viewSummary() {
	//Click sign in button in home page
	HomePage homepage = new HomePage(driver);
	homepage.clickSigninButton();
	//logger.info("signin button clicked");
	
	//Enter credentials in login page
	LoginPage loginpage = new LoginPage(driver);
	if(loginpage.isPageDisplayed())
	loginpage.enterCredentials("username", "password");
	//logger.info("Credentials entered");
	
	//Verify AccountSummary page
	
	}
	
	

}
