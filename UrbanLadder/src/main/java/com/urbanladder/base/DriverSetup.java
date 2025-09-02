package com.urbanladder.base;



import java.net.URI;
import java.time.Duration;
 
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
 
import com.urbanladder.utils.ConfigReader;
 
import io.github.bonigarcia.wdm.WebDriverManager;
 
public class DriverSetup {
	private static final Logger logger = LogManager.getLogger(DriverSetup.class);

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();
 
	private DriverSetup() {}
	/**
     * Initialize WebDriver based on browser configuration
     * 
     * @param operatingSystem(Windows/Mac), browserName Browser name (chrome/edge)
     */
    public static void initializeDriver(String operatingSystem, String browserName) {
        try {
        	if (driver.get() != null) {
                logger.warn("WebDriver already initialized for this thread");
                return; // prevent multiple launches
            }
        	logger.info("Initializing WebDriver for OS: {} and browser: {}", operatingSystem, browserName);
        	WebDriver localDriver;
        	logger.info("Execution environment: {}", ConfigReader.getExecutionEnv());
            if(ConfigReader.getExecutionEnv().equalsIgnoreCase("remote")) {
            	DesiredCapabilities capabilities = new DesiredCapabilities();
            	switch(operatingSystem.toLowerCase()) {
            		case "windows":
            			capabilities.setPlatform(Platform.WIN11);
            			break;
            		case "mac":
            			capabilities.setPlatform(Platform.MAC);
            			break;
            		default:
                        throw new IllegalArgumentException("OS not supported: " + operatingSystem);
            	}
            	switch (browserName.toLowerCase()) {
                	case "chrome":
                		capabilities.setBrowserName("chrome");
            			break;
                	case "edge":
                		capabilities.setBrowserName("MicrosoftEdge");
            			break;
            		default:
            			throw new IllegalArgumentException("Browser not supported: " + browserName);
                }
            	URI gridUri = URI.create("http://localhost:4444/wd/hub");
            	localDriver = new RemoteWebDriver(gridUri.toURL(), capabilities);
 
            	
            } else {
            	switch (browserName.toLowerCase()) {
	                case "chrome":
	                    WebDriverManager.chromedriver().setup();
	                    ChromeOptions chromeOptions = new ChromeOptions();
	        	        localDriver = new ChromeDriver(chromeOptions);      
	                    break;
	                case "edge":
	                    WebDriverManager.edgedriver().setup();
	                    EdgeOptions edgeOptions = new EdgeOptions();
	        	        localDriver = new EdgeDriver(edgeOptions);
	                    break;
	                default:
	                    throw new IllegalArgumentException("Browser not supported: " + browserName);
            	}
            }
 
            localDriver.manage().window().maximize();
            // Set timeouts
            localDriver.manage().timeouts().implicitlyWait(
                    Duration.ofSeconds(ConfigReader.getImplicitWait()));
            localDriver.manage().timeouts().pageLoadTimeout(
                    Duration.ofSeconds(ConfigReader.getPageLoadTimeout()));
            driver.set(localDriver);
            wait.set(new WebDriverWait(localDriver, Duration.ofSeconds(ConfigReader.getExplicitWait())));
 
 
            logger.info("WebDriver initialized successfully for thread: {}", Thread.currentThread().getName());
 
        } catch (Exception e) {
            logger.error("Failed to initialize WebDriver: {}", e.getMessage());
            throw new RuntimeException("Driver initialization failed", e);
        }
    }
    /**
     * Navigate to application URL
     */
    public static void navigateToApplication() {
    	try {
    		String url = ConfigReader.getProperty("appURL");
            logger.info("Navigating to application URL: {}", url);
            getDriver().get(url);   		
    	} catch(Exception e) {
    		logger.error("Failed to navigate to application: {}", e.getMessage());
            throw new RuntimeException("Navigation failed", e);
    	}
    }
    /**
     * Close browser and cleanup resources
     */
    public static void tearDown() {
    	try {
            if (driver.get() != null) {
                logger.info("Closing browser for thread: {}", Thread.currentThread().getName());
                driver.get().quit();
                driver.remove();
                wait.remove();
                logger.info("Browser closed successfully for thread");
            }
        } catch (Exception e) {
            logger.error("Error during teardown: {}", e.getMessage());
        }
    }
    /**
     * Get WebDriver instance
     * 
     * @return WebDriver instance
     */
    public static WebDriver getDriver() {
        return driver.get();
    }
 
    /**
     * Get WebDriverWait instance
     * 
     * @return WebDriverWait instance
     */
    public static WebDriverWait getWait() {
        return wait.get();
    }
}
