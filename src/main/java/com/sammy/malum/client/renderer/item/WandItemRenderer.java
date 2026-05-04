package com.sammy.malum.client.renderer.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
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
        return material.id().withPrefix("textures/item/wand/").withSuffix("_parts.png");
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext ctx, PoseStack poseStack, MultiBufferSource buffer, int light, int overlay) {
        if (stack.getItem() instanceof WandItem) {
            var data = stack.get(MalumDataComponents.WAND_PARTS);
            if (data == null) {
                return;
            }
            if (!data.isValid()) {
                return;
            }

            poseStack.translate(0.5F, 0f, 0.5F);
            poseStack.scale(1.0F, -1.0F, -1.0F);
            if (ctx.equals(ItemDisplayContext.GUI)) {
                poseStack.mulPose(Axis.YP.rotationDegrees(-135));
                poseStack.mulPose(Axis.XP.rotationDegrees(45));
                poseStack.translate(0.8F, -0.2f, -0.15F);
            }
            else {
                poseStack.translate(0F, 0f, -0.125F);
            }
            var parts = data.parts().entrySet();


            int coreTier = -1;
            for (Map.Entry<WandPartType, WandMaterialType> entry : parts) {
                WandPartType part = entry.getKey();
                if (part.group().equals(WandPartType.WandPartGroup.CORE)) {
                    coreTier = part.coreTier();
                }
            }
            poseStack.translate(0f, -0.05f * coreTier, 0f);
            if (coreTier >= 0) {
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
                    poseStack.pushPose();
                    if (part.group().equals(WandPartType.WandPartGroup.HEAD)) {
                        int inverse = 2 - coreTier;
                        poseStack.translate(0f, 0.25f * inverse, 0f);
                    }
                    model.render(poseStack, buffer.getBuffer(renderType), light, overlay);

                    poseStack.popPose();
                }
            }
        }
    }
}