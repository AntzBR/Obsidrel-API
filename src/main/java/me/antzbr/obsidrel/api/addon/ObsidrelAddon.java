package me.antzbr.obsidrel.api.addon;

public interface ObsidrelAddon {

    default void onLoad(AddonContext context) throws Exception {
    }

    default void onEnable(AddonContext context) throws Exception {
    }

    default void onDisable(AddonContext context) throws Exception {
    }
}
