package com.Utility;

import java.io.IOException;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks {
	static WebDriver driver=null;
	
	@Before
	public void setUpdriver() throws IOException {
		System.out.println("Started");
		if(Utilities.getProperty("browser").equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
		}
		else if(Utilities.getProperty("browser").equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		else if(Utilities.getProperty("browser").equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else {
			Assert.fail("Failing the execution as you pass the incorrect browser ! Note this framework only accept chrome,edge and firefox browser");
		}
		Utilities.driver = driver;
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
	
	
}
