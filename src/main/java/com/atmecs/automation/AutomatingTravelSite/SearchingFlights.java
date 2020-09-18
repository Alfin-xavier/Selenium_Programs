package com.atmecs.automation.AutomatingTravelSite;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SearchingFlights 
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
	
	 @Test(priority=1)
	  public void twoWayTripSelectionTest() throws InterruptedException
	  {
		WebElement returntrip = driver.findElement(By.xpath(properties.getProperty("roundtrip")));
		  if(returntrip.isSelected()==false)
			  returntrip.click();
		  Thread.sleep(1000);
		  
		 WebElement source = driver.findElement(By.xpath(properties.getProperty("source")));
		 source.sendKeys("coimbatore");
		 Thread.sleep(1000);
		 
		 WebElement destination = driver.findElement(By.xpath(properties.getProperty("destination")));
		 destination.sendKeys("bangalore"); 
		 Thread.sleep(1000);
		 
		 WebElement date1 = driver.findElement(By.xpath(properties.getProperty("date1")));
		 date1.click();
		
		 WebElement departdate = driver.findElement(By.cssSelector(properties.getProperty("departdate")));
		 departdate.click();
		 Thread.sleep(1000);
		
		 WebElement date2 = driver.findElement(By.xpath(properties.getProperty("date2")));
		 date2.click();
		 
		 WebElement returndate = driver.findElement(By.xpath(properties.getProperty("returndate")));
		 returndate.click();
		 Thread.sleep(3000);
		
		 WebElement travellers = driver.findElement(By.xpath(properties.getProperty("travellers")));
		 travellers.click();
		 Thread.sleep(2000);
		
		 Select cabinclass = new Select(driver.findElement(By.id(properties.getProperty("cabinclass"))));
		 cabinclass.selectByValue("First");
		 Thread.sleep(2000);
		
		 WebElement adult = driver.findElement(By.xpath(properties.getProperty("adult")));
		 for(int i=1;i<=4;i++)
			adult.click();
		 Thread.sleep(2000);
		
		 WebElement checkbox = driver.findElement(By.xpath(properties.getProperty("checkbox")));
		 if(checkbox.isSelected()!=true)
			checkbox.click();
		 Thread.sleep(2000);
		
		 WebElement search = driver.findElement(By.xpath(properties.getProperty("searchflight")));
		 search.click();
		 Thread.sleep(2000);
		
	  }
	 
	 @AfterTest 
	  public void closingDriver() 
	  {
		  driver.quit();
	  }
}
