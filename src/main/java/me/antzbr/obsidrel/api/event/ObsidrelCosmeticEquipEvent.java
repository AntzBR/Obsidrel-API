package me.antzbr.obsidrel.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.antzbr.obsidrel.api.view.CosmeticView;

public final class ObsidrelCosmeticEquipEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final CosmeticView cosmetic;
    private boolean cancelled;

    public ObsidrelCosmeticEquipEvent(Player player, CosmeticView cosmetic) {
        this.player = player;
        this.cosmetic = cosmetic;
    }

    public Player getPlayer() { return player; }
    public CosmeticView getCosmetic() { return cosmetic; }
    @Override public boolean isCancelled() { return cancelled; }
    @Override public void setCancelled(boolean cancelled) { this.cancelled = cancelled; }
    @Override public HandlerList getHandlers() { return HANDLERS; }
    public static HandlerList getHandlerList() { return HANDLERS; }
}
