package com.atmecs.automation.HandlingFramesUsingJavaScriptExecuter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

public class HandlingChatIconFrameUsingJS 
{
	
	WebDriver driver;
	Properties properties;
	JavascriptExecutor js;
	@BeforeTest
	public void settingAndLaunchingDriver() throws IOException 
	{
		
		System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		FileInputStream file = new FileInputStream(
				System.getProperty("user.dir") + "\\locatorsAndTestDatas\\assignmnet4.properties");
				properties = new Properties();
				properties.load(file);
		driver.get(properties.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	}
	
	@Test(priority=1)
	public void handlingFrameAndClickChatIconTest() throws InterruptedException, IOException
	{
		Wait<WebDriver> wait = new WebDriverWait(driver, 30);
		WebElement frame = driver.findElement(By.id(properties.getProperty("frame")));
		wait.until(ExpectedConditions.elementToBeClickable(frame));
		driver.switchTo().frame(frame);
		js = (JavascriptExecutor) driver;
		WebElement chaticon = driver.findElement(By.xpath(properties.getProperty("chaticon")));
		js.executeScript("window.scrollBy(0,1000)");
		js.executeScript("arguments[0].click();", chaticon);
		
	}
	
	 @AfterTest 
	  public void closingDriver() 
	  {
		  driver.quit();
	  }
}
