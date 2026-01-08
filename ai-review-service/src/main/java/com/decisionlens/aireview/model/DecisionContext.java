package com.decisionlens.aireview.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DecisionContext {
    private String id;
    private String title;
    private String content;
    private String author;
    private String context; // Additional context if any
}
