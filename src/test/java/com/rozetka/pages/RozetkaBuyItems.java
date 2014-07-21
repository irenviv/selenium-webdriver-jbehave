package com.rozetka.pages;

import java.util.NoSuchElementException;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Iryna_Vintonyk
 *
 */

public class RozetkaBuyItems {

	private final static Logger log = LoggerFactory.getLogger(RozetkaBuyItems.class);
	private WebDriver driver;
	
	private static final int TIMEOUT = 5000;
	private static final int ORDER_SUM = 1500;
	private static final int SUM = 20000;
	private static final int TOTAL_PRICE = 500;
	private static final String CUSTOMER_NAME = "Virina"; 
	private static final String CUSTOMER_PHONE = "044-509-12-34";
	private static final String CUSTOMER_EMAIL = "19vinni87@gmail.com";
	
	private By first_item_in_mp3_section = By.xpath(".//*[@id='block_with_goods']/div[1]/div[1]/div[1]/div[1]/div/div/div[3]/a");
	private By buyButton = By.xpath("//button[@name='topurchases']");
	private By deleteButton = By.cssSelector(".cart-i-remove-icon");
	private By closeCart = By.cssSelector(".popup-close-icon");
	private By return_to_mp3_section = By.xpath("//span[3]/a/span");
	private By courierTab = By.xpath("//div[2]/ul/li[2]/a");
	private By delivery_cost = By.xpath("//aside[2]/div/div[2]/div[2]");
	private By checkOut = By.id("popup-checkout");
	private By customer_name = By.id("reciever_name");
	private By customer_locality = By.id("suggest_locality");
	private By first_locality_in_list = By.name("0");
	private By customer_phone = By.id("reciever_phone");
	private By customer_email = By.id("reciever_email");
	private By nextStepButton = By.name("next_step");
	private By quantityOfItems = By.name("quantity");
	private By totalPriceOfChoosenItems = By.name("cost");
	private By total_quantity = By.xpath("//aside[2]/div/div/div");
	private By first_item_in_kidsToys_section = By.xpath(".//*[@id='block_with_goods']/div[1]/div[1]/div[1]/div[1]/div/div/div[2]/a");

	public RozetkaBuyItems(WebDriver driver) {
		this.driver = driver;
	}

	public void selectItem() throws InterruptedException {
		try {
			driver.findElement(first_item_in_mp3_section).click();
			Thread.sleep(TIMEOUT);
		} catch (NoSuchElementException e) {
			log.error("Unable to locate element: " + first_item_in_mp3_section);
		}
	}

	public void buyItem() throws InterruptedException {
		try {
			driver.findElement(buyButton).click();
			Thread.sleep(TIMEOUT);
		} catch (NoSuchElementException e) {
			log.error("Unable to locate element: " + buyButton);
		}
	}
	
	public void assertWhenTotalPricelessThen1500() throws Exception {
		int price = getTotalPrice();
		while (price > ORDER_SUM ) {
			driver.findElement(deleteButton).click();
			driver.findElement(closeCart).click();
			driver.findElement(return_to_mp3_section).click();
		}
		userRegistration();
	}		
	
	public void assertDeliveryCostOfAddingItems () throws InterruptedException {
		try {
			driver.findElement(courierTab).click();
			Thread.sleep(TIMEOUT);
		}catch (NoSuchElementException e) {
			log.error("Unable to locate courierTab");
		}
		String deliveryCost = driver.findElement(delivery_cost).getText();
		log.info("get delivery cost = " + deliveryCost);
		String getCost = deliveryCost.replaceAll("[^0-9]", "");
		log.info("get cost = " + getCost);
		int cost = Integer.parseInt(getCost);
		try {
			Assert.assertEquals(35, cost);
		} catch (Exception e) {
			log.error("Assertion error");
		}
	}	
	
	public void assertWhenTotalPriceMoreThen1500 () throws InterruptedException {
		int productAmount = 1;
		int price = 0;
		recountAmountofItems(productAmount);
		while (price < ORDER_SUM) {
			recountAmountofItems(productAmount);
			price = getTotalPrice();
			productAmount++;
		}
		userRegistration ();
	}
	
	private void userRegistration() throws InterruptedException {
		try {
			driver.findElement(checkOut).click();
			Thread.sleep(TIMEOUT);
		} catch (NoSuchElementException e) {
			log.error("Unable to locate element " + checkOut);
		}
		
		try {
			driver.findElement(customer_name).sendKeys(CUSTOMER_NAME);
		} catch (NoSuchElementException e) {
			log.error("Unable to locate element " + customer_name);
		}
		
		try {
			driver.findElement(customer_locality).click();
		} catch (NoSuchElementException e) {
			log.error("Unable to locate element " + customer_locality);
		}
		
		try {
			driver.findElement(first_locality_in_list).click();
		} catch (NoSuchElementException e) {
			log.error("Unable to locate element " + first_locality_in_list);
		}
		
		try {
			driver.findElement(customer_phone).sendKeys(CUSTOMER_PHONE);
		} catch (NoSuchElementException e) {
			log.error("Unable to locate element " + customer_phone);
		}
		
		try {
			driver.findElement(customer_email).sendKeys(CUSTOMER_EMAIL);
		} catch (NoSuchElementException e) {
			log.error("Unable to locate element " + customer_email);
		}
		
		try {
			driver.findElement(nextStepButton).click();
			Thread.sleep(TIMEOUT);
		} catch (NoSuchElementException e) {
			log.error("Unable to locate element " + nextStepButton);
		}
	}	
	
	private void recountAmountofItems(int productAmount) throws InterruptedException {
		try {
			driver.findElement(quantityOfItems).clear();
		}catch (NoSuchElementException e) {
			log.error("Unable to clear " + quantityOfItems);
		}
		
		try {
			driver.findElement(quantityOfItems).sendKeys(String.valueOf(productAmount));
		} catch (NoSuchElementException e) {
			log.error("Unable to input product amount");
		}
		
		try {
			driver.findElement(totalPriceOfChoosenItems).click();
			Thread.sleep(TIMEOUT);
		} catch (NoSuchElementException e) {
			log.error("Unable to locate element " + totalPriceOfChoosenItems);
		}
	}
		
	private int getTotalPrice() {
		String totalPrice = "";
		try {
			totalPrice = driver.findElement(totalPriceOfChoosenItems).getText();
		} catch (NoSuchElementException e) {
			log.error("Unable to locate element " + totalPriceOfChoosenItems);
		}
		String getPrice = totalPrice.replaceAll(" ", "").replaceAll("[^0-9]", "");
		int price = Integer.parseInt(getPrice);
		return price;
	}

	public void assertWhenDeliveryCostIsFree () throws InterruptedException {
		try {
			driver.findElement(courierTab).click();
			Thread.sleep(TIMEOUT);
		} catch (NoSuchElementException e) {
			log.error("Unable to locate element " + courierTab);
		}
		String deliveryCost = driver.findElement(delivery_cost).getText();
		log.info("delivery cost  is " + deliveryCost);
		
		try {
		Assert.assertEquals("бесплатно", deliveryCost);
		} catch (NoSuchElementException e) {
			log.error("assertion has failed");
		}
	}
	
	public void assertWhenTotalPriceIsMoreThen20000 () throws InterruptedException {
		int price = getTotalPrice();
		int productAmount = 10;
		while (price < SUM) {
			recountAmountofItems(productAmount);
			price = getTotalPrice();
			productAmount++;
		}	
		userRegistration();
	}
	
	public void assertThePresenceOfAdditionalItem () throws InterruptedException {
		String totalQuantity = "";
		try {
		totalQuantity = driver.findElement(total_quantity).getText();
		} catch (NoSuchElementException e) {
			log.error("can't find the total quantity of items");
		}
		log.info("total quantity - " + totalQuantity);
		String getItemsQuantity = totalQuantity.replaceAll("[^0-9]", "");
		log.info("get items quantity - " + getItemsQuantity);
		int quantity = Integer.parseInt(getItemsQuantity);
		Thread.sleep(TIMEOUT);
		
		try {
			Assert.assertNotSame(quantity+1, quantity);
		} catch (NoSuchElementException e) {
			log.error("assertion has failed");
		}
	}	
		
	public void buySelectedItemFromKidsTab () throws InterruptedException {
		try {
			driver.findElement(first_item_in_kidsToys_section).click();
		} catch (NoSuchElementException e) {
			log.error("Unable to locate element " + first_item_in_kidsToys_section);
		}
		
		try {
			driver.findElement(buyButton).click();
			Thread.sleep(TIMEOUT);
		} catch (NoSuchElementException e) {
			log.error("Unable to locate element " + buyButton);
		}
	}
	
	public void assertWhenTotalPriceIsMoreThen500 () throws InterruptedException {
		int price = getTotalPrice();
		int productAmount = 1;
		recountAmountofItems(productAmount);
		while (price < TOTAL_PRICE) {
			recountAmountofItems(productAmount);
			price = getTotalPrice();
			productAmount++;
		}	
		userRegistration();
	}
	
}
 


