package com.sammy.malum.common.worldgen.blight;

import com.google.common.collect.*;
import com.sammy.malum.common.block.blight.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.block.*;
import net.minecraft.core.*;
import net.minecraft.util.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.synth.*;
import net.minecraft.world.level.material.*;
import team.lodestar.lodestone.systems.easing.*;
import team.lodestar.lodestone.systems.worldgen.*;

import java.util.*;
import java.util.function.*;

import static team.lodestar.lodestone.systems.worldgen.LodestoneBlockFiller.create;

public class BlightFeature extends Feature<NoneFeatureConfiguration> {

    private static final PerlinSimplexNoise COVERING_NOISE = new PerlinSimplexNoise(new WorldgenRandom(new LegacyRandomSource(1234L)), ImmutableList.of(0));
    public static final LodestoneBlockFiller.LodestoneLayerToken BLIGHT = new LodestoneBlockFiller.LodestoneLayerToken();
    public static final LodestoneBlockFiller.LodestoneLayerToken COVERING = new LodestoneBlockFiller.LodestoneLayerToken();

    public BlightFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        var pos = context.origin();
        generateBlight(level, pos, 8).fill(level);
        return true;
    }

    public static LodestoneBlockFiller generateBlight(WorldGenLevel level, BlockPos pos, int radius) {
        var random = level.getRandom();
        var filler = new LodestoneBlockFiller().addLayers(BLIGHT, COVERING);
        var blightLayer = filler.getLayer(BLIGHT);
        var coveringLayer = filler.getLayer(COVERING);
        var blockEntry = create(MalumBlocks.BLIGHTED_EARTH.get().defaultBlockState()).setForcePlace().build();

        List<BlockPos> blightedArea = fetchCoveringPositions(level, pos, radius);
        for (BlockPos blockPos : blightedArea) {
            BlockState state = level.getBlockState(blockPos);
            if (state.is(MalumTags.BlockTags.BLIGHT_REPLACEABLE)) {
                blightLayer.put(blockPos, blockEntry);
                for (int i = 0; i < 4; i++) {
                    BlockPos above = blockPos.above(i+1);
                    BlockState aboveState = level.getBlockState(above);
                    if (aboveState.getFluidState().isEmpty() && aboveState.is(MalumTags.BlockTags.BLIGHT_REMOVABLE)) {
                        var air = create(Blocks.AIR.defaultBlockState()).setForcePlace();
                        coveringLayer.put(above, air);
                    }
                }
            }
        }

        ArrayList<BlockPos> floraPositions = new ArrayList<>(blightedArea);
        if (!floraPositions.isEmpty()) {
            Collections.shuffle(floraPositions);
            int floraCount = Math.min(random.nextInt(1, radius * 4 + 1), floraPositions.size()-1);
            boolean hasSoulwood = false;
            for (int i = 0; i < floraCount; i++) {
                BlockPos above = floraPositions.get(i).above();
                BlockState state = level.getBlockState(above);
                if (state.getFluidState().isEmpty() && state.canBeReplaced() && !state.is(MalumTags.BlockTags.BLIGHTED_PLANTS)) {
                    Block block;
                    if (!hasSoulwood && random.nextFloat() < 0.1f) {
                        block = MalumBlocks.SOULWOOD_GROWTH.get();
                        hasSoulwood = true;
                    }
                    else if (random.nextFloat() < 0.4f) {
                        block = random.nextFloat() < 0.2f ? MalumBlocks.BLIGHTPEARL.get() : MalumBlocks.BLIGHTROOT.get();
                    } else {
                        block = MalumBlocks.BLIGHTED_GROWTH.get();
                    }
                    coveringLayer.put(above, create(block.defaultBlockState()));
                }
            }
        }
        List<BlockPos> coveringArea = fetchCoveringPositions(level, pos, radius+3);
        if (!coveringArea.isEmpty()) {
            Collections.shuffle(coveringArea);
            int coveringCount = Math.min(random.nextInt(1, radius * 8 + 1), coveringArea.size()-1);
            for (BlockPos blockPos : coveringArea) {
                BlockState state = level.getBlockState(blockPos);
                if (state.is(MalumTags.BlockTags.BLIGHT_REPLACEABLE) && !blightLayer.containsKey(blockPos)) {
                    var above = blockPos.above();
                    var aboveState = level.getBlockState(above);
                    boolean isWaterLogged = aboveState.getFluidState().is(Fluids.WATER);
                    var coveringEntry = create(MalumBlocks.BLIGHT.get().defaultBlockState()
                            .setValue(MultifaceBlock.getFaceProperty(Direction.DOWN), true)
                            .setValue(BlockStateProperties.WATERLOGGED, isWaterLogged)).build();
                    coveringLayer.put(above, coveringEntry);
                    coveringCount--;
                    if (coveringCount == 0) {
                        break;
                    }
                }
            }
        }

        return filler;
    }

    public static List<BlockPos> fetchCoveringPositions(ServerLevelAccessor level, BlockPos center, int radius) {
        return fetchCoveringPositions(level, center, radius,
                p -> {
                    BlockState state = level.getBlockState(p);
                    if (!state.isFaceSturdy(level, p, Direction.UP)) {
                        return false;
                    }
                    final BlockState above = level.getBlockState(p.above());
                    return above.canBeReplaced() || above.is(MalumTags.BlockTags.BLIGHT_REMOVABLE);
                });
    }

    public static List<BlockPos> fetchCoveringPositions(ServerLevelAccessor level, BlockPos center, int radius, Predicate<BlockPos> statePredicate) {
        List<BlockPos> positions = new ArrayList<>();
        int x = center.getX();
        int z = center.getZ();
        var mutable = new BlockPos.MutableBlockPos();

        int verticalRange = 6;
        float limit = Mth.sqrt(radius * radius + radius * radius);
        for (int i = -radius; i <= radius; i++) {
            for (int j = -radius; j <= radius; j++) {
                int offsetX = x + i;
                int offsetZ = z + j;
                float distance = Mth.sqrt(i * i + j * j);
                double theta = Math.toDegrees(Math.atan2(i, j)) * 0.01f;
                double noise = (COVERING_NOISE.getValue(x * 10000 + theta, z * 10000 + theta, true) + 1) / 2;
                double threshold = Easing.SINE_IN_OUT.clamped(noise, 0.5f, 2) * radius * (limit - distance) / limit;
                if (distance <= threshold) {
                    mutable.set(offsetX, center.getY(), offsetZ);
                    for (int k = 0; k < verticalRange; k++) {
                        if (!level.isStateAtPosition(mutable, BlightFeature::canBeRemoved)) {
                            mutable.move(Direction.UP);
                        }
                    }
                    for (int k = 0; k <= verticalRange*2; k++) {
                        if (level.isStateAtPosition(mutable, BlightFeature::canBeRemoved)) {
                            mutable.move(Direction.DOWN);
                        }
                    }
                    if (statePredicate.test(mutable)) {
                        positions.add(mutable.immutable());
                    }
                }
            }
        }
        return positions;
    }

    public static boolean canBeRemoved(BlockState state) {
        return state.canBeReplaced() || state.is(MalumTags.BlockTags.BLIGHT_REMOVABLE);
    }
}