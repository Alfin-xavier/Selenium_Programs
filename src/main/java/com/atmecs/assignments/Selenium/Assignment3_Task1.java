package com.atmecs.assignments.Selenium;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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
		 Thread.sleep(1000); 
		 
	}
	
	  @Test(priority=2)
	  public void bookingTwoWayTrip() throws InterruptedException
	  { 
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS); 
		
		// Checking the RoundTrip
		
		WebElement roundtrip = driver.findElement(By.xpath("//ul[@class='fswTabs latoBlack greyText']//li[2]")); 
		if(roundtrip.isSelected()==false)
			roundtrip.click();
		Thread.sleep(1000);
		
		//From And To
		
		WebElement from = driver.findElement(By.xpath("//input[@id='fromCity']"));
		from.sendKeys("Tri");
		from.sendKeys(Keys.ARROW_DOWN);
		from.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		
		WebElement to=driver.findElement(By.xpath("//*[@id='toCity']"));
		to.sendKeys("Chen");
		to.sendKeys(Keys.ARROW_DOWN);
		to.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		
		//Depature & Return
		
		driver.findElement(By.xpath("//div[@class='fsw_inputBox dates inactiveWidget activeWidget']")).click();
			driver.findElement(By.cssSelector("div.DayPicker-Day.DayPicker-Day--start.DayPicker-Day--selected.DayPicker-Day--today")).click();
	  
		driver.findElement(By.xpath("//div[@class='fsw_inputBox dates reDates inactiveWidget activeWidget shiftModal']")).click();
		List<WebElement> web=driver.findElements(By.cssSelector("td.DayPicker-Day"));
		int count=web.size();
		for(int i=0;i<count;i++)
		{
			String dates=web.get(i).getText();
			if(dates.equalsIgnoreCase("29"))
			{
				web.get(i).click();
			}
		}
		
		//Travellers & classes
		
		driver.findElement(By.id("travellers")).click();
		
		driver.findElement(By.xpath("//div[@class='appendBottom20']//ul//li[@data-cy='adults-2']")).click();
		driver.findElement(By.xpath("//div[@class='makeFlex column childCounter']//ul//li[@data-cy='children-1']")).click();
		driver.findElement(By.xpath("//div[@class='makeFlex column pushRight infantCounter']//ul//li[@data-cy='infants-0']")).click();
	  
		driver.findElement(By.xpath("//ul[@class='guestCounter classSelect font12 darkText']//li[2]")).click();
		driver.findElement(By.xpath("//button[@class='primaryBtn btnApply pushRight ']")).click();
		
		//Clicking the Search Button
		
		driver.findElement(By.className("primaryBtn font24 latoBold widgetSearchBtn ")).click();
	  }
	 
	
	
	  @AfterTest 
	  public void closingDriver()
	  { 
		  driver.close(); 
	  }
	 
}
