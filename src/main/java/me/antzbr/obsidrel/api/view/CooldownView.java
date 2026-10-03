package me.antzbr.obsidrel.api.view;

public record CooldownView(String id, boolean enabled, long durationMillis, String scope, boolean persistent) {
    public CooldownView {
        id = id == null ? "" : id;
        scope = scope == null ? "" : scope;
        durationMillis = Math.max(0L, durationMillis);
    }
}
