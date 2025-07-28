package com.sammy.malum.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.sammy.malum.client.RenderUtils;
import com.sammy.malum.common.entity.activator.RiteEffectActivatorEntity;
import com.sammy.malum.common.entity.nitrate.AbstractNitrateEntity;
import com.sammy.malum.registry.client.MalumRenderTypeTokens;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import team.lodestar.lodestone.registry.client.LodestoneRenderTypes;
import team.lodestar.lodestone.systems.rendering.VFXBuilders;

import java.awt.*;
import java.util.function.Function;

import static com.sammy.malum.client.renderer.entity.FloatingItemEntityRenderer.renderSpiritGlimmer;

public class RiteEffectActivatorEntityRenderer extends EntityRenderer<RiteEffectActivatorEntity> {

    public RiteEffectActivatorEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0;
        this.shadowStrength = 0;
    }


    @Override
    public void render(RiteEffectActivatorEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int packedLightIn) {
        float effectScalar = entity.getVisualEffectScalar();
        var renderType = LodestoneRenderTypes.ADDITIVE_TEXTURE_TRIANGLE.apply(MalumRenderTypeTokens.CONCENTRATED_TRAIL);
        var builder = VFXBuilders.createWorld().setRenderType(renderType);
        var primaryColor = entity.
        RenderUtils.renderEntityTrail(poseStack, builder, entity.trailPointBuilder, entity, primaryColor, secondaryColor, effectScalar, partialTicks);
        RenderUtils.renderEntityTrail(poseStack, builder, entity.spinningTrailPointBuilder, entity, primaryColor, secondaryColor, effectScalar, partialTicks);
        if (entity.age > 1 && !entity.fadingAway) {
            poseStack.pushPose();
            float glimmerScale = 3f * Math.min(1f, (entity.age - 2) / 5f);
            poseStack.scale(glimmerScale, glimmerScale, glimmerScale);
            renderSpiritGlimmer(poseStack, primaryColor.apply(0f), secondaryColor.apply(0.125f), partialTicks);
            poseStack.popPose();
        }
        super.render(entity, entityYaw, partialTicks, poseStack, bufferIn, packedLightIn);
    }

    @Override
    public ResourceLocation getTextureLocation(RiteEffectActivatorEntity entity) {
        return TextureAtlas.LOCATION_BLOCKS;
    }
}
