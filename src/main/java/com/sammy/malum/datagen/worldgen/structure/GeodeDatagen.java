package com.sammy.malum.datagen.worldgen.structure;

import com.sammy.malum.common.worldgen.geode.*;
import com.sammy.malum.common.worldgen.geode.config.*;
import com.sammy.malum.common.worldgen.sanctuary.RunicSanctuaryStructure;
import com.sammy.malum.common.worldgen.well.WeepingWellStructure;
import com.sammy.malum.registry.common.util.GeodeCrystalSet;
import com.sammy.malum.registry.common.worldgen.MalumStructureTypes;
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
import static com.sammy.malum.registry.common.MalumContent.Materials.MUNDANE_QUARTZ;
import static com.sammy.malum.registry.common.MalumTags.Biomes.*;
import static net.minecraft.util.valueproviders.UniformInt.of;
import static net.minecraft.world.level.block.Blocks.*;
import static net.minecraft.world.level.levelgen.GenerationStep.Decoration.*;
import static net.minecraft.world.level.levelgen.structure.TerrainAdjustment.NONE;

public class GeodeDatagen {

    public static void structureBootstrap(BootstrapContext<Structure> context) {
        float overworldAirPocket = 8f;
        var buddingQuartzGeodes = of(6, 8);
        var quartzClusters = of(40, 60);
        float gemLayerSize = 1f;
        var buddingGemGeodes = of(2, 5);
        var gemClusters = of(6, 12);
        float primaryQuartzLayerSize = 3f;
        float secondaryQuartzLayerSize = 2f;

        geode(context, MalumStructureTypes.StructureKeys.QUARTZ_GEODE, create(overworldAirPocket, primaryQuartzLayerSize, buddingQuartzGeodes, quartzClusters));
        geode(context, MalumStructureTypes.StructureKeys.VIVID_QUARTZ_GEODE, createSpecial(overworldAirPocket, secondaryQuartzLayerSize, buddingQuartzGeodes, quartzClusters, gemLayerSize, buddingGemGeodes, gemClusters));
        geode(context, MalumStructureTypes.StructureKeys.MARINE_QUARTZ_GEODE, createSpecial(overworldAirPocket, secondaryQuartzLayerSize, buddingQuartzGeodes, quartzClusters, gemLayerSize, buddingGemGeodes, gemClusters));
        geode(context, MalumStructureTypes.StructureKeys.RUGGED_QUARTZ_GEODE, createSpecial(overworldAirPocket, secondaryQuartzLayerSize, buddingQuartzGeodes, quartzClusters, gemLayerSize, buddingGemGeodes, gemClusters));

        float netherAirPocket = 16f;
        float primaryNetherQuartzLayerSize = 4f;
        float secondaryNetherQuartzLayerSize = 6f;
        var netherBuddingQuartzGeodes = of(16, 24);
        var netherQuartzClusters = of(100, 200);
        float netherGemLayerSize = 7f;
        var netherGemClusters = of(16, 32);
        var netherBuddingGemGeodes = of(4, 9);
        geode(context, MalumStructureTypes.StructureKeys.NETHER_QUARTZ_GEODE, createNether(netherAirPocket, primaryNetherQuartzLayerSize, netherBuddingQuartzGeodes, netherQuartzClusters));
        geode(context, MalumStructureTypes.StructureKeys.JAGGED_QUARTZ_GEODE, createSpecialNether(netherAirPocket, secondaryNetherQuartzLayerSize, netherBuddingQuartzGeodes, netherQuartzClusters, netherGemLayerSize, netherBuddingGemGeodes, netherGemClusters));
        geode(context, MalumStructureTypes.StructureKeys.PERFECT_QUARTZ_GEODE, createSpecialNether(netherAirPocket, secondaryNetherQuartzLayerSize, netherBuddingQuartzGeodes, netherQuartzClusters, netherGemLayerSize, netherBuddingGemGeodes, netherGemClusters));
        geode(context, MalumStructureTypes.StructureKeys.BLAZING_QUARTZ_GEODE, createSpecialNether(netherAirPocket, secondaryNetherQuartzLayerSize, netherBuddingQuartzGeodes, netherQuartzClusters, netherGemLayerSize, netherBuddingGemGeodes, netherGemClusters));
    }

    public static void geode(BootstrapContext<Structure> context, MalumStructureTypes.StructureKeys.StructureKey key, List<GeodeLayer> layers) {
        var geodeBlockSettings = new MalumGeodeLayerSettings(layers, BlockTags.FEATURES_CANNOT_REPLACE, BlockTags.GEODE_INVALID_BLOCKS);
        var geodeCrackSettings = new MalumGeodeCrackSettings(0.95f, 2f, 4);
        var geodeAnchorSettings = new GeodeAnchorSettings(List.of(
                new GeodeAnchorSettings.GeodeAnchor(of(0, 4), UniformFloat.of(0.25f, 0.4f), UniformFloat.of(0.8f, 1.0f))
        ));
        structure(context, key, b -> new MalumGeodeStructure(b, new MalumGeodeConfiguration(
                        geodeBlockSettings, geodeCrackSettings, geodeAnchorSettings, Optional.empty(), 1
                ), UniformHeight.of(VerticalAnchor.aboveBottom(24), VerticalAnchor.aboveBottom(24))),
                HAS_QUARTZ_GEODE, UNDERGROUND_DECORATION, NONE);

    }

    public static List<GeodeLayer> create(float airLayerSize, float gemLayerSize, UniformInt buddingGeodes, UniformInt clusters) {
        return List.of(new GeodeLayer(AIR, airLayerSize),
                new GeodeLayer(MUNDANE_QUARTZ, buddingGeodes, clusters, gemLayerSize),
                new GeodeLayer(CALCITE, 1.2f), new GeodeLayer(SMOOTH_BASALT, 1.8f)
        );
    }

    public static List<GeodeLayer> createNether(float airLayerSize, float gemLayerSize, UniformInt buddingGeodes, UniformInt clusters) {
        return List.of(new GeodeLayer(AIR, airLayerSize), new GeodeLayer(MUNDANE_QUARTZ, buddingGeodes, clusters, gemLayerSize),
                new GeodeLayer(MAGMA_BLOCK, 2.2f), new GeodeLayer(BLACKSTONE, 4.8f)
        );
    }

    public static List<GeodeLayer> createSpecial(float airLayerSize,
                                                 float quartzLayerSize, UniformInt buddingQuartzGeodes, UniformInt quartzClusters,
                                                 GeodeCrystalSet specialCrystal, float gemLayerSize, UniformInt buddingGeodes, UniformInt clusters) {
        var quartzLayer = new GeodeLayer(MUNDANE_QUARTZ, buddingQuartzGeodes, quartzClusters, quartzLayerSize);
        var gemLayer = new GeodeLayer(specialCrystal, buddingGeodes, clusters, gemLayerSize);

        return List.of(new GeodeLayer(AIR, airLayerSize),
                quartzLayer, gemLayer, quartzLayer,
                new GeodeLayer(CALCITE, 1.2f), new GeodeLayer(SMOOTH_BASALT, 1.8f)
        );
    }

    public static List<GeodeLayer> createSpecialNether(float airLayerSize,
                                                       float quartzLayerSize, UniformInt buddingQuartzGeodes, UniformInt quartzClusters,
                                                       GeodeCrystalSet specialCrystal, float gemLayerSize, UniformInt buddingGeodes, UniformInt clusters) {

        var quartzLayer = new GeodeLayer(MUNDANE_QUARTZ, buddingQuartzGeodes, quartzClusters, quartzLayerSize);
        var gemLayer = new GeodeLayer(specialCrystal, buddingGeodes, clusters, gemLayerSize);

        return List.of(new GeodeLayer(AIR, airLayerSize),
                quartzLayer, gemLayer, quartzLayer,
                new GeodeLayer(MAGMA_BLOCK, 2.2f), new GeodeLayer(BLACKSTONE, 4.8f)
        );
    }
}