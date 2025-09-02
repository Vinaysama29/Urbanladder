package com.urbanladder.pages;

import com.urbanladder.utils.ConfigReader;
import com.urbanladder.utils.WebDriverUtil;
import com.urbanladder.utils.ExcelUtil;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

public class StoresPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private final int waitTime = ConfigReader.getExplicitWait();
    private static final String baseurl = "https://www.urbanladder.com/";

    @FindBy(xpath = "//li[contains(@class,'topnav_item storesunit')]")
    private WebElement storesMenu;

    @FindBy(linkText = "Furniture Stores In Hyderabad")
    private WebElement hyderabadLink;

    @FindBy(className = "close-reveal-modal")
    private WebElement closePopup;

    @FindBy(css = "h3._3JJeW")
    private List<WebElement> storeNames;

    // @FindBy(xpath = "//figure[@class='TQXv_']//*[name()='svg']")
    // private WebElement homeLogo;

    public StoresPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        PageFactory.initElements(driver, this);
    }

    public void goToHyderabadStore() {
        try {
            WebDriverUtil.waitForElementToBeVisible(driver, storesMenu, waitTime);
            WebDriverUtil.hoverOverElement(driver, storesMenu);
            WebDriverUtil.waitAndClick(driver, hyderabadLink, waitTime);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void printHyderabadStores() {
        List<String> hyderabadStoreList = new ArrayList<>();

        try {
            if (!storeNames.isEmpty()) {
                for (WebElement store : storeNames) {
                    String name = WebDriverUtil.getElementText(store);
                    hyderabadStoreList.add(name);
                }
                ExcelUtil.writeItemList("HyderabadStores", hyderabadStoreList);
            }
        } catch (Exception e) {
            // Handle silently
        	System.out.println(e.getMessage());
        } finally {
            clickHomeLogo();
        }
    }

    public void clickHomeLogo() {
        try {
            driver.navigate().to(baseurl);
        } catch (Exception e) {
        	 System.out.println("Error while scrolling and clicking Home logo: " + e.getMessage());
        }
    }
}
