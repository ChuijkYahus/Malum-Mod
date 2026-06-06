package com.sammy.malum.datagen.worldgen.configured;

import com.sammy.malum.common.worldgen.sanctuary.feature.*;
import com.sammy.malum.common.worldgen.sanctuary.feature.SanctuaryWallFeatureConfiguration.SegmentData;
import com.sammy.malum.common.worldgen.tree.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.util.data.BlockBundle;
import com.sammy.malum.registry.common.worldgen.*;
import com.sammy.malum.registry.common.worldgen.MalumFeatures.*;
import net.minecraft.data.worldgen.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.NoiseProvider;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import team.lodestar.lodestone.modules.toolkit.block.BlockBlockItemHolder;

import java.util.List;

import static com.sammy.malum.registry.common.MalumContent.BlockSets.*;
import static net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider.simple;

public class SanctuaryFeatureDatagen {

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {

        var stoneProvider = new NoiseProvider(1345L, new NormalNoise.NoiseParameters(0, 1.0),
                0.15f, List.of(
                TRODDEN_STONE.getRaw().block.getDefaultState(),
                Blocks.STONE.defaultBlockState()
        ));
        var troddenProvider = new SegmentData(simple(TRODDEN_STONE.getPolished().block), 1, 1);
        context.register(ConfiguredFeatures.SANCTUARY_PILLAR, new ConfiguredFeature<>(MalumFeatures.SANCTUARY_PILLAR.get(),
                new SanctuaryPillarFeatureConfiguration(
                        List.of(
                                troddenProvider,
                                new SegmentData(stoneProvider, 3, 5),
                                troddenProvider
                        )
                )
        ));
        context.register(ConfiguredFeatures.SANCTUARY_WALL, new ConfiguredFeature<>(MalumFeatures.SANCTUARY_WALL.get(),
                new SanctuaryWallFeatureConfiguration(
                        List.of(
                                troddenProvider,
                                new SegmentData(stoneProvider, 3, 5),
                                troddenProvider
                        ),
                        List.of(
                                troddenProvider,
                                new SegmentData(stoneProvider, 3, 4),
                                troddenProvider
                        ),
                        6, 12
                )
        ));
    }

    private static BlockStateProvider simple(BlockBlockItemHolder<Block, BlockItem> block) {
        return BlockStateProvider.simple(block.get());
    }
}