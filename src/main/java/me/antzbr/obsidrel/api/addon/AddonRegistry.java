package me.antzbr.obsidrel.api.addon;

import java.util.List;
import java.util.Optional;

public interface AddonRegistry {

    List<AddonView> all();

    Optional<AddonView> find(String id);
}
