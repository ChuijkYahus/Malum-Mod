package com.sammy.malum.client.screen.container.tinkerer;

import com.sammy.malum.MalumMod;
import com.sammy.malum.client.screen.container.AbstractMalumContainerScreen;
import com.sammy.malum.common.block.curiosities.sorcery.wand_tinkerer.WandTinkererContainer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class WandTinkererScreen extends AbstractMalumContainerScreen<WandTinkererContainer> {

    private static final ResourceLocation TEXTURE = MalumMod.malumPath("textures/gui/container/wand_tinkerer.png");

    public WandTinkererScreen(WandTinkererContainer menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        imageWidth = 243;
        imageHeight = 233;
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