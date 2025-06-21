package com.sammy.malum.common.worldgen.blight;

import com.google.common.collect.*;
import com.sammy.malum.common.block.blight.scarstone.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.block.*;
import net.minecraft.core.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.synth.*;
import net.minecraft.world.level.material.*;
import team.lodestar.lodestone.systems.worldgen.*;

import java.util.*;
import java.util.stream.*;

import static com.sammy.malum.common.block.blight.CreepingBlightBlock.BlightType.SOULWOOD_SPIKE;
import static com.sammy.malum.common.worldgen.blight.BlightFeature.fetchCoveringPositions;

public class ScarstoneFeature extends Feature<NoneFeatureConfiguration> {

    public ScarstoneFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        var pos = context.origin();
        generateScarstone(level, pos, 6).place(level);
        return true;
    }

    public static LodestoneWorldgenBuilder generateScarstone(WorldGenLevel level, BlockPos pos, int radius) {
        var random = level.getRandom();

        var builder = LodestoneWorldgenBuilder.create().addAdditionalPlacement(BlightFeature::cleanupFoliage);
        var scarstoneLayer = builder.createLayer();
        var floraLayer = builder.createLayer();

        List<BlockPos> scarredArea = fetchCoveringPositions(level, pos, radius);
        for (BlockPos blockPos : scarredArea) {
            BlockState state = level.getBlockState(blockPos);
            if (state.is(MalumTags.BlockTags.BLIGHT_REPLACEABLE) || state.is(MalumTags.BlockTags.BLIGHT)) {
                scarstoneLayer.add(blockPos, MalumBlocks.SCARSTONE.get()).setImportant();
            }
        }

        List<BlockPos> crystalPositions = fetchCoveringPositions(level, pos, radius-1);
        if (!crystalPositions.isEmpty()) {
            Collections.shuffle(crystalPositions);
            int floraCount = Math.min(random.nextInt(radius * 2 + 1, radius * 4 + 1), crystalPositions.size() - 1);
            for (BlockPos blockPos : crystalPositions) {
                BlockPos above = blockPos.above();
                BlockState state = level.getBlockState(above);
                if (!state.getFluidState().isEmpty()) {
                    continue;
                }
                if (!state.canBeReplaced()) {
                    continue;
                }
                Block block;
                if (random.nextFloat() < 0.7f) {
                    block = random.nextFloat() < 0.4f ? MalumBlocks.LARGE_STRANGE_CRYSTAL.get() : MalumBlocks.STRANGE_CRYSTAL.get();
                } else {
                    block = MalumBlocks.STRANGEROOT.get();
                }
                var entry = floraLayer.add(above, block).setImportant();
                if (block.equals(MalumBlocks.LARGE_STRANGE_CRYSTAL.get())) {
                    entry.addAdditionalPlacement((l, e) -> e.place(l, e.position().above(), e.blockState().setValue(LargeStrangeCrystalBlock.HALF, DoubleBlockHalf.UPPER)));
                }
                floraCount--;
                if (floraCount == 0) {
                    break;
                }
            }
        }
        return builder;
    }
    public static List<BlockPos> fetchCoveringPositions(ServerLevelAccessor level, BlockPos center, int radius) {
        return BlightFeature.fetchCoveringPositions(level, center, radius, ScarstoneFeature::canBeRemoved).stream().filter(
                        p -> {
                            BlockState above = level.getBlockState(p.above());
                            return above.canBeReplaced() || above.is(MalumTags.BlockTags.BLIGHT_REMOVABLE) || above.is(MalumTags.BlockTags.BLIGHTED_PLANTS);
                        })
                .collect(Collectors.toList());
    }
    public static boolean canBeRemoved(BlockState state) {
        return state.canBeReplaced();
    }
}