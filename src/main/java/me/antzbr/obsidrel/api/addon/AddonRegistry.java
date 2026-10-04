package me.antzbr.obsidrel.api.addon;

import java.util.List;
import java.util.Optional;

public interface AddonRegistry {

    List<AddonView> all();

    Optional<AddonView> find(String id);

    default List<AddonView> enabled() {
        return all().stream().filter(view -> view.state() == AddonState.ENABLED).toList();
    }

    default boolean isEnabled(String id) {
        return find(id).map(view -> view.state() == AddonState.ENABLED).orElse(false);
    }
}

