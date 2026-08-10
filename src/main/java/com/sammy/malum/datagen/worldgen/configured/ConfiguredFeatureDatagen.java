package com.sammy.malum.datagen.worldgen.configured;

import net.minecraft.data.worldgen.*;
import net.minecraft.world.level.levelgen.feature.*;


public class ConfiguredFeatureDatagen {

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        OreFeatureDatagen.bootstrap(context);
        TreeFeatureDatagen.bootstrap(context);
        EbonyFeatureDatagen.bootstrap(context);
    }
}