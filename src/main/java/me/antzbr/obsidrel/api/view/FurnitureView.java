package me.antzbr.obsidrel.api.view;

import org.bukkit.Material;

public record FurnitureView(String id, String name, Material itemMaterial, Integer customModelData,
                            boolean modeled, int hitboxes, int seats, int states) {
    public FurnitureView {
        id = id == null ? "" : id;
        name = name == null ? "" : name;
    }
}
