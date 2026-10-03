package me.antzbr.obsidrel.api.service;

import java.util.List;
import java.util.Optional;

import org.bukkit.entity.Player;

import me.antzbr.obsidrel.api.view.PlayerValueView;
import me.antzbr.obsidrel.api.view.VariableView;

public interface PlayerDataAPI {
    List<VariableView> variables();
    Optional<VariableView> findVariable(String id);
    PlayerValueView value(Player player, String id);
    PlayerValueView set(Player player, String id, Object value);
    PlayerValueView add(Player player, String id, double amount);
    boolean reset(Player player, String id);
    long startTimer(Player player, String id, long durationMillis);
    long timerRemaining(Player player, String id);
}
