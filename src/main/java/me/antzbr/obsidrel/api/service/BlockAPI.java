package me.antzbr.obsidrel.api.service;

import java.util.List;
import java.util.Optional;

import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import me.antzbr.obsidrel.api.view.BlockView;
import me.antzbr.obsidrel.api.view.PlacedBlockView;

public interface BlockAPI {
    List<BlockView> all();
    Optional<BlockView> find(String id);
    Optional<BlockView> identify(ItemStack item);
    Optional<BlockView> identify(Block block);
    Optional<PlacedBlockView> placed(Block block);
    ItemStack createItem(String id, int amount);
}
