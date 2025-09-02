package com.urbanladder.stepdefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.urbanladder.base.DriverSetup;
import com.urbanladder.pages.CheckoutPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckoutSteps {

    private static final Logger logger = LogManager.getLogger(CheckoutSteps.class);
    private CheckoutPage checkoutPage;

    public CheckoutSteps() {
        checkoutPage = new CheckoutPage(DriverSetup.getDriver());
    }

    @When("the user clicks on the View Product button")
    public void the_user_clicks_on_the_view_product_button() {
        logger.info("Attempting to click on the View Product button");
        checkoutPage.clickViewProduct();
        logger.info("Clicked on the View Product button successfully");
    }

    @And("the user adds the product to the cart")
    public void the_user_adds_the_product_to_the_cart() {
        logger.info("Attempting to add the product to the cart");
        checkoutPage.clickAddToCart();
        logger.info("Product added to the cart successfully");
    }

    @Then("the user proceeds to checkout")
    public void the_user_proceeds_to_checkout() {
        logger.info("Attempting to proceed to checkout");
        checkoutPage.clickCheckout();
        logger.info("Proceeded to checkout successfully");
    }
}
