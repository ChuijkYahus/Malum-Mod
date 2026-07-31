package com.sammy.malum.client.screen.codex.display;

public interface IGizmoHolder {

    default boolean shouldGizmoRenderTooltip() {
        return true;
    }

    default String getGizmoTooltipKey() {
        return "";
    }
}
