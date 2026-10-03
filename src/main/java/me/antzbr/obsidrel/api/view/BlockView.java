package me.antzbr.obsidrel.api.view;

public record BlockView(String id, String name, String carrier, String orientation,
                        boolean modeled, double hardness) {
    public BlockView {
        id = id == null ? "" : id;
        name = name == null ? "" : name;
        carrier = carrier == null ? "" : carrier;
        orientation = orientation == null ? "" : orientation;
    }
}
