package com.atmecs.assignments.Selenium;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class Assignment3_Task1 
{	
	WebDriver driver;
	@BeforeTest
	public void settingAndLaunchingDriver() 
	{

		System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.makemytrip.com/");
		driver.manage().window().maximize();
	}
	
	@Test(priority=1)
	public void handlingWindowsViaSignup() throws InterruptedException
	{
		driver.findElement(By.cssSelector("li.makeFlex.hrtlCenter.font10.makeRelative.lhUser")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("span.font14.darkGreyText.latoBold")).click();
		Set<String> ids = driver.getWindowHandles();
		Iterator<String> windows = ids.iterator();
		String parentWindow = windows.next();
		String childwindow = windows.next();
		driver.switchTo().window(childwindow);
		driver.findElement(By.id("identifierId")).sendKeys("shanealfin720@gmail.com");
		WebElement next1 = driver.findElement(By.cssSelector("button.VfPpkd-LgbsSe.VfPpkd-LgbsSe-OWXEXe-k8QpJ.VfPpkd-LgbsSe-OWXEXe-dgl2Hf.nCP5yc.AjY5Oe.DuMIQc.qIypjc.TrZEUc"));
		next1.click();
		Thread.sleep(2000);
		WebElement password=driver.findElement(By.xpath("//div[@class='aXBtI I0VJ4d Wic03c']//div//input[@name='password']"));
		password.sendKeys("alfinxavier.a");
		
		WebElement next2=driver.findElement(By.cssSelector("button.VfPpkd-LgbsSe.VfPpkd-LgbsSe-OWXEXe-k8QpJ.VfPpkd-LgbsSe-OWXEXe-dgl2Hf.nCP5yc.AjY5Oe.DuMIQc.qIypjc.TrZEUc"));
		next2.click();
		Thread.sleep(2000);
	}
	
	@Test(priority=2)
	public void bookingTwoWayTrip() throws InterruptedException
	{
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement roundtrip = driver.findElement(By.xpath("//div[@class='minContainer']//div//div//div[1]//ul//li[2]//span[@class='tabsCircle appendRight5']"));
		if(roundtrip.isSelected()==false)
			roundtrip.click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input.react-autosuggest__input.react-autosuggest__input--open")).sendKeys("Tri");
	}
}
