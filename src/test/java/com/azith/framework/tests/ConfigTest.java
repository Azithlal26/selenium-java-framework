package com.azith.framework.tests;

import com.azith.framework.listeners.RetryAnalyzer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import com.azith.framework.utilities.ConfigReader;


public class ConfigTest {

    private static final Logger logger =
            LogManager.getLogger(
                    ConfigTest.class);

    @Test
    public void readProperties() {
        logger.info(ConfigReader.getProperty("url"));
        logger.info(ConfigReader.getProperty("browser"));
        logger.info(ConfigReader.getProperty("username"));

    }
}
