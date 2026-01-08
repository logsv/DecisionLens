package com.decisionlens.aireview.service;

import com.decisionlens.aireview.model.PromptTemplate;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PromptManagerImpl implements PromptManager {

    private final Map<String, PromptTemplate> prompts = new HashMap<>();
    private static final String LATEST_VERSION = "v1.0";

    @PostConstruct
    public void init() {
        String templateContent = """
                Analyze the following decision:
                Title: {{title}}
                Content: {{content}}
                
                Provide a critique covering:
                1. Trade-offs (pros and cons)
                2. Potential blind spots or risks
                3. Clarifying questions that should be asked
                
                Return the response in JSON format.
                """;

        prompts.put("v1.0", PromptTemplate.builder()
                .version("v1.0")
                .description("Initial prompt version")
                .template(templateContent)
                .build());
    }

    @Override
    public PromptTemplate getPrompt(String version) {
        return prompts.getOrDefault(version, getLatestPrompt());
    }

    @Override
    public PromptTemplate getLatestPrompt() {
        return prompts.get(LATEST_VERSION);
    }
}
