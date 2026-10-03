package me.antzbr.obsidrel.api;

import java.util.Optional;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

/** Runtime lookup entry point for plugins that are not loaded as Obsidrel addons. */
public final class ObsidrelProvider {

    private ObsidrelProvider() {
    }

    public static ObsidrelPlatform get() {
        return getOptional().orElseThrow(IllegalStateException::new);
    }

    public static Optional<ObsidrelPlatform> getOptional() {
        RegisteredServiceProvider<ObsidrelPlatform> registration =
                Bukkit.getServicesManager().getRegistration(ObsidrelPlatform.class);
        return registration == null ? Optional.empty() : Optional.ofNullable(registration.getProvider());
    }

    public static boolean isAvailable() {
        return getOptional().isPresent();
    }
}
