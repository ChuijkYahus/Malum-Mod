package com.sammy.malum.client.renderer.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.sammy.malum.common.block.storage.ItemStandBlockEntity;
import com.sammy.malum.common.item.spirit.SpiritShardItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import static net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY;


public class ItemStandRenderer implements BlockEntityRenderer<ItemStandBlockEntity> {
    public ItemStandRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(ItemStandBlockEntity blockEntityIn, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        Level level = Minecraft.getInstance().level;
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack stack = blockEntityIn.inventory.getStackInSlot(0);
        if (!stack.isEmpty()) {
            poseStack.pushPose();
            Vector3f offset = new Vector3f(blockEntityIn.itemOffset());
            if (stack.getItem() instanceof SpiritShardItem) {
                double y = Math.sin(((level.getGameTime() + partialTicks)) / 20f) * 0.05f;
                poseStack.translate(0, y, 0);
            }
            poseStack.translate(offset.x(), offset.y(), offset.z());
            poseStack.mulPose(Vector3f.YP.rotationDegrees(((level.getGameTime() % 360) + partialTicks) * 3));
            poseStack.scale(0.6f, 0.6f, 0.6f);
            itemRenderer.renderStatic(stack, ItemTransforms.TransformType.FIXED, combinedLightIn, NO_OVERLAY, poseStack, bufferIn, 0);
            poseStack.popPose();
        }
    }
}