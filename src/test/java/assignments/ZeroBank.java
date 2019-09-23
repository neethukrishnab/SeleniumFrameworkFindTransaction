package assignments;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ZeroBank {
	public static void main(String[] args) throws InterruptedException {
		//Initializing the browser
		System.setProperty("webdriver.chrome.driver", "C:\\\\Program Files\\\\chromedriver\\\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		//Launching URL
		driver.get("http://zero.webappsecurity.com/");

		//Click signin button on top right of the home page
		driver.findElement(By.id("signin_button")).click();

		//Login Details in sign in page
		driver.findElement(By.id("user_login")).sendKeys("username");
		driver.findElement(By.id("user_password")).sendKeys("password");
		driver.findElement(By.name("submit")).click();

		//Click on button add New Payee
		driver.findElement(By.linkText("Pay Bills")).click();
		driver.findElement(By.linkText("Add New Payee")).click();
		Thread.sleep(5000);

		//Add payee Details
		driver.findElement(By.id("np_new_payee_name")).sendKeys("HydroOne Utility");
		driver.findElement(By.id("np_new_payee_address")).sendKeys("200 RoberSpec Pkwy, Mississauga, ON L6R1K9");
		driver.findElement(By.id("np_new_payee_account")).sendKeys("123234434");
		driver.findElement(By.id("np_new_payee_details")).sendKeys("Natural Gas Utility");

		//Click Pay
		driver.findElement(By.id("add_new_payee")).click();
		Thread.sleep(2000);

		//Saving all tabs in an arraylist		
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());

		//Finding the size of the arraylist it is optional
		System.out.println("Number of tabs present " + tabs.size());
		
		//Size of windows will be 1 only as it is switching to new tab in the same window. 
		//After it switches to new tab we are refocusing driver to the same window.
		//Now it can access the elements of new tab.
		driver.switchTo().window(tabs.get(0));

		//Verify that payee is added from text message
		String verificationText = driver.findElement(By.id("alert_content")).getText();
		System.out.println(verificationText); 

		//Check whether the testcase passed or failed
		String expectedText= "The new payee HydroOne Utility was successfully created.";
		if(verificationText.equals(expectedText)) {
			System.out.println("Testcase Passes. New payee is added");
		}
		else {
			System.out.println("Testcase Fails. new payee is not added");
		}

		driver.quit();
	}


}


