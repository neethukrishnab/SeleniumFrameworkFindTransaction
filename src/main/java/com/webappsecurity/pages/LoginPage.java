package com.webappsecurity.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class LoginPage extends BasePage{
@FindBy(id = "user_login")WebElement Username_txtbox;
@FindBy(id = "user_password") WebElement Password_txtbox;
@FindBy(css = "input[name='submit']") WebElement Login_btn;
	
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	public boolean isPageDisplayed() {
		return driver.getTitle().equals("Zero - Log in") ;
	}

	public void enterCredentials(String user,String pwd) {
		Username_txtbox.sendKeys(user);
		Password_txtbox.sendKeys(pwd);
		Login_btn.click();
	}
}
