package com.sammy.malum.client.renderer.block;

import com.mojang.blaze3d.vertex.*;
import com.sammy.malum.client.*;
import com.sammy.malum.common.block.curiosities.mana_mote.*;
import com.sammy.malum.core.systems.registry.*;
import com.sammy.malum.core.systems.spirit.*;
import com.sammy.malum.core.systems.spirit.type.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.blockentity.*;
import net.minecraft.core.*;
import net.minecraft.world.level.block.state.*;
import team.lodestar.lodestone.registry.client.*;
import team.lodestar.lodestone.systems.rendering.cube.CubeVertexData;
import team.lodestar.lodestone.systems.rendering.rendeertype.*;

import java.awt.*;
import java.util.*;

import static com.sammy.malum.registry.client.MalumRenderTypeTokens.MOTE_OF_MANA;


public class MoteOfManaRenderer implements BlockEntityRenderer<ManaMoteBlockEntity> {

    public MoteOfManaRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(ManaMoteBlockEntity blockEntityIn, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        var state = blockEntityIn.getBlockState();
        SpiritHolder<SpiritArcanaType> spirit = SpiritTypeProperty.getSpiritType(state);

        var additive = LodestoneRenderTypes.ADDITIVE_TEXTURE.apply(MOTE_OF_MANA);
        var transparent = LodestoneRenderTypes.TRANSPARENT_TEXTURE.apply(MOTE_OF_MANA);
        var builder = SpiritBasedWorldVFXBuilder.create(spirit);

        var cube = CubeVertexData.makeCubePositions(1f).applyWobble(0, 0.5f, 0.015f);
        var inverse = CubeVertexData.makeCubePositions(-1f).applyWobble(0, 0.5f, 0.015f);
        var primaryColor = spirit.getPrimaryColor();
        var secondaryColor = spirit.getSecondaryColor();

        var directions = new ArrayList<Direction>();
        for (Direction direction : Direction.values()) {
            if (!ManaMoteBlock.isOccluded(state, direction)) {
                directions.add(direction);
            }
        }
        poseStack.pushPose();
        poseStack.translate(0.5f, 0.5f, 0.5f);

        builder.setRenderType(transparent).setColor(primaryColor, 0.9f)
                .renderCubeSides(poseStack, cube);
        builder.setRenderType(additive).setAlpha(0.25f)
                .renderCubeSides(poseStack, cube.scale(1.05f));
        builder.setColor(secondaryColor, 0.4f)
                .renderCubeSides(poseStack, inverse.scale(0.95f));

        poseStack.popPose();
    }
}