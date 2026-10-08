package me.antzbr.obsidrel.api;

/** Public Obsidrel API contract revision being finalized for the 1.0 line. */
public final class ObsidrelApiVersion {

    public static final int LEVEL = 1;
    public static final String VERSION = "1.0.0-REV-2.0-RELEASE";

    private ObsidrelApiVersion() {
    }

    public static boolean supports(int requiredLevel) {
        return requiredLevel > 0 && requiredLevel <= LEVEL;
    }

    public static void require(int requiredLevel) {
        if (!supports(requiredLevel)) {
            throw new IllegalStateException("Obsidrel API level " + requiredLevel
                    + " is not supported by runtime API level " + LEVEL);
        }
    }
}
