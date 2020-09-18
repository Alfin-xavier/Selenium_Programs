package com.atmecs.assignments.Selenium;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;


public class LocatorTest 
{
	WebDriver driver;
	JavascriptExecutor js;
	public static Properties properties;
	@BeforeTest
	public void settingAndLaunchingDriver() throws InterruptedException, IOException 
	{

		System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		readDatas();
		driver.get(properties.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
		driver.manage().deleteAllCookies();
		
	}
	
	@Test(priority=1)
	public void handlingFrameAndClickChatIconTest() throws InterruptedException, IOException
	{
		Wait<WebDriver> wait=new WebDriverWait(driver,30);
		
		WebElement frame = driver.findElement(By.id(properties.getProperty("frame")));
		
		wait.until(ExpectedConditions.elementToBeClickable(frame));
		driver.switchTo().frame(frame);
		
		 js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollBy(0,1000)");
		
		 WebElement chaticon = driver.findElement(By.xpath(properties.getProperty("chaticon")));
		
		js.executeScript("arguments[0].click();", chaticon);
		
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
	
	public void readDatas() throws IOException
	{
		File file = new File("C:\\Users\\alfin.anthonyraj\\eclipse-workspace\\Selenium\\locators\\assignmnet4.properties");
		  
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
