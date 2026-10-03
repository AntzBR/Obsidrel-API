package me.antzbr.obsidrel.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.antzbr.obsidrel.api.view.CosmeticView;

public final class ObsidrelCosmeticUnequipEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final CosmeticView cosmetic;

    public ObsidrelCosmeticUnequipEvent(Player player, CosmeticView cosmetic) {
        this.player = player;
        this.cosmetic = cosmetic;
    }

    public Player getPlayer() { return player; }
    public CosmeticView getCosmetic() { return cosmetic; }
    @Override public HandlerList getHandlers() { return HANDLERS; }
    public static HandlerList getHandlerList() { return HANDLERS; }
}
