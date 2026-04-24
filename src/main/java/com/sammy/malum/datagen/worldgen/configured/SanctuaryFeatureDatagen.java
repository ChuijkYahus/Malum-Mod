package com.sammy.malum.datagen.worldgen.configured;

import com.sammy.malum.common.worldgen.sanctuary.feature.*;
import com.sammy.malum.common.worldgen.tree.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.worldgen.*;
import com.sammy.malum.registry.common.worldgen.MalumFeatures.*;
import net.minecraft.data.worldgen.*;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.configurations.*;

import static com.sammy.malum.registry.common.MalumContent.BlockSets.*;

public class SanctuaryFeatureDatagen {

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {

        context.register(ConfiguredFeatures.SANCTUARY_PILLAR, new ConfiguredFeature<>(MalumFeatures.SANCTUARY_PILLAR.get(),
                new SanctuaryPillarFeatureConfiguration(TRODDEN_STONE.block.get(), POLISHED_TRODDEN_STONE.block.get(), 2, 6)));

        context.register(ConfiguredFeatures.SANCTUARY_WALL, new ConfiguredFeature<>(MalumFeatures.SANCTUARY_WALL.get(),
                new SanctuaryWallFeatureConfiguration(TRODDEN_STONE.block.get(), POLISHED_TRODDEN_STONE.block.get(), TRODDEN_STONE_BRICKS.block.get(), 1, 3, 4, 8)));
    }
}