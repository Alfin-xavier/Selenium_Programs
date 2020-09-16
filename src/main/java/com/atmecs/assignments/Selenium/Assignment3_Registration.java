package com.atmecs.assignments.Selenium;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.*;

public class Assignment3_Registration 
{
	WebDriver driver;
	@BeforeTest
	public void settingAndLaunchingDriver() 
	{

		System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://demo.automationtesting.in/Register.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	}
	
	@Test(priority=1)
	public void registerationTest()
	{
		//textBox
		driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys("Alfin");
		driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys("Xavier");
		driver.findElement(By.xpath("//textarea[@ng-model='Adress']")).sendKeys("136/3A Rc church st");
		driver.findElement(By.xpath("//input[@ng-model='EmailAdress']")).sendKeys("shanealfin720@gmail.com");
		driver.findElement(By.xpath("//input[@ng-model='Phone']")).sendKeys("9629283359");
		
		//Radio button
		WebElement radiobutton=driver.findElement(By.xpath("//div[@class='form-group']//div//label[2]//input"));
		if(radiobutton.isSelected()==false)
			radiobutton.click();
		
		//CheckBox
		WebElement checkbox=driver.findElement(By.xpath("//div[@class='form-group']//div//div[2]//input[@id='checkbox2']"));
		if(checkbox.isSelected()==false)
			checkbox.click();
		driver.findElement(By.id("msdd")).click();
		for(int i=1;i<=3;i++)
			driver.findElement(By.xpath("//multi-select//div//ul")).click();
		
		//Select Dropdowns
		Select skills=new Select(driver.findElement(By.id("Skills")));
		skills.selectByValue("PHP");
		
		Select countries=new Select(driver.findElement(By.id("countries")));
		countries.selectByVisibleText("India");
		
		WebElement selectcountry=driver.findElement(By.xpath("//span[@aria-labelledby='select2-country-container']"));
		selectcountry.click();
		for(int i=1;i<=5;i++)
			selectcountry.sendKeys(Keys.ARROW_DOWN);
		selectcountry.sendKeys(Keys.ENTER);
		
		//Selecting DOB
		Select year=new Select(driver.findElement(By.id("yearbox")));
		year.selectByValue("1996");
		Select month=new Select(driver.findElement(By.xpath("//select[@placeholder='Month']")));
		month.selectByVisibleText("August");
		Select date=new Select(driver.findElement(By.id("daybox")));
		date.selectByIndex(11);
		
		//Passwords
		driver.findElement(By.id("firstpassword")).sendKeys("Alfin@118");
		driver.findElement(By.id("secondpassword")).sendKeys("Alfin@118");
		
		//Registering details by clicking submit button
		WebDriverWait wait=new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("submitbtn"))));
		driver.findElement(By.id("submitbtn")).click();
	}
	
	@Test(priority=2) // Handling child window
	public void windowsHandling()
	{
	
		WebElement subdriver = driver.findElement(By.id("footer"));
		System.out.println(subdriver.findElements(By.tagName("a")).size());
		
		WebElement subdriver1 = subdriver.findElement(By.xpath("//div[@class='col-md-6 col-xs-6 col-sm-6 social pull-right']"));
		for(int i=0;i<subdriver1.findElements(By.tagName("a")).size();i++)
		{
			String link=Keys.chord(Keys.CONTROL,Keys.ENTER);
			subdriver1.findElements(By.tagName("a")).get(i).sendKeys(link);
		}
		
		//switching taps
		Set<String> windows=driver.getWindowHandles();
		Iterator<String> itr=windows.iterator();
		//get the title of each tap
		while(itr.hasNext())
		{
			driver.switchTo().window(itr.next());
			System.out.println(driver.getTitle());
		}
		
	}
	
	@AfterTest
	public void closingDriver()
	{
		driver.quit();
	}
}
