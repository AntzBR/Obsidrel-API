package me.antzbr.obsidrel.api.view;

import java.util.List;

public record LootRollResult(boolean accepted, int selected, int delivered, int failed,
                             List<String> rewards, String details) {
    public LootRollResult {
        rewards = rewards == null ? List.of() : List.copyOf(rewards);
        details = details == null ? "" : details;
    }
}
