package me.antzbr.obsidrel.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

import me.antzbr.obsidrel.api.view.ItemView;

public final class ObsidrelItemBreakEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final ItemView definition;
    private final ItemStack item;
    private boolean cancelled;

    public ObsidrelItemBreakEvent(Player player, ItemView definition, ItemStack item) {
        this.player = player;
        this.definition = definition;
        this.item = item == null ? null : item.clone();
    }

    public Player getPlayer() { return player; }
    public ItemView getDefinition() { return definition; }
    public ItemStack getItem() { return item == null ? null : item.clone(); }
    @Override public boolean isCancelled() { return cancelled; }
    @Override public void setCancelled(boolean cancelled) { this.cancelled = cancelled; }
    @Override public HandlerList getHandlers() { return HANDLERS; }
    public static HandlerList getHandlerList() { return HANDLERS; }
}
