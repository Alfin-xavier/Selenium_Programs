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
	  public void bookingTwoWayTrip() throws InterruptedException
	  { 
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); 
		
		// Checking the RoundTrip
		
		WebElement roundtrip = driver.findElement(By.xpath("//*[@id=\'root\']/div/div[2]/div/div/div[1]/ul/li[2]/span")); 
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
