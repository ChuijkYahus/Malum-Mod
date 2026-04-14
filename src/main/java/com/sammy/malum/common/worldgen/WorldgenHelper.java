package com.sammy.malum.common.worldgen;

import com.google.common.collect.*;
import com.sammy.malum.common.block.flora.wood.MalumLeavesBlock;
import net.minecraft.core.*;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.levelgen.synth.ImprovedNoise;

import java.util.*;

public class WorldgenHelper {

    public static <T> List<T> shuffle(Collection<T> collection, RandomSource random) {
        List<T> list = new ArrayList<>(collection);
        for (int i = list.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            T temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }
        return list;
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

    public static double getNoise(ImprovedNoise noiseSampler, int blockX, int blockZ, float noiseFrequency, boolean blur) {
        return getNoise(noiseSampler, blockX, blockZ, 0, noiseFrequency, blur);
    }

    public static double getNoise(ImprovedNoise noiseSampler, int blockX, int blockZ, int offset, float noiseFrequency, boolean blur) {
        if (blur) {
            return getBlurredNoise(noiseSampler, blockX, blockZ, offset, noiseFrequency);
        }
        return noiseSampler.noise((blockX + offset) * noiseFrequency, 0, (blockZ + offset) * noiseFrequency) + 1;
    }

    public static double getBlurredNoise(ImprovedNoise noiseSampler, int blockX, int blockZ, float noiseFrequency) {
        return getBlurredNoise(noiseSampler, blockX, blockZ, 0, 1, noiseFrequency);
    }

    public static double getBlurredNoise(ImprovedNoise noiseSampler, int blockX, int blockZ, int offset, float noiseFrequency) {
        return getBlurredNoise(noiseSampler, blockX, blockZ, offset, 1, noiseFrequency);
    }

    public static double getBlurredNoise(ImprovedNoise noiseSampler, int blockX, int blockZ, int offset, int blurRadius, float noiseFrequency) {
        double noise = 0;
        int count = 0;
        for (int x = -blurRadius; x <= blurRadius; x++) {
            for (int z = -blurRadius; z <= blurRadius; z++) {
                noise += getNoise(noiseSampler, blockX, blockZ, offset, noiseFrequency);
                count++;
            }
        }
        return noise / count;
    }

    public static double getNoise(ImprovedNoise noiseSampler, BlockPos pos, float noiseFrequency) {
        return getNoise(noiseSampler, pos.getX(), pos.getZ(), 0, noiseFrequency);
    }

    public static double getNoise(ImprovedNoise noiseSampler, BlockPos pos, int offset, float noiseFrequency) {
        return getNoise(noiseSampler, pos.getX(), pos.getZ(), offset, noiseFrequency);
    }

    public static double getNoise(ImprovedNoise noiseSampler, int blockX, int blockZ, float noiseFrequency) {
        return getNoise(noiseSampler, blockX, blockZ, 0, noiseFrequency);
    }

    public static double getNoise(ImprovedNoise noiseSampler, int blockX, int blockZ, int offset, float noiseFrequency) {
        return (noiseSampler.noise((blockX + offset) * noiseFrequency, 0, (blockZ + offset) * noiseFrequency) + 1) * 0.5f;
    }
}