package me.antzbr.obsidrel.api.addon;

import java.util.List;

import me.antzbr.obsidrel.api.ObsidrelApiVersion;

public record AddonDescriptor(
        String id,
        String name,
        String version,
        String mainClass,
        int apiLevel,
        String coreRange,
        List<String> authors,
        List<AddonDependency> dependencies,
        List<AddonDependency> softDependencies,
        List<AddonDependency> conflicts) {

    public AddonDescriptor {
        id = AddonDependency.normalizeId(id);
        name = requireText(name, "Addon name");
        version = requireText(version, "Addon version");
        mainClass = requireText(mainClass, "Addon main class");
        if (apiLevel <= 0) throw new IllegalArgumentException("Addon apiLevel must be positive");
        coreRange = coreRange == null || coreRange.isBlank() ? "*" : coreRange.trim();
        authors = immutableStrings(authors);
        dependencies = dependencies == null ? List.of() : List.copyOf(dependencies);
        softDependencies = softDependencies == null ? List.of() : List.copyOf(softDependencies);
        conflicts = conflicts == null ? List.of() : List.copyOf(conflicts);
    }

    /** Binary/source bridge for RC47-RC51 addon code that used the previous descriptor shape. */
    public AddonDescriptor(String id, String name, String version, String mainClass, int apiLevel,
            String coreRange, List<String> authors, List<AddonDependency> dependencies,
            List<AddonDependency> softDependencies) {
        this(id, name, version, mainClass, apiLevel, coreRange, authors, dependencies, softDependencies, List.of());
    }

    public boolean apiCompatible() {
        return ObsidrelApiVersion.supports(apiLevel);
    }

    private static String requireText(String value, String label) {
        if (value == null || value.isBlank()) throw new IllegalArgumentException(label + " is required");
        return value.trim();
    }

    private static List<String> immutableStrings(List<String> values) {
        if (values == null || values.isEmpty()) return List.of();
        return values.stream()
                .filter(value -> value != null && !value.isBlank())
                .map(String::trim)
                .distinct()
                .toList();
    }
}
