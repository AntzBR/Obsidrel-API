package me.antzbr.obsidrel.api.service;

import java.util.List;
import java.util.Optional;

import org.bukkit.entity.Player;

import me.antzbr.obsidrel.api.view.LootRollResult;
import me.antzbr.obsidrel.api.view.LootTableView;

public interface LootAPI {
    List<LootTableView> all();
    Optional<LootTableView> find(String id);
    LootRollResult roll(String id, Player player);
    LootRollResult roll(String id, Player player, String delivery);
}
