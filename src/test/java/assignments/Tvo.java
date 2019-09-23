package assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tvo {
		
		public static void main(String[] args) throws InterruptedException {
			
			System.setProperty("webdriver.chrome.driver", "E:\\Softwares\\Drivers\\ChromeDriver\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			
			driver.get("https://tvo.org/");
			
			//****************************************
			//To scroll in the page
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.scrollBy(0,1200)");
	        //*****************************************

			driver.findElement(By.linkText("Jobs at TVO")).click();
			Thread.sleep(1000);
			
			//To scroll in the page
	        js.executeScript("window.scrollBy(0,100)");   
		    driver.findElement(By.linkText("Current Job Postings")).click();
			Thread.sleep(1000);			    
		    
			for (String handle1 : driver.getWindowHandles()) {

	        	System.out.println(handle1);
	        	driver.switchTo().window(handle1);

	        	}
			
			WebElement Searchiframe = driver.findElement(By.id("icims_content_iframe"));
			driver.switchTo().frame(Searchiframe);
			
			driver.findElement(By.id("jsb_f_keywords_i")).sendKeys("Automation");
			driver.findElement(By.id("jsb_form_submit_i")).click();
			Thread.sleep(5000);
			
			//Verify error message
			String ActualMessage = driver.findElement(By.cssSelector(".iCIMS_Message.iCIMS_ErrorMessage.iCIMS_GenericMessage")).getText();
			System.out.println(ActualMessage);
			
			String ExpectedMsg = "Sorry, no jobs were found that match your search criteria. Please try other selections.";
			
			if(ActualMessage.equals(ExpectedMsg)) {
				System.out.println("Testcase Passes. Error message is displayed currectly");
			}
			else
			{
				System.out.println("Testcase Passes. Error message is displayed currectly");
			}
				
			driver.quit();
			
			}

	}



