package com.decisionlens.policy.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleAssignment {
    private UUID userId;
    private Role role;
    private String resourceId; // Optional: Scope to specific resource/project
}
