package com.sammy.malum.datagen;

import com.sammy.malum.*;
import com.sammy.malum.datagen.worldgen.*;
import com.sammy.malum.datagen.worldgen.configured.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.data.*;
import net.minecraft.data.registries.*;
import net.neoforged.neoforge.common.data.*;
import net.neoforged.neoforge.registries.*;

import java.util.*;
import java.util.concurrent.*;

public class RegistryDataGenerator extends DatapackBuiltinEntriesProvider {

    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.ENCHANTMENT, MalumEnchantmentDatagen::bootstrap)
            .add(Registries.DAMAGE_TYPE, MalumDamageTypeDatagen::bootstrap)
            .add(Registries.STRUCTURE, StructureDatagen::structureBootstrap)
            .add(Registries.STRUCTURE_SET, StructureDatagen::structureSetBootstrap)
            .add(Registries.CONFIGURED_FEATURE, ConfiguredFeatureDatagen::bootstrap)
            .add(Registries.PLACED_FEATURE, PlacedFeatureDatagen::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, BiomeModificationDatagen::bootstrap);

    public RegistryDataGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, RegistryPatchGenerator.createLookup(registries, BUILDER), Set.of("minecraft", MalumMod.MALUM));
    }
}
