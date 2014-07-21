package com.rozetka.pages;

import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Iryna_Vintonyk
 *
 */

public class RozetkaHomePage {

	private final static Logger log = LoggerFactory.getLogger(RozetkaHomePage.class);
	public WebDriver driver;
	private static final int TIMEOUT = 3000;
	
	private By tab_phones_and_mp3 = By.xpath("//td[@id='phones-mp3-gps']/div/a");
	private By section_mp3 = By.xpath("//div[5]/div/div/div/h3/a");
	private By tab_kids_world = By.xpath("//td[@id='kids']/div/a");
	private By section_kids_toys = By.xpath("//div[5]/div/ul/li[2]/a");
	
	public RozetkaHomePage (WebDriver driver) {
		this.driver = driver;
	}
	
	public void open(String url) throws Exception {
		log.info("Test has started");
		driver.get(url);
		Thread.sleep(TIMEOUT);
		
	}
	
	public RozetkaBuyItems chooseProductTab () throws InterruptedException {
		try {
			driver.findElement(tab_phones_and_mp3).click();
			Thread.sleep(TIMEOUT);
		} catch (NoSuchElementException e) {
			log.error("Unable to locate element: " + tab_phones_and_mp3);
		}
		
		try {
			driver.findElement(section_mp3).click();
			Thread.sleep(TIMEOUT);
		} catch (NoSuchElementException e) {
			log.error("Unable to locate element: " + section_mp3);
		}
		return new RozetkaBuyItems(driver);
		
	}
	
	public RozetkaBuyItems selectTabKidsWorld () throws InterruptedException {
		try{
			driver.findElement(tab_kids_world).click();
			Thread.sleep(TIMEOUT);
		} catch (NoSuchElementException e) {
			log.error("Unable to locate element: " + tab_kids_world);
		}
		
		try{
			driver.findElement(section_kids_toys).click();
			Thread.sleep(TIMEOUT);
		} catch (NoSuchElementException e) {
			log.error("Unable to locate element: " + section_kids_toys);
		}
		return new RozetkaBuyItems(driver);
	}

}
