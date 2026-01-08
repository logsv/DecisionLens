package com.decisionlens.decision.repository;

import com.decisionlens.decision.domain.Decision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DecisionRepository extends JpaRepository<Decision, UUID> {
}
