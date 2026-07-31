package com.sammy.malum.client.screen.codex.display.gizmo;

import com.sammy.malum.client.screen.codex.display.IGizmoHolder;
import com.sammy.malum.client.screen.codex.screens.AbstractMalumCodexScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

import java.awt.*;
import java.util.*;
import java.util.List;

public abstract class DisplayedGizmo {

    public static String title(String key) {
        return "malum.gui.book.gizmo." + key + ".title";
    }

    public static String subtext(String key) {
        return "malum.gui.book.gizmo." + key + ".subtext";
    }

    protected boolean isHoveredOver;
    protected Color color = Color.WHITE;

    public final void render(AbstractMalumCodexScreen screen, IGizmoHolder holder, GuiGraphics guiGraphics, int x, int y, int mouseX, int mouseY) {
        if (!isHoveredOver) {
            isHoveredOver = holder.shouldGizmoBeConsideredHoveredOver() || screen.isHovering(mouseX, mouseY, x, y, 16, 16);
        }
        boolean isHoveredCache = isHoveredOver;
        renderDecals(screen, holder, guiGraphics, x, y, mouseX, mouseY);
        resetValues();
        if (!holder.shouldGizmoRenderTooltip()) {
            return;
        }
        if (isHoveredCache) {
            var tooltip = new ArrayList<Component>();
            var builder = new GizmoTooltipBuilder(tooltip);
            holder.addGizmoTooltip(builder);

            if (tooltip.isEmpty()) {
                gatherTooltip(holder, builder);
            }
            screen.renderLater(() -> guiGraphics.renderComponentTooltip(Minecraft.getInstance().font, tooltip, mouseX, mouseY));
        }
    }

    public void gatherTooltip(IGizmoHolder holder, GizmoTooltipBuilder tooltip) {

    }

    public void resetValues() {
        isHoveredOver = false;
        color = Color.WHITE;
    }

    public DisplayedGizmo setColor(Color color) {
        this.color = color;
        return this;
    }

    public abstract void renderDecals(AbstractMalumCodexScreen screen, IGizmoHolder holder, GuiGraphics guiGraphics, int x, int y, int mouseX, int mouseY);

}
