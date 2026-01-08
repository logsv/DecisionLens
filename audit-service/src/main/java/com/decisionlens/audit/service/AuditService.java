package com.decisionlens.audit.service;

import com.decisionlens.audit.domain.AuditLog;
import com.decisionlens.audit.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuditService {

    private final AuditLogRepository auditLogRepository;

    @Transactional
    public AuditLog logEvent(String decisionId, String actor, String action, String ruleEngineOutput, String llmModel, String promptVersion) {
        AuditLog logEntry = AuditLog.builder()
                .decisionId(decisionId)
                .actor(actor)
                .action(action)
                .ruleEngineOutput(ruleEngineOutput)
                .llmModel(llmModel)
                .promptVersion(promptVersion)
                .build();
        
        log.info("Logging audit event for decision {}: {}", decisionId, action);
        return auditLogRepository.save(logEntry);
    }

    public List<AuditLog> getLogsForDecision(String decisionId) {
        return auditLogRepository.findByDecisionIdOrderByTimestampDesc(decisionId);
    }
    
    public List<AuditLog> getAllLogs() {
        return auditLogRepository.findAll();
    }
}
