package assignments;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MercuryTours {
	WebDriver driver;
	public void initialSetUp() {
		System.setProperty("webdriver.chrome.driver", "E:\\Softwares\\Drivers\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://newtours.demoaut.com/");
	}
	public static void main(String[] args) {
		MercuryTours M1 = new MercuryTours();
		M1.initialSetUp();
		M1.AllHyperLinks();
		M1.printUnamePword();
		M1.signInText();
	}

	public void AllHyperLinks() {
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
		int ExpectedCount = 16;
		if(HyperLinks.size() == ExpectedCount) {
			System.out.println("Test passes. Hyperlinks count is as expected. There are 16 links in the webpage");
		}
		else
			System.out.println("Test fails. Hyperlinks count is not as expected. They should be 16");
	
		System.out.println("------------------------------------------------");
	}

	public void printUnamePword() {
		//
		String UNFieldName = driver.findElement(By.cssSelector("[name='userName'][type='text']")).getAttribute("name");
		String pFieldName = driver.findElement(By.cssSelector("[name='password'][type='password']")).getAttribute("name");	
		System.out.println("Name in Username field is " + UNFieldName + " and Name in Password field is "+ pFieldName);
		if(UNFieldName.equals("userName") && pFieldName.equals("password")) {
			System.out.println("Test passes. Name of username and password fields are as expected");
		}
		else
			System.out.println("Test fails. Name of username and password fields are not as expected");
		
		System.out.println("------------------------------------------------");
	}

	public void signInText() {
		String signInText = driver.findElement(By.cssSelector("[name='login'][value='Login']")).getAttribute("alt");
		System.out.println("Text inside signin button is "+ signInText);
		if(signInText.equals("Sign-In")) {
			System.out.println("Test passes. Text sign in is as expected");
		}
		else
			System.out.println("Test fails. Text sign in is not as expected");
		
		System.out.println("------------------------------------------------");
	}
}
