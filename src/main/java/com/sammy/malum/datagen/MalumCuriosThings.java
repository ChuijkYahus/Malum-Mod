package com.sammy.malum.datagen;

import com.sammy.malum.*;
import net.minecraft.core.*;
import net.minecraft.data.*;
import net.neoforged.neoforge.common.data.*;

import java.util.concurrent.*;

public class MalumCuriosThings extends top.theillusivec4.curios.api.CuriosDataProvider {

    public MalumCuriosThings(PackOutput output, ExistingFileHelper fileHelper, CompletableFuture<HolderLookup.Provider> registries) {
        super(MalumMod.MALUM, output, fileHelper, registries);
    }

    @Override
    public void generate(HolderLookup.Provider registries, ExistingFileHelper fileHelper) {
        createSlot("brooch")
                .size(1)
                .addCosmetic(false)
                .icon(MalumMod.malumPath("slot/empty_brooch_slot"));
        createSlot("ring")
                .size(2)
                .addCosmetic(false);
        createSlot("necklace")
                .size(1)
                .addCosmetic(false);
        createSlot("belt")
                .size(1)
                .addCosmetic(false);
        createSlot("rune")
                .size(0)
                .renderToggle(false)
                .icon(MalumMod.malumPath("slot/empty_rune_slot"));
        createSlot("geas")
                .size(0)
                .renderToggle(false)
                .icon(MalumMod.malumPath("slot/empty_geas_slot"));

        createSlot("charm")
                .size(1)
                .addCosmetic(true);

        createEntities("malum_entities")
                .addPlayer()
                .addSlots("brooch", "ring", "necklace", "belt", "rune", "geas", "charm");
    }
}
