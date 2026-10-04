package me.antzbr.obsidrel.api.addon;

import me.antzbr.obsidrel.api.ObsidrelApiVersion;

/** Stable constants for the Obsidrel 1.0 addon contract. */
public final class AddonContract {

    public static final String DESCRIPTOR_FILE = "obsidrel-addon.yml";
    public static final int API_LEVEL = ObsidrelApiVersion.LEVEL;
    public static final String API_VERSION = ObsidrelApiVersion.VERSION;

    private AddonContract() {
    }
}
