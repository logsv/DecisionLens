package com.decisionlens.decision.controller;

import com.decisionlens.decision.domain.Decision;
import com.decisionlens.decision.domain.DecisionStatus;
import com.decisionlens.decision.dto.CreateDecisionRequest;
import com.decisionlens.decision.dto.DecisionResponse;
import com.decisionlens.decision.dto.UpdateDecisionRequest;
import com.decisionlens.decision.repository.DecisionRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("/decisions")
@RequiredArgsConstructor
@Tag(name = "Decisions", description = "API for managing decisions")
public class DecisionController {

    private final DecisionRepository decisionRepository;

    @PostMapping
    @Operation(summary = "Create a new decision")
    public ResponseEntity<DecisionResponse> createDecision(@Valid @RequestBody CreateDecisionRequest request) {
        Decision decision = Decision.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .author(request.getAuthor())
                .version(request.getVersion())
                .status(DecisionStatus.DRAFT)
                .build();

        Decision saved = decisionRepository.save(decision);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToResponse(saved));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get decision by ID")
    public ResponseEntity<DecisionResponse> getDecision(@PathVariable UUID id) {
        return decisionRepository.findById(id)
                .map(this::mapToResponse)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Decision not found"));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update decision")
    public ResponseEntity<DecisionResponse> updateDecision(
            @PathVariable UUID id,
            @RequestBody UpdateDecisionRequest request) {
        
        Decision decision = decisionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Decision not found"));

        if (request.getTitle() != null) {
            decision.setTitle(request.getTitle());
        }
        if (request.getContent() != null) {
            decision.setContent(request.getContent());
        }
        if (request.getStatus() != null) {
            decision.setStatus(request.getStatus());
        }

        Decision updated = decisionRepository.save(decision);
        return ResponseEntity.ok(mapToResponse(updated));
    }

    private DecisionResponse mapToResponse(Decision decision) {
        return DecisionResponse.builder()
                .id(decision.getId())
                .title(decision.getTitle())
                .content(decision.getContent())
                .author(decision.getAuthor())
                .status(decision.getStatus())
                .version(decision.getVersion())
                .createdAt(decision.getCreatedAt())
                .updatedAt(decision.getUpdatedAt())
                .build();
    }
}
