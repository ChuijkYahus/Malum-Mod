package com.sammy.malum.client.screen.codex.display.gizmo;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.sammy.malum.MalumMod;
import com.sammy.malum.client.screen.codex.display.IGizmoHolder;
import com.sammy.malum.client.screen.codex.display.texture.DynamicTextureRenderer;
import com.sammy.malum.client.screen.codex.screens.AbstractMalumCodexScreen;
import com.sammy.malum.core.systems.spirit.SpiritLike;
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

public class DisplayedSpirit extends DisplayedGizmo {

    protected final SpiritLike spirit;

    public DisplayedSpirit(SpiritLike spirit) {
        this.spirit = spirit;
    }

    public static DisplayedSpirit spirit(SpiritLike spirit) {
        return new DisplayedSpirit(spirit);
    }

    @Override
    public void renderDecals(AbstractMalumCodexScreen screen, IGizmoHolder holder, GuiGraphics guiGraphics, int x, int y, int mouseX, int mouseY) {
        if (isHoveredOver) {
            y -= 1;
        }
        var shard = spirit.getSpiritShard();
        var dynamicTexture = DynamicTextureRenderer.create(shard)
                .setTextureSize(16, 16)
                .requestFlatItemTexture(shard);
        if (dynamicTexture == null) {
            return;
        }
        dynamicTexture.bind(0);

        var stack = guiGraphics.pose();
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
    }

    @Override
    public void gatherTooltip(IGizmoHolder holder, List<Component> tooltip) {
        var textData = spirit.getTextData();
        textData.addToCodexTooltip(tooltip);
    }

    @Override
    public ResourceLocation getPageBackground() {
        return MalumMod.malumPath("textures/gui/book/pages/headline_item_page.png");
    }
}