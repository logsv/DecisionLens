package com.decisionlens.aireview.service;

import com.decisionlens.aireview.model.PromptTemplate;

public interface PromptManager {
    PromptTemplate getPrompt(String version);
    PromptTemplate getLatestPrompt();
}
