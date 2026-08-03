package com.sammy.malum.common.block.dungeon;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import team.lodestar.lodestone.modules.toolkit.block.LodestoneHorizontalBlock;
import team.lodestar.lodestone.modules.toolkit.block.VoxelShapeRotator;

public class MeditatingEffigyBlock extends LodestoneHorizontalBlock {

    private static final VoxelShapeRotator SHAPE = new VoxelShapeRotator(box(4.0, 0.0, 5.0, 12.0, 9.0, 11.0));

    public MeditatingEffigyBlock(Properties builder) {
        super(builder);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE.getShape(state);
    }
}
