package me.antzbr.obsidrel.api.extension;

import me.antzbr.obsidrel.api.view.ActionContextView;
import me.antzbr.obsidrel.api.view.ActionStepView;

@FunctionalInterface
public interface ActionStepHandler {
    boolean execute(ActionContextView context, ActionStepView step) throws Exception;
}
