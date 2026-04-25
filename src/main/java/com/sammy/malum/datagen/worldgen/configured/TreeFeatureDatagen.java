package com.sammy.malum.datagen.worldgen.configured;

import com.sammy.malum.common.worldgen.tree.*;
import com.sammy.malum.registry.common.worldgen.*;
import com.sammy.malum.registry.common.worldgen.MalumFeatures.*;
import net.minecraft.data.worldgen.*;
import net.minecraft.tags.*;
import net.minecraft.util.valueproviders.*;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.configurations.*;

import java.util.*;

import static com.sammy.malum.registry.common.MalumContent.BlockSets.*;
import static com.sammy.malum.registry.common.MalumContent.Materials.*;
import static net.minecraft.world.level.block.Blocks.*;
import static net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider.*;

public class TreeFeatureDatagen {

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {

        context.register(ConfiguredFeatures.RUNEWOOD_TREE, new ConfiguredFeature<>(MalumFeatures.RUNEWOOD_TREE.get(), new RunewoodTreeConfiguration(
                RUNEWOOD_SAPLING.get(),
                RUNEWOOD_SET.log.get(),
                RUNEWOOD_SET.sappyLog.get(),
                RUNEWOOD_LEAVES.get(),
                HANGING_RUNEWOOD_LEAVES.get()
        )));
        context.register(ConfiguredFeatures.AZURE_RUNEWOOD_TREE, new ConfiguredFeature<>(MalumFeatures.RUNEWOOD_TREE.get(), new RunewoodTreeConfiguration(
                RUNEWOOD_SAPLING.get(),
                RUNEWOOD_SET.log.get(),
                RUNEWOOD_SET.sappyLog.get(),
                AZURE_RUNEWOOD_LEAVES.get(),
                HANGING_AZURE_RUNEWOOD_LEAVES.get()
        )));

        context.register(ConfiguredFeatures.SOULWOOD_TREE, new ConfiguredFeature<>(MalumFeatures.SOULWOOD_TREE.get(), NoneFeatureConfiguration.INSTANCE));
    }
}