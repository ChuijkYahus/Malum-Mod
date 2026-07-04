package com.sammy.malum.common.block.curiosities.artifice.elemental_artifice;

import com.sammy.malum.common.block.curiosities.artifice.elemental_artifice.wind_tunnel.WindTunnelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public abstract class CaptureCompatibleArtificeBlock<T extends ElementalArtificeBlockEntity> extends ElementalArtificeBlock<T> {

    public static final BooleanProperty CAPTURED = BooleanProperty.create("captured");

    public CaptureCompatibleArtificeBlock(Properties properties) {
        super(properties);
    }

    public boolean canCapture(Level level, BlockState state, BlockPos pos, BlockState connectedTo, BlockPos connectedAt) {

        var opposite = connectedTo.getValue(WindTunnelBlock.FACING).getOpposite();
        return !opposite.equals(state.getValue(WindTunnelBlock.FACING));
    }

    public abstract void activate(Level level, BlockPos pos, boolean powered);

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(CAPTURED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        var state = super.getStateForPlacement(context);
        if (state == null) {
            return null;
        }
        var level = context.getLevel();
        var pos = context.getClickedPos();
        var direction = context.getClickedFace();
        var opposite = direction.getOpposite();
        var connectedAt = pos.relative(opposite);
        var connectedTo = level.getBlockState(connectedAt);
        if (canCapture(level, state, pos, connectedTo, connectedAt)) {
            state = state.setValue(FACING, opposite).setValue(CAPTURED, true);
        }

        boolean powered = level.hasNeighborSignal(pos);
        level.scheduleTick(pos, this, 2);
        return state.setValue(POWERED, powered);
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean isMoving) {
        super.neighborChanged(state, level, pos, neighborBlock, neighborPos, isMoving);
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
            var direction = state.getValue(FACING);
            var connectedAt = pos.relative(direction);
            if (neighborPos.equals(connectedAt)) {
                var connectedTo = level.getBlockState(connectedAt);
                var value = state.getValue(CAPTURED);
                if (value != canCapture(level, state, pos, connectedTo, connectedAt)) {
                    level.setBlock(pos, state.cycle(CAPTURED), 2);
                }
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
}