package com.azith.framework.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {

            String browser =
                    System.getProperty("browser", "chrome");

            new File("reports/" + browser).mkdirs();

            String reportPath =
                    "reports/" + browser + "/ExtentReport.html";

            ExtentSparkReporter spark =
                    new ExtentSparkReporter(reportPath);

            spark.config().setDocumentTitle("Automation Report");
            spark.config().setReportName("Selenium Framework Report");

            extent = new ExtentReports();
            extent.attachReporter(spark);
        }

        return extent;
    }
}