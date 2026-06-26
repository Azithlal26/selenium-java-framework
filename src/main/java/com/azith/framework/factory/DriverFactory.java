package com.azith.framework.factory;

import com.azith.framework.utilities.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driver =
            new ThreadLocal<>();

    private static final Logger logger =
            LogManager.getLogger(DriverFactory.class);

    public static void setDriver() {

        String browser =
                ConfigReader.getProperty("browser");

        setDriver(browser);
    }

    public static void setDriver(String browser) {

        try {

            String executionMode =
                    ConfigReader.getProperty("executionMode");

            logger.info("Execution Mode = {}", executionMode);
            logger.info("Browser = {}", browser);

            switch (browser.toLowerCase()) {

                case "chrome":

                    ChromeOptions chromeOptions =
                            new ChromeOptions();

                    if (executionMode.equalsIgnoreCase("local")) {

                        logger.info("Launching Local Chrome");

                        driver.set(
                                new ChromeDriver(chromeOptions));

                    } else {

                        logger.info("Launching Remote Chrome");
                        logger.info("Remote URL = {}",
                                ConfigReader.getProperty("remoteUrl"));

                        driver.set(
                                new RemoteWebDriver(
                                        new URL(
                                                ConfigReader.getProperty("remoteUrl")),
                                        chromeOptions));
                    }

                    break;

                case "edge":

                    EdgeOptions edgeOptions =
                            new EdgeOptions();

                    if (executionMode.equalsIgnoreCase("local")) {

                        logger.info("Launching Local Edge");

                        driver.set(
                                new EdgeDriver(edgeOptions));

                    } else {

                        logger.info("Launching Remote Edge");
                        logger.info("Remote URL = {}",
                                ConfigReader.getProperty("remoteUrl"));

                        driver.set(
                                new RemoteWebDriver(
                                        new URL(
                                                ConfigReader.getProperty("remoteUrl")),
                                        edgeOptions));
                    }

                    break;

                case "firefox":

                    FirefoxOptions firefoxOptions =
                            new FirefoxOptions();

                    if (executionMode.equalsIgnoreCase("local")) {

                        logger.info("Launching Local Firefox");

                        driver.set(
                                new FirefoxDriver(firefoxOptions));

                    } else {

                        logger.info("Launching Remote Firefox");
                        logger.info("Remote URL = {}",
                                ConfigReader.getProperty("remoteUrl"));

                        driver.set(
                                new RemoteWebDriver(
                                        new URL(
                                                ConfigReader.getProperty("remoteUrl")),
                                        firefoxOptions));
                    }

                    break;

                default:

                    throw new IllegalArgumentException(
                            "Unsupported browser: " + browser);
            }

        } catch (Exception e) {

            logger.error(
                    "Failed to create browser session", e);

            throw new RuntimeException(
                    "Unable to create WebDriver", e);
        }
    }

    public static WebDriver getDriver() {

        return driver.get();
    }

    public static void quitDriver() {

        if (driver.get() != null) {

            driver.get().quit();
            driver.remove();
        }
    }
}