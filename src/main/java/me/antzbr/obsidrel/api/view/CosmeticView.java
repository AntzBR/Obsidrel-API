package me.antzbr.obsidrel.api.view;

public record CosmeticView(String id, boolean enabled, String itemId, String slot) {
    public CosmeticView {
        id = id == null ? "" : id;
        itemId = itemId == null ? "" : itemId;
        slot = slot == null ? "" : slot;
    }
}
