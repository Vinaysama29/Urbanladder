package com.urbanladder.Hooks;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger; // For logging messages
import org.testng.ITestContext; // Used to share data across tests
import org.testng.Reporter; // Provides access to current test result

import com.urbanladder.base.DriverSetup; // Handles browser driver setup and teardown
import com.urbanladder.utils.ScreenshotUtil; // Utility class for taking screenshots

import io.cucumber.java.After; // Cucumber hook to run after each scenario
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before; // Cucumber hook to run before each scenario
import io.cucumber.java.Scenario; // Represents the current scenario
import io.qameta.allure.Allure; // Used to attach screenshots to Allure reports

// Hooks class to manage setup and teardown for each test scenario
public class Hooks {

	private static final Logger logger = LogManager.getLogger(Hooks.class); // Logger for debugging and reporting

	// This method runs before each scenario starts
	@Before
	public void setup(Scenario scenario) {
		// Get the current test context from TestNG
		ITestContext context = Reporter.getCurrentTestResult().getTestContext();

		// Retrieve OS and browser info from context
		String os = (String) context.getAttribute("os");
		String browser = (String) context.getAttribute("browser");

		// Initialize the browser driver with OS and browser info
		DriverSetup.initializeDriver(os, browser);
	}

	// This method runs after each scenario ends
	@After
	public void tearDown(Scenario scenario) {
		// Check if the driver is active
		if (DriverSetup.getDriver() != null) {

			// Take a screenshot of the current browser state
			String screenshotPath = ScreenshotUtil.getScreenshot(DriverSetup.getDriver(), scenario.getName());

			File screenshotFile = new File(screenshotPath);

			// If screenshot file exists, attach it to the Allure report
			if (screenshotFile.exists()) {
				try (FileInputStream fis = new FileInputStream(screenshotFile)) {
					logger.info("Attaching screenshot to allure report");
					Allure.addAttachment("Screenshot - " + scenario.getName(), fis);
				} catch (IOException e) {
					logger.error("Error in attaching screenshots to allure report", e);
				}
			}
		}

//		// Close and clean up the browser driver
//		DriverSetup.tearDown();
	}

	@AfterAll
	public static void tearDown2() {

		// Close and clean up the browser driver
		DriverSetup.tearDown();
	}
}

//package com.urbanladder.Hooks;
//    import java.util.Properties;
//	 
//	import org.openqa.selenium.OutputType;
//
//	import org.openqa.selenium.TakesScreenshot;
//
//	import org.openqa.selenium.WebDriver;
//
//	import org.testng.Reporter;
//	 
//	import com.urbanladder.base.Base;
//
//	import com.urbanladder.utils.PropertiesReader;
//	 
//	import io.cucumber.java.After;
//
//	import io.cucumber.java.Before;
//
//	import io.cucumber.java.BeforeAll;
//
//	import io.cucumber.java.AfterAll;
//
//	import io.cucumber.java.Scenario;
//	 
//	public class Hooks {
//	 
//	    static WebDriver driver;
//
//	    static Properties properties;
//	 
//	    // Runs once before all scenarios
//
//	    @BeforeAll
//
//	    public static void globalSetup() throws Exception {
//
//	        // Read browser from config.properties, not from testng.xml
//
//	        properties = Base.getProperties();
//
//	        String browser = properties.getProperty("browser");
//	 
//	        if (browser == null || browser.isEmpty()) {
//
//	            throw new RuntimeException("Browser not specified in config.properties");
//
//	        }
//	 
//	        driver = Base.initilizeBrowser(browser);
//
//	        System.out.println("🌐 Browser initialized: " + browser);
//	 
//	        String baseUrl = properties.getProperty("appURL");
//
//	        driver.get(baseUrl);
//
//	        System.out.println("🔗 Navigated to: " + baseUrl);
//
//	    }
//	 
//	    // Runs before each scenario
//
//	    @Before
//
//	    public void beforeScenario(Scenario scenario) {
//
//	        System.out.println("🔍 Starting scenario: " + scenario.getName());
//	 
//	        if (scenario.getSourceTagNames().contains("@Retry")) {
//
//	            retryScenario(() -> {
//
//	                // Any setup logic here if needed
//
//	            }, 2);
//
//	        }
//
//	    }
//	 
//	    // Runs after each scenario
//
//	    @After
//
//	    public void afterScenario(Scenario scenario) {
//
//	        if (scenario.isFailed()) {
//
//	            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//
//	            // Attach screenshot to report tool if needed
//
//	            System.out.println("📸 Screenshot captured for failed scenario: " + scenario.getName());
//
//	        }
//	 
//	        System.out.println("✅ Finished scenario: " + scenario.getName());
//
//	    }
//	 
//	    // Runs once after all scenarios
//
//	    @AfterAll
//	    public static void globalTeardown() {
//	        if (driver != null) {
//	            try {
//	                driver.quit();
//	                System.out.println("🛑 Browser closed after all scenarios.");
//	            } catch (Exception e) {
//	                System.out.println("⚠️ Error while quitting driver: " + e.getMessage());
//	            }
//	        }
//	    }
//
//	 
//	    // Retry logic for flaky setup
//
//	    public static void retryScenario(Runnable runnable, int maxRetries) {
//
//	        int attempt = 0;
//
//	        while (attempt <= maxRetries) {
//
//	            try {
//
//	                runnable.run();
//
//	                return;
//
//	            } catch (Exception e) {
//
//	                attempt++;
//
//	                System.out.println("🔁 Scenario retry #" + attempt + ": " + e.getMessage());
//
//	                if (attempt > maxRetries) {
//
//	                    throw new RuntimeException("❌ Scenario setup failed after " + maxRetries + " retries", e);
//
//	                }
//
//	            }
//
//	        }
//
//	    }
//
//	}
//
//	 
//
//
