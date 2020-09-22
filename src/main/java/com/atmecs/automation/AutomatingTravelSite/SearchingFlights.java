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
		System.setProperty("webdriver.chrome.driver", "\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		//dataprovider.dataProvider();
		dataProvider();
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
		  
		 WebElement source = driver.findElement(By.xpath(properties.getProperty("source")));
		 source.sendKeys("coimbatore");
		 
		 WebElement destination = driver.findElement(By.xpath(properties.getProperty("destination")));
		 destination.sendKeys("bangalore"); 
		 
		 WebElement date1 = driver.findElement(By.xpath(properties.getProperty("date1")));
		 date1.click();
		
		 WebElement departdate = driver.findElement(By.cssSelector(properties.getProperty("departdate")));
		 departdate.click();
		 
		 WebElement date2 = driver.findElement(By.xpath(properties.getProperty("date2")));
		 date2.click();
		 
		 WebElement returndate = driver.findElement(By.xpath(properties.getProperty("returndate")));
		 returndate.click();
		
		 WebElement travellers = driver.findElement(By.xpath(properties.getProperty("travellers")));
		 travellers.click();
		
		 Select cabinclass = new Select(driver.findElement(By.id(properties.getProperty("cabinclass"))));
		 cabinclass.selectByValue("First");
		
		 WebElement adult = driver.findElement(By.xpath(properties.getProperty("adult")));
		 for(int i=1;i<=4;i++)
			adult.click();
		
		 WebElement checkbox = driver.findElement(By.xpath(properties.getProperty("checkbox")));
		 if(checkbox.isSelected()!=true)
			checkbox.click();
		
		 WebElement search = driver.findElement(By.xpath(properties.getProperty("searchflight")));
		 search.click();
		
	  }
	 
	 public void dataProvider() throws IOException
	 {
		 FileInputStream locator = new FileInputStream(
					System.getProperty("user.dir") + "\\datas\\testdata.properties");
					properties = new Properties();
					properties.load(locator);
					
	 }
	 
	 @AfterTest 
	  public void closingDriver() 
	  {
		  driver.quit();
	  }
}
