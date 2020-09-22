package com.atmecs.automation.HandlingFramesUsingJavaScriptExecuter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class HelperClass 
{
	WebDriver driver;
	public HelperClass(WebDriver driver)
	{
		this.driver = driver;
	}
	public void textDatas(String xpath, String values)
	{
		WebElement textbox = driver.findElement(By.xpath(xpath));
		textbox.sendKeys(values);
	}
	public void selectValueFromDropDown(String xpath, String values)
	{
		Select selectValue = new Select(driver.findElement(By.xpath(xpath)));
		selectValue.selectByValue(values);
	}
	public void button(String xpath)
	{
		driver.findElement(By.xpath(xpath));
	}
}
