package com.sammy.malum.common.block.storage.pedestal;

import net.minecraft.core.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.phys.shapes.*;

import java.util.stream.*;

public class DecoratedItemPedestalBlock<T extends ItemPedestalBlockEntity> extends ItemPedestalBlock<T>{

    public static final VoxelShape SHAPE = Stream.of(
            Block.box(4, 0, 4, 12, 4, 12),
            Block.box(5, 4, 5, 11, 11, 11),
            Block.box(4, 12, 4, 12, 15, 12),
            Block.box(7, 10, 3, 9, 15, 13),
            Block.box(3, 10, 7, 13, 15, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public DecoratedItemPedestalBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}
