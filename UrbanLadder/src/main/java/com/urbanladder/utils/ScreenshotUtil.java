package com.urbanladder.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Utility class for taking screenshots
public class ScreenshotUtil {

    // Take screenshot and save to specified path
    public static String getScreenshot(WebDriver driver, String name) {
        try {
            File screenshotDir = new File(ConfigReader.getScreenshotPath());
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }
            String filePath = ConfigReader.getScreenshotPath() + getScreenshotName(name);
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
            File destFile = new File(filePath);
            FileUtils.copyFile(sourceFile, destFile);
            System.out.println("Screenshot saved: " + filePath);
            return filePath;
        } catch (IOException e) {
            System.out.println("Failed to save screenshot: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private static String getScreenshotName(String name) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
        return name + "_" + LocalDateTime.now().format(formatter) + ".png";
    }
}

