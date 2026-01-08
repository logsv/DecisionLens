package com.decisionlens.aireview.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AiCritique {
    private String summary;
    private List<String> tradeOffs;
    private List<String> blindSpots;
    private List<String> questions;
    private double confidenceScore; // 0.0 to 1.0
}
