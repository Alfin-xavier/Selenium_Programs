package com.atmecs.assignments.Selenium;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.support.ui.Select;
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
		readDatas();
		
		Wait<WebDriver> wait=new WebDriverWait(driver,30);
		String switchframe = properties.getProperty("frame");
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(properties.getProperty(switchframe)));
		
		driver.switchTo().frame(switchframe);
		
		js = (JavascriptExecutor) driver;
		String clickicon = properties.getProperty("chaticon");
		js.executeScript("arguments[0].click();", clickicon);
		
	}
	
	@Test(priority=2)
	public void fillingRequiredFieldsTest() throws InterruptedException
	{
		readDatas();
		String uname = properties.getProperty("username");
		uname.sendKeys(properties.getProperty(name));
		Thread.sleep(5000);
		WebElement mob = driver.findElement(By.xpath("(//input[@class='lc-1gz7fd7 e1xplv9i0'])[2]"));
		mob.sendKeys("9123456780");
		Thread.sleep(2000);
		WebElement usermail = driver.findElement(By.id("email"));
		usermail.sendKeys("xyz@gmail.com");
		Thread.sleep(2000);
		WebElement checkbox = driver.findElement(By.xpath("//input[@class='lc-5mlnfz e81sjne0']"));
		checkbox.click();
		Thread.sleep(2000);
		Select s=new Select(driver.findElement(By.xpath("//select[@class='lc-jjhb0i egtcv0s1']")));
		s.selectByValue("index1_1");
		Thread.sleep(2000);
		WebElement	button = driver.findElement(By.xpath("//button[@class='lc-tf4jp6 esv0owm0']"));
		js.executeScript("arguments[0].click();", button);
		Thread.sleep(3000);
	}
	
	public void readDatas() throws IOException
	{
		properties = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("User.dir")+"/locators/assignmnet4.properties");
		properties.load(file);
	}
	
	@AfterTest
	public void closingDriver()
	{
		driver.close();
	}
}
