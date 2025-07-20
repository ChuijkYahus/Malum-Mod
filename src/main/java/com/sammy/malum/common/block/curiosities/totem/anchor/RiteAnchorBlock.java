package com.sammy.malum.common.block.curiosities.totem.anchor;

import com.sammy.malum.core.systems.spirit.*;
import net.minecraft.core.*;
import net.minecraft.world.item.context.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import team.lodestar.lodestone.systems.block.*;

public class RiteAnchorBlock extends LodestoneEntityBlock<RiteAnchorBlockEntity> {

    public static final DirectionProperty HORIZONTAL_FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final SpiritTypeProperty SPIRIT_TYPE = SpiritTypeProperty.SPIRIT_TYPE;

    public RiteAnchorBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(HORIZONTAL_FACING, Direction.NORTH).setValue(SPIRIT_TYPE, "sacred"));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(HORIZONTAL_FACING, context.getHorizontalDirection());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HORIZONTAL_FACING);
        builder.add(SPIRIT_TYPE);
    }
}