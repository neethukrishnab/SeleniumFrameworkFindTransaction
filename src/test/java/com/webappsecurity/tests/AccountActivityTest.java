package com.webappsecurity.tests;

import org.testng.annotations.Test;
import org.apache.commons.logging.impl.Log4JLogger;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.webappsecurity.pages.AccountFindTrans;
import com.webappsecurity.pages.HomePage;
import com.webappsecurity.pages.LoginPage;

import utils.ExcelConnect;

//Verify Find Transaction
public class AccountActivityTest extends TestBase {

	Logger logger = Logger.getLogger("AccountActivityTest.class");
	// TODO Auto-generated method stub
	HomePage homepage;
	LoginPage loginpage;
	AccountFindTrans accountfindtrans;

	@BeforeClass
	public void initialize() throws Throwable {
		homepage = new HomePage(driver);
		loginpage = new LoginPage(driver);
		accountfindtrans = new AccountFindTrans();
		homepage.clickSigninButton();
		loginpage.enterCredentials("username", "password");
		accountfindtrans.accountactivityclick();
		accountfindtrans.find_transaction_tabclick();
	}

	@Test(priority = 1, dataProvider = "adddata", dataProviderClass = ExcelConnect.class)
	// String d,String date1, String date2, String amt1, String amt2, String type
	public void adddata(String Description, String day1, String MonthYear1, String Day2, String MonthYear2,
			String Amount1, String Amount2, String Type) throws Throwable {
		accountfindtrans.find_transaction(Description, Amount1, Amount2, Type);
		accountfindtrans.fromdate_datepicker(MonthYear1, day1);
		accountfindtrans.todate_datepicker(MonthYear2, Day2);
		logger.info("Fetched one row from excel and added t the fields");
		accountfindtrans.find_submitbutton();
		logger.info("Clicked in Find Button");
	}

	// Verifying the description field
	@Test(priority = 2)
	public void verify_description() {
		Assert.assertTrue(accountfindtrans.check_description(), "Description is not entered or is not matching");
		// Thread.sleep(2000);
		logger.info("Verified whether description field is filled");
	}

	// Verifying the date fields
	@Test(priority = 3)
	public void verify_date() throws Throwable {
		Assert.assertTrue(accountfindtrans.check_date(), "Date is not entered or it is not matching");
		logger.info("Verified whether to and from date is entered or not");
	}

	// Verifying amount coulmn
	@Test(priority = 4)
	public void verify_amount() {
		Assert.assertTrue(accountfindtrans.check_amount(), "Amount is not entered");
		logger.info("Verified whether the from and to amount is entered or not");
	}

	// Verifying the Type of Transaction
	@Test(priority = 5)
	public void verify_typeoftrans() {
		// accountfindtrans.check_type();
		Assert.assertTrue(accountfindtrans.check_type(), "Amount is not between the limits");
		logger.info("Verified the type of transaction");
	}

	@AfterClass
	public void exit() {
		driver.close();
	}
}
