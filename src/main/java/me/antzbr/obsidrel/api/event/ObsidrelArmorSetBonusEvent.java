package me.antzbr.obsidrel.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.antzbr.obsidrel.api.view.ArmorSetView;

public final class ObsidrelArmorSetBonusEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final ArmorSetView set;
    private final int threshold;
    private final int equippedPieces;
    private final boolean active;

    public ObsidrelArmorSetBonusEvent(Player player, ArmorSetView set, int threshold, int equippedPieces, boolean active) {
        this.player = player;
        this.set = set;
        this.threshold = threshold;
        this.equippedPieces = equippedPieces;
        this.active = active;
    }

    public Player getPlayer() { return player; }
    public ArmorSetView getSet() { return set; }
    public int getThreshold() { return threshold; }
    public int getEquippedPieces() { return equippedPieces; }
    public boolean isActive() { return active; }
    @Override public HandlerList getHandlers() { return HANDLERS; }
    public static HandlerList getHandlerList() { return HANDLERS; }
}
