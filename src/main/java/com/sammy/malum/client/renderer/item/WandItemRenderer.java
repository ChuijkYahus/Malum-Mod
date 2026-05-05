package com.sammy.malum.client.renderer.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.sammy.malum.common.data.custom.wand_parts.WandMaterialType;
import com.sammy.malum.common.data.custom.wand_parts.WandPartType;
import com.sammy.malum.common.data.custom.wand_parts.WandPartType.WandPartGroup;
import com.sammy.malum.common.item.curiosities.WandItem;
import com.sammy.malum.registry.client.MalumModels;
import com.sammy.malum.registry.common.item.MalumDataComponents;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import team.lodestar.lodestone.registry.client.LodestoneRenderTypes;
import team.lodestar.lodestone.registry.client.LodestoneShaders;
import team.lodestar.lodestone.systems.rendering.StateShards;
import team.lodestar.lodestone.systems.rendering.rendeertype.RenderTypeProvider;
import team.lodestar.lodestone.systems.rendering.rendeertype.RenderTypeToken;

import java.util.Collections;
import java.util.Map;

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

    /**
     * Mixin here to add custom part handling.
     */
    public static void renderWandPart(PoseStack poseStack, VertexConsumer vertexConsumer, RenderType renderType, WandPartType corePart, WandPartType partType) {
        var wandParts = MalumModels.WAND_PARTS;
        var group = partType.group();
        String partName;
        boolean isHead = group.equals(WandPartGroup.HEAD);
        boolean isCore = group.equals(WandPartGroup.CORE);
        if (isHead || isCore) {
            partName = getModelPartName(partType);
        }
        else {
            partName = getModelPartName(corePart) + "_" + getModelPartName(partType);
        }

        int coreTier = corePart.coreTier();
        int partTier = partType.coreTier();
        poseStack.pushPose();
        if (isCore || !isHead) {
            poseStack.translate(-16f * coreTier, 0f, 0f);
        }
        if (isHead) {
            poseStack.translate(-16f * partTier, 0f, 0f);
        }
        wandParts.renderPart(partName, poseStack, vertexConsumer, renderType);
        poseStack.popPose();
    }

    /**.
     * @return The name of the model part that should be used for a wand
     */
    protected static String getModelPartName(WandPartType partType) {
        return partType.id().getPath();
    }

    protected static ModelPart getPart(ModelPart part, String name) {
        try {
            return part.getChild(name);
        } catch (Exception ignored) {
            return new ModelPart(Collections.emptyList(), Collections.emptyMap());
        }
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
//            poseStack.scale(1.0F, -1.0F, -1.0F);

            var parts = data.parts().entrySet();


            int coreTier = -1;
            WandPartType corePart = null;
            for (Map.Entry<WandPartType, WandMaterialType> entry : parts) {
                WandPartType part = entry.getKey();
                if (part.group().equals(WandPartGroup.CORE)) {
                    corePart = part;
                    coreTier = part.coreTier();
                }
            }
            if (coreTier >= 0) {
                for (Map.Entry<WandPartType, WandMaterialType> entry : parts) {
                    var part = entry.getKey();
                    var material = entry.getValue();
                    var texture = getPartTexture(material);
                    var renderType = WAND_CUTOUT.apply(RenderTypeToken.createToken(texture)).getRenderType();
                    var vertexConsumer = buffer.getBuffer(renderType);
                    renderWandPart(poseStack, vertexConsumer, renderType, corePart, part);
                }
            }
        }
    }
}