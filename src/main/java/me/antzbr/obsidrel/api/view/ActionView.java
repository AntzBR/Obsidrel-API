package me.antzbr.obsidrel.api.view;

public record ActionView(String id, boolean enabled, int stepCount) {
    public ActionView { id = id == null ? "" : id; }
}
