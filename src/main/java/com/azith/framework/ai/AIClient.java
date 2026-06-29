package com.azith.framework.ai;

import com.azith.framework.ai.model.FailureContext;

public interface AIClient {

    String analyze(FailureContext context);

}