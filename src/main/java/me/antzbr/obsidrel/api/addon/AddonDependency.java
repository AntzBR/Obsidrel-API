package me.antzbr.obsidrel.api.addon;

import java.util.Locale;

public record AddonDependency(String id, String versionRange) {

    public AddonDependency {
        id = normalizeId(id);
        versionRange = versionRange == null || versionRange.isBlank() ? "*" : versionRange.trim();
    }

    static String normalizeId(String value) {
        if (value == null) throw new IllegalArgumentException("Addon id is required");
        String normalized = value.trim().toLowerCase(Locale.ROOT);
        if (!normalized.matches("[a-z0-9][a-z0-9._-]{0,63}")) {
            throw new IllegalArgumentException("Invalid addon id '" + value + "'");
        }
        return normalized;
    }
}
