package com.atmecs.automation.demoAutomation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class OpeningSocialIconsAndHandlingWindows 
{
	WebDriver driver;
	Properties properties;
	
	@BeforeTest
	public void settingAndLaunchingDriver() throws IOException 
	{
		
		System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		FileInputStream file = new FileInputStream(
				System.getProperty("user.dir") + "\\locatorsAndTestDatas\\registration.properties");
				properties = new Properties();
				properties.load(file);
		driver.get(properties.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	}
	
	@Test(priority=2)
	public void windowsHandling() throws IOException
	{
		WebElement subdriver = driver.findElement(By.id(properties.getProperty("footer")));
		
		WebElement subdriver1 = subdriver.findElement(By.xpath(properties.getProperty("icons")));
		for(int i=0;i<subdriver1.findElements(By.tagName("a")).size();i++)
		{
			String link=Keys.chord(Keys.CONTROL,Keys.ENTER);
			subdriver1.findElements(By.tagName("a")).get(i).sendKeys(link);
		}
		
		//switching taps
		Set<String> windows=driver.getWindowHandles();
		Iterator<String> itr=windows.iterator();
		//get the title of each tap
		while(itr.hasNext())
		{
			driver.switchTo().window(itr.next());
			System.out.println(driver.getTitle());
		}
	}
	
	@AfterTest
	public void closingDriver()
	{
		driver.close();
	}
}
