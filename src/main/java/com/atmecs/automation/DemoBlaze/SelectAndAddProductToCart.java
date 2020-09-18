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

public class SelectAndAddProductToCart 
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
	
	@Test(priority = 3) 
	public void selectAndAddToCartProduct() throws InterruptedException 
	{ 
		  
		  
		  WebElement selectProduct1 = driver.findElement(By.xpath(properties.getProperty("selectProduct1")));
		  selectProduct1.click();
		  
		  WebElement addProduct1 = driver.findElement(By.cssSelector(properties.getProperty("addProduct1")));
		  addProduct1.click();
		  Thread.sleep(2000);

		  driver.switchTo().alert().accept();
		  Thread.sleep(2000);
		  
		  // click the cart to check whether the product added or not
		  WebElement clickCart = driver.findElement(By.xpath(properties.getProperty("clickCart")));
		  clickCart.click(); 
		  Thread.sleep(2000);
		 }
	
	 @AfterTest 
	  public void closingDriver() 
	  {
		  driver.quit();
	  }
}
