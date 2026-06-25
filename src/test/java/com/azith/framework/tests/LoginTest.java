package com.azith.framework.tests;

import com.azith.framework.base.BaseTest;
import com.azith.framework.factory.DriverFactory;
import com.azith.framework.listeners.ExtentListener;
import com.azith.framework.listeners.RetryTransformer;
import com.azith.framework.pages.LoginPage;
import com.azith.framework.pages.ProductsPage;
import com.azith.framework.testdata.TestData;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.azith.framework.listeners.TestListener;
import org.testng.annotations.Listeners;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Allure;
import java.io.ByteArrayInputStream;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

@Listeners({
        TestListener.class,
        ExtentListener.class,
        RetryTransformer.class
})

public class LoginTest extends BaseTest {

    private static final Logger logger =
            LogManager.getLogger(
                    LoginTest.class);

    @Test(
            dataProvider = "loginData",
            dataProviderClass = TestData.class
    )

    @Description("Verify login functionality using multiple user credentials")
    @Owner("Azith")
    @Severity(SeverityLevel.CRITICAL)

    public void verifyLogin(String username, String password, String expectedSuccess) {

        boolean isSuccess =
                Boolean.parseBoolean(expectedSuccess);

        LoginPage loginPage =
                new LoginPage();

        ProductsPage productsPage =
                new ProductsPage();

        loginPage.login(
                username, password);

        if(isSuccess) {

            Assert.assertEquals(
                    productsPage.getPageTitle(),
                    "Products"
            );

            byte[] screenshot =
                    ((TakesScreenshot) DriverFactory.getDriver())
                            .getScreenshotAs(OutputType.BYTES);

            Allure.addAttachment(
                    "Failure Screenshot",
                    "image/png",
                    new ByteArrayInputStream(screenshot),
                    ".png");

            logger.info(
                    "Executing for user: "
                            + username
                            + "On Thread : "
                            + Thread.currentThread().getId()
            );
        }
        else {
            Assert.assertEquals(
                    loginPage.getErrorMessage(),
                    "Epic sadface: Sorry, this user has been locked out."
            );

            logger.info("Negative Login Tested");
        }

    }
}