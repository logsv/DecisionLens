package com.decisionlens.aireview.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PromptTemplate {
    private String version;
    private String template; // Contains placeholders like {{title}}, {{content}}
    private String description;
}
