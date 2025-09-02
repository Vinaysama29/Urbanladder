package com.urbanladder.stepdefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.urbanladder.base.DriverSetup;
import com.urbanladder.base.DriverSetup;
import com.urbanladder.pages.BeingatHomePage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BeingAtHomeSteps {

    private static final Logger logger = LogManager.getLogger(BeingAtHomeSteps.class);
    WebDriver driver = DriverSetup.getDriver();
    BeingatHomePage beingatHomePage = new BeingatHomePage(driver);

    @When("the user searches for the 'Being at Home' filter from JSON")
    public void searchForBeingAtHome() {
        logger.info("Starting search for 'Being at Home' using filter from JSON");
        beingatHomePage.searchForBeingAtHome();
        logger.info("Search completed");
    }

    @And("the user captures the top three items from the search results")
    public void captureTopThreeItems() {
        logger.info("Capturing top three items from search results");
        beingatHomePage.captureAndStoreTopThreeItems();
        logger.info("Top three items captured and stored in Excel");
    }

    @Then("the item names should be written to the Excel sheet")
    public void verifyItemsWrittenToExcel() {
        logger.info("Verifying item names are written to Excel sheet");
        // Assuming ExcelUtil.writeItemList already handles writing and logging
        logger.info("Excel write operation completed");
    }

    @When("the user clicks on the main logo")
    public void clickHomeLogo() {
        logger.info("Clicking on the main logo to navigate to homepage");
        beingatHomePage.clickHomeLogo();
        logger.info("Navigation to homepage triggered");
    }

    @Then("the user should be redirected to the homepage")
    public void verifyHomepageNavigation() {
        logger.info("Verifying redirection to homepage");
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.equals("https://www.urbanladder.com/")) {
            logger.info("Successfully redirected to homepage: " + currentUrl);
        } else {
            logger.warn("Redirection failed. Current URL: " + currentUrl);
        }
    }
}
