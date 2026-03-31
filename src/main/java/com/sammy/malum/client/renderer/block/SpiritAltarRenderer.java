package com.sammy.malum.client.renderer.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.sammy.malum.client.renderer.entity.FloatingItemRenderer;
import com.sammy.malum.common.block.curiosities.spirit_altar.SpiritAltarBlockEntity;
import com.sammy.malum.common.item.spirit.SpiritShardItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;
import team.lodestar.lodestone.modules.toolkit.client.ItemStackDisplayDataRenderer;
import team.lodestar.lodestone.modules.toolkit.inventory.LodestoneItemStackHandler;

import static net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY;

public class SpiritAltarRenderer implements BlockEntityRenderer<SpiritAltarBlockEntity> {
    public SpiritAltarRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(SpiritAltarBlockEntity blockEntityIn, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        var renderer = new ItemStackDisplayDataRenderer();
        renderer.setItemScale(0.45f).render(blockEntityIn.inventory, poseStack, bufferIn, combinedLightIn, partialTicks);
        renderer.setItemScale(0.5f).render(blockEntityIn.spiritInventory, poseStack, bufferIn, combinedLightIn, partialTicks);
//      FloatingItemRenderer.renderSpiritGlimmer(poseStack, shardItem.getSpiritHolder(), partialTicks);
    }

    @Override
    public AABB getRenderBoundingBox(SpiritAltarBlockEntity altar) {
        var pos = altar.getBlockPos();
        return new AABB(pos.getX() - 1, pos.getY(), pos.getZ() - 1, pos.getX() + 2, pos.getY() + 2, pos.getZ() + 2);
    }
}
