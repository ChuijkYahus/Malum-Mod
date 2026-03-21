package com.sammy.malum.common.block.curiosities.soul_brazier;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import team.lodestar.lodestone.modules.toolkit.block.*;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.FACING;

public class SoulBrazierBlock<T extends SoulBrazierBlockEntity> extends LodestoneEntityBlock<T> {

    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    public SoulBrazierBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(stateDefinition.any().setValue(LIT, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT);
        super.createBlockStateDefinition(builder);
    }
}