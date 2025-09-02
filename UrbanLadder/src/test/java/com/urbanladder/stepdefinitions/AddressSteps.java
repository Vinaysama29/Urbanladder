package com.urbanladder.stepdefinitions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.urbanladder.base.DriverSetup;
import com.urbanladder.pages.AddressPage;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
 
public class AddressSteps {
 
    private static final Logger logger = LogManager.getLogger(AddressSteps.class);
 
    WebDriver driver;
    WebDriverWait wait;
    AddressPage shippingAddress = new AddressPage(DriverSetup.getDriver());
 
    @When("I fill the shipping address form with valid data from Excel sheet {string}")
    public void i_fill_shipping_address_with_valid_data(String sheetName) {
        logger.info("📦 Filling shipping address form with valid data from sheet: {}", sheetName);
        if (sheetName.equalsIgnoreCase("valid")) {
            shippingAddress.fillShippingAddressFromExcel();
            logger.info("✅ Shipping address form filled with valid data.");
        } else {
            logger.warn("⚠️ Sheet name '{}' is not recognized as valid.", sheetName);
        }
    }
 
    @Then("the {string} button should be enabled")
    public void save_and_continue_button_should_be_enabled(String buttonText) {
        logger.info("🔍 Checking if '{}' button is enabled...", buttonText);
        boolean isEnabled = shippingAddress.isSaveAndContinueEnabled();
       Assert.assertTrue(isEnabled, buttonText + " button should be enabled.");
       // Assert.fail("💥 Forcing failure to test RetryListener");

        
        logger.info("✅ '{}' button is enabled as expected.", buttonText);
    }
 
    @When("I fill the shipping address form with invalid data from Excel sheet {string}")
    public void i_fill_shipping_address_with_invalid_data(String sheetName) {
        logger.info("📦 Filling shipping address form with invalid data from sheet: {}", sheetName);
        if (sheetName.equalsIgnoreCase("Invalid")) {
            shippingAddress.fillShippingAddressFromExcelInvalid();
            logger.info("❌ Shipping address form filled with invalid data.");
        } else {
            logger.warn("⚠️ Sheet name '{}' is not recognized as invalid.", sheetName);
        }
    }
 
    @Then("the {string} button should be disabled and an error message should be displayed")
    public void save_and_continue_button_should_be_disabled(String buttonText) {
        logger.info("🔍 Checking if '{}' button is disabled and error message is shown...", buttonText);
        boolean isEnabled = shippingAddress.isSaveAndContinueEnabled();
        String errorMessage = shippingAddress.getErrorMessage();
        Assert.assertFalse(isEnabled, buttonText + " button should be disabled.");
        Assert.assertNotNull(errorMessage, "Error message should be displayed.");
        logger.info("✅ '{}' button is disabled and error message displayed: {}", buttonText, errorMessage);
    }
}