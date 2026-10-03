package me.antzbr.obsidrel.api.addon;

import java.nio.file.Path;
import java.util.function.Function;
import java.util.logging.Logger;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitTask;

import me.antzbr.obsidrel.api.ObsidrelAPI;
import me.antzbr.obsidrel.api.extension.ActionStepHandler;
import me.antzbr.obsidrel.api.extension.ItemMechanicHandler;
import me.antzbr.obsidrel.api.importer.SourceImporterProvider;

public interface AddonContext {

    ObsidrelAPI api();

    AddonDescriptor descriptor();

    AddonServiceRegistry services();

    Path dataDirectory();

    Logger logger();

    <T> AddonRegistration registerService(Class<T> type, T service);

    AddonRegistration registerSourceImporter(SourceImporterProvider provider);

    AddonRegistration registerActionStep(String type, ActionStepHandler handler);

    AddonRegistration registerItemMechanic(String type, ItemMechanicHandler handler);

    AddonRegistration registerPlaceholder(String id, Function<Player, String> provider);

    AddonRegistration registerListener(Listener listener);

    AddonRegistration registerTask(BukkitTask task);

    AddonRegistration track(AutoCloseable resource);
}
