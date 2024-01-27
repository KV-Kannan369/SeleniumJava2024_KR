package com.saucedemo.test.suite;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC001_AddToCart_E2E {

	private ChromeDriver driver;
	private String productName;
	private String price;
	
	@DataProvider
	public Object[][] getProductName(){
		return new Object[][] {
			{"Sauce Labs Bike Light"},
			{"Sauce Labs Bolt T-Shirt"}
		};
		
		
	}

	@BeforeClass
	public void launchSauceDemoAppInChrome() {
		try {
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://www.saucedemo.com/");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		} catch (Exception e) {
			throw new RuntimeException("Unable to lauch Chrome browser due to -->" + e.toString());
		}

	}

	@BeforeMethod
	public void loginIntoSauceDemoApp() {
		try { // Enter Username
			driver.findElement(By.id("user-name")).sendKeys("standard_user");
			// Enter Password
			driver.findElement(By.id("password")).sendKeys("secret_sauce");
			// Enter login button
			driver.findElement(By.id("login-button")).click();
			Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"));

		} catch (Exception e) {
			throw new RuntimeException("Unable to login into the sauce demo site due to -->" + e.toString());
		}
	}

	@Test
	public void shouldAbleToAddFirstProductInAddToCart() {
		// Add first product
		productName = driver.findElement(By.className("inventory_item_name")).getText();
		price = driver.findElement(By.className("inventory_item_price")).getText();
		driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
		driver.findElement(By.className("shopping_cart_link")).click();
		Assert.assertTrue(driver.getCurrentUrl().contains("/cart.html"));
	}

	
	  @Test 
	  public void shouldAbleToAddFirstProductInAddToCart2() throws InterruptedException 
	  { 
		  //Add product
	 // driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
	  //Select all product name 
	  List<WebElement> lstAllProduct = driver.findElements(By.className("inventory_item_name")); 
	  String pName = lstAllProduct.get(0).getText(); System.out.println("Product name: "+pName);
	  //select all prices 
	  List<WebElement> lstAllPrices =
	  driver.findElements(By.className("inventory_item_price")); 
	  String pPrice = lstAllPrices.get(0).getText(); System.out.println("Product name: "+pPrice);
	  String pNameAfterAdd =
	  driver.findElement(By.className("inventory_item_name")).getText(); 
	  String pPriceAfterAdd = driver.findElement(By.className("inventory_item_price")).getText();
	  System.out.println("After adding Product Name; "+ pNameAfterAdd);
	  System.out.println("Price after adding: "+ pPriceAfterAdd);
	  assertEquals(pName, pNameAfterAdd); assertEquals(pPrice,pPriceAfterAdd);
	  Thread.sleep(3000);
	  driver.findElement(By.className("shopping_cart_link")).click();
	  driver.findElement(By.id("checkout")).click(); 
	  // Enter First name
	  driver.findElement(By.id("first-name")).sendKeys("Kannan"); 
	  // Enter Last name 
	  driver.findElement(By.id("last-name")).sendKeys("K.V."); 
	  // Enter postal code 
	  driver.findElement(By.id("postal-code")).sendKeys("603103"); 
	  // Click	  Continue Button 
	  driver.findElement(By.id("continue")).click();
	  
	  driver.findElement(By.id("finish")).click(); String successMsg =
	  driver.findElement(By.className("complete-header")).getText(); String
	  expectedMsg = "Thank you for your order!";
	  assertEquals(successMsg,expectedMsg);
	  }
	  
	  @Test(dataProvider="getProductName")
	  public void shouldAbleToAddGivenProductInAddToCart(String productName)
	  {
		  for(int i=0;i<driver.findElements(By.className("inventory_item_description")).size();i++) {
			price = driver.findElements(By.className("inventory_item_price")).get(i).getText();
			if(driver.findElements(By.className("inventory_item_name")).get(i).getText().equals(productName)) {
				driver.findElements(By.xpath("//button[text()='Add to cart']")).get(i).click();
				break;
			}
			
		  }
		  driver.findElement(By.className("shopping_cart_link")).click();
			Assert.assertTrue(driver.getCurrentUrl().contains("/cart.html"));
			Assert.assertEquals(driver.findElement(By.className("cart_quantity")).getText(), "1");
			Assert.assertEquals(driver.findElement(By.className("inventory_item_name")).getText(), productName);
			Assert.assertEquals(driver.findElement(By.className("inventory_item_price")).getText(), price);
			driver.findElement(By.xpath("//button[starts-with(@id,'remove')]")).click();
			Assert.assertEquals(driver.findElements(By.className("cart_item")).size(), 0);		

	  }
	  
	 
	@AfterMethod
	public void logoutSauceDemoApp() {
		try {
			driver.findElement(By.id("react-burger-menu-btn")).click();
			driver.findElement(By.id("logout_sidebar_link")).click();
		} catch (Exception e) {
			throw new RuntimeException("Unable to logout the saucedemo site due to --> " + e.toString());

		}

	}

	@AfterClass
	public void closeTheChromeBrowser() {
		try {
			driver.close();

		} catch (Exception e) {
			throw new RuntimeException("Unable to close the chrome brower due to --> " + e.toString());
		}

	}

}
