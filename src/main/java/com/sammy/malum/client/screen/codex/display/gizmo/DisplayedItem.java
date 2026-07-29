package com.sammy.malum.client.screen.codex.display.gizmo;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.sammy.malum.MalumMod;
import com.sammy.malum.client.screen.codex.display.IGizmoHolder;
import com.sammy.malum.client.screen.codex.display.texture.DynamicTextureRenderer;
import com.sammy.malum.client.screen.codex.screens.AbstractMalumCodexScreen;
import com.sammy.malum.registry.common.magic.MalumSpiritTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import team.lodestar.lodestone.systems.rendering.VFXBuilders;

import java.util.List;

public class DisplayedItem extends DisplayedGizmo {

    protected final ItemStack itemDisplay;

    public DisplayedItem(ItemStack itemDisplay) {
        this.itemDisplay = itemDisplay;
    }

    public static DisplayedItem item(ItemLike item) {
        return new DisplayedItem(item.asItem().getDefaultInstance());
    }

    public static DisplayedItem item(ItemLike item, int count) {
        return new DisplayedItem(new ItemStack(item, count));
    }

    public static DisplayedItem item(ItemStack stack) {
        return new DisplayedItem(stack);
    }

    @Override
    public void renderDecals(AbstractMalumCodexScreen screen, IGizmoHolder holder, GuiGraphics guiGraphics, int x, int y, int mouseX, int mouseY) {
        if (isHoveredOver) {
            y -= 1;
        }
        var dynamicTexture = DynamicTextureRenderer.create(itemDisplay.getItem())
                .setTextureSize(16, 16).requestFlatItemTexture(itemDisplay.getItem().getDefaultInstance());
        if (dynamicTexture == null) {
            return;
        }
        dynamicTexture.bind(0);
        PoseStack stack = guiGraphics.pose();
        var builder = VFXBuilders.createScreen()
                .setShader(GameRenderer::getPositionTexColorShader)
                .setUV(0, 1, 1, 0)
                .setPositionWithWidth(x, y, 16, 16)
                .setZLevel(200)
                .setColor(color)
                .blit(stack);
        if (isHoveredOver) {
            RenderSystem.enableBlend();
            RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
            float alphaScale = color.getRed() / 255f;
            builder
                    .setColor(MalumSpiritTypes.ARCANE_COLORS().primaryColor())
                    .multiplyColor(color.getRed(), color.getBlue(), color.getGreen())
                    .setAlpha(0.3f * alphaScale)
                    .blit(stack);
            RenderSystem.defaultBlendFunc();
        }

        guiGraphics.renderItemDecorations(Minecraft.getInstance().font, itemDisplay, x, y, null);
    }

    @Override
    public void gatherTooltip(IGizmoHolder holder, List<Component> tooltip) {
        if (tooltip.isEmpty()) {
            tooltip.addAll(Screen.getTooltipFromItem(Minecraft.getInstance(), itemDisplay));
        }
    }

    @Override
    public ResourceLocation getPageBackground() {
        return MalumMod.malumPath("textures/gui/book/pages/headline_item_page.png");
    }
}