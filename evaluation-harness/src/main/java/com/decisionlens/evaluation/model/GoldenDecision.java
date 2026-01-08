package com.decisionlens.evaluation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoldenDecision {
    private String decisionTitle;
    private String decisionContent;
    
    // Expected outcomes
    private List<String> expectedBlindSpots;
    private List<String> expectedTradeOffs;
    
    // Metadata
    private String historicalOutcome; // e.g., "FAILED", "SUCCESS"
    private String postMortemNotes;
}
