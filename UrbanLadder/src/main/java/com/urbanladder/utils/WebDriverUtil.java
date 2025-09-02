package com.urbanladder.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

// Utility class for common WebDriver operations
public class WebDriverUtil {

    private static final Logger logger = LogManager.getLogger(WebDriverUtil.class);

    // Wait for element to be clickable and click
    public static void waitAndClick(WebDriver driver, WebElement element, int timeoutSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            logger.info("Successfully clicked element");
        } catch (Exception e) {
            logger.error("Failed to click element: {}", e.getMessage());
            throw new RuntimeException("Click operation failed", e);
        }
    }

    // Wait for element to be visible and send keys
    public static void waitAndSendKeys(WebDriver driver, WebElement element, String text, int timeoutSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            wait.until(ExpectedConditions.visibilityOf(element));
            element.clear();
            element.sendKeys(text);
            logger.info("Successfully sent keys: {}", text);
        } catch (Exception e) {
            logger.error("Failed to send keys: {}", e.getMessage());
            throw new RuntimeException("Send keys operation failed", e);
        }
    }

    // Clear the input field and send keys after waiting for visibility
    public static void clearAndSendKeys(WebDriver driver, WebElement element, String text, int timeoutSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            wait.until(ExpectedConditions.visibilityOf(element));
            element.clear();
            element.sendKeys(text);
            logger.info("Successfully cleared and sent keys: {}", text);
        } catch (Exception e) {
            logger.error("Failed to clear and send keys: {}", e.getMessage());
            throw new RuntimeException("Clear and send keys operation failed", e);
        }
    }

    // Wait for element to be visible
    public static WebElement waitForElementToBeVisible(WebDriver driver, WebElement element, int timeoutSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            return wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            logger.error("Element not visible within timeout: {}", e.getMessage());
            throw new RuntimeException("Element visibility timeout", e);
        }
    }

    // Wait for element to be present
    public static WebElement waitForElementToBePresent(WebDriver driver, By locator, int timeoutSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            logger.error("Element not present within timeout: {}", e.getMessage());
            throw new RuntimeException("Element presence timeout", e);
        }
    }

    // Select dropdown option by visible text
    public static void selectByVisibleText(WebElement element, String text) {
        try {
            Select select = new Select(element);
            select.selectByVisibleText(text);
            logger.info("Successfully selected option: {}", text);
        } catch (Exception e) {
            logger.error("Failed to select option: {}", e.getMessage());
            throw new RuntimeException("Select operation failed", e);
        }
    }

    // Select dropdown option by value
    public static void selectByValue(WebElement element, String value) {
        try {
            Select select = new Select(element);
            select.selectByValue(value);
            logger.info("Successfully selected value: {}", value);
        } catch (Exception e) {
            logger.error("Failed to select value: {}", e.getMessage());
            throw new RuntimeException("Select operation failed", e);
        }
    }

    // Scroll to element using JavaScript
    public static void scrollToElement(WebDriver driver, WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            logger.info("Successfully scrolled to element");
        } catch (Exception e) {
            logger.error("Failed to scroll to element: {}", e.getMessage());
            throw new RuntimeException("Scroll operation failed", e);
        }
    }

    // Mouse hover over element
    public static void hoverOverElement(WebDriver driver, WebElement element) {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();
            logger.info("Successfully hovered over element");
        } catch (Exception e) {
            logger.error("Failed to hover over element: {}", e.getMessage());
            throw new RuntimeException("Hover operation failed", e);
        }
    }

    // Check if element is displayed
    public static boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Get text from element with null check
    public static String getElementText(WebElement element) {
        try {
            return element.getText().trim();
        } catch (Exception e) {
            logger.warn("Failed to get element text: {}", e.getMessage());
            return "";
        }
    }
}
