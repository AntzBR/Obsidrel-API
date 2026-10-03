package me.antzbr.obsidrel.api.view;

import java.util.Locale;

public record PlayerValueView(String key, String type, String value, long updatedAt) {
    public PlayerValueView {
        key = key == null ? "" : key;
        type = type == null ? "" : type;
        value = value == null ? "" : value;
        updatedAt = Math.max(0L, updatedAt);
    }

    public double asDouble(double fallback) {
        try { return Double.parseDouble(value.trim()); }
        catch (RuntimeException ignored) { return fallback; }
    }

    public long asLong(long fallback) {
        try { return Long.parseLong(value.trim()); }
        catch (RuntimeException ignored) {
            try { return (long) Double.parseDouble(value.trim()); }
            catch (RuntimeException second) { return fallback; }
        }
    }

    public boolean asBoolean(boolean fallback) {
        String normalized = value.trim().toLowerCase(Locale.ROOT);
        if (normalized.equals("true") || normalized.equals("yes") || normalized.equals("on") || normalized.equals("1")) return true;
        if (normalized.equals("false") || normalized.equals("no") || normalized.equals("off") || normalized.equals("0")) return false;
        return fallback;
    }
}
