package me.antzbr.obsidrel.api;

import java.util.Objects;

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

/**
 * Public API facade. Addons receive this facade from {@code AddonContext.api()}.
 * External plugins may use {@link #get()} or {@link ObsidrelProvider}.
 */
public final class ObsidrelAPI implements ObsidrelPlatform {

    private final ObsidrelPlatform delegate;

    public ObsidrelAPI(ObsidrelPlatform delegate) {
        this.delegate = Objects.requireNonNull(delegate);
    }

    public static ObsidrelAPI get() {
        ObsidrelPlatform platform = ObsidrelProvider.get();
        return platform instanceof ObsidrelAPI api ? api : new ObsidrelAPI(platform);
    }

    @Override public int apiLevel() { return delegate.apiLevel(); }
    @Override public String apiVersion() { return delegate.apiVersion(); }
    @Override public String coreVersion() { return delegate.coreVersion(); }
    @Override public ItemAPI items() { return delegate.items(); }
    @Override public BlockAPI blocks() { return delegate.blocks(); }
    @Override public FurnitureAPI furniture() { return delegate.furniture(); }
    @Override public EntityAPI entities() { return delegate.entities(); }
    @Override public RecipeAPI recipes() { return delegate.recipes(); }
    @Override public ArmorAPI armor() { return delegate.armor(); }
    @Override public CosmeticAPI cosmetics() { return delegate.cosmetics(); }
    @Override public PlayerDataAPI playerData() { return delegate.playerData(); }
    @Override public ActionAPI actions() { return delegate.actions(); }
    @Override public LootAPI loot() { return delegate.loot(); }
    @Override public AssetAPI assets() { return delegate.assets(); }
    @Override public UiAPI ui() { return delegate.ui(); }
    @Override public HudAPI huds() { return delegate.huds(); }
    @Override public PackAPI pack() { return delegate.pack(); }
    @Override public PlaceholderAPI placeholders() { return delegate.placeholders(); }
    @Override public ExtensionAPI extensions() { return delegate.extensions(); }
    @Override public AddonRegistry addons() { return delegate.addons(); }
    @Override public AddonServiceRegistry services() { return delegate.services(); }
    @Override public SourceImporterRegistry sourceImporters() { return delegate.sourceImporters(); }
}
