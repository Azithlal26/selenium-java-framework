package com.azith.framework.pages;

import com.azith.framework.factory.DriverFactory;
import com.azith.framework.utilities.WaitUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductsPage extends BasePage {

    private By productsTitle =
            By.className("title");

    public String getPageTitle() {

        // Wait until navigation completes
        WaitUtility.waitForUrlContains("inventory");

        return getText(productsTitle);
    }
}