package com.sammy.malum.datagen.recipe.builder;

import com.google.common.collect.Lists;
import com.sammy.malum.common.recipe.SpiritInfusionRecipe;
import com.sammy.malum.core.systems.recipe.SpiritIngredient;
import com.sammy.malum.core.systems.registry.*;
import com.sammy.malum.core.systems.spirit.type.*;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.crafting.SizedIngredient;
import team.lodestar.lodestone.recipe.builder.LodestoneRecipeBuilder;

import java.util.List;

public class SpiritInfusionRecipeBuilder implements LodestoneRecipeBuilder<SpiritInfusionRecipe> {
    private final SizedIngredient input;
    private final ItemStack output;

    private final List<SpiritIngredient> spirits = Lists.newArrayList();
    private final List<SizedIngredient> extraIngredients = Lists.newArrayList();

    private boolean carryOverComponentData = false;

    public SpiritInfusionRecipeBuilder(Ingredient input, ItemStack output) {
        this.input = new SizedIngredient(input, 1);
        this.output = output;
    }

    public SpiritInfusionRecipeBuilder(SizedIngredient input, ItemStack output) {
        this.input = input;
        this.output = output;
    }

    public SpiritInfusionRecipeBuilder(Ingredient input, int inputCount, ItemLike output, int outputCount) {
        this(new SizedIngredient(input, inputCount), new ItemStack(output, outputCount));
    }

    public SpiritInfusionRecipeBuilder(Item input, int inputCount, ItemLike output, int outputCount) {
        this(SizedIngredient.of(input, inputCount), new ItemStack(output, outputCount));
    }

    public SpiritInfusionRecipeBuilder(Ingredient input, ItemLike output, int outputCount) {
        this(input, new ItemStack(output, outputCount));
    }

    public SpiritInfusionRecipeBuilder(ItemLike input, ItemStack output) {
        this(Ingredient.of(input), output);
    }

    public SpiritInfusionRecipeBuilder(ItemLike input, ItemLike output, int outputCount) {
        this(Ingredient.of(input), new ItemStack(output, outputCount));
    }

    public SpiritInfusionRecipeBuilder addExtraItem(SizedIngredient ingredient) {
        extraIngredients.add(ingredient);
        return this;
    }

    public SpiritInfusionRecipeBuilder addExtraItem(Item input, int amount) {
        extraIngredients.add(SizedIngredient.of(input, amount));
        return this;
    }

    public SpiritInfusionRecipeBuilder addExtraItem(TagKey<Item> input, int amount) {
        extraIngredients.add(SizedIngredient.of(input, amount));
        return this;
    }

    public SpiritInfusionRecipeBuilder addSpirit(SpiritHolder<MalumSpiritType> spirit, int count) {
        spirits.add(new SpiritIngredient(spirit, count));
        return this;
    }

    public SpiritInfusionRecipeBuilder carryOverComponentData() {
        carryOverComponentData = true;
        return this;
    }

    public void save(RecipeOutput recipeOutput) {
        this.save(recipeOutput, output.getItem());
    }

    @Override
    public SpiritInfusionRecipe buildRecipe(ResourceLocation resourceLocation) {
        return new SpiritInfusionRecipe(input, output, extraIngredients, spirits, carryOverComponentData);
    }

    @Override
    public String getRecipeSubfolder() {
        return "spirit_infusion";
    }
}