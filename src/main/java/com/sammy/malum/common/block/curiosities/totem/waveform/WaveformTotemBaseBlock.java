package com.sammy.malum.common.block.curiosities.totem.waveform;

import com.sammy.malum.common.block.curiosities.totem.*;
import net.minecraft.core.*;
import net.minecraft.server.level.*;
import net.minecraft.util.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;

public class WaveformTotemBaseBlock<T extends WaveformTotemBaseBlockEntity> extends TotemBaseBlock<T> {

    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    public WaveformTotemBaseBlock(Properties properties, boolean corrupted) {
        super(properties, corrupted);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        super.neighborChanged(state, level, pos, block, fromPos, isMoving);
        if (level instanceof ServerLevel serverLevel) {
            boolean flag = state.getValue(POWERED);
            if (flag != level.hasNeighborSignal(pos)) {
                if (flag) {
                    level.scheduleTick(pos, this, 4);
                } else {
                    level.setBlock(pos, state.cycle(POWERED), 2);
                    if (level.getBlockEntity(pos) instanceof WaveformTotemBaseBlockEntity totemBase) {
                        if (totemBase.canTriggerRite()) {
                            totemBase.triggerRite(serverLevel);
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getValue(POWERED) && !level.hasNeighborSignal(pos)) {
            level.setBlock(pos, state.cycle(POWERED), 2);
        }
    }
}
