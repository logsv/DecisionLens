package com.decisionlens.audit.controller;

import com.decisionlens.audit.domain.AuditLog;
import com.decisionlens.audit.service.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/audit-logs")
@RequiredArgsConstructor
public class AuditController {

    private final AuditService auditService;

    @GetMapping
    public ResponseEntity<List<AuditLog>> getAllLogs() {
        return ResponseEntity.ok(auditService.getAllLogs());
    }

    @GetMapping("/decision/{decisionId}")
    public ResponseEntity<List<AuditLog>> getLogsByDecisionId(@PathVariable String decisionId) {
        return ResponseEntity.ok(auditService.getLogsForDecision(decisionId));
    }
}
