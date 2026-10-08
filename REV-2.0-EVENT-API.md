# ObsidrelAPI 1.0.0-REV-2.0-RELEASE — Public Event Foundation

This revision establishes the PRE/cancellable and POST/notification event semantics that will be frozen for the 1.0 API line.

New public events:

- `ObsidrelEntityInteractEvent` (PRE / cancellable)
- `ObsidrelEntitySpawnEvent` (POST)
- `ObsidrelEntityDeathEvent` (POST)
- `ObsidrelAnimationFinishEvent` (POST)
- `ObsidrelStateChangeEvent` (POST)

`ObsidrelFurnitureInteractEvent` and `ObsidrelActionExecuteEvent` remain PRE/cancellable and are documented as such.

The event contract is intended for any Bukkit/Paper plugin or native Obsidrel addon. A future Obsidrel-Skript addon can translate the same public events without accessing Core internals.
