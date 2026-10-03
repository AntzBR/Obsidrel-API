package me.antzbr.obsidrel.api.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

import me.antzbr.obsidrel.api.view.FurnitureInstanceView;
import me.antzbr.obsidrel.api.view.FurnitureView;

public interface FurnitureAPI {
    List<FurnitureView> all();
    Optional<FurnitureView> find(String id);
    Optional<FurnitureView> identify(ItemStack item);
    Optional<FurnitureInstanceView> identify(Entity entity);
    Optional<FurnitureInstanceView> placed(UUID instanceId);
    ItemStack createItem(String id, int amount);
    FurnitureInstanceView place(String id, Location location, float yaw, UUID ownerId);
    boolean remove(UUID instanceId);
}
