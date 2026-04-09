package com.sammy.malum.registry.client;

import com.sammy.malum.common.block.curiosities.mana_mote.*;
import com.sammy.malum.common.block.ether.*;
import com.sammy.malum.common.block.flora.wood.IGradientedLeavesBlock;
import com.sammy.malum.common.block.flora.wood.MalumLeavesBlock;
import com.sammy.malum.core.systems.registry.*;
import com.sammy.malum.registry.common.content.MalumContent;
import net.minecraft.util.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import net.neoforged.neoforge.client.event.*;
import team.lodestar.lodestone.modules.core.easing.Easing;

import static com.sammy.malum.registry.common.content.block.MalumBlocks.*;

public class MalumBlockColors {

    public static void setBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register((s, l, p, c) -> {
            if (l == null) {
                return -1;
            }
            BlockEntity blockEntity = l.getBlockEntity(p);
            if (blockEntity instanceof EtherBlockEntity etherBlockEntity) {
                if (etherBlockEntity.firstColor != null) {
                    return c == 0 ? etherBlockEntity.firstColor.rgb() : -1;
                }
            }
            return -1;
        }, MalumContent.BlockSets.ETHER.get(), MalumContent.BlockSets.IRIDESCENT_ETHER.get());

        var colorProperty = MalumLeavesBlock.COLOR;
        event.register((s, l, p, c) -> {
            float colorMax = colorProperty.getPossibleValues().size();
            float color = s.getValue(colorProperty);
            float pct = (colorMax - (color / colorMax));
            float value = Easing.SINE_IN_OUT.ease(pct);
            var leaves = (IGradientedLeavesBlock) s.getBlock();
            int red = (int) Mth.lerp(value, leaves.getMinColor().getRed(), leaves.getMaxColor().getRed());
            int green = (int) Mth.lerp(value, leaves.getMinColor().getGreen(), leaves.getMaxColor().getGreen());
            int blue = (int) Mth.lerp(value, leaves.getMinColor().getBlue(), leaves.getMaxColor().getBlue());
            return red << 16 | green << 8 | blue;
        }, RUNEWOOD_LEAVES.get(), HANGING_RUNEWOOD_LEAVES.get(), AZURE_RUNEWOOD_LEAVES.get(), HANGING_AZURE_RUNEWOOD_LEAVES.get());

        event.register((s, l, p, c) -> {
            boolean isPersistent = s.getOptionalValue(MalumLeavesBlock.PERSISTENT).orElse(false);
            int distanceMax = MalumLeavesBlock.DISTANCE.getPossibleValues().size();
            BlockState stateForDistance = s;
            if (s.getOptionalValue(MalumLeavesBlock.DISTANCE).isEmpty())  {
                if (l != null && p != null) {
                    BlockState state = l.getBlockState(p.above());
                    if (state.getBlock() instanceof MalumLeavesBlock) {
                        stateForDistance = state;
                    }
                }
            }
            float distance = stateForDistance.getOptionalValue(MalumLeavesBlock.DISTANCE).orElse(distanceMax);
            float colorMax = colorProperty.getPossibleValues().size();
            float color = s.getValue(colorProperty);
            float distanceDelta = distance / distanceMax;
            float colorDelta = color / colorMax;
            float value = isPersistent ? colorDelta : Easing.QUAD_OUT.lerp(distanceDelta, 0, colorDelta);
            var leaves = (IGradientedLeavesBlock) s.getBlock();
            int red = (int) Mth.lerp(value, leaves.getMinColor().getRed(), leaves.getMaxColor().getRed());
            int green = (int) Mth.lerp(value, leaves.getMinColor().getGreen(), leaves.getMaxColor().getGreen());
            int blue = (int) Mth.lerp(value, leaves.getMinColor().getBlue(), leaves.getMaxColor().getBlue());
            return red << 16 | green << 8 | blue;
        }, SOULWOOD_LEAVES.get(), HANGING_SOULWOOD_LEAVES.get());

        event.register((s, l, p, c) -> {
            var spiritType = SpiritHolder.getSpiritType(s.getValue(ManaMoteBlock.SPIRIT_TYPE));
            var color  = spiritType.getPrimaryColor();
            int red = color.getRed();
            int green = color.getGreen();
            int blue = color.getBlue();
            return red << 16 | green << 8 | blue;
        }, MalumContent.Progression.SPIRIT_MOTE.get());
    }
}
