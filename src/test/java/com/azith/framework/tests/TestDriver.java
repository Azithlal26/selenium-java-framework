package com.azith.framework.tests;

import com.azith.framework.factory.DriverFactory;
import com.azith.framework.utilities.TestConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestDriver {

    private static final Logger logger =
            LogManager.getLogger(
                    TestDriver.class);

    public static void main(String[] args) {

        DriverFactory.setDriver();

        DriverFactory.getDriver()
                .get("https://www.google.com");

        logger.info(
                DriverFactory.getDriver().getTitle());

        DriverFactory.quitDriver();
    }
}