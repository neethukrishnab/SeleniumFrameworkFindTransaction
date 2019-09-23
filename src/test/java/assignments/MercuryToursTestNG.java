package assignments;


import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(utils.NGListeners.class)	


public class MercuryToursTestNG {
	WebDriver driver;
	
	@BeforeMethod
	public void initialSetUp() {
		System.setProperty("webdriver.chrome.driver", "E:\\Softwares\\Drivers\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://newtours.demoaut.com/");
	}

	@Test
	public void AllHyperLinks() throws IOException {
		//All the hyperlinks are saved with tagname a. 
		//So Collecting all of them in a list
		List<WebElement> HyperLinks=driver.findElements(By.tagName("a"));

		System.out.println("The Hyper links are -");
		//To print the hyperlinks using enhanced for loop
		for(WebElement HLink:HyperLinks) {
			//.getText method will give the text present in that web element
			System.out.println(HLink.getText());
		}

		//To print the count of the hyperlinks
		System.out.println("Total number of hyperlinks are "+HyperLinks.size());
		int ExpectedCount = 14;
		if(HyperLinks.size() == ExpectedCount) {
			System.out.println("Test passes. Hyperlinks count is as expected. There are 16 links in the webpage");
			Assert.assertTrue(true);

		}
		else {
			
		captureScreen(driver,"printUnamePword1");
			System.out.println("Test fails. Hyperlinks count is not as expected. They should be 16");
			Assert.assertTrue(false);
		}
		System.out.println("------------------------------------------------");

	}
	
	@Test
	public void printUnamePword() throws IOException {
		
		String UNFieldName = driver.findElement(By.cssSelector("[name='userName'][type='text']")).getAttribute("name");
		String pFieldName = driver.findElement(By.cssSelector("[name='password'][type='password']")).getAttribute("name");	
		System.out.println("Name in Username field is " + UNFieldName + " and Name in Password field is "+ pFieldName);
		if(UNFieldName.equals("userName ") && pFieldName.equals("password")) {
			Assert.assertTrue(true);
			System.out.println("Test passes. Name of username and password fields are as expected");
		}
		else {
			
			captureScreen(driver,"printUnamePword");
			Assert.assertTrue(false);
		}
			System.out.println("Test fails. Name of username and password fields are not as expected");

		System.out.println("------------------------------------------------");
	}
	
	@Test
	public void signInText() {
		String signInText = driver.findElement(By.cssSelector("[name='login'][value='Login']")).getAttribute("alt");
		System.out.println("Text inside signin button is "+ signInText);
		if(signInText.equals("Sign-In")) {
			Assert.assertTrue(true);

			System.out.println("Test passes. Text sign in is as expected");
		}
		else
			Assert.assertTrue(false);
			System.out.println("Test fails. Text sign in is not as expected");

		System.out.println("------------------------------------------------");
	}
	
	@AfterMethod
	public void close() {
		driver.quit();
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
}
