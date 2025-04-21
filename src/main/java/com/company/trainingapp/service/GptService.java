package com.company.trainingapp.service;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class GptService {

    // Not used in mock, but ready for future use
    private final WebClient webClient;


    public GptService(@Value("${openai.api.key}") String apiKey) {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.openai.com/v1/chat/completions")
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .build();
    }


    public String ask(String prompt) {
        System.out.println("🧪 Mock GPT called with prompt: " + prompt);
        return "1. Advanced Java\n2. Spring Boot Fundamentals\n3. Microservices Architecture";
    }

    /*
    // 🧠 REAL version (commented out for now)
    public String ask(String prompt) {
        Map<String, Object> request = Map.of(
                "model", "gpt-3.5-turbo",
                "messages", List.of(Map.of("role", "user", "content", prompt))
        );

        return webClient.post()
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    List choices = (List) response.get("choices");
                    Map choice = (Map) choices.get(0);
                    Map message = (Map) choice.get("message");
                    return (String) message.get("content");
                })
                .block(); // Simple blocking for now
    }
    */
}
