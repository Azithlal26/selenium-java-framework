package com.azith.framework.tests;

import com.azith.framework.base.BaseTest;
import com.azith.framework.factory.DriverFactory;
import com.azith.framework.utilities.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class GoogleTest extends BaseTest {

    private static final Logger logger =
            LogManager.getLogger(
                    GoogleTest.class);

    @Test
    public void verifyGoogleTitle() throws InterruptedException {
        logger.info(
                DriverFactory.getDriver().getTitle());
    }
}