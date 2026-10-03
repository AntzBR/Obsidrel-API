package me.antzbr.obsidrel.api.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.bukkit.entity.Player;

import me.antzbr.obsidrel.api.view.CosmeticView;

public interface CosmeticAPI {
    List<CosmeticView> all();
    Optional<CosmeticView> find(String id);
    boolean equip(Player player, String id);
    boolean clear(Player player, String slot);
    int clearAll(Player player);
    Map<String, CosmeticView> selected(Player player);
    String visualProvider();
}
