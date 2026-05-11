package com.sammy.malum.client.screen.container.coffer;

import com.google.common.collect.HashMultimap;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.sammy.malum.MalumMod;
import com.sammy.malum.client.screen.codex.display.texture.DynamicTextureRenderer;
import com.sammy.malum.client.screen.container.AbstractMalumContainerScreen;
import com.sammy.malum.client.screen.container.tinkerer.ItemDepositWidget;
import com.sammy.malum.client.screen.container.tinkerer.PartButtonWidget;
import com.sammy.malum.common.block.curiosities.sorcery.magehand_coffer.MagehandCofferContainer;
import com.sammy.malum.common.block.curiosities.sorcery.wand_tinkerer.WandTinkererContainer;
import com.sammy.malum.common.data.component.WandPartsComponent;
import com.sammy.malum.common.data.custom.wand_parts.WandMaterialType;
import com.sammy.malum.common.data.custom.wand_parts.WandPartType;
import com.sammy.malum.common.payloads.wand_tinkerer.WandTinkererInteractionItemPayload;
import com.sammy.malum.common.payloads.wand_tinkerer.WandTinkererSelectGroupPayload;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.network.PacketDistributor;
import team.lodestar.lodestone.helpers.DataHelper;
import team.lodestar.lodestone.modules.core.easing.Easing;
import team.lodestar.lodestone.systems.rendering.VFXBuilders;

public class MagehandCofferScreen extends AbstractMalumContainerScreen<MagehandCofferContainer> {

    private static final ResourceLocation TEXTURE = MalumMod.malumPath("textures/gui/container/magehand_coffer.png");

    public MagehandCofferScreen(MagehandCofferContainer menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        imageWidth = 200;
        imageHeight = 253;
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