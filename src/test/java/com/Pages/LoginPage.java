package com.Pages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.Utility.Utilities;



public class LoginPage extends Utilities {
	private static HashMap<String,String> pageMap;
	static {
		pageMap=new HashMap<String, String>();
		pageMap.put("usernamexpath","//input[@name='username']");
		pageMap.put("passwordxpath","//input[@name='password']");
		pageMap.put("loginBtn","//button[@type='submit']");
		pageMap.put("profile","//img[@class='oxd-userdropdown-img']");
		pageMap.put("LogoutBtn", "//a[text()='Logout']");
		
	}
	public void launchTheApplication() {
		String appUrl = Utilities.getProperty("url");
		Utilities.getDriver().get(appUrl);
	}
	
	public void enterUserName(String username) {
		enterDetails(pageMap.get("usernamexpath"),username);
	}

	public void enterPassword(String password) {
	   enterDetails(pageMap.get("passwordxpath"),password);
	}

	public void clickOnLogInButton() throws InterruptedException {
		clickOnButton(pageMap.get("loginBtn"));
	}

	public boolean checkForProfileImage() {
		waitforElementLocated(By.xpath(pageMap.get("profile")));
		WebElement profileImage=getDriver().findElement(By.xpath(pageMap.get("profile")));
		return profileImage.isDisplayed();
	}

	public void logout() {
		clickOnButton(pageMap.get("profile"));
		clickOnButton(pageMap.get("LogoutBtn"));
	}

	public boolean checkForLogInpage() {
		waitforElementLocated(By.xpath(pageMap.get("loginBtn")));
		WebElement loginButton=getDriver().findElement(By.xpath(pageMap.get("loginBtn")));
		return loginButton.isDisplayed();
	}

	
	
}
