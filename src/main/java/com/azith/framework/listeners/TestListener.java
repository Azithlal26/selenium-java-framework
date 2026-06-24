package com.azith.framework.listeners;

import com.azith.framework.pages.LoginPage;
import com.azith.framework.utilities.ScreenshotUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static final Logger logger =
            LogManager.getLogger(
                    TestListener.class);

    public TestListener() {
        logger.info("Listeners Loaded");
    }

    @Override
    public void onTestStart(ITestResult result) {

        logger.info(
                "STARTED : "
                        + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        logger.info(
                "PASSED : "
                        + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {

        logger.info(
                "FAILED : "
                        + result.getName());

        ScreenshotUtility.captureScreenshot(
                result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        logger.info(
                "SKIPPED : "
                        + result.getName());
    }
}