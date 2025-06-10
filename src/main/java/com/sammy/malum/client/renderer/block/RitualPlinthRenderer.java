package com.sammy.malum.client.renderer.block;

import com.mojang.blaze3d.vertex.*;
import com.sammy.malum.common.block.curiosities.ritual_plinth.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.blockentity.*;
import net.minecraft.world.phys.*;

public class RitualPlinthRenderer implements BlockEntityRenderer<RitualPlinthBlockEntity> {

    public RitualPlinthRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public AABB getRenderBoundingBox(RitualPlinthBlockEntity blockEntityIn) {
        var pos = blockEntityIn.getBlockPos();
        return new AABB(pos.getX() - 2, pos.getY()-1, pos.getZ() - 2, pos.getX() + 3, pos.getY() + 6, pos.getZ() + 3);
    }
    
    @Override
    public void render(RitualPlinthBlockEntity blockEntityIn, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
    }
}
