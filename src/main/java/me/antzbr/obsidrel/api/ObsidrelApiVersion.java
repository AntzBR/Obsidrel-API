package me.antzbr.obsidrel.api;

/** Public Obsidrel API contract version frozen for the 1.0 line. */
public final class ObsidrelApiVersion {

    public static final int LEVEL = 1;
    public static final String VERSION = "1.0.0";

    private ObsidrelApiVersion() {
    }

    public static boolean supports(int requiredLevel) {
        return requiredLevel > 0 && requiredLevel <= LEVEL;
    }
}
