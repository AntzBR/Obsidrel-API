package me.antzbr.obsidrel.api.view;

import java.util.Map;

public record ActionStepView(String id, String type, int delayTicks, double chance,
                             String permission, boolean cancelEvent, Map<String, Object> options) {
    public ActionStepView {
        id = id == null ? "" : id;
        type = type == null ? "" : type;
        permission = permission == null ? "" : permission;
        options = options == null ? Map.of() : Map.copyOf(options);
    }
}
