package me.antzbr.obsidrel.api.view;

public record ActionExecutionResult(boolean accepted, int steps, String details) {
    public ActionExecutionResult { details = details == null ? "" : details; }
}
