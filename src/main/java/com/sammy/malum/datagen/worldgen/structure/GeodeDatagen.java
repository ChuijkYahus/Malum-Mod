package com.sammy.malum.datagen.worldgen.structure;

import com.sammy.malum.common.worldgen.geode.*;
import com.sammy.malum.common.worldgen.geode.config.*;
import com.sammy.malum.registry.common.util.GeodeCrystalRegistrySet;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.sammy.malum.datagen.worldgen.structure.StructureDatagen.*;
import static com.sammy.malum.registry.common.MalumContent.Materials.*;
import static com.sammy.malum.registry.common.MalumContent.Materials.MUNDANE_QUARTZ;
import static com.sammy.malum.registry.common.worldgen.MalumStructureTypes.StructureKeys.*;
import static net.minecraft.util.valueproviders.UniformInt.of;
import static net.minecraft.world.level.block.Blocks.*;
import static net.minecraft.world.level.levelgen.GenerationStep.Decoration.*;
import static net.minecraft.world.level.levelgen.structure.TerrainAdjustment.NONE;

@SuppressWarnings("ExtractMethodRecommender")
public class GeodeDatagen {

    public static void structureSetBootstrap(BootstrapContext<StructureSet> context) {
        set(context, OVERWORLD_GEODES, VIVID_AMETRINE_QUARTZ_GEODE, MARINE_BERYL_QUARTZ_GEODE, RUGGED_CITRINE_QUARTZ_GEODE);
    }

    @SafeVarargs
    public static void set(BootstrapContext<StructureSet> context, StructureKey key, ResourceKey<Structure>... geodes) {
        List<StructureSet.StructureSelectionEntry> entries = new ArrayList<>();

        for (ResourceKey<Structure> geode : geodes) {
            entries.add(StructureSet.entry(context.lookup(Registries.STRUCTURE).getOrThrow(geode), 1));
        }

        context.register(key.structureSet(), new StructureSet(
                entries,
                new RandomSpreadStructurePlacement(Vec3i.ZERO, StructurePlacement.FrequencyReductionMethod.DEFAULT, 0.7f, 20083232, Optional.empty(), 12, 3, RandomSpreadType.TRIANGULAR)
        ));
    }


    public static void structureBootstrap(BootstrapContext<Structure> context) {
        geode(context, VIVID_AMETRINE_QUARTZ_GEODE, VIVID_AMETRINE);
        geode(context, MARINE_BERYL_QUARTZ_GEODE, MARINE_AGATE);
        geode(context, RUGGED_CITRINE_QUARTZ_GEODE, RUGGED_CITRINE);
    }

    public static void geode(BootstrapContext<Structure> context, ResourceKey<Structure> key, GeodeCrystalRegistrySet set) {
        float airPocket = 9f;

        var buddingQuartzGeodes = of(6, 8);
        var quartzClusters = of(200, 300);
        float quartzLayerSize = 0.7f;

        var buddingGemGeodes = of(4, 6);
        var gemClusters = of(40, 60);
        float gemLayerSize = 1.4f;


        var quartzLayer = new GeodeLayer(MUNDANE_QUARTZ, buddingQuartzGeodes, quartzClusters, quartzLayerSize);
        var gemLayer = new GeodeLayer(set, buddingGemGeodes, gemClusters, gemLayerSize);

        var layers = List.of(new GeodeLayer(AIR, airPocket), quartzLayer, gemLayer, quartzLayer,
                new GeodeLayer(CALCITE, 1.2f), new GeodeLayer(SMOOTH_BASALT, 1.4f)
        );

        var geodeBlockSettings = new MalumGeodeLayerSettings(layers, BlockTags.FEATURES_CANNOT_REPLACE, BlockTags.GEODE_INVALID_BLOCKS);
        var geodeCrackSettings = new MalumGeodeCrackSettings(List.of(
                new GeodeAnchor(of(0, 4), UniformFloat.of(1f, 1.5f), UniformFloat.of(0.3f, 0.6f))
        ),
                0.85f, 0.75f);
        var geodeAnchorSettings = new GeodeAnchorSettings(List.of(
                new GeodeAnchor(of(4, 6), UniformFloat.of(0.1f, 0.2f), UniformFloat.of(0.8f, 0.9f)),
                new GeodeAnchor(of(4, 14), UniformFloat.of(0.2f, 0.3f), UniformFloat.of(0.6f, 0.7f))
        ));

        structure(context, key, b -> new MalumGeodeStructure(b, new MalumGeodeConfiguration(
                        geodeBlockSettings, geodeCrackSettings, geodeAnchorSettings, Optional.empty(), 1
                ), UniformHeight.of(VerticalAnchor.aboveBottom(24), VerticalAnchor.absolute(16))),
                set.getBiomeTag(), UNDERGROUND_DECORATION, NONE);

    }
}