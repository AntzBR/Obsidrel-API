package me.antzbr.obsidrel.api.view;

public record SoundView(String id, String category, int clips) {
    public SoundView {
        id = id == null ? "" : id;
        category = category == null ? "" : category;
        clips = Math.max(0, clips);
    }
}
