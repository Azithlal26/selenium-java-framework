package com.azith.framework.utilities;

import com.azith.framework.factory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtility {

    private static final int WAIT_TIME = 20;

    public static WebElement waitForVisibility(By locator) {

        WebDriverWait wait =
                new WebDriverWait(
                        DriverFactory.getDriver(),
                         Duration.ofSeconds(WAIT_TIME));

        return wait.until(
                ExpectedConditions
                        .visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickable(By locator) {

        WebDriverWait wait =
                new WebDriverWait(
                        DriverFactory.getDriver(),
                        Duration.ofSeconds(WAIT_TIME));

        return wait.until(
                ExpectedConditions
                        .elementToBeClickable(locator));
    }

    public static void waitForUrlContains(String text) {

        WebDriverWait wait =
                new WebDriverWait(
                        DriverFactory.getDriver(),
                        Duration.ofSeconds(WAIT_TIME));

        wait.until(
                ExpectedConditions.urlContains(text));
    }
}