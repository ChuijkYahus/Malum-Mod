package com.sammy.malum.common.block.curiosities.ritual_plinth;

import com.sammy.malum.registry.common.block.*;
import net.minecraft.core.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import team.lodestar.lodestone.modules.toolkit.blockentity.*;

public class RitualPlinthBlockEntity extends LodestoneBlockEntity {

    public RitualPlinthBlockEntity(LodestoneBlockEntityType<? extends RitualPlinthBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public RitualPlinthBlockEntity(BlockPos pos, BlockState state) {
        super(MalumBlockEntities.RITUAL_PLINTH.get(), pos, state);
    }
}
