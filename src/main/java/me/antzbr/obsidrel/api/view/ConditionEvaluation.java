package me.antzbr.obsidrel.api.view;

import java.util.Map;

public record ConditionEvaluation(boolean passed, String failedCondition, String details,
                                  String message, Map<String, String> variables) {
    public ConditionEvaluation {
        failedCondition = failedCondition == null ? "" : failedCondition;
        details = details == null ? "" : details;
        message = message == null ? "" : message;
        variables = variables == null ? Map.of() : Map.copyOf(variables);
    }
}
