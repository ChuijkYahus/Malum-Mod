package com.sammy.malum.datagen.recipe.builder;

import com.sammy.malum.common.recipe.VoidFavorRecipe;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import team.lodestar.lodestone.modules.toolkit.recipe.LodestoneRecipeBuilder;

public class VoidFavorRecipeBuilder implements LodestoneRecipeBuilder<VoidFavorRecipe> {
    private final Ingredient input;

    private final ItemStack output;

    public VoidFavorRecipeBuilder(Ingredient input, ItemStack output) {
        this.input = input;
        this.output = output;
    }

    public VoidFavorRecipeBuilder(Ingredient input, ItemLike output, int outputCount) {
        this(input, new ItemStack(output, outputCount));
    }

    public VoidFavorRecipeBuilder(ItemLike input, ItemStack output) {
        this(Ingredient.of(input), output);
    }

    public VoidFavorRecipeBuilder(ItemLike input, ItemLike output, int outputCount) {
        this(input, new ItemStack(output, outputCount));
    }

    public void save(RecipeOutput recipeOutput) {
        this.save(recipeOutput, output.getItem());
    }

    @Override
    public VoidFavorRecipe buildRecipe(ResourceLocation resourceLocation) {
        return new VoidFavorRecipe(input, output);
    }

    @Override
    public String getRecipeSubfolder() {
        return "void_favor";
    }
}
