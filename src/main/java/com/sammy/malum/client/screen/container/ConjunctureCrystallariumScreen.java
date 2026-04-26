package com.sammy.malum.client.screen.container;

import com.sammy.malum.*;
import com.sammy.malum.client.screen.codex.display.*;
import com.sammy.malum.common.block.curiosities.artifice.crystallarium.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.screens.inventory.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.*;
import net.minecraft.world.entity.player.*;

import javax.annotation.*;

public class ConjunctureCrystallariumScreen extends AbstractContainerScreen<ConjunctureCrystallariumContainer> {

    private static final ResourceLocation TEXTURE = MalumMod.malumPath("textures/gui/container/conjuncture_crystallarium.png");

    public ConjunctureCrystallariumScreen(ConjunctureCrystallariumContainer menu, Inventory pPlayerInventory, Component pTitle) {
        super(menu, pPlayerInventory, pTitle);
        leftPos = 0;
        topPos = 0;
        imageWidth = 198;
        imageHeight = 233;
        titleLabelX = 103;
        titleLabelY = -20;
        inventoryLabelX = 19;
        inventoryLabelY = imageHeight-12;
    }

    @Override
    public void render(@Nonnull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        var renderer = CodexTextRenderer.create().setScale(1.25f);
        renderer.renderWrappingText(guiGraphics, Component.empty().append(title.copy()).append(title.copy()).append(title.copy()), titleLabelX, titleLabelY, 1);
//        renderer.renderText(guiGraphics, title, titleLabelX, titleLabelY);

        guiGraphics.drawString(font, playerInventoryTitle, inventoryLabelX, inventoryLabelY, 4210752, false);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
    }
}