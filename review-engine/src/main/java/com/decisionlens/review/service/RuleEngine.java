package com.decisionlens.review.service;

import com.decisionlens.review.model.Action;
import com.decisionlens.review.model.Condition;
import com.decisionlens.review.model.Rule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class RuleEngine {

    private final RuleLoader ruleLoader;

    public List<Action> evaluate(Map<String, String> decisionData) {
        List<Action> triggeredActions = new ArrayList<>();
        List<Rule> rules = ruleLoader.getRules();

        for (Rule rule : rules) {
            if (matches(rule, decisionData)) {
                log.info("Rule triggered: {}", rule.getName());
                triggeredActions.add(rule.getAction());
            }
        }
        return triggeredActions;
    }

    private boolean matches(Rule rule, Map<String, String> decisionData) {
        for (Condition condition : rule.getConditions()) {
            String fieldValue = decisionData.get(condition.getField());
            if (fieldValue == null) {
                // If field is missing, strictly speaking it doesn't contain the value, 
                // but for NOT_CONTAINS, if value is null, it indeed does not contain it.
                // However, logic depends on interpretation. 
                // Here: if field is missing, we treat it as empty string.
                fieldValue = "";
            }

            if (!evaluateCondition(condition, fieldValue)) {
                return false; // All conditions must match (AND logic)
            }
        }
        return true;
    }

    private boolean evaluateCondition(Condition condition, String fieldValue) {
        String conditionValue = condition.getValue().toLowerCase();
        String actualValue = fieldValue.toLowerCase();

        switch (condition.getOperator()) {
            case CONTAINS:
                return actualValue.contains(conditionValue);
            case NOT_CONTAINS:
                return !actualValue.contains(conditionValue);
            case EQUALS:
                return actualValue.equals(conditionValue);
            case MATCHES:
                return Pattern.compile(condition.getValue(), Pattern.CASE_INSENSITIVE).matcher(fieldValue).find();
            default:
                return false;
        }
    }
}
