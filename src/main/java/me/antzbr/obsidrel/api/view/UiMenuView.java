package me.antzbr.obsidrel.api.view;

public record UiMenuView(String id, String title, int size) {
    public UiMenuView { id = id == null ? "" : id; title = title == null ? "" : title; }
}
