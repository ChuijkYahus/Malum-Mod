package com.sammy.malum.common.block.flora.wood;

import com.sammy.malum.registry.common.MalumContent;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

import static com.sammy.malum.MalumMod.RANDOM;

@SuppressWarnings("NullableProblems")
public abstract class MalumLeavesBlock extends LeavesBlock implements StagedLeavesBlock {

    public MalumLeavesBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(getColorProperty(), 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(DISTANCE, PERSISTENT, getColorProperty(), WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = super.getStateForPlacement(context);
        if (state == null) {
            return null;
        }
        return state.setValue(getColorProperty(), 0);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hitResult) {
        if (stack.getItem().equals(MalumContent.Spirits.INFERNAL_SPIRIT.get())) {
            IntegerProperty color = getColorProperty();
            level.setBlockAndUpdate(pos, state.cycle(color));
            player.swing(handIn);
            player.playSound(SoundEvents.BLAZE_SHOOT, 1F, 1.5f + RANDOM.nextFloat() * 0.5f);
            return ItemInteractionResult.SUCCESS;
        }
        return super.useItemOn(stack, state, level, pos, player, handIn, hitResult);
    }
}