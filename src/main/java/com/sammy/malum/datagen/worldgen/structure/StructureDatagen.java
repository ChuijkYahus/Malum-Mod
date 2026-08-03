package com.sammy.malum.datagen.worldgen.structure;

import com.sammy.malum.common.worldgen.well.*;
import com.sammy.malum.registry.common.worldgen.*;
import net.minecraft.core.registries.*;
import net.minecraft.data.worldgen.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.structure.*;
import net.minecraft.world.level.levelgen.structure.placement.*;

import java.util.*;
import java.util.function.Function;

import static com.sammy.malum.registry.common.MalumTags.Biomes.*;
import static net.minecraft.world.level.levelgen.GenerationStep.Decoration.*;
import static net.minecraft.world.level.levelgen.structure.TerrainAdjustment.*;

public class StructureDatagen {

    public static void structureBootstrap(BootstrapContext<Structure> context) {
        structure(context, MalumStructureTypes.StructureKeys.WEEPING_WELL, WeepingWellStructure::new, HAS_WEEPING_WELL, UNDERGROUND_STRUCTURES, NONE);

        GeodeDatagen.structureBootstrap(context);
    }

    public static void structureSetBootstrap(BootstrapContext<StructureSet> context) {
        set(context, MalumStructureTypes.StructureKeys.WEEPING_WELL, 24, 18, 546451666);

        GeodeDatagen.structureSetBootstrap(context);
    }

    public static void structure(BootstrapContext<Structure> context, MalumStructureTypes.StructureKeys.StructureKey key, Function<Structure.StructureSettings, Structure> structure, TagKey<Biome> tag, Decoration decoration, TerrainAdjustment adj) {
        structure(context, key.structure(), structure, tag, decoration, adj);
    }

    public static void structure(BootstrapContext<Structure> context, ResourceKey<Structure> key, Function<Structure.StructureSettings, Structure> structure, TagKey<Biome> tag, Decoration decoration, TerrainAdjustment adj) {
        var holder = context.lookup(Registries.BIOME).getOrThrow(tag);
        context.register(key, structure.apply(new Structure.StructureSettings(holder, Map.of(), decoration, adj)));
    }

    public static void set(BootstrapContext<StructureSet> context, MalumStructureTypes.StructureKeys.StructureKey key, int spacing, int separation, int salt) {
        context.register(key.structureSet(), new StructureSet(
                List.of(StructureSet.entry(context.lookup(Registries.STRUCTURE).getOrThrow(key.structure()))),
                new RandomSpreadStructurePlacement(spacing, separation, RandomSpreadType.TRIANGULAR, salt)));
    }
}