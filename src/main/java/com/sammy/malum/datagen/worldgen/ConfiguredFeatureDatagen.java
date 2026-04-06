package com.sammy.malum.datagen.worldgen;

import com.sammy.malum.common.worldgen.ore.*;
import com.sammy.malum.common.worldgen.tree.*;
import com.sammy.malum.registry.common.block.*;
import com.sammy.malum.registry.common.worldgen.*;
import net.minecraft.data.worldgen.*;
import net.minecraft.tags.*;
import net.minecraft.util.valueproviders.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;

import java.util.*;

public class ConfiguredFeatureDatagen {

    private static final RuleTest REPLACE_AIR = new TagMatchTest(BlockTags.AIR);
    private static final RuleTest REPLACE_STONES = new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD);
    private static final RuleTest REPLACE_STONE = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
    private static final RuleTest REPLACE_DEEPSLATE = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

    public static final List<OreConfiguration.TargetBlockState> SOULSTONE_TARGET_LIST = List.of(
            OreConfiguration.target(REPLACE_STONE, MalumBlocks.SOULSTONE_ORE.get().defaultBlockState()),
            OreConfiguration.target(REPLACE_DEEPSLATE, MalumBlocks.DEEPSLATE_SOULSTONE_ORE.get().defaultBlockState()));

    public static final List<OreConfiguration.TargetBlockState> BRILLIANT_TARGET_LIST = List.of(
            OreConfiguration.target(REPLACE_STONE, MalumBlocks.BRILLIANT_STONE.get().defaultBlockState()),
            OreConfiguration.target(REPLACE_DEEPSLATE, MalumBlocks.BRILLIANT_DEEPSLATE.get().defaultBlockState()));

    public static final List<OreConfiguration.TargetBlockState> NATURAL_QUARTZ_TARGET_LIST = List.of(
            OreConfiguration.target(REPLACE_STONE, MalumBlocks.NATURAL_QUARTZ_ORE.get().defaultBlockState()),
            OreConfiguration.target(REPLACE_DEEPSLATE, MalumBlocks.DEEPSLATE_QUARTZ_ORE.get().defaultBlockState()));

    public static final List<LayeredOreConfiguration.LayeredTargetBlockState> CTHONIC_GOLD_PRIMARY_LAYER = List.of(
            LayeredOreConfiguration.target(REPLACE_STONES, BlockStateProvider.simple(MalumBlocks.CTHONIC_GOLD_ORE.get())));
    public static final List<LayeredOreConfiguration.LayeredTargetBlockState> CTHONIC_GOLD_SECONDARY_LAYER = List.of(
            LayeredOreConfiguration.target(REPLACE_STONE, BlockStateProvider.simple(Blocks.GOLD_ORE)),
            LayeredOreConfiguration.target(REPLACE_DEEPSLATE, BlockStateProvider.simple(Blocks.DEEPSLATE_GOLD_ORE)));
    public static final List<LayeredOreConfiguration.LayeredTargetBlockState> CTHONIC_GOLD_DECORATOR_LAYER = List.of(
            LayeredOreConfiguration.target(REPLACE_AIR, BlockStateProvider.simple(MalumBlocks.CTHONIC_GOLD_CLUSTER.get())));

    public static final List<OreConfiguration.TargetBlockState> BLAZING_QUARTZ_TARGET_LIST = List.of(
            OreConfiguration.target(new TagMatchTest(BlockTags.BASE_STONE_NETHER), MalumBlocks.BLAZING_QUARTZ_ORE.get().defaultBlockState()));

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        context.register(MalumFeatures.ConfiguredFeatures.CONFIGURED_SOULSTONE_ORE, addOreConfig(SOULSTONE_TARGET_LIST, 8));
        context.register(MalumFeatures.ConfiguredFeatures.CONFIGURED_BRILLIANT_ORE, addOreConfig(BRILLIANT_TARGET_LIST, 4));
        context.register(MalumFeatures.ConfiguredFeatures.CONFIGURED_NATURAL_QUARTZ_ORE, addOreConfig(NATURAL_QUARTZ_TARGET_LIST, 5));
        context.register(MalumFeatures.ConfiguredFeatures.CONFIGURED_BLAZING_QUARTZ_ORE, addOreConfig(BLAZING_QUARTZ_TARGET_LIST, 14));
        context.register(MalumFeatures.ConfiguredFeatures.CONFIGURED_CTHONIC_GOLD_ORE_FEATURE, new ConfiguredFeature<>(MalumFeatures.LAYERED_ORE.get(), new LayeredOreConfiguration(
                List.of(
                        new LayeredOreConfiguration.OreLayer(CTHONIC_GOLD_PRIMARY_LAYER, 4, 16, 0f, true),
                        new LayeredOreConfiguration.OreLayer(CTHONIC_GOLD_SECONDARY_LAYER, 12, 24, 0.1f, false)
                ),
                Optional.of(new LayeredOreConfiguration.LayeredOreFeatureDecorator(CTHONIC_GOLD_DECORATOR_LAYER, 3, 6)))
        ));

        context.register(MalumFeatures.ConfiguredFeatures.CONFIGURED_RUNEWOOD_TREE, addTreeConfig(MalumFeatures.RUNEWOOD_TREE.get(), new RunewoodTreeConfiguration(
                MalumBlocks.RUNEWOOD_SAPLING.get(),
                MalumBlocks.RUNEWOOD_LOG.get(),
                MalumBlocks.SAPPY_RUNEWOOD_LOG.get(),
                MalumBlocks.RUNEWOOD_LEAVES.get(),
                MalumBlocks.HANGING_RUNEWOOD_LEAVES.get()
        )));
        context.register(MalumFeatures.ConfiguredFeatures.CONFIGURED_AZURE_RUNEWOOD_TREE, addTreeConfig(MalumFeatures.RUNEWOOD_TREE.get(), new RunewoodTreeConfiguration(
                MalumBlocks.RUNEWOOD_SAPLING.get(),
                MalumBlocks.RUNEWOOD_LOG.get(),
                MalumBlocks.SAPPY_RUNEWOOD_LOG.get(),
                MalumBlocks.AZURE_RUNEWOOD_LEAVES.get(),
                MalumBlocks.HANGING_AZURE_RUNEWOOD_LEAVES.get()
        )));

        context.register(MalumFeatures.ConfiguredFeatures.CONFIGURED_SOULWOOD_TREE, addTreeConfig(MalumFeatures.SOULWOOD_TREE.get(), NoneFeatureConfiguration.INSTANCE));

        context.register(MalumFeatures.ConfiguredFeatures.CONFIGURED_QUARTZ_GEODE_FEATURE, new ConfiguredFeature<>(Feature.GEODE, new GeodeConfiguration(
                new GeodeBlockSettings(
                        BlockStateProvider.simple(Blocks.AIR),
                        BlockStateProvider.simple(MalumBlocks.NATURAL_QUARTZ_ORE.get()),
                        BlockStateProvider.simple(MalumBlocks.NATURAL_QUARTZ_ORE.get()),
                        BlockStateProvider.simple(Blocks.TUFF), BlockStateProvider.simple(Blocks.SMOOTH_BASALT),
                        List.of(MalumBlocks.NATURAL_QUARTZ_CLUSTER.get().defaultBlockState()), BlockTags.FEATURES_CANNOT_REPLACE, BlockTags.GEODE_INVALID_BLOCKS),
                new GeodeLayerSettings(1D, 1.2D, 2.2D, 2.8D),
                new GeodeCrackSettings(1f, 4.0D, 3),
                0.85D,
                0.2D,
                true,
                UniformInt.of(3, 5),
                UniformInt.of(2, 3),
                UniformInt.of(0, 1),
                -16,
                16,
                0.1D,
                1)
        ));


        context.register(MalumFeatures.ConfiguredFeatures.CONFIGURED_DEEPSLATE_QUARTZ_GEODE_FEATURE, new ConfiguredFeature<>(Feature.GEODE, new GeodeConfiguration(
                new GeodeBlockSettings(
                        BlockStateProvider.simple(Blocks.AIR),
                        BlockStateProvider.simple(MalumBlocks.DEEPSLATE_QUARTZ_ORE.get()),
                        BlockStateProvider.simple(MalumBlocks.DEEPSLATE_QUARTZ_ORE.get()),
                        BlockStateProvider.simple(Blocks.CALCITE),
                        BlockStateProvider.simple(Blocks.SMOOTH_BASALT),
                        List.of(MalumBlocks.NATURAL_QUARTZ_CLUSTER.get().defaultBlockState()),
                        BlockTags.FEATURES_CANNOT_REPLACE, BlockTags.GEODE_INVALID_BLOCKS),
                new GeodeLayerSettings(1D, 1.4D, 2.6D, 4.2D),
                new GeodeCrackSettings(1f, 4.0D, 3),
                0.85D,
                0.2D,
                true,
                UniformInt.of(3, 5),
                UniformInt.of(2, 3),
                UniformInt.of(0, 1),
                -16,
                16,
                0.1D,
                1)
        ));
    }

    private static <T extends FeatureConfiguration, K extends Feature<T>> ConfiguredFeature<?, ?> addTreeConfig(K feature, T config) {
        return new ConfiguredFeature<>(feature, config);
    }

    private static ConfiguredFeature<?, ?> addOreConfig(List<OreConfiguration.TargetBlockState> targetList, int veinSize) {
        return new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(targetList, veinSize));
    }

    private static ConfiguredFeature<?, ?> addOreConfig(Feature<OreConfiguration> feature, List<OreConfiguration.TargetBlockState> targetList, int veinSize) {
        return new ConfiguredFeature<>(feature, new OreConfiguration(targetList, veinSize));
    }
}