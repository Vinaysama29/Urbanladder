package com.urbanladder.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.urbanladder.utils.ConfigReader;
import com.urbanladder.utils.WebDriverUtil;
import com.urbanladder.utils.JsonUtility;

public class WishlistPage {

    private WebDriver driver;
    private final int waitTime = ConfigReader.getExplicitWait();

    @FindBy(className = "headersearch")
    private WebElement searchIcon;

    @FindBy(id = "search")
    private WebElement searchInput;

    @FindBy(xpath = "(//li[contains(@class,'product')])")
    private WebElement firstProduct;

    @FindBy(xpath = "//li[1]//div[1]//div[4]//span[1]")
    private WebElement wishlistButton;

    @FindBy(xpath = "//a[@id='header-icon-wishlist']")
    private WebElement wishlistHeaderIcon;

    @FindBy(xpath = "//figure[contains(@class,'header__topBar_logo')]")
    private WebElement homeLogo;

    @FindBy(xpath = "//*[@id=\\\"filters_availability_In_Stock_Only\\\"]")
    private WebElement outofstock;

    public WishlistPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void searchFromJson() {
        try {
            WebDriverUtil.waitAndClick(driver, searchIcon, waitTime);

            String item = JsonUtility.getValue("src/test/resources/data.json", "item");
            if (item != null && !item.isEmpty()) {
                WebDriverUtil.waitAndSendKeys(driver, searchInput, item + Keys.ENTER, waitTime);
            }
        } catch (Exception e) {
        	 System.out.println("Error while scrolling and clicking Home logo: " + e.getMessage());
        }
    }

    public void addFirstProductToWishlist() {
        try {
            WebDriverUtil.waitForElementToBeVisible(driver, firstProduct, waitTime);
            WebDriverUtil.hoverOverElement(driver, firstProduct);

            WebElement productNameElement = firstProduct.findElement(By.xpath(".//span[@class='name']"));
            WebElement productPriceElement = firstProduct.findElement(By.className("pricing"));

            String className = wishlistButton.getAttribute("class");
            if (className.contains("heart_outline_thick")) {
                wishlistButton.click();
            }

            String productName = WebDriverUtil.getElementText(productNameElement);
            String productPrice = WebDriverUtil.getElementText(productPriceElement);

            WebDriverUtil.scrollToElement(driver, wishlistHeaderIcon);
            WebDriverUtil.waitAndClick(driver, wishlistHeaderIcon, waitTime);

            WebElement wishlistProductName = WebDriverUtil.waitForElementToBeVisible(driver,
                driver.findElement(By.xpath("//img[@title='Weston Half Leather Sofa (Licorice Italian Leather)']")), waitTime);

            WebElement wishlistProductPrice = wishlistProductName.findElement(
                By.xpath("//span[@class='pricing']"));

            String wishlistName = WebDriverUtil.getElementText(wishlistProductName);
            String wishlistPrice = WebDriverUtil.getElementText(wishlistProductPrice);
			/*
			 * if (productName.equalsIgnoreCase(wishlistName) &&
			 * productPrice.equalsIgnoreCase(wishlistPrice)) { // Wishlist verification
			 * passed } else { // Wishlist verification failed }
			 */

        } catch (Exception e) {
        	 System.out.println("Error while scrolling and clicking Home logo: " + e.getMessage());
        }
    }
}
