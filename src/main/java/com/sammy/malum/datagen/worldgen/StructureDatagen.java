package com.sammy.malum.datagen.worldgen;

import com.sammy.malum.common.worldgen.sanctuary.RunicSanctuaryStructure;
import com.sammy.malum.common.worldgen.well.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.worldgen.*;
import net.minecraft.core.registries.*;
import net.minecraft.data.worldgen.*;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.structure.*;
import net.minecraft.world.level.levelgen.structure.placement.*;

import java.util.*;
import java.util.function.Function;

public class StructureDatagen {

    public static void structureBootstrap(BootstrapContext<Structure> context) {
        structure(context, MalumStructureTypes.StructureKeys.WEEPING_WELL, WeepingWellStructure::new, MalumTags.Biomes.HAS_WEEPING_WELL, GenerationStep.Decoration.UNDERGROUND_STRUCTURES, TerrainAdjustment.NONE);
        structure(context, MalumStructureTypes.StructureKeys.RUNIC_SANCTUARY, RunicSanctuaryStructure::new, MalumTags.Biomes.HAS_RUNIC_SANCTUARY, GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.NONE);
        structure(context, MalumStructureTypes.StructureKeys.AZURE_SANCTUARY, RunicSanctuaryStructure::new, MalumTags.Biomes.HAS_AZURE_SANCTUARY, GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.NONE);
    }

    public static void structureSetBootstrap(BootstrapContext<StructureSet> context) {
        set(context, MalumStructureTypes.StructureKeys.WEEPING_WELL, 24, 18, 546451666);
        set(context, MalumStructureTypes.StructureKeys.RUNIC_SANCTUARY, 32, 24, 546451665);
        set(context, MalumStructureTypes.StructureKeys.AZURE_SANCTUARY, 32, 24, 546451665);
    }

    private static void structure(BootstrapContext<Structure> context, MalumStructureTypes.StructureKeys.StructureKey key, Function<Structure.StructureSettings, Structure> structure, TagKey<Biome> tag, GenerationStep.Decoration decoration, TerrainAdjustment adj) {
        var holder = context.lookup(Registries.BIOME).getOrThrow(tag);
        context.register(key.structure(), structure.apply(new Structure.StructureSettings(holder, Map.of(), decoration, adj)));

    }
    private static void set(BootstrapContext<StructureSet> context, MalumStructureTypes.StructureKeys.StructureKey key, int spacing, int separation, int salt) {
        context.register(key.structureSet(), new StructureSet(
                List.of(StructureSet.entry(context.lookup(Registries.STRUCTURE).getOrThrow(key.structure()))),
                new RandomSpreadStructurePlacement(spacing, separation, RandomSpreadType.TRIANGULAR, salt)));
    }

}