package me.antzbr.obsidrel.api.event;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.antzbr.obsidrel.api.view.BlockView;

public final class ObsidrelBlockBreakEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final Block block;
    private final BlockView definition;
    private final String orientation;
    private final Cause cause;
    private boolean cancelled;
    private boolean defaultDrops = true;

    public ObsidrelBlockBreakEvent(Player player, Block block, BlockView definition, String orientation, Cause cause) {
        this.player = player;
        this.block = block;
        this.definition = definition;
        this.orientation = orientation == null ? "DEFAULT" : orientation;
        this.cause = cause;
    }

    public Player getPlayer() { return player; }
    public Block getBlock() { return block; }
    public BlockView getDefinition() { return definition; }
    public String getOrientation() { return orientation; }
    public Cause getCause() { return cause; }
    public boolean isDefaultDrops() { return defaultDrops; }
    public void setDefaultDrops(boolean defaultDrops) { this.defaultDrops = defaultDrops; }
    @Override public boolean isCancelled() { return cancelled; }
    @Override public void setCancelled(boolean cancelled) { this.cancelled = cancelled; }
    @Override public HandlerList getHandlers() { return HANDLERS; }
    public static HandlerList getHandlerList() { return HANDLERS; }

    public enum Cause { PLAYER, CREATIVE, EXPLOSION }
}
