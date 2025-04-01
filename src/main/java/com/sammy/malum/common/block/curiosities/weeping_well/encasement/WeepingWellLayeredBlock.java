package com.sammy.malum.common.block.curiosities.weeping_well.encasement;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.PushReaction;

public class WeepingWellLayeredBlock extends HorizontalDirectionalBlock {
    public static final IntegerProperty LAYER = IntegerProperty.create("layer", 0, 4);

    MapCodec<WeepingWellLayeredBlock> CODEC = simpleCodec(WeepingWellLayeredBlock::new);

    public WeepingWellLayeredBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return CODEC;
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState pState) {
        return PushReaction.IGNORE;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
        pBuilder.add(LAYER);
    }
}
