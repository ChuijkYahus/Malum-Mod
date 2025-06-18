package com.sammy.malum.datagen.recipe.builder;

import com.sammy.malum.common.recipe.RunicWorkbenchRecipe;
import com.sammy.malum.core.systems.recipe.SpiritIngredient;
import com.sammy.malum.core.systems.spirit.type.*;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.*;
import net.neoforged.neoforge.common.crafting.SizedIngredient;
import team.lodestar.lodestone.recipe.builder.LodestoneRecipeBuilder;

public class RunicWorkbenchRecipeBuilder implements LodestoneRecipeBuilder<RunicWorkbenchRecipe> {
    private ItemStack primaryInput;
    private SizedIngredient secondaryInput;
    private final ItemStack output;

    public RunicWorkbenchRecipeBuilder(ItemStack output) {
        this.output = output;
    }

    public RunicWorkbenchRecipeBuilder(ItemLike output, int outputCount) {
        this.output = new ItemStack(output.asItem(), outputCount);
    }

    public RunicWorkbenchRecipeBuilder setPrimaryInput(ItemStack primaryInput) {
        this.primaryInput = primaryInput;
        return this;
    }

    public RunicWorkbenchRecipeBuilder setPrimaryInput(ItemLike primaryInput, int primaryInputCount) {
        return setPrimaryInput(new ItemStack(primaryInput, primaryInputCount));
    }

    public RunicWorkbenchRecipeBuilder setSecondaryInput(SpiritWrapper type, int amount) {
        return setSecondaryInput((Item) type.getSpiritShard(), amount);
    }
    public RunicWorkbenchRecipeBuilder setSecondaryInput(Item item, int amount) {
        this.secondaryInput = new SizedIngredient(Ingredient.of(item), amount);
        return this;
    }

    public void save(RecipeOutput recipeOutput) {
        this.save(recipeOutput, output.getItem());
    }

    @Override
    public RunicWorkbenchRecipe buildRecipe(ResourceLocation resourceLocation) {
        return new RunicWorkbenchRecipe(
                SizedIngredient.of(primaryInput.getItem(), primaryInput.getCount()),
                secondaryInput, output);
    }

    @Override
    public String getRecipeSubfolder() {
        return "runeworking";
    }
}