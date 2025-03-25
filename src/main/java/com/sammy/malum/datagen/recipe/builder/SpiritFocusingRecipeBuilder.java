package com.sammy.malum.datagen.recipe.builder;

import com.google.common.collect.Lists;
import com.sammy.malum.common.recipe.SpiritFocusingRecipe;
import com.sammy.malum.core.systems.recipe.SpiritIngredient;
import com.sammy.malum.core.systems.spirit.MalumSpiritType;
import net.minecraft.core.*;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import team.lodestar.lodestone.recipe.builder.LodestoneRecipeBuilder;

import java.util.List;

public class SpiritFocusingRecipeBuilder implements LodestoneRecipeBuilder<SpiritFocusingRecipe> {
    private final int time;
    private final int durabilityCost;
    private final Ingredient input;
    private final ItemStack output;
    private final List<SpiritIngredient> spirits = Lists.newArrayList();

    public SpiritFocusingRecipeBuilder(int time, int durabilityCost, Ingredient input, ItemStack output) {
        this.time = time;
        this.durabilityCost = durabilityCost;
        this.input = input;
        this.output = output;
    }

    public SpiritFocusingRecipeBuilder(int time, int durabilityCost, Ingredient input, ItemLike output, int outputCount) {
        this(time, durabilityCost, input, new ItemStack(output, outputCount));
    }

    public SpiritFocusingRecipeBuilder(int time, int durabilityCost, Holder<Item> input, ItemLike output, int outputCount) {
        this(time, durabilityCost, Ingredient.of(input.value()), new ItemStack(output, outputCount));
    }

    public SpiritFocusingRecipeBuilder addSpirit(MalumSpiritType type, int count) {
        spirits.add(new SpiritIngredient(type, count));
        return this;
    }

    public void save(RecipeOutput recipeOutput) {
        this.save(recipeOutput, output.getItem());
    }

    @Override
    public SpiritFocusingRecipe buildRecipe(ResourceLocation resourceLocation) {
        return new SpiritFocusingRecipe(time, durabilityCost, input, output, spirits);
    }


    @Override
    public String getRecipeSubfolder() {
        return "spirit_crucible";
    }
}
