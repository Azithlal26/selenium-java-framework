package com.azith.framework.pages;

import com.azith.framework.utilities.WaitUtility;
import org.openqa.selenium.By;

public class BasePage {

    protected void click(By locator) {

        WaitUtility
                .waitForClickable(locator)
                .click();
    }

    protected void type(By locator,
                        String text) {

        WaitUtility
                .waitForVisibility(locator)
                .sendKeys(text);
    }

    protected String getText(By locator) {

        return WaitUtility
                .waitForVisibility(locator)
                .getText();
    }

    protected boolean isDisplayed(By locator) {

        return WaitUtility
                .waitForVisibility(locator)
                .isDisplayed();
    }
}