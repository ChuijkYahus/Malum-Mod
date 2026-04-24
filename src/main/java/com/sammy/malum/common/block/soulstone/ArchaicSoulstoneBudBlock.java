package com.sammy.malum.common.block.soulstone;

import com.sammy.malum.registry.common.MalumTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import team.lodestar.lodestone.modules.toolkit.block.*;

@SuppressWarnings("NullableProblems")
public class ArchaicSoulstoneBudBlock extends LodestoneDirectionalBlock {

    public static final IntegerProperty STAGE = IntegerProperty.create("stage", 0, 2);

    public ArchaicSoulstoneBudBlock(Properties builder) {
        super(builder);
        registerDefaultState(defaultBlockState().setValue(STAGE, 0));
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        return !state.canSurvive(level, currentPos)
                ? Blocks.AIR.defaultBlockState()
                : super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        int stage = state.getValue(STAGE);
        var offset = state.getOffset(level, pos);
        return SoulstoneBudCommons.SHAPES[stage].getShape(state).move(offset.x, offset.y, offset.z);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(STAGE);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        var attachedPos = SoulstoneBudCommons.getAttachedPos(state, pos);
        if (!Block.canSupportCenter(level, attachedPos, state.getValue(FACING))) {
            return false;
        }
        var attachedState = level.getBlockState(attachedPos);
        return attachedState.is(MalumTags.Blocks.NATURAL_SOULSTONE_BUD_SURFACE);
    }
}