package com.sammy.malum.client.screen.codex.display;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.util.*;
import com.sammy.malum.MalumMod;
import com.sammy.malum.client.screen.codex.display.texture.DynamicTextureRenderer;
import com.sammy.malum.client.screen.codex.objects.*;
import com.sammy.malum.client.screen.codex.objects.progression.*;
import com.sammy.malum.client.screen.codex.pages.*;
import com.sammy.malum.client.screen.codex.pages.text.*;
import com.sammy.malum.client.screen.codex.screens.AbstractMalumCodexScreen;
import com.sammy.malum.client.screen.codex.screens.progression.AbstractProgressionCodexScreen;
import com.sammy.malum.core.systems.geas.GeasEffectType;
import com.sammy.malum.registry.common.magic.MalumSpiritTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import team.lodestar.lodestone.systems.particle.render_types.LodestoneWorldParticleRenderType;
import team.lodestar.lodestone.systems.rendering.VFXBuilders;

import javax.annotation.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public abstract class DisplayedGizmo {

    public static String title(String key) {
        return "malum.gui.book.gizmo." + key + ".title";
    }

    public static String subtext(String key) {
        return "malum.gui.book.gizmo." + key + ".subtext";
    }

    protected String id = "";
    protected boolean hasTooltip = true;
    protected boolean isHoveredOver;
    protected Color color = Color.WHITE;

    public final void render(AbstractMalumCodexScreen screen, IGizmoHolder holder, GuiGraphics guiGraphics, int x, int y, int mouseX, int mouseY) {
        if (!isHoveredOver) {
            isHoveredOver = screen.isHovering(mouseX, mouseY, x, y, 16, 16);
        }
        boolean isHoveredCache = isHoveredOver;
        renderDecals(screen, holder, guiGraphics, x, y, mouseX, mouseY);
        resetValues();
        if (screen instanceof AbstractProgressionCodexScreen) {
            return;
        }
        if (isHoveredCache && hasTooltip) {
            var tooltip = new ArrayList<Component>();
            addDefaultTooltip(holder, tooltip);
            gatherTooltip(holder, tooltip);
            screen.renderLater(() -> guiGraphics.renderComponentTooltip(Minecraft.getInstance().font, tooltip, mouseX, mouseY));
        }
    }

    public void resetValues() {
        isHoveredOver = false;
        color = Color.WHITE;
    }

    protected final void addDefaultTooltip(IGizmoHolder holder, ArrayList<Component> tooltip) {
        var usedId = holder.getGizmoId();
        if (usedId.isEmpty()) {
            usedId = id;
        }
        if (!usedId.isEmpty()) {
            var title = holder instanceof HeadlineTextPage ? BookPage.headlineKey(usedId) : title(usedId);
            tooltip.add(Component.translatable(title).withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable(subtext(usedId)).withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
        }
    }

    public DisplayedGizmo setHoveredOver() {
        isHoveredOver = true;
        return this;
    }

    public DisplayedGizmo setColor(Color color) {
        this.color = color;
        return this;
    }

    public DisplayedGizmo setId(String id) {
        this.id = id;
        return this;
    }

    public DisplayedGizmo noTooltip() {
        this.hasTooltip = false;
        return this;
    }

    public abstract void renderDecals(AbstractMalumCodexScreen screen, IGizmoHolder holder, GuiGraphics guiGraphics, int x, int y, int mouseX, int mouseY);

    public void gatherTooltip(IGizmoHolder holder, List<Component> tooltip) {

    }

    public abstract ResourceLocation getPageBackground();



    public static DisplayedItem geas(Holder<GeasEffectType> geas) {
        return new DisplayedItem(geas.value().createDefaultStack());
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

    public static DisplayedTexture texture(CodexIconRenderer renderer) {
        return new DisplayedTexture(renderer);
    }

    public static class DisplayedItem extends DisplayedGizmo {

        protected final ItemStack itemDisplay;

        public DisplayedItem(ItemStack itemDisplay) {
            this.itemDisplay = itemDisplay;
        }

        @Override
        public void renderDecals(AbstractMalumCodexScreen screen, IGizmoHolder holder, GuiGraphics guiGraphics, int x, int y, int mouseX, int mouseY) {
            if (isHoveredOver) {
                y -= 1;
            }
            var dynamicTexture = DynamicTextureRenderer.create(itemDisplay.getItem())
                    .setTextureSize(16, 16).requestFlatItemTexture(itemDisplay.getItem());
            if (dynamicTexture == null) {
                return;
            }
            RenderSystem.setShaderTexture(0, dynamicTexture.getRenderTarget().getColorTextureId());
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
                builder
                        .setColor(MalumSpiritTypes.ARCANE_COLORS().primaryColor())
                        .multiplyColor(color.getRed(), color.getBlue(), color.getGreen())
                        .setAlpha(0.3f * color.getRed()/255f)
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

    public static class DisplayedTexture extends DisplayedGizmo {

        protected final CodexIconRenderer renderer;

        public DisplayedTexture(CodexIconRenderer renderer) {
            this.renderer = renderer;
        }

        @Override
        public void renderDecals(AbstractMalumCodexScreen screen, IGizmoHolder holder, GuiGraphics guiGraphics, int x, int y, int mouseX, int mouseY) {
            renderer.renderIcon(guiGraphics.pose(), x, y);
        }

        @Override
        public ResourceLocation getPageBackground() {
            return MalumMod.malumPath("textures/gui/book/pages/headline_icon_page.png");
        }
    }
}
