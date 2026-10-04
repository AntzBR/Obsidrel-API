package me.antzbr.obsidrel.api.addon;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        dependencies = dependencies(dependencies, "dependencies", id);
        softDependencies = dependencies(softDependencies, "soft-dependencies", id);
        conflicts = dependencies(conflicts, "conflicts", id);
        ensureDisjoint(dependencies, softDependencies, "dependencies", "soft-dependencies");
        ensureDisjoint(dependencies, conflicts, "dependencies", "conflicts");
        ensureDisjoint(softDependencies, conflicts, "soft-dependencies", "conflicts");
    }

    /** Binary/source bridge for addons compiled against the previous descriptor shape. */
    public AddonDescriptor(String id, String name, String version, String mainClass, int apiLevel,
            String coreRange, List<String> authors, List<AddonDependency> dependencies,
            List<AddonDependency> softDependencies) {
        this(id, name, version, mainClass, apiLevel, coreRange, authors, dependencies, softDependencies, List.of());
    }

    public boolean apiCompatible() {
        return ObsidrelApiVersion.supports(apiLevel);
    }

    private static List<AddonDependency> dependencies(List<AddonDependency> values, String field, String selfId) {
        if (values == null || values.isEmpty()) return List.of();
        Map<String, AddonDependency> unique = new LinkedHashMap<>();
        for (AddonDependency dependency : values) {
            if (dependency == null) throw new IllegalArgumentException("Null addon dependency in " + field);
            if (dependency.id().equals(selfId)) {
                throw new IllegalArgumentException("Addon '" + selfId + "' cannot reference itself in " + field);
            }
            if (unique.putIfAbsent(dependency.id(), dependency) != null) {
                throw new IllegalArgumentException("Duplicate addon dependency '" + dependency.id() + "' in " + field);
            }
        }
        return List.copyOf(unique.values());
    }

    private static void ensureDisjoint(List<AddonDependency> left, List<AddonDependency> right,
                                       String leftName, String rightName) {
        for (AddonDependency dependency : left) {
            if (right.stream().anyMatch(other -> other.id().equals(dependency.id()))) {
                throw new IllegalArgumentException("Addon dependency '" + dependency.id()
                        + "' cannot be declared in both " + leftName + " and " + rightName);
            }
        }
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
