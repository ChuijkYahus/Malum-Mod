package com.sammy.malum.common.block.curiosities.redstone.wavecharger;

import com.sammy.malum.common.block.curiosities.redstone.SpiritDiodeBlockEntity;
import com.sammy.malum.registry.common.block.MalumBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class WaveChargerBlockEntity extends SpiritDiodeBlockEntity {

    public WaveChargerBlockEntity(BlockPos pos, BlockState state) {
        super(MalumBlockEntities.WAVECHARGER.get(), pos, state);
    }
}
