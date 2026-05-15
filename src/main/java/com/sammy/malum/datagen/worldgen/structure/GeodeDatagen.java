package com.sammy.malum.datagen.worldgen.structure;

import com.sammy.malum.common.worldgen.geode.*;
import com.sammy.malum.common.worldgen.geode.config.*;
import com.sammy.malum.registry.common.util.GeodeCrystalSet;
import com.sammy.malum.registry.common.worldgen.MalumStructureTypes.StructureKeys;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.structure.Structure;

import java.util.List;
import java.util.Optional;

import static com.sammy.malum.datagen.worldgen.structure.StructureDatagen.*;
import static com.sammy.malum.registry.common.MalumContent.Materials.*;
import static com.sammy.malum.registry.common.MalumContent.Materials.MUNDANE_QUARTZ;
import static com.sammy.malum.registry.common.MalumTags.Biomes.*;
import static net.minecraft.util.valueproviders.UniformInt.of;
import static net.minecraft.world.level.block.Blocks.*;
import static net.minecraft.world.level.levelgen.GenerationStep.Decoration.*;
import static net.minecraft.world.level.levelgen.structure.TerrainAdjustment.NONE;

public class GeodeDatagen {

    public static void structureBootstrap(BootstrapContext<Structure> context) {
        float overworldAirPocket = 6f;

        float primaryQuartzLayerSize = 1f;
        float secondaryQuartzLayerSize = 0.5f;
        var buddingQuartzGeodes = of(6, 8);
        var quartzClusters = of(120, 160);

        float gemLayerSize = 1f;
        var buddingGemGeodes = of(2, 5);
        var gemClusters = of(20, 40);

        geode(context, StructureKeys.QUARTZ_GEODE, create(overworldAirPocket, primaryQuartzLayerSize, buddingQuartzGeodes, quartzClusters));
        geode(context, StructureKeys.VIVID_QUARTZ_GEODE, createSpecial(overworldAirPocket,
                VIVID_AMETRINE, buddingGemGeodes, gemClusters, gemLayerSize,
                MUNDANE_QUARTZ, buddingQuartzGeodes, quartzClusters, secondaryQuartzLayerSize));
        geode(context, StructureKeys.MARINE_QUARTZ_GEODE, createSpecial(overworldAirPocket,
                MARINE_AGATE, buddingGemGeodes, gemClusters, gemLayerSize,
                MUNDANE_QUARTZ, buddingQuartzGeodes, quartzClusters, secondaryQuartzLayerSize));
        geode(context, StructureKeys.RUGGED_QUARTZ_GEODE, createSpecial(overworldAirPocket,
                RUGGED_CITRINE, buddingGemGeodes, gemClusters, gemLayerSize,
                MUNDANE_QUARTZ, buddingQuartzGeodes, quartzClusters, secondaryQuartzLayerSize));

        float netherAirPocket = 16f;

        float primaryNetherQuartzLayerSize = 4f;
        float secondaryNetherQuartzLayerSize = 6f;
        var netherBuddingQuartzGeodes = of(16, 24);
        var netherQuartzClusters = of(300, 400);

        float netherGemLayerSize = 7f;
        var netherGemClusters = of(32, 48);
        var netherBuddingGemGeodes = of(4, 9);

        netherGeode(context, StructureKeys.NETHER_QUARTZ_GEODE, createNether(netherAirPocket, primaryNetherQuartzLayerSize, netherBuddingQuartzGeodes, netherQuartzClusters));
        netherGeode(context, StructureKeys.JAGGED_QUARTZ_GEODE, createSpecialNether(netherAirPocket,
                JAGGED_ONYX, netherBuddingGemGeodes, netherGemClusters, netherGemLayerSize,
                MUNDANE_QUARTZ, netherQuartzClusters, netherBuddingQuartzGeodes, secondaryNetherQuartzLayerSize));
        netherGeode(context, StructureKeys.PERFECT_QUARTZ_GEODE, createSpecialNether(netherAirPocket,
                PERFECT_QUARTZ, netherBuddingGemGeodes, netherGemClusters, netherGemLayerSize,
                MUNDANE_QUARTZ, netherQuartzClusters, netherBuddingQuartzGeodes, secondaryNetherQuartzLayerSize));
        netherGeode(context, StructureKeys.BLAZING_QUARTZ_GEODE, createSpecialNether(netherAirPocket,
                BLAZING_CARNELIAN, netherBuddingGemGeodes, netherGemClusters, netherGemLayerSize,
                MUNDANE_QUARTZ, netherQuartzClusters, netherBuddingQuartzGeodes, secondaryNetherQuartzLayerSize));
    }

    public static void geode(BootstrapContext<Structure> context, StructureKeys.StructureKey key, List<GeodeLayer> layers) {
        var geodeBlockSettings = new MalumGeodeLayerSettings(layers, BlockTags.FEATURES_CANNOT_REPLACE, BlockTags.GEODE_INVALID_BLOCKS);
        var geodeCrackSettings = new MalumGeodeCrackSettings(List.of(
                new GeodeAnchor(of(4, 6), UniformFloat.of(0.3f, 0.5f), UniformFloat.of(1.2f, 1.8f)),
                new GeodeAnchor(of(6, 8), UniformFloat.of(0.5f, 0.7f), UniformFloat.of(1.2f, 2.4f))
        ),
                0.85f, 0.75f);
        var geodeAnchorSettings = new GeodeAnchorSettings(List.of(
                new GeodeAnchor(of(0, 6), UniformFloat.of(0.1f, 0.2f), UniformFloat.of(0.8f, 0.9f)),
                new GeodeAnchor(of(4, 14), UniformFloat.of(0.2f, 0.3f), UniformFloat.of(0.6f, 0.7f))
        ));

        structure(context, key, b -> new MalumGeodeStructure(b, new MalumGeodeConfiguration(
                        geodeBlockSettings, geodeCrackSettings, geodeAnchorSettings, Optional.empty(), 1
                ), UniformHeight.of(VerticalAnchor.aboveBottom(24), VerticalAnchor.absolute(16))),
                HAS_QUARTZ_GEODE, UNDERGROUND_DECORATION, NONE);

    }

    public static void netherGeode(BootstrapContext<Structure> context, StructureKeys.StructureKey key, List<GeodeLayer> layers) {
        var geodeBlockSettings = new MalumGeodeLayerSettings(layers, BlockTags.FEATURES_CANNOT_REPLACE, BlockTags.GEODE_INVALID_BLOCKS);
        var geodeCrackSettings = new MalumGeodeCrackSettings(List.of(
                new GeodeAnchor(of(4, 6), UniformFloat.of(0.3f, 0.5f), UniformFloat.of(1.2f, 1.8f)),
                new GeodeAnchor(of(6, 8), UniformFloat.of(0.5f, 0.7f), UniformFloat.of(1.2f, 2.4f)),
                new GeodeAnchor(of(9, 12), UniformFloat.of(0.5f, 0.7f), UniformFloat.of(1.2f, 2.4f)),
                new GeodeAnchor(of(9, 12), UniformFloat.of(0.5f, 0.7f), UniformFloat.of(1.2f, 2.4f))
        ),
                0.85f, 1.5f);
        var geodeAnchorSettings = new GeodeAnchorSettings(List.of(
                new GeodeAnchor(of(0, 6), UniformFloat.of(0.1f, 0.2f), UniformFloat.of(1.2f, 1.6f)),
                new GeodeAnchor(of(8, 14), UniformFloat.of(0.2f, 0.3f), UniformFloat.of(0.6f, 0.7f)),
                new GeodeAnchor(of(8, 14), UniformFloat.of(0.2f, 0.3f), UniformFloat.of(0.6f, 0.7f))
        ));

        structure(context, key, b -> new MalumGeodeStructure(b, new MalumGeodeConfiguration(
                        geodeBlockSettings, geodeCrackSettings, geodeAnchorSettings, Optional.empty(), 1
                ), UniformHeight.of(VerticalAnchor.aboveBottom(32), VerticalAnchor.belowTop(32))),
                HAS_QUARTZ_GEODE, UNDERGROUND_DECORATION, NONE);

    }

    public static List<GeodeLayer> create(float airLayerSize, float gemLayerSize, UniformInt buddingGeodes, UniformInt clusters) {
        return List.of(new GeodeLayer(AIR, airLayerSize),
                new GeodeLayer(MUNDANE_QUARTZ, buddingGeodes, clusters, gemLayerSize),
                new GeodeLayer(CALCITE, 1.2f), new GeodeLayer(SMOOTH_BASALT, 1.8f)
        );
    }

    public static List<GeodeLayer> createNether(float airLayerSize, float gemLayerSize, UniformInt buddingQuartzGeodes, UniformInt quartzClusters) {
        return List.of(new GeodeLayer(AIR, airLayerSize), new GeodeLayer(MUNDANE_QUARTZ, buddingQuartzGeodes, quartzClusters, gemLayerSize),
                new GeodeLayer(MAGMA_BLOCK, 1.2f), new GeodeLayer(BLACKSTONE, 1.8f)
        );
    }

    public static List<GeodeLayer> createSpecial(float airLayerSize,
                                                 GeodeCrystalSet specialCrystal, UniformInt buddingGeodes, UniformInt clusters, float gemLayerSize,
                                                 GeodeCrystalSet quartz, UniformInt buddingQuartzGeodes, UniformInt quartzClusters, float quartzLayerSize) {
        var quartzLayer = new GeodeLayer(quartz, buddingQuartzGeodes, quartzClusters, quartzLayerSize);
        var gemLayer = new GeodeLayer(specialCrystal, buddingGeodes, clusters, gemLayerSize);

        return List.of(new GeodeLayer(AIR, airLayerSize), quartzLayer, gemLayer, quartzLayer,
                new GeodeLayer(CALCITE, 1.2f), new GeodeLayer(SMOOTH_BASALT, 1.8f)
        );
    }

    public static List<GeodeLayer> createSpecialNether(float airLayerSize,
                                                       GeodeCrystalSet specialCrystal, UniformInt buddingGeodes, UniformInt clusters, float gemLayerSize,
                                                       GeodeCrystalSet quartz, UniformInt buddingQuartzGeodes, UniformInt quartzClusters, float quartzLayerSize) {

        var quartzLayer = new GeodeLayer(quartz, buddingQuartzGeodes, quartzClusters, quartzLayerSize);
        var gemLayer = new GeodeLayer(specialCrystal, buddingGeodes, clusters, gemLayerSize);

        return List.of(new GeodeLayer(AIR, airLayerSize), quartzLayer, gemLayer, quartzLayer,
                new GeodeLayer(MAGMA_BLOCK, 1.2f), new GeodeLayer(BLACKSTONE, 1.8f)
        );
    }
}