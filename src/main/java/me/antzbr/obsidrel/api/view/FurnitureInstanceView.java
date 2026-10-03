package me.antzbr.obsidrel.api.view;

import java.util.UUID;

public record FurnitureInstanceView(UUID instanceId, String id, UUID worldId, String worldName,
                                    double x, double y, double z, float yaw, UUID ownerId, String state) {
    public FurnitureInstanceView {
        id = id == null ? "" : id;
        worldName = worldName == null ? "" : worldName;
        state = state == null ? "default" : state;
    }
}
