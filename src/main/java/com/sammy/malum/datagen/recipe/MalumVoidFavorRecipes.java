package com.sammy.malum.datagen.recipe;

import com.sammy.malum.*;
import com.sammy.malum.datagen.recipe.builder.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.content.MalumContent;
import com.sammy.malum.registry.common.content.item.MalumItemProperties;

import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.neoforged.neoforge.common.Tags;


public class MalumVoidFavorRecipes {

    protected static void buildRecipes(RecipeOutput recipeOutput) {
        new VoidFavorRecipeBuilder(Ingredient.of(MalumContent.ENCYCLOPEDIA_ARCANA.get()), MalumContent.ENCYCLOPEDIA_ESOTERICA.get(), 1)
                .save(recipeOutput);

        new VoidFavorRecipeBuilder(Ingredient.of(MalumTags.Items.VOID_SOULSTONE_CONVERSION), MalumContent.Materials.RAW_SOULSTONE.get(), 1)
                .save(recipeOutput);

        new VoidFavorRecipeBuilder(Ingredient.of(Tags.Items.STORAGE_BLOCKS_IRON), MalumContent.Materials.ANOMALOUS_DESIGN.get(), 1)
                .save(recipeOutput);
        new VoidFavorRecipeBuilder(Ingredient.of(MalumContent.Materials.COMPLETE_DESIGN.get()), MalumContent.Materials.FUSED_CONSCIOUSNESS.get(), 1)
                .save(recipeOutput);

        new VoidFavorRecipeBuilder(MalumContent.Materials.REFINED_SOULSTONE.get(), MalumContent.Materials.NULL_SLATE.get(), 1)
                .save(recipeOutput);

        new VoidFavorRecipeBuilder(MalumContent.Materials.HEX_ASH.get(), MalumContent.Materials.VOID_SALTS.get(), 1)
                .save(recipeOutput);

        new VoidFavorRecipeBuilder(MalumContent.Materials.REFINED_BRILLIANCE.get(), MalumContent.Materials.MNEMONIC_FRAGMENT.get(), 1)
                .save(recipeOutput);
        new VoidFavorRecipeBuilder(MalumContent.Materials.RAW_BRILLIANCE.get(), MalumContent.Materials.MNEMONIC_FRAGMENT.get(), 2)
                .save(recipeOutput, MalumMod.malumPath("mnemonic_fragment_from_cluster"));

        new VoidFavorRecipeBuilder(net.minecraft.world.item.Items.BLAZE_POWDER, MalumContent.Materials.AURIC_EMBERS.get(), 1)
                .save(recipeOutput);

        new VoidFavorRecipeBuilder(MalumContent.Materials.CTHONIC_GOLD.get(), MalumContent.Materials.MALIGNANT_LEAD.get(), 1)
                .save(recipeOutput);

        new VoidFavorRecipeBuilder(Ingredient.of(MalumItemProperties.THE_DEVICE.get()), MalumItemProperties.THE_VESSEL.get(), 1)
                .save(recipeOutput);

        new VoidFavorRecipeBuilder(net.minecraft.world.item.Items.BLACK_WOOL, MalumItemProperties.TOPHAT.get(), 1)
                .save(recipeOutput);

        new VoidFavorRecipeBuilder(MalumContent.ARCANE_ELEGY.get(), MalumContent.AESTHETICA.get(), 1)
                .save(recipeOutput);
    }
}
