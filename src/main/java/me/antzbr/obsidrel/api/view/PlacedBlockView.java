package me.antzbr.obsidrel.api.view;

import java.util.UUID;

public record PlacedBlockView(String id, UUID worldId, String worldName, int x, int y, int z,
                              String orientation, String state) {
    public PlacedBlockView {
        id = id == null ? "" : id;
        worldName = worldName == null ? "" : worldName;
        orientation = orientation == null ? "DEFAULT" : orientation;
        state = state == null ? "" : state;
    }
}
