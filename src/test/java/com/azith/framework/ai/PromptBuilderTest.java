package com.azith.framework.ai;

import com.azith.framework.ai.model.FailureContext;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PromptBuilderTest {

    @Test
    void shouldGeneratePromptSuccessfully() {

        FailureContext context = new FailureContext();

        context.setTestName("Login Test");
        context.setBrowser("chrome");
        context.setEnvironment("qa");
        context.setExceptionType("NoSuchElementException");
        context.setExceptionMessage("Unable to locate element");
        context.setStackTrace("Stack Trace Sample");

        String prompt =
                PromptBuilder.buildFailurePrompt(context);

        assertNotNull(prompt);

        assertTrue(prompt.contains("Login Test"));
        assertTrue(prompt.contains("chrome"));
        assertTrue(prompt.contains("qa"));
        assertTrue(prompt.contains("NoSuchElementException"));
        assertTrue(prompt.contains("Unable to locate element"));
        assertTrue(prompt.contains("Stack Trace Sample"));

        assertTrue(prompt.contains("Root Cause"));
        assertTrue(prompt.contains("Possible Reasons"));
        assertTrue(prompt.contains("Suggested Fix"));
        assertTrue(prompt.contains("Best Practice"));
    }

    @Test
    void shouldHandleEmptyFailureContext() {

        FailureContext context = new FailureContext();

        String prompt =
                PromptBuilder.buildFailurePrompt(context);

        assertNotNull(prompt);
        assertFalse(prompt.isEmpty());
    }
}