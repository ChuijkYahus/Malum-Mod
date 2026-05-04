package com.sammy.malum.client.screen.container.tinkerer;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.sammy.malum.MalumMod;
import com.sammy.malum.client.screen.codex.display.texture.DynamicTextureRenderer;
import com.sammy.malum.client.screen.container.AbstractMalumContainerScreen;
import com.sammy.malum.common.block.curiosities.sorcery.wand_tinkerer.WandTinkererContainer;
import com.sammy.malum.common.data.custom.wand_parts.WandPartType;
import com.sammy.malum.common.payloads.wand_tinkerer.WandTinkererInteractionItemPayload;
import com.sammy.malum.common.payloads.wand_tinkerer.WandTinkererSelectGroupPayload;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.network.PacketDistributor;
import team.lodestar.lodestone.helpers.DataHelper;
import team.lodestar.lodestone.modules.core.easing.Easing;
import team.lodestar.lodestone.systems.rendering.VFXBuilders;

public class WandTinkererScreen extends AbstractMalumContainerScreen<WandTinkererContainer> {

    private static final ResourceLocation TEXTURE = MalumMod.malumPath("textures/gui/container/wand_tinkerer.png");

    private ItemDepositWidget itemDepositWidget;

    private float hoveredDelta;

    public WandTinkererScreen(WandTinkererContainer menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        imageWidth = 243;
        imageHeight = 233;
        titleLabelX = 88;
        titleLabelY = -20;
        inventoryLabelX = 6;
        inventoryLabelY = imageHeight-12;
    }

    public void sendInteraction() {
        PacketDistributor.sendToServer(new WandTinkererInteractionItemPayload(menu.blockEntity.getBlockPos(), menu.getCarried()));
    }

    public void select(WandPartType.WandPartGroup group) {
        PacketDistributor.sendToServer(new WandTinkererSelectGroupPayload(menu.blockEntity.getBlockPos(), group));
    }

    public boolean isGlowing(WandPartType.WandPartGroup group) {
        return menu.blockEntity.hasPart(group);
    }

    public boolean isSelected(WandPartType.WandPartGroup group) {
        return group.equals(menu.blockEntity.selectedGroup);
    }

    public boolean isLocked(WandPartType.WandPartGroup group) {
        return menu.blockEntity.isLocked(group);
    }

    @Override
    protected void init() {
        super.init();
        itemDepositWidget = addRenderableWidget(new ItemDepositWidget(leftPos + 56, topPos + 34, this::sendInteraction));

        var groups = WandPartType.WandPartGroup.values();
        for (int i = 0; i < groups.length; i++) {
            var group = groups[i];
            int x = leftPos + 180 + 11 * i;
            int y = topPos + 82;
            addRenderableWidget(new PartButtonWidget(this, x, y, group));
        }
    }



    @Override
    public void renderFloatingItem(GuiGraphics guiGraphics, ItemStack stack, int x, int y, String text) {
        if (hoveredDelta > 0f) {
            var dynamicTexture = DynamicTextureRenderer.create(stack.getItem())
                    .setTextureSize(16, 16).requestFlatItemTexture(stack);
            PoseStack poseStack = guiGraphics.pose();
            if (dynamicTexture != null) {
                RenderSystem.setShaderTexture(0, dynamicTexture.getRenderTarget().getColorTextureId());

                float dark = Easing.SINE_IN_OUT.lerp(hoveredDelta, 1f, 0.3f);

                VFXBuilders.createScreen()
                        .setShader(GameRenderer::getPositionTexColorShader)
                        .setUV(0, 1, 1, 0)
                        .setZLevel(234)
                        .setPositionWithWidth(x, y, 16, 16)
                        .setColor(dark, dark, dark)
                        .blit(poseStack);


                guiGraphics.pose().pushPose();
                guiGraphics.pose().translate(0.0F, 0.0F, 235.0F);
                var font = IClientItemExtensions.of(stack).getFont(stack, IClientItemExtensions.FontContext.ITEM_COUNT);
                RenderSystem.setShaderColor(1, 1, 1, dark);
                guiGraphics.renderItemDecorations(font == null ? this.font : font, stack, x, y, text);
                guiGraphics.pose().popPose();
                return;
            }

        }
        super.renderFloatingItem(guiGraphics, stack, x, y, text);
    }

    @Override
    protected void containerTick() {
        super.containerTick();

        if (itemDepositWidget.isHovered()) {
            hoveredDelta = DataHelper.approach(hoveredDelta, 1, 0.2f);
            return;
        }
        hoveredDelta = DataHelper.approach(hoveredDelta, 0, 0.4f);
        for (Renderable renderable : renderables) {
            if (renderable instanceof PartButtonWidget widget) {
                widget.tick();
            }
        }
    }

    @Override
    public ResourceLocation getBackgroundTexture() {
        return TEXTURE;
    }
}