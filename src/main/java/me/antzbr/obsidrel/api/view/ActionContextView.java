package me.antzbr.obsidrel.api.view;

import java.util.Map;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

public record ActionContextView(Player player, Location location, Entity targetEntity, Block targetBlock,
                                ItemStack item, Event sourceEvent, String sourceId, String triggerId,
                                Map<String, String> variables, int depth) {
    public ActionContextView {
        location = location == null ? null : location.clone();
        item = item == null ? null : item.clone();
        sourceId = sourceId == null ? "" : sourceId;
        triggerId = triggerId == null ? "" : triggerId;
        variables = variables == null ? Map.of() : Map.copyOf(variables);
        depth = Math.max(0, depth);
    }

    @Override public Location location() { return location == null ? null : location.clone(); }
    @Override public ItemStack item() { return item == null ? null : item.clone(); }
}
