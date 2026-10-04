package me.antzbr.obsidrel.api.addon;

import java.util.List;
import java.util.Optional;

public interface AddonServiceRegistry {

    <T> Optional<T> find(Class<T> type);

    <T> List<T> all(Class<T> type);

    default <T> T require(Class<T> type) {
        return find(type).orElseThrow(() -> new IllegalStateException(
                "Required Obsidrel addon service is not available: " + (type == null ? "null" : type.getName())));
    }
}

