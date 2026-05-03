package com.sammy.malum.datagen.wand;

import com.sammy.malum.MalumMod;
import com.sammy.malum.common.data.custom.wand_parts.WandMaterialType;
import com.sammy.malum.common.data.custom.wand_parts.WandPartType.WandPartGroup;
import com.sammy.malum.registry.common.MalumContent;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.JsonCodecProvider;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.sammy.malum.registry.common.MalumContent.BlockSets.RUNEWOOD_SET;
import static com.sammy.malum.registry.common.MalumContent.BlockSets.SOULWOOD_SET;
import static net.minecraft.data.PackOutput.Target.DATA_PACK;

public final class WandMaterialTypeDatagen extends JsonCodecProvider<WandMaterialType> {

    public WandMaterialTypeDatagen(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, DATA_PACK, "wand/material_types", PackType.SERVER_DATA, WandMaterialType.DIRECT_CODEC, lookupProvider, MalumMod.MALUM, existingFileHelper);
    }

    public enum GroupPreset {
        ALL,
        HEADS,
        DECOR
    }

    @Override
    protected void gather() {
        add("runewood", RUNEWOOD_SET.planksTag.ingredient(), GroupPreset.ALL);
        add("soulwood", SOULWOOD_SET.planksTag.ingredient(), GroupPreset.ALL);

        add("quartz", Ingredient.of(Tags.Items.GEMS_QUARTZ), GroupPreset.HEADS);
        add("ebony", Ingredient.of(MalumContent.Materials.CALCIFIED_EBONY), GroupPreset.HEADS);
    }

    public void add(String id, Ingredient ingredient, WandPartGroup... groups) {
        add(MalumMod.malumPath(id), ingredient, groups);
    }

    public void add(String id, Ingredient ingredient, GroupPreset preset) {
        var groups = switch (preset) {
            case ALL -> WandPartGroup.values();
            case HEADS -> new WandPartGroup[]{WandPartGroup.HEAD};
            case DECOR -> new WandPartGroup[]{WandPartGroup.BASE, WandPartGroup.BAUBLE, WandPartGroup.ORNAMENT};
        };
        add(MalumMod.malumPath(id), ingredient, groups);
    }

    public void add(ResourceLocation id, Ingredient ingredient, WandPartGroup... groups) {
        unconditional(id, new WandMaterialType(id, ingredient, List.of(groups)));
    }
}