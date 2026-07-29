package com.sammy.malum.client.screen.codex.display.gizmo;

import com.sammy.malum.client.screen.codex.display.IGizmoHolder;
import com.sammy.malum.client.screen.codex.pages.*;
import com.sammy.malum.client.screen.codex.pages.text.*;
import com.sammy.malum.client.screen.codex.screens.AbstractMalumCodexScreen;
import com.sammy.malum.client.screen.codex.screens.progression.AbstractProgressionCodexScreen;
import com.sammy.malum.core.systems.geas.GeasEffectType;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

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

    protected String id = "";
    protected boolean hasTooltip = true;
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
        if (isHoveredCache && hasTooltip) {
            var tooltip = new ArrayList<Component>();
            addDefaultTooltip(holder, tooltip);
            gatherTooltip(holder, tooltip);
            screen.renderLater(() -> guiGraphics.renderComponentTooltip(Minecraft.getInstance().font, tooltip, mouseX, mouseY));
        }
    }

    public void resetValues() {
        isHoveredOver = false;
        color = Color.WHITE;
    }

    protected final void addDefaultTooltip(IGizmoHolder holder, ArrayList<Component> tooltip) {
        var usedId = holder.getGizmoId();
        if (usedId.isEmpty()) {
            usedId = id;
        }
        if (!usedId.isEmpty()) {
            var title = holder instanceof HeadlineTextPage ? BookPage.headlineKey(usedId) : title(usedId);
            tooltip.add(Component.translatable(title).withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable(subtext(usedId)).withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
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

    public DisplayedGizmo setId(String id) {
        this.id = id;
        return this;
    }

    public DisplayedGizmo noTooltip() {
        this.hasTooltip = false;
        return this;
    }

    public abstract void renderDecals(AbstractMalumCodexScreen screen, IGizmoHolder holder, GuiGraphics guiGraphics, int x, int y, int mouseX, int mouseY);

    public void gatherTooltip(IGizmoHolder holder, List<Component> tooltip) {

    }

    public abstract ResourceLocation getPageBackground();

    public static DisplayedItem geas(Holder<GeasEffectType> geas) {
        return new DisplayedItem(geas.value().createDefaultStack());
    }
}
