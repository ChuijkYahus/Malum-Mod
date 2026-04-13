package com.sammy.malum.common.block.flora.wood;

import com.sammy.malum.registry.common.MalumContent;
import net.minecraft.core.*;
import net.minecraft.sounds.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.phys.*;
import net.minecraft.world.phys.shapes.*;

import java.awt.*;

import static com.sammy.malum.MalumMod.RANDOM;

@SuppressWarnings("NullableProblems")
public abstract class MalumHangingLeavesBlock extends Block implements SimpleWaterloggedBlock, StagedLeavesBlock {

    protected static final VoxelShape SHAPE = Block.box(3.0D, 3.0D, 3.0D, 13.0D, 16.0D, 13.0D);

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public MalumHangingLeavesBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(getColorProperty(), 0).setValue(WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(getColorProperty(), WATERLOGGED);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction pFacing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos pFacingPos) {
        if (pFacing.equals(Direction.UP)) {
            var color = getColorProperty();
            if (facingState.hasProperty(color)) {
                return super.updateShape(state.setValue(color, facingState.getValue(color)), pFacing, facingState, level, currentPos, pFacingPos);
            }
        }
        return !state.canSurvive(level, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, pFacing, facingState, level, currentPos, pFacingPos);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hitResult) {
        if (stack.getItem().equals(MalumContent.Spirits.INFERNAL_SPIRIT.get())) {
            var color = getColorProperty();
            level.setBlockAndUpdate(pos, state.cycle(color));
            player.swing(handIn);
            player.playSound(SoundEvents.BLAZE_SHOOT, 1F, 1.5f + RANDOM.nextFloat() * 0.5f);
            return ItemInteractionResult.SUCCESS;
        }
        return super.useItemOn(stack, state, level, pos, player, handIn, hitResult);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        var block = level.getBlockState(pos.above()).getBlock();
        return block instanceof LeavesBlock;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext pContext) {
        return SHAPE;
    }
}