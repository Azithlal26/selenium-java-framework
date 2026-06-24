package com.azith.framework.pages;

import com.azith.framework.utilities.WaitUtility;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.apache.logging.log4j.Logger;

public class LoginPage extends BasePage {

    private static final Logger logger =
            LogManager.getLogger(
                    LoginPage.class);

    private By username =
            By.id("user-name");

    private By password =
            By.id("password");

    private By loginButton =
            By.id("login-button");

    private By errorMessage =
            By.cssSelector("[data-test='error']");

    public void enterUsername(String user) {

        logger.info(
                "Entering Username");
        type(username, user);
    }

    public void enterPassword(String pass) {

        logger.info(
                "Entering Password");
        type(password, pass);
    }

    public void clickLogin() {

        logger.info(
                "Clicking Login Button");
        click(loginButton);
    }

    public void login(String user,
                      String pass) {

        enterUsername(user);

        enterPassword(pass);

        clickLogin();
    }

    public String getErrorMessage() {

        return getText(errorMessage);
    }
}