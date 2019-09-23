package demos;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import utils.DataProviderHolder;
import utils.DataProviderHolder;

public class DataProviderTest1{
	@Test(dataProvider="findvalues", dataProviderClass = DataProviderHolder.class)
	public void VerifyInvalidLogin(String userName, String password) throws IOException {
	System.out.println(userName);
	System.out.println(password);
	DataProviderHolder.writeIntoExcel();
}
}
