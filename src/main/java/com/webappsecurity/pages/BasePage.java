package com.webappsecurity.pages;
//import org.apache.commons.io cannot be resolved
import java.io.File;
import java.io.IOException;
//import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	static WebDriver driver;

	public BasePage(WebDriver driver){
		BasePage.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException  {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
}
