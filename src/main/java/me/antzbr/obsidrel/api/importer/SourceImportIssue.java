package me.antzbr.obsidrel.api.importer;

public record SourceImportIssue(Level level, String subject, String message) {

    public SourceImportIssue {
        if (level == null) level = Level.INFO;
        subject = subject == null ? "" : subject.trim();
        message = message == null ? "" : message.trim();
    }

    public enum Level {
        INFO,
        WARNING,
        ERROR
    }
}
