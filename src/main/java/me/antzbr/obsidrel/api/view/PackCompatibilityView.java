package me.antzbr.obsidrel.api.view;

import java.util.List;

public record PackCompatibilityView(String stage, String targetVersion, String format,
                                    int files, int jsonFiles, int pngFiles, int oggFiles,
                                    List<String> errors, List<String> warnings) {
    public PackCompatibilityView {
        stage = stage == null ? "" : stage;
        targetVersion = targetVersion == null ? "" : targetVersion;
        format = format == null ? "" : format;
        errors = errors == null ? List.of() : List.copyOf(errors);
        warnings = warnings == null ? List.of() : List.copyOf(warnings);
    }
    public boolean valid() { return errors.isEmpty(); }
    public int issueCount() { return errors.size() + warnings.size(); }
}
