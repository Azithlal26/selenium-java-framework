package com.azith.framework.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties = new Properties();

    static {

        try {

            String env =
                    System.getProperty("env", "qa");

            String configFile =
                    "src/main/resources/config/config-"
                            + env
                            + ".properties";

            FileInputStream fis =
                    new FileInputStream(configFile);

            properties.load(fis);

            System.out.println(
                    "Loaded Environment : "
                            + env);

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to load config file", e);
        }
    }

    public static String getProperty(String key) {

        return properties.getProperty(key);
    }
}