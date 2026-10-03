package me.antzbr.obsidrel.api.view;

import java.util.Map;

public record ItemMechanicView(String id, String type, String trigger, int cooldownTicks,
                               int durabilityCost, boolean cancelEvent, String permission,
                               Map<String, Object> options) {
    public ItemMechanicView {
        id = id == null ? "" : id;
        type = type == null ? "" : type;
        trigger = trigger == null ? "" : trigger;
        permission = permission == null ? "" : permission;
        options = options == null ? Map.of() : Map.copyOf(options);
    }
}
