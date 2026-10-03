package me.antzbr.obsidrel.api.view;

import java.util.Set;

public record ArmorSetView(String id, boolean enabled, int pieceCount, Set<Integer> bonusThresholds) {
    public ArmorSetView {
        id = id == null ? "" : id;
        bonusThresholds = bonusThresholds == null ? Set.of() : Set.copyOf(bonusThresholds);
    }
}
