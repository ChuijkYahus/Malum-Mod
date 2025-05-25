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
        context.register(MalumFeatures.PlacedFeatures.ORE_SOULSTONE,
                addOreFeature(features.getOrThrow(MalumFeatures.ConfiguredFeatures.CONFIGURED_SOULSTONE_ORE), -64, 100, 3));
        context.register(MalumFeatures.PlacedFeatures.ORE_BRILLIANT,
                addOreFeature(features.getOrThrow(MalumFeatures.ConfiguredFeatures.CONFIGURED_BRILLIANT_ORE), -64, 40, 3));
        context.register(MalumFeatures.PlacedFeatures.ORE_NATURAL_QUARTZ,
                addOreFeature(features.getOrThrow(MalumFeatures.ConfiguredFeatures.CONFIGURED_NATURAL_QUARTZ_ORE), -64, 10, 1));
        context.register(MalumFeatures.PlacedFeatures.ORE_BLAZING_QUARTZ,
                addOreFeature(features.getOrThrow(MalumFeatures.ConfiguredFeatures.CONFIGURED_BLAZING_QUARTZ_ORE), -16, 112, 3));
        context.register(MalumFeatures.PlacedFeatures.ORE_CTHONIC_GOLD,
                addOreFeature(features.getOrThrow(MalumFeatures.ConfiguredFeatures.CONFIGURED_CTHONIC_GOLD_ORE_FEATURE), -48, 0, 1, RarityFilter.onAverageOnceEvery(4)));

        context.register(MalumFeatures.PlacedFeatures.RUNEWOOD_TREE,
                new PlacedFeature(features.getOrThrow(MalumFeatures.ConfiguredFeatures.CONFIGURED_RUNEWOOD_TREE),
                        ImmutableList.<PlacementModifier>builder().add(
                                RarityFilter.onAverageOnceEvery(24),
                                CountPlacement.of(UniformInt.of(1, 3)),
                                InSquarePlacement.spread(),
                                PlacementUtils.HEIGHTMAP
                        ).build()
                )
        );
        context.register(MalumFeatures.PlacedFeatures.RARE_RUNEWOOD_TREE,
                new PlacedFeature(features.getOrThrow(MalumFeatures.ConfiguredFeatures.CONFIGURED_RUNEWOOD_TREE),
                        ImmutableList.<PlacementModifier>builder().add(
                                RarityFilter.onAverageOnceEvery(40),
                                CountPlacement.of(UniformInt.of(1, 2)),
                                InSquarePlacement.spread(),
                                PlacementUtils.HEIGHTMAP
                        ).build()
                )
        );

        context.register(MalumFeatures.PlacedFeatures.AZURE_RUNEWOOD_TREE,
                new PlacedFeature(features.getOrThrow(MalumFeatures.ConfiguredFeatures.CONFIGURED_AZURE_RUNEWOOD_TREE),
                        ImmutableList.<PlacementModifier>builder().add(
                                RarityFilter.onAverageOnceEvery(32),
                                CountPlacement.of(UniformInt.of(1, 3)),
                                InSquarePlacement.spread(),
                                PlacementUtils.HEIGHTMAP
                        ).build()
                )
        );
        context.register(MalumFeatures.PlacedFeatures.RARE_AZURE_RUNEWOOD_TREE,
                new PlacedFeature(features.getOrThrow(MalumFeatures.ConfiguredFeatures.CONFIGURED_AZURE_RUNEWOOD_TREE),
                        ImmutableList.<PlacementModifier>builder().add(
                                RarityFilter.onAverageOnceEvery(64),
                                CountPlacement.of(UniformInt.of(1, 2)),
                                InSquarePlacement.spread(),
                                PlacementUtils.HEIGHTMAP
                        ).build()
                )
        );

        context.register(MalumFeatures.PlacedFeatures.QUARTZ_GEODE_FEATURE,
                new PlacedFeature(features.getOrThrow(MalumFeatures.ConfiguredFeatures.CONFIGURED_QUARTZ_GEODE_FEATURE),
                        ImmutableList.<PlacementModifier>builder().add(
                                        RarityFilter.onAverageOnceEvery(24),
                                        InSquarePlacement.spread(),
                                        HeightRangePlacement.uniform(
                                                VerticalAnchor.aboveBottom(6),
                                                VerticalAnchor.absolute(-10)),
                                        BiomeFilter.biome())
                                .build()
                ));

        context.register(MalumFeatures.PlacedFeatures.DEEPSLATE_QUARTZ_GEODE_FEATURE,
                new PlacedFeature(features.getOrThrow(MalumFeatures.ConfiguredFeatures.CONFIGURED_DEEPSLATE_QUARTZ_GEODE_FEATURE),
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