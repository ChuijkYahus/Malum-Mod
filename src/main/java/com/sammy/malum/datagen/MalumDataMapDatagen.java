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
