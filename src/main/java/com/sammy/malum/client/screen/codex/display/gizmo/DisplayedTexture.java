package com.sammy.malum.client.screen.codex.display.gizmo;

import com.sammy.malum.MalumMod;
import com.sammy.malum.client.screen.codex.display.CodexIconRenderer;
import com.sammy.malum.client.screen.codex.display.IGizmoHolder;
import com.sammy.malum.client.screen.codex.screens.AbstractMalumCodexScreen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

public class DisplayedTexture extends DisplayedGizmo {

    protected final CodexIconRenderer renderer;

    public DisplayedTexture(CodexIconRenderer renderer) {
        this.renderer = renderer;
    }

    public static DisplayedTexture texture(CodexIconRenderer renderer) {
        return new DisplayedTexture(renderer);
    }

    @Override
    public void renderDecals(AbstractMalumCodexScreen screen, IGizmoHolder holder, GuiGraphics guiGraphics, int x, int y, int mouseX, int mouseY) {
        renderer.renderIcon(guiGraphics.pose(), x, y);
    }

    @Override
    public ResourceLocation getPageBackground() {
        return MalumMod.malumPath("textures/gui/book/pages/headline_icon_page.png");
    }
}
