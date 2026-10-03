package me.antzbr.obsidrel.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.antzbr.obsidrel.api.view.FurnitureInstanceView;
import me.antzbr.obsidrel.api.view.FurnitureView;

public final class ObsidrelFurnitureSeatEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final FurnitureView definition;
    private final FurnitureInstanceView furniture;
    private boolean cancelled;

    public ObsidrelFurnitureSeatEvent(Player player, FurnitureView definition, FurnitureInstanceView furniture) {
        this.player = player;
        this.definition = definition;
        this.furniture = furniture;
    }

    public Player getPlayer() { return player; }
    public FurnitureView getDefinition() { return definition; }
    public FurnitureInstanceView getFurniture() { return furniture; }
    @Override public boolean isCancelled() { return cancelled; }
    @Override public void setCancelled(boolean cancelled) { this.cancelled = cancelled; }
    @Override public HandlerList getHandlers() { return HANDLERS; }
    public static HandlerList getHandlerList() { return HANDLERS; }
}
