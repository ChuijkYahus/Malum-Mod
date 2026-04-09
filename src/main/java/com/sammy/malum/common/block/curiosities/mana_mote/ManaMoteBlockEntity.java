package com.sammy.malum.common.block.curiosities.mana_mote;

import com.sammy.malum.registry.common.content.block.MalumBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import team.lodestar.lodestone.modules.toolkit.blockentity.*;

public class ManaMoteBlockEntity extends LodestoneBlockEntity {
    public ManaMoteBlockEntity(BlockPos pos, BlockState state) {
        super(MalumBlockEntities.MANA_MOTE.get(), pos, state);
    }
}
