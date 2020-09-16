package com.atmecs.assignments.Selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Assignment4 
{
	WebDriver driver;
	@BeforeTest
	public void settingAndLaunchingDriver() throws InterruptedException 
	{

		System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://phptravels.com/demo");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
		driver.manage().deleteAllCookies();
		
	}
	
	@Test
	public void chatBoxTest() throws InterruptedException
	{
		WebElement frame = driver.findElement(By.id("chat-widget"));
		driver.switchTo().frame(frame);
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='lc-zukybo ek650k30']")).click();
	}
}
