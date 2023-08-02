package com.sammy.malum.client.renderer.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.*;
import net.minecraft.world.item.*;
import org.joml.*;
import com.sammy.malum.common.block.storage.SpiritJarBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.level.Level;
import org.joml.Math;

import static net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY;


public class SpiritJarRenderer implements BlockEntityRenderer<SpiritJarBlockEntity> {
    public SpiritJarRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(SpiritJarBlockEntity blockEntityIn, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        Level level = Minecraft.getInstance().level;
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        if (blockEntityIn.type != null) {
            poseStack.pushPose();
            double y = 0.5f + Math.sin(((level.getGameTime() % 360) + partialTicks) / 20f) * 0.2f;
            poseStack.translate(0.5f, y, 0.5f);
            poseStack.mulPose(Axis.YP.rotationDegrees(((level.getGameTime() % 360) + partialTicks) * 3));
            poseStack.scale(0.6f, 0.6f, 0.6f);
            itemRenderer.renderStatic(blockEntityIn.type.getSpiritShardItem().getDefaultInstance(), ItemDisplayContext.FIXED, combinedLightIn, NO_OVERLAY, poseStack, bufferIn, level, 0);
            poseStack.popPose();
        }
    }
}