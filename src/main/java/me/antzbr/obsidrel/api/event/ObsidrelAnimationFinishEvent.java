package me.antzbr.obsidrel.api.event;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * POST notification fired once when an ONCE animation reaches its authored end.
 * The event is shared by entity and furniture animation runtimes and leaves room
 * for additional Obsidrel animation targets in later API levels.
 */
public final class ObsidrelAnimationFinishEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();

    private final TargetType targetType;
    private final String definitionId;
    private final UUID instanceId;
    private final Entity entity;
    private final Location location;
    private final Player actor;
    private final String state;
    private final String animation;
    private final Channel channel;
    private final String trigger;

    public ObsidrelAnimationFinishEvent(TargetType targetType, String definitionId, UUID instanceId,
                                        Entity entity, Location location, Player actor,
                                        String state, String animation, Channel channel, String trigger) {
        this.targetType = targetType == null ? TargetType.ENTITY : targetType;
        this.definitionId = definitionId == null ? "" : definitionId;
        this.instanceId = instanceId;
        this.entity = entity;
        this.location = location == null ? null : location.clone();
        this.actor = actor;
        this.state = state == null ? "" : state;
        this.animation = animation == null ? "" : animation;
        this.channel = channel == null ? Channel.BASE : channel;
        this.trigger = trigger == null ? "" : trigger;
    }

    public TargetType getTargetType() { return targetType; }
    public String getDefinitionId() { return definitionId; }
    public UUID getInstanceId() { return instanceId; }
    public Entity getEntity() { return entity; }
    public Location getLocation() { return location == null ? null : location.clone(); }
    public Player getActor() { return actor; }
    public String getState() { return state; }
    public String getAnimation() { return animation; }
    public Channel getChannel() { return channel; }
    public String getTrigger() { return trigger; }

    @Override public HandlerList getHandlers() { return HANDLERS; }
    public static HandlerList getHandlerList() { return HANDLERS; }

    public enum TargetType { ENTITY, FURNITURE }
    public enum Channel { BASE, ACTION }
}
