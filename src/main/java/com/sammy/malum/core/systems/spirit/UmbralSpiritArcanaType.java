package com.sammy.malum.core.systems.spirit;

import com.sammy.malum.common.item.spirit.*;
import com.sammy.malum.core.systems.spirit.type.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.*;

public class UmbralSpiritArcanaType extends SpiritArcanaType {

    public static final int INVERT_COLOR = 0x4D616C6D; // M = chr 4D, a = chr 61, l = chr 6C, m = chr 6D

    public UmbralSpiritArcanaType(SpiritColorProperties colorProperties, DeferredHolder<Item, SpiritShardItem> spiritShard) {
        super(colorProperties, spiritShard);
    }

    @Override
    public TextColor getTextColor(boolean isTooltip) {
        return TextColor.fromRgb(INVERT_COLOR);
    }
}
