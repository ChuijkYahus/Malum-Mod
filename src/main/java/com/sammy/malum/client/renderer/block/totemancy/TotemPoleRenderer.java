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
        var renderType = LodestoneRenderTypes.ADDITIVE_TEXTURE.apply(RenderTypeToken.createToken(spiritType.getTotemGlowTexture()));
        var color = spiritType.getPrimaryColor();
        float delta = blockEntityIn.getGlowDelta();
        float alpha = delta * 0.7f;
        float ease = Easing.SINE_OUT.ease(delta, 0, 1, 1);
        float distance = 0.2f - ease * 0.2f;
        float wobbleStrength = 0.1f - ease * 0.075f;
        Vector3f[] positions = new Vector3f[]{
                new Vector3f(-0.025f, -0.025f, 1.01f), new Vector3f(1.025f, -0.025f, 1.01f),
                new Vector3f(1.025f, 1.025f, 1.01f), new Vector3f(-0.025f, 1.025f, 1.01f)};



        poseStack.pushPose();
        poseStack.translate(0.5f, 0.5f, 0.5f);
        poseStack.mulPose(Axis.YN.rotationDegrees(direction.toYRot()));
        poseStack.translate(-0.5f, -0.5f, -0.5f);


        float gameTime = level.getGameTime() + partialTicks;
        int time = 160;
        for (int i = 0; i < 4; i++) {
            applyWobble(positions, wobbleStrength);
            double translation = 0;
            if (distance > 0) {
                boolean odd = i % 2 == 0;
                double angle = i / 4f * (Math.PI * 2);
                angle += ((gameTime % time) / time) * (Math.PI * 2);
                double offset = (distance * Math.cos(angle));
                translation = odd ? -offset : offset;
            }
            poseStack.translate(translation, 0, 0);
            SpiritBasedWorldVFXBuilder.create(spiritType)
                    .setColor(color, alpha)
                    .setRenderType(renderType)
                    .renderQuad(poseStack, positions, 1f);
            poseStack.translate(-translation, 0, 0);
            alpha *= (1 - delta * 0.5f);
        }
        poseStack.popPose();
    }

    public static void applyWobble(Vector3f[] offsets, float strength) {
        float offset = 0;
        for (Vector3f vector3f : offsets) {
            double time = ((Minecraft.getInstance().level.getGameTime() / 40.0F) % Math.PI * 2);
            float sine = Mth.sin((float) (time + (offset * Math.PI * 2))) * strength;
            vector3f.add(sine, -sine, 0);
            offset += 0.25f;
        }
    }
}
