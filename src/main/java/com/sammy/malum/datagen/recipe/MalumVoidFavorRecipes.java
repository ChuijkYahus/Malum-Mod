package com.sammy.malum.datagen.recipe;

import com.sammy.malum.*;
import com.sammy.malum.datagen.recipe.builder.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.item.*;

import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.neoforged.neoforge.common.Tags;


public class MalumVoidFavorRecipes {

    protected static void buildRecipes(RecipeOutput recipeOutput) {
        new VoidFavorRecipeBuilder(Ingredient.of(MalumItems.ENCYCLOPEDIA_ARCANA.get()), MalumItems.ENCYCLOPEDIA_ESOTERICA.get(), 1)
                .save(recipeOutput);

        new VoidFavorRecipeBuilder(Ingredient.of(MalumTags.Items.VOID_SOULSTONE_CONVERSION), MalumItems.RAW_SOULSTONE.get(), 1)
                .save(recipeOutput);

        new VoidFavorRecipeBuilder(Ingredient.of(Tags.Items.STORAGE_BLOCKS_IRON), MalumItems.ANOMALOUS_DESIGN.get(), 1)
                .save(recipeOutput);
        new VoidFavorRecipeBuilder(Ingredient.of(MalumItems.COMPLETE_DESIGN.get()), MalumItems.FUSED_CONSCIOUSNESS.get(), 1)
                .save(recipeOutput);

        new VoidFavorRecipeBuilder(MalumItems.REFINED_SOULSTONE.get(), MalumItems.NULL_SLATE.get(), 1)
                .save(recipeOutput);

        new VoidFavorRecipeBuilder(MalumItems.HEX_ASH.get(), MalumItems.VOID_SALTS.get(), 1)
                .save(recipeOutput);

        new VoidFavorRecipeBuilder(MalumItems.REFINED_BRILLIANCE.get(), MalumItems.MNEMONIC_FRAGMENT.get(), 1)
                .save(recipeOutput);
        new VoidFavorRecipeBuilder(MalumItems.RAW_BRILLIANCE.get(), MalumItems.MNEMONIC_FRAGMENT.get(), 2)
                .save(recipeOutput, MalumMod.malumPath("mnemonic_fragment_from_cluster"));

        new VoidFavorRecipeBuilder(net.minecraft.world.item.Items.BLAZE_POWDER, MalumItems.AURIC_EMBERS.get(), 1)
                .save(recipeOutput);

        new VoidFavorRecipeBuilder(MalumItems.CTHONIC_GOLD.get(), MalumItems.MALIGNANT_LEAD.get(), 1)
                .save(recipeOutput);

        new VoidFavorRecipeBuilder(Ingredient.of(MalumItems.THE_DEVICE.get()), MalumItems.THE_VESSEL.get(), 1)
                .save(recipeOutput);

        new VoidFavorRecipeBuilder(net.minecraft.world.item.Items.BLACK_WOOL, MalumItems.TOPHAT.get(), 1)
                .save(recipeOutput);

        new VoidFavorRecipeBuilder(MalumItems.ARCANE_ELEGY.get(), MalumItems.AESTHETICA.get(), 1)
                .save(recipeOutput);
    }
}
