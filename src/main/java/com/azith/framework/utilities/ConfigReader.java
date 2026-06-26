package com.azith.framework.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final Logger logger =
            LogManager.getLogger(
                    ConfigReader.class);

    private static Properties properties = new Properties();

    static {

        try {

            String env =
                    System.getProperty("env", "qa");

            String configFile =
                    "src/main/resources/config/config-"
                            + env
                            + ".properties";

            logger.info("Loading Config File : " + configFile);

            FileInputStream fis =
                    new FileInputStream(configFile);

            properties.load(fis);

            logger.info("Execution Mode : " + properties.getProperty("executionMode"));
            logger.info("Loaded Environment : " + env);
            logger.info("Remote URL : " + properties.getProperty("remoteUrl"));

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to load config file", e);
        }
    }

    public static String getProperty(String key) {

        return properties.getProperty(key);
    }
}