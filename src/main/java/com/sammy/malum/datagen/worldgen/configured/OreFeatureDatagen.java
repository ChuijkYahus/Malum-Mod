package com.sammy.malum.datagen.worldgen.configured;

import com.sammy.malum.common.block.soulstone.*;
import com.sammy.malum.common.worldgen.ore.*;
import com.sammy.malum.common.worldgen.ore.LayeredOreConfiguration.*;
import com.sammy.malum.common.worldgen.tree.*;
import com.sammy.malum.registry.common.worldgen.*;
import com.sammy.malum.registry.common.worldgen.MalumFeatures.*;
import net.minecraft.data.worldgen.*;
import net.minecraft.tags.*;
import net.minecraft.util.random.*;
import net.minecraft.util.valueproviders.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;

import java.util.*;

import static com.sammy.malum.common.worldgen.ore.LayeredOreConfiguration.*;
import static com.sammy.malum.registry.common.MalumContent.BlockSets.*;
import static com.sammy.malum.registry.common.MalumContent.Materials.*;
import static net.minecraft.world.level.block.Blocks.*;
import static net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider.*;

public class OreFeatureDatagen {

    private static final RuleTest REPLACE_AIR = new TagMatchTest(BlockTags.AIR);
    private static final RuleTest REPLACE_STONES = new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD);
    private static final RuleTest REPLACE_STONE = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
    private static final RuleTest REPLACE_DEEPSLATE = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

    public static final List<OreLayer> SOULSTONE_LAYERS = List.of(
            new OreLayer(List.of(
                    target(REPLACE_STONE, simple(SOULSTONE_ORE.get())),
                    target(REPLACE_DEEPSLATE, simple(DEEPSLATE_SOULSTONE_ORE.get()))
            ), 20, 12, 0f, true),
            new OreLayer(target(REPLACE_STONES, simple(TUFF)), 28, 16, 0.2f, false)
    );

    public static final LayeredOreFeatureDecorator SOULSTONE_DECOR =
            new LayeredOreFeatureDecorator(target(REPLACE_AIR,
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                            .add(ARCHAIC_SOULSTONE_BUD.get().defaultBlockState())
                            .add(ARCHAIC_SOULSTONE_BUD.get().defaultBlockState().setValue(ArchaicSoulstoneBudBlock.STAGE, 1))
                            .add(ARCHAIC_SOULSTONE_BUD.get().defaultBlockState().setValue(ArchaicSoulstoneBudBlock.STAGE, 2)).build())), 4, 8);

    public static final List<OreLayer> CTHONIC_GOLD_LAYERS = List.of(
            new OreLayer(target(REPLACE_STONES, simple(CTHONIC_GOLD_ORE.get())
            ), 4, 8, 0f, true),
            new OreLayer(List.of(
                    target(REPLACE_STONE, simple(GOLD_ORE)),
                    target(REPLACE_DEEPSLATE, simple(DEEPSLATE_GOLD_ORE))
            ), 8, 16, 0.15f, false)
    );

    public static final LayeredOreFeatureDecorator CTHONIC_GOLD_DECOR =
            new LayeredOreFeatureDecorator(target(REPLACE_AIR, simple(CTHONIC_GOLD_FRAGMENT.get())), 4, 6);

    public static final List<OreConfiguration.TargetBlockState> BRILLIANT_TARGET_LIST = List.of(
            OreConfiguration.target(REPLACE_STONE, BRILLIANT_STONE.get().defaultBlockState()),
            OreConfiguration.target(REPLACE_DEEPSLATE, BRILLIANT_DEEPSLATE.get().defaultBlockState()));

    public static final List<OreConfiguration.TargetBlockState> NATURAL_QUARTZ_TARGET_LIST = List.of(
            OreConfiguration.target(REPLACE_STONE, NATURAL_QUARTZ_ORE.get().defaultBlockState()),
            OreConfiguration.target(REPLACE_DEEPSLATE, DEEPSLATE_QUARTZ_ORE.get().defaultBlockState()));


    public static final List<OreConfiguration.TargetBlockState> BLAZING_QUARTZ_TARGET_LIST = List.of(
            OreConfiguration.target(new TagMatchTest(BlockTags.BASE_STONE_NETHER), BLAZING_QUARTZ_ORE.get().defaultBlockState()));

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        context.register(ConfiguredFeatures.SOULSTONE_ORE, addLayeredOreConfig(SOULSTONE_LAYERS, SOULSTONE_DECOR));

        context.register(ConfiguredFeatures.CTHONIC_GOLD_ORE, addLayeredOreConfig(CTHONIC_GOLD_LAYERS, CTHONIC_GOLD_DECOR));

        context.register(ConfiguredFeatures.BRILLIANT_ORE, addOreConfig(BRILLIANT_TARGET_LIST, 4));
        context.register(ConfiguredFeatures.NATURAL_QUARTZ_ORE, addOreConfig(NATURAL_QUARTZ_TARGET_LIST, 5));
        context.register(ConfiguredFeatures.BLAZING_QUARTZ_ORE, addOreConfig(BLAZING_QUARTZ_TARGET_LIST, 14));
    }

    private static ConfiguredFeature<?, ?> addLayeredOreConfig(List<OreLayer> layers) {
        return addLayeredOreConfig(layers, null);
    }
    private static ConfiguredFeature<?, ?> addLayeredOreConfig(List<OreLayer> layers, LayeredOreFeatureDecorator decorator) {
        return new ConfiguredFeature<>(MalumFeatures.LAYERED_ORE.get(), new LayeredOreConfiguration(layers, Optional.ofNullable(decorator)));
    }

    private static ConfiguredFeature<?, ?> addOreConfig(List<OreConfiguration.TargetBlockState> targetList, int veinSize) {
        return new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(targetList, veinSize));
    }

    private static ConfiguredFeature<?, ?> addOreConfig(Feature<OreConfiguration> feature, List<OreConfiguration.TargetBlockState> targetList, int veinSize) {
        return new ConfiguredFeature<>(feature, new OreConfiguration(targetList, veinSize));
    }
}