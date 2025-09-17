package com.sammy.malum.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.sammy.malum.client.RenderUtils;
import com.sammy.malum.common.entity.activator.EntityRiteEffectActivatorEntity;
import com.sammy.malum.registry.client.MalumRenderTypeTokens;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import team.lodestar.lodestone.registry.client.LodestoneRenderTypes;
import team.lodestar.lodestone.systems.rendering.VFXBuilders;

import static com.sammy.malum.client.renderer.entity.FloatingItemEntityRenderer.renderSpiritGlimmer;

public class EntityRiteEffectActivatorEntityRenderer extends EntityRenderer<EntityRiteEffectActivatorEntity> {

    public EntityRiteEffectActivatorEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0;
        this.shadowStrength = 0;
    }


    @Override
    public void render(EntityRiteEffectActivatorEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int packedLightIn) {
        float effectScalar = entity.getVisualEffectScalar();
        var renderType = LodestoneRenderTypes.ADDITIVE_ROUNDED_TEXTURE_TRIANGLE.apply(MalumRenderTypeTokens.CONCENTRATED_TRAIL);
        var builder = VFXBuilders.createWorld().setRenderType(renderType);
        var primaryColor = entity.getSpiritType().getPrimaryColor();
        var secondaryColor = entity.getSpiritType().getSecondaryColor();
        float scale = 1.2f * effectScalar;
        float alpha = 0.3f * effectScalar;
        RenderUtils.renderEntityTrail(poseStack, builder, entity.trail, entity, primaryColor, secondaryColor, scale*0.75f, alpha*2, partialTicks);
        RenderUtils.renderEntityTrail(poseStack, builder, entity.longTrail, entity, primaryColor, secondaryColor, scale, alpha, partialTicks);
        super.render(entity, entityYaw, partialTicks, poseStack, bufferIn, packedLightIn);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityRiteEffectActivatorEntity entity) {
        return TextureAtlas.LOCATION_BLOCKS;
    }
}
