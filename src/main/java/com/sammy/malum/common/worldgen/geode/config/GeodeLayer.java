package com.sammy.malum.common.worldgen.geode.config;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sammy.malum.registry.common.util.GeodeCrystalSet;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;

import java.util.Optional;

import static net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider.simple;

public record GeodeLayer(BlockStateProvider block,
                         Optional<SpecialBlockInfo> buddingGeodes,
                         Optional<SpecialBlockInfo> crystalClusters,
                         double size) {

    private static final Codec<Double> LAYER_RANGE = Codec.doubleRange(0.01, 50.0);

    public static final Codec<SpecialBlockInfo> INFO_CODEC = RecordCodecBuilder.create(
            obj -> obj.group(
                            BlockStateProvider.CODEC.fieldOf("state").forGetter(SpecialBlockInfo::state),
                            IntProvider.NON_NEGATIVE_CODEC.fieldOf("amount").forGetter(SpecialBlockInfo::amount))
                    .apply(obj, SpecialBlockInfo::new)
    );

    public static final Codec<GeodeLayer> CODEC = RecordCodecBuilder.create(
            obj -> obj.group(
                            BlockStateProvider.CODEC.fieldOf("layer").forGetter(GeodeLayer::block),
                            INFO_CODEC.lenientOptionalFieldOf("budding_geodes").forGetter(GeodeLayer::buddingGeodes),
                            INFO_CODEC.lenientOptionalFieldOf("crystal_clusters").forGetter(GeodeLayer::crystalClusters),
                            LAYER_RANGE.fieldOf("size").forGetter(GeodeLayer::size))
                    .apply(obj, GeodeLayer::new)
    );


    public GeodeLayer(GeodeCrystalSet set, IntProvider buddingCount, IntProvider clusterCount, double size) {
        this(simple(set.getGeode().get()),
                Optional.of(new SpecialBlockInfo(simple(set.getBudding().get()), buddingCount)),
                Optional.of(new SpecialBlockInfo(simple(set.getCluster().get()), clusterCount)), size);
    }

    public GeodeLayer(Block block, double size) {
        this(simple(block), Optional.empty(), Optional.empty(), size);
    }

    public boolean hasBuddingGeodes() {
        return buddingGeodes.isPresent();
    }

    public boolean hasCrystals() {
        return crystalClusters.isPresent();
    }

    public SpecialBlockInfo getBuddingGeodes() {
        assert buddingGeodes.isPresent();
        return buddingGeodes.get();
    }

    public SpecialBlockInfo getCrystals() {
        assert crystalClusters.isPresent();
        return crystalClusters.get();
    }

    public boolean isAir() {
        return block instanceof SimpleStateProvider provider && provider.state.isAir();
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

    public record SpecialBlockInfo(BlockStateProvider state, IntProvider amount) {

    }
}
