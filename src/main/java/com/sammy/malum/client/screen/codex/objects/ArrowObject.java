package com.sammy.malum.client.screen.codex.objects;

import com.mojang.blaze3d.vertex.*;
import com.sammy.malum.*;
import com.sammy.malum.client.screen.codex.screens.*;
import net.minecraft.client.gui.*;
import net.minecraft.resources.*;

import static com.sammy.malum.client.screen.codex.helper.CodexRenderHelper.renderTexture;

public class ArrowObject extends BookObject<CodexEntryScreen> {

    public static final ResourceLocation ARROWS = MalumMod.malumPath("textures/gui/book/buttons/arrows.png");
    public static final ResourceLocation ARROWS_LIT_UP = MalumMod.malumPath("textures/gui/book/buttons/arrows_hover.png");

    public final boolean flipped;

    public ArrowObject(int posX, int posY, boolean flipped) {
        super(posX, posY, 39, 22);
        this.flipped = flipped;
    }

    @Override
    public void render(CodexEntryScreen screen, GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        final int arrowX = getOffsetXPosition();
        final int arrowY = getOffsetYPosition();
        var texture = isHoveredOver ? ARROWS_LIT_UP : ARROWS;
        final PoseStack poseStack = guiGraphics.pose();
        renderTexture(texture, poseStack, arrowX, arrowY, 0, flipped ? 0 : 22, width, height, 39, 44);
    }

    @Override
    public boolean click(CodexEntryScreen screen, double mouseX, double mouseY) {
        if (flipped) {
            screen.nextPage();
            return true;
        }
        screen.previousPage(true);
        return true;
    }

    @Override
    public boolean isValid(CodexEntryScreen screen) {
        return !flipped || (screen.hasNextPage());
    }
}
