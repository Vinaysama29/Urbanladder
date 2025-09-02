package com.urbanladder.rerun;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
 
/**
* RetryAnalyzer is used to automatically retry failed test cases in TestNG.
* Implements IRetryAnalyzer interface to define custom retry logic.
*/
public class RetryAnalyzer implements IRetryAnalyzer {
 
    // Counter to track the number of retry attempts
    private int retryCount = 0;
 
    // Maximum number of retry attempts allowed
    private static final int maxRetryCount = 1; // You can customize this value
 
    /**
     * This method is invoked by TestNG when a test fails.
     * It returns true if the test should be retried, false otherwise.
     *
     * @param result The result of the test method execution
     * @return boolean indicating whether to retry the test
     */
    @Override
    public boolean retry(ITestResult result) {
        // Retry only if the test failed and retry count is less than max allowed
        if (!result.isSuccess() && retryCount < maxRetryCount) {
            retryCount++; // Increment retry count
            System.out.println("Retrying failed scenario: " + result.getName() + " | Attempt #" + retryCount);
            return true; // Retry the test
        }
        return false; // Do not retry
    }
}