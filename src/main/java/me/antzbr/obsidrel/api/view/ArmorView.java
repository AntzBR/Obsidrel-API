package me.antzbr.obsidrel.api.view;

public record ArmorView(String itemId, String slot, String setId) {
    public ArmorView {
        itemId = itemId == null ? "" : itemId;
        slot = slot == null ? "" : slot;
        setId = setId == null ? "" : setId;
    }
}
