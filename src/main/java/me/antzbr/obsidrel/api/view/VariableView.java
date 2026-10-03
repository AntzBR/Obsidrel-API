package me.antzbr.obsidrel.api.view;

public record VariableView(String id, String type, String defaultValue, Double min, Double max,
                           String description) {
    public VariableView {
        id = id == null ? "" : id;
        type = type == null ? "" : type;
        defaultValue = defaultValue == null ? "" : defaultValue;
        description = description == null ? "" : description;
    }
}
