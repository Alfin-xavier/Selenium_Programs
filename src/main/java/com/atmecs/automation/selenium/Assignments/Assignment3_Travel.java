// Automationg a Travel website by selecting Two way Trip and handling windows when click on the login/signup

package com.atmecs.automation.selenium.Assignments;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Assignment3_Travel 
{
	WebDriver driver;
	Properties properties;
	
	@BeforeTest
	public void settingAndLaunchingDriver() throws InterruptedException, IOException 
	{
		readDatas();
		System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(properties.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(150, TimeUnit.SECONDS); 
		driver.manage().deleteAllCookies();
		
	}
	
	
	@Test(priority=1) 
	  public void handlingWindowTest() throws IOException, InterruptedException 
	  { 
		readDatas();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement login = driver.findElement(By.xpath(properties.getProperty("login"))); 
		login.click();
	  
		WebElement gmail = driver.findElement(By.xpath(properties.getProperty("gmail"))); 
		gmail.click();
	  
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
	
	  
	  @Test(priority=0)
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
	  
	  public void readDatas() throws IOException
		{
			File file = new File("C:\\Users\\alfin.anthonyraj\\eclipse-workspace\\Selenium\\locatorsAndTestDatas\\travel.properties");
			  
			FileInputStream fileInput = null;
			try {
				fileInput = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			 properties = new Properties();
			
			//load properties file
			try {
				properties.load(fileInput);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	  
		  @AfterTest 
		  public void closingDriver() 
		  {
			  driver.quit();
		  }
		 
}
