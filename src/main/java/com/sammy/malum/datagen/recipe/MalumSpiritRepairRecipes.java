package com.sammy.malum.datagen.recipe;

import com.sammy.malum.*;
import com.sammy.malum.common.item.impetus.*;
import com.sammy.malum.datagen.recipe.builder.*;
import com.sammy.malum.registry.common.item.*;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import static com.sammy.malum.registry.common.MalumSpiritTypes.*;

public class MalumSpiritRepairRecipes implements IConditionBuilder {

    protected static void buildRecipes(RecipeOutput recipeOutput) {
        var has = MalumRecipes.has(MalumItems.SPIRIT_CRUCIBLE.get());
        new SpiritRepairRecipeBuilder("wooden_.+", 0.5f, Ingredient.of(ItemTags.PLANKS), 4)
                .addSpirit(SACRED_SPIRIT, 4)
                .addSpirit(EARTHEN_SPIRIT, 4)
                .addItem(Items.BOW)
                .addItem(Items.CROSSBOW)
                .unlockedBy("has_crucible", has)
                .save(recipeOutput, MalumMod.malumPath("wooden_restoration"));

        new SpiritRepairRecipeBuilder("flint_.+", 0.5f, Ingredient.of(Items.FLINT), 2)
                .addSpirit(EARTHEN_SPIRIT, 4)
                .unlockedBy("has_crucible", has)
                .save(recipeOutput, MalumMod.malumPath("flint_restoration"));

        new SpiritRepairRecipeBuilder("stone_.+", 0.5f, Ingredient.of(ItemTags.STONE_TOOL_MATERIALS), 2)
                .addSpirit(EARTHEN_SPIRIT, 4)
                .unlockedBy("has_crucible", has)
                .save(recipeOutput, MalumMod.malumPath("stone_restoration"));

        new SpiritRepairRecipeBuilder("copper_.+", 0.5f, Ingredient.of(Tags.Items.INGOTS_COPPER), 2)
                .addSpirit(EARTHEN_SPIRIT, 6)
                .unlockedBy("has_crucible", has)
                .save(recipeOutput, MalumMod.malumPath("copper_restoration"));

        new SpiritRepairRecipeBuilder("iron_.+", 0.5f, Ingredient.of(Tags.Items.INGOTS_IRON), 2)
                .addItem(MalumItems.CRUDE_SCYTHE.get())
                .addSpirit(EARTHEN_SPIRIT, 8)
                .unlockedBy("has_crucible", has)
                .save(recipeOutput, MalumMod.malumPath("iron_restoration"));

        new SpiritRepairRecipeBuilder("golden_.+", 0.5f, Ingredient.of(Tags.Items.INGOTS_GOLD), 2)
                .addSpirit(ARCANE_SPIRIT, 8)
                .unlockedBy("has_crucible", has)
                .save(recipeOutput, MalumMod.malumPath("gold_restoration"));

        new SpiritRepairRecipeBuilder("diamond_.+", 0.5f, Ingredient.of(Tags.Items.GEMS_DIAMOND), 2)
                .addSpirit(ARCANE_SPIRIT, 16)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .unlockedBy("has_crucible", has)
                .save(recipeOutput, MalumMod.malumPath("diamond_restoration"));

        new SpiritRepairRecipeBuilder("netherite_.+", 0.5f, Ingredient.of(Tags.Items.INGOTS_NETHERITE), 1)
                .addSpirit(INFERNAL_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .unlockedBy("has_crucible", has)
                .save(recipeOutput, MalumMod.malumPath("netherite_restoration"));

        new SpiritRepairRecipeBuilder(0.5f, Ingredient.of(Items.PRISMARINE_CRYSTALS), 8)
                .addItem(Items.TRIDENT)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .unlockedBy("has_crucible", has)
                .save(recipeOutput, MalumMod.malumPath("trident_restoration"));

        new SpiritRepairRecipeBuilder(0.5f, Ingredient.of(Items.WIND_CHARGE), 8)
                .addItem(Items.MACE)
                .addSpirit(AERIAL_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .unlockedBy("has_crucible", has)
                .save(recipeOutput, MalumMod.malumPath("mace_restoration"));

        new SpiritRepairRecipeBuilder(0.75f, Ingredient.of(Items.OBSIDIAN), 2)
                .addItem(MalumItems.TYRVING.get())
                .addSpirit(WICKED_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 8)
                .addSpirit(EARTHEN_SPIRIT, 8)
                .addSpirit(ELDRITCH_SPIRIT, 4)
                .unlockedBy("has_crucible", has)
                .save(recipeOutput, MalumMod.malumPath("tyrving_restoration"));

        new SpiritRepairRecipeBuilder(0.75f, Ingredient.of(MalumItems.SOUL_STAINED_STEEL_INGOT.get()), 2)
                .addItem(MalumItems.SOUL_STAINED_STEEL_SCYTHE.get())
                .addItem(MalumItems.SOUL_STAINED_STEEL_HELMET.get())
                .addItem(MalumItems.SOUL_STAINED_STEEL_CHESTPLATE.get())
                .addItem(MalumItems.SOUL_STAINED_STEEL_LEGGINGS.get())
                .addItem(MalumItems.SOUL_STAINED_STEEL_BOOTS.get())
                .addSpirit(WICKED_SPIRIT, 8)
                .addSpirit(ARCANE_SPIRIT, 8)
                .addSpirit(EARTHEN_SPIRIT, 4)
                .unlockedBy("has_crucible", has)
                .save(recipeOutput, MalumMod.malumPath("special_soul_stained_steel_restoration"));

        new SpiritRepairRecipeBuilder(0.75f, Ingredient.of(MalumItems.SOUL_STAINED_STEEL_INGOT.get()), 2)
                .addItem(MalumItems.SOUL_STAINED_STEEL_SWORD.get())
                .addItem(MalumItems.SOUL_STAINED_STEEL_AXE.get())
                .addItem(MalumItems.SOUL_STAINED_STEEL_PICKAXE.get())
                .addItem(MalumItems.SOUL_STAINED_STEEL_SHOVEL.get())
                .addItem(MalumItems.SOUL_STAINED_STEEL_HOE.get())
                .addItem(MalumItems.SOUL_STAINED_STEEL_KNIFE.get())
                .addSpirit(WICKED_SPIRIT, 8)
                .addSpirit(ARCANE_SPIRIT, 8)
                .addSpirit(EARTHEN_SPIRIT, 4)
                .unlockedBy("has_crucible", has)
                .save(recipeOutput, MalumMod.malumPath("soul_stained_steel_restoration"));

        new SpiritRepairRecipeBuilder(0.75f, Ingredient.of(MalumItems.SOULWOVEN_SILK.get()), 2)
                .addItem(MalumItems.SOUL_HUNTER_CLOAK.get())
                .addItem(MalumItems.SOUL_HUNTER_ROBE.get())
                .addItem(MalumItems.SOUL_HUNTER_LEGGINGS.get())
                .addItem(MalumItems.SOUL_HUNTER_BOOTS.get())
                .addSpirit(WICKED_SPIRIT, 8)
                .addSpirit(ARCANE_SPIRIT, 8)
                .addSpirit(AERIAL_SPIRIT, 4)
                .unlockedBy("has_crucible", has)
                .save(recipeOutput, MalumMod.malumPath("soul_hunter_armor_restoration"));

        new SpiritRepairRecipeBuilder(1f, Ingredient.of(MalumItems.ALCHEMICAL_CALX.get()), 2)
                .addItem(MalumItems.FRACTURED_ALCHEMICAL_IMPETUS.get())
                .addSpirit(ARCANE_SPIRIT, 4)
                .addSpirit(EARTHEN_SPIRIT, 4)
                .overrideOutput(MalumItems.ALCHEMICAL_IMPETUS.get())
                .unlockedBy("has_crucible", has)
                .save(recipeOutput, MalumMod.malumPath("alchemical_impetus_restoration"));

        new SpiritRepairRecipeBuilder(1f, Ingredient.of(MalumItems.WIND_NUCLEUS.get()), 4)
                .addItem(MalumItems.FRACTURED_ZEPHYR_IMPETUS.get())
                .addSpirit(ARCANE_SPIRIT, 8)
                .addSpirit(AERIAL_SPIRIT, 8)
                .overrideOutput(MalumItems.ZEPHYR_IMPETUS.get())
                .unlockedBy("has_crucible", has)
                .save(recipeOutput, MalumMod.malumPath("zephyr_impetus_restoration"));

        var crackedMetalImpetus = MalumItems.ITEMS.getEntries()
                .stream().filter(i -> i.get() instanceof CrackedImpetusItem).sorted((d1, d2) -> d1.getId().compareNamespaced(d2.getId())).toList();
        var metalImpetus = MalumItems.ITEMS.getEntries()
                .stream().filter(i -> i.get() instanceof ImpetusItem).sorted((d1, d2) -> d1.getId().compareNamespaced(d2.getId())).toList();
        for (int i = 0; i < crackedMetalImpetus.size(); i++) {
            var metal = metalImpetus.get(i);
            var cracked = crackedMetalImpetus.get(i);
            if (metal.get().equals(MalumItems.ALCHEMICAL_IMPETUS.get()) || metal.get().equals(MalumItems.ZEPHYR_IMPETUS.get())) {
                continue;
            }
            var id = cracked.getId().withPath(cracked.getId().getPath().split("_")[1] + "_impetus_restoration");
            new SpiritRepairRecipeBuilder(1f, Ingredient.of(MalumItems.CTHONIC_GOLD_FRAGMENT.get()), 2)
                    .addItem(cracked.get())
                    .overrideOutput(metal.get())
                    .addSpirit(AQUEOUS_SPIRIT, 16)
                    .unlockedBy("has_crucible", has)
                    .save(recipeOutput, id);
        }
    }
}