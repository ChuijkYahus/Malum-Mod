package com.sammy.malum.client.renderer.block;

import com.mojang.blaze3d.vertex.*;
import com.mojang.math.*;
import com.sammy.malum.common.block.curiosities.weeping_well.*;
import com.sammy.malum.core.systems.registry.*;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.registry.client.*;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.blockentity.*;
import org.joml.*;
import team.lodestar.lodestone.handlers.*;
import team.lodestar.lodestone.registry.client.*;
import team.lodestar.lodestone.systems.rendering.*;
import team.lodestar.lodestone.systems.rendering.rendeertype.*;

import java.awt.*;


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

        var vignette = LodestoneRenderTypes.TRANSPARENT_TEXTURE.apply(MalumRenderTypeTokens.VOID_VIGNETTE);
        builder.replaceBufferSource(LodestoneRenderHandler.LATE_DEFERRED_RENDER)
                .setRenderType(vignette)
                .renderQuad(poseStack, positions, 1f);
        long gameTime = blockEntityIn.getLevel().getGameTime();
        float uOffset = ((gameTime + partialTicks) % 4000) / 2000f;
        float vOffset = ((gameTime + 500f + partialTicks) % 8000) / 8000f;
        float alpha = 0.08f;

        builder.replaceBufferSource(LodestoneRenderHandler.DEFERRED_RENDER.getTarget());

        Color[] colors = new Color[] {
                MalumSpiritTypes.ELDRITCH_SPIRIT.getPrimaryColor(),
                MalumSpiritTypes.WICKED_SPIRIT.getSecondaryColor(),
                MalumSpiritTypes.AQUEOUS_SPIRIT.getPrimaryColor(),
                MalumSpiritTypes.AERIAL_SPIRIT.getSecondaryColor()
        };
        for (int i = 0; i < 4; i++) {
            float speed = 1000f + 250f * i;
            ShaderUniformHandler uniforms = new ShaderUniformHandler()
                    .modifyUniform("Speed", speed)
                    .modifyUniform("Width", 48f)
                    .modifyUniform("Height", 48f)
                    .modifyUniform("UVCoordinates", -20f, 40f, -20f, 40f);
            var distortion = MalumRenderTypes.WEEPING_WELL_DISTORTED_TEXTURE.apply(MalumRenderTypeTokens.VOID_NOISE).withUniformHandler(uniforms);

            builder.setColor(colors[i]).setRenderType(distortion);

            builder.setAlpha(alpha);
            builder.setUV(-uOffset, vOffset, 1 - uOffset, 1 + vOffset).renderQuad(poseStack, positions, 1f);
            builder.setUV(uOffset, -vOffset, 1 + uOffset, 1 - vOffset).renderQuad(poseStack, positions, 1f);
            alpha -= 0.02f;
            uOffset = -uOffset - 0.2f;
            vOffset = -vOffset + 0.4f;
            poseStack.translate(0, 0.05f, 0);
            poseStack.mulPose(Axis.YP.rotationDegrees(90));
        }
        poseStack.popPose();
    }
}