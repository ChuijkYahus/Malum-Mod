package com.sammy.malum.client.renderer.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.sammy.malum.common.block.storage.jar.SpiritJarBlockEntity;
import com.sammy.malum.common.item.spirit.SpiritJarItem;
import com.sammy.malum.registry.common.MalumContent;
import com.sammy.malum.registry.common.item.MalumDataComponents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class WandItemRenderer extends BlockEntityWithoutLevelRenderer {

    private final BlockEntityRenderDispatcher blockEntityRenderDispatcher;

    public WandItemRenderer(BlockEntityRenderDispatcher pBlockEntityRenderDispatcher, EntityModelSet pEntityModelSet) {
        super(pBlockEntityRenderDispatcher, pEntityModelSet);
        this.blockEntityRenderDispatcher = pBlockEntityRenderDispatcher;
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext ctx, PoseStack poseStack, MultiBufferSource buffer, int light, int overlay) {
        if (stack.getItem() instanceof SpiritJarItem) {
//            ItemRenderer renderer = Minecraft.getInstance().getItemRenderer();
//
//            // Pop off the transformations applied by ItemRenderer before calling this
//            poseStack.popPose();
//            poseStack.pushPose();
//
//            BakedModel model = Minecraft.getInstance().getBlockRenderer().getBlockModel(MalumContent.Sorcery.SPIRIT_JAR.get().defaultBlockState());
//            model = model.applyTransform(ctx, poseStack, isLeftHand(ctx));
//            poseStack.translate(-.5, -.5, -.5);
//
//            boolean glint = stack.hasFoil();
//            for (BakedModel pass : model.getRenderPasses(stack, true)) {
//                for (RenderType type : pass.getRenderTypes(stack, true)) {
//                    VertexConsumer consumer = ItemRenderer.getFoilBufferDirect(buffer, type, true, glint);
//                    renderer.renderModelLists(pass, stack, light, overlay, poseStack, consumer);
//                }
//            }
//
//            if (stack.has(MalumDataComponents.SPIRIT_JAR_CONTENTS)) {
//                jar.contents = stack.get(MalumDataComponents.SPIRIT_JAR_CONTENTS);
//                this.blockEntityRenderDispatcher.renderItem(jar, poseStack, buffer, light, overlay);
//            }
        }
    }

    public static boolean isLeftHand(ItemDisplayContext ctx) {
        return ctx == ItemDisplayContext.FIRST_PERSON_LEFT_HAND || ctx == ItemDisplayContext.THIRD_PERSON_LEFT_HAND;
    }
}