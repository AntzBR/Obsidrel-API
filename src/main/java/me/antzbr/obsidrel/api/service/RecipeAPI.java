package me.antzbr.obsidrel.api.service;

import java.util.List;
import java.util.Optional;

import me.antzbr.obsidrel.api.view.RecipeView;

public interface RecipeAPI {
    List<RecipeView> all();
    Optional<RecipeView> find(String id);
}
