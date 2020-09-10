//Implementing various test annotation to automate a google webpage

package com.atmecs.assignments.Selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class Assignmant1 
{
	WebDriver driver;
	
	@BeforeClass // will run after the @BeforeTest
	public void greeting()
	{
		System.out.println("Welcome To Selenium\n");
	}

	@BeforeTest //executed first
	public void message()
	{
		System.out.println("Simple script using annotations\n");
	}

	@BeforeMethod // Will execute before all the test and after the @BeforeClass
	public void infoBeforeLaunching()
	{
		System.out.println("Browser Launching and hit the url and validate the page");
	}
	
	@Test
	public void settingAndLaunchingDriver() 
	{
		System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.google.com");
	}
	
	@Test
	public void validatingPage()
	{
		System.out.println("Page Title:" + driver.getTitle() + "\n");
		String url = driver.getCurrentUrl();
		System.out.println("Current Url:" + url + "\n");
		Assert.assertEquals(url, "https://www.google.com/");
	}

	@AfterMethod // will execute after all the tests
	public void infoAfterLaunching()
	{
		System.out.println("Browser Launched and hit the url and validated the page");
	}
	
	@AfterClass // will run after the @AfterMethod
	public void warning()
	{
		System.out.println("Browser exited \n");
	}
	
	@AfterTest // will execute after the @AfterClass
	public void closingDriver() 
	{
		driver.close();
	}

	
}
