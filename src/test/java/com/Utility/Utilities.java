package com.Utility;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilities {
	public static WebDriver driver = null;
	public static WebDriver getDriver() {
		return driver;
	}
	
	public static String getProperty(String key){
		Properties prop= new Properties();
		try {
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/test/resources/config.properties");
			prop.load(ip);
		}
		catch (Exception e) {
			System.out.println("Failed to load property! Where Key = "+key);
		}
		
		return prop.getProperty(key);
		
	}
	public static void waitforElementLocated(By ele) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
	}
	
	public void enterDetails(String xpath, String text) {
		waitforElementLocated(By.xpath(xpath));
		WebElement ele=getDriver().findElement(By.xpath(xpath));
		ele.clear();
		ele.sendKeys(text);
	}
	public void clickOnButton(String xpath) {
		waitforElementLocated(By.xpath(xpath));
		WebElement ele=getDriver().findElement(By.xpath(xpath));
		ele.click();
	}
	
	public void waitForABit(int sec) {
		try {
			Thread.sleep(sec*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getFieldText(String xpath) {
		waitforElementLocated(By.xpath(xpath));
		WebElement ele=getDriver().findElement(By.xpath(xpath));
		return ele.getText();
	}
}
