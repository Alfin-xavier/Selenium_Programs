// Filling Registration form and clicking all the social Icons and Handling Those Windows

package com.atmecs.automation.selenium.Assignments;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Assignment3_RegistrationFormFilling 
{
	WebDriver driver;
	Properties properties;
	@BeforeTest
	public void settingAndLaunchingDriver() throws IOException 
	{
		
		System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		readDatas();
		driver.get(properties.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	}
	
	@Test(priority=1)
	public void registerationTest()
	{
		//textBox
		WebElement firstName = driver.findElement(By.xpath(properties.getProperty("firstname")));
		firstName.sendKeys(properties.getProperty("fname"));
		
		WebElement lastName = driver.findElement(By.xpath(properties.getProperty("lastname")));
		lastName.sendKeys(properties.getProperty("lname"));
		
		WebElement address = driver.findElement(By.xpath(properties.getProperty("address")));
		address.sendKeys(properties.getProperty("addressvalue"));
		
		WebElement gmail = driver.findElement(By.xpath(properties.getProperty("gmail")));
		gmail.sendKeys(properties.getProperty("mail"));
		
		WebElement mob = driver.findElement(By.xpath(properties.getProperty("mob")));
		mob.sendKeys(properties.getProperty("number"));
		
		//Radio button
		WebElement gender=driver.findElement(By.xpath(properties.getProperty("gender")));
		if(gender.isSelected()==false)
			gender.click();
		
		//CheckBox
		WebElement hobbies=driver.findElement(By.xpath(properties.getProperty("hobbies")));
		if(hobbies.isSelected()==false)
			hobbies.click();
		
		WebElement language = driver.findElement(By.id(properties.getProperty("language")));
		language.click();
		for(int i=1;i<=4;i++)
			driver.findElement(By.xpath(properties.getProperty("multiselect"))).click();
		
		//Select Dropdowns
		Select skills = new Select(driver.findElement(By.id(properties.getProperty("skills"))));
		skills.selectByValue("PHP");
		
		Select countries = new Select(driver.findElement(By.id(properties.getProperty("countries"))));
		countries.selectByVisibleText("India");
		
		WebElement selectCountry=driver.findElement(By.xpath(properties.getProperty("selectcountry")));
		selectCountry.click();
		for(int i=1;i<=5;i++)
			selectCountry.sendKeys(Keys.ARROW_DOWN);
		selectCountry.sendKeys(Keys.ENTER);
		
		//Selecting DOB
		Select year=new Select(driver.findElement(By.id(properties.getProperty("year"))));
		year.selectByValue("1996");
		
		Select month=new Select(driver.findElement(By.xpath(properties.getProperty("month"))));
		month.selectByVisibleText("August");
		
		Select date=new Select(driver.findElement(By.id(properties.getProperty("date"))));
		date.selectByIndex(11);
		
		//Passwords
		WebElement password = driver.findElement(By.id(properties.getProperty("password")));
		password.sendKeys(properties.getProperty("passvalue1"));
		
		WebElement confirmpass = driver.findElement(By.id(properties.getProperty("confirmpass")));
		confirmpass.sendKeys(properties.getProperty("passvalue2"));
		
		//Registering details by clicking submit button
		WebDriverWait wait=new WebDriverWait(driver,5);
		WebElement submit = driver.findElement(By.id(properties.getProperty("submit")));
		wait.until(ExpectedConditions.elementToBeClickable(submit));
		submit.click();
	}
	
	@Test(priority=2) // Handling windows
	public void windowsHandling() throws IOException
	{
		
		WebElement subdriver = driver.findElement(By.id(properties.getProperty("footer")));
		
		WebElement subdriver1 = subdriver.findElement(By.xpath(properties.getProperty("icons")));
		
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
	
	 public void readDatas() throws IOException
		{
			File file = new File("C:\\Users\\alfin.anthonyraj\\eclipse-workspace\\Selenium\\locatorsAndTestDatas\\registration.properties");
			  
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
