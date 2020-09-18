package com.atmecs.automation.AutomatingTravelSite;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginWindowHandling 
{
	WebDriver driver;
	Properties properties;
	@BeforeTest
	public void settingAndLaunchingDriver() throws IOException 
	{
		System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		FileInputStream file = new FileInputStream(
				System.getProperty("user.dir") + "\\locatorsAndTestDatas\\travel.properties");
				properties = new Properties();
				properties.load(file);
		driver.get(properties.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	}
	
	@Test(priority=2) 
	  public void handlingWindowTest() throws IOException, InterruptedException 
	  { 
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement login = driver.findElement(By.xpath(properties.getProperty("login"))); 
		login.click();
	  
		WebElement gmail = driver.findElement(By.xpath(properties.getProperty("gmail"))); 
		gmail.click();
	  
		//Handling WIndows
		
		Set<String> ids = driver.getWindowHandles(); 
		Iterator<String> windows = ids.iterator();
		String parentWindow = windows.next();
		String childwindow = windows.next(); 
		
		driver.switchTo().window(childwindow);
	  
		WebElement username = driver.findElement(By.id(properties.getProperty("username")));
		username.sendKeys(properties.getProperty("name"));
		
		WebElement next1 = driver.findElement(By.cssSelector(properties.getProperty("button1"))); 
		next1.click();
	  
	  
		WebElement password=driver.findElement(By.xpath(properties.getProperty("userpassword")));
		password.sendKeys(properties.getProperty("password")); 
	  
		WebElement next2=driver.findElement(By.xpath(properties.getProperty("button2")));
		next2.click(); 
		Thread.sleep(10000);
	  
	  }
	
	 @AfterTest 
	  public void closingDriver() 
	  {
		  driver.quit();
	  }
}
