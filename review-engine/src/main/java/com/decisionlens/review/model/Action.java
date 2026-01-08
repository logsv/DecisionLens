package com.decisionlens.review.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Action {
    private String type; // e.g., "FLAG", "REJECT"
    private String message;
    private String severity; // e.g., "HIGH", "MEDIUM", "LOW"
}
