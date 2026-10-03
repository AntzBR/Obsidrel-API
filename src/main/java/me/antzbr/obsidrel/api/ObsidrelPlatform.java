package me.antzbr.obsidrel.api;

import me.antzbr.obsidrel.api.addon.AddonRegistry;
import me.antzbr.obsidrel.api.addon.AddonServiceRegistry;
import me.antzbr.obsidrel.api.importer.SourceImporterRegistry;
import me.antzbr.obsidrel.api.service.ActionAPI;
import me.antzbr.obsidrel.api.service.ArmorAPI;
import me.antzbr.obsidrel.api.service.AssetAPI;
import me.antzbr.obsidrel.api.service.BlockAPI;
import me.antzbr.obsidrel.api.service.CosmeticAPI;
import me.antzbr.obsidrel.api.service.EntityAPI;
import me.antzbr.obsidrel.api.service.ExtensionAPI;
import me.antzbr.obsidrel.api.service.FurnitureAPI;
import me.antzbr.obsidrel.api.service.HudAPI;
import me.antzbr.obsidrel.api.service.ItemAPI;
import me.antzbr.obsidrel.api.service.LootAPI;
import me.antzbr.obsidrel.api.service.PackAPI;
import me.antzbr.obsidrel.api.service.PlaceholderAPI;
import me.antzbr.obsidrel.api.service.PlayerDataAPI;
import me.antzbr.obsidrel.api.service.RecipeAPI;
import me.antzbr.obsidrel.api.service.UiAPI;

/** Stable public facade implemented by the Obsidrel Core. */
public interface ObsidrelPlatform {

    int apiLevel();
    String apiVersion();
    String coreVersion();

    ItemAPI items();
    BlockAPI blocks();
    FurnitureAPI furniture();
    EntityAPI entities();
    RecipeAPI recipes();
    ArmorAPI armor();
    CosmeticAPI cosmetics();
    PlayerDataAPI playerData();
    ActionAPI actions();
    LootAPI loot();
    AssetAPI assets();
    UiAPI ui();
    HudAPI huds();
    PackAPI pack();
    PlaceholderAPI placeholders();
    ExtensionAPI extensions();

    AddonRegistry addons();
    AddonServiceRegistry services();
    SourceImporterRegistry sourceImporters();
}
