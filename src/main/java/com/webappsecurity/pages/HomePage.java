package com.webappsecurity.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
@FindBy(id="signin_button") WebElement SignIn_btn;

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public void clickSigninButton() {
		SignIn_btn.click();
	}
}
