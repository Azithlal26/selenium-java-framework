package com.azith.framework.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestConfig {

    private static final Logger logger =
            LogManager.getLogger(
                    TestConfig.class);

    public static void main(String[] args) {

        logger.info(
                ConfigReader.getProperty("browser"));

        logger.info(
                ConfigReader.getProperty("url"));
    }
}