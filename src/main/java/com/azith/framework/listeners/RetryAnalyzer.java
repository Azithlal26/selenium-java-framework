package com.azith.framework.listeners;

import com.azith.framework.factory.DriverFactory;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private static final Logger logger =
            LogManager.getLogger(
                    RetryAnalyzer.class);

    private int retryCount = 0;

    private static final int MAX_RETRY_COUNT = 2;

    @Override
    public boolean retry(ITestResult result) {

        if (retryCount < MAX_RETRY_COUNT) {

            retryCount++;

            logger.info(
                    "Retrying Test : "
                            + result.getName()
                            + " | Attempt : "
                            + retryCount);

            // Add retry information to Allure
            Allure.step(
                    "Retrying " + result.getName()
                            + " (Attempt " + retryCount + ")"
            );

            return true;
        }

        return false;
    }
}