package com.sammy.malum.datagen.recipe;

import com.sammy.malum.*;
import com.sammy.malum.datagen.recipe.builder.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.MalumContent;
import com.sammy.malum.registry.common.MalumContent.Materials;
import com.sammy.malum.registry.common.item.MalumItemProperties;

import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.neoforged.neoforge.common.Tags;

import static com.sammy.malum.registry.common.MalumContent.*;
import static com.sammy.malum.registry.common.MalumContent.BlockSets.*;
import static com.sammy.malum.registry.common.MalumContent.Materials.*;
import static com.sammy.malum.registry.common.MalumContent.Vanity.*;
import static net.minecraft.world.item.Items.*;


public class MalumVoidFavorRecipes {

    protected static void buildRecipes(RecipeOutput recipeOutput) {
        new VoidFavorRecipeBuilder(Ingredient.of(ENCYCLOPEDIA_ARCANA), ENCYCLOPEDIA_ESOTERICA, 1)
                .save(recipeOutput);

        new VoidFavorRecipeBuilder(Ingredient.of(MalumTags.Items.VOID_SOULSTONE_CONVERSION), RAW_SOULSTONE, 1)
                .save(recipeOutput);

        new VoidFavorRecipeBuilder(Ingredient.of(Tags.Items.STORAGE_BLOCKS_IRON), ANOMALOUS_DESIGN, 1)
                .save(recipeOutput);
        new VoidFavorRecipeBuilder(Ingredient.of(COMPLETE_DESIGN), FUSED_CONSCIOUSNESS, 1)
                .save(recipeOutput);

        new VoidFavorRecipeBuilder(REFINED_SOULSTONE, NULL_SLATE, 1)
                .save(recipeOutput);

        new VoidFavorRecipeBuilder(HEX_ASH, VOID_SALTS, 1)
                .save(recipeOutput);

        new VoidFavorRecipeBuilder(REFINED_BRILLIANCE, MNEMONIC_FRAGMENT, 1)
                .save(recipeOutput);
        new VoidFavorRecipeBuilder(RAW_BRILLIANCE, MNEMONIC_FRAGMENT, 2)
                .save(recipeOutput, MalumMod.malumPath("mnemonic_fragment_from_cluster"));

        new VoidFavorRecipeBuilder(BLAZE_POWDER, AURIC_EMBERS, 1)
                .save(recipeOutput);

        new VoidFavorRecipeBuilder(CTHONIC_GOLD, MALIGNANT_LEAD, 1)
                .save(recipeOutput);

        new VoidFavorRecipeBuilder(Ingredient.of(THE_DEVICE), THE_VESSEL, 1)
                .save(recipeOutput);

        new VoidFavorRecipeBuilder(ARCANE_ELEGY, AESTHETICA, 1)
                .save(recipeOutput);
    }
}
