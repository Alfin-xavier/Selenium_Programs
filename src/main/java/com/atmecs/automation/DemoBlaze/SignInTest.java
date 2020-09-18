package com.atmecs.automation.DemoBlaze;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SignInTest 
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
	

	  @Test(priority = 1) 
	  public void signUpTest() throws InterruptedException
	  {
		  
		  WebElement signin = driver.findElement(By.id(properties.getProperty("signin")));
		  signin.click();
		  
		  WebElement susername = driver.findElement(By.id(properties.getProperty("signinusername")));
		  susername.sendKeys(properties.getProperty("sname"));
		  
		  WebElement spassword = driver.findElement(By.id(properties.getProperty("signinpassword")));
		  spassword.sendKeys(properties.getProperty("spass"));
		  
		  WebElement button1 = driver.findElement(By.xpath(properties.getProperty("button1")));
		  button1.click();
		  Thread.sleep(2000);
		  
		  driver.switchTo().alert().accept();
		  Thread.sleep(1000); 
		  
	  }
	  
	  @AfterTest 
	  public void closingDriver() 
	  {
		  driver.quit();
	  }
}
