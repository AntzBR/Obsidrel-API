package me.antzbr.obsidrel.api.view;

import java.util.UUID;

public record EntityLifecycleView(UUID instanceId, String entityId, String state, UUID ownerId, UUID bodyId,
                                  UUID worldId, String worldName, double x, double y, double z,
                                  float yaw, float pitch, double health, String movementMode,
                                  long createdAt, long updatedAt, String transition) {
    public EntityLifecycleView {
        entityId = entityId == null ? "" : entityId;
        state = state == null ? "" : state;
        worldName = worldName == null ? "" : worldName;
        movementMode = movementMode == null ? "" : movementMode;
        transition = transition == null ? "" : transition;
    }
}
