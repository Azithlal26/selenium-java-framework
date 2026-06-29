package com.azith.framework.ai;

import com.azith.framework.ai.model.FailureContext;

public class PromptBuilder {

    private PromptBuilder() {
    }

    public static String buildFailurePrompt(FailureContext context) {

        return """
                You are a Senior QA Automation Engineer.

                Analyze the following Selenium Automation Failure.

                Test Name:
                %s

                Browser:
                %s

                Environment:
                %s

                Exception Type:
                %s

                Exception Message:
                %s

                Stack Trace:
                %s

                Please provide the response in the following format.

                Root Cause:
                -

                Possible Reasons:
                -

                Suggested Fix:
                -

                Best Practice:
                -
                """.formatted(
                context.getTestName(),
                context.getBrowser(),
                context.getEnvironment(),
                context.getExceptionType(),
                context.getExceptionMessage(),
                context.getStackTrace()
        );
    }
}