package com.sammy.malum.client.renderer.entity;

import com.mojang.blaze3d.vertex.*;
import com.sammy.malum.client.*;
import com.sammy.malum.common.entity.activator.*;
import com.sammy.malum.registry.client.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.resources.*;
import team.lodestar.lodestone.registry.client.*;
import team.lodestar.lodestone.systems.rendering.*;

public class BlockRiteEffectActivatorEntityRenderer extends EntityRenderer<BlockRiteEffectActivatorEntity> {

    public BlockRiteEffectActivatorEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0;
        this.shadowStrength = 0;
    }


    @Override
    public void render(BlockRiteEffectActivatorEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int packedLightIn) {
        float effectScalar = entity.getVisualEffectScalar();
        var renderType = LodestoneRenderTypes.ADDITIVE_ROUNDED_TEXTURE_TRIANGLE.apply(MalumRenderTypeTokens.CONCENTRATED_TRAIL);
        var builder = VFXBuilders.createWorld().setRenderType(renderType);
        var primaryColor = entity.getSpiritType().getPrimaryColor();
        var secondaryColor = entity.getSpiritType().getSecondaryColor();
        float scale = 0.8f * effectScalar;
        float alpha = 0.45f * effectScalar;
        RenderUtils.renderEntityTrail(poseStack, builder, entity.trail, entity, primaryColor, secondaryColor, scale*0.75f, alpha*2, partialTicks);
        RenderUtils.renderEntityTrail(poseStack, builder, entity.longTrail, entity, primaryColor, secondaryColor, scale, alpha, partialTicks);
        super.render(entity, entityYaw, partialTicks, poseStack, bufferIn, packedLightIn);
    }

    @Override
    public ResourceLocation getTextureLocation(BlockRiteEffectActivatorEntity entity) {
        return TextureAtlas.LOCATION_BLOCKS;
    }
}

