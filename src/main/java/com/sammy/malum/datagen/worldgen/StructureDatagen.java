package com.sammy.malum.datagen.worldgen;

import com.sammy.malum.common.worldgen.well.*;
import com.sammy.malum.registry.common.worldgen.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.data.worldgen.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.heightproviders.*;
import net.minecraft.world.level.levelgen.structure.*;
import net.minecraft.world.level.levelgen.structure.placement.*;
import net.minecraft.world.level.levelgen.structure.pools.*;
import net.minecraft.world.level.levelgen.structure.structures.*;

import java.util.*;

public class StructureDatagen {

    public static void structureBootstrap(BootstrapContext<Structure> context) {
        context.register(MalumStructureKeys.WEEPING_WELL_STRUCTURE_KEY,
                new WeepingWellStructure(
                        structure(context.lookup(Registries.BIOME).getOrThrow(BiomeTagRegistry.HAS_WEEPING_WELL), GenerationStep.Decoration.UNDERGROUND_STRUCTURES, TerrainAdjustment.NONE)
                )
        );
    }

    public static void structureSetBootstrap(BootstrapContext<StructureSet> context) {
        context.register(MalumStructureKeys.WEEPING_WELL_STRUCTURE_SET_KEY, new StructureSet(
                List.of(StructureSet.entry(context.lookup(Registries.STRUCTURE).getOrThrow(MalumStructureKeys.WEEPING_WELL_STRUCTURE_KEY))),
                new RandomSpreadStructurePlacement(24, 18, RandomSpreadType.TRIANGULAR, 546451665)));
    }

    private static Structure.StructureSettings structure(HolderSet<Biome> tag, TerrainAdjustment adj) {
        return new Structure.StructureSettings(tag, Map.of(), GenerationStep.Decoration.SURFACE_STRUCTURES, adj);
    }

    private static Structure.StructureSettings structure(HolderSet<Biome> tag, Map<MobCategory, StructureSpawnOverride> spawnOverrides, TerrainAdjustment adj) {
        return new Structure.StructureSettings(tag, spawnOverrides, GenerationStep.Decoration.SURFACE_STRUCTURES, adj);
    }

    private static Structure.StructureSettings structure(HolderSet<Biome> tag, GenerationStep.Decoration decoration, TerrainAdjustment adj) {
        return new Structure.StructureSettings(tag, Map.of(), decoration, adj);
    }

    private static JigsawStructure createJigsaw(Structure.StructureSettings settings, Holder<StructureTemplatePool> startPool, int maxDepth, HeightProvider startHeight, Heightmap.Types projectStartToHeightmap) {
        return new JigsawStructure(settings, startPool, maxDepth, startHeight, false, projectStartToHeightmap);
    }

    private static JigsawStructure createJigsawWithExpansion(Structure.StructureSettings settings, Holder<StructureTemplatePool> startPool, int maxDepth, HeightProvider startHeight, Heightmap.Types projectStartToHeightmap) {
        return new JigsawStructure(settings, startPool, maxDepth, startHeight, true, projectStartToHeightmap);
    }


    @FunctionalInterface
    public interface StructureFactory {
        Structure generate(BootstrapContext<Structure> structureFactoryBootstapContext);
    }
}