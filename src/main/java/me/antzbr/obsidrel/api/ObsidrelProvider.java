package me.antzbr.obsidrel.api;

import java.util.Optional;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

/** Runtime lookup entry point for plugins that are not loaded as Obsidrel addons. */
public final class ObsidrelProvider {

    private ObsidrelProvider() {
    }

    public static ObsidrelPlatform get() {
        return getOptional().orElseThrow(() -> new IllegalStateException("Obsidrel API is not available"));
    }

    public static ObsidrelPlatform require(int apiLevel) {
        ObsidrelPlatform platform = get();
        if (apiLevel <= 0 || apiLevel > platform.apiLevel()) {
            throw new IllegalStateException("Obsidrel API level " + apiLevel
                    + " is not supported by runtime API level " + platform.apiLevel());
        }
        return platform;
    }

    public static boolean supports(int apiLevel) {
        return apiLevel > 0 && getOptional().map(platform -> apiLevel <= platform.apiLevel()).orElse(false);
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
