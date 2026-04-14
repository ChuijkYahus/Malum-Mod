package com.sammy.malum.common.block.curiosities.artifice.gust_igniter;

import com.sammy.malum.common.block.curiosities.artifice.gust_igniter.wind_tunnel.*;
import net.minecraft.core.*;
import net.minecraft.server.level.*;
import net.minecraft.util.*;
import net.minecraft.world.item.context.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;

public class GustIgniterBlock extends AbstractGustGizmoBlock<GustIgniterBlockEntity> {

    public GustIgniterBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        var state = super.getStateForPlacement(context);
        if (state == null) {
            return null;
        }
        var level = context.getLevel();
        var pos = context.getClickedPos();
        var direction = context.getClickedFace().getOpposite();
        if (level.getBlockEntity(pos.relative(direction)) instanceof WindTunnelBlockEntity) {
            state = state.setValue(FACING, direction);
        }

        boolean powered = level.hasNeighborSignal(pos);
        level.scheduleTick(pos, this, 2);
        return state.setValue(POWERED, powered);
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        super.neighborChanged(state, level, pos, block, fromPos, isMoving);
        if (!level.isClientSide) {
            boolean flag = state.getValue(POWERED);
            if (flag != level.hasNeighborSignal(pos)) {
                if (flag) {
                    level.scheduleTick(pos, this, 4);
                } else {
                    activate(level, pos, true);
                    level.setBlock(pos, state.cycle(POWERED), 2);
                }
            }
            if (flag) {
                activate(level, pos, true);
            }
        }
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getValue(POWERED)) {
            if (!level.hasNeighborSignal(pos)) {
                state = state.cycle(POWERED);
                level.setBlock(pos, state, 2);
            }
            activate(level, pos, state.getValue(POWERED));
        }
    }

    public static void activate(Level level, BlockPos pos, boolean powered) {
        if (!(level.getBlockEntity(pos) instanceof GustIgniterBlockEntity igniter)) {
            return;
        }
        igniter.activate(powered);
    }
}