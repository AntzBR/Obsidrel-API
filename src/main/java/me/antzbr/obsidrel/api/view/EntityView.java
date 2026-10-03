package me.antzbr.obsidrel.api.view;

import org.bukkit.entity.EntityType;

public record EntityView(String id, String name, EntityType bodyType, boolean ai, boolean persistent,
                         boolean modeled, double maxHealth, double movementSpeed) {
    public EntityView {
        id = id == null ? "" : id;
        name = name == null ? "" : name;
    }
}
