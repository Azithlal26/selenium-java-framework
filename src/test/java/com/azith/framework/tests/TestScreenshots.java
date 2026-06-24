package com.azith.framework.tests;

import com.azith.framework.base.BaseTest;
import com.azith.framework.utilities.ScreenshotUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class TestScreenshots extends BaseTest {

    private static final Logger logger =
            LogManager.getLogger(
                    TestScreenshots.class);

    @Test
    public void testScreenshot() {

        String path =
                ScreenshotUtility
                        .captureScreenshot(
                                "GoogleTest");

        logger.info(path);
    }
}
