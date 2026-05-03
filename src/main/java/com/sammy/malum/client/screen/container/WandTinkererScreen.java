package com.sammy.malum.client.screen.container;

import com.sammy.malum.MalumMod;
import com.sammy.malum.client.screen.codex.display.CodexTextRenderer;
import com.sammy.malum.common.block.curiosities.artifice.crystallarium.ConjunctureCrystallariumContainer;
import com.sammy.malum.common.block.curiosities.sorcery.wand_tinkerer.WandTinkererContainer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import javax.annotation.Nonnull;

public class WandTinkererScreen extends AbstractMalumContainerScreen<WandTinkererContainer> {

    private static final ResourceLocation TEXTURE = MalumMod.malumPath("textures/gui/container/wand_tinkerer.png");

    public WandTinkererScreen(WandTinkererContainer menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        leftPos = 0;
        topPos = 0;
        imageWidth = 176;
        imageHeight = 222;
        titleLabelX = 88;
        titleLabelY = -20;
        inventoryLabelX = 6;
        inventoryLabelY = imageHeight-12;
    }

    @Override
    public ResourceLocation getBackgroundTexture() {
        return TEXTURE;
    }


}