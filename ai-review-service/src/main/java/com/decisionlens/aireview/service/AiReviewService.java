package com.decisionlens.aireview.service;

import com.decisionlens.aireview.model.AiCritique;
import com.decisionlens.aireview.model.DecisionContext;
import com.decisionlens.aireview.model.PromptTemplate;
import com.decisionlens.aireview.provider.LLMProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AiReviewService {

    private final PromptManager promptManager;
    private final LLMProvider llmProvider;

    public AiCritique reviewDecision(DecisionContext decisionContext) {
        log.info("Starting AI review for decision: {}", decisionContext.getTitle());

        PromptTemplate promptTemplate = promptManager.getLatestPrompt();
        String finalPrompt = constructPrompt(promptTemplate, decisionContext);

        log.debug("Generated prompt: {}", finalPrompt);

        return llmProvider.analyze(finalPrompt);
    }

    private String constructPrompt(PromptTemplate template, DecisionContext context) {
        String content = template.getTemplate();
        content = content.replace("{{title}}", context.getTitle() != null ? context.getTitle() : "");
        content = content.replace("{{content}}", context.getContent() != null ? context.getContent() : "");
        return content;
    }
}
