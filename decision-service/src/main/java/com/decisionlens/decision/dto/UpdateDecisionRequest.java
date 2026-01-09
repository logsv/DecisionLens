package com.decisionlens.decision.dto;

import com.decisionlens.decision.domain.DecisionStatus;

public class UpdateDecisionRequest {
    
    private String title;
    private String content;
    private DecisionStatus status;

    public UpdateDecisionRequest() {
    }

    public UpdateDecisionRequest(String title, String content, DecisionStatus status) {
        this.title = title;
        this.content = content;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public DecisionStatus getStatus() {
        return status;
    }

    public void setStatus(DecisionStatus status) {
        this.status = status;
    }
}
