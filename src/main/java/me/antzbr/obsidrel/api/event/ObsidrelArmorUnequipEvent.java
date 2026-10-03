package me.antzbr.obsidrel.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

import me.antzbr.obsidrel.api.view.ArmorView;

public final class ObsidrelArmorUnequipEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final ArmorView armor;
    private final ItemStack item;

    public ObsidrelArmorUnequipEvent(Player player, ArmorView armor, ItemStack item) {
        this.player = player;
        this.armor = armor;
        this.item = item == null ? null : item.clone();
    }

    public Player getPlayer() { return player; }
    public ArmorView getArmor() { return armor; }
    public ItemStack getItem() { return item == null ? null : item.clone(); }
    @Override public HandlerList getHandlers() { return HANDLERS; }
    public static HandlerList getHandlerList() { return HANDLERS; }
}
