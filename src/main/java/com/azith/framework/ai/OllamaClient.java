package com.azith.framework.ai;

import com.azith.framework.ai.model.FailureContext;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class OllamaClient implements AIClient {

    private static final String OLLAMA_URL =
            "http://localhost:11434/api/generate";

    private static final String MODEL = "mistral";

    private final HttpClient httpClient =
            HttpClient.newHttpClient();

    @Override
    public String analyze(FailureContext context) {

        String prompt =
                PromptBuilder.buildFailurePrompt(context);

        String requestBody =
                """
                {
                  "model":"%s",
                  "prompt":%s,
                  "stream":false
                }
                """.formatted(
                        MODEL,
                        escapeJson(prompt));

        HttpRequest request =
                HttpRequest.newBuilder()
                        .uri(URI.create(OLLAMA_URL))
                        .header(
                                "Content-Type",
                                "application/json")
                        .POST(
                                HttpRequest.BodyPublishers
                                        .ofString(requestBody))
                        .build();

        try {

            HttpResponse<String> response =
                    httpClient.send(
                            request,
                            HttpResponse.BodyHandlers.ofString());

            return extractResponse(response.body());

        } catch (IOException | InterruptedException e) {

            Thread.currentThread().interrupt();

            return "AI Analysis Failed : "
                    + e.getMessage();
        }
    }

    private String escapeJson(String value) {

        return "\"" +
                value.replace("\\", "\\\\")
                        .replace("\"", "\\\"")
                        .replace("\n", "\\n")
                        .replace("\r", "") +
                "\"";
    }

    private String extractResponse(String json) {

        String key = "\"response\":\"";

        int start = json.indexOf(key);

        if (start == -1) {

            return "Unable to parse AI response.";
        }

        start += key.length();

        int end = json.indexOf("\",\"done\"", start);

        if (end == -1) {

            return "Unable to parse AI response.";
        }

        return json.substring(start, end)
                .replace("\\n", System.lineSeparator())
                .replace("\\\"", "\"");
    }
}