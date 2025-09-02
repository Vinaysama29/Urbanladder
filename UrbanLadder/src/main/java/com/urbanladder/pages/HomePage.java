package com.urbanladder.pages;

import com.urbanladder.utils.ConfigReader;
import com.urbanladder.utils.WebDriverUtil;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final String baseurl = "https://www.urbanladder.com/";

    // Login elements
    @FindBy(xpath = "//span[@class='header-icon-link user-profile-icon']//*[name()='svg']")
    private WebElement profileIcon;

    @FindBy(xpath = "//a[@id='header-icon-login']")
    private WebElement loginIcon;

    @FindBy(xpath = "//input[@id='spree_user_email' and @class='email required input_authentication']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@id='spree_user_password' and @class='required input_authentication']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@id='ul_site_login']")
    private WebElement loginButton;

    // Social media icons
    @FindBy(xpath = "//footer//a[contains(@href,'facebook')]")
    private WebElement facebookIcon;

    @FindBy(xpath = "//footer//a[contains(@href,'twitter')]")
    private WebElement twitterIcon;

    @FindBy(className = "close-reveal-modal")
    private WebElement closePopup;

    // Home logo element
    @FindBy(xpath = "//figure[contains(@class,'header__topBar_logo')]")
    private WebElement homeLogo;

    // Credentials from config
    private final String emailValue = ConfigReader.getUserEmail();
    private final String passwordValue = ConfigReader.getUserPassword();

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
        PageFactory.initElements(driver, this);
    }

    public void clickProfileIcon() {
        WebDriverUtil.waitAndClick(driver, profileIcon, ConfigReader.getExplicitWait());
    }

    public void clickLogin() {
        WebDriverUtil.waitAndClick(driver, loginIcon, ConfigReader.getExplicitWait());
        WebDriverUtil.waitForElementToBeVisible(driver, emailField, ConfigReader.getExplicitWait());
    }

    public void loginAction() {
        WebDriverUtil.waitAndSendKeys(driver, emailField, emailValue, ConfigReader.getExplicitWait());
        WebDriverUtil.waitAndSendKeys(driver, passwordField, passwordValue, ConfigReader.getExplicitWait());
        WebDriverUtil.waitAndClick(driver, loginButton, ConfigReader.getExplicitWait());
    }

    public boolean isFacebookIconVisible() {
        WebDriverUtil.scrollToElement(driver, facebookIcon);
        boolean visible = WebDriverUtil.waitForElementToBeVisible(driver, facebookIcon, ConfigReader.getExplicitWait()).isDisplayed();
        return visible;
    }

    public boolean isTwitterIconVisible() {
        boolean visible = WebDriverUtil.waitForElementToBeVisible(driver, twitterIcon, ConfigReader.getExplicitWait()).isDisplayed();
        return visible;
    }

    public void clickHomeLogo() {
        try {
        	 driver.navigate().to(baseurl);
        } catch (Exception e) {
            System.out.println("Error while scrolling and clicking Home logo: " + e.getMessage());
        }
    }
}
