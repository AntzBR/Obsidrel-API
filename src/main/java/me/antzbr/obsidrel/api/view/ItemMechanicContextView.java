package me.antzbr.obsidrel.api.view;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

public record ItemMechanicContextView(Player player, ItemStack item, ItemView definition,
                                      String trigger, Event sourceEvent, Entity targetEntity,
                                      Block targetBlock) {
    public ItemMechanicContextView {
        item = item == null ? null : item.clone();
        trigger = trigger == null ? "" : trigger;
    }
    @Override public ItemStack item() { return item == null ? null : item.clone(); }
}
