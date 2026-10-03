package me.antzbr.obsidrel.api.view;

public record LootTableView(String id, boolean enabled, String mode, int rolls, int entries) {
    public LootTableView {
        id = id == null ? "" : id;
        mode = mode == null ? "" : mode;
        rolls = Math.max(1, rolls);
        entries = Math.max(0, entries);
    }
}
