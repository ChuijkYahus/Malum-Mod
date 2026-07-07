package com.sammy.malum.common.block.curiosities.artifice.elemental_artifice.base;

import com.sammy.malum.common.block.curiosities.artifice.elemental_artifice.aerial.GustIgniterBlockEntity;
import com.sammy.malum.core.handlers.WindTunnelHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public abstract class PrimaryArtificeBlock<T extends ElementalArtificeBlockEntity> extends ElementalArtificeBlock<T> {

    public static final BooleanProperty CAPTURED = BooleanProperty.create("captured");

    public PrimaryArtificeBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(CAPTURED, false));
    }

    public boolean canCapture(Level level, BlockState state, BlockPos pos, BlockState connectedTo, BlockPos connectedAt) {
        var opposite = connectedTo.getValue(FACING).getOpposite();
        return !opposite.equals(state.getValue(FACING));
    }

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

        level.scheduleTick(pos, this, 2);
        return state.setValue(POWERED, level.hasNeighborSignal(pos));
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean isMoving) {
        super.neighborChanged(state, level, pos, neighborBlock, neighborPos, isMoving);
        if (level instanceof ServerLevel serverLevel) {
            respondToImpulse(state, level, pos, neighborPos, serverLevel);
        }
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getValue(POWERED)) {
            if (!level.hasNeighborSignal(pos)) {
                state = state.cycle(POWERED);
                level.setBlock(pos, state, 2);
            }
        }
        receiveSignal(level, pos, state, state.getValue(POWERED));
    }

    private void respondToImpulse(BlockState state, Level level, BlockPos pos, BlockPos neighborPos, ServerLevel serverLevel) {
        boolean isPowered = state.getValue(POWERED);
        boolean hasSignal = level.hasNeighborSignal(pos);
        if (isPowered != hasSignal) {
            if (isPowered) {
                level.scheduleTick(pos, this, 4);
            } else {
                state = state.cycle(POWERED);
                level.setBlock(pos, state, 2);
                receiveSignal(serverLevel, pos, state, true);
            }
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

    public void receiveSignal(ServerLevel level, BlockPos pos, BlockState state, boolean powered) {
        if (!(level.getBlockEntity(pos) instanceof PrimaryArtificeBlockEntity blockEntity)) {
            return;
        }
        WindTunnelHandler.modifyComponents(level, blockEntity, state.getValue(OPEN), powered);
        blockEntity.activate(level, powered);
    }
}