package me.antzbr.obsidrel.api.service;

import java.util.Map;

import org.bukkit.entity.Player;

public interface PlaceholderAPI {
    String render(Player player, String input);
    String render(Player player, String input, Map<String, String> context);
    Map<String, String> values(Player player);
}
