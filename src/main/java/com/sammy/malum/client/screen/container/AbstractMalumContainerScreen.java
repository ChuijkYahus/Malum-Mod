package com.sammy.malum.client.screen.container;

import com.sammy.malum.MalumMod;
import com.sammy.malum.client.screen.codex.display.CodexTextRenderer;
import com.sammy.malum.common.block.curiosities.sorcery.wand_tinkerer.WandTinkererContainer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import team.lodestar.lodestone.modules.toolkit.blockentity.LodestoneBlockEntity;

import javax.annotation.Nonnull;

public abstract class AbstractMalumContainerScreen<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> {

    public AbstractMalumContainerScreen(T menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        leftPos = 0;
        topPos = 0;
    }

    @Override
    public void render(@Nonnull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        CodexTextRenderer.create().setScale(1.5f).setCentered(true).renderText(guiGraphics, title, titleLabelX, titleLabelY);
        guiGraphics.drawString(font, playerInventoryTitle, inventoryLabelX, inventoryLabelY, 4210752, false);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        guiGraphics.blit(getBackgroundTexture(), x, y, 0, 0, imageWidth, imageHeight);
    }

    public abstract ResourceLocation getBackgroundTexture();
}