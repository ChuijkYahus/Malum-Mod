package com.sammy.malum.common.block.dungeon;

import com.sammy.malum.common.block.decor.ColumnBlock;
import com.sammy.malum.common.item.spirit.SpiritShardItem;
import com.sammy.malum.common.worldgen.blight.BlightFeature;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class ColumnarFleshBlock extends ColumnBlock implements BonemealableBlock {

    public ColumnarFleshBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
        return blockState.getValue(AXIS).equals(Direction.Axis.Y);
    }

    @Override
    public boolean isBonemealSuccess(Level pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        int addedHeight = pRandom.nextInt(2, 4);
        BlockPos.MutableBlockPos mutable = pPos.mutable();
        for (int i = 0; i < addedHeight; i++) {
            mutable.move(Direction.UP);
            pLevel.setBlockAndUpdate(mutable, pState.setValue(BOTTOM, true));
        }
    }
}