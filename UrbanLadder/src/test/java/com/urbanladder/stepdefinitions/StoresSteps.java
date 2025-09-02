package com.urbanladder.stepdefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.urbanladder.base.DriverSetup;
import com.urbanladder.pages.StoresPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StoresSteps {

    private static final Logger logger = LogManager.getLogger(StoresSteps.class);
    private WebDriver driver = DriverSetup.getDriver();
    private StoresPage storesPage = new StoresPage(driver);

    @When("the user navigates to the Hyderabad store section")
    public void navigateToHyderabadStoreSection() {
        logger.info("Navigating to the Hyderabad store section...");
        storesPage.goToHyderabadStore();
        logger.info("Successfully navigated to Hyderabad store section.");
    }

    @Then("the user captures the list of Hyderabad store names")
    public void captureHyderabadStoreNames() {
        logger.info("Capturing list of Hyderabad store names...");
        // This is handled inside printHyderabadStores()
        logger.info("Store names captured.");
    }

    @And("the user stores the names in the Excel sheet")
    public void storeNamesInExcelSheet() {
        logger.info("Storing Hyderabad store names in Excel sheet...");
        storesPage.printHyderabadStores();
        logger.info("Store names successfully written to Excel.");
    }

    @Then("the user should be redirected to the homepage to see")
    public void verifyHomepageRedirection() {
        logger.info("Verifying homepage redirection...");
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.equals("https://www.urbanladder.com/")) {
            logger.info("Successfully redirected to homepage.");
        } else {
            logger.error("Redirection failed. Current URL: " + currentUrl);
            throw new AssertionError("Homepage redirection failed.");
        }
    }
}
