package com.sammy.malum.data.recipe.builder;

import com.sammy.malum.MalumMod;
import com.sammy.malum.common.recipe.*;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.DeferredHolder;
import team.lodestar.lodestone.recipe.builder.LodestoneRecipeBuilder;

import javax.annotation.Nullable;

public class SpiritTransmutationRecipeBuilder implements LodestoneRecipeBuilder<SpiritTransmutationRecipe> {
    private final Ingredient ingredient;
    private final ItemStack output;

    @Nullable
    private String group = "";

    public SpiritTransmutationRecipeBuilder(Ingredient input, ItemStack output) {
        ingredient = input;
        this.output = output;
    }

    public SpiritTransmutationRecipeBuilder(ItemLike input, ItemLike output) {
        this(Ingredient.of(input), new ItemStack(output));
    }

    public SpiritTransmutationRecipeBuilder(ItemStack input, ItemLike output) {
        this(Ingredient.of(input), new ItemStack(output));
    }

    public SpiritTransmutationRecipeBuilder group(@Nullable String group) {
        this.group = group;
        return this;
    }

    @Override
    public SpiritTransmutationRecipe buildRecipe(ResourceLocation resourceLocation) {
        return new SpiritTransmutationRecipe(ingredient, output, group);
    }

    @Override
    public String getRecipeSubfolder() {
        return "spirit_transmutation";
    }
}
