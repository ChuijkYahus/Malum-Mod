package com.sammy.malum.common.block.dungeon.curiosities;

import com.sammy.malum.common.block.curiosities.obelisk.*;
import com.sammy.malum.common.block.curiosities.spirit_altar.*;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.registry.common.block.*;
import net.minecraft.core.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.phys.*;
import team.lodestar.lodestone.modules.toolkit.multiblock.*;

import java.util.function.*;

public class OminousAltarBlockEntity extends SpiritAltarBlockEntity {
    public OminousAltarBlockEntity(BlockPos pos, BlockState state) {
        super(MalumBlockEntities.OMINOUS_ALTAR.get(), pos, state);
    }
}