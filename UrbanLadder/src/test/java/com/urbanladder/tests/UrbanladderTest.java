
package com.urbanladder.tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.urbanladder.pages.AddressPage;
import com.urbanladder.pages.BeingatHomePage;
import com.urbanladder.pages.BookshelvesPage;
import com.urbanladder.pages.CheckoutPage;
import com.urbanladder.pages.HelpPage;
import com.urbanladder.pages.HomePage;
import com.urbanladder.pages.StoresPage;
import com.urbanladder.pages.StudyChairPage;
import com.urbanladder.pages.WishlistPage;
import com.urbanladder.utils.ConfigReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UrbanladderTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private HomePage homePage;
    private HelpPage helpPage;
    private BeingatHomePage beingatHomePage;
    private StudyChairPage studyChairPage;
    private StoresPage storesPage;
    private WishlistPage wishlistPage;
    private BookshelvesPage bookshelvesPage;
    private CheckoutPage checkoutPage;
    private AddressPage addressPage;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getImplicitWait()));
        driver.get(ConfigReader.getAppUrl());

        wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
        homePage = new HomePage(driver);
        helpPage = new HelpPage(driver);
        beingatHomePage = new BeingatHomePage(driver);
        studyChairPage = new StudyChairPage(driver);
        storesPage = new StoresPage(driver);
        wishlistPage = new WishlistPage(driver);
        bookshelvesPage = new BookshelvesPage(driver);
        checkoutPage = new CheckoutPage(driver);
        addressPage = new AddressPage(driver);
    }

    @Test(priority = 1)
    public void testLoginFunctionality() {
        homePage.clickProfileIcon();
        homePage.clickLogin();
        homePage.loginAction();
        Reporter.log("Login test executed", true);
    }

    @Test(priority = 2)
    public void testSocialMediaIconsVisibility() {
        boolean facebookVisible = homePage.isFacebookIconVisible();
        boolean twitterVisible = homePage.isTwitterIconVisible();

        Assert.assertTrue(facebookVisible, "Facebook icon should be visible");
        Assert.assertTrue(twitterVisible, "Twitter icon should be visible");

        Reporter.log("Social media icons visibility test executed", true);
    }

    @Test(priority = 3)
    public void testHelpPageTopics() {
        //helpPage.closePopupIfPresent();
        helpPage.clickHelpLink();
        helpPage.printHelpTopics();
        helpPage.clickHomeLogo();
        Reporter.log("Help page topics printed and navigated back to Home", true);
    }

    @Test(priority = 4)
    public void testBeingAtHomeSearch() {
        beingatHomePage.searchForBeingAtHome();
        beingatHomePage.captureAndStoreTopThreeItems();
        Reporter.log("Search test completed and top 3 items stored in Excel. Navigated back to Home.", true);
    }


    @Test(priority = 5)
   
    public void testStudyChairDetailsExtraction() {
        studyChairPage.clickStudyChairAndStoreData();
        Reporter.log("Study Chairs data extracted and stored successfully.", true);
    }


    @Test(priority = 6)
    public void testBookshelvesFilteringAndExtraction() {
        bookshelvesPage.clickBookshelves();
        bookshelvesPage.applyPriceFilter();
        bookshelvesPage.selectStorageType("Open");
        bookshelvesPage.excludeOutOfStockItems();
        bookshelvesPage.extractTopBookshelvesAndStore(3); // ✅ Corrected method name
        Reporter.log("Bookshelves filtering and extraction test executed", true);
    }


    @Test(priority = 7)
    public void testHyderabadStores() {
        storesPage.goToHyderabadStore();
        storesPage.printHyderabadStores();
        storesPage.clickHomeLogo();
        Reporter.log("Hyderabad store listing test executed and navigated back to Home", true);
    }

    @Test(priority = 8)
    public void testAddProductToWishlist() {
    	wishlistPage.searchFromJson();
        wishlistPage.addFirstProductToWishlist();
        Reporter.log("Wishlist test executed and navigated back to Home", true);
    }

    @Test(priority = 9)
    public void testCheckoutFlow() {
      
        checkoutPage.clickViewProduct();
        checkoutPage.clickAddToCart();
        checkoutPage.clickCheckout();
        Reporter.log("Checkout flow test executed and navigated back to Home", true);
    }

 
    @Test(priority = 10)
    public void testAddressPageValidationFromExcel() {
        Reporter.log("Starting address validation with valid data", true);
        addressPage.fillShippingAddressFromExcel(); // Reads from "valid" sheet
        Reporter.log("Valid address data filled and verified", true);

        Reporter.log("Starting address validation with invalid data", true);
        addressPage.fillShippingAddressFromExcelInvalid(); // Reads from "Invalid" sheet
        Reporter.log("Invalid address data filled and error handling verified", true);
    }


    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            Reporter.log("Browser closed after test execution", true);
        }
    }
}


//package com.urbanladder.tests;
//
//import com.urbanladder.pages.*;
//import com.urbanladder.utils.ConfigReader;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import org.testng.Assert;
//import org.testng.Reporter;
//import org.testng.annotations.*;
//
//import java.time.Duration;
//import java.util.List;
//import java.util.Map;
//
//public class UrbanladderTest {
//
//    private WebDriver driver;
//    private WebDriverWait wait;
//    private HomePage homePage;
//    private HelpPage helpPage;
//    private BeingatHomePage beingatHomePage;
//    private StudyChairPage studyChairPage;
//    private StoresPage storesPage;
//    private WishlistPage wishlistPage;
//    private BookshelvesPage bookshelvesPage;
//    private CheckoutPage checkoutPage;
//    private AddressPage addressPage;
//
//    @BeforeClass
//    public void setup() {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getImplicitWait()));
//        driver.get(ConfigReader.getAppUrl());
//
//        wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
//        homePage = new HomePage(driver);
//        helpPage = new HelpPage(driver);
//        beingatHomePage = new BeingatHomePage(driver);
//        studyChairPage = new StudyChairPage(driver);
//        storesPage = new StoresPage(driver);
//        wishlistPage = new WishlistPage(driver);
//        bookshelvesPage = new BookshelvesPage(driver);
//        checkoutPage = new CheckoutPage(driver);
//        addressPage = new AddressPage(driver);
//    }
//
//    @Test(priority = 1)
//    public void testLoginFunctionality() {
//        //homePage.closePopupIfPresent();
//        homePage.clickProfileIcon();
//        homePage.clickLogin();
//        homePage.loginAction();
//        Reporter.log("Login test executed", true);
//    }
//
//    @Test(priority = 2)
//    public void testSocialMediaIconsVisibility() {
//        boolean facebookVisible = homePage.isFacebookIconVisible();
//        boolean twitterVisible = homePage.isTwitterIconVisible();
//
//        Assert.assertTrue(facebookVisible, "Facebook icon should be visible");
//        Assert.assertTrue(twitterVisible, "Twitter icon should be visible");
//
//        Reporter.log("Social media icons visibility test executed", true);
//    }
//
//    @Test(priority = 3)
//    public void testHelpPageTopics() {
//        helpPage.closePopupIfPresent();
//        helpPage.clickHelpLink();
//        helpPage.printHelpTopics();
//        helpPage.clickHomeLogo();
//        Reporter.log("Help page topics printed and navigated back to Home", true);
//    }
//
//    @Test(priority = 4)
//    public void testBeingAtHomeSearch() {
//        beingatHomePage.searchForBeingAtHome();
//        List<String> items = beingatHomePage.getTopThreeItems();
//
//        Assert.assertEquals(items.size(), 3, "Should retrieve exactly 3 items");
//        for (String item : items) {
//            Reporter.log("Item: " + item, true);
//        }
//
//        beingatHomePage.clickHomeLogo();
//        Reporter.log("Search test completed and navigated back to Home", true);
//    }
//
//    @Test(priority = 5)
//    public void testStudyChairDetails() {
//        studyChairPage.clickStudyChair();
//        Map<String, String> chairs = studyChairPage.chairsName();
//
//        Assert.assertTrue(chairs.size() > 0, "At least one chair should be listed");
//        for (Map.Entry<String, String> entry : chairs.entrySet()) {
//            Reporter.log("Chair: " + entry.getKey() + " - Price: " + entry.getValue(), true);
//        }
//
//        Reporter.log("Study Chairs test executed and navigated back to Home", true);
//    }
//
//    @Test(priority = 7)
//    public void testHyderabadStores() {
//        storesPage.goToHyderabadStore();
//        storesPage.printHyderabadStores();
//        storesPage.clickHomeLogo();
//        Reporter.log("Hyderabad store listing test executed and navigated back to Home", true);
//    }
//
//    @Test(priority = 8)
//    public void testWishlistFunctionality()
//    {   
//    	wishlistPage.search("Sofa");
//        wishlistPage.addFirstProductToWishlist();
//        Reporter.log("Wishlist test executed and navigated back to Home", true);
//    }
//
//    @Test(priority = 6)
//    public void testBookshelvesFilteringAndExtraction() 
//    {
//        bookshelvesPage.clickBookshelves();
//        bookshelvesPage.applyPriceFilter();
//        bookshelvesPage.selectStorageType("Open");
//        bookshelvesPage.excludeOutOfStockItems();
//        bookshelvesPage.extractTopBookshelves(3);
//        Reporter.log("Bookshelves filtering and extraction test executed", true);
//    }
//
//    @Test(priority = 9)
//    public void testCheckoutFlow() 
//    {
//    	homePage.clickProfileIcon();
//        homePage.clickLogin();
//        homePage.loginAction();
//    	
//    	wishlistPage.search("Sofa");
//        wishlistPage.addFirstProductToWishlist();
//    	
//        checkoutPage.clickViewProduct();
//        checkoutPage.clickAddToCart();
//        checkoutPage.clickCheckout();
//        Reporter.log("Checkout flow test executed and navigated back to Home", true);
//    }
//
//    @Test(priority = 10)
//    public void testAddressPageEmailValidation() {
//        addressPage.enterInvalidEmail("invalidemail");
//        String errorMsg = addressPage.getErrorMessage();
//        Assert.assertNotNull(errorMsg, "Error message should be displayed for invalid email");
//        Reporter.log("Invalid email error message: " + errorMsg, true);
//
//        addressPage.enterValidEmail(ConfigReader.getUserEmail());
//        boolean isEnabled = addressPage.isSaveContinueEnabled();
//        Assert.assertTrue(isEnabled, "'Save and Continue' button should be enabled for valid email");
//
//        addressPage.goToHomePage();
//        Reporter.log("Address page email validation test executed and navigated back to Home", true);
//    }
//
//    @AfterClass
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//            Reporter.log("Browser closed after test execution", true);
//        }
//    }
//}
