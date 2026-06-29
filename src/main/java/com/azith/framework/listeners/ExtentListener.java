package com.azith.framework.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.azith.framework.ai.model.FailureContext;
import com.azith.framework.utilities.ExtentManager;
import com.azith.framework.utilities.ScreenshotUtility;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentListener
        implements ITestListener {

    private static ExtentReports extent =
            ExtentManager.getInstance();

    private static ThreadLocal<ExtentTest>
            test = new ThreadLocal<>();

    @Override
    public void onTestStart(
            ITestResult result) {

        ExtentTest extentTest =
                extent.createTest(
                        result.getName());

        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(
            ITestResult result) {

        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(
            ITestResult result) {

        String screenshotPath =
                ScreenshotUtility
                        .captureScreenshot(
                                result.getName());

        test.get().fail(
                result.getThrowable());

        // AI Summary
        FailureContext context = new FailureContext();

        context.setTestName(result.getName());

        context.setBrowser(
                System.getProperty("browser"));

        context.setEnvironment(
                System.getProperty("env"));

        context.setScreenshotPath(screenshotPath);

        context.setException(
                result.getThrowable());

        String aiSummary =
                AIFailureAnalyzer.analyze(context);

        test.get().info("<pre>" + aiSummary + "</pre>");

        test.get()
                .addScreenCaptureFromPath(
                        screenshotPath);
    }

    @Override
    public void onTestSkipped(
            ITestResult result) {

        if (result.getThrowable() != null) {

            test.get().skip(
                    result.getThrowable());

        } else {

            test.get().skip(
                    "Test Skipped");
        }
    }

    @Override
    public void onFinish(
            org.testng.ITestContext context) {

        extent.flush();
    }
}