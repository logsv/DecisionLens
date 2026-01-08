package com.decisionlens.decision.dto;

import com.decisionlens.decision.domain.DecisionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDecisionRequest {
    
    private String title;
    private String content;
    private DecisionStatus status;
}
