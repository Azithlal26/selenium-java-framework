package com.azith.framework.ai;

import com.azith.framework.ai.model.FailureContext;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FailureContextTest {

    @Test
    void shouldSetAndGetAllProperties() {

        FailureContext context = new FailureContext();

        context.setTestName("Login Test");
        context.setBrowser("chrome");
        context.setEnvironment("qa");
        context.setExceptionType("NoSuchElementException");
        context.setExceptionMessage("Element not found");
        context.setStackTrace("Stack Trace");

        assertEquals("Login Test", context.getTestName());
        assertEquals("chrome", context.getBrowser());
        assertEquals("qa", context.getEnvironment());
        assertEquals("NoSuchElementException", context.getExceptionType());
        assertEquals("Element not found", context.getExceptionMessage());
        assertEquals("Stack Trace", context.getStackTrace());
    }

    @Test
    void shouldCreateObjectUsingParameterizedConstructor() {

        FailureContext context = new FailureContext(
                "Checkout Test",
                "firefox",
                "uat",
                "TimeoutException",
                "Timeout after 30 seconds",
                "Stack Trace Sample"
        );

        assertEquals("Checkout Test", context.getTestName());
        assertEquals("firefox", context.getBrowser());
        assertEquals("uat", context.getEnvironment());
        assertEquals("TimeoutException", context.getExceptionType());
        assertEquals("Timeout after 30 seconds", context.getExceptionMessage());
        assertEquals("Stack Trace Sample", context.getStackTrace());
    }

    @Test
    void shouldCreateObjectUsingDefaultConstructor() {

        FailureContext context = new FailureContext();

        assertNotNull(context);

        assertNull(context.getTestName());
        assertNull(context.getBrowser());
        assertNull(context.getEnvironment());
        assertNull(context.getExceptionType());
        assertNull(context.getExceptionMessage());
        assertNull(context.getStackTrace());
    }
}