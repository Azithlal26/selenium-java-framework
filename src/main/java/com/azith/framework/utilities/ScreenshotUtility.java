package com.azith.framework.utilities;

import com.azith.framework.factory.DriverFactory;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtility {

    private static final Logger logger =
            LogManager.getLogger(
                    ScreenshotUtility.class);

    @Attachment(
            value = "Failure Screenshot",
            type = "image/png"
    )
    public static byte[] attachScreenshot(byte[] screenshot) {

        return screenshot;
    }

    public static String captureScreenshot(String testName) {

        if (DriverFactory.getDriver() == null) {

            logger.info(
                    "Driver is null. Screenshot skipped."
            );

            return "";
        }

        String timestamp =
                LocalDateTime.now()
                        .format(
                                DateTimeFormatter.ofPattern(
                                        "yyyyMMdd_HHmmss"));

        String filePath =
                "screenshots/"
                        + testName
                        + "_"
                        + timestamp
                        + ".png";

        File source =
                ((TakesScreenshot)
                        DriverFactory.getDriver())
                        .getScreenshotAs(
                                OutputType.FILE);

        try {

            FileUtils.copyFile(
                    source,
                    new File(filePath));

        } catch (IOException e) {

            throw new RuntimeException(e);
        }

        return filePath;
    }
}