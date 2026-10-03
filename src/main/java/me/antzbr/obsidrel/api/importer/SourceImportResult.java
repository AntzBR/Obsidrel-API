package me.antzbr.obsidrel.api.importer;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public final class SourceImportResult {

    private final Map<String, Integer> stats = new LinkedHashMap<>();
    private final List<SourceImportIssue> issues = new ArrayList<>();
    private boolean partial;
    private boolean failed;

    public void increment(String key) {
        add(key, 1);
    }

    public void add(String key, int amount) {
        if (key == null || key.isBlank() || amount == 0) return;
        stats.merge(key.trim(), amount, Integer::sum);
    }

    public void info(String subject, String message) {
        issues.add(new SourceImportIssue(SourceImportIssue.Level.INFO, subject, message));
    }

    public void warning(String subject, String message) {
        issues.add(new SourceImportIssue(SourceImportIssue.Level.WARNING, subject, message));
    }

    public void error(String subject, String message) {
        issues.add(new SourceImportIssue(SourceImportIssue.Level.ERROR, subject, message));
        partial = true;
    }

    public void markPartial() {
        partial = true;
    }

    public void fail(String subject, String message) {
        issues.add(new SourceImportIssue(SourceImportIssue.Level.ERROR, subject, message));
        failed = true;
    }

    public void markFailed() {
        failed = true;
    }

    public Map<String, Integer> stats() {
        return Collections.unmodifiableMap(stats);
    }

    public List<SourceImportIssue> issues() {
        return Collections.unmodifiableList(issues);
    }

    public int warnings() {
        return (int) issues.stream().filter(issue -> issue.level() == SourceImportIssue.Level.WARNING).count();
    }

    public int errors() {
        return (int) issues.stream().filter(issue -> issue.level() == SourceImportIssue.Level.ERROR).count();
    }

    public SourceImportStatus status() {
        if (failed) return SourceImportStatus.FAILED;
        if (partial || errors() > 0) return SourceImportStatus.PARTIAL;
        if (warnings() > 0) return SourceImportStatus.SUCCESS_WITH_WARNINGS;
        return SourceImportStatus.SUCCESS;
    }
}
