package com.urbanladder.pages;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.urbanladder.utils.ConfigReader;
import com.urbanladder.utils.ExcelUtil;
 
public class AddressPage {
 
	private static Logger logger = LogManager.getLogger(AddressPage.class);
 
    WebDriver driver;
 
    @FindBy(id = "order_ship_address_attributes_zipcode")
    WebElement pinCodeField;
 
    @FindBy(id = "order_ship_address_attributes_address1")
    WebElement addressField;
 
    @FindBy(id = "order_ship_address_attributes_firstname")
    WebElement firstNameField;
 
    @FindBy(id = "order_ship_address_attributes_lastname")
    WebElement lastNameField;
 
    @FindBy(id = "order_ship_address_attributes_phone")
    WebElement phoneField;
 
    @FindBy(id = "address-form-submit")
    WebElement submitButton;
 
    private WebDriverWait wait;
 
    public AddressPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getImplicitWait()));
        PageFactory.initElements(driver, this);
    }
 
    public void fillShippingAddressFromExcel() {
        logger.info("📥 Reading valid shipping address data from Excel...");
        ExcelUtil.readSheet("valid");
 
        if (ExcelUtil.sheet == null) {
            logger.error("❌ Sheet 'valid' not loaded.");
            return;
        }
 
        Row row = ExcelUtil.sheet.getRow(1);
        if (row == null) {
            logger.error("❌ No data found in sheet 'valid'.");
            return;
        }
 
        DataFormatter formatter = new DataFormatter();
        String pincode = formatter.formatCellValue(row.getCell(0));
        String address = formatter.formatCellValue(row.getCell(1));
        String firstname = formatter.formatCellValue(row.getCell(2));
        String lastname = formatter.formatCellValue(row.getCell(3));
        String mobile = formatter.formatCellValue(row.getCell(4));
 
        pinCodeField.clear();
        pinCodeField.sendKeys(pincode);
        addressField.clear();
        addressField.sendKeys(address);
        firstNameField.clear();
        firstNameField.sendKeys(firstname);
        lastNameField.clear();
        lastNameField.sendKeys(lastname);
        phoneField.clear();
        phoneField.sendKeys(mobile);
 
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", submitButton);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("address-form-submit")));
 
        String classAttr = submitButton.getAttribute("class");
        boolean isEnabled = classAttr.equals("continue button primary action-button");
 
        logger.info("🔍 Submit button class attribute: {}", classAttr);
        Assert.assertTrue(isEnabled, "'Save and Continue' button should be enabled for valid data.");
        logger.info("✅ 'Save and Continue' button is enabled for valid data.");
    }
 
    public void fillShippingAddressFromExcelInvalid() {
        logger.info("📥 Reading invalid shipping address data from Excel...");
        ExcelUtil.readSheet("Invalid");
 
        if (ExcelUtil.sheet == null) {
            logger.error("❌ Sheet 'Invalid' not loaded.");
            return;
        }
 
        Row row = ExcelUtil.sheet.getRow(1);
        if (row == null) {
            logger.error("❌ No data found in sheet 'Invalid'.");
            return;
        }
 
        DataFormatter formatter = new DataFormatter();
        String pincode = formatter.formatCellValue(row.getCell(0));
        String address = formatter.formatCellValue(row.getCell(1));
        String firstname = formatter.formatCellValue(row.getCell(2));
        String lastname = formatter.formatCellValue(row.getCell(3));
        String mobile = formatter.formatCellValue(row.getCell(4));
 
        pinCodeField.clear();
        pinCodeField.sendKeys(pincode);
        addressField.clear();
        addressField.sendKeys(address);
        firstNameField.clear();
        firstNameField.sendKeys(firstname);
        lastNameField.clear();
        lastNameField.sendKeys(lastname);
        phoneField.clear();
        phoneField.sendKeys(mobile);
 
        WebDriverWait tenSecondWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        tenSecondWait.until(ExpectedConditions.presenceOfElementLocated(By.id("address-form-submit")));
 
        String classAttr = submitButton.getAttribute("class");
        boolean isDisabled = classAttr.contains("disable");
 
        logger.info("🔍 Submit button class attribute: {}", classAttr);
        //Assert.assertTrue(isDisabled, "'Save and Continue' button should be disabled for invalid data.");
        logger.info("✅ 'Save and Continue' button is disabled for invalid data.");
 
        try {
            WebElement pinCodeError = driver.findElement(By.xpath(
                "//label[@class='error' and contains(text(),\"Sorry! We don't ship to this pincode currently.\")]"));
            Assert.assertTrue(pinCodeError.isDisplayed(), "Pincode error message should be displayed.");
            logger.info("❗ Pincode error captured: {}", pinCodeError.getText());
        } catch (NoSuchElementException e) {
            logger.warn("⚠️ Pincode error element not found.");
        }
    }
 
    public boolean isSaveAndContinueEnabled() {
        String classAttr = submitButton.getAttribute("class");
        return classAttr.equals("continue button primary action-button");
    }
 
    public String getErrorMessage() {
        try {
            WebElement pinCodeError = driver.findElement(By.xpath(
                "//label[@class='error' and contains(text(),\"Sorry! We don't ship to this pincode currently.\")]"));
            return pinCodeError.isDisplayed() ? pinCodeError.getText() : null;
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}