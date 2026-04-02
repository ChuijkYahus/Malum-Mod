package com.sammy.malum.client.renderer.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.sammy.malum.common.block.storage.MalumItemHolderBlockEntity;
import com.sammy.malum.common.block.storage.stand.ItemStandBlock;
import com.sammy.malum.registry.common.item.MalumItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.*;
import team.lodestar.lodestone.modules.toolkit.client.ItemStackDisplayDataRenderer;

import static net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY;


@SuppressWarnings("NullableProblems")
public class MalumItemHolderRenderer implements BlockEntityRenderer<MalumItemHolderBlockEntity> {
    public MalumItemHolderRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(MalumItemHolderBlockEntity blockEntityIn, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        var renderer = new ItemStackDisplayDataRenderer();
        renderer.render(blockEntityIn.inventory, poseStack, bufferIn, combinedLightIn, partialTicks);
    }

    @Override
    public AABB getRenderBoundingBox(MalumItemHolderBlockEntity blockEntity) {
        var pos = blockEntity.getBlockPos();
        return new AABB(pos.getX() - 1, pos.getY() - 1, pos.getZ() - 1, pos.getX() + 2, pos.getY() + 2, pos.getZ() + 2);
    }
}