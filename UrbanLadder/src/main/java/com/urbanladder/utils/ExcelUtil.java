package com.urbanladder.utils;

import java.io.*;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

    private static Workbook workbook;
    public static Sheet sheet;

    // Path to the Excel file
    private static final String filePath = "src/test/resources/testdata/testData.xlsx";

    // Reads a sheet from the Excel file
    public static void readSheet(String sheetName) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Excel file not found: " + filePath);
            return;
        }

        try (FileInputStream fis = new FileInputStream(file)) {
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);
        } catch (IOException e) {
            System.out.println("Error reading Excel file: " + e.getMessage());
        }
    }

    // Creates a sheet if it doesn't exist
    private static void createSheetIfNotExists(String sheetName) {
        sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            sheet = workbook.createSheet(sheetName);
        }
    }

    // Writes product data with dynamic column label
    public static void writeProductData(String sheetName, Map<String, String> productData, String productLabel) {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                workbook = new XSSFWorkbook(fis);
                fis.close();
            } else {
                workbook = new XSSFWorkbook();
            }

            createSheetIfNotExists(sheetName);

            // Clear existing rows
            int lastRow = sheet.getLastRowNum();
            for (int i = lastRow; i >= 0; i--) {
                Row row = sheet.getRow(i);
                if (row != null) sheet.removeRow(row);
            }

            // Write header
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue(productLabel);
            header.createCell(1).setCellValue("Price");

            // Write product data
            int rowIndex = 1;
            for (Map.Entry<String, String> entry : productData.entrySet()) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(entry.getKey());
                row.createCell(1).setCellValue(entry.getValue());
            }

            // Save changes
            FileOutputStream fos = new FileOutputStream(filePath);
            workbook.write(fos);
            fos.close();
            workbook.close();

            System.out.println("Data written successfully to sheet: " + sheetName);
        } catch (IOException e) {
            System.out.println("Error writing to Excel file: " + e.getMessage());
        }
    }

    // Writes a list of product names to the specified sheet
    public static void writeItemList(String sheetName, List<String> items) {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                workbook = new XSSFWorkbook(fis);
                fis.close();
            } else {
                workbook = new XSSFWorkbook();
            }

            createSheetIfNotExists(sheetName);

            // Clear existing rows
            int lastRow = sheet.getLastRowNum();
            for (int i = lastRow; i >= 0; i--) {
                Row row = sheet.getRow(i);
                if (row != null) sheet.removeRow(row);
            }

            // Write header
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Product Name");

            // Write each item
            for (int i = 0; i < items.size(); i++) {
                Row row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(items.get(i));
            }

            // Save changes
            FileOutputStream fos = new FileOutputStream(filePath);
            workbook.write(fos);
            fos.close();
            workbook.close();

            System.out.println("Item list written successfully to sheet: " + sheetName);
        } catch (IOException e) {
            System.out.println("Error writing item list to Excel: " + e.getMessage());
        }
    }
}
