package com.saucedemo.test.suite;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

import java.time.Duration;

public class LoginDemo {

	@Test
	public void login() throws InterruptedException {

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
		//Click Menu and Logout
		driver.findElement(By.id("react-burger-menu-btn")).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("logout_sidebar_link"))));
		driver.findElement(By.id("logout_sidebar_link")).click();
		//Validate that logout successful - Login should be enabled
		boolean loginEnabled = driver.findElement(By.id("login-button")).isEnabled();
		assertTrue(loginEnabled);
		driver.close();

	}

}
