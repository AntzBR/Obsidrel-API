package me.antzbr.obsidrel.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.antzbr.obsidrel.api.view.ActionContextView;
import me.antzbr.obsidrel.api.view.ActionView;

/** PRE / cancellable event fired before a reusable Obsidrel action sequence is accepted for execution. */
public final class ObsidrelActionExecuteEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final ActionView action;
    private final ActionContextView context;
    private boolean cancelled;

    public ObsidrelActionExecuteEvent(Player player, ActionView action, ActionContextView context) {
        this.player = player;
        this.action = action;
        this.context = context;
    }

    public Player getPlayer() { return player; }
    public ActionView getAction() { return action; }
    public ActionContextView getContext() { return context; }
    @Override public boolean isCancelled() { return cancelled; }
    @Override public void setCancelled(boolean cancelled) { this.cancelled = cancelled; }
    @Override public HandlerList getHandlers() { return HANDLERS; }
    public static HandlerList getHandlerList() { return HANDLERS; }
}
