package com.sammy.malum.client.screen.codex.pages.text;

import com.sammy.malum.client.screen.codex.display.*;
import com.sammy.malum.client.screen.codex.screens.*;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

public class HeadlineTextGizmoPage extends HeadlineTextPage {

    private final DisplayedGizmo displayedGizmo;
    public static HeadlineTextPage headlineTextGizmoPage(String text, DisplayedGizmo displayedGizmo) {
        return new HeadlineTextGizmoPage(text, text +".1", displayedGizmo);
    }

    public static HeadlineTextPage headlineTextGizmoPage(String headline, String text, DisplayedGizmo displayedGizmo) {
        return new HeadlineTextGizmoPage(headline, text, displayedGizmo);
    }

    protected HeadlineTextGizmoPage(String headline, String text, DisplayedGizmo displayedGizmo) {
        super(headline, text);
        this.displayedGizmo = displayedGizmo;
    }

    @Override
    public ResourceLocation getBackground() {
        return displayedGizmo.getPageBackground();
    }

    @Override
    public void render(CodexEntryScreen screen, GuiGraphics guiGraphics, int left, int top, int mouseX, int mouseY, float partialTicks, boolean isRepeat) {
        CodexTextRenderer.create()
                .renderHeadline(guiGraphics, headline, left, top)
                .renderHeadlineGizmoPageContents(guiGraphics, text, left, top);
        displayedGizmo.render(screen, this, guiGraphics, left + 63, top + 45, mouseX, mouseY);
    }
}