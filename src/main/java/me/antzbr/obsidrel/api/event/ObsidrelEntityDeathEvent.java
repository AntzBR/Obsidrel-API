package me.antzbr.obsidrel.api.event;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent;

import me.antzbr.obsidrel.api.view.EntityLifecycleView;
import me.antzbr.obsidrel.api.view.EntityView;

/**
 * POST event fired after Obsidrel has committed a custom entity's DEAD
 * lifecycle state. This notification is intentionally not cancellable.
 */
public final class ObsidrelEntityDeathEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();

    private final LivingEntity entity;
    private final EntityView definition;
    private final EntityLifecycleView lifecycle;
    private final Player killer;
    private final EntityDamageEvent.DamageCause damageCause;

    public ObsidrelEntityDeathEvent(LivingEntity entity, EntityView definition,
                                    EntityLifecycleView lifecycle, Player killer,
                                    EntityDamageEvent.DamageCause damageCause) {
        this.entity = entity;
        this.definition = definition;
        this.lifecycle = lifecycle;
        this.killer = killer;
        this.damageCause = damageCause;
    }

    public LivingEntity getEntity() { return entity; }
    public EntityView getDefinition() { return definition; }
    public EntityLifecycleView getLifecycle() { return lifecycle; }
    public Player getKiller() { return killer; }
    public EntityDamageEvent.DamageCause getDamageCause() { return damageCause; }

    @Override public HandlerList getHandlers() { return HANDLERS; }
    public static HandlerList getHandlerList() { return HANDLERS; }
}
