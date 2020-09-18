package com.atmecs.assignments.Selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;


public class Assignment4 
{
	WebDriver driver;
	JavascriptExecutor js;
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
	
	@Test(priority=1)
	public void handlingFrameAndClickChatIconTest() throws InterruptedException
	{
		
		Wait<WebDriver> wait=new WebDriverWait(driver,30);
		
		WebElement frame = driver.findElement(By.id("chat-widget"));
		
		wait.until(ExpectedConditions.elementToBeClickable(frame));
		driver.switchTo().frame(frame);
		
		 js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollBy(0,1000)");
		
		 WebElement chaticon = driver.findElement(By.xpath("//div[@class='lc-1z06j5z e1dmt1bi1']"));
		
		js.executeScript("arguments[0].click();", chaticon);
		
		
		
	}
	
	@Test(priority=2)
	public void fillingRequiredFieldsTest() throws InterruptedException
	{
		
		
		WebElement username = driver.findElement(By.id("name"));
		username.sendKeys("Abc");
		
		WebElement mob = driver.findElement(By.xpath("(//input[@class='lc-1gz7fd7 e1xplv9i0'])[2]"));
		mob.sendKeys("9123456780");

		WebElement usermail = driver.findElement(By.id("email"));
		usermail.sendKeys("xyz@gmail.com");

		WebElement checkbox = driver.findElement(By.xpath("//input[@class='lc-5mlnfz e81sjne0']"));
		checkbox.click();

		Select s=new Select(driver.findElement(By.xpath("//select[@class='lc-jjhb0i egtcv0s1']")));
		s.selectByValue("index1_1");
		
		WebElement	button = driver.findElement(By.xpath("//button[@class='lc-tf4jp6 esv0owm0']"));
		js.executeScript("arguments[0].click();", button);
		Thread.sleep(3000);
	}
	
	@AfterTest
	public void closingDriver()
	{
		driver.close();
	}
}
