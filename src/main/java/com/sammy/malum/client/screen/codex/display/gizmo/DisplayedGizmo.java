package com.sammy.malum.client.screen.codex.display.gizmo;

import com.sammy.malum.client.screen.codex.display.IGizmoHolder;
import com.sammy.malum.client.screen.codex.pages.*;
import com.sammy.malum.client.screen.codex.pages.text.*;
import com.sammy.malum.client.screen.codex.screens.AbstractMalumCodexScreen;
import com.sammy.malum.client.screen.codex.screens.progression.AbstractProgressionCodexScreen;
import net.minecraft.ChatFormatting;
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
            isHoveredOver = screen.isHovering(mouseX, mouseY, x, y, 16, 16);
        }
        boolean isHoveredCache = isHoveredOver;
        renderDecals(screen, holder, guiGraphics, x, y, mouseX, mouseY);
        resetValues();
        if (screen instanceof AbstractProgressionCodexScreen) {
            return;
        }
        if (!holder.shouldGizmoRenderTooltip()) {
            return;
        }
        if (isHoveredCache) {
            var tooltip = new ArrayList<Component>();
            addUniqueTooltip(holder, tooltip);
            if (tooltip.isEmpty()) {
                gatherTooltip(holder, tooltip);
            }
            screen.renderLater(() -> guiGraphics.renderComponentTooltip(Minecraft.getInstance().font, tooltip, mouseX, mouseY));
        }
    }

    public void resetValues() {
        isHoveredOver = false;
        color = Color.WHITE;
    }

    protected final void addUniqueTooltip(IGizmoHolder holder, ArrayList<Component> tooltip) {
        var usedId = holder.getGizmoTooltipKey();
        if (!usedId.isEmpty()) {
            var title = holder instanceof HeadlineTextPage ? BookPage.headlineKey(usedId) : title(usedId);
            var subtext = subtext(usedId);
            tooltip.add(Component.translatable(title).withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable(subtext).withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
        }
    }

    public DisplayedGizmo setHoveredOver() {
        isHoveredOver = true;
        return this;
    }

    public DisplayedGizmo setColor(Color color) {
        this.color = color;
        return this;
    }

    public abstract void renderDecals(AbstractMalumCodexScreen screen, IGizmoHolder holder, GuiGraphics guiGraphics, int x, int y, int mouseX, int mouseY);

    public void gatherTooltip(IGizmoHolder holder, List<Component> tooltip) {

    }
}
