package me.antzbr.obsidrel.api.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import me.antzbr.obsidrel.api.view.EntityLifecycleView;
import me.antzbr.obsidrel.api.view.EntityRespawnResult;
import me.antzbr.obsidrel.api.view.EntityView;

public interface EntityAPI {
    List<EntityView> all();
    Optional<EntityView> find(String id);
    Optional<LivingEntity> spawn(String id, Location location, Player source);
    Optional<LivingEntity> spawn(String id, Location location, Player source, UUID ownerId);
    boolean despawn(LivingEntity entity);
    EntityRespawnResult respawn(UUID instanceId, Location override, Player source);
    Optional<EntityLifecycleView> lifecycle(UUID instanceId);
    Optional<UUID> instanceId(Entity entity);
    Optional<UUID> ownerId(Entity entity);
    Optional<Location> rigPart(Entity entity, String partId);
    Optional<Location> attachment(Entity entity, String attachmentId);
}
