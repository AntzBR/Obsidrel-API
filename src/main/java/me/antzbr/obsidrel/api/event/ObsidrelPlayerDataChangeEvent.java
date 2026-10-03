package me.antzbr.obsidrel.api.event;

import java.util.UUID;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.antzbr.obsidrel.api.view.PlayerValueView;

public final class ObsidrelPlayerDataChangeEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final UUID playerId;
    private final String key;
    private final PlayerValueView previous;
    private final PlayerValueView current;

    public ObsidrelPlayerDataChangeEvent(UUID playerId, String key, PlayerValueView previous, PlayerValueView current) {
        this.playerId = playerId;
        this.key = key == null ? "" : key;
        this.previous = previous;
        this.current = current;
    }

    public UUID playerId() { return playerId; }
    public String key() { return key; }
    public PlayerValueView previous() { return previous; }
    public PlayerValueView current() { return current; }
    @Override public HandlerList getHandlers() { return HANDLERS; }
    public static HandlerList getHandlerList() { return HANDLERS; }
}
