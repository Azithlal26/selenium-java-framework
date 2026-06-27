package com.azith.framework.testdata;

import com.azith.framework.tests.LoginTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import com.azith.framework.utilities.ExcelUtility;

public class TestData {

    private static final Logger logger =
            LogManager.getLogger(
                    TestData.class);

    @DataProvider(
            name = "loginData",
            parallel = false)

    public Object[][] loginData() throws Exception {

        logger.info("Reading Excel Data");

        Object[][] data =
                ExcelUtility.getTestData(
                        "src/test/resources/testdata/LoginData.xlsx",
                        "LoginData"
                );

        logger.info(
                "Rows Loaded = " + data.length);

        return data;
    }
}