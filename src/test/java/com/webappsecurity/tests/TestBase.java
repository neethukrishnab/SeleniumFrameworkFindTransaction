package com.webappsecurity.tests;
//import org.apache.log4j.Logger;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.lf5.util.ResourceUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import utils.BrowserFactory;

@Listeners(utils.NGListeners.class)	


public class TestBase {
static WebDriver driver;
public Properties properties;
Logger logger=Logger.getLogger("TestBase.class");

public void configReader() {
	 //Declare a properties object
   properties = new Properties();

   //Read configuration.properties file
   try {
   	properties.load(new FileInputStream(
   			System.getProperty("user.dir") 
   			+ "\\src\\test\\resources\\Configurations.properties"));
   } catch (IOException e) {
       System.out.println("Configuration properties file cannot be found");
   }	
}


@BeforeClass
	public void setUp() throws IOException {
	configReader();
	logger=Logger.getLogger("TestBase.class");
	
	//PropertyConfigurator.configure("log4j.properties");

	properties.load(getClass().getResourceAsStream("/log4j.properties"));
	PropertyConfigurator.configure(properties);
	//PropertyConfigurator.configure(Testing.class.getClassLoader().getResource("log4j.properties"));
	//org.apache.log4j.PropertyConfigurator.configure("log4j.properties");
	driver = BrowserFactory.chooseBrowser("chrome");
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driver.get(properties.getProperty("url"));
	}
}
