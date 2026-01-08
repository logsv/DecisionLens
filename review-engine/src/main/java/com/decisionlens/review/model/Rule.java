package com.decisionlens.review.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rule {
    private String id;
    private String name;
    private String description;
    private List<Condition> conditions;
    private Action action;
}
