package com.sammy.malum.client.screen.container;

import com.sammy.malum.*;
import com.sammy.malum.client.screen.codex.display.*;
import com.sammy.malum.common.block.curiosities.artifice.crystallarium.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.screens.inventory.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.*;

import javax.annotation.*;

//TODO abstract this as well
public class ConjunctureCrystallariumScreen extends AbstractMalumContainerScreen<ConjunctureCrystallariumContainer> {

    private static final ResourceLocation TEXTURE = MalumMod.malumPath("textures/gui/container/conjuncture_crystallarium.png");
    private static final ResourceLocation ARROW_LEFT = MalumMod.malumPath("textures/gui/container/conjuncture_arrow_left.png");
    private static final ResourceLocation ARROW_RIGHT = MalumMod.malumPath("textures/gui/container/conjuncture_arrow_right.png");
    private static final ResourceLocation BURN = MalumMod.malumPath("textures/gui/container/burn_overlay.png");

    public ConjunctureCrystallariumScreen(ConjunctureCrystallariumContainer menu, Inventory pPlayerInventory, Component pTitle) {
        super(menu, pPlayerInventory, pTitle);
        imageWidth = 198;
        imageHeight = 233;
        titleLabelX = 99;
        titleLabelY = -20;
        inventoryLabelX = 19;
        inventoryLabelY = imageHeight - 12;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        super.renderBg(guiGraphics, partialTick, mouseX, mouseY);
        int leftPos = this.leftPos;
        int topPos = this.topPos;
        if (menu.isLit()) {
            int textureSize = 14;
            int progress = Mth.ceil(this.menu.getLitProgress() * 13.0F) + 1;
            guiGraphics.blitSprite(BURN, textureSize, textureSize, 0, textureSize - progress, leftPos + 56, topPos + 36 + textureSize - progress, textureSize, progress);
        }

        int burnProgress = Mth.ceil(this.menu.getBurnProgress() * 27.0F);
        guiGraphics.blitSprite(ARROW_LEFT, 18, 27, 0, 0, leftPos + 79, topPos + 34, burnProgress, 16);
        guiGraphics.blitSprite(ARROW_RIGHT, 19, 27, 0, 0, leftPos + 79, topPos + 34, burnProgress, 16);
    }

    @Override
    public ResourceLocation getBackgroundTexture() {
        return TEXTURE;
    }
}