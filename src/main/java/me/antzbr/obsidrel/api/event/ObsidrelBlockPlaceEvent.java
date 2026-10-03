package me.antzbr.obsidrel.api.event;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.antzbr.obsidrel.api.view.BlockView;

public final class ObsidrelBlockPlaceEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final Block block;
    private final BlockView definition;
    private String orientation;
    private boolean cancelled;

    public ObsidrelBlockPlaceEvent(Player player, Block block, BlockView definition, String orientation) {
        this.player = player;
        this.block = block;
        this.definition = definition;
        this.orientation = orientation == null ? "DEFAULT" : orientation;
    }

    public Player getPlayer() { return player; }
    public Block getBlock() { return block; }
    public BlockView getDefinition() { return definition; }
    public String getOrientation() { return orientation; }
    public void setOrientation(String orientation) { this.orientation = orientation == null || orientation.isBlank() ? "DEFAULT" : orientation.trim().toUpperCase(java.util.Locale.ROOT); }
    @Override public boolean isCancelled() { return cancelled; }
    @Override public void setCancelled(boolean cancelled) { this.cancelled = cancelled; }
    @Override public HandlerList getHandlers() { return HANDLERS; }
    public static HandlerList getHandlerList() { return HANDLERS; }
}
