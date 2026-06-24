package com.azith.framework.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {

            String reportPath =
                    "reports/ExtentReport.html";

            ExtentSparkReporter spark =
                    new ExtentSparkReporter(reportPath);

            spark.config()
                    .setDocumentTitle(
                            "Automation Report");

            spark.config()
                    .setReportName(
                            "Selenium Framework Report");

            extent = new ExtentReports();

            extent.attachReporter(spark);
        }

        return extent;
    }
}