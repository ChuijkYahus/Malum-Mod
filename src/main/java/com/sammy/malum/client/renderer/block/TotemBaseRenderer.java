package com.sammy.malum.client.renderer.block;

import com.mojang.blaze3d.vertex.*;
import com.sammy.malum.*;
import com.sammy.malum.client.*;
import com.sammy.malum.common.block.curiosities.totem.*;
import com.sammy.malum.core.systems.item.HeldItemTracker;
import com.sammy.malum.registry.client.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.blockentity.*;
import net.minecraft.core.*;
import net.minecraft.util.*;
import net.minecraft.world.phys.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.registry.client.*;
import team.lodestar.lodestone.systems.easing.*;
import team.lodestar.lodestone.systems.rendering.cube.CubeVertexData;
import team.lodestar.lodestone.systems.rendering.rendeertype.*;


public class TotemBaseRenderer implements BlockEntityRenderer<TotemBaseBlockEntity> {

    public static final HeldItemTracker STAFF_TRACKER = new HeldItemTracker(p -> p.is(MalumTags.ItemTags.IS_TOTEMIC_TOOL));

    public TotemBaseRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public AABB getRenderBoundingBox(TotemBaseBlockEntity blockEntity) {
        return BlockEntityRenderer.super.getRenderBoundingBox(blockEntity);
    }

    @Override
    public void render(TotemBaseBlockEntity blockEntityIn, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        if (STAFF_TRACKER.isVisible()) {
            var riteType = blockEntityIn.cachedRadiusRite;
            if (riteType == null) {
                return;
            }
            float totemTimer = Mth.clamp((blockEntityIn.radiusVisibility + (blockEntityIn.isActiveOrAssembling() ? 1 : -1) * partialTicks), 0, 40);
            float scalar = Easing.SINE_IN_OUT.ease(STAFF_TRACKER.getDelta(partialTicks), 0, 1) * Easing.SINE_IN_OUT.ease(totemTimer / 40f, 0, 1, 1);
            var spirit = riteType.getIdentifyingSpirit();
            var riteEffect = riteType.getRiteEffect(blockEntityIn.isSoulwood);
            var riteEffectCenter = riteEffect.getRiteEffectCenter(blockEntityIn);
            var offset = riteEffectCenter.subtract(blockEntityIn.getBlockPos());
            int width = riteEffect.getRiteEffectHorizontalRadius();
            if (width > 1) {
                width = width * 2 + 1;
            }
            int height = riteEffect.getRiteEffectVerticalRadius();
            if (height > 1) {
                height = height * 2 + 1;
            }
            float distortion = 6f + (width + height) / 2f;

            var border = LodestoneRenderTypes.ADDITIVE_DISTORTED_NINE_SLICE_TEXTURE.apply(MalumRenderTypeTokens.AREA_COVERAGE_BORDER)
                    .withUniformHandler(new ShaderUniformHandler()
                            .modifyUniform("Speed", 1500f)
                            .modifyUniform("Distortion", distortion)
                    );
            var squiggles = LodestoneRenderTypes.ADDITIVE_DISTORTED_NINE_SLICE_TEXTURE.apply(MalumRenderTypeTokens.AREA_COVERAGE_SQUIGGLES)
                    .withUniformHandler(new ShaderUniformHandler()
                            .modifyUniform("Speed", 2500f)
                            .modifyUniform("Distortion", distortion * 2f)
                    );
            var checkerboard = LodestoneRenderTypes.ADDITIVE_DISTORTED_NINE_SLICE_TEXTURE.apply(MalumRenderTypeTokens.AREA_COVERAGE_CHECKERBOARD)
                    .withUniformHandler(new ShaderUniformHandler()
                            .modifyUniform("Speed", 500f)
                            .modifyUniform("Distortion", distortion / 2f)
                    );

            poseStack.pushPose();
            poseStack.translate(offset.getX() + 0.5f, offset.getY() + 0.5f, offset.getZ() + 0.5f);
            for (int i = 0; i < 2; i++) {
                float cubeWidth = i == 0 ? width : -width;
                float cubeHeight = i == 0 ? height : -height;
                var primaryColor = i == 0 ? spirit.getPrimaryColor() : spirit.getSecondaryColor();
                var secondaryColor = i == 0 ? spirit.getSecondaryColor() : spirit.getPrimaryColor();
                CubeVertexData borderArea = CubeVertexData.makeCubePositions(cubeWidth, cubeHeight)
                        .applyWobble(0, 0.5f, 0.01f)
                        .scale(1.1f);
                CubeVertexData squiggleArea = CubeVertexData.makeCubePositions(cubeWidth, cubeHeight)
                        .applyWobble(0.2f, 0.7f, 0.02f)
                        .scale(1.09f);
                CubeVertexData checkerboardArea = CubeVertexData.makeCubePositions(cubeWidth, cubeHeight)
                        .applyWobble(0.5f, 0, 0.03f)
                        .scale(1.08f);

                var builder = SpiritBasedWorldVFXBuilder.create(spirit);
                builder
                        .setRenderType(border)
                        .setColor(primaryColor, 0.95f * scalar)
                        .drawCube(poseStack, borderArea);
                builder
                        .setRenderType(squiggles)
                        .setColor(secondaryColor, 0.8f * scalar)
                        .drawCube(poseStack, squiggleArea);
                builder
                        .setRenderType(checkerboard)
                        .setColor(primaryColor, 0.6f * scalar)
                        .drawCube(poseStack, checkerboardArea);
            }
            poseStack.popPose();
        }
    }
}