package com.atmecs.automation.DemoBlaze;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ContactUs 
{
	WebDriver driver;
	Properties properties;
	@BeforeTest
	public void settingAndLaunchingDriver() throws IOException 
	{
		
		System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		FileInputStream file = new FileInputStream(
				System.getProperty("user.dir") + "\\locatorsAndTestDatas\\assignment2.properties");
				properties = new Properties();
				properties.load(file);
		driver.get(properties.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	}
	
	@Test(priority=4)
	public void contactUsScript() throws InterruptedException 
	{
			
		WebElement contactUs = driver.findElement(By.xpath(properties.getProperty("contactUs")));
		contactUs.click();
		
		WebElement recipientMail = driver.findElement(By.id(properties.getProperty("recipientMail")));
		recipientMail.sendKeys(properties.getProperty("mailValue"));
		
		WebElement recipientName = driver.findElement(By.id(properties.getProperty("recipientName")));
		recipientName.sendKeys(properties.getProperty("nameValue"));
		
		WebElement message = driver.findElement(By.id(properties.getProperty("message")));
		message.sendKeys(properties.getProperty("messageValue"));
		
		WebDriverWait w= new WebDriverWait(driver, 5);
		WebElement button=driver.findElement(By.xpath(properties.getProperty("button3")));
		w.until(ExpectedConditions.elementToBeClickable(button));
		button.click();
		Thread.sleep(1000);
		
		driver.switchTo().alert().accept();
		Thread.sleep(1000); 
	}
}
