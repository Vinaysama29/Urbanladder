package com.urbanladder.rerun;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
 
/**
* RetryListener is a TestNG listener that automatically applies the
* RetryAnalyzer to all test methods without needing to annotate each test
* individually.
*/
public class RetryListener implements IAnnotationTransformer {
 
    /**
     * This method is called by TestNG during runtime to modify test method annotations.
     * It sets a retry analyzer for each test method, which allows failed tests to be retried.
     *
     * @param annotation The test method's annotation
     * @param testClass The class containing the test method
     * @param constructor The constructor of the test class
     * @param method The test method itself
     */
    @Override
    public void transform(ITestAnnotation annotation,
                          @SuppressWarnings("rawtypes") Class testClass,
                          @SuppressWarnings("rawtypes") Constructor constructor,
                          Method method) {
        // Automatically assign RetryAnalyzer to every test method
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }
}