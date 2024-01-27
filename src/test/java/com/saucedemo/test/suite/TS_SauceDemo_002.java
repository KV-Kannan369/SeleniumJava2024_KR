package com.saucedemo.test.suite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;


public class TS_SauceDemo_002 {

	@Test
	public void setup() throws InterruptedException {

		// Browser launching
		ChromeDriver driver = new ChromeDriver();
		// Browser maximizing
		driver.manage().window().maximize();
		// URL loading
		driver.get("https://www.saucedemo.com/");
		// Enter User name
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		// Enter Password
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		// Enter login button
		driver.findElement(By.id("login-button")).click();
		// Validate the current URL
		String currentURL = driver.getCurrentUrl();
		assertTrue(currentURL.contains("inventory.html"));
		//Add product
		//Select all product name
		List<WebElement> lstAllProduct = driver.findElements(By.className("inventory_item_name"));
		//select all prices
		List<WebElement> lstAllPrices = driver.findElements(By.className("inventory_item_price"));
		String prodName = null;
		String prodPrice = null;
		for(int i=0;i<lstAllProduct.size();i++)
		{
			prodName = lstAllProduct.get(i).getText();
			prodPrice = lstAllPrices.get(i).getText();
			if(prodName.equalsIgnoreCase("Sauce Labs Bolt T-Shirt"))
			{
				
				driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
				break;
			}
		}
		driver.findElement(By.className("shopping_cart_link")).click();
		//validate the product 
		String finalProd = driver.findElement(By.className("inventory_item_name")).getText();
		System.out.println("Final Product: "+finalProd);
		String finalPrice = driver.findElement(By.className("inventory_item_price")).getText();
		System.out.println("Final Product: "+finalPrice);
		Thread.sleep(3000);
		
		driver.findElement(By.id("checkout")).click();
		// Enter First name
		driver.findElement(By.id("first-name")).sendKeys("Kannan");
		// Enter Last name
		driver.findElement(By.id("last-name")).sendKeys("K.V.");
		// Enter postal code
		driver.findElement(By.id("postal-code")).sendKeys("603103");
		// Click Continue Button
		driver.findElement(By.id("continue")).click();
		
		driver.findElement(By.id("finish")).click();
		assertEquals(prodName, finalProd);
		assertEquals(prodPrice,finalPrice);
		
		String successMsg = driver.findElement(By.className("complete-header")).getText();
		String expectedMsg = "Thank you for your order!";
		assertEquals(successMsg,expectedMsg);

		driver.close();
		

	}

}
