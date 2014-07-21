package com.rozetka.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Iryna_Vintonyk
 *
 */

public class RozetkaLoginPage {
	
	public WebDriver driver;
	
	private By sign_in = By.name("signin");
	private By login = By.name("login");
	private By pasword = By.name("password");
	private By submitButton = By.xpath("//button[@type='submit']");

	public void logIn(String username, String password) throws Exception  {
		driver.findElement (sign_in).click();
		driver.findElement(login).sendKeys(username);
		driver.findElement (pasword).sendKeys(password);
		driver.findElement(submitButton).click();
		Thread.sleep(10000);
	}

}
