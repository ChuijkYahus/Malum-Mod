package com.sammy.malum.core.handlers.client;

import com.mojang.blaze3d.vertex.*;
import com.sammy.malum.registry.client.*;
import net.minecraft.client.*;
import net.minecraft.client.model.geom.*;
import net.minecraft.world.entity.*;
import team.lodestar.lodestone.handlers.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.registry.client.*;
import team.lodestar.lodestone.systems.rendering.rendeertype.*;
import team.lodestar.lodestone.systems.rendering.vertexconsumer.*;

import java.awt.*;

public class SoullessStateRenderHandler {

    private static boolean renderingSoullessCreature = false;
    private static PoseStack poseStack;

    public static void renderSoullessModelPart(ModelPart modelPart, PoseStack.Pose pose, int packedLight, int packedOverlay) {
        if (renderingSoullessCreature) {
            var renderType = LodestoneRenderTypes.TRANSPARENT_TEXTURE.apply(MalumRenderTypeTokens.VOID_NOISE);
            var vertexConsumer = LodestoneRenderHandler.DEFERRED_RENDER.getTarget().getBuffer(renderType);


            for (int i = 0; i < 4; i++) {
                Color color = new Color(42 + i * 20, 32, 61);
                float size = 1f + ((i+1) * 0.01F);
                float rate = 0.25f + (i * 0.15F);
                if (i % 2 == 0) {
                    rate *= -1;
                }
                renderOutline(modelPart, vertexConsumer, pose, color, size, rate, packedLight, packedOverlay);
            }

            //            Soul Ward Test
//            Color bright = new Color(255, 238, 163, 200);
//            Color wa = new Color(251, 102, 221, 150);
//            Color wawa = new Color(89, 26, 99, 100);
//            var renderType = LodestoneRenderTypes.TRANSPARENT_TEXTURE.apply(MalumRenderTypeTokens.VOID_NOISE);
//            var vertexConsumer = LodestoneRenderHandler.DEFERRED_RENDER.getTarget().getBuffer(renderType);
//            renderOutline(modelPart, vertexConsumer, pose, bright, 1.03F, 0.5F, RenderHelper.FULL_BRIGHT, packedOverlay);
//            renderOutline(modelPart, vertexConsumer, pose, wa, 1.02F, 1F, RenderHelper.FULL_BRIGHT, packedOverlay);
//            renderOutline(modelPart, vertexConsumer, pose, wawa, 1.01F, 1.5F, RenderHelper.FULL_BRIGHT, packedOverlay);
//
//            renderType = LodestoneRenderTypes.ADDITIVE_TEXTURE.apply(MalumRenderTypeTokens.VOID_NOISE);
//            vertexConsumer = LodestoneRenderHandler.LATE_DEFERRED_RENDER.getTarget().getBuffer(renderType);
//            renderOutline(modelPart, vertexConsumer, pose, bright, 1.04F, 0.5F, RenderHelper.FULL_BRIGHT, packedOverlay);
//            renderOutline(modelPart, vertexConsumer, pose, wa, 1.03F, 1F, RenderHelper.FULL_BRIGHT, packedOverlay);
//            renderOutline(modelPart, vertexConsumer, pose, wawa, 1.02F, 1.5F, RenderHelper.FULL_BRIGHT, packedOverlay);
        }
    }

    public static void renderOutline(ModelPart modelPart, VertexConsumer vertexConsumer, PoseStack.Pose pose, Color color, float size, float rate, int packedLight, int packedOverlay) {
        float inverse = 1 / size;
        var minecraft = Minecraft.getInstance();
        long timeOffset = modelPart.hashCode() % 1000;
        long gameTime = minecraft.level.getGameTime() + timeOffset;
        float uInterval = 800 * rate;
        float vInterval = uInterval * 4;
        float uOffset = (gameTime % uInterval) / uInterval;
        float vOffset = (gameTime % vInterval) / vInterval;
        var effectBuffer = new UVOffsetVertexConsumer(vertexConsumer).setOffset(uOffset, vOffset);
        poseStack.scale(size, size, size);
        modelPart.compile(pose, effectBuffer, packedLight, packedOverlay, ColorHelper.getColor(color));
        poseStack.scale(inverse, inverse, inverse);
    }

    public static void startRenderingSoullessOutline(LivingEntity entity, PoseStack stack) {
        poseStack = stack;
        renderingSoullessCreature = true;
    }

    public static void endRenderingSoullessOutline(LivingEntity entity) {
        renderingSoullessCreature = false;
    }
}
