package com.atmecs.assignments.Selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Assignment3_TravelSite 
{
WebDriver driver;
	
	@BeforeTest
	public void settingAndLaunchingDriver() throws InterruptedException 
	{

		System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.kiwi.com/en/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS); 
		
	}
	
	 @Test(priority=1) 
	  public void handlingWindowTest()
	  {
		 driver.findElement(By.xpath("//button[@class='SideNav__MenuOpen-i1ybzg-3 jRiyjw']")).click();
		 
		 List<WebElement> web=driver.findElements(By.cssSelector("div.MenuItem__Link-sc-16ph38z-0.fpEjOe"));
			int count=web.size();
			for(int i=0;i<count;i++)
			{
				String obj=web.get(i).getText();
				if(obj.equalsIgnoreCase("Sign in"))
				{
					web.get(i).click();
				}
			}
	  }
}
