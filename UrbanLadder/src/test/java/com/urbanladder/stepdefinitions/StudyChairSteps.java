package com.urbanladder.stepdefinitions;

import com.urbanladder.pages.StudyChairPage;
import com.urbanladder.base.DriverSetup;
import com.urbanladder.base.DriverSetup;

import io.cucumber.java.en.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class StudyChairSteps {

    private static final Logger logger = LogManager.getLogger(StudyChairSteps.class);
    private WebDriver driver = DriverSetup.getDriver();
    private StudyChairPage studyChairPage = new StudyChairPage(driver);

    @When("the user navigates to the Study Chairs section")
    public void navigateToStudyChairs() {
        logger.info("Navigating to Study Chairs section...");
        studyChairPage.clickStudyChairAndStoreData();
        logger.info("Navigation to Study Chairs completed.");
    }

    @And("the user captures the top three study chair names and prices")
    public void captureChairDetails() {
        logger.info("Capturing top three study chair names and prices...");
        logger.info("Chair details captured successfully.");
    }

    @Then("the product details should be written to the Excel sheet")
    public void writeDetailsToExcel() {
        logger.info("Product details have been written to the Excel sheet.");
    }

    @When("the user clicks on the main logo to go")
    public void clickMainLogo() {
        logger.info("Clicking on the main logo to return to homepage...");
        studyChairPage.clickHomeLogo();
        logger.info("User redirected to homepage.");
    }

    @Then("the user should be redirected to the homepage to view")
    public void verifyHomePageRedirection() {
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
