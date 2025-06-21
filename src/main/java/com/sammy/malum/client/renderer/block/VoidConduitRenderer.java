package com.sammy.malum.client.renderer.block;

import com.mojang.blaze3d.vertex.*;
import com.mojang.math.*;
import com.sammy.malum.common.block.curiosities.weeping_well.*;
import com.sammy.malum.registry.client.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.blockentity.*;
import org.joml.*;
import team.lodestar.lodestone.handlers.*;
import team.lodestar.lodestone.registry.client.*;
import team.lodestar.lodestone.systems.rendering.*;
import team.lodestar.lodestone.systems.rendering.rendeertype.*;


public class VoidConduitRenderer implements BlockEntityRenderer<VoidConduitBlockEntity> {

    public VoidConduitRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(VoidConduitBlockEntity blockEntityIn, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        float height = 0.375f;
        float width = 1.5f;

        Vector3f[] positions = new Vector3f[]{new Vector3f(-width, height, width), new Vector3f(width, height, width), new Vector3f(width, height, -width), new Vector3f(-width, height, -width)};
        VFXBuilders.WorldVFXBuilder builder = VFXBuilders.createWorld();
        poseStack.pushPose();
        poseStack.translate(0.5f, 0.01f, 0.5f);

        builder.replaceBufferSource(RenderHandler.LATE_DELAYED_RENDER)
                .setRenderType(LodestoneRenderTypes.TRANSPARENT_TEXTURE.apply(MalumRenderTypeTokens.VOID_VIGNETTE))
                .renderQuad(poseStack, positions, 1f);
        long gameTime = blockEntityIn.getLevel().getGameTime();
        float uOffset = ((gameTime + partialTicks) % 4000) / 2000f;
        float vOffset = ((gameTime + 500f + partialTicks) % 8000) / 8000f;
        float alpha = 0.05f;

        var distortion = MalumRenderTypes.WEEPING_WELL_DISTORTED_TEXTURE.apply(MalumRenderTypeTokens.VOID_NOISE);
        builder.replaceBufferSource(RenderHandler.DELAYED_RENDER.getTarget());
        for (int i = 0; i < 3; i++) {
            float speed = 1000f + 250f * i;
            final ShaderUniformHandler uniforms = new ShaderUniformHandler()
                    .modifyUniform("Speed", speed)
                    .modifyUniform("Width", 48f)
                    .modifyUniform("Height", 48f)
                    .modifyUniform("UVCoordinates", -2f, 4f, -2f, 4f);
            builder.setColor(MalumSpiritTypes.WICKED_SPIRIT.getPrimaryColor())
                    .setRenderType(distortion.withUniformHandler(uniforms));

            builder.setAlpha(alpha);
            builder.setUV(-uOffset, vOffset, 1 - uOffset, 1 + vOffset).renderQuad(poseStack, positions, 1f);
            builder.setUV(uOffset, -vOffset, 1 + uOffset, 1 - vOffset).renderQuad(poseStack, positions, 1f);
            alpha -= 0.0125f;
            uOffset = -uOffset - 0.2f;
            vOffset = -vOffset + 0.4f;
            poseStack.translate(0, 0.05f, 0);
            poseStack.mulPose(Axis.YP.rotationDegrees(90));
            if (i == 0) {
                builder.setColor(MalumSpiritTypes.ELDRITCH_SPIRIT.getPrimaryColor());
            }
        }
        poseStack.popPose();
    }
}