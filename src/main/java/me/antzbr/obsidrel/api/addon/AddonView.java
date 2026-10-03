package me.antzbr.obsidrel.api.addon;

public record AddonView(AddonDescriptor descriptor, AddonState state, String detail) {

    public AddonView {
        if (descriptor == null) throw new IllegalArgumentException("descriptor is required");
        if (state == null) state = AddonState.DISCOVERED;
        detail = detail == null ? "" : detail.trim();
    }
}
