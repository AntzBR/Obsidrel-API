package me.antzbr.obsidrel.api.importer;

import java.util.Locale;

import me.antzbr.obsidrel.api.ObsidrelApiVersion;

public record SourceImporterDescriptor(String id, String name, String version, int apiLevel) {

    public SourceImporterDescriptor {
        if (id == null) throw new IllegalArgumentException("Importer id is required");
        id = id.trim().toLowerCase(Locale.ROOT);
        if (!id.matches("[a-z0-9][a-z0-9._-]{0,63}")) {
            throw new IllegalArgumentException("Invalid importer id '" + id + "'");
        }
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Importer name is required");
        name = name.trim();
        if (version == null || version.isBlank()) throw new IllegalArgumentException("Importer version is required");
        version = version.trim();
        if (apiLevel <= 0) throw new IllegalArgumentException("Importer apiLevel must be positive");
    }

    public boolean apiCompatible() {
        return ObsidrelApiVersion.supports(apiLevel);
    }
}
