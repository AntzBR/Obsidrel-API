package me.antzbr.obsidrel.api.importer;

public enum SourceImportStatus {
    SUCCESS,
    SUCCESS_WITH_WARNINGS,
    PARTIAL,
    FAILED;

    public boolean reusable() {
        return this != FAILED;
    }
}
