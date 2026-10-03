package me.antzbr.obsidrel.api.view;

public record HudView(String id, boolean enabled, String type, boolean autoShow, int priority) {
    public HudView { id = id == null ? "" : id; type = type == null ? "" : type; }
}
