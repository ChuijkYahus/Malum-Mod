package com.sammy.malum.client.renderer.block.totemancy;

import com.mojang.blaze3d.vertex.*;
import com.mojang.math.*;
import com.sammy.malum.client.*;
import com.sammy.malum.common.block.curiosities.totem.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.blockentity.*;
import net.minecraft.util.*;
import net.minecraft.world.level.block.state.properties.*;
import org.joml.*;
import team.lodestar.lodestone.registry.client.*;
import team.lodestar.lodestone.systems.easing.*;
import team.lodestar.lodestone.systems.rendering.cube.*;
import team.lodestar.lodestone.systems.rendering.rendeertype.*;

import java.lang.Math;


public class TotemPoleRenderer implements BlockEntityRenderer<TotemPoleBlockEntity> {

    public TotemPoleRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(TotemPoleBlockEntity blockEntityIn, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        var spiritType = blockEntityIn.getSpirit();
        if (spiritType == null) {
            return;
        }
        var direction = blockEntityIn.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);

        var level = Minecraft.getInstance().level;
        var renderType = LodestoneRenderTypes.ADDITIVE_TEXTURE.apply(RenderTypeToken.createToken(spiritType.getGlowTexture()));
        float delta = blockEntityIn.getGlowDelta();
        float alpha = delta * 0.7f;
        float ease = Easing.SINE_OUT.ease(delta, 0, 1, 1);
        float offsetDistance = 0.2f - ease * 0.2f;
        float wobbleStrength = 0.1f - ease * 0.075f;
        Vector3f[] vertices = new Vector3f[]{
                new Vector3f(-0.5125f, 0f, 0.5125f), new Vector3f(0.5125f, 0f, 0.5125f),
                new Vector3f(0.5125f, 1f, 0.5125f), new Vector3f(-0.5125f, 1f, 0.5125f)};

        poseStack.pushPose();
        poseStack.translate(0.5f, 0, 0.5f);
        poseStack.mulPose(Axis.YN.rotationDegrees(direction.toYRot()));


        float gameTime = level.getGameTime() + partialTicks;
        int time = 160;
        for (int i = 0; i < 4; i++) {
            var color = i <= 2 ? spiritType.getPrimaryColor() : spiritType.getSecondaryColor();
            double offset = 0;
            if (offsetDistance > 0) {
                double angle = i / 4f * (Math.PI * 2);
                angle += ((gameTime % time) / time) * (Math.PI * 2);
                offset = (offsetDistance * Math.cos(angle));
                if (i % 2 == 0) {
                    offset *= -1;
                }
            }

            poseStack.pushPose();
            poseStack.translate(offset, 0, 0);
            CubeVertexData.applyVertexWobble(vertices, 0, wobbleStrength);
            SpiritBasedWorldVFXBuilder.create(spiritType)
                    .setColor(color, alpha)
                    .setRenderType(renderType)
                    .renderQuad(poseStack, vertices, 1f);
            poseStack.popPose();
            alpha *= (1 - (delta+0.2f) * 0.5f);
        }
        poseStack.popPose();
    }
}
