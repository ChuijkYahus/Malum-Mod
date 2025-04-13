package com.sammy.malum.common.block.storage.pedestal;

import net.minecraft.core.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.phys.shapes.*;

import java.util.stream.*;

public class DecoratedItemPedestalBlock<T extends ItemPedestalBlockEntity> extends ItemPedestalBlock<T>{

    public static final VoxelShape SHAPE = Stream.of(
            Block.box(4, 0, 4, 12, 3, 12),
            Block.box(5, 3, 5, 11, 10, 11),
            Block.box(4, 11, 4, 12, 14, 12),
            Block.box(7, 9, 3, 9, 14, 13),
            Block.box(3, 9, 7, 13, 14, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public DecoratedItemPedestalBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}
