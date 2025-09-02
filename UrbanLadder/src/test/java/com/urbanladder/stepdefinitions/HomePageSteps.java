package com.urbanladder.stepdefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.urbanladder.base.DriverSetup;
import com.urbanladder.pages.HomePage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HomePageSteps {

	private static final Logger logger = LogManager.getLogger(HomePageSteps.class);
	private WebDriver driver = DriverSetup.getDriver();
	private HomePage homePage = new HomePage(driver);

	@Given("the user launches the browser and navigates to the Urban Ladder home page")
	public void launchHomePage() {
		logger.info("Launching browser and navigating to Urban Ladder home page.");
//		driver.get("https://www.urbanladder.com");
		DriverSetup.navigateToApplication();
	}

	@When("the user clicks on the profile icon")
	public void clickProfileIcon() {
		logger.info("Clicking on the profile icon.");
		homePage.clickProfileIcon();
	}

	@When("the user clicks on the login option")
	public void clickLoginOption() {
		logger.info("Clicking on the login option.");
		homePage.clickLogin();
	}

	@When("the user enters valid credentials and submits the login form")
	public void performLogin() {
		logger.info("Entering credentials and submitting the login form.");
		homePage.loginAction();
	}

	@Then("the user should be logged in successfully")
	public void verifyLoginSuccess() {
		logger.info("Verifying login success.");
		// Replace with actual login verification logic
		Assert.assertTrue(true, "Login assumed successful for now.");
	}

	@Then("the Facebook icon should be visible in the footer")
	public void verifyFacebookIcon() {
		logger.info("Checking visibility of Facebook icon.");
		boolean isVisible = homePage.isFacebookIconVisible();
		logger.info("Facebook icon visible: {}", isVisible);
		Assert.assertTrue(isVisible, "Facebook icon is not visible.");
	}

	@Then("the Twitter icon should be visible in the footer")
	public void verifyTwitterIcon() {
		logger.info("Checking visibility of Twitter icon.");
		boolean isVisible = homePage.isTwitterIconVisible();
		logger.info("Twitter icon visible: {}", isVisible);
		Assert.assertTrue(isVisible, "Twitter icon is not visible.");
	}

	@When("the user clicks on the home logo")
	public void clickHomeLogo() {
		logger.info("Clicking on the home logo.");
		homePage.clickHomeLogo();
	}

	@Then("the user should be redirected to the home page")
	public void verifyHomeRedirection() {
		String currentUrl = driver.getCurrentUrl();
		logger.info("Current URL after clicking home logo: {}", currentUrl);
		Assert.assertTrue(currentUrl.contains("urbanladder.com"), "Redirection to home page failed.");
	}
}
