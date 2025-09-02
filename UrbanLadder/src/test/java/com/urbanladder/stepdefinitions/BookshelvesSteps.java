package com.urbanladder.stepdefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.urbanladder.base.DriverSetup;

import com.urbanladder.pages.BookshelvesPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BookshelvesSteps {

    private static final Logger logger = LogManager.getLogger(BookshelvesSteps.class);
    private WebDriver driver = DriverSetup.getDriver();
    private BookshelvesPage bookshelvesPage = new BookshelvesPage(driver);

    @When("the user clicks on the Bookshelves section")
    public void clickBookshelvesSection() {
        logger.info("Clicking on the Bookshelves section...");
        bookshelvesPage.clickBookshelves();
        logger.info("Bookshelves section clicked.");
    }

    @And("the user applies the price filter")
    public void applyPriceFilter() {
        logger.info("Applying price filter...");
        bookshelvesPage.applyPriceFilter();
        logger.info("Price filter applied.");
    }

    @And("the user selects {string} as the storage type")
    public void selectStorageType(String type) {
        logger.info("Selecting storage type: " + type);
        bookshelvesPage.selectStorageType(type);
        logger.info("Storage type selected: " + type);
    }

    @And("the user excludes out-of-stock items")
    public void excludeOutOfStockItems() {
        logger.info("Excluding out-of-stock items...");
        bookshelvesPage.excludeOutOfStockItems();
        logger.info("Out-of-stock items excluded.");
    }

    @Then("the user captures the top 3 bookshelves and stores them in the Excel sheet")
    public void captureTopBookshelves() {
        logger.info("Capturing top 3 bookshelves and storing in Excel...");
        bookshelvesPage.extractTopBookshelvesAndStore(3);
        logger.info("Bookshelf data captured and stored successfully.");
    }

    @Then("the user should be redirected to the homepage to use Next")
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
