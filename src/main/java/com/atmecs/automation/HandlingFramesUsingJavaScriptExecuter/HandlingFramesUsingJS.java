// Handling frames using javascript

package com.atmecs.automation.HandlingFramesUsingJavaScriptExecuter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;


public class HandlingFramesUsingJS 
{
	WebDriver driver;
	JavascriptExecutor js;
	Properties properties;
	@BeforeTest
	public void settingAndLaunchingDriver() throws InterruptedException, IOException 
	{

		System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		dataProvider();
		driver.get(properties.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
		driver.manage().deleteAllCookies();
		
	}
	
	@Test
	public void handlingFrameAndClickChatIconTest() throws InterruptedException, IOException
	{
		dataProvider();
		
		Wait<WebDriver> wait=new WebDriverWait(driver,30);
		
		WebElement frame = driver.findElement(By.id(properties.getProperty("frame")));
		
		wait.until(ExpectedConditions.elementToBeClickable(frame));
		driver.switchTo().frame(frame);
		
		 js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollBy(0,1000)");
		
		 Thread.sleep(2000);
		 
		 WebElement chaticon = driver.findElement(By.xpath(properties.getProperty("chaticon")));
		
		js.executeScript("arguments[0].click();", chaticon);
		
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
	
	public void dataProvider() throws IOException
	{
		FileInputStream file = new FileInputStream(
				System.getProperty("user.dir") + "\\locatorsAndTestDatas\\assignmnet4.properties");
				properties = new Properties();
				properties.load(file);
	}
	
	@AfterTest
	public void closingDriver()
	{
		driver.quit();
	}
}
