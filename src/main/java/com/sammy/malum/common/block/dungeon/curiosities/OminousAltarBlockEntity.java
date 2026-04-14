package com.sammy.malum.common.block.dungeon.curiosities;

import com.sammy.malum.common.block.curiosities.crafting.spirit_altar.*;
import com.sammy.malum.registry.common.block.*;
import net.minecraft.core.*;
import net.minecraft.world.level.block.state.*;

public class OminousAltarBlockEntity extends SpiritAltarBlockEntity {
    public OminousAltarBlockEntity(BlockPos pos, BlockState state) {
        super(MalumBlockEntities.OMINOUS_ALTAR.get(), pos, state);
    }
}