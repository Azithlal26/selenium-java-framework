package com.azith.framework.tests;

import com.azith.framework.base.BaseTest;
import com.azith.framework.factory.DriverFactory;
import com.azith.framework.listeners.ExtentListener;
import com.azith.framework.listeners.RetryAnalyzer;
import com.azith.framework.listeners.TestListener;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({
        TestListener.class,
        ExtentListener.class
})
public class AIFailureAnalysisTest extends BaseTest {

    @Test(
            groups = "ai-demo",
            retryAnalyzer = RetryAnalyzer.class
    )

    @Description("Intentional failure to verify AI failure analysis")
    @Owner("Azith")
    @Severity(SeverityLevel.NORMAL)

    public void verifyAIFailureAnalysis() {

        boolean actual =
                DriverFactory.getDriver()
                        .findElement(
                                By.id("add-to-cart-sauce-labs-backpack"))
                        .isDisplayed();

        Assert.assertEquals(
                actual,
                false,
                "Intentional AI Demo Failure : Expected Add To Cart button NOT to be visible."
        );
    }
}