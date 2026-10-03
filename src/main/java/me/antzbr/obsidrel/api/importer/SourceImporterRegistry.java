package me.antzbr.obsidrel.api.importer;

import java.util.List;
import java.util.Optional;

public interface SourceImporterRegistry {

    List<SourceImporterDescriptor> all();

    Optional<SourceImporterDescriptor> find(String id);
}
