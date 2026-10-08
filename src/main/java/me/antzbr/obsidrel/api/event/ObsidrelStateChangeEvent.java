package me.antzbr.obsidrel.api.event;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * POST notification emitted after an Obsidrel runtime state transition has been
 * committed. State snapshots are immutable string maps so integrations do not
 * need access to Core implementation classes.
 */
public final class ObsidrelStateChangeEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();

    private final TargetType targetType;
    private final StateType stateType;
    private final String definitionId;
    private final UUID instanceId;
    private final Entity entity;
    private final Location location;
    private final Player actor;
    private final Map<String, String> previousState;
    private final Map<String, String> currentState;
    private final String cause;

    public ObsidrelStateChangeEvent(TargetType targetType, StateType stateType, String definitionId,
                                    UUID instanceId, Entity entity, Location location, Player actor,
                                    Map<String, String> previousState, Map<String, String> currentState,
                                    String cause) {
        this.targetType = targetType == null ? TargetType.ENTITY : targetType;
        this.stateType = stateType == null ? StateType.CONTENT : stateType;
        this.definitionId = definitionId == null ? "" : definitionId;
        this.instanceId = instanceId;
        this.entity = entity;
        this.location = location == null ? null : location.clone();
        this.actor = actor;
        this.previousState = immutable(previousState);
        this.currentState = immutable(currentState);
        this.cause = cause == null ? "" : cause;
    }

    public TargetType getTargetType() { return targetType; }
    public StateType getStateType() { return stateType; }
    public String getDefinitionId() { return definitionId; }
    public UUID getInstanceId() { return instanceId; }
    public Entity getEntity() { return entity; }
    public Location getLocation() { return location == null ? null : location.clone(); }
    public Player getActor() { return actor; }
    public Map<String, String> getPreviousState() { return previousState; }
    public Map<String, String> getCurrentState() { return currentState; }
    public String getCause() { return cause; }

    public Set<String> getChangedKeys() {
        LinkedHashSet<String> keys = new LinkedHashSet<>();
        keys.addAll(previousState.keySet());
        keys.addAll(currentState.keySet());
        keys.removeIf(key -> java.util.Objects.equals(previousState.get(key), currentState.get(key)));
        return Collections.unmodifiableSet(keys);
    }

    @Override public HandlerList getHandlers() { return HANDLERS; }
    public static HandlerList getHandlerList() { return HANDLERS; }

    private static Map<String, String> immutable(Map<String, String> values) {
        if (values == null || values.isEmpty()) return Map.of();
        LinkedHashMap<String, String> copy = new LinkedHashMap<>();
        values.forEach((key, value) -> copy.put(key == null ? "" : key, value == null ? "" : value));
        return Collections.unmodifiableMap(copy);
    }

    public enum TargetType { BLOCK, FURNITURE, ENTITY }
    public enum StateType { CONTENT, ANIMATION }
}
