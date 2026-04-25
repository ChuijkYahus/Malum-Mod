package com.sammy.malum.datagen.worldgen.configured;

import com.sammy.malum.common.block.soulstone.*;
import com.sammy.malum.common.worldgen.ore.*;
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
import static com.sammy.malum.registry.common.MalumContent.Materials.*;
import static net.minecraft.world.level.block.Blocks.*;
import static net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider.*;

public class GeodeFeatureDatagen {

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        context.register(ConfiguredFeatures.QUARTZ_GEODE, new ConfiguredFeature<>(Feature.GEODE, new GeodeConfiguration(
                new GeodeBlockSettings(
                        simple(AIR),
                        simple(NATURAL_QUARTZ_ORE.get()),
                        simple(NATURAL_QUARTZ_ORE.get()),
                        simple(TUFF), simple(SMOOTH_BASALT),
                        List.of(NATURAL_QUARTZ.getDefaultState()), BlockTags.FEATURES_CANNOT_REPLACE, BlockTags.GEODE_INVALID_BLOCKS),
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
                        simple(AIR),
                        simple(DEEPSLATE_QUARTZ_ORE.get()),
                        simple(DEEPSLATE_QUARTZ_ORE.get()),
                        simple(CALCITE),
                        simple(SMOOTH_BASALT),
                        List.of(NATURAL_QUARTZ.getDefaultState()),
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
}