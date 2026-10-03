package me.antzbr.obsidrel.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.antzbr.obsidrel.api.view.FurnitureInstanceView;
import me.antzbr.obsidrel.api.view.FurnitureView;

public final class ObsidrelFurnitureBreakEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final FurnitureView definition;
    private final FurnitureInstanceView furniture;
    private final Cause cause;
    private boolean cancelled;
    private boolean defaultDrops = true;

    public ObsidrelFurnitureBreakEvent(Player player, FurnitureView definition, FurnitureInstanceView furniture, Cause cause) {
        this.player = player;
        this.definition = definition;
        this.furniture = furniture;
        this.cause = cause;
    }

    public Player getPlayer() { return player; }
    public FurnitureView getDefinition() { return definition; }
    public FurnitureInstanceView getFurniture() { return furniture; }
    public Cause getCause() { return cause; }
    public boolean isDefaultDrops() { return defaultDrops; }
    public void setDefaultDrops(boolean defaultDrops) { this.defaultDrops = defaultDrops; }
    @Override public boolean isCancelled() { return cancelled; }
    @Override public void setCancelled(boolean cancelled) { this.cancelled = cancelled; }
    @Override public HandlerList getHandlers() { return HANDLERS; }
    public static HandlerList getHandlerList() { return HANDLERS; }
    public enum Cause { PLAYER, CREATIVE, EXPLOSION, ADMIN }
}
