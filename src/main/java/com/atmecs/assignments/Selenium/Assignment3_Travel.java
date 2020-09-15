package com.atmecs.assignments.Selenium;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Assignment3_Travel 
{
	WebDriver driver;
	
	@BeforeTest
	public void settingAndLaunchingDriver() throws InterruptedException 
	{

		System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.skyscanner.co.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS); 
		
	}
	
	
	  @Test(priority=1) 
	  public void handlingWindowTest() throws InterruptedException 
	  { 
		  WebElement login = driver.findElement(By.xpath("//button[@class='BpkButtonBase_bpk-button__26z7U BpkButtonSecondary_bpk-button--secondary__2JNwP']")); login.click();
	  
	  WebElement gmail = driver.findElement(By.xpath("//button[@class='BpkButtonBase_bpk-button__27cTF BpkButtonSecondary_bpk-button--secondary__2jp0a SocialLoginButton_SocialLoginButton__wideButton__1DpyO']")); 
	  gmail.click();
	  
	  Set<String> ids = driver.getWindowHandles(); 
	  Iterator<String> windows = ids.iterator();
	  String parentWindow = windows.next();
	  String childwindow = windows.next(); 
	  driver.switchTo().window(childwindow);
	  
	  driver.findElement(By.id("identifierId")).sendKeys("shanealfin720@gmail.com");
	  WebElement next1 = driver.findElement(By.cssSelector("button.VfPpkd-LgbsSe.VfPpkd-LgbsSe-OWXEXe-k8QpJ.VfPpkd-LgbsSe-OWXEXe-dgl2Hf.nCP5yc.AjY5Oe.DuMIQc.qIypjc.TrZEUc")); next1.click(); Thread.sleep(2000);
	  
	  WebElement password=driver.findElement(By.xpath("//div[@class='aXBtI I0VJ4d Wic03c']//div//input[@name='password']"));
	  password.sendKeys("alfinxavier.a"); 
	  WebElement next2=driver.findElement(By.xpath("//div[@id='passwordNext']//div//button"));
	  next2.click(); 
	  Thread.sleep(10000);
	  
	  }
	 
	  
	  @Test(priority=0)
	  public void twoWayTripSelectionTest() throws InterruptedException
	  {
		WebElement returntrip = driver.findElement(By.xpath("//input[@id='fsc-trip-type-selector-return']"));
		  if(returntrip.isSelected()==false)
			  returntrip.click();
		  Thread.sleep(1000);
		  
		 WebElement source = driver.findElement(By.xpath("//input[@id='fsc-origin-search']"));
		 source.sendKeys("chennai");
		 Thread.sleep(1000);
		 
		 WebElement destination = driver.findElement(By.xpath("//input[@id='fsc-destination-search']"));
		 destination.sendKeys("bangalore"); 
		 Thread.sleep(1000);
		 
		driver.findElement(By.xpath("//button[@id='depart-fsc-datepicker-button']")).click();
		WebElement departdate = driver.findElement(By.cssSelector("button.BpkCalendarDate_bpk-calendar-date__MuPBz.BpkCalendarDate_bpk-calendar-date--today__3HDEP"));
		departdate.click();
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//button[@id='return-fsc-datepicker-button']")).click();
		List<WebElement> returndate=driver.findElements(By.cssSelector("button.BpkCalendarDate_bpk-calendar-date__MuPBz"));
		int count=returndate.size();
		for(int i=0;i<count;i++)
		{
			String dates=returndate.get(i).getText();
			if(dates.equalsIgnoreCase("29"))
			{
				returndate.get(i).click();
			}
		}
		Thread.sleep(2000);
		
		WebDriverWait wait=new WebDriverWait(driver,5);
		WebElement travellers = driver.findElement(By.xpath("//div[@class='CabinClassTravellersSelector_form-item__2dJHc']//button[@id='CabinClassTravellersSelector_fsc-class-travellers-trigger__1qSiF']"));
		wait.until(ExpectedConditions.elementToBeClickable(travellers));
		travellers.click();
		
		Select cabinclass = new Select(driver.findElement(By.id("search-controls-cabin-class-dropdown")));
		cabinclass.selectByValue("First");
		
		WebElement adult = driver.findElement(By.xpath("//button[@class='BpkButtonBase_bpk-button__1pnhi BpkButtonBase_bpk-button--icon-only__3J3rW BpkButtonSecondary_bpk-button--secondary__1-kNc BpkNudger_bpk-nudger__button__YOZET']"));
		for(int i=1;i<=2;i++)
			adult.click();

		WebElement checkbox = driver.findElement(By.xpath("//input[@name='directOnly']"));
		if(checkbox.isSelected()!=true)
			checkbox.click();
		
		WebElement search = driver.findElement(By.xpath("//button[@class='BpkButtonBase_bpk-button__1pnhi BpkButtonBase_bpk-button--large__24bi- App_submit-button__3OawW App_submit-button-oneline__23Etl']"));
		search.click();
		
	  }
	  
		  @AfterTest 
		  public void closingDriver() 
		  {
			  driver.quit();
		  }
		 
}
