package com.sammy.malum.common.block.dungeon;

import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import team.lodestar.lodestone.modules.toolkit.block.LodestoneHorizontalBlock;

public class MeditatingEffigyBlock extends LodestoneHorizontalBlock {

    private static final VoxelShape SHAPE = Block.box(4.0, 0.0, 4.0, 12.0, 9.0, 12.0);

    public MeditatingEffigyBlock(Properties builder) {
        super(builder);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}
