package com.sammy.malum.common.block.curiosities.fluid;

import com.sammy.malum.registry.common.MalumDataMaps;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import team.lodestar.lodestone.modules.toolkit.block.LodestoneEntityBlock;

import javax.annotation.Nullable;
import java.util.List;

import static net.minecraft.world.level.block.LayeredCauldronBlock.LEVEL;

@SuppressWarnings({"deprecation", "NullableProblems"})
public class ArcaneTapBlock extends Block {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public ArcaneTapBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        var state = this.defaultBlockState();
        var level = context.getLevel();
        var pos = context.getClickedPos();
        var nearestDirections = context.getNearestLookingDirections();

        for (Direction direction : nearestDirections) {
            if (!direction.getAxis().isHorizontal()) {
                continue;
            }
            state = state.setValue(FACING, direction);
            if (state.canSurvive(level, pos)) {
                return state;
            }
        }

        return null;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        var direction = state.getValue(FACING);
        var attachedTo = pos.relative(direction);
        var stateBehind = level.getBlockState(attachedTo);
        return MultifaceBlock.canAttachTo(level, direction, attachedTo, stateBehind) || stateBehind.getBlock() instanceof AbstractCauldronBlock;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        if (facing == state.getValue(FACING) && !state.canSurvive(level, currentPos)) {
            return Blocks.AIR.defaultBlockState();
        }
        return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }


    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        var cauldronPos = getCauldronPos(level, pos);
        if (cauldronPos == null) {
            return;
        }
        var cauldronState = level.getBlockState(cauldronPos);
        var direction = state.getValue(FACING);
        var attachedTo = level.getBlockState(pos.relative(direction));
        var tappingData = attachedTo.getBlockHolder().getData(MalumDataMaps.FLUID_TAPPING);
        if (tappingData == null) {
            return;
        }

        if (random.nextFloat() < tappingData.chance()) {
            Holder<Block> holder = tappingData.filledCauldron();
            var resultingCauldron = holder.value();

            if (cauldronState.is(Blocks.CAULDRON)) {
                var resultingState = resultingCauldron.defaultBlockState();
                level.setBlockAndUpdate(cauldronPos, resultingState);
                level.gameEvent(GameEvent.BLOCK_CHANGE, cauldronPos, GameEvent.Context.of(resultingState));
            }
            else if (cauldronState.is(holder)) {
                if (!(cauldronState.getBlock() instanceof SapFilledCauldronBlock cauldronBlock)) {
                    return;
                }
                if (cauldronBlock.isFull(cauldronState)) {
                    return;
                }
                var resultingState = resultingCauldron.defaultBlockState().setValue(SapFilledCauldronBlock.LEVEL, cauldronState.getValue(SapFilledCauldronBlock.LEVEL) + 1);

                level.setBlockAndUpdate(cauldronPos, resultingState);
                level.gameEvent(GameEvent.BLOCK_CHANGE, cauldronPos, GameEvent.Context.of(resultingState));
            }
        }
    }

    public static BlockPos getCauldronPos(Level level, BlockPos tapPos) {
        var mutable = tapPos.mutable();
        for (int i = 0; i < 4; i++) {
            mutable.move(Direction.DOWN);
            var state = level.getBlockState(mutable);
            if (state.getBlock() instanceof AbstractCauldronBlock) {
                return mutable.immutable();
            }
            if (state.isCollisionShapeFullBlock(level, mutable)) {
                return null;
            }
        }
        return null;
    }
}