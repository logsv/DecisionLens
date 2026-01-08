package com.decisionlens.audit.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "audit_logs")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String decisionId;

    @Column(nullable = false)
    private String actor; // "Who reviewed the decision"

    @Column(nullable = false)
    private String action; // e.g., "REVIEWED", "APPROVED"

    @Column(columnDefinition = "TEXT")
    private String ruleEngineOutput; // JSON string of rule results

    @Column(nullable = true)
    private String llmModel; // e.g., "gpt-4"

    @Column(nullable = true)
    private String promptVersion; // e.g., "v1.0"

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime timestamp;
}
