package com.urbanladder.TestRunner;

// Importing TestNG interfaces and annotations
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

// Importing Cucumber's TestNG integration classes
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

// Configuring Cucumber options
@CucumberOptions(
        features = {"src/test/resources/features"}
         , // Location of feature files
        glue = {"com.urbanladder.stepdefinitions","com.urbanladder.Hooks"}, // Package containing step definitions
       
        plugin = {
                "pretty", // Console output in readable format
                "html:test-output/cucumber-reports/cucumber.html", // HTML report
                "json:test-output/cucumber-reports/cucumber.json", // JSON report
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" // Allure report integration
        },
        monochrome = false 
)
public class TestRunner extends AbstractTestNGCucumberTests {
    
    // This method runs before each test method
    @BeforeMethod
    @Parameters({"os", "browser"}) // Accepts parameters from testng.xml or command line
    public void setUp(String os, String browser, ITestContext context) {
        // Stores OS and browser info in the test context for use in tests
        context.setAttribute("os", os);
        context.setAttribute("browser", browser);
    }
}


//package com.urbanladder.TestRunner;
//
//import org.testng.annotations.AfterSuite;
//
//import com.urbanladder.utils.AllureOpener;
//
//import io.cucumber.testng.AbstractTestNGCucumberTests;
//import io.cucumber.testng.CucumberOptions;
// 
//@CucumberOptions(
//    features = { "src/test/resources/features"  // Path to your .feature files
//    },
//   glue = { "com.urbanladder.stepdefinitions", "com.urbanladder.Hooks" }, // Package with step definitions
//		   plugin = {
//			        "pretty",
//			        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
//			        "html:target/cucumber-reports.html",
//			        "json:target/cucumber.json",
//			        "rerun:target/failed_scenarios.txt"
//			    },
//    monochrome = false
//)
//public class TestRunner extends AbstractTestNGCucumberTests {
//	
//	@AfterSuite
//	public void afterSuite()
//	{
//		AllureOpener.openAllureReport();
//	}
//}