Narrative:
In order to buy goods in the online shop
As a user
I want to have an ability to add items into the cart
					 
Scenario: test when delivery cost is 35 UAH 
Given product page https://www.rozetka.com.ua
And I add the items into the cart by pressing the button-Buy
When the total price of items in the cart is less than 1500 UAH
Then the delivery cost is 35 UAH

Scenario: test when delivery cost is free 
Given product page https://www.rozetka.com.ua
And I add the items into the cart by pressing the button-Buy
When the total price of items in the cart more than or equals to 1500 UAH
Then the delivery cost is free

Scenario: get additional item as a present
Given product page https://www.rozetka.com.ua
And I add the items into the cart by pressing the button-Buy
When the total price of items in the cart more than 20000 UAH
Then I get additional item as a present

Scenario: test when delivery is free, choosing the item from the tab "Детский мир"
Given product page https://www.rozetka.com.ua
And I add the item into the cart using tab - Детский мир
When the total price of items in the cart is more then 500 UAH
Then the delivery cost is free

Scenario: 
 