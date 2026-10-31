package me.antzbr.obsidrel.api.service;

import me.antzbr.obsidrel.api.view.PackCompatibilityView;
import me.antzbr.obsidrel.api.view.PackPublicationView;
import me.antzbr.obsidrel.api.view.PackTargetView;

public interface PackAPI {
    PackTargetView target();
    PackCompatibilityView compatibility();
    String distributionMode();
    String outputFile();

    /**
     * Read-only, provider-derived publication snapshot. Implemented as a
     * default method to preserve binary compatibility with older providers.
     */
    default PackPublicationView publication() {
        return PackPublicationView.unavailable();
    }
}
