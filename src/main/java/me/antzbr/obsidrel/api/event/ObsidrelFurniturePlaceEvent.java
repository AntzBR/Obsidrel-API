package me.antzbr.obsidrel.api.event;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.antzbr.obsidrel.api.view.FurnitureView;

public final class ObsidrelFurniturePlaceEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final FurnitureView definition;
    private final Location location;
    private float yaw;
    private boolean cancelled;

    public ObsidrelFurniturePlaceEvent(Player player, FurnitureView definition, Location location, float yaw) {
        this.player = player;
        this.definition = definition;
        this.location = location == null ? null : location.clone();
        this.yaw = normalizeYaw(yaw);
    }

    public Player getPlayer() { return player; }
    public FurnitureView getDefinition() { return definition; }
    public Location getLocation() { return location == null ? null : location.clone(); }
    public float getYaw() { return yaw; }
    public void setYaw(float yaw) { this.yaw = normalizeYaw(yaw); }
    @Override public boolean isCancelled() { return cancelled; }
    @Override public void setCancelled(boolean cancelled) { this.cancelled = cancelled; }
    @Override public HandlerList getHandlers() { return HANDLERS; }
    public static HandlerList getHandlerList() { return HANDLERS; }

    private static float normalizeYaw(float yaw) {
        float value = yaw % 360F;
        return value < 0F ? value + 360F : value;
    }
}
