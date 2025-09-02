package com.urbanladder.pages;

import com.urbanladder.utils.ConfigReader;
import com.urbanladder.utils.ExcelUtil;
import com.urbanladder.utils.WebDriverUtil;
import com.urbanladder.utils.JsonUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;

import java.util.ArrayList;
import java.util.List;

public class BeingatHomePage {

    private WebDriver driver;
    private final int waitTime = ConfigReader.getExplicitWait();
    private static final String baseurl = "https://www.urbanladder.com/";

    @FindBy(xpath = "//*[@id='search']")
    private WebElement searchBox;

    @FindBy(xpath = "//*[@id='search_button']/span")
    private WebElement searchButton;

    @FindBy(linkText = "Close")
    private WebElement closeButton;

    @FindBy(xpath = "//*[@id='search-results']/div[3]/ul/li[1]/div/div[5]/a")
    private WebElement firstItem;

    @FindBy(xpath = "//*[@id='search-results']/div[3]/ul/li[2]/div/div[5]/a")
    private WebElement secondItem;

    @FindBy(xpath = "//*[@id='search-results']/div[3]/ul/li[3]/div/div[5]/a")
    private WebElement thirdItem;

    @FindBy(xpath = "//figure[contains(@class,'header__topBar_logo')]")
    private WebElement homeLogo;

    public BeingatHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void searchForBeingAtHome() {
        String filterValue = JsonUtility.getValue("src/test/resources/data.json", "filter");
        if (filterValue != null && !filterValue.isEmpty()) {
            WebDriverUtil.waitForElementToBeVisible(driver, searchBox, waitTime);
            searchBox.sendKeys(filterValue);
            WebDriverUtil.waitAndClick(driver, searchButton, waitTime);
        }
    }

    public void captureAndStoreTopThreeItems() {
        /*
        try {
            WebDriverUtil.waitAndClick(driver, closeButton, waitTime);
        } catch (Exception e) {
            // Popup not found or already closed
        }
        */
        WebDriverUtil.waitForElementToBeVisible(driver, thirdItem, waitTime);

        List<String> items = new ArrayList<>();
        items.add(WebDriverUtil.getElementText(firstItem));
        items.add(WebDriverUtil.getElementText(secondItem));
        items.add(WebDriverUtil.getElementText(thirdItem));

        ExcelUtil.writeItemList("BeingAtHome", items);
    }

    public void clickHomeLogo() {
        try {
        	driver.navigate().to(baseurl);
        } catch (Exception e) {
            System.out.println("Error while scrolling and clicking Home logo: " + e.getMessage());
        }
    }
}
