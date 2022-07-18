package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveTests {

	@Test
	public void loginTest() {
		System.out.println("Starting loginTest");

		// Create driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
		WebDriver driver = new ChromeDriver();

		// maximise browser window
		driver.manage().window().maximize();

//		open test page
		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);
		System.out.println("Page is opened");

		// add wait time
		sleep(1000);

//		input username
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");

//		input password
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("SuperSecretPassword!");

//		click login
		/* WebElement loginButton = driver.findElement(By.xpath("username")); */
		WebElement logInButton = driver.findElement(By.tagName("button"));
		logInButton.click();
		sleep(3000);
//
//		verifications:
//			url contains /secure endpoint
		String expectedUrl = "http://the-internet.herokuapp.com/secure";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual page URL is not the same as expected");

//			logout button should be visible
		WebElement logOutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
		Assert.assertTrue(logOutButton.isDisplayed(), "LogOut button is not visible");

//			successful login message
		WebElement successMessage = driver.findElement(By.cssSelector("div#flash"));
		String expectedMessage = "You logged into a secure area!";
		String actualMessage = successMessage.getText();
		//Assert.assertEquals(actualMessage, expectedMessage, "Actual message is not the same as expected");
		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Actual message does not contain expected.\nActual message: " + actualMessage + "\nExpected message: "
						+ expectedMessage);

		// close browser
		driver.quit();
		System.out.println("Browser is closed");

	}

	private void sleep(long m) {
		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
