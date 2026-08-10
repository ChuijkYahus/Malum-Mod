package com.sammy.malum.datagen.worldgen.configured;

import com.sammy.malum.registry.common.worldgen.MalumFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class EbonyFeatureDatagen {

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {

        context.register(
                MalumFeatures.ConfiguredFeatures.EBONY,
                new ConfiguredFeature<>(
                        MalumFeatures.EBONY.get(),
                        NoneFeatureConfiguration.NONE
                )
        );
    }
}