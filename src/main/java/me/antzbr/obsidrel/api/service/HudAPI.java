package me.antzbr.obsidrel.api.service;

import java.util.List;
import java.util.Optional;

import org.bukkit.entity.Player;

import me.antzbr.obsidrel.api.view.HudView;

public interface HudAPI {
    List<HudView> all();
    Optional<HudView> find(String id);
    boolean show(Player player, String id);
    boolean hide(Player player, String id);
    boolean toggle(Player player, String id);
    void resetPreferences(Player player);
}
