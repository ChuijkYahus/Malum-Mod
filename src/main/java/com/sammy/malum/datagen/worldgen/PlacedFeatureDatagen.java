package com.sammy.malum.datagen.worldgen;

import com.google.common.collect.*;
import com.sammy.malum.registry.common.worldgen.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.data.worldgen.*;
import net.minecraft.data.worldgen.placement.*;
import net.minecraft.util.valueproviders.*;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.*;

public class PlacedFeatureDatagen {
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> features = context.lookup(Registries.CONFIGURED_FEATURE);
        context.register(MalumPlacedFeatures.ORE_SOULSTONE,
                addOreFeature(features.getOrThrow(ConfiguredFeatureRegistry.CONFIGURED_SOULSTONE_ORE), -64, 100, 3));
        context.register(MalumPlacedFeatures.ORE_BRILLIANT,
                addOreFeature(features.getOrThrow(ConfiguredFeatureRegistry.CONFIGURED_BRILLIANT_ORE), -64, 40, 3));
        context.register(MalumPlacedFeatures.ORE_NATURAL_QUARTZ,
                addOreFeature(features.getOrThrow(ConfiguredFeatureRegistry.CONFIGURED_NATURAL_QUARTZ_ORE), -64, 10, 2));
        context.register(MalumPlacedFeatures.ORE_BLAZING_QUARTZ,
                addOreFeature(features.getOrThrow(ConfiguredFeatureRegistry.CONFIGURED_BLAZING_QUARTZ_ORE), -16, 112, 3));
        context.register(MalumPlacedFeatures.ORE_CTHONIC_GOLD,
                addOreFeature(features.getOrThrow(ConfiguredFeatureRegistry.CONFIGURED_CTHONIC_GOLD_ORE_FEATURE), -48, 0, 1, RarityFilter.onAverageOnceEvery(2)));

        context.register(MalumPlacedFeatures.RUNEWOOD_TREE,
                new PlacedFeature(features.getOrThrow(ConfiguredFeatureRegistry.CONFIGURED_RUNEWOOD_TREE),
                        ImmutableList.<PlacementModifier>builder().add(
                                RarityFilter.onAverageOnceEvery(12),
                                CountPlacement.of(UniformInt.of(1, 3)),
                                InSquarePlacement.spread(),
                                PlacementUtils.HEIGHTMAP
                        ).build()
                )
        );
        context.register(MalumPlacedFeatures.RARE_RUNEWOOD_TREE,
                new PlacedFeature(features.getOrThrow(ConfiguredFeatureRegistry.CONFIGURED_RUNEWOOD_TREE),
                        ImmutableList.<PlacementModifier>builder().add(
                                RarityFilter.onAverageOnceEvery(20),
                                CountPlacement.of(UniformInt.of(1, 2)),
                                InSquarePlacement.spread(),
                                PlacementUtils.HEIGHTMAP
                        ).build()
                )
        );

        context.register(MalumPlacedFeatures.AZURE_RUNEWOOD_TREE,
                new PlacedFeature(features.getOrThrow(ConfiguredFeatureRegistry.CONFIGURED_AZURE_RUNEWOOD_TREE),
                        ImmutableList.<PlacementModifier>builder().add(
                                RarityFilter.onAverageOnceEvery(16),
                                CountPlacement.of(UniformInt.of(1, 3)),
                                InSquarePlacement.spread(),
                                PlacementUtils.HEIGHTMAP
                        ).build()
                )
        );
        context.register(MalumPlacedFeatures.RARE_AZURE_RUNEWOOD_TREE,
                new PlacedFeature(features.getOrThrow(ConfiguredFeatureRegistry.CONFIGURED_AZURE_RUNEWOOD_TREE),
                        ImmutableList.<PlacementModifier>builder().add(
                                RarityFilter.onAverageOnceEvery(24),
                                CountPlacement.of(UniformInt.of(1, 2)),
                                InSquarePlacement.spread(),
                                PlacementUtils.HEIGHTMAP
                        ).build()
                )
        );

        context.register(MalumPlacedFeatures.QUARTZ_GEODE_FEATURE,
                new PlacedFeature(features.getOrThrow(ConfiguredFeatureRegistry.CONFIGURED_QUARTZ_GEODE_FEATURE),
                        ImmutableList.<PlacementModifier>builder().add(
                                        RarityFilter.onAverageOnceEvery(24),
                                        InSquarePlacement.spread(),
                                        HeightRangePlacement.uniform(
                                                VerticalAnchor.aboveBottom(6),
                                                VerticalAnchor.absolute(-10)),
                                        BiomeFilter.biome())
                                .build()
                ));

        context.register(MalumPlacedFeatures.DEEPSLATE_QUARTZ_GEODE_FEATURE,
                new PlacedFeature(features.getOrThrow(ConfiguredFeatureRegistry.CONFIGURED_DEEPSLATE_QUARTZ_GEODE_FEATURE),
                        ImmutableList.<PlacementModifier>builder().add(
                                        RarityFilter.onAverageOnceEvery(24),
                                        InSquarePlacement.spread(),
                                        HeightRangePlacement.uniform(
                                                VerticalAnchor.aboveBottom(6),
                                                VerticalAnchor.absolute(-10)),
                                        BiomeFilter.biome())
                                .build()
                ));
    }

    private static PlacedFeature addOreFeature(Holder<ConfiguredFeature<?, ?>> configureFeature, int minHeight, int maxHeight, int count, PlacementModifier... extraModifiers) {
        final List<PlacementModifier> modifiers = ImmutableList.<PlacementModifier>builder().add(
                        CountPlacement.of(count),
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(minHeight), VerticalAnchor.absolute(maxHeight)),
                        InSquarePlacement.spread(),
                        BiomeFilter.biome())
                .add(extraModifiers)
                .build();
        return new PlacedFeature(configureFeature, modifiers);
    }
}