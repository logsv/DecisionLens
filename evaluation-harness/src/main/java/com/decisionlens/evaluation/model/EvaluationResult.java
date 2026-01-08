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
public class EvaluationResult {
    private String decisionTitle;
    
    private double precision;
    private double recall;
    private double f1Score;
    
    private List<String> falsePositives; // Risks flagged by AI but not in Golden set
    private List<String> falseNegatives; // Risks in Golden set missed by AI
    
    private boolean passed; // Based on threshold
}
