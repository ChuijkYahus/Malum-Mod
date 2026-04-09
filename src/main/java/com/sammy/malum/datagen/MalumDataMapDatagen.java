package com.sammy.malum.datagen;

import com.sammy.malum.common.data.map.*;
import com.sammy.malum.registry.common.content.MalumContent;
import com.sammy.malum.registry.common.content.block.*;
import com.sammy.malum.registry.common.content.item.MalumItemProperties;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.*;

import java.util.concurrent.CompletableFuture;

import static com.sammy.malum.registry.common.MalumDataMaps.*;

public class MalumDataMapDatagen extends DataMapProvider {

    protected MalumDataMapDatagen(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.Provider provider) {
        MalumMetallicsDatagen.MALUM.fillDataMap(builder(SOULSTONE_ORE_CONVERSION));

        builder(TOTEM_POLE_CONVERSION)
                .add(MalumBlocks.RUNEWOOD_LOG, new TotemPoleConversionMap(MalumContent.Progression.RUNEWOOD_TOTEM_POLE), false)
                .add(MalumBlocks.RUNEWOOD, new TotemPoleConversionMap(MalumContent.Progression.RUNEWOOD_TOTEM_POLE), false)
                .add(MalumBlocks.SOULWOOD_LOG, new TotemPoleConversionMap(MalumContent.Progression.SOULWOOD_TOTEM_POLE), false)
                .add(MalumBlocks.SOULWOOD, new TotemPoleConversionMap(MalumContent.Progression.SOULWOOD_TOTEM_POLE), false)
                .add(MalumContent.Progression.RUNEWOOD_TOTEM_POLE, new TotemPoleConversionMap(MalumContent.Progression.SOULWOOD_TOTEM_POLE), false);

        builder(NeoForgeDataMaps.COMPOSTABLES)
                .add(MalumItemProperties.RUNEWOOD_SAPLING, new Compostable(0.3f), false)
                .add(MalumItemProperties.RUNEWOOD_LEAVES, new Compostable(0.3f), false)
                .add(MalumItemProperties.HANGING_RUNEWOOD_LEAVES, new Compostable(0.2f), false)
                .add(MalumItemProperties.AZURE_RUNEWOOD_SAPLING, new Compostable(0.3f), false)
                .add(MalumItemProperties.AZURE_RUNEWOOD_LEAVES, new Compostable(0.3f), false)
                .add(MalumItemProperties.HANGING_AZURE_RUNEWOOD_LEAVES, new Compostable(0.2f), false)
                .add(MalumItemProperties.SOULWOOD_SAPLING, new Compostable(0.3f), false)
                .add(MalumItemProperties.SOULWOOD_LEAVES, new Compostable(0.3f), false)
                .add(MalumItemProperties.HANGING_SOULWOOD_LEAVES, new Compostable(0.2f), false)
                .add(MalumItemProperties.BLIGHTED_GUNK, new Compostable(0.1f), false);


        builder(NeoForgeDataMaps.FURNACE_FUELS)
                .add(MalumContent.Materials.ARCANE_CHARCOAL, new FurnaceFuel(32000), false)
                .add(MalumContent.Materials.BLOCK_OF_ARCANE_CHARCOAL.item(), new FurnaceFuel(288000), false);
    }
}
