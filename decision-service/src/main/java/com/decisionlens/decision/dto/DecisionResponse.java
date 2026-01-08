package com.decisionlens.decision.dto;

import com.decisionlens.decision.domain.DecisionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DecisionResponse {

    private UUID id;
    private String title;
    private String content;
    private String author;
    private DecisionStatus status;
    private String version;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
