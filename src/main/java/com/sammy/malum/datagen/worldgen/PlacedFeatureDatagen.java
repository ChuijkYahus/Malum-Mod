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

        context.register(MalumFeatures.PlacedFeatures.ORE_SOULSTONE_CAVES,
                addOreFeature(features.getOrThrow(MalumFeatures.ConfiguredFeatures.SOULSTONE_ORE), -16, 48, 2, RarityFilter.onAverageOnceEvery(8)));
        context.register(MalumFeatures.PlacedFeatures.ORE_SOULSTONE_DEEPSLATE_CAVES,
                addOreFeature(features.getOrThrow(MalumFeatures.ConfiguredFeatures.SOULSTONE_ORE), -64, 0, 3, RarityFilter.onAverageOnceEvery(6)));


        context.register(MalumFeatures.PlacedFeatures.ORE_BRILLIANT,
                addOreFeature(features.getOrThrow(MalumFeatures.ConfiguredFeatures.BRILLIANT_ORE), -64, 40, 3));
        context.register(MalumFeatures.PlacedFeatures.ORE_NATURAL_QUARTZ,
                addOreFeature(features.getOrThrow(MalumFeatures.ConfiguredFeatures.NATURAL_QUARTZ_ORE), -64, 10, 1));
        context.register(MalumFeatures.PlacedFeatures.ORE_BLAZING_QUARTZ,
                addOreFeature(features.getOrThrow(MalumFeatures.ConfiguredFeatures.BLAZING_QUARTZ_ORE), -16, 112, 3));
        context.register(MalumFeatures.PlacedFeatures.ORE_CTHONIC_GOLD,
                addOreFeature(features.getOrThrow(MalumFeatures.ConfiguredFeatures.CTHONIC_GOLD_ORE), -48, 0, 2, RarityFilter.onAverageOnceEvery(8)));

        context.register(MalumFeatures.PlacedFeatures.RUNEWOOD_TREE,
                new PlacedFeature(features.getOrThrow(MalumFeatures.ConfiguredFeatures.RUNEWOOD_TREE),
                        ImmutableList.<PlacementModifier>builder().add(
                                RarityFilter.onAverageOnceEvery(120),
                                CountPlacement.of(UniformInt.of(1, 3)),
                                InSquarePlacement.spread(),
                                PlacementUtils.HEIGHTMAP
                        ).build()
                )
        );
        context.register(MalumFeatures.PlacedFeatures.RARE_RUNEWOOD_TREE,
                new PlacedFeature(features.getOrThrow(MalumFeatures.ConfiguredFeatures.RUNEWOOD_TREE),
                        ImmutableList.<PlacementModifier>builder().add(
                                RarityFilter.onAverageOnceEvery(160),
                                CountPlacement.of(UniformInt.of(1, 2)),
                                InSquarePlacement.spread(),
                                PlacementUtils.HEIGHTMAP
                        ).build()
                )
        );

        context.register(MalumFeatures.PlacedFeatures.AZURE_RUNEWOOD_TREE,
                new PlacedFeature(features.getOrThrow(MalumFeatures.ConfiguredFeatures.AZURE_RUNEWOOD_TREE),
                        ImmutableList.<PlacementModifier>builder().add(
                                RarityFilter.onAverageOnceEvery(200),
                                CountPlacement.of(UniformInt.of(1, 3)),
                                InSquarePlacement.spread(),
                                PlacementUtils.HEIGHTMAP
                        ).build()
                )
        );
        context.register(MalumFeatures.PlacedFeatures.RARE_AZURE_RUNEWOOD_TREE,
                new PlacedFeature(features.getOrThrow(MalumFeatures.ConfiguredFeatures.AZURE_RUNEWOOD_TREE),
                        ImmutableList.<PlacementModifier>builder().add(
                                RarityFilter.onAverageOnceEvery(240),
                                CountPlacement.of(UniformInt.of(1, 2)),
                                InSquarePlacement.spread(),
                                PlacementUtils.HEIGHTMAP
                        ).build()
                )
        );

        context.register(MalumFeatures.PlacedFeatures.QUARTZ_GEODE_FEATURE,
                new PlacedFeature(features.getOrThrow(MalumFeatures.ConfiguredFeatures.QUARTZ_GEODE),
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
                new PlacedFeature(features.getOrThrow(MalumFeatures.ConfiguredFeatures.DEEPSLATE_QUARTZ_GEODE),
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
                        HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(64 + minHeight), VerticalAnchor.absolute(maxHeight)),
                        InSquarePlacement.spread(),
                        BiomeFilter.biome())
                .add(extraModifiers)
                .build();
        return new PlacedFeature(configureFeature, modifiers);
    }
}