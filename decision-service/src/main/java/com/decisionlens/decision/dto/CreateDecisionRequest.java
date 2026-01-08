package com.decisionlens.decision.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateDecisionRequest {

    @NotBlank(message = "Title is required")
    private String title;

    private String content;

    @NotBlank(message = "Author is required")
    private String author;

    @NotBlank(message = "Version is required")
    private String version;
}
