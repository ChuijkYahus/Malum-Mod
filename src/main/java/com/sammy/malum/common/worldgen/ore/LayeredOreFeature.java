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
    public record LayerFeedback(HashMap<BlockKey, BlockState> placedBlocks, HashSet<Integer> blockHashes) {

        public LayerFeedback() {
            this(new HashMap<>(), new HashSet<>());
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
        double sinOrCos = randomsource.nextBoolean() ? sin : cos;
        for (LayeredOreConfiguration.OreLayer layer : layers) {
            float horizontal = layer.width();
            float vertical = layer.height();
            float horizontalFactor = horizontal / 4f;
            float verticalFactor = vertical / 4f;
            int wRand = Mth.ceil((horizontalFactor + 1f) / 2f);
            int yRand = Mth.ceil((verticalFactor + 1f) / 2f);

            int xStart = blockpos.getX() - Mth.ceil(sin * horizontalFactor) - wRand;
            int xEnd = blockpos.getX() + Mth.ceil(sin * horizontalFactor) + wRand;

            int yStart = blockpos.getY() - Mth.ceil(sinOrCos * verticalFactor) - yRand;
            int yEnd = blockpos.getY() + Mth.ceil(sinOrCos * verticalFactor) + yRand;

            int zStart = blockpos.getZ() - Mth.ceil(cos * horizontalFactor) - wRand;
            int zEnd = blockpos.getZ() + Mth.ceil(cos * horizontalFactor) + wRand;

            var feedback = blockMap.computeIfAbsent(layer, (l) -> new LayerFeedback());
            for (int x1 = xStart; x1 <= xEnd; x1++) {
                for (int z1 = zStart; z1 <= zEnd; z1++) {
                    int worldHeight = worldgenlevel.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, x1, z1);
                    for (int y1 = yStart; y1 < yEnd; y1++) {
                        if (y1 <= worldHeight) {
                            doPlace(feedback, worldgenlevel, randomsource, layer, x1, y1, z1, xStart, xEnd, yStart, yEnd, zStart, zEnd);
                        }
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
            int xPos, int yPos, int zPos,
            int xStart, int xEnd,
            int yStart, int yEnd,
            int zStart, int zEnd
    ) {
        var mutable = new BlockPos.MutableBlockPos();
        int widthValue = layer.width();
        try (BulkSectionAccess bulksectionaccess = new BulkSectionAccess(level)) {
            for (int i = 0; i < widthValue; i++) {
                float delta = i / (float)widthValue;
                double rand = random.nextDouble() * (double)widthValue / 16.0;
                double distanceOrSomething = ((double)(Mth.sin((float) Math.PI * delta) + 1.0F) * rand + 1.0);
                if ((distanceOrSomething > 0.0)) {
                    int negativeX = Math.max(Mth.floor(xPos - distanceOrSomething), xStart);
                    int negativeY = Math.max(Mth.floor(yPos - distanceOrSomething), yStart);
                    int negativeZ = Math.max(Mth.floor(zPos - distanceOrSomething), zStart);
                    int positiveX = Math.min(Mth.ceil(xPos + distanceOrSomething), xEnd);
                    int positiveY = Math.min(Mth.ceil(yPos + distanceOrSomething), yEnd);
                    int positiveZ = Math.min(Mth.ceil(zPos + distanceOrSomething), zEnd);
                    float xCenter = (xStart + xEnd) / 2f;
                    float yCenter = (yStart + yEnd) / 2f;
                    float zCenter = (zStart + zEnd) / 2f;

                    for (int xSomething = negativeX; xSomething <= positiveX; xSomething++) {
                        double xDist = ((double)xSomething + 0.5 - xCenter) / distanceOrSomething;
                        if (xDist * xDist > 1) {
                            continue;
                        }
                        for (int ySomething = negativeY; ySomething <= positiveY; ySomething++) {
                            double yDist = ((double)ySomething + 0.5 - yCenter) / distanceOrSomething;
                            if (xDist * xDist + yDist * yDist > 1) {
                                continue;
                            }
                            for (int zSomething = negativeZ; zSomething <= positiveZ; zSomething++) {
                                double zDist = ((double) zSomething + 0.5 - zCenter) / distanceOrSomething;
                                if (xDist * xDist + yDist * yDist + zDist + zDist * zDist > 1) {
                                    continue;
                                }

                                mutable.set(xSomething, ySomething, zSomething);
                                if (!level.ensureCanWrite(mutable)) {
                                    continue;
                                }
                                var hash = mutable.hashCode();
                                if (feedback.blockHashes.contains(hash)) {
                                    continue;
                                }

                                var section = bulksectionaccess.getSection(mutable);
                                if (section == null) {
                                    continue;
                                }
                                feedback.blockHashes.add(hash);
                                int rX = SectionPos.sectionRelative(xSomething);
                                int rY = SectionPos.sectionRelative(ySomething);
                                int rZ = SectionPos.sectionRelative(zSomething);
                                BlockState blockstate = section.getBlockState(rX, rY, rZ);

                                for (LayeredOreConfiguration.LayeredTargetBlockState placementRules : layer.targetStates()) {
                                    if (canPlaceOre(blockstate, bulksectionaccess::getBlockState, random, layer, placementRules, mutable)) {
                                        var oreState = placementRules.state().getState(random, mutable);
                                        var key = new BlockKey(section, mutable.immutable(), rX, rY, rZ);
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
