package com.sammy.malum.common.block.curiosities.totem.spreader;

import com.mojang.serialization.*;
import com.sammy.malum.common.block.curiosities.totem.unweaver.*;
import net.minecraft.core.*;
import net.minecraft.world.item.context.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import team.lodestar.lodestone.modules.toolkit.block.*;

public class RiteSpreaderBlock extends LodestoneEntityBlock<RiteSpreaderBlockEntity> {

    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    public RiteSpreaderBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        var level = context.getLevel();
        var pos = context.getClickedPos().relative(context.getClickedFace().getOpposite());
        var state = level.getBlockState(pos);
        if (state.getBlock() instanceof RiteSpreaderBlock) {
            return state;
        }
        var direction = context.getNearestLookingDirection();
        if (context.getPlayer() == null || !context.getPlayer().isShiftKeyDown()) {
            direction = direction.getOpposite();
        }
        return this.defaultBlockState().setValue(FACING, direction);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.setValue(FACING, mirrorIn.mirror(state.getValue(FACING)));
    }
}