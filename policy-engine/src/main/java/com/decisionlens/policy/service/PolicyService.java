package com.decisionlens.policy.service;

import com.decisionlens.policy.domain.ActionType;
import com.decisionlens.policy.domain.PolicyDecisionState;
import com.decisionlens.policy.domain.Role;
import com.decisionlens.policy.domain.RoleAssignment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@Slf4j
public class PolicyService {

    /**
     * Checks if a user can perform an action on a decision in a given state.
     */
    public boolean canPerformAction(UUID userId, ActionType action, PolicyDecisionState currentState, List<RoleAssignment> userRoles) {
        for (RoleAssignment roleAssignment : userRoles) {
            if (roleAssignment.getUserId().equals(userId)) {
                if (hasPermission(roleAssignment.getRole(), action, currentState)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Determines the next state based on the action and current state.
     */
    public PolicyDecisionState getNextState(PolicyDecisionState currentState, ActionType action) {
        switch (action) {
            case SUBMIT_FOR_REVIEW:
                if (currentState == PolicyDecisionState.DRAFT) {
                    return PolicyDecisionState.REVIEW;
                }
                break;
            case APPROVE:
                if (currentState == PolicyDecisionState.REVIEW) {
                    return PolicyDecisionState.APPROVED;
                }
                break;
            case REJECT:
                if (currentState == PolicyDecisionState.REVIEW) {
                    return PolicyDecisionState.REJECTED;
                }
                break;
            case EDIT:
                // Editing resets to DRAFT if it was rejected or in review
                if (currentState == PolicyDecisionState.REJECTED || currentState == PolicyDecisionState.REVIEW) {
                    return PolicyDecisionState.DRAFT;
                }
                break;
            default:
                break;
        }
        return currentState; // No state change
    }

    private boolean hasPermission(Role role, ActionType action, PolicyDecisionState state) {
        switch (role) {
            case AUTHOR:
                // Authors can create, edit (in draft), and submit
                if (action == ActionType.CREATE) return true;
                if (action == ActionType.EDIT && (state == PolicyDecisionState.DRAFT || state == PolicyDecisionState.REJECTED)) return true;
                if (action == ActionType.SUBMIT_FOR_REVIEW && state == PolicyDecisionState.DRAFT) return true;
                break;
            case REVIEWER:
                // Reviewers can approve or reject in REVIEW state
                if ((action == ActionType.APPROVE || action == ActionType.REJECT) && state == PolicyDecisionState.REVIEW) return true;
                break;
            case APPROVER:
                // Approvers act as super-reviewers (simplified here)
                if ((action == ActionType.APPROVE || action == ActionType.REJECT) && state == PolicyDecisionState.REVIEW) return true;
                break;
            case ADMIN:
                return true; // Admins can do anything
            default:
                return false;
        }
        return false;
    }
}
