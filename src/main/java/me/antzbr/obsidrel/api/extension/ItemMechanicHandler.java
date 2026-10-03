package me.antzbr.obsidrel.api.extension;

import me.antzbr.obsidrel.api.view.ItemMechanicContextView;
import me.antzbr.obsidrel.api.view.ItemMechanicView;

@FunctionalInterface
public interface ItemMechanicHandler {
    boolean execute(ItemMechanicContextView context, ItemMechanicView mechanic) throws Exception;
}
