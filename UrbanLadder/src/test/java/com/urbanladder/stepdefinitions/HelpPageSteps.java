package com.urbanladder.stepdefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.github.dockerjava.api.model.Driver;
import com.urbanladder.base.DriverSetup;
import com.urbanladder.pages.HelpPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HelpPageSteps {

    private static final Logger logger = LogManager.getLogger(HelpPageSteps.class);
    private WebDriver driver = DriverSetup.getDriver();
    private HelpPage helpPage = new HelpPage(driver);

    @Given("the user launches the browser and navigates to the Urban Ladder mainpage")
    public void launchHomePage() {
        logger.info("🌐 Launching browser and navigating to Urban Ladder home page.");
        DriverSetup.navigateToApplication();
    }

    @When("the user clicks on the Help link")
    public void clickHelpLink() {
        logger.info("🆘 Clicking on the Help link.");
        helpPage.clickHelpLink();
    }

    @And("the user extracts and stores all help topics")
    public void extractHelpTopics() {
        logger.info("📋 Extracting and storing help topics to Excel.");
        helpPage.printHelpTopics();
    }

    @When("the user clicks on the home logo to see")
    public void clickHomeLogo() {
        logger.info("🏠 Clicking on the home logo to return to homepage.");
        helpPage.clickHomeLogo();
    }

    @Then("the user should be redirected to the home pages")
    public void verifyHomeRedirection() {
        String currentUrl = driver.getCurrentUrl();
        logger.info("🔗 Current URL after clicking home logo: {}", currentUrl);
        Assert.assertTrue(currentUrl.contains("urbanladder.com"), "Redirection to home page failed.");
    }
}
