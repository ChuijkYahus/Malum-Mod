package com.sammy.malum.client.renderer.block.totemancy;

import com.mojang.blaze3d.vertex.*;
import com.mojang.math.*;
import com.sammy.malum.client.*;
import com.sammy.malum.common.block.curiosities.totem.anchor.*;
import com.sammy.malum.registry.client.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.blockentity.*;
import net.minecraft.util.*;
import net.minecraft.world.level.block.state.properties.*;
import org.joml.*;
import team.lodestar.lodestone.registry.client.*;
import team.lodestar.lodestone.systems.easing.*;
import team.lodestar.lodestone.systems.rendering.rendeertype.*;

import java.lang.Math;

public class RiteAnchorRenderer implements BlockEntityRenderer<RiteAnchorBlockEntity> {

    public RiteAnchorRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(RiteAnchorBlockEntity blockEntityIn, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        var spiritType = blockEntityIn.spirit;
        if (spiritType == null) {
            return;
        }
        var direction = blockEntityIn.getBlockState().getValue(RiteAnchorBlock.HORIZONTAL_FACING);

        var level = Minecraft.getInstance().level;
        var renderType = LodestoneRenderTypes.ADDITIVE_TEXTURE.apply(MalumRenderTypeTokens.RITE_ANCHOR_GLOW);
        float delta = blockEntityIn.effectStrength / 20f;
        float alpha = 0.6f + delta * 0.4f;
        float ease = Easing.SINE_OUT.ease(delta, 0, 1, 1);
        float wobbleStrength = ease * 0.02f;
        Vector3f[] positions = new Vector3f[]{
                new Vector3f(0.5f, 1.01f, -0.5f), new Vector3f(-0.5f, 1.01f, -0.5f), new Vector3f(-0.5f, 1.01f, 0.5f), new Vector3f(0.5f, 1.01f, 0.5f)};

        poseStack.pushPose();
        poseStack.translate(0.5f, 0, 0.5f);
        poseStack.mulPose(Axis.YN.rotationDegrees(direction.toYRot()));


        float gameTime = level.getGameTime() + partialTicks;
        int time = 160;
        for (int i = 0; i < 4; i++) {
            poseStack.pushPose();
            applyWobble(positions, wobbleStrength);
            var builder = SpiritBasedWorldVFXBuilder.create(spiritType)
                    .setRenderType(renderType)
                    .setAlpha(alpha);
            if (i > 0) {
                double angle = i / 4f * (Math.PI * 2);
                angle += ((gameTime % time) / time) * (Math.PI * 2);
                double x = (i * 0.01f * Math.sin(angle));
                double z = (i * 0.01f * Math.cos(angle));
                poseStack.translate(x, 0, z);
                builder.setColor(spiritType.getSecondaryColor());
            }
            else {
                builder.setColor(spiritType.getPrimaryColor());
            }
            builder.renderQuad(poseStack, positions, 1f);
            alpha *= 0.3f + delta * 0.5f;
            poseStack.popPose();
        }
        poseStack.popPose();
    }

    public static void applyWobble(Vector3f[] offsets, float strength) {
        float offset = 0;
        for (Vector3f vector3f : offsets) {
            double time = ((Minecraft.getInstance().level.getGameTime() / 40.0F) % Math.PI * 2);
            float angle = (float) (time + (offset * Math.PI * 2));
            float x = Mth.sin(angle) * strength;
            float z = Mth.cos(angle) * strength;
            vector3f.add(x, 0, z);
            offset += 0.8f;
        }
    }
}