package com.sammy.malum.client.screen.container.tinkerer;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.sammy.malum.MalumMod;
import com.sammy.malum.client.screen.codex.display.CodexOutlineRenderer;
import com.sammy.malum.client.screen.codex.display.texture.DynamicTextureRenderer;
import com.sammy.malum.client.screen.codex.screens.CodexEntryScreen;
import com.sammy.malum.common.block.curiosities.sorcery.wand_tinkerer.WandTinkererBlockEntity;
import com.sammy.malum.common.data.custom.wand_parts.WandPartType;
import com.sammy.malum.registry.common.magic.MalumSpiritTypes;
import com.sammy.malum.registry.common.sound.MalumSoundEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import team.lodestar.lodestone.helpers.DataHelper;
import team.lodestar.lodestone.registry.client.LodestoneRenderTypes;
import team.lodestar.lodestone.registry.client.LodestoneShaders;
import team.lodestar.lodestone.systems.rendering.VFXBuilders;
import team.lodestar.lodestone.systems.rendering.rendeertype.RenderTypeToken;

import java.awt.*;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class PartButtonWidget extends AbstractWidget {

    protected final ResourceLocation LOCKED = MalumMod.malumPath("textures/gui/container/wand_tinkerer_slot_cover.png");
    protected final ResourceLocation FILLED = MalumMod.malumPath("textures/gui/container/wand_tinkerer_slot_filled.png");
    protected final WandTinkererScreen parent;
    protected final ResourceLocation texture;
    protected final WandPartType.WandPartGroup group;

    protected boolean wasHoveredOver;
    protected float oldSymbolVisibility;
    protected float symbolVisibility;

    public PartButtonWidget(WandTinkererScreen parent, int x, int y, WandPartType.WandPartGroup group) {
        super(x, y, 10, 17, Component.empty());
        this.parent = parent;
        this.texture = MalumMod.malumPath("textures/gui/container/wand_tinkerer_" + group.name + ".png");
        this.group = group;
    }

    @Override
    public void onClick(double mouseX, double mouseY, int button) {
        if (parent.isLocked(group)) {
            return;
        }
        parent.select(group);
        super.onClick(mouseX, mouseY, button);
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        PoseStack stack = guiGraphics.pose();
        if (parent.isLocked(group)) {
            VFXBuilders.createScreen()
                    .setPositionWithWidth(getX(), getY(), width, height)
                    .setShader(GameRenderer::getPositionTexShader)
                    .setTexture(LOCKED)
                    .blit(stack);
            return;
        }

        var blockEntity = parent.getMenu().blockEntity;

        var nonEmptyStacks = blockEntity.getInventory(group).getNonEmptyStacks();

        for (int i = 0; i < nonEmptyStacks.size(); i++) {
            renderGoober(stack, partialTick, FILLED, getX()+1, getY()+23+i*10, 8, 8);
        }

        renderGoober(stack, partialTick, texture, getX(), getY(), width, height);

        if (isHovered) {
            guiGraphics.fill(getX(), getY(), getX() + width, getY() + height, 1073741825);
        }
    }

    public void renderGoober(PoseStack stack, float partialTick, ResourceLocation texture, int x, int y, int width, int height) {
        float strength = Mth.lerp(partialTick, oldSymbolVisibility, symbolVisibility);
        CodexOutlineRenderer.create(texture, x - 16 + width/2, y - 16 + height/2, width, height, 32, 32)
                .setEffectStrength(strength)
                .setDistortion(60f * strength)
                .setOffset(group.ordinal() * 600)
                .setEffectAlpha(strength)

                .setOutlineWidth(1)
                .setShadowWidth(2)
                .renderOutline(stack);

        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);

        var distorted = LodestoneShaders.SCREEN_DISTORTED_TEXTURE.getShaderInstance();
        distorted.safeGetUniform("YFrequency").set(24f);
        distorted.safeGetUniform("XFrequency").set(16f);
        distorted.safeGetUniform("Speed").set(1000f);
        distorted.safeGetUniform("Intensity").set(80f);
        distorted.safeGetUniform("Width").set((float)width);
        distorted.safeGetUniform("Height").set((float)height);

        VFXBuilders.createScreen()
                .setPositionWithWidth(x, y, width, height)
                .setShader(distorted)
                .setColor(MalumSpiritTypes.ARCANE_COLORS().primaryColor())
                .setTexture(texture).setAlpha(0.5f * strength)
                .blit(stack);
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
    }


    public void tick() {
        oldSymbolVisibility = symbolVisibility;
        float target = choose(0f, 0.6f, 0.8f, 1.0f);
        symbolVisibility = DataHelper.approach(symbolVisibility, target, 0.5f);
        if (!wasHoveredOver && isHovered) {
            if (!parent.isSelected(group) && !parent.isLocked(group)) {
                Minecraft.getInstance().player.playNotifySound(MalumSoundEvents.ARCANA_GIZMO_HOVER.value(), SoundSource.PLAYERS, 1f, 1f);
            }
        }
        wasHoveredOver = isHovered;
    }


    public <T> T choose(T onDefault, T onHover, T onSelected, T onGlowing) {
        if (parent.isGlowing(group)) {
            return onGlowing;
        }
        if (parent.isSelected(group)) {
            return onSelected;
        }
        if (isHovered) {
            return onHover;
        }
        return onDefault;
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {

    }

    @Override
    public void playDownSound(SoundManager handler) {

    }
}
