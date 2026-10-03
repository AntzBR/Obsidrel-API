package me.antzbr.obsidrel.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.antzbr.obsidrel.api.view.ActionContextView;
import me.antzbr.obsidrel.api.view.TriggerView;

/** Fired after a trigger matched its source/target and before its action references run. */
public final class ObsidrelTriggerFireEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final TriggerView trigger;
    private final ActionContextView context;
    private boolean cancelled;

    public ObsidrelTriggerFireEvent(Player player, TriggerView trigger, ActionContextView context) {
        this.player = player;
        this.trigger = trigger;
        this.context = context;
    }

    public Player getPlayer() { return player; }
    public TriggerView getTrigger() { return trigger; }
    public ActionContextView getContext() { return context; }
    @Override public boolean isCancelled() { return cancelled; }
    @Override public void setCancelled(boolean cancelled) { this.cancelled = cancelled; }
    @Override public HandlerList getHandlers() { return HANDLERS; }
    public static HandlerList getHandlerList() { return HANDLERS; }
}
