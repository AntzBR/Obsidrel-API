package me.antzbr.obsidrel.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

import me.antzbr.obsidrel.api.view.ItemView;

public final class ObsidrelItemDamageEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final ItemView definition;
    private final ItemStack item;
    private int damage;
    private int remaining;
    private boolean cancelled;

    public ObsidrelItemDamageEvent(Player player, ItemView definition, ItemStack item, int damage, int remaining) {
        this.player = player;
        this.definition = definition;
        this.item = item == null ? null : item.clone();
        this.damage = Math.max(0, damage);
        this.remaining = Math.max(0, remaining);
    }

    public Player getPlayer() { return player; }
    public ItemView getDefinition() { return definition; }
    public ItemStack getItem() { return item == null ? null : item.clone(); }
    public int getDamage() { return damage; }
    public void setDamage(int damage) { this.damage = Math.max(0, damage); }
    public int getRemaining() { return remaining; }
    public void setRemaining(int remaining) { this.remaining = Math.max(0, remaining); }
    @Override public boolean isCancelled() { return cancelled; }
    @Override public void setCancelled(boolean cancelled) { this.cancelled = cancelled; }
    @Override public HandlerList getHandlers() { return HANDLERS; }
    public static HandlerList getHandlerList() { return HANDLERS; }
}
