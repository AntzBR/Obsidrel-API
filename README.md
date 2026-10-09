# ObsidrelAPI 1.0.0

[![Maven Central](https://img.shields.io/maven-central/v/io.github.antzbr/ObsidrelAPI?color=blue&label=Maven%20Central)](https://central.sonatype.com/artifact/io.github.antzbr/ObsidrelAPI)
[![](https://jitpack.io/v/AntzBR/ObsidrelAPI.svg)](https://jitpack.io/#AntzBR/ObsidrelAPI)
[![Java Version](https://img.shields.io/badge/Java-21-orange?logo=openjdk)](pom.xml)


Public Java API and addon contracts for **Obsidrel 1.0**.

ObsidrelAPI is intended for two integration models:

- regular Bukkit/Paper plugins that want to access Obsidrel at runtime;
- native Obsidrel addons loaded from `plugins/Obsidrel/addons/`.

The Obsidrel Core provides the API implementation at runtime. **Do not shade or bundle ObsidrelAPI inside your plugin or addon.**

## Requirements

- Java 21
- Obsidrel API level `1`
- ObsidrelAPI

API level 1 is the public contract for the Obsidrel 1.0 line.

## Dependency

### Maven

```xml
<dependency>
    <groupId>io.github.antzbr</groupId>
    <artifactId>ObsidrelAPI</artifactId>
    <version>VERSION</version>
    <scope>provided</scope>
</dependency>
```

### Gradle

```gradle
dependencies {
    compileOnly("io.github.antzbr:ObsidrelAPI:VERSION")
}
```

`provided` / `compileOnly` is required because the API classes are supplied by Obsidrel at runtime.

## External Bukkit/Paper plugin

External plugins can access the public platform through `ObsidrelProvider`.

```java
import me.antzbr.obsidrel.api.ObsidrelPlatform;
import me.antzbr.obsidrel.api.ObsidrelProvider;

import org.bukkit.inventory.ItemStack;

ObsidrelPlatform obsidrel = ObsidrelProvider.require(1);

ItemStack item = obsidrel.items().create("obsidrel:ruby", 1);
```

When Obsidrel is optional:

```java
if (ObsidrelProvider.isAvailable()) {
    ObsidrelPlatform obsidrel = ObsidrelProvider.get();
}
```

You can also use:

```java
ObsidrelProvider.getOptional();
ObsidrelProvider.supports(1);
```

## Public API facade

`ObsidrelPlatform` exposes the stable public services provided by the Core:

```java
obsidrel.items();
obsidrel.blocks();
obsidrel.furniture();
obsidrel.entities();
obsidrel.recipes();
obsidrel.armor();
obsidrel.cosmetics();
obsidrel.playerData();
obsidrel.actions();
obsidrel.loot();
obsidrel.assets();
obsidrel.ui();
obsidrel.huds();
obsidrel.pack();
obsidrel.placeholders();
obsidrel.extensions();
obsidrel.addons();
obsidrel.services();
obsidrel.sourceImporters();
```

It also exposes runtime version information:

```java
obsidrel.apiLevel();
obsidrel.apiVersion();
obsidrel.coreVersion();
```


## Public event contract

Obsidrel events are ordinary Bukkit/Paper events. Plugins use the normal Bukkit listener model, and native addons can register listeners through `AddonContext.registerListener(...)` so cleanup is automatic.

The public contract distinguishes two semantics:

- **PRE / cancellable** — fired before Obsidrel commits the operation. Cancelling the event prevents the Obsidrel-side effect.
- **POST / notification** — fired after the relevant runtime state has been committed. These events are intentionally not cancellable.

Core PRE events include `ObsidrelFurnitureInteractEvent`, `ObsidrelEntityInteractEvent` and `ObsidrelActionExecuteEvent`. Core POST notifications include `ObsidrelAnimationFinishEvent`, `ObsidrelEntitySpawnEvent`, `ObsidrelEntityDeathEvent` and `ObsidrelStateChangeEvent`. Existing block, item, furniture, armor, cosmetic, loot, trigger and player-data events remain available under `me.antzbr.obsidrel.api.event`.

`ObsidrelStateChangeEvent` uses immutable string-map snapshots and identifies the target domain (`BLOCK`, `FURNITURE`, `ENTITY`) plus whether the transition belongs to content state or animation state. This keeps consumers independent from Core implementation classes and allows future integrations to consume new stateful systems without a private bridge.

`ObsidrelAnimationFinishEvent` is a POST notification for authored `ONCE` animations and identifies the runtime target, definition, instance, state, clip, channel and trigger that completed.

## Native Obsidrel addon

A native addon implements:

```java
me.antzbr.obsidrel.api.addon.ObsidrelAddon
```

Example:

```java
package com.example.obsidrel;

import me.antzbr.obsidrel.api.addon.AddonContext;
import me.antzbr.obsidrel.api.addon.ObsidrelAddon;

public final class ExampleAddon implements ObsidrelAddon {

    @Override
    public void onLoad(AddonContext context) {
        context.logger().info("Loading " + context.id());

        context.registerPlaceholder(
                "example",
                player -> "Hello " + player.getName()
        );
    }

    @Override
    public void onEnable(AddonContext context) {
        context.logger().info("Enabled " + context.id());
    }

    @Override
    public void onDisable(AddonContext context) {
        // AddonContext automatically closes tracked registrations/resources.
    }
}
```

Lifecycle order:

```text
onLoad -> onEnable -> onDisable
```

## Addon descriptor

Every addon JAR must contain `obsidrel-addon.yml`.

```yaml
id: example-addon
name: Example Addon
version: 1.0.0
main: com.example.obsidrel.ExampleAddon

api-level: 1
core: ">=0.9.1-RC60-HF1"

authors:
  - Example

dependencies: []
soft-dependencies: []
conflicts: []
```

Dependencies may also include version ranges:

```yaml
dependencies:
  - other-addon:>=1.2.0

soft-dependencies:
  - id: optional-addon
    version: ">=1.0.0"

conflicts:
  - incompatible-addon
```

The runtime rejects invalid descriptors before addon code is linked, including self-dependencies, duplicate declarations and contradictory dependency/soft-dependency/conflict declarations.

## AddonContext

`AddonContext` is the owned runtime context for an addon.

Use:

```java
context.api();              // Public Obsidrel API
context.id();               // Addon id
context.version();          // Addon version
context.descriptor();       // Parsed addon descriptor
context.dataDirectory();    // Addon data directory
context.logger();           // Addon logger
context.services();         // Addon-to-addon services
context.isAddonEnabled(...);
```

Registrations created through the context are tracked automatically:

```java
context.registerService(...);
context.registerSourceImporter(...);
context.registerActionStep(...);
context.registerItemMechanic(...);
context.registerPlaceholder(...);
context.registerListener(...);
context.registerTask(...);
context.track(...);
```

Tracked registrations and `AutoCloseable` resources are closed automatically in reverse order when the addon is disabled or reloaded.

## Addon-to-addon services

Addons can expose public services without depending on another addon's implementation classes.

Producer:

```java
context.registerService(MyService.class, new MyServiceImpl());
```

Consumer:

```java
MyService service = context.services().require(MyService.class);
```

Optional lookup is also available:

```java
context.services().find(MyService.class);
```

Use descriptor `dependencies` or `soft-dependencies` when the relationship between addons must also affect load order or availability.

## Source importer extensions

Source importers are first-class addon extensions.

```java
context.registerSourceImporter(myImporter);
```

Importer implementations use the public contracts under:

```text
me.antzbr.obsidrel.api.importer.*
```

This is the same public extension path used by the official CraftEngine, ItemsAdder, Nexo, Oraxen and Blockbench importers.

## Compatibility and isolation

Native addons must depend only on the public API, Bukkit/Paper APIs and their own libraries.

Do **not** reference internal Core implementation packages such as:

```text
me.antzbr.obsidrel.<internal package>
```

Public integrations should use only:

```text
me.antzbr.obsidrel.api.*
```

The addon runtime validates addon bytecode before classloading and rejects:

- bundled or shaded copies of `me.antzbr.obsidrel.api.*`;
- direct references to Obsidrel Core implementation classes;
- unsupported API levels;
- incompatible Core version ranges;
- missing hard dependencies;
- hard dependency cycles;
- declared conflicts.

Soft dependencies are optional and do not become hard dependencies.

## API version checks

The public contract exposes both an API version and an API level.

```java
import me.antzbr.obsidrel.api.ObsidrelApiVersion;

ObsidrelApiVersion.LEVEL;      // 1
ObsidrelApiVersion.VERSION;    // 1.0.0-REV-2.0-RELEASE

ObsidrelApiVersion.supports(1);
ObsidrelApiVersion.require(1);
```

For an external plugin, prefer checking the runtime implementation:

```java
ObsidrelPlatform obsidrel = ObsidrelProvider.require(1);
```

## Distribution

ObsidrelAPI is a compile-time dependency.

Do not:

```text
shade ObsidrelAPI
relocate ObsidrelAPI
copy API classes into your JAR
depend directly on Core implementation classes
```

The Obsidrel Core owns the runtime API classes.

## License

ObsidrelAPI is distributed under the MIT License.
