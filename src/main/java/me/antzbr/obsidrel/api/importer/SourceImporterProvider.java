package me.antzbr.obsidrel.api.importer;

import java.nio.file.Path;

public interface SourceImporterProvider {

    SourceImporterDescriptor descriptor();

    default boolean supports(Path source) {
        return source != null;
    }

    SourceImportResult importSource(SourceImportRequest request) throws Exception;
}
