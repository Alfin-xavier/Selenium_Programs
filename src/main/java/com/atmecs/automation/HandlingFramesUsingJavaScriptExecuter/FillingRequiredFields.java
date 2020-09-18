package com.atmecs.automation.HandlingFramesUsingJavaScriptExecuter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FillingRequiredFields 
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
	
	@Test(priority=2)
	public void fillingRequiredFieldsTest() throws InterruptedException
	{
		
		WebElement username = driver.findElement(By.id(properties.getProperty("username")));
		username.sendKeys(properties.getProperty("name"));
		
		WebElement mob = driver.findElement(By.xpath(properties.getProperty("mob")));
		mob.sendKeys(properties.getProperty("number"));

		WebElement usermail = driver.findElement(By.id(properties.getProperty("usermail")));
		usermail.sendKeys(properties.getProperty("gmail"));

		WebElement checkbox = driver.findElement(By.xpath(properties.getProperty("checkbox")));
		checkbox.click();

		Select client=new Select(driver.findElement(By.xpath(properties.getProperty("client"))));
		client.selectByValue("index1_1");

		WebElement	button = driver.findElement(By.xpath(properties.getProperty("button")));
		js.executeScript("arguments[0].click();", button);
		Thread.sleep(3000);
	}
	
	 @AfterTest 
	  public void closingDriver() 
	  {
		  driver.quit();
	  }
}
