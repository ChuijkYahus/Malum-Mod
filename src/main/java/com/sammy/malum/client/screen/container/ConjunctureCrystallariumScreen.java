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

public class ConjunctureCrystallariumScreen extends AbstractMalumContainerScreen<ConjunctureCrystallariumContainer> {

    private static final ResourceLocation TEXTURE = MalumMod.malumPath("textures/gui/container/conjuncture_crystallarium.png");

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
    public ResourceLocation getBackgroundTexture() {
        return TEXTURE;
    }
}