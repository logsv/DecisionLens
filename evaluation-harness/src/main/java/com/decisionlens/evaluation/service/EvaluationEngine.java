package com.decisionlens.evaluation.service;

import com.decisionlens.evaluation.model.EvaluationResult;
import com.decisionlens.evaluation.model.GoldenDecision;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class EvaluationEngine {

    // Simple keyword-based semantic matching for "No ML" requirement
    public EvaluationResult evaluate(GoldenDecision golden, List<String> aiGeneratedBlindSpots) {
        Set<String> expectedSet = new HashSet<>(golden.getExpectedBlindSpots());
        Set<String> actualSet = new HashSet<>(aiGeneratedBlindSpots);
        
        List<String> falsePositives = new ArrayList<>();
        List<String> falseNegatives = new ArrayList<>();
        int truePositives = 0;

        // Check for matches (allowing partial matches for text)
        for (String actual : actualSet) {
            boolean matchFound = false;
            for (String expected : expectedSet) {
                if (isMatch(actual, expected)) {
                    matchFound = true;
                    truePositives++;
                    break;
                }
            }
            if (!matchFound) {
                falsePositives.add(actual);
            }
        }

        // Check for misses
        for (String expected : expectedSet) {
            boolean matchFound = false;
            for (String actual : actualSet) {
                if (isMatch(actual, expected)) {
                    matchFound = true;
                    break;
                }
            }
            if (!matchFound) {
                falseNegatives.add(expected);
            }
        }

        double precision = calculatePrecision(truePositives, falsePositives.size());
        double recall = calculateRecall(truePositives, falseNegatives.size());
        double f1 = calculateF1(precision, recall);

        return EvaluationResult.builder()
                .decisionTitle(golden.getDecisionTitle())
                .precision(precision)
                .recall(recall)
                .f1Score(f1)
                .falsePositives(falsePositives)
                .falseNegatives(falseNegatives)
                .passed(f1 > 0.7) // Threshold
                .build();
    }

    private boolean isMatch(String s1, String s2) {
        // Simple case-insensitive containment or similarity
        // In a real system, use cosine similarity or embeddings
        return s1.toLowerCase().contains(s2.toLowerCase()) || s2.toLowerCase().contains(s1.toLowerCase());
    }

    private double calculatePrecision(int tp, int fp) {
        if (tp + fp == 0) return 0.0;
        return (double) tp / (tp + fp);
    }

    private double calculateRecall(int tp, int fn) {
        if (tp + fn == 0) return 0.0;
        return (double) tp / (tp + fn);
    }

    private double calculateF1(double precision, double recall) {
        if (precision + recall == 0) return 0.0;
        return 2 * (precision * recall) / (precision + recall);
    }
}
