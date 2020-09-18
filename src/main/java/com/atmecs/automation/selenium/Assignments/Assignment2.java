// Adding product on the cart and checking whether the product has been added or not on www.demoblaze.com

package com.atmecs.automation.selenium.Assignments;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class Assignment2
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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	
	  @Test(priority = 1) 
	  public void signUpTest() throws InterruptedException
	  {
		  
		  WebElement signin = driver.findElement(By.id(properties.getProperty("signin")));
		  signin.click();
		  
		  WebElement susername = driver.findElement(By.id(properties.getProperty("signinusername")));
		  susername.sendKeys(properties.getProperty("sname"));
		  
		  WebElement spassword = driver.findElement(By.id(properties.getProperty("signinpassword")));
		  spassword.sendKeys(properties.getProperty("spass"));
		  
		  WebElement button1 = driver.findElement(By.xpath(properties.getProperty("button1")));
		  button1.click();
		  Thread.sleep(2000);
		  
		  driver.switchTo().alert().accept();
		  Thread.sleep(1000); 
		  
	  }
	 
	  @Test(priority = 2) 
	  public void logInTest() throws InterruptedException, IOException 
	  {
		  	  
		  WebElement login = driver.findElement(By.id(properties.getProperty("login")));
		  login.click();
		  
		  WebElement lusername = driver.findElement(By.id(properties.getProperty("lusername")));
		  lusername.sendKeys(properties.getProperty("lname"));
		  
		  WebElement lpassword = driver.findElement(By.id(properties.getProperty("lpassword")));
		  lpassword.sendKeys(properties.getProperty("lpass"));
		  
		  WebElement button2 = driver.findElement(By.xpath(properties.getProperty("button2")));
		  button2.click();
		  Thread.sleep(2000);
		  
	  }
	  
	@Test(priority = 3) 
	public void selectAndAddToCartProduct() throws InterruptedException 
	{ 
		  
		  
		  WebElement selectProduct1 = driver.findElement(By.xpath(properties.getProperty("selectProduct1")));
		  selectProduct1.click();
		  
		  WebElement addProduct1 = driver.findElement(By.cssSelector(properties.getProperty("addProduct1")));
		  addProduct1.click();
		  Thread.sleep(2000);

		  driver.switchTo().alert().accept();
		  Thread.sleep(2000);
		  
		  // click the cart to check whether the product added or not
		  WebElement clickCart = driver.findElement(By.xpath(properties.getProperty("clickCart")));
		  clickCart.click(); 
		  Thread.sleep(2000);
		 }
	 
	@Test(priority=4)
	public void contactUsScript() throws InterruptedException 
	{
			
		WebElement contactUs = driver.findElement(By.xpath(properties.getProperty("contactUs")));
		contactUs.click();
		
		WebElement recipientMail = driver.findElement(By.id(properties.getProperty("recipientMail")));
		recipientMail.sendKeys(properties.getProperty("mailValue"));
		
		WebElement recipientName = driver.findElement(By.id(properties.getProperty("recipientName")));
		recipientName.sendKeys(properties.getProperty("nameValue"));
		
		WebElement message = driver.findElement(By.id(properties.getProperty("message")));
		message.sendKeys(properties.getProperty("messageValue"));
		
		WebDriverWait w= new WebDriverWait(driver, 5);
		WebElement button=driver.findElement(By.xpath(properties.getProperty("button3")));
		w.until(ExpectedConditions.elementToBeClickable(button));
		button.click();
		Thread.sleep(1000);
		
		driver.switchTo().alert().accept();
		Thread.sleep(1000); 
	}
	
	 public void readDatas() throws IOException
		{
			File file = new File("C:\\Users\\alfin.anthonyraj\\eclipse-workspace\\Selenium\\locatorsAndTestDatas\\assignment2.properties");
			  
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
	public void closeDriver()
	{
		driver.close();
	}
}
