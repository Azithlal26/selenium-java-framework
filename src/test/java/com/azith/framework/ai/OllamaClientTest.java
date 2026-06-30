package com.azith.framework.ai;

import com.azith.framework.ai.model.FailureContext;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OllamaClientTest {

    @Test
    void shouldReturnAIResponse() throws Exception {

        HttpClient client = mock(HttpClient.class);

        HttpResponse<String> response = mock(HttpResponse.class);

        when(response.body()).thenReturn("""
        {
          "response":"Locator is incorrect",
          "done":true
        }
        """);

        when(client.send(
                any(HttpRequest.class),
                any(HttpResponse.BodyHandler.class)))
                .thenReturn(response);

        FailureContext context = new FailureContext();

        context.setTestName("Login");

        context.setBrowser("chrome");

        context.setEnvironment("qa");

        context.setExceptionType("NoSuchElementException");

        context.setExceptionMessage("Unable to locate");

        context.setStackTrace("stack");

        OllamaClient ollama =
                new OllamaClient(client);

        String result =
                ollama.analyze(context);

        assertTrue(result.contains("Locator is incorrect"));
    }

    @Test
    void shouldHandleIOException() throws Exception {

        HttpClient client = mock(HttpClient.class);

        when(client.send(
                any(HttpRequest.class),
                any(HttpResponse.BodyHandler.class)))
                .thenThrow(new IOException("Connection refused"));

        FailureContext context = new FailureContext();

        OllamaClient ollama =
                new OllamaClient(client);

        String result =
                ollama.analyze(context);

        assertTrue(result.contains("AI Analysis Failed"));
    }

    @Test
    void shouldHandleInterruptedException() throws Exception {

        HttpClient client = mock(HttpClient.class);

        when(client.send(
                any(HttpRequest.class),
                any(HttpResponse.BodyHandler.class)))
                .thenThrow(new InterruptedException("Interrupted"));

        FailureContext context = new FailureContext();

        OllamaClient ollama =
                new OllamaClient(client);

        String result =
                ollama.analyze(context);

        assertTrue(result.contains("AI Analysis Failed"));
    }

    @Test
    void shouldHandleInvalidJson() throws Exception {

        HttpClient client = mock(HttpClient.class);

        HttpResponse<String> response =
                mock(HttpResponse.class);

        when(response.body()).thenReturn("""
        {
            "invalid":"response"
        }
        """);

        when(client.send(
                any(HttpRequest.class),
                any(HttpResponse.BodyHandler.class)))
                .thenReturn(response);

        FailureContext context = new FailureContext();

        OllamaClient ollama =
                new OllamaClient(client);

        String result =
                ollama.analyze(context);

        assertEquals(
                "Unable to parse AI response.",
                result);
    }

    @Test
    void shouldHandleEmptyResponse() throws Exception {

        HttpClient client = mock(HttpClient.class);

        HttpResponse<String> response =
                mock(HttpResponse.class);

        when(response.body()).thenReturn("");

        when(client.send(
                any(HttpRequest.class),
                any(HttpResponse.BodyHandler.class)))
                .thenReturn(response);

        FailureContext context = new FailureContext();

        OllamaClient ollama =
                new OllamaClient(client);

        String result =
                ollama.analyze(context);

        assertEquals(
                "Unable to parse AI response.",
                result);
    }

}