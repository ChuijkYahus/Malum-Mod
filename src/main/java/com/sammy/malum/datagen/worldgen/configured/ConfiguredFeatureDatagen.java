package com.sammy.malum.datagen.worldgen.configured;

import com.sammy.malum.common.worldgen.sanctuary.feature.*;
import net.minecraft.data.worldgen.*;
import net.minecraft.world.level.levelgen.feature.*;

import static net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider.simple;

public class ConfiguredFeatureDatagen {

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        OreFeatureDatagen.bootstrap(context);
        TreeFeatureDatagen.bootstrap(context);
        SanctuaryFeatureDatagen.bootstrap(context);
        EbonyFeatureDatagen.bootstrap(context);
    }
}