package me.antzbr.obsidrel.api.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.bukkit.entity.Player;

import me.antzbr.obsidrel.api.view.ArmorSetView;
import me.antzbr.obsidrel.api.view.ArmorView;

public interface ArmorAPI {
    List<ArmorView> all();
    List<ArmorSetView> allSets();
    Optional<ArmorView> find(String itemId);
    Optional<ArmorSetView> findSet(String id);
    Map<String, ArmorView> equipped(Player player);
    int equippedPieces(Player player, String setId);
    Set<Integer> activeBonuses(Player player, String setId);
}
