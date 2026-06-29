package com.azith.framework.listeners;

import com.azith.framework.ai.AIClient;
import com.azith.framework.ai.OllamaClient;
import com.azith.framework.ai.model.FailureContext;
import com.azith.framework.factory.DriverFactory;
import com.azith.framework.utilities.ScreenshotUtility;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

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

        logger.info(
                "Invocation Count = "
                        + result.getMethod()
                        .getCurrentInvocationCount());

        FailureContext context = new FailureContext();

        context.setTestName(result.getName());

        context.setBrowser(
                System.getProperty("browser"));

        context.setEnvironment(
                System.getProperty("env"));

        Throwable t =
                result.getThrowable();

        context.setTestName(result.getName());

        context.setBrowser(
                System.getProperty("browser"));

        context.setEnvironment(
                System.getProperty("env"));

        context.setExceptionType(
                t.getClass().getSimpleName());

        context.setExceptionMessage(
                t.getMessage());

        context.setStackTrace(
                t.getMessage());

        AIClient aiClient = new OllamaClient();

        String aiSummary =
                aiClient.analyze(context);

        logger.info("========== AI FAILURE ANALYSIS ==========");

        logger.info(aiSummary);

        logger.info("=========================================");

        Allure.addAttachment(
                "AI Failure Analysis",
                "text/plain",
                aiSummary);

        if (DriverFactory.getDriver() != null) {

            byte[] screenshot =
                    ((TakesScreenshot)
                            DriverFactory.getDriver())
                            .getScreenshotAs(
                                    OutputType.BYTES);

            logger.info("ATTACHING SCREENSHOT TO ALLURE");

            Allure.addAttachment(
                    "Failure Screenshot",
                    "image/png",
                    new ByteArrayInputStream(
                            screenshot),
                    ".png");

        }

        ScreenshotUtility.captureScreenshot(
                result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        logger.info(
                "SKIPPED : "
                        + result.getName());

        logger.info(
                "Current Invocation Count : "
                        + result.getMethod()
                        .getCurrentInvocationCount());
    }
}