package me.antzbr.obsidrel.api.view;

public record UiOpenResult(boolean success, String details) {
    public UiOpenResult { details = details == null ? "" : details; }
}
