package com.sammy.malum.common.worldgen.ore;

import com.sammy.malum.common.worldgen.*;
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

import static java.lang.Math.sqrt;

public class LayeredOreFeature extends Feature<LayeredOreConfiguration> {

    public LayeredOreFeature() {
        super(LayeredOreConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<LayeredOreConfiguration> pContext) {
        var randomsource = pContext.random();
        var blockpos = pContext.origin();
        var worldgenlevel = pContext.level();

        var config = pContext.config();
        var layers = config.oreLayers();
        var decoratorOptional = config.decorator();

        var toPlace = new HashMap<BlockPos, BlockState>();

        float angle = randomsource.nextFloat() * (float) Math.PI;
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);
        for (LayeredOreConfiguration.OreLayer layer : layers) {
            float horizontal = layer.width();
            float vertical = layer.height();
            float width = horizontal / 2f;
            float height = vertical / 2f;
            int hMinimum = Mth.ceil((width + 1f) / 2f);
            int yMinimum = Mth.ceil((height + 1f) / 2f);

            int minX = ((blockpos.getX() >> 4) - 1) * 16;
            int maxX = ((blockpos.getX() >> 4) + 1) * 16;

            int minZ = ((blockpos.getZ() >> 4) - 1) * 16;
            int maxZ = ((blockpos.getZ() >> 4) + 1) * 16;

            int xStart = blockpos.getX() - hMinimum;
            int xEnd = blockpos.getX() + hMinimum;

            int yStart = blockpos.getY() - yMinimum;
            int yEnd = blockpos.getY() + yMinimum;

            int zStart = blockpos.getZ() - hMinimum;
            int zEnd = blockpos.getZ() + hMinimum;

            xStart = Mth.clamp(xStart, minX, maxX);
            xEnd = Mth.clamp(xEnd, minX, maxX);
            zStart = Mth.clamp(zStart, minZ, maxZ);
            zEnd = Mth.clamp(zEnd, minZ, maxZ);

            int xCenter = (xStart + xEnd) / 2;
            int yCenter = (yStart + yEnd) / 2;
            int zCenter = (zStart + zEnd) / 2;

            boolean isValid = false;
            var mutable = new BlockPos.MutableBlockPos();
            for (int x = xStart; x <= xEnd; x++) {
                for (int z = zStart; z <= zEnd; z++) {
                    int worldHeight = worldgenlevel.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, x, z);
                    for (int y = yStart; y < yEnd; y++) {
                        if (y <= worldHeight) {
                            double xDist = ((double) x + 0.5 - xCenter);
                            double yDist = ((double) y + 0.5 - yCenter);
                            double zDist = ((double) z + 0.5 - zCenter);
                            if (sqrt(xDist * xDist + zDist + zDist) > width / 4 || sqrt(yDist * yDist) > height / 4) {
                                continue;
                            }

                            mutable.set(x, y, z);
                            int hash = mutable.hashCode();
                            if (hashes.contains(hash)) {
                                continue;
                            }
                            hashes.add(hash);
                            BlockState state = worldgenlevel.getBlockState(mutable);

                            for (LayeredOreConfiguration.LayeredTargetBlockState placementRules : layer.targetStates()) {
                                if (!placementRules.target().test(state, randomsource)) {
                                    continue;
                                }

                                if (!shouldSkipAirCheck(randomsource, layer.discardChanceOnAirExposure()) && isAdjacentToAir(worldgenlevel::getBlockState, mutable)) {
                                    continue;
                                }

                                var oreState = placementRules.state().getState(randomsource, mutable);
                                worldgenlevel.setBlock()
                                feedback.placedBlocks().put(key, oreState);
                                break;
                            }

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

    public static boolean canPlaceOre(WorldGenLevel level, RandomSource random, LayeredOreConfiguration.OreLayer layer, LayeredOreConfiguration.LayeredTargetBlockState targetState,
                                      BlockPos pos
    ) {
        if (!targetState.target().test(state, random)) {
            return false;
        }
        return shouldSkipAirCheck(random, layer.discardChanceOnAirExposure()) || !isAdjacentToAir(level::getBlockState, pos);
    }

    protected static boolean shouldSkipAirCheck(RandomSource random, float chance) {
        if (chance <= 0.0F) {
            return true;
        } else {
            return !(chance >= 1.0F) && random.nextFloat() >= chance;
        }
    }
}
