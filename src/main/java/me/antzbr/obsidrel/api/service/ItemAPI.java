package me.antzbr.obsidrel.api.service;

import java.util.List;
import java.util.Optional;

import org.bukkit.inventory.ItemStack;

import me.antzbr.obsidrel.api.view.ItemView;

public interface ItemAPI {
    List<ItemView> all();
    Optional<ItemView> find(String id);
    Optional<ItemView> identify(ItemStack item);
    ItemStack create(String id, int amount);
}
