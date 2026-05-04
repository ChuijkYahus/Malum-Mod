package com.sammy.malum.common.worldgen.ore;

import com.google.common.collect.*;
import com.sammy.malum.common.worldgen.*;
import net.minecraft.core.*;
import net.minecraft.util.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.chunk.*;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.synth.*;
import team.lodestar.lodestone.modules.core.easing.*;

import java.util.*;
import java.util.function.Function;

import static java.lang.Math.*;

public class LayeredOreFeature extends Feature<LayeredOreConfiguration> {

    public LayeredOreFeature() {
        super(LayeredOreConfiguration.CODEC);
    }

    public record LayerFeedback(HashMap<BlockPos, BlockState> placedBlocks) {
        public LayerFeedback() {
            this(new HashMap<>());
        }
    }

    @Override
    public boolean place(FeaturePlaceContext<LayeredOreConfiguration> pContext) {
        var random = pContext.random();
        var blockpos = pContext.origin();
        var worldgenlevel = pContext.level();

        var config = pContext.config();
        var layers = config.oreLayers();
        var decoratorOptional = config.decorator();

        var worldgenrandom = new WorldgenRandom(new LegacyRandomSource(worldgenlevel.getSeed()));
        var normalnoise = NormalNoise.create(worldgenrandom, -4, 1.0);

        var blockMap = new HashMap<LayeredOreConfiguration.OreLayer, LayerFeedback>();

        float angle = random.nextFloat() * 6.28f;
        float xScale = 1 + Mth.sin(angle) * 0.5f;
        float yScale = 1 + random.nextFloat() * 0.5f;
        float zScale = 1 + Mth.cos(angle) * 0.5f;
        for (LayeredOreConfiguration.OreLayer layer : layers) {
            float horizontal = layer.width();
            float vertical = layer.height();
            int xWidth = Mth.ceil(xScale * horizontal / 4f);
            int height = Mth.ceil(yScale * vertical / 4f);
            int zWidth = Mth.ceil(zScale * horizontal / 4f);

            int minX = ((blockpos.getX() >> 4) - 1) * 16;
            int maxX = ((blockpos.getX() >> 4) + 1) * 16;

            int minZ = ((blockpos.getZ() >> 4) - 1) * 16;
            int maxZ = ((blockpos.getZ() >> 4) + 1) * 16;

            int xStart = blockpos.getX() - xWidth;
            int xEnd = blockpos.getX() + xWidth;

            int yStart = blockpos.getY() - height;
            int yEnd = blockpos.getY() + height;

            int zStart = blockpos.getZ() - zWidth;
            int zEnd = blockpos.getZ() + zWidth;

            xStart = Mth.clamp(xStart, minX, maxX);
            xEnd = Mth.clamp(xEnd, minX, maxX);
            zStart = Mth.clamp(zStart, minZ, maxZ);
            zEnd = Mth.clamp(zEnd, minZ, maxZ);

            int xCenter = (xStart + xEnd) / 2;
            int yCenter = (yStart + yEnd) / 2;
            int zCenter = (zStart + zEnd) / 2;

            var feedback = blockMap.computeIfAbsent(layer, (l) -> new LayerFeedback());
            var mutable = new BlockPos.MutableBlockPos();
            for (int x = xStart; x <= xEnd; x++) {
                for (int z = zStart; z <= zEnd; z++) {
                    int worldHeight = worldgenlevel.getHeight(Heightmap.Types.WORLD_SURFACE_WG, x, z);
                    for (int y = yStart; y <= yEnd; y++) {
                        if (y > worldHeight) {
                            continue;
                        }

                        double xDist = ((double) x + 0.5 - xCenter);
                        double yDist = ((double) y + 0.5 - yCenter);
                        double zDist = ((double) z + 0.5 - zCenter);
                        float theta = (float) Math.atan2(xDist, zDist);
                        float noise = (float) ((normalnoise.getValue(x + theta, y, z + theta) + 1) / 2);
                        double threshold = Easing.SINE_IN_OUT.lerp(noise, 0.25f, 0.75f);

                        if (abs(xDist) > xWidth * threshold || abs(yDist) > height * threshold || abs(zDist) > zWidth * threshold) {
                            continue;
                        }

                        mutable.set(x, y, z);

                        var state = worldgenlevel.getBlockState(mutable);

                        for (LayeredOreConfiguration.LayeredTargetBlockState placementRules : layer.targetStates()) {
                            if (!placementRules.target().test(state, random)) {
                                continue;
                            }

                            if (!shouldSkipAirCheck(random, layer.discardChanceOnAirExposure()) && isAdjacentToAir(worldgenlevel::getBlockState, mutable)) {
                                continue;
                            }

                            var oreState = placementRules.state().getState(random, mutable);
                            feedback.placedBlocks().put(mutable.immutable(), oreState);
                            break;
                        }
                    }
                }
            }
            if (layer.discardFeatureIfEmpty()) {
                if (!blockMap.containsKey(layer) || blockMap.get(layer).placedBlocks().isEmpty()) {
                    return false;
                }
            }
        }


        var exhaustedPositions = new HashSet<BlockPos>();
        for (LayeredOreConfiguration.OreLayer layer : layers) {
            var map = blockMap.get(layer);
            var toPlace = map.placedBlocks.entrySet();
            WorldgenHelper.shuffle(toPlace, random);
            int min = layer.minBlocks();
            int max = layer.maxBlocks();
            int roll = min > max ? min : Easing.SINE_IN_OUT.asWeighedRandom(random, min, max);
            roll = Math.min(roll, toPlace.size());
            for (Map.Entry<BlockPos, BlockState> entry : toPlace) {
                if (roll == 0) {
                    break;
                }
                var pos = entry.getKey();
                if (exhaustedPositions.contains(pos)) {
                    continue;
                }
                var state = entry.getValue();
                worldgenlevel.setBlock(pos, state, 3);
                exhaustedPositions.add(pos);
                roll--;
            }
        }

        if (decoratorOptional.isPresent()) {
            var decorator = decoratorOptional.get();
            var shuffled = WorldgenHelper.shuffle(exhaustedPositions, random);
            var mutable = new BlockPos.MutableBlockPos();

            int min = decorator.minDecorations();
            int max = decorator.maxDecorations();
            if (max < min) {
                max = min;
            }
            int toPlace = random.nextInt(min, max);
            int placed = 0;
            for (BlockPos attached : shuffled) {
                for (Direction direction : Direction.values()) {
                    mutable.set(attached).move(direction);
                    if (tryPlaceDecorator(worldgenlevel,decorator, mutable, direction, random)) {
                        placed++;
                        break;
                    }
                    if (placed >= toPlace) {
                        return true;
                    }
                }
            }
        }
        return true;
    }

    public boolean tryPlaceDecorator(WorldGenLevel worldgenlevel, LayeredOreConfiguration.LayeredOreFeatureDecorator decorator, BlockPos pos, Direction direction, RandomSource randomSource) {
        var state = worldgenlevel.getBlockState(pos);
        for (LayeredOreConfiguration.LayeredTargetBlockState placementRules : decorator.decorators()) {
            if (placementRules.target().test(state, randomSource)) {
                var decoratorState = placementRules.state().getState(randomSource, pos);
                if (decoratorState.hasProperty(BlockStateProperties.FACING)) {
                    decoratorState = decoratorState.setValue(BlockStateProperties.FACING, direction);
                }
                if (!decoratorState.canSurvive(worldgenlevel, pos)) {
                    break;
                }
                worldgenlevel.setBlock(pos, decoratorState, 3);
                return true;
            }
        }
        return false;
    }

    protected static boolean shouldSkipAirCheck(RandomSource random, float chance) {
        if (chance <= 0.0F) {
            return true;
        } else {
            return !(chance >= 1.0F) && random.nextFloat() >= chance;
        }
    }
}
