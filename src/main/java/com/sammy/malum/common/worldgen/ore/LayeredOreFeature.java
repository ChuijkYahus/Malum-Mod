package com.sammy.malum.common.worldgen.ore;

import com.sammy.malum.common.worldgen.*;
import com.sammy.malum.registry.common.block.*;
import net.minecraft.core.*;
import net.minecraft.util.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.chunk.*;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.feature.*;

import java.util.*;
import java.util.function.Function;

public class LayeredOreFeature extends Feature<LayeredOreConfiguration> {
    
    public LayeredOreFeature() {
        super(LayeredOreConfiguration.CODEC);
    }

    public record BlockKey(LevelChunkSection section, BlockPos absolute, int x, int y, int z) {

    }
    public record LayerFeedback(HashMap<BlockKey, BlockState> placedBlocks) {

        public LayerFeedback() {
            this(new HashMap<>());
        }
    }

    @Override
    public boolean place(FeaturePlaceContext<LayeredOreConfiguration> pContext) {
        var randomsource = pContext.random();
        var blockpos = pContext.origin();
        var worldgenlevel = pContext.level();

        var config = pContext.config();
        var layers = config.oreLayers();
        var decoratorOptional = config.decorator();

        var blockMap = new HashMap<LayeredOreConfiguration.OreLayer, LayerFeedback>();
        float angle = randomsource.nextFloat() * (float) Math.PI;
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);
        double minY = (blockpos.getY() + randomsource.nextInt(3) - 2);
        double maxY = (blockpos.getY() + randomsource.nextInt(3) - 2);
        for (LayeredOreConfiguration.OreLayer layer : layers) {
            float horizontal = layer.width();
            float vertical = layer.height();
            float horizontalFactor = horizontal / 8f;
            float verticalFactor = vertical / 8f;
            int wRand = Mth.ceil((horizontalFactor + 1f) / 2f);
            int yRand = Mth.ceil((verticalFactor + 1f) / 2f);
            double minX = (double) blockpos.getX() + sin * (double) horizontalFactor;
            double maxX = (double) blockpos.getX() - sin * (double) horizontalFactor;
            double minZ = (double) blockpos.getZ() + cos * (double) horizontalFactor;
            double maxZ = (double) blockpos.getZ() - cos * (double) horizontalFactor;
            int xStart = blockpos.getX() - Mth.ceil(horizontalFactor) - wRand;
            int yStart = blockpos.getY() - Mth.ceil(verticalFactor) - yRand;
            int zStart = blockpos.getZ() - Mth.ceil(horizontalFactor) - wRand;
            int width = Mth.ceil(2 * (horizontalFactor + wRand));
            int height = Mth.ceil(2 * (verticalFactor + yRand));

            for (int x1 = xStart; x1 <= xStart + width; ++x1) {
                for (int z1 = zStart; z1 <= zStart + width; ++z1) {
                    if (yStart <= worldgenlevel.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, x1, z1)) {
                        var feedback = blockMap.computeIfAbsent(layer, (l)->new LayerFeedback());
                        doPlace(feedback, worldgenlevel, randomsource, layer, minX, maxX, minZ, maxZ, minY, maxY, xStart, yStart, zStart, width, height);
                    }
                }
            }
            if (layer.discardFeatureIfEmpty()) {
                if (!blockMap.containsKey(layer) || blockMap.get(layer).placedBlocks.isEmpty()) {
                    return false;
                }
            }
        }
        for (int i = layers.size() - 1; i >= 0; i--) {
            var layer = layers.get(i);
            blockMap.get(layer).placedBlocks.forEach((key, state) -> key.section.setBlockState(key.x, key.y, key.z, state, false));
        }

        if (decoratorOptional.isPresent()) {
            try (BulkSectionAccess bulksectionaccess = new BulkSectionAccess(worldgenlevel)) {
                var decorator = decoratorOptional.get();
                var positions = blockMap.values().stream().map(feedback -> feedback.placedBlocks).flatMap(m -> m.keySet().stream()).toList();
                var shuffled = WorldgenHelper.shuffle(positions, randomsource);
                var mutable = new BlockPos.MutableBlockPos();

                int min = decorator.minDecorations();
                int max = decorator.maxDecorations();
                if (max < min) {
                    max = min;
                }
                int toPlace = randomsource.nextInt(min, max);
                int placed = 0;
                for (BlockKey position : shuffled) {
                    for (Direction direction : Direction.values()) {
                        mutable.set(position.absolute).move(direction);
                        if (worldgenlevel.ensureCanWrite(mutable)) {
                            var offsetSection = bulksectionaccess.getSection(mutable);
                            if (offsetSection != null) {
                                int clusterX = SectionPos.sectionRelative(mutable.getX());
                                int clusterY = SectionPos.sectionRelative(mutable.getY());
                                int clusterZ = SectionPos.sectionRelative(mutable.getZ());
                                var state = worldgenlevel.getBlockState(mutable);
                                for (LayeredOreConfiguration.LayeredTargetBlockState placementRules : decorator.decorators()) {
                                    if (placementRules.target().test(state, randomsource)) {
                                        var decoratorState = placementRules.state().getState(randomsource, mutable);
                                        if (decoratorState.hasProperty(BlockStateProperties.FACING)) {
                                            decoratorState = decoratorState.setValue(BlockStateProperties.FACING, direction);
                                        }
                                        offsetSection.setBlockState(clusterX, clusterY, clusterZ, decoratorState, false);
                                        placed++;
                                        break;
                                    }
                                }
                            }
                        }
                        if (placed >= toPlace) {
                            return true;
                        }
                    }
                }
            }
        }
        return true;
    }

    protected void doPlace(
            LayerFeedback feedback,
            WorldGenLevel level,
            RandomSource random,
            LayeredOreConfiguration.OreLayer layer,
            double minX,
            double maxX,
            double minZ,
            double maxZ,
            double minY,
            double maxY,
            int x,
            int y,
            int z,
            int width,
            int height
    ) {
        //TODO: This method sucks
        BitSet bitset = new BitSet(width * height * width);
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        int j = layer.width();
        double[] adouble = new double[j * 4];

        for (int k = 0; k < j; k++) {
            float f = (float)k / (float)j;
            double d0 = Mth.lerp(f, minX, maxX);
            double d1 = Mth.lerp(f, minY, maxY);
            double d2 = Mth.lerp(f, minZ, maxZ);
            double d3 = random.nextDouble() * (double)j / 16.0;
            double d4 = ((double)(Mth.sin((float) Math.PI * f) + 1.0F) * d3 + 1.0) / 2.0;
            adouble[k * 4] = d0;
            adouble[k * 4 + 1] = d1;
            adouble[k * 4 + 2] = d2;
            adouble[k * 4 + 3] = d4;
        }

        for (int l3 = 0; l3 < j - 1; l3++) {
            if (!(adouble[l3 * 4 + 3] <= 0.0)) {
                for (int i4 = l3 + 1; i4 < j; i4++) {
                    if (!(adouble[i4 * 4 + 3] <= 0.0)) {
                        double d8 = adouble[l3 * 4] - adouble[i4 * 4];
                        double d10 = adouble[l3 * 4 + 1] - adouble[i4 * 4 + 1];
                        double d12 = adouble[l3 * 4 + 2] - adouble[i4 * 4 + 2];
                        double d14 = adouble[l3 * 4 + 3] - adouble[i4 * 4 + 3];
                        if (d14 * d14 > d8 * d8 + d10 * d10 + d12 * d12) {
                            if (d14 > 0.0) {
                                adouble[i4 * 4 + 3] = -1.0;
                            } else {
                                adouble[l3 * 4 + 3] = -1.0;
                            }
                        }
                    }
                }
            }
        }

        try (BulkSectionAccess bulksectionaccess = new BulkSectionAccess(level)) {
            for (int j4 = 0; j4 < j; j4++) {
                double d9 = adouble[j4 * 4 + 3];
                if (!(d9 < 0.0)) {
                    double d11 = adouble[j4 * 4];
                    double d13 = adouble[j4 * 4 + 1];
                    double d15 = adouble[j4 * 4 + 2];
                    int k4 = Math.max(Mth.floor(d11 - d9), x);
                    int l = Math.max(Mth.floor(d13 - d9), y);
                    int i1 = Math.max(Mth.floor(d15 - d9), z);
                    int j1 = Math.max(Mth.floor(d11 + d9), k4);
                    int k1 = Math.max(Mth.floor(d13 + d9), l);
                    int l1 = Math.max(Mth.floor(d15 + d9), i1);

                    for (int i2 = k4; i2 <= j1; i2++) {
                        double d5 = ((double)i2 + 0.5 - d11) / d9;
                        if (d5 * d5 < 1.0) {
                            for (int j2 = l; j2 <= k1; j2++) {
                                double d6 = ((double)j2 + 0.5 - d13) / d9;
                                if (d5 * d5 + d6 * d6 < 1.0) {
                                    for (int k2 = i1; k2 <= l1; k2++) {
                                        double d7 = ((double)k2 + 0.5 - d15) / d9;
                                        if (d5 * d5 + d6 * d6 + d7 * d7 < 1.0 && !level.isOutsideBuildHeight(j2)) {
                                            int l2 = i2 - x + (j2 - y) * width + (k2 - z) * width * height;
                                            if (!bitset.get(l2)) {
                                                bitset.set(l2);
                                                blockpos$mutableblockpos.set(i2, j2, k2);
                                                if (level.ensureCanWrite(blockpos$mutableblockpos)) {
                                                    LevelChunkSection levelchunksection = bulksectionaccess.getSection(blockpos$mutableblockpos);
                                                    if (levelchunksection != null) {
                                                        int i3 = SectionPos.sectionRelative(i2);
                                                        int j3 = SectionPos.sectionRelative(j2);
                                                        int k3 = SectionPos.sectionRelative(k2);
                                                        BlockState blockstate = levelchunksection.getBlockState(i3, j3, k3);

                                                        for (LayeredOreConfiguration.LayeredTargetBlockState placementRules : layer.targetStates()) {
                                                            if (canPlaceOre(
                                                                    blockstate,
                                                                    bulksectionaccess::getBlockState,
                                                                    random,
                                                                    layer,
                                                                    placementRules,
                                                                    blockpos$mutableblockpos
                                                            )) {
                                                                var oreState = placementRules.state().getState(random, blockpos$mutableblockpos);
                                                                var key = new BlockKey(levelchunksection, blockpos$mutableblockpos.immutable(), i3, j3, k3);
                                                                feedback.placedBlocks().put(key, oreState);
                                                                break;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static boolean canPlaceOre(
            BlockState state,
            Function<BlockPos, BlockState> adjacentStateAccessor,
            RandomSource random,
            LayeredOreConfiguration.OreLayer layer,
            LayeredOreConfiguration.LayeredTargetBlockState targetState,
            BlockPos.MutableBlockPos mutablePos
    ) {
        if (!targetState.target().test(state, random)) {
            return false;
        }
        return shouldSkipAirCheck(random, layer.discardChanceOnAirExposure()) || !isAdjacentToAir(adjacentStateAccessor, mutablePos);
    }

    protected static boolean shouldSkipAirCheck(RandomSource random, float chance) {
        if (chance <= 0.0F) {
            return true;
        } else {
            return !(chance >= 1.0F) && random.nextFloat() >= chance;
        }
    }
}
