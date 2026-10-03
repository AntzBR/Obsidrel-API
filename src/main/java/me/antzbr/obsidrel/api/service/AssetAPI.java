package me.antzbr.obsidrel.api.service;

import java.util.List;
import java.util.Optional;

import org.bukkit.entity.Player;

import me.antzbr.obsidrel.api.view.FontView;
import me.antzbr.obsidrel.api.view.SoundView;

public interface AssetAPI {
    List<FontView> fonts();
    List<SoundView> sounds();
    Optional<FontView> findFont(String id);
    Optional<SoundView> findSound(String id);
    String glyph(String fontId, String glyphId);
    Integer glyphCodepoint(String fontId, String glyphId);
    String glyphMarkup(String fontId, String glyphId);
    boolean playSound(Player player, String id);
    boolean playSound(Player player, String id, float volume, float pitch);
}
