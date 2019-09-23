package com.webappsecurity.pages;

import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AccountFindTrans extends BasePage {
	Logger logger = Logger.getLogger("AccountFindTrans.class");
	@FindBy(name = "description")
	WebElement description;

	@FindBy(name = "fromDate")
	WebElement fromdate;

	@FindBy(name = "toDate")
	WebElement todate;

	@FindBy(name = "fromAmount")
	WebElement fromamount;

	@FindBy(name = "toAmount")
	WebElement toamount;

	@FindBy(id = "aa_type")
	WebElement Select;

	@FindBy(linkText = "Account Activity")
	WebElement accountactivity;

	@FindBy(linkText = "Find Transactions")
	WebElement findtransactions;

	@FindBy(xpath = "//div//button[@type='submit']")
	WebElement find;

	public AccountFindTrans() {
		super(driver);
		PageFactory.initElements(driver, this);
		logger.info("Page Factory elements are initialized");
	}

	public void accountactivityclick() throws InterruptedException {
		accountactivity.click();
		Thread.sleep(2000);
		logger.info("Clicked in Account Activity tab");

	}

//Entering values to different fields

	public void find_transaction(String desc, String fromamount1, String toamount1, String index)
			throws InterruptedException {
		description.clear();
		fromamount.clear();
		toamount.clear();
		description.sendKeys(desc);
		fromamount.sendKeys(fromamount1);
		toamount.sendKeys(toamount1);

		Select stat = new Select(driver.findElement(By.id("aa_type")));
		if (index.isEmpty()) {
			stat.getFirstSelectedOption();
		} else {
			stat.selectByVisibleText(index);
			Thread.sleep(2000);
		}
		logger.info("Values are entered into different fields");
	}

	// Date picker from date
	public void fromdate_datepicker(String month, String day) throws InterruptedException {
		if (month.isEmpty() || day.isEmpty()) {
			System.out.println("From date is not entered");
			return;
		}
		fromdate.clear();
		Thread.sleep(1000);
		fromdate.click();
		Thread.sleep(3000);
		while (true) {
			String s = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();
			if (s.contentEquals(month)) {
				break;
			} else {
				driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-w']")).click();
			}
		}

		driver.findElement(By.linkText(day)).click();
		logger.info("From date is picked from the date picker");
		Thread.sleep(2000);
	}

	// Date picker to date
	public void todate_datepicker(String month, String day) throws Throwable {
		if (month.isEmpty() || day.isEmpty()) {
			System.out.println("To date value is not entered");
			return;
		}
		todate.clear();
		Thread.sleep(1000);
		todate.click();
		Thread.sleep(2000);
		while (true) {
			String s = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();
			if (s.contentEquals(month)) {
				break;
			} else {
				driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-w']")).click();
			}
		}

		driver.findElement(By.linkText(day)).click();
		logger.info("To date is picked from the date picker");
		Thread.sleep(3000);

	}

	// Clicking on Find Transaction Tab
	public void find_transaction_tabclick() {
		findtransactions.click();
		logger.info("Clicked in Find transaction tab");
	}

	// Clicking on Find Button for Submit
	public void find_submitbutton() throws Throwable {
		find.click();
		JavascriptExecutor jsx = (JavascriptExecutor) driver;
		jsx.executeScript("window.scrollBy(450,700)", "");
		Thread.sleep(2000);
		logger.info("Clicked in find button and scrolled down the screen for results");
	}

	// Verifying the type of transaction
	public boolean check_type() {
		Select type = new Select(Select);
		WebElement first_value = type.getFirstSelectedOption();
		String value = first_value.getText();
		if (value.contentEquals("Deposit") == true) {
			return check_depositamount();
		}

		else if (value.contentEquals("Withdrawal") == true) {
			return check_withdrawalamount();

		}

		else {
			System.out.println("Deposit and Withdrwal");
			return true;
		}
	}

	// verifying the Withdraw transaction
	public boolean check_withdrawalamount() {
		String t = "filtered_transactions_for_account";
		char u = '"';
		String f = "//*[@id=" + u + t + u + "]/table/tbody/tr[";
		String s = "]/td[4]";
		int r = row_size();
		for (int m = 1; m <= r; m++) {
			String xpath = f + m + s;
			String amt1 = fromamount.getAttribute("value");
			String amt2 = toamount.getAttribute("value");
			String str1 = driver.findElement(By.xpath(xpath)).getText();
			if ((amt1.isEmpty()) || (amt2.isEmpty())) {
				System.out.println("The type of transaction is withdrawal");
				return true;
			}

			if (str1.isEmpty()) {
				// continue;
				System.out.println("The value in deposit column for row" + m + "is  empty");
				return false;
			}
			Float amount = Float.valueOf(str1);
			System.out.println(amount);

			Integer amount1 = Integer.valueOf(amt1);
			Integer amount2 = Integer.valueOf(amt2);
			System.out.println(amount1);
			System.out.println(amount2);
			logger.info("Withdrawal type is verified");
			if ((amount > amount1) && (amount < amount2)) {
				System.out.println("The type of transaction is 'Withdrawal' and the amount is between the limit");
				continue;
			} else {
				System.out.println(
						"the type of transaction is 'Withdrawal' and the amount is not between the limit the given");
				return false;
			}

		}
		return true;

	}

	// Verifying the amount fields
	public boolean check_amount() {
		String amt1 = fromamount.getAttribute("value");
		String amt2 = toamount.getAttribute("value");
		if (amt1.isEmpty()) {
			System.out.println("The fromamount is not entered");
		}
		if (amt2.isEmpty()) {
			System.out.println("The toamount is not entered");
		} else {
			return true;
		}
		logger.info("Amount is verified");
		return false;
	}

	// Verifying the deposit type
	public boolean check_depositamount() {

		String t = "filtered_transactions_for_account";
		char u = '"';
		String f = "//*[@id=" + u + t + u + "]/table/tbody/tr[";
		String s = "]/td[3]";
		int r = row_size();
		for (int m = 1; m <= r; m++) {
			String xpath = f + m + s;
			String str1 = driver.findElement(By.xpath(xpath)).getText();
			String amt1 = fromamount.getAttribute("value");
			String amt2 = toamount.getAttribute("value");
			if (str1.isEmpty()) {
				// continue;

				System.out.println("the value in the deposit column in row" + m + "is empty");
				return false;
			}
			if (amt1.isEmpty() && amt2.isEmpty()) {
				System.out.println("The type of transaction is Deposit");
				return true;
			}
			Integer amount1 = Integer.valueOf(amt1);
			Integer amount2 = Integer.valueOf(amt2);
			Float amount = Float.valueOf(str1);
			System.out.println(amount);
			System.out.println(amount1);
			System.out.println(amount2);
			logger.info("Deposit amount is checked and is verified");
			if ((amount > amount1) && (amount < amount2)) {
				System.out.println("the type of transaction is 'Deposit' and the amount is between the limits");
				continue;
			} else {
				System.out.println(
						"the type of transaction is 'Deposit' and the amount is not between the limit the given");
				return false;
			}
		}
		return true;
	}

	// Checking the date entered in the fields
	public boolean check_date() throws Throwable {
		if (fromdate.getAttribute("value").isEmpty() == false || todate.getAttribute("value").isEmpty() == false) {
			String str1;
			String t = "filtered_transactions_for_account";
			char u = '"';
			String f = "//*[@id=" + u + t + u + "]/table/tbody/tr[";
			String s = "]/td[1]";
			int r = row_size();
			boolean t1 = true;
			for (int m = 1; m <= r; m++) {
				String xpath = f + m + s;
				String startdate = fromdate.getAttribute("value");
				String enddate = todate.getAttribute("value");
				if (startdate.isEmpty()) {
					System.out.println("The start date is not entered");
					startdate = enddate;
				}
				if (enddate.isEmpty()) {
					System.out.println("The end date is not entered");
					enddate = startdate;
				}
				str1 = driver.findElement(By.xpath(xpath)).getText();
				// String sDate1="31/12/1998"; //change this here
				Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(str1);
				Date startdate1 = new SimpleDateFormat("yyyy-MM-dd").parse(startdate);
				Date enddate1 = new SimpleDateFormat("yyyy-MM-dd").parse(enddate);
				t1 = !(date1.before((java.util.Date) startdate1) || date1.after((java.util.Date) enddate1));

			}
			System.out.println(t1);
			logger.info("checked whether the result date is between the start and the ned date");
			System.out.println("The date is between the start and end date");
			return t1;
		} else {
			System.out.println("The date is not between the start and the end date");
			return false;
		}
	}

	// finding the number of rows in result table
	public int row_size() {
		List<WebElement> rows = (List<WebElement>) driver
				.findElements(By.xpath("//*[@id=\"filtered_transactions_for_account\"]/table"));
		return rows.size();

	}

	// Verifying the description field with the result table
	public boolean check_description() {
		if (description.getAttribute("value").isEmpty() == false) {
			String t = "filtered_transactions_for_account";
			char u = '"';
			String f = "//*[@id=" + u + t + u + "]/table/tbody/tr[";
			String s = "]/td[2]";
			String xpath = f + 1 + s;
			String str = description.getAttribute("value");
			String str1 = driver.findElement(By.xpath(xpath)).getText();
			logger.info("Checking whether the description field is empty or not");
			if (str1.contentEquals(str)) {
				System.out.println("The description result is matching");
				return true;
			} else {
				System.out.println("The description result is not matching ");

			}
		}
		return false;
	}

}
