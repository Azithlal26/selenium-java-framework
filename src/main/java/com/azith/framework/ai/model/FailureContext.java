package com.azith.framework.ai.model;

public class FailureContext {

    private String testName;
    private String browser;
    private String environment;
    private String exceptionType;
    private String exceptionMessage;
    private String stackTrace;

    public FailureContext() {
    }

    public FailureContext(
            String testName,
            String browser,
            String environment,
            String exceptionType,
            String exceptionMessage,
            String stackTrace) {

        this.testName = testName;
        this.browser = browser;
        this.environment = environment;
        this.exceptionType = exceptionType;
        this.exceptionMessage = exceptionMessage;
        this.stackTrace = stackTrace;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }
}