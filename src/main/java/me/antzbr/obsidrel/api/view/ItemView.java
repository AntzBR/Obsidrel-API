package me.antzbr.obsidrel.api.view;

import org.bukkit.Material;

public record ItemView(String id, String name, Material material, Integer customModelData,
                       boolean modeled, int maxStackSize) {
    public ItemView {
        id = id == null ? "" : id;
        name = name == null ? "" : name;
    }
}
