package com.sammy.malum.common.block.curiosities.obelisk.rite_pylon;

import com.sammy.malum.common.block.curiosities.obelisk.*;
import com.sammy.malum.registry.common.block.*;
import net.minecraft.core.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.phys.shapes.*;

import java.util.function.*;

public class ArcanaPylonComponentBlock extends ObeliskComponentBlock {
    private static final VoxelShape SHAPE = makeShape();

    public ArcanaPylonComponentBlock(Properties properties, Supplier<Item> cloneStack) {
        super(properties, cloneStack);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    private static VoxelShape makeShape(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.1875, 0, 0.1875, 0.8125, 0.625, 0.8125), BooleanOp.OR);

        return shape;
    }
}