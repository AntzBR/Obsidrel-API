package me.antzbr.obsidrel.api.service;

import java.util.List;
import java.util.Optional;

import org.bukkit.entity.Player;

import me.antzbr.obsidrel.api.view.UiMenuView;
import me.antzbr.obsidrel.api.view.UiOpenResult;

public interface UiAPI {
    List<UiMenuView> all();
    Optional<UiMenuView> find(String id);
    UiOpenResult open(Player player, String id);
    UiOpenResult open(Player player, String id, int page);
    UiOpenResult openPlayerHub(Player player);
    UiOpenResult openAdminHub(Player player);
    List<String> available(Player player);
}
