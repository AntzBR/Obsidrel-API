package me.antzbr.obsidrel.api.view;

public record PackTargetView(String minecraftVersion, String format, String support,
                             boolean preview, String compilerMode, String metadataMode,
                             String atlasMode, String details) {
    public PackTargetView {
        minecraftVersion = minecraftVersion == null ? "" : minecraftVersion;
        format = format == null ? "" : format;
        support = support == null ? "" : support;
        compilerMode = compilerMode == null ? "" : compilerMode;
        metadataMode = metadataMode == null ? "" : metadataMode;
        atlasMode = atlasMode == null ? "" : atlasMode;
        details = details == null ? "" : details;
    }
}
