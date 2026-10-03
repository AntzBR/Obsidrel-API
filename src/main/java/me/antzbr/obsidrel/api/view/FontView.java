package me.antzbr.obsidrel.api.view;

public record FontView(String id, int glyphs) {
    public FontView { id = id == null ? "" : id; glyphs = Math.max(0, glyphs); }
}
