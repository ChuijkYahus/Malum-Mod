package com.sammy.malum.data.recipe.builder;

import com.sammy.malum.common.recipe.*;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import team.lodestar.lodestone.recipe.builder.LodestoneRecipeBuilder;

import javax.annotation.Nullable;

public class UnchainedTransmutationRecipeBuilder implements LodestoneRecipeBuilder<SpiritTransmutationRecipe> {
    private final Ingredient ingredient;
    private final ItemStack output;

    @Nullable
    private String group = "";

    public UnchainedTransmutationRecipeBuilder(Ingredient input, ItemStack output) {
        ingredient = input;
        this.output = output;
    }

    public UnchainedTransmutationRecipeBuilder(ItemLike input, ItemLike output) {
        this(Ingredient.of(input), new ItemStack(output));
    }

    public UnchainedTransmutationRecipeBuilder(ItemStack input, ItemLike output) {
        this(Ingredient.of(input), new ItemStack(output));
    }

    public UnchainedTransmutationRecipeBuilder group(@Nullable String group) {
        this.group = group;
        return this;
    }

    @Override
    public SpiritTransmutationRecipe buildRecipe(ResourceLocation resourceLocation) {
        return new SpiritTransmutationRecipe(ingredient, output, group);
    }

    public void save(RecipeOutput recipeOutput) {
        LodestoneRecipeBuilder.super.save(recipeOutput, output.getItem());
    }

    @Override
    public String getRecipeSubfolder() {
        return "spirit_transmutation";
    }
}
