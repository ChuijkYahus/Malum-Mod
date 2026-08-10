package com.sammy.malum.client.screen.codex.display;

import com.sammy.malum.client.screen.codex.display.gizmo.GizmoTooltipBuilder;

public interface IGizmoHolder {

    default boolean shouldGizmoRenderTooltip() {
        return true;
    }

    default boolean shouldGizmoBeConsideredHoveredOver() {
        return false;
    }

    default void addGizmoTooltip(GizmoTooltipBuilder builder) {
    }
}
