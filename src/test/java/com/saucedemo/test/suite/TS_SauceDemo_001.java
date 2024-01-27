package com.saucedemo.test.suite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

import java.time.Duration;
import java.util.List;

public class TS_SauceDemo_001 {

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
		driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
		//Select all product name
		List<WebElement> lstAllProduct = driver.findElements(By.className("inventory_item_name"));
		String pName = lstAllProduct.get(0).getText();
		System.out.println("Product name: "+pName);
		//select all prices
		List<WebElement> lstAllPrices = driver.findElements(By.className("inventory_item_price"));
		String pPrice = lstAllPrices.get(0).getText();
		System.out.println("Product name: "+pPrice);
		String pNameAfterAdd = driver.findElement(By.className("inventory_item_name")).getText();
		String pPriceAfterAdd = driver.findElement(By.className("inventory_item_price")).getText();
		System.out.println("After adding Product Name; "+ pNameAfterAdd);
		System.out.println("Price after adding: "+ pPriceAfterAdd);
		assertEquals(pName, pNameAfterAdd);
		assertEquals(pPrice,pPriceAfterAdd);
		Thread.sleep(3000);
		driver.findElement(By.className("shopping_cart_link")).click();
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
		String successMsg = driver.findElement(By.className("complete-header")).getText();
		String expectedMsg = "Thank you for your order!";
		assertEquals(successMsg,expectedMsg);

		driver.close();

	}

}
