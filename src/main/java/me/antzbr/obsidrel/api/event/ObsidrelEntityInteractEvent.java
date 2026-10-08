package me.antzbr.obsidrel.api.event;

import java.util.UUID;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.antzbr.obsidrel.api.view.EntityView;

/**
 * PRE event fired before Obsidrel handles a player's primary-hand interaction
 * with a custom entity. Cancelling this event prevents the Obsidrel-side
 * interaction, such as mounting a rideable entity.
 */
public final class ObsidrelEntityInteractEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();

    private final Player player;
    private final EntityView definition;
    private final LivingEntity entity;
    private final Entity clickedEntity;
    private final UUID instanceId;
    private boolean cancelled;

    public ObsidrelEntityInteractEvent(Player player, EntityView definition, LivingEntity entity,
                                       Entity clickedEntity, UUID instanceId) {
        this.player = player;
        this.definition = definition;
        this.entity = entity;
        this.clickedEntity = clickedEntity;
        this.instanceId = instanceId;
    }

    public Player getPlayer() { return player; }
    public EntityView getDefinition() { return definition; }
    public LivingEntity getEntity() { return entity; }
    public Entity getClickedEntity() { return clickedEntity; }
    public UUID getInstanceId() { return instanceId; }

    @Override public boolean isCancelled() { return cancelled; }
    @Override public void setCancelled(boolean cancelled) { this.cancelled = cancelled; }
    @Override public HandlerList getHandlers() { return HANDLERS; }
    public static HandlerList getHandlerList() { return HANDLERS; }
}
