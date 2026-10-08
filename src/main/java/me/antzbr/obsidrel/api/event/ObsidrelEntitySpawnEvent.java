package me.antzbr.obsidrel.api.event;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.antzbr.obsidrel.api.view.EntityLifecycleView;
import me.antzbr.obsidrel.api.view.EntityView;

/**
 * POST event fired after a custom entity has been created, configured and
 * recorded by the Obsidrel lifecycle store.
 */
public final class ObsidrelEntitySpawnEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();

    private final LivingEntity entity;
    private final EntityView definition;
    private final EntityLifecycleView lifecycle;
    private final Player source;
    private final Cause cause;

    public ObsidrelEntitySpawnEvent(LivingEntity entity, EntityView definition,
                                    EntityLifecycleView lifecycle, Player source, Cause cause) {
        this.entity = entity;
        this.definition = definition;
        this.lifecycle = lifecycle;
        this.source = source;
        this.cause = cause == null ? Cause.SPAWN : cause;
    }

    public LivingEntity getEntity() { return entity; }
    public EntityView getDefinition() { return definition; }
    public EntityLifecycleView getLifecycle() { return lifecycle; }
    public Player getSource() { return source; }
    public Cause getCause() { return cause; }

    @Override public HandlerList getHandlers() { return HANDLERS; }
    public static HandlerList getHandlerList() { return HANDLERS; }

    public enum Cause { SPAWN, RESPAWN }
}
