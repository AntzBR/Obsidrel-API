package me.antzbr.obsidrel.api.service;

import java.util.List;
import java.util.Optional;

import org.bukkit.entity.Player;

import me.antzbr.obsidrel.api.view.ActionExecutionResult;
import me.antzbr.obsidrel.api.view.ActionView;
import me.antzbr.obsidrel.api.view.ConditionEvaluation;
import me.antzbr.obsidrel.api.view.ConditionView;
import me.antzbr.obsidrel.api.view.CooldownView;
import me.antzbr.obsidrel.api.view.TriggerView;

public interface ActionAPI {
    List<ActionView> actions();
    List<TriggerView> triggers();
    List<ConditionView> conditions();
    List<CooldownView> cooldowns();
    Optional<ActionView> findAction(String id);
    Optional<TriggerView> findTrigger(String id);
    Optional<ConditionView> findCondition(String id);
    Optional<CooldownView> findCooldown(String id);
    ActionExecutionResult execute(String id, Player player);
    ConditionEvaluation testCondition(String id, Player player);
    long cooldownRemaining(Player player, String id);
    boolean resetCooldown(Player player, String id);
}
