package com.sammy.malum.common.worldgen;

import com.google.common.collect.*;
import com.sammy.malum.common.block.flora.wood.MalumLeavesBlock;
import net.minecraft.core.*;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.state.*;

import java.util.*;

public class WorldgenHelper {

    public static <T> List<T> shuffle(Collection<T> collection, RandomSource random) {
        return collection.stream().sorted((a, b) -> random.nextIntBetweenInclusive(-1, 0)).toList();
    }

    public static void updateLeaves(LevelAccessor pLevel, Collection<BlockPos> logPositions) {
        List<Set<BlockPos>> list = Lists.newArrayList();
        for (int j = 0; j < 6; ++j) {
            list.add(Sets.newHashSet());
        }

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        for (BlockPos pos : Lists.newArrayList(logPositions)) {
            for (Direction direction : Direction.values()) {
                mutable.setWithOffset(pos, direction);
                if (!logPositions.contains(mutable)) {
                    BlockState blockstate = pLevel.getBlockState(mutable);
                    if (blockstate.hasProperty(MalumLeavesBlock.DISTANCE)) {
                        list.getFirst().add(mutable.immutable());
                        pLevel.setBlock(mutable, blockstate.setValue(MalumLeavesBlock.DISTANCE, 1), 19);
                    }
                }
            }
        }

        for (int l = 1; l < 6; ++l) {
            Set<BlockPos> set = list.get(l - 1);
            Set<BlockPos> set1 = list.get(l);

            for (BlockPos pos : set) {
                for (Direction direction1 : Direction.values()) {
                    mutable.setWithOffset(pos, direction1);
                    if (!set.contains(mutable) && !set1.contains(mutable)) {
                        BlockState blockstate1 = pLevel.getBlockState(mutable);
                        if (blockstate1.hasProperty(MalumLeavesBlock.DISTANCE)) {
                            int k = blockstate1.getValue(MalumLeavesBlock.DISTANCE);
                            if (k > l + 1) {
                                BlockState blockstate2 = blockstate1.setValue(MalumLeavesBlock.DISTANCE, l + 1);
                                pLevel.setBlock(mutable, blockstate2, 19);
                                set1.add(mutable.immutable());
                            }
                        }
                    }
                }
            }
        }
    }
}