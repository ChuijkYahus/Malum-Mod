package com.sammy.malum.common.block.decor;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.TintedGlassBlock;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.state.BlockState;

public class NullGlassBlock extends TintedGlassBlock {
    public NullGlassBlock(Properties p_53640_) {
        super(p_53640_);
    }

    @Override
    public boolean skipRendering(BlockState pState, BlockState pAdjacentBlockState, Direction pSide) {
        return pAdjacentBlockState.getBlock() instanceof NullGlassBlock || super.skipRendering(pState, pAdjacentBlockState, pSide);
    }
}
