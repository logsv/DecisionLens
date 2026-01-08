package com.decisionlens.aireview.provider;

import com.decisionlens.aireview.model.AiCritique;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
public class MockLLMProvider implements LLMProvider {

    @Override
    public AiCritique analyze(String prompt) {
        log.info("Mock LLM received prompt of length: {}", prompt.length());
        
        // In a real implementation, this would call an external API (OpenAI, Anthropic, etc.)
        // and parse the JSON response.
        
        return AiCritique.builder()
                .summary("This decision seems well-structured but lacks detail on failure modes.")
                .tradeOffs(Arrays.asList(
                        "Speed vs. Consistency: The proposed approach favors availability.",
                        "Complexity vs. Maintainability: Introducing a new service adds operational overhead."
                ))
                .blindSpots(Arrays.asList(
                        "Data migration strategy is missing.",
                        "Impact on latency for cross-region requests."
                ))
                .questions(Arrays.asList(
                        "What is the expected peak throughput?",
                        "How will schema evolution be handled?"
                ))
                .confidenceScore(0.85)
                .build();
    }
}
