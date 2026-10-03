package me.antzbr.obsidrel.api.view;

import org.bukkit.entity.LivingEntity;

public record EntityRespawnResult(boolean success, String details, LivingEntity entity,
                                  EntityLifecycleView lifecycle) {
    public EntityRespawnResult { details = details == null ? "" : details; }
}
