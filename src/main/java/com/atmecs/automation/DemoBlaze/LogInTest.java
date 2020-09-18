package com.atmecs.automation.DemoBlaze;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class LogInTest
{
	WebDriver driver;
	Properties properties;
	@BeforeTest
	public void settingAndLaunchingDriver() throws IOException 
	{
		System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		FileInputStream file = new FileInputStream(
				System.getProperty("user.dir") + "\\locatorsAndTestDatas\\assignment2.properties");
				properties = new Properties();
				properties.load(file);
		driver.get(properties.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	}
	
	 @Test(priority = 2) 
	  public void logInTest() throws InterruptedException, IOException 
	  {
		  	  
		  WebElement login = driver.findElement(By.id(properties.getProperty("login")));
		  login.click();
		  
		  WebElement lusername = driver.findElement(By.id(properties.getProperty("lusername")));
		  lusername.sendKeys(properties.getProperty("lname"));
		  
		  WebElement lpassword = driver.findElement(By.id(properties.getProperty("lpassword")));
		  lpassword.sendKeys(properties.getProperty("lpass"));
		  
		  WebElement button2 = driver.findElement(By.xpath(properties.getProperty("button2")));
		  button2.click();
		  Thread.sleep(2000);
		  
	  }
}
