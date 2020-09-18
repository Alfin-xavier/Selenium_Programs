
//Implementing various test annotation to automate a google webpage

package com.atmecs.automation.Annotations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class Assignmant1 
{
	WebDriver driver;
	
	@BeforeClass(alwaysRun=true) // will run after the @BeforeTest
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
	
	@Test(priority=0)
	public void settingAndLaunchingDriver() 
	{
		System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.google.com");
	}
	
	@Test(priority=2)
	public void gettingPageTitle()
	{
		System.out.println("Page Title:" + driver.getTitle() + "\n");
		
	}
	
	@Test(priority=1)
	public void gettingAndValidatingUrl()
	{
		String url = driver.getCurrentUrl();
		System.out.println("Current Url: " + url + "\n");
		Assert.assertEquals(url, "https://www.google.com/");
	}

	@AfterMethod // will execute after all the tests
	public void infoAfterLaunching()
	{
		System.out.println("Browser Launched and hit the url and validated the page");
	}
	
	@AfterClass // will run after the @AfterMethod
	public void greeting1()
	{
		System.out.println("Browser exited \n");
	}

	@BeforeSuite
	public void beforeSuite()
	{
		System.out.println("This will be execute before all the test\n");
	}
	
	@AfterSuite
	public void afterSuite()
	{
		System.out.println("This will be execute after all the test\n");
	}
	
	@AfterTest(description="Going to close the browser") // will execute after the @AfterClass()
	public void closingDriver() 
	{
		driver.close();
	}

	
}
