package com.urbanladder.pages;

import com.urbanladder.utils.ConfigReader;
import com.urbanladder.utils.WebDriverUtil;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(linkText = "Close")
    private WebElement closePopup;

//    @FindBy(xpath = "//li[1]//div[1]//div[5]//div[2]//a[2]")
//    private WebElement productHover;

    @FindBy(xpath = "//a[normalize-space()='View Product']")
    private WebElement viewProductBtn;

    @FindBy(xpath = "//button[@id='add-to-cart-button']")
    private WebElement addToCartBtn;

    @FindBy(xpath = "//div[@class='col-md-5']//button[@id='checkout-link']")
    private WebElement checkoutBtn;

    @FindBy(xpath = "//figure[contains(@class,'header__topBar_logo')]")
    private WebElement homeLogo;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
        PageFactory.initElements(driver, this);
    }

//    public void hoverOverProduct() {
//        try {
//            WebDriverUtil.waitForElementToBeVisible(driver, productHover, ConfigReader.getExplicitWait());
//            WebDriverUtil.hoverOverElement(driver, productHover);
//        } catch (Exception e) {
//            // Handle silently or rethrow if needed
//        }
//    }

    public void clickViewProduct() {
        try {
            WebDriverUtil.waitAndClick(driver, viewProductBtn, ConfigReader.getExplicitWait());
        } catch (Exception e) {
        	System.out.println("Error while scrolling and clicking Home logo: " + e.getMessage());
        }
    }

    public void clickAddToCart() {
        try {
            WebDriverUtil.waitAndClick(driver, addToCartBtn, ConfigReader.getExplicitWait());
        } catch (Exception e) {
        	System.out.println("Error while scrolling and clicking Home logo: " + e.getMessage());
      }
    }

    public void clickCheckout() {
        try {
            WebDriverUtil.waitAndClick(driver, checkoutBtn, ConfigReader.getExplicitWait());
        } catch (Exception e) {
        	System.out.println("Error while scrolling and clicking Home logo: " + e.getMessage());
        }
    }
}
