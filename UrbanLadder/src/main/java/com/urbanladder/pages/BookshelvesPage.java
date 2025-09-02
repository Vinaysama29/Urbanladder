package com.urbanladder.pages;

import com.urbanladder.utils.ConfigReader;
import com.urbanladder.utils.ExcelUtil;
import com.urbanladder.utils.WebDriverUtil;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BookshelvesPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions action;
    private static final String baseurl = "https://www.urbanladder.com/";

    @FindBy(xpath = "//h4[text()='Bookshelves']/parent::a")
    private WebElement bookshelvesLink;

    @FindBy(xpath = "//div[normalize-space()='Price']")
    private WebElement priceFilter;

    @FindBy(xpath = "//div[@class='noUi-handle noUi-handle-upper']")
    private WebElement priceSlider;

    @FindBy(xpath = "//li[@class='selrange-filter']")
    private WebElement priceRangeFilter;

    @FindBy(xpath = "//div[normalize-space()='Storage Type']")
    private WebElement storageTypeFilter;

    @FindBy(xpath = "//label[normalize-space()='Open']")
    private WebElement openStorageType;

    @FindBy(xpath = "//label[normalize-space()='Exclude Out Of Stock']")
    private WebElement excludeOutOfStock;

    public BookshelvesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
        this.action = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickBookshelves() {
        try {
            WebDriverUtil.waitAndClick(driver, bookshelvesLink, ConfigReader.getExplicitWait());
        } catch (Exception e) {
            // Handle silently
        }
    }

    public void applyPriceFilter() {
        try {
            action.moveToElement(priceFilter).perform();
            WebDriverUtil.waitForElementToBeVisible(driver, priceSlider, ConfigReader.getExplicitWait());
            action.clickAndHold(priceSlider).moveByOffset(-230, 0).release().perform();
            WebDriverUtil.waitForElementToBeVisible(driver, priceRangeFilter, ConfigReader.getExplicitWait());
        } catch (Exception e) {
            // Handle silently
        }
    }

    public void selectStorageType(String type) {
        try {
            action.moveToElement(storageTypeFilter).perform();
            if (type.equalsIgnoreCase("Open")) {
                WebDriverUtil.waitAndClick(driver, openStorageType, ConfigReader.getExplicitWait());
            }
        } catch (Exception e) {
            // Handle silently
        }
    }

    public void excludeOutOfStockItems() {
        try {
            WebDriverUtil.waitAndClick(driver, excludeOutOfStock, ConfigReader.getExplicitWait());
        } catch (Exception e) {
            // Handle silently
        }
    }

    public void extractTopBookshelvesAndStore(int count) {
        try {
            List<WebElement> products = driver.findElements(By.xpath("//span[@class='name']"));
            List<WebElement> prices = driver.findElements(By.xpath("//div[@class='price-number']/span"));

            if (products.isEmpty() || prices.isEmpty()) {
                return;
            }

            Map<String, String> topProducts = new LinkedHashMap<>();
            for (int i = 0; i < Math.min(count, products.size()); i++) {
                String name = products.get(i).getText();
                String price = prices.get(i).getText();
                topProducts.put(name, price);
            }

            ExcelUtil.writeProductData("Bookshelves", topProducts, "Bookshelf Name");
            driver.navigate().to(baseurl);
        } catch (Exception e) {
        	 System.out.println("Error while scrolling and clicking Home logo: " + e.getMessage());
        }
    }
}
