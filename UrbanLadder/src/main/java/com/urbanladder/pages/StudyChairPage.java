package com.urbanladder.pages;

import com.urbanladder.utils.ConfigReader;
import com.urbanladder.utils.ExcelUtil;
import com.urbanladder.utils.WebDriverUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StudyChairPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final String baseurl = "https://www.urbanladder.com/";

    @FindBy(xpath = "//*[@id=\"topnav_wrapper\"]/ul/li[8]/span")
    private WebElement study;

    @FindBy(xpath = "//span[normalize-space()='Study Chairs']")
    private WebElement studychair;

    @FindBy(xpath = "//span[@class='name']")
    private List<WebElement> chairsNames;

    @FindBy(xpath = "//div[@class='price-number']/span")
    private List<WebElement> costOfChairs;

    @FindBy(xpath = "//figure[contains(@class,'header__topBar_logo')]")
    private WebElement homeLogo;

    public StudyChairPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
        PageFactory.initElements(driver, this);
    }

    public void clickStudyChairAndStoreData() {
        try {
            WebDriverUtil.hoverOverElement(driver, study);
            WebDriverUtil.waitAndClick(driver, studychair, ConfigReader.getExplicitWait());
            WebDriverUtil.waitForElementToBeVisible(driver, chairsNames.get(0), ConfigReader.getExplicitWait());

            Map<String, String> chairDetails = extractChairDetails();
            if (chairDetails != null && !chairDetails.isEmpty()) {
                ExcelUtil.writeProductData("StudyChairs", chairDetails, "Chair Name");
            }
        } catch (Exception e) {
            // Handle silently
        }
    }

    private Map<String, String> extractChairDetails() {
        Map<String, String> chairMap = new HashMap<>();
        try {
            for (int i = 0; i < Math.min(3, chairsNames.size()); i++) {
                String name = WebDriverUtil.getElementText(chairsNames.get(i));
                String price = WebDriverUtil.getElementText(costOfChairs.get(i));
                chairMap.put(name, price);
            }
            clickHomeLogo();
        } catch (Exception e) {
            // Handle silently
        }
        return chairMap;
    }

    public void clickHomeLogo() {
        try {
        	driver.navigate().to(baseurl);
            
        } catch (Exception e) {
        	 System.out.println("Error while scrolling and clicking Home logo: " + e.getMessage());
        }
    }
}
