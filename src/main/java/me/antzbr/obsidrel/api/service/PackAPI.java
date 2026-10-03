package me.antzbr.obsidrel.api.service;

import me.antzbr.obsidrel.api.view.PackCompatibilityView;
import me.antzbr.obsidrel.api.view.PackTargetView;

public interface PackAPI {
    PackTargetView target();
    PackCompatibilityView compatibility();
    String distributionMode();
    String outputFile();
}
