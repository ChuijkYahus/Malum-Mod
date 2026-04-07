package com.sammy.malum.client.screen.codex.pages.text;

import com.sammy.malum.client.screen.codex.display.DisplayedGizmo;
import com.sammy.malum.client.screen.codex.helper.*;
import com.sammy.malum.client.screen.codex.screens.*;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

import static com.sammy.malum.client.screen.codex.helper.CodexTextHelper.renderHeadline;

public class HeadlineTextGizmoPage extends HeadlineTextPage {

    private final DisplayedGizmo displayedGizmo;

    public HeadlineTextGizmoPage(String headline, String text, DisplayedGizmo displayedGizmo) {
        super(headline, text);
        this.displayedGizmo = displayedGizmo;
    }

    public HeadlineTextGizmoPage(String text, DisplayedGizmo displayedGizmo) {
        this(text, text + ".1", displayedGizmo);
    }

    @Override
    public ResourceLocation getBackground() {
        return displayedGizmo.getPageBackground();
    }

    @Override
    public void render(CodexEntryScreen screen, GuiGraphics guiGraphics, int left, int top, int mouseX, int mouseY, float partialTicks, boolean isRepeat) {
        renderHeadline(guiGraphics, headline, left, top);
        CodexTextHelper.renderWrappingText(guiGraphics, text, left + 6, top + 87, 140);
        displayedGizmo.render(screen, guiGraphics, left + 63, top + 45, mouseX, mouseY);
    }
}