package com.sammy.malum.registry.client;

import com.sammy.malum.client.extensions.*;
import com.sammy.malum.common.block.curiosities.mana_mote.*;
import com.sammy.malum.common.block.ether.*;
import com.sammy.malum.common.block.nature.*;
import com.sammy.malum.core.systems.spirit.*;
import com.sammy.malum.registry.common.block.*;
import com.sammy.malum.registry.common.item.*;
import net.minecraft.util.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import net.neoforged.bus.api.*;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.client.extensions.common.*;
import team.lodestar.lodestone.systems.easing.*;

import static com.sammy.malum.registry.common.block.MalumBlocks.*;

public class MalumBlockColors {

    public static void setBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register((s, l, p, c) -> {
            BlockEntity blockEntity = l.getBlockEntity(p);
            if (blockEntity instanceof EtherBlockEntity etherBlockEntity) {
                if (etherBlockEntity.firstColor != null) {
                    return c == 0 ? etherBlockEntity.firstColor.rgb() : -1;
                }
            }
            return -1;
        }, ETHER.get(), IRIDESCENT_ETHER.get());

        var colorProperty = MalumLeavesBlock.COLOR;
        event.register((s, l, p, c) -> {
            float colorMax = colorProperty.getPossibleValues().size();
            float color = s.getValue(colorProperty);
            float pct = (colorMax - (color / colorMax));
            float value = Easing.SINE_IN_OUT.ease(pct, 0, 1, 1);
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
            float value = isPersistent ? colorDelta : Easing.QUAD_OUT.ease(distanceDelta, 0, colorDelta);
            var leaves = (IGradientedLeavesBlock) s.getBlock();
            int red = (int) Mth.lerp(value, leaves.getMinColor().getRed(), leaves.getMaxColor().getRed());
            int green = (int) Mth.lerp(value, leaves.getMinColor().getGreen(), leaves.getMaxColor().getGreen());
            int blue = (int) Mth.lerp(value, leaves.getMinColor().getBlue(), leaves.getMaxColor().getBlue());
            return red << 16 | green << 8 | blue;
        }, SOULWOOD_LEAVES.get(), HANGING_SOULWOOD_LEAVES.get());

        event.register((s, l, p, c) -> {
            var spiritType = MalumSpiritType.getSpiritType(s.getValue(ManaMoteBlock.SPIRIT_TYPE));
            var color  = spiritType.getPrimaryColor();
            int red = color.getRed();
            int green = color.getGreen();
            int blue = color.getBlue();
            return red << 16 | green << 8 | blue;
        }, SPIRIT_MOTE.get());
    }
}
