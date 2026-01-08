package com.decisionlens.aireview.provider;

import com.decisionlens.aireview.model.AiCritique;

public interface LLMProvider {
    AiCritique analyze(String prompt);
}
