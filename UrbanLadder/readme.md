# Display BookShelves — Selenium + Cucumber + TestNG Automation Framework

A robust Java automation framework for validating core user journeys on [Justdial](https://justdial.com) and extracting service details. It supports both local and Selenium Grid execution, BDD-style test definitions with Cucumber, TestNG orchestration with retries, data-driven testing via Excel, JSON exports, and rich reporting with Allure, ExtentReports, and Cucumber HTML.

---

## Tech Stack

- Java 21  
- Maven  
- Selenium 4  
- Cucumber JVM 7 (Gherkin BDD)  
- TestNG  
- Allure Reports + ExtentReports + Cucumber HTML  
- Apache POI (Excel)  
- Log4j2  
- JSON-simple  

---

##  Project Structure
```
DisplayBookShelves/
├── pom.xml
├── testng.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── base/
│   │   │   ├── pages/
│   │   │   └── utilities/
│   │   └── resources/
│   │       ├── config.properties
│   │       └── log4j2.xml
│   └── test/
│       ├── java/
│       │   ├── hooks/
│       │   ├── stepDefinitions/
│       │   ├── testRunner/
│       │   └── reRun/
│       └── resources/
│           ├── features/
│           ├── extent.properties
│           └── allure.properties
├── selenium_grid/
├── testdata/
├── output/
├── Report/
└── target/
```

---

## Key Capabilities

- Page Object Model with explicit waits and JS-safe interactions  
- Local and Remote (Selenium Grid) execution  
- Cucumber BDD with TestNG runner  
- Retry failed tests once via TestNG listeners  
- Data-driven testing via Excel  
- JSON export of service results  
- Automatic report generation:
  - Allure (requires CLI)
  - Extent Spark HTML
  - Cucumber HTML  
- Screenshots on failure attached to Allure and Cucumber reports  

---

## Prerequisites

- JDK 21  
- Maven 3.8+  
- Google Chrome or Microsoft Edge  
- [Allure CLI](https://docs.qameta.io/allure/) installed and configured  
- Optional: Selenium Grid (for remote execution)  

---

##  Configuration

Edit `src/main/resources/config.properties`:

```properties
environment = local | remote
remote.url = http://localhost:4444
app.url = https://justdial.com
implicit.wait = 5
explicit.wait = 10
test.data.file = testdata/test_data.xlsx
test.data.sheet.name = ServiceData
total.number.of.services = 10
expected.votes = 100
json.file.path = output/result.json
allure.bat.path = C:\\path\\to\\allure.bat
```

##  Running Tests

1. Maven CLI
mvn clean test

#2. From IDE
Right-click testng.xml and run

Or run TestRunner.java directly

# 3. Remote Execution (Selenium Grid)

Set environment=remote in config.properties

Start Grid using .bat scripts in selenium_grid/
Run:
mvn clean test

#  Feature Coverage

category_display.feature: Validate category count

search_functionality.feature: Search with valid/invalid inputs

filter_functionality.feature: Apply filters and validate

show_number_popup.feature: Validate contact popup

service_page_query.feature: Submit queries and handle OTP/errors

excel_reading.feature: Data-driven service extraction

retrieve_items.feature: Filter services by vote threshold

## Data-Driven Testing

reading the data from excel sheet,json file , properties file
 writting the data to excel sheet
 

#  Reports
Automatically opened post-suite (Windows only):

Allure: target/allure-report

# Logging & Screenshots
Log4j2 config: log4j2.xml

Screenshots on failure attached to Allure and Cucumber reports

# Retry Logic
Failed tests retried once via RetryListener and RetryAnalyzer

#Troubleshooting Tips
Allure not opening: Check allure.bat.path and CLI installation

Grid errors: Ensure remote.url is reachable

Popups blocking actions: Validate UI hasn't changed

Timing issues: Tune implicit.wait and explicit.wait

Feature selection: Update features and tags in TestRunner.java




