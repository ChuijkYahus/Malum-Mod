package com.sammy.malum.datagen;

import com.sammy.malum.common.data.map.*;
import com.sammy.malum.registry.common.block.*;
import com.sammy.malum.registry.common.item.MalumItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.*;
import org.jetbrains.annotations.*;

import java.util.concurrent.CompletableFuture;

import static com.sammy.malum.registry.common.MalumDataMaps.*;

public class MalumDataMapDatagen extends DataMapProvider {

    protected MalumDataMapDatagen(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.@NotNull Provider provider) {
        builder(FRACTURED_IMPETUS_VARIANT)
                .add(MalumItems.IRON_IMPETUS, new ImpetusDataMap(MalumItems.FRACTURED_IRON_IMPETUS), false)
                .add(MalumItems.COPPER_IMPETUS, new ImpetusDataMap(MalumItems.FRACTURED_COPPER_IMPETUS), false)
                .add(MalumItems.GOLD_IMPETUS, new ImpetusDataMap(MalumItems.FRACTURED_GOLD_IMPETUS), false)
                .add(MalumItems.ALUMINUM_IMPETUS, new ImpetusDataMap(MalumItems.FRACTURED_ALUMINUM_IMPETUS), false)
                .add(MalumItems.NICKEL_IMPETUS, new ImpetusDataMap(MalumItems.FRACTURED_NICKEL_IMPETUS), false)
                .add(MalumItems.SILVER_IMPETUS, new ImpetusDataMap(MalumItems.FRACTURED_SILVER_IMPETUS), false)
                .add(MalumItems.TIN_IMPETUS, new ImpetusDataMap(MalumItems.FRACTURED_TIN_IMPETUS), false)
                .add(MalumItems.ZINC_IMPETUS, new ImpetusDataMap(MalumItems.FRACTURED_ZINC_IMPETUS), false)
                .add(MalumItems.OSMIUM_IMPETUS, new ImpetusDataMap(MalumItems.FRACTURED_OSMIUM_IMPETUS), false)
                .add(MalumItems.LEAD_IMPETUS, new ImpetusDataMap(MalumItems.FRACTURED_LEAD_IMPETUS), false)
                .add(MalumItems.URANIUM_IMPETUS, new ImpetusDataMap(MalumItems.FRACTURED_URANIUM_IMPETUS), false)
                .add(MalumItems.COBALT_IMPETUS, new ImpetusDataMap(MalumItems.FRACTURED_COBALT_IMPETUS), false)
                .add(MalumItems.ZEPHYR_IMPETUS, new ImpetusDataMap(MalumItems.FRACTURED_ZEPHYR_IMPETUS), false)
                .add(MalumItems.IFRIT_IMPETUS, new ImpetusDataMap(MalumItems.FRACTURED_IFRIT_IMPETUS), false)
                .add(MalumItems.ALCHEMICAL_IMPETUS, new ImpetusDataMap(MalumItems.FRACTURED_ALCHEMICAL_IMPETUS), false);

        builder(REPAIRED_IMPETUS_VARIANT)
                .add(MalumItems.FRACTURED_IRON_IMPETUS, new ImpetusDataMap(MalumItems.IRON_IMPETUS), false)
                .add(MalumItems.FRACTURED_COPPER_IMPETUS, new ImpetusDataMap(MalumItems.COPPER_IMPETUS), false)
                .add(MalumItems.FRACTURED_GOLD_IMPETUS, new ImpetusDataMap(MalumItems.GOLD_IMPETUS), false)
                .add(MalumItems.FRACTURED_ALUMINUM_IMPETUS, new ImpetusDataMap(MalumItems.ALUMINUM_IMPETUS), false)
                .add(MalumItems.FRACTURED_NICKEL_IMPETUS, new ImpetusDataMap(MalumItems.NICKEL_IMPETUS), false)
                .add(MalumItems.FRACTURED_SILVER_IMPETUS, new ImpetusDataMap(MalumItems.SILVER_IMPETUS), false)
                .add(MalumItems.FRACTURED_TIN_IMPETUS, new ImpetusDataMap(MalumItems.TIN_IMPETUS), false)
                .add(MalumItems.FRACTURED_ZINC_IMPETUS, new ImpetusDataMap(MalumItems.ZINC_IMPETUS), false)
                .add(MalumItems.FRACTURED_OSMIUM_IMPETUS, new ImpetusDataMap(MalumItems.OSMIUM_IMPETUS), false)
                .add(MalumItems.FRACTURED_LEAD_IMPETUS, new ImpetusDataMap(MalumItems.LEAD_IMPETUS), false)
                .add(MalumItems.FRACTURED_URANIUM_IMPETUS, new ImpetusDataMap(MalumItems.URANIUM_IMPETUS), false)
                .add(MalumItems.FRACTURED_COBALT_IMPETUS, new ImpetusDataMap(MalumItems.COBALT_IMPETUS), false)
                .add(MalumItems.FRACTURED_ZEPHYR_IMPETUS, new ImpetusDataMap(MalumItems.ZEPHYR_IMPETUS), false)
                .add(MalumItems.FRACTURED_IFRIT_IMPETUS, new ImpetusDataMap(MalumItems.IFRIT_IMPETUS), false)
                .add(MalumItems.FRACTURED_ALCHEMICAL_IMPETUS, new ImpetusDataMap(MalumItems.ALCHEMICAL_IMPETUS), false);


        builder(TOTEM_POLE_CONVERSION)
                .add(MalumBlocks.RUNEWOOD_LOG, new TotemPoleMap(MalumBlocks.RUNEWOOD_TOTEM_POLE), false)
                .add(MalumBlocks.RUNEWOOD, new TotemPoleMap(MalumBlocks.RUNEWOOD_TOTEM_POLE), false)
                .add(MalumBlocks.SOULWOOD_LOG, new TotemPoleMap(MalumBlocks.SOULWOOD_TOTEM_POLE), false)
                .add(MalumBlocks.SOULWOOD, new TotemPoleMap(MalumBlocks.SOULWOOD_TOTEM_POLE), false)
                .add(MalumBlocks.RUNEWOOD_TOTEM_POLE, new TotemPoleMap(MalumBlocks.SOULWOOD_TOTEM_POLE), false);

        builder(NeoForgeDataMaps.COMPOSTABLES)
                .add(MalumItems.RUNEWOOD_SAPLING, new Compostable(0.3f), false)
                .add(MalumItems.RUNEWOOD_LEAVES, new Compostable(0.3f), false)
                .add(MalumItems.HANGING_RUNEWOOD_LEAVES, new Compostable(0.2f), false)
                .add(MalumItems.AZURE_RUNEWOOD_SAPLING, new Compostable(0.3f), false)
                .add(MalumItems.AZURE_RUNEWOOD_LEAVES, new Compostable(0.3f), false)
                .add(MalumItems.HANGING_AZURE_RUNEWOOD_LEAVES, new Compostable(0.2f), false)
                .add(MalumItems.SOULWOOD_SAPLING, new Compostable(0.3f), false)
                .add(MalumItems.SOULWOOD_LEAVES, new Compostable(0.3f), false)
                .add(MalumItems.HANGING_SOULWOOD_LEAVES, new Compostable(0.2f), false)
                .add(MalumItems.BLIGHTED_GUNK, new Compostable(0.1f), false);
    }
}
