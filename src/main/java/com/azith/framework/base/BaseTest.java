package com.azith.framework.base;

import com.azith.framework.factory.DriverFactory;
import com.azith.framework.utilities.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Optional;


public class BaseTest {

    private static final Logger logger =
            LogManager.getLogger(
                    DriverFactory.class);

    @BeforeMethod
    @Parameters("browser")
    public void setup(
            @Optional("chrome")
            String browser)
    {

        logger.info(
                "Thread ID = "
                        + Thread.currentThread().getId()
        );

        logger.info(
                "Browser = "
                        + browser);

        DriverFactory.setDriver(browser);

        DriverFactory.getDriver()
                .manage()
                .window()
                .maximize();

        DriverFactory.getDriver()
                .get(ConfigReader.getProperty("url"));
    }

    @AfterMethod
    public void tearDown() {

        DriverFactory.quitDriver();

    }
}