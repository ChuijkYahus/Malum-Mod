package com.sammy.malum.datagen;

import com.sammy.malum.common.data.map.*;
import com.sammy.malum.registry.common.MalumContent.BlockSets;
import com.sammy.malum.registry.common.MalumContent.Totemancy;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.*;

import java.util.concurrent.CompletableFuture;

import static com.sammy.malum.registry.common.MalumDataMaps.*;
import static com.sammy.malum.registry.common.MalumContent.*;

public class MalumDataMapDatagen extends DataMapProvider {

    protected MalumDataMapDatagen(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.Provider provider) {
        MalumMetallicsDatagen.MALUM.fillDataMap(builder(SOULSTONE_ORE_CONVERSION));

        builder(TOTEM_POLE_CONVERSION)
                .add(BlockSets.RUNEWOOD_SET.getLog().block(), new TotemPoleConversionMap(Totemancy.RUNEWOOD_TOTEM_POLE), false)
                .add(BlockSets.RUNEWOOD_SET.getWood().block(), new TotemPoleConversionMap(Totemancy.RUNEWOOD_TOTEM_POLE), false)
                .add(BlockSets.SOULWOOD_SET.getLog().block(), new TotemPoleConversionMap(Totemancy.SOULWOOD_TOTEM_POLE), false)
                .add(BlockSets.SOULWOOD_SET.getWood().block(), new TotemPoleConversionMap(Totemancy.SOULWOOD_TOTEM_POLE), false)
                .add(Totemancy.RUNEWOOD_TOTEM_POLE, new TotemPoleConversionMap(Totemancy.SOULWOOD_TOTEM_POLE), false);

        builder(NeoForgeDataMaps.COMPOSTABLES)
                .add(BlockSets.RUNEWOOD_SAPLING.item(), new Compostable(0.4f), false)
                .add(BlockSets.RUNEWOOD_LEAVES.item(), new Compostable(0.3f), false)
                .add(BlockSets.HANGING_RUNEWOOD_LEAVES.item(), new Compostable(0.2f), false)

                .add(BlockSets.AZURE_RUNEWOOD_SAPLING.item(), new Compostable(0.4f), false)
                .add(BlockSets.AZURE_RUNEWOOD_LEAVES.item(), new Compostable(0.3f), false)
                .add(BlockSets.HANGING_AZURE_RUNEWOOD_LEAVES.item(), new Compostable(0.2f), false)

                .add(BlockSets.SOULWOOD_SAPLING.item(), new Compostable(0.4f), false)
                .add(BlockSets.SOULWOOD_LEAVES.item(), new Compostable(0.3f), false)
                .add(BlockSets.HANGING_SOULWOOD_LEAVES.item(), new Compostable(0.2f), false)

                .add(Blight.BLIGHTED_GUNK.item(), new Compostable(0.1f), false);


        builder(NeoForgeDataMaps.FURNACE_FUELS)
                .add(Materials.ARCANE_CHARCOAL, new FurnaceFuel(32000), false)
                .add(CompactBlocks.BLOCK_OF_ARCANE_CHARCOAL.item(), new FurnaceFuel(288000), false);

    }
}
