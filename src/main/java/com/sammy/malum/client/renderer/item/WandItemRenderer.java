package com.sammy.malum.client.renderer.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.sammy.malum.client.model.item.WandPartsModel;
import com.sammy.malum.common.block.storage.jar.SpiritJarBlockEntity;
import com.sammy.malum.common.data.component.WandPartsComponent;
import com.sammy.malum.common.data.custom.wand_parts.WandMaterialType;
import com.sammy.malum.common.data.custom.wand_parts.WandPartType;
import com.sammy.malum.common.item.curiosities.WandItem;
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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import team.lodestar.lodestone.registry.client.LodestoneRenderTypes;
import team.lodestar.lodestone.registry.client.LodestoneShaders;
import team.lodestar.lodestone.systems.rendering.LodestoneRenderType;
import team.lodestar.lodestone.systems.rendering.StateShards;
import team.lodestar.lodestone.systems.rendering.rendeertype.RenderTypeProvider;
import team.lodestar.lodestone.systems.rendering.rendeertype.RenderTypeToken;

import java.util.Map;
import java.util.Set;

import static com.mojang.blaze3d.vertex.DefaultVertexFormat.POSITION_COLOR_TEX_LIGHTMAP;
import static com.mojang.blaze3d.vertex.VertexFormat.Mode.QUADS;

public class WandItemRenderer extends BlockEntityWithoutLevelRenderer {

    public static final RenderTypeProvider WAND_CUTOUT = new RenderTypeProvider((token) ->
            LodestoneRenderTypes.createGenericRenderType(token, "wand_texture", POSITION_COLOR_TEX_LIGHTMAP,
                    QUADS, LodestoneRenderTypes.builder(token, StateShards.NORMAL_TRANSPARENCY, LodestoneShaders.LODESTONE_TEXTURE, LodestoneRenderTypes.NO_CULL, LodestoneRenderTypes.LIGHTMAP)));


    private final BlockEntityRenderDispatcher blockEntityRenderDispatcher;

    public WandItemRenderer(BlockEntityRenderDispatcher pBlockEntityRenderDispatcher, EntityModelSet pEntityModelSet) {
        super(pBlockEntityRenderDispatcher, pEntityModelSet);
        this.blockEntityRenderDispatcher = pBlockEntityRenderDispatcher;
    }

    public ResourceLocation getPartTexture(WandMaterialType material) {
        return material.id().withPath("item/wand/").withSuffix("_parts.png");
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext ctx, PoseStack poseStack, MultiBufferSource buffer, int light, int overlay) {
        if (stack.getItem() instanceof WandItem) {
            var data = stack.get(MalumDataComponents.WAND_PARTS);
            if (data == null) {
                return;
            }
            var parts = data.parts().entrySet();
            for (Map.Entry<WandPartType, WandMaterialType> entry : parts) {
                var part = entry.getKey();
                var material = entry.getValue();
                var optional = WandPartsModel.getModelPart(part);
                if (optional.isEmpty()) {
                    continue;
                }
                var texture = getPartTexture(material);
                var renderType = WAND_CUTOUT.apply(RenderTypeToken.createToken(texture)).getRenderType();
                var model = optional.get();
                model.render(poseStack, buffer.getBuffer(renderType), light, overlay);
            }
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