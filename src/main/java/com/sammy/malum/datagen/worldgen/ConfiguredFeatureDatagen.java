package com.sammy.malum.datagen.worldgen;

import com.sammy.malum.common.worldgen.ore.*;
import com.sammy.malum.common.worldgen.ore.LayeredOreConfiguration.LayeredOreFeatureDecorator;
import com.sammy.malum.common.worldgen.ore.LayeredOreConfiguration.OreLayer;
import com.sammy.malum.common.worldgen.tree.*;
import com.sammy.malum.registry.common.block.*;
import com.sammy.malum.registry.common.worldgen.*;
import com.sammy.malum.registry.common.worldgen.MalumFeatures.ConfiguredFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.*;
import net.minecraft.util.valueproviders.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;

import java.util.*;

import static com.sammy.malum.common.worldgen.ore.LayeredOreConfiguration.target;
import static net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider.simple;

public class ConfiguredFeatureDatagen {

    private static final RuleTest REPLACE_AIR = new TagMatchTest(BlockTags.AIR);
    private static final RuleTest REPLACE_STONES = new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD);
    private static final RuleTest REPLACE_STONE = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
    private static final RuleTest REPLACE_DEEPSLATE = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

    public static final List<OreLayer> SOULSTONE_ORE_LAYERS = List.of(
            new OreLayer(List.of(
                    target(REPLACE_STONE, simple(MalumBlocks.SOULSTONE_ORE.get())),
                    target(REPLACE_DEEPSLATE, simple(MalumBlocks.DEEPSLATE_SOULSTONE_ORE.get()))
            ), 32, 16, 0f, true),
            new OreLayer(target(REPLACE_STONES, simple(Blocks.TUFF)),
                    40, 24, 0.1f, false)
    );

    public static final List<OreLayer> CTHONIC_GOLD_ORE_LAYERS = List.of(
            new OreLayer(target(REPLACE_STONES, simple(MalumBlocks.CTHONIC_GOLD_ORE.get())
            ), 4, 16, 0f, true),
            new OreLayer(List.of(
                    target(REPLACE_STONE, simple(Blocks.GOLD_ORE)),
                    target(REPLACE_DEEPSLATE, simple(Blocks.DEEPSLATE_GOLD_ORE))
            ), 12, 24, 0.1f, false)
    );
    public static final List<OreConfiguration.TargetBlockState> BRILLIANT_TARGET_LIST = List.of(
            OreConfiguration.target(REPLACE_STONE, MalumBlocks.BRILLIANT_STONE.get().defaultBlockState()),
            OreConfiguration.target(REPLACE_DEEPSLATE, MalumBlocks.BRILLIANT_DEEPSLATE.get().defaultBlockState()));

    public static final List<OreConfiguration.TargetBlockState> NATURAL_QUARTZ_TARGET_LIST = List.of(
            OreConfiguration.target(REPLACE_STONE, MalumBlocks.NATURAL_QUARTZ_ORE.get().defaultBlockState()),
            OreConfiguration.target(REPLACE_DEEPSLATE, MalumBlocks.DEEPSLATE_QUARTZ_ORE.get().defaultBlockState()));


    public static final List<OreConfiguration.TargetBlockState> BLAZING_QUARTZ_TARGET_LIST = List.of(
            OreConfiguration.target(new TagMatchTest(BlockTags.BASE_STONE_NETHER), MalumBlocks.BLAZING_QUARTZ_ORE.get().defaultBlockState()));

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        context.register(ConfiguredFeatures.SOULSTONE_ORE, new ConfiguredFeature<>(MalumFeatures.LAYERED_ORE.get(),
                new LayeredOreConfiguration(SOULSTONE_ORE_LAYERS, Optional.empty())
        ));
        context.register(ConfiguredFeatures.CTHONIC_GOLD_ORE, new ConfiguredFeature<>(MalumFeatures.LAYERED_ORE.get(),
                new LayeredOreConfiguration(CTHONIC_GOLD_ORE_LAYERS, Optional.of(
                        new LayeredOreFeatureDecorator(target(REPLACE_AIR, simple(MalumBlocks.CTHONIC_GOLD_CLUSTER.get())), 3, 6))
                )
        ));

        context.register(ConfiguredFeatures.SOULSTONE_ORE_SURFACE, addDistributedOreConfig(context, ConfiguredFeatures.SOULSTONE_ORE, 16, 0, 2));
        context.register(ConfiguredFeatures.SOULSTONE_ORE_CAVES, addDistributedOreConfig(context, ConfiguredFeatures.SOULSTONE_ORE, 24, 1, 3));
        context.register(ConfiguredFeatures.SOULSTONE_ORE_DEEPSLATE_CAVES, addDistributedOreConfig(context, ConfiguredFeatures.SOULSTONE_ORE, 32, 2, 4));

        context.register(ConfiguredFeatures.BRILLIANT_ORE, addOreConfig(BRILLIANT_TARGET_LIST, 4));
        context.register(ConfiguredFeatures.NATURAL_QUARTZ_ORE, addOreConfig(NATURAL_QUARTZ_TARGET_LIST, 5));
        context.register(ConfiguredFeatures.BLAZING_QUARTZ_ORE, addOreConfig(BLAZING_QUARTZ_TARGET_LIST, 14));
        
        context.register(ConfiguredFeatures.RUNEWOOD_TREE, addTreeConfig(MalumFeatures.RUNEWOOD_TREE.get(), new RunewoodTreeConfiguration(
                MalumBlocks.RUNEWOOD_SAPLING.get(),
                MalumBlocks.RUNEWOOD_LOG.get(),
                MalumBlocks.SAPPY_RUNEWOOD_LOG.get(),
                MalumBlocks.RUNEWOOD_LEAVES.get(),
                MalumBlocks.HANGING_RUNEWOOD_LEAVES.get()
        )));
        context.register(ConfiguredFeatures.AZURE_RUNEWOOD_TREE, addTreeConfig(MalumFeatures.RUNEWOOD_TREE.get(), new RunewoodTreeConfiguration(
                MalumBlocks.RUNEWOOD_SAPLING.get(),
                MalumBlocks.RUNEWOOD_LOG.get(),
                MalumBlocks.SAPPY_RUNEWOOD_LOG.get(),
                MalumBlocks.AZURE_RUNEWOOD_LEAVES.get(),
                MalumBlocks.HANGING_AZURE_RUNEWOOD_LEAVES.get()
        )));

        context.register(ConfiguredFeatures.SOULWOOD_TREE, addTreeConfig(MalumFeatures.SOULWOOD_TREE.get(), NoneFeatureConfiguration.INSTANCE));

        context.register(ConfiguredFeatures.QUARTZ_GEODE, new ConfiguredFeature<>(Feature.GEODE, new GeodeConfiguration(
                new GeodeBlockSettings(
                        simple(Blocks.AIR),
                        simple(MalumBlocks.NATURAL_QUARTZ_ORE.get()),
                        simple(MalumBlocks.NATURAL_QUARTZ_ORE.get()),
                        simple(Blocks.TUFF), simple(Blocks.SMOOTH_BASALT),
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


        context.register(ConfiguredFeatures.DEEPSLATE_QUARTZ_GEODE, new ConfiguredFeature<>(Feature.GEODE, new GeodeConfiguration(
                new GeodeBlockSettings(
                        simple(Blocks.AIR),
                        simple(MalumBlocks.DEEPSLATE_QUARTZ_ORE.get()),
                        simple(MalumBlocks.DEEPSLATE_QUARTZ_ORE.get()),
                        simple(Blocks.CALCITE),
                        simple(Blocks.SMOOTH_BASALT),
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

    public static ConfiguredFeature<?, ?> addDistributedOreConfig(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> distributedFeature, int height, int minDistributions, int maxDistributions) {
        var lookup = context.lookup(Registries.CONFIGURED_FEATURE);
        var help = lookup.getOrThrow(distributedFeature);
        return new ConfiguredFeature<>(MalumFeatures.DISTRIBUTED_ORE.get(), new DistributedOreFeatureConfiguration(help, height, minDistributions, maxDistributions));
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