package com.sammy.malum.client.renderer.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.sammy.malum.client.renderer.entity.FloatingItemEntityRenderer;
import com.sammy.malum.common.block.curiosities.soul_brazier.SoulBrazierBlockEntity;
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
import team.lodestar.lodestone.systems.blockentity.LodestoneBlockEntityInventory;

import static net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY;

public class SoulBrazierRenderer implements BlockEntityRenderer<SoulBrazierBlockEntity> {
    public SoulBrazierRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(SoulBrazierBlockEntity blockEntityIn, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        var level = Minecraft.getInstance().level;
        var itemRenderer = Minecraft.getInstance().getItemRenderer();
        var spiritInventory = blockEntityIn.spiritInventory;
        var extrasInventory = blockEntityIn.extrasInventory;
        int spiritsRendered = 0;
        if (!spiritInventory.isEmpty()) {
            for (int i = 0; i < spiritInventory.slotCount; i++) {
                ItemStack item = spiritInventory.getStackInSlot(i);
                if (item.getItem() instanceof SpiritShardItem shardItem) {
                    poseStack.pushPose();
                    Vector3f offset = blockEntityIn.getSpiritOffset(spiritsRendered++, partialTicks).toVector3f();
                    poseStack.translate(offset.x(), offset.y(), offset.z());
                    FloatingItemEntityRenderer.renderSpiritGlimmer(poseStack, shardItem.type, partialTicks);
                    poseStack.mulPose(Axis.YP.rotationDegrees(((level.getGameTime() % 360) + partialTicks) * 3));
                    poseStack.scale(0.5f, 0.5f, 0.5f);
                    itemRenderer.renderStatic(item, ItemDisplayContext.FIXED, combinedLightIn, NO_OVERLAY, poseStack, bufferIn, level, 0);
                    poseStack.popPose();
                }
            }
        }
        int extrasRendered = 0;
        if (!extrasInventory.isEmpty()) {
            for (int i = 0; i < extrasInventory.slotCount; i++) {
                ItemStack item = extrasInventory.getStackInSlot(i);
                poseStack.pushPose();
                Vector3f offset = blockEntityIn.getExtrasOffset(extrasRendered++, partialTicks).toVector3f();
                poseStack.translate(offset.x(), offset.y(), offset.z());
                poseStack.mulPose(Axis.YN.rotationDegrees(((level.getGameTime() % 360) + partialTicks) * 3));
                poseStack.scale(0.5f, 0.5f, 0.5f);
                itemRenderer.renderStatic(item, ItemDisplayContext.FIXED, combinedLightIn, NO_OVERLAY, poseStack, bufferIn, level, 0);
                poseStack.popPose();
            }
        }
        ItemStack stack = blockEntityIn.inventory.getStackInSlot(0);
        if (!stack.isEmpty()) {
            poseStack.pushPose();
            Vec3 offset = SoulBrazierBlockEntity.BRAZIER_ITEM_OFFSET;
            poseStack.translate(offset.x, offset.y, offset.z);
            poseStack.mulPose(Axis.YP.rotationDegrees((((level.getGameTime() + partialTicks) * 3) % 360)));
            poseStack.scale(0.55f, 0.55f, 0.55f);
            itemRenderer.renderStatic(stack, ItemDisplayContext.FIXED, combinedLightIn, NO_OVERLAY, poseStack, bufferIn, level, 0);
            poseStack.popPose();
        }
    }

    @Override
    public AABB getRenderBoundingBox(SoulBrazierBlockEntity altar) {
        var pos = altar.getBlockPos();
        return new AABB(pos.getX() - 1, pos.getY(), pos.getZ() - 1, pos.getX() + 2, pos.getY() + 2, pos.getZ() + 2);
    }
}
