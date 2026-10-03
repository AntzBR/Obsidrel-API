package me.antzbr.obsidrel.api.view;

public record TriggerView(String id, boolean enabled, String event, String target, boolean cancelEvent) {
    public TriggerView {
        id = id == null ? "" : id;
        event = event == null ? "" : event;
        target = target == null ? "" : target;
    }
}
