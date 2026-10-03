package me.antzbr.obsidrel.api.view;

public record ConditionView(String id, boolean enabled, String message) {
    public ConditionView {
        id = id == null ? "" : id;
        message = message == null ? "" : message;
    }
}
