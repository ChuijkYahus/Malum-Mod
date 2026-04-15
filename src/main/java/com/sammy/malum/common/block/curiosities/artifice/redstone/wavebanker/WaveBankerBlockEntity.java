package com.sammy.malum.common.block.curiosities.artifice.redstone.wavebanker;

import com.sammy.malum.common.block.curiosities.artifice.redstone.SpiritDiodeBlockEntity;
import com.sammy.malum.registry.common.block.MalumBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class WaveBankerBlockEntity extends SpiritDiodeBlockEntity {

    public WaveBankerBlockEntity(BlockPos pos, BlockState state) {
        super(MalumBlockEntities.WAVEBANKER.get(), pos, state);
    }
}
