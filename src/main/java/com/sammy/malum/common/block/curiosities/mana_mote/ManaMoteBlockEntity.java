package com.sammy.malum.common.block.curiosities.mana_mote;

import com.sammy.malum.registry.common.block.MalumBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import team.lodestar.lodestone.systems.blockentity.LodestoneBlockEntity;

public class ManaMoteBlockEntity extends LodestoneBlockEntity {
    public ManaMoteBlockEntity(BlockPos pos, BlockState state) {
        super(MalumBlockEntities.MANA_MOTE.get(), pos, state);
    }
}
