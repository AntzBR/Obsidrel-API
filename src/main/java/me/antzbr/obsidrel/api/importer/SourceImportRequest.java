package me.antzbr.obsidrel.api.importer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public record SourceImportRequest(
        String sourceId,
        Path originalSource,
        Path sourceRoot,
        Path stagingRoot,
        String namespace) {

    public SourceImportRequest {
        if (sourceId == null || sourceId.isBlank()) throw new IllegalArgumentException("sourceId is required");
        sourceId = sourceId.trim();
        if (originalSource == null) throw new IllegalArgumentException("originalSource is required");
        if (sourceRoot == null) throw new IllegalArgumentException("sourceRoot is required");
        if (stagingRoot == null) throw new IllegalArgumentException("stagingRoot is required");
        if (namespace == null || namespace.isBlank()) throw new IllegalArgumentException("namespace is required");
        namespace = namespace.trim();
    }

    public Path runtimeFile(String category) throws IOException {
        if (category == null || !category.matches("[a-z0-9-]+")) {
            throw new IllegalArgumentException("Invalid runtime category '" + category + "'");
        }
        Path runtime = stagingRoot.resolve("runtime");
        Files.createDirectories(runtime);
        return runtime.resolve(category + ".yml");
    }

    public Path packRoot() throws IOException {
        Path pack = stagingRoot.resolve("pack");
        Files.createDirectories(pack);
        return pack;
    }

    public Path generatedRoot() throws IOException {
        Path generated = stagingRoot.resolve("generated");
        Files.createDirectories(generated);
        return generated;
    }
}
