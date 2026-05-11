package com.sammy.malum.datagen.wand;

import com.sammy.malum.MalumMod;
import com.sammy.malum.common.data.custom.wand_parts.WandMaterialType;
import com.sammy.malum.common.data.custom.wand_parts.WandPartType.WandPartGroup;
import com.sammy.malum.registry.common.MalumContent;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
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
        METAL,
        CORE,
        HEAD,
        DECOR
    }

    @Override
    protected void gather() {
        add("runewood", RUNEWOOD_SET.planksTag.ingredient(), GroupPreset.ALL);
        add("soulwood", SOULWOOD_SET.planksTag.ingredient(), GroupPreset.ALL);

        add("ebony_stalk", Ingredient.of(MalumContent.Materials.EBONY_STALK), GroupPreset.CORE);

        add("quartz", Ingredient.of(Tags.Items.GEMS_QUARTZ), GroupPreset.HEAD);
        add("calcified_ebony", Ingredient.of(MalumContent.Materials.CALCIFIED_EBONY), GroupPreset.HEAD);


        addWood(Items.OAK_PLANKS);
        addWood(Items.SPRUCE_PLANKS);
        addWood(Items.BIRCH_PLANKS);
        addWood(Items.JUNGLE_PLANKS);
        addWood(Items.ACACIA_PLANKS);
        addWood(Items.CHERRY_PLANKS);
        addWood(Items.DARK_OAK_PLANKS);
        addWood(Items.MANGROVE_PLANKS);
        addWood(Items.BAMBOO_PLANKS);
        addWood(Items.CRIMSON_PLANKS);
        addWood(Items.WARPED_PLANKS);
    }

    @SuppressWarnings({"deprecation", "DataFlowIssue"})
    public void addWood(Item planks) {
        var id = planks.builtInRegistryHolder().getKey().location().getPath().replace("_planks", "_wood");
        add(id, Ingredient.of(planks), GroupPreset.ALL);
    }

    public void add(String id, Ingredient ingredient, WandPartGroup... groups) {
        add(MalumMod.malumPath(id), ingredient, groups);
    }

    public void add(String id, Item item, GroupPreset preset) {
        add(id, Ingredient.of(item), preset);
    }

    public void add(String id, Ingredient ingredient, GroupPreset preset) {
        var groups = switch (preset) {
            case ALL -> WandPartGroup.values();
            case METAL -> new WandPartGroup[]{WandPartGroup.HEAD, WandPartGroup.BASE, WandPartGroup.BAUBLE, WandPartGroup.ORNAMENT};

            case CORE -> new WandPartGroup[]{WandPartGroup.CORE};
            case HEAD -> new WandPartGroup[]{WandPartGroup.HEAD};
            case DECOR -> new WandPartGroup[]{WandPartGroup.BASE, WandPartGroup.BAUBLE, WandPartGroup.ORNAMENT};
        };
        add(MalumMod.malumPath(id), ingredient, groups);
    }

    public void add(ResourceLocation id, Ingredient ingredient, WandPartGroup... groups) {
        unconditional(id, new WandMaterialType(id, ingredient, List.of(groups)));
    }
}