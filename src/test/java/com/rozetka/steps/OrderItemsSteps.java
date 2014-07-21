package com.rozetka.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;
import org.jbehave.core.annotations.Then;
import com.rozetka.main.BaseWebdriverActions;
import com.rozetka.pages.RozetkaBuyItems;
import com.rozetka.pages.RozetkaHomePage;

/**
 * @author Iryna_Vintonyk
 *
 */

public class OrderItemsSteps extends BaseWebdriverActions{
	
	public OrderItemsSteps () throws Exception {
		super ();
	}
	
	private RozetkaHomePage homepage;
	private RozetkaBuyItems buyItems;
		
	@Given ("product page $url")
	public void openHomePage (String url) throws Exception {
		homepage = new RozetkaHomePage (this.driver);
		homepage.open(url);
	}
	
	@Given ("I add the items into the cart by pressing the button-Buy")
	public void addingItemsIntoTheCart () throws InterruptedException {
		buyItems = homepage.chooseProductTab();
		buyItems.selectItem();
		buyItems.buyItem();
	}
	
	@Given ("I add the item into the cart using tab - Детский мир")
	public void addItemsFromTabKidsWorld () throws InterruptedException {
		buyItems = homepage.selectTabKidsWorld();
		buyItems.buySelectedItemFromKidsTab();
	}
	
	@When ("the total price of items in the cart is less than 1500 UAH")
	public void assertTheTotalPriceIsLessThen1500 () throws Exception {
		buyItems.assertWhenTotalPricelessThen1500();
	}
	
	@Then ("the delivery cost is 35 UAH")
	public void assertDeliveryCost () throws InterruptedException {
		buyItems.assertDeliveryCostOfAddingItems();
	}
	
	@When ("the total price of items in the cart more than or equals to 1500 UAH")
	public void assertTotalPriceIsMoreThen1500 () throws InterruptedException {
		buyItems.assertWhenTotalPriceMoreThen1500();
	}
	
	@Then ("the delivery cost is free")
	public void assertDeliveryCostIsFree () throws InterruptedException {
		buyItems.assertWhenDeliveryCostIsFree();
	}
	
	@When ("the total price of items in the cart more than 20000 UAH")
	public void assertTotalPriceIsMoreThen20000 () throws InterruptedException {
		buyItems.assertWhenTotalPriceIsMoreThen20000();
	}
	
	@Then ("I get additional item as a present")
	public void assertAddingFreeItem () throws InterruptedException {
		buyItems.assertThePresenceOfAdditionalItem();
	}
	
	@When ("the total price of items in the cart is more then 500 UAH")
	public void assertTotalPriceIsMoreThen500 () throws InterruptedException {
		buyItems.assertWhenTotalPriceIsMoreThen500();
	}
	
	
}
