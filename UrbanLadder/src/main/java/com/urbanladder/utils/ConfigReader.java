package com.urbanladder.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Utility class to read configuration values from properties files.
 * Supports both general configuration and Allure-specific configuration.
 */
public class ConfigReader {

    private static final Logger logger = LogManager.getLogger(ConfigReader.class); // Logger for debugging and error logging

    private static Properties properties; // Stores general configuration properties
    private static final String CONFIG_FILE_PATH = "src/main/resources/config.properties"; // File path to the main config file

    private static Properties allureProperties; // Stores Allure reporting-related configuration
    private static final String ALLURE_CONFIG_FILES = "src/test/resources/allure.properties"; // File path to the Allure config file

    // Static initializer block - runs only once when the class is first accessed
    static {
        loadProperties();          // Load general config properties
        loadAllureProperties();    // Load Allure-specific config properties
    }

    /**
     * Loads general configuration properties from the config.properties file.
     */
    private static void loadProperties() {
        try {
            properties = new Properties(); // Create a new Properties object
            FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE_PATH); // Open file stream
            properties.load(fileInputStream); // Load properties from file
            fileInputStream.close(); // Close stream after loading
            logger.info("Configuration properties loaded successfully");
        } catch (IOException e) {
            logger.error("Failed to load configuration properties: {}", e.getMessage());
            throw new RuntimeException("Configuration file not found", e);
        }
    }

    /**
     * Loads Allure-specific properties from the allure.properties file.
     */
    private static void loadAllureProperties() {
        try {
            allureProperties = new Properties(); // Create a new Properties object
            FileInputStream fileInputStream = new FileInputStream(ALLURE_CONFIG_FILES); // Open file stream
            allureProperties.load(fileInputStream); // Load properties from file
            fileInputStream.close(); // Close stream after loading
            logger.info("Allure properties loaded successfully");
        } catch (IOException e) {
            logger.error("Failed to load Allure properties: {}", e.getMessage());
            throw new RuntimeException("Allure configuration file not found", e);
        }
    }

    /**
     * Gets a value from the general config file using a provided key.
     * @param key The key to look up.
     * @return The corresponding value or null if not found.
     */
    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            logger.warn("Property not found for key: {}", key);
        }
        return value;
    }

    /**
     * Gets a value from the Allure config file using a provided key.
     * @param key The key to look up.
     * @return The corresponding value or null if not found.
     */
    public static String getAllureProperty(String key) {
        String value = allureProperties.getProperty(key);
        if (value == null) {
            logger.warn("Property not found for key: {}", key);
        }
        return value;
    }

    // Helper methods to fetch specific config values for ease of use

    public static String getBrowser() {
        return getProperty("browser");
    }

    public static String getAppUrl() {
        return getProperty("app.url");
    }

    public static String getExecutionEnv() {
        return getProperty("execution_env");
    }

    public static int getImplicitWait() {
        return Integer.parseInt(getProperty("implicit.wait"));
    }

    public static int getExplicitWait() {
        return Integer.parseInt(getProperty("explicit.wait"));
    }

    public static int getPageLoadTimeout() {
        return Integer.parseInt(getProperty("page.load.timeout"));
    }

    public static String getTestDataFile() {
        return getProperty("test.data.file");
    }

    public static String getTestDataSheetNameSearch() {
        return getProperty("test.data.sheetname.search");
    }

    public static String getTestDataSheetNameForm() {
        return getProperty("test.data.sheetname.form");
    }

    public static String getTestDataSheetNameResults() {
        return getProperty("test.data.sheetname.results");
    }

    public static String getScreenshotPath() {
        return getProperty("screenshot.path");
    }

    public static String getTestDataFileJson() {
        return getProperty("test.data.file.json");
    }
    
  public static String getUserEmail() {
 return getProperty("user.email");
}

public static String getUserPassword() {
  return getProperty("user.password");
}

}


//package com.urbanladder.utils;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.Properties;
//
//public class ConfigReader {
//
//    private static Properties properties;
//    private static final String CONFIG_FILE_PATH = "src/main/resources/config.properties";
//
//    static {
//        loadProperties();
//    }
//
//    private static void loadProperties() {
//        try {
//            properties = new Properties();
//            FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE_PATH);
//            properties.load(fileInputStream);
//            fileInputStream.close();
//            System.out.println("Configuration properties loaded successfully");
//        } catch (IOException e) {
//            System.out.println("Failed to load configuration properties: " + e.getMessage());
//            throw new RuntimeException("Configuration file not found", e);
//        }
//    }
//
//    public static String getProperty(String key) {
//        String value = properties.getProperty(key);
//        if (value == null) {
//            System.out.println("Property not found for key: " + key);
//        }
//        return value;
//    }
//
//    public static String getBrowser() {
//        return getProperty("browser");
//    }
//
//    public static String getAppUrl() {
//        return getProperty("appURL");
//    }
//
//    public static int getImplicitWait() {
//        return Integer.parseInt(getProperty("implicit.wait"));
//    }
//
//    public static int getExplicitWait() {
//        return Integer.parseInt(getProperty("explicit.wait"));
//    }
//
//    public static int getPageLoadTimeout() {
//        return Integer.parseInt(getProperty("page.load.timeout"));
//    }
//
//    public static String getTestDataFile() {
//        return getProperty("test.data.file");
//    }
//
//    public static String getSheet() {
//        return getProperty("test.data.sheet");
//    }
//
//    public static String getExtentReportPath() {
//        return getProperty("extent.report.path");
//    }
//
//    public static String getScreenshotPath() {
//        return getProperty("screenshot.path");
//    }
//
//    public static String getExtentReportPathScreenshot() {
//        return getProperty("extent.screenshot.path");
//    }
//
//    public static String getVideoRecordingPath() {
//        return getProperty("video.recording.path");
//    }
//
//    public static String getUserEmail() {
//        return getProperty("user.email");
//    }
//
//    public static String getUserPassword() {
//        return getProperty("user.password");
//    }
//
//	public static String getExecutionEnv() {
//		// TODO Auto-generated method stub
//		return getProperty("execution_env");
//	}
//}
