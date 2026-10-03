package me.antzbr.obsidrel.api.view;

public record RecipeView(String id, boolean enabled, String type, String result, int amount) {
    public RecipeView {
        id = id == null ? "" : id;
        type = type == null ? "" : type;
        result = result == null ? "" : result;
        amount = Math.max(1, amount);
    }
}
