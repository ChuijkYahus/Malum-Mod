package com.sammy.malum.common.block.curiosities.totem.anchor;

import com.sammy.malum.core.systems.spirit.*;
import net.minecraft.core.*;
import net.minecraft.world.item.context.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import team.lodestar.lodestone.systems.block.*;

public class RiteAnchorBlock extends LodestoneEntityBlock<RiteAnchorBlockEntity> {

    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    public RiteAnchorBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getNearestLookingDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}