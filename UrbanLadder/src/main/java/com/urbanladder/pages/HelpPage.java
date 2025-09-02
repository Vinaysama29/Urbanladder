package com.urbanladder.pages;

import com.urbanladder.utils.ConfigReader;
import com.urbanladder.utils.WebDriverUtil;
import com.urbanladder.utils.ExcelUtil;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;

import java.util.ArrayList;
import java.util.List;

public class HelpPage {

    private WebDriver driver;
    private final int waitTime = ConfigReader.getExplicitWait();
    private static final String baseurl = "https://www.urbanladder.com/";

    @FindBy(className = "close-reveal-modal")
    private WebElement closePopup;

    @FindBy(linkText = "Help")
    private WebElement helpLink;

    @FindBy(className = "topics")
    private WebElement topicsContainer;

    @FindBy(xpath = "//ul[@class='topics']/li")
    private List<WebElement> helpTopics;

    @FindBy(xpath = "//figure[contains(@class,'header__topBar_logo')]")
    private WebElement homeLogo;

    public HelpPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /*
    public void closePopupIfPresent() {
        try {
            WebDriverUtil.waitAndClick(driver, closePopup, waitTime);
        } catch (Exception e) {
            // Popup not present or already closed
        }
    }
    */

    public void clickHelpLink() {
        WebDriverUtil.waitAndClick(driver, helpLink, waitTime);
    }

    public void printHelpTopics() {
        WebDriverUtil.waitForElementToBeVisible(driver, topicsContainer, waitTime);
        List<String> topicsList = new ArrayList<>();

        for (WebElement topic : helpTopics) {
            String text = WebDriverUtil.getElementText(topic);
            topicsList.add(text);
        }

        ExcelUtil.writeItemList("HelpTopics", topicsList);
    }

    public void clickHomeLogo() {
        try {
        	 driver.navigate().to(baseurl);
        } catch (Exception e) {
            System.out.println("Error while scrolling and clicking Home logo: " + e.getMessage());
        }
    }
}
