package com.sammy.malum.common.block.curiosities.obelisk.rite_pylon;

import com.sammy.malum.common.block.curiosities.obelisk.*;
import net.minecraft.core.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.phys.shapes.*;

public class ArcanaPylonComponentBlock extends ObeliskComponentBlock {
    private static final VoxelShape SHAPE = makeShape();

    public ArcanaPylonComponentBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    private static VoxelShape makeShape() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.1875, 0, 0.1875, 0.8125, 0.625, 0.8125), BooleanOp.OR);

        return shape;
    }
}