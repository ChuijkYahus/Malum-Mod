package com.sammy.malum.common.worldgen.geode.config;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.PairCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sammy.malum.registry.common.util.GeodeCrystalSet;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import team.lodestar.lodestone.modules.toolkit.codec.LodestoneCodecs;

import java.util.Optional;

import static net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider.simple;

public record GeodeLayer(BlockStateProvider block,
                         Optional<Pair<BlockStateProvider, IntProvider>> buddingGeodes,
                         Optional<Pair<BlockStateProvider, IntProvider>> crystalClusters,
                         double size) {

    private static final Codec<Double> LAYER_RANGE = Codec.doubleRange(0.01, 50.0);
    public static final Codec<GeodeLayer> CODEC = RecordCodecBuilder.create(
            obj -> obj.group(
                            BlockStateProvider.CODEC.fieldOf("layer").forGetter(GeodeLayer::block),
                            LodestoneCodecs.optionalCodec(new PairCodec<>(BlockStateProvider.CODEC, IntProvider.POSITIVE_CODEC)).fieldOf("budding_geodes").forGetter(GeodeLayer::buddingGeodes),
                            LodestoneCodecs.optionalCodec(new PairCodec<>(BlockStateProvider.CODEC, IntProvider.POSITIVE_CODEC)).fieldOf("crystal_clusters").forGetter(GeodeLayer::crystalClusters),
                            LAYER_RANGE.fieldOf("size").forGetter(GeodeLayer::size))
                    .apply(obj, GeodeLayer::new)
    );


    public GeodeLayer(GeodeCrystalSet set, IntProvider buddingCount, IntProvider clusterCount, double size) {
        this(simple(set.getGeode().get()),
                Optional.of(Pair.of(simple(set.getBudding().get()), buddingCount)),
                Optional.of(Pair.of(simple(set.getCluster().get()), clusterCount)), size);
    }

    public GeodeLayer(Block block, double size) {
        this(simple(block), Optional.empty(), Optional.empty(), size);
    }

    public boolean hasCrystals() {
        return crystalClusters.isPresent();
    }

    public static GeodeLayer getLayer(MalumGeodeLayerSettings settings, double delta) {
        var layers = settings.layers();
        double geodeSize = layers.stream().mapToDouble(GeodeLayer::size).sum();
        var relative = delta * geodeSize;

        for (GeodeLayer layer : layers) {
            relative -= layer.size();
            if (relative < 0) {
                return layer;
            }
        }
        return layers.getLast();
    }
}
