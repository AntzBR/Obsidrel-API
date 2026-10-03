package me.antzbr.obsidrel.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.antzbr.obsidrel.api.view.ActionContextView;
import me.antzbr.obsidrel.api.view.LootTableView;

public final class ObsidrelLootRollEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final LootTableView table;
    private final ActionContextView context;
    private boolean cancelled;

    public ObsidrelLootRollEvent(Player player, LootTableView table, ActionContextView context) {
        this.player = player;
        this.table = table;
        this.context = context;
    }

    public Player getPlayer() { return player; }
    public LootTableView getTable() { return table; }
    public ActionContextView getContext() { return context; }
    @Override public boolean isCancelled() { return cancelled; }
    @Override public void setCancelled(boolean cancel) { this.cancelled = cancel; }
    @Override public HandlerList getHandlers() { return HANDLERS; }
    public static HandlerList getHandlerList() { return HANDLERS; }
}
