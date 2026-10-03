# ObsidrelAPI 1.0.0

Public Java contracts for Obsidrel 1.0.

## Maven

```xml
<dependency>
    <groupId>me.antzbr.obsidrel</groupId>
    <artifactId>ObsidrelAPI</artifactId>
    <version>1.0.0</version>
    <scope>provided</scope>
</dependency>
```

Obsidrel ships the API classes at runtime. Do not shade or bundle `ObsidrelAPI` inside an addon/plugin.

## External Bukkit/Paper plugin

```java
ObsidrelPlatform obsidrel = ObsidrelProvider.get();
ItemStack item = obsidrel.items().create("obsidrel:ruby", 1);
```

`ObsidrelProvider.getOptional()` can be used when Obsidrel is an optional dependency.

## Obsidrel addon

Addons receive an `AddonContext` in their lifecycle hooks and should use `context.api()` for Core services. Registrations returned by `AddonContext` are tracked and cleaned up automatically when the addon is disabled/reloaded.

Public addons must depend only on `me.antzbr.obsidrel.api.*`. RC53 rejects bytecode references to internal Core packages before classloading.
