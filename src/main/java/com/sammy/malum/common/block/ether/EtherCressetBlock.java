package com.sammy.malum.common.block.ether;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

@SuppressWarnings({"deprecation", "NullableProblems"})
public class EtherCressetBlock<T extends EtherCressetBlockEntity> extends EtherBlock<T> {
    protected static final VoxelShape LOWER_SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 24.0D, 13.0D);
    protected static final VoxelShape UPPER_SHAPE = Block.box(3.0D, -16.0D, 3.0D, 13.0D, 8.0D, 13.0D);

    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;

    public EtherCressetBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED, false).setValue(HALF, DoubleBlockHalf.LOWER));
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HALF);
        super.createBlockStateDefinition(builder);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return state.getValue(HALF).equals(DoubleBlockHalf.UPPER) ? UPPER_SHAPE : LOWER_SHAPE;
    }

    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        var half = state.getValue(HALF);
        if (facing.getAxis() != Direction.Axis.Y || half == DoubleBlockHalf.LOWER != (facing == Direction.UP) || facingState.is(this) && facingState.getValue(HALF) != half) {
            return half == DoubleBlockHalf.LOWER && facing == Direction.DOWN && !state.canSurvive(level, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, level, currentPos, facingPos);
        } else {
            return Blocks.AIR.defaultBlockState();
        }
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos pos = context.getClickedPos();
        Level level = context.getLevel();
        if (pos.getY() >= level.getMaxBuildHeight() - 1) {
            return null;
        }
        if (!level.getBlockState(pos.above()).canBeReplaced(context)) {
            return null;
        }
        return super.getStateForPlacement(context);
    }

    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        var abovePos = pos.above();
        var upperState = defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER);
        level.setBlock(abovePos, DoublePlantBlock.copyWaterloggedFrom(level, abovePos, upperState), 3);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos belowPos = pos.below();
        if (state.getValue(HALF) != DoubleBlockHalf.UPPER) {
            return canSupportCenter(level, belowPos, Direction.UP);
        }
        BlockState belowState = level.getBlockState(belowPos);
        if (state.getBlock() != this) {
            return super.canSurvive(state, level, pos);
        }
        return belowState.is(this) && belowState.getValue(HALF) == DoubleBlockHalf.LOWER;
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (level.isClientSide) {
            return super.playerWillDestroy(level, pos, state, player);
        }
        if (player.isCreative()) {
            preventDropFromBottomPart(level, pos, state, player);
        } else {
            dropResources(state, level, pos, null, player, player.getMainHandItem());
        }

        return super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity te, ItemStack stack) {
        super.playerDestroy(level, player, pos, Blocks.AIR.defaultBlockState(), te, stack);
    }

    protected static void preventDropFromBottomPart(Level level, BlockPos pos, BlockState state, Player player) {
        var half = state.getValue(HALF);
        if (half == DoubleBlockHalf.LOWER) {
            return;
        }
        var lowerPos = pos.below();
        var lowerState = level.getBlockState(lowerPos);
        if (!lowerState.is(state.getBlock())) {
            return;
        }
        if (lowerState.getValue(HALF) == DoubleBlockHalf.UPPER) {
            return;
        }
        var newState = lowerState.getFluidState().is(Fluids.WATER) ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState();
        level.setBlock(lowerPos, newState, 35);
        level.levelEvent(player, 2001, lowerPos, Block.getId(lowerState));
    }

    @Override
    protected long getSeed(BlockState state, BlockPos pos) {
        return Mth.getSeed(pos.getX(), pos.below(state.getValue(HALF) == DoubleBlockHalf.LOWER ? 0 : 1).getY(), pos.getZ());
    }
}