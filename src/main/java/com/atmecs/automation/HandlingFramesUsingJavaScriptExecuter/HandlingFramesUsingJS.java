// Handling frames using javascript

package com.atmecs.automation.HandlingFramesUsingJavaScriptExecuter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

public class HandlingFramesUsingJS {
	WebDriver driver;
	JavascriptExecutor js;
	Properties properties;
	HelperClass helperclass;

	@BeforeTest
	public void settingAndLaunchingDriver() throws InterruptedException, IOException 
	{

		System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		dataProvider();
		helperclass = new HelperClass(driver);
		driver.get(properties.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	@Test
	public void handlingFrameAndClickChatIconTest() throws InterruptedException, IOException
	{
		dataProvider();

		Wait<WebDriver> wait = new WebDriverWait(driver, 30);

		WebElement frame = driver.findElement(By.id(properties.getProperty("frame")));

		wait.until(ExpectedConditions.elementToBeClickable(frame));

		driver.switchTo().frame(frame);

		js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollBy(0,1000)");

		WebElement chaticon = driver.findElement(By.xpath(properties.getProperty("chaticon")));
		js.executeScript("arguments[0].click();", chaticon);

		helperclass.textDatas(properties.getProperty("username"), properties.getProperty("name"));

		helperclass.textDatas(properties.getProperty("mob"), properties.getProperty("number"));

		helperclass.textDatas(properties.getProperty("usermail"), properties.getProperty("gmail"));

		driver.findElement(By.xpath(properties.getProperty("checkbox"))).click();

		helperclass.selectValueFromDropDown(properties.getProperty("client"), "index1_1");

		WebElement button = driver.findElement(By.xpath(properties.getProperty("button")));

		js.executeScript("arguments[0].click();", button);

	}

	public void dataProvider() throws IOException 
	{
		FileInputStream file = new FileInputStream(
				System.getProperty("user.dir") + "\\locatorsAndTestDatas\\assignmnet4.properties");
		properties = new Properties();
		properties.load(file);
	}

	@AfterTest
	public void closingDriver() {
		driver.close();
	}
}
