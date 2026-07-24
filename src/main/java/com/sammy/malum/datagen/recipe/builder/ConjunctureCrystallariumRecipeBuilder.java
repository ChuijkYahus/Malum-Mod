package com.sammy.malum.datagen.recipe.builder;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Either;
import com.sammy.malum.common.data.component.soulstone.StoredInSoulstoneMetal;
import com.sammy.malum.common.recipe.derealization.ConjunctureCrystallariumRecipe;
import com.sammy.malum.common.recipe.derealization.CrystalPropertyModifier;
import com.sammy.malum.common.recipe.derealization.MalumSizedChanceResult;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import team.lodestar.lodestone.recipe.builder.LodestoneRecipeBuilder;

import java.util.List;
import java.util.Optional;

public class ConjunctureCrystallariumRecipeBuilder implements LodestoneRecipeBuilder<ConjunctureCrystallariumRecipe> {
    private final Ingredient input;
    private final CrystalPropertyModifier crystalToGrow;
    private final NonNullList<MalumSizedChanceResult> additionalResults = NonNullList.create();
    private final StoredInSoulstoneMetal metalData;
    private final int processingTime;
    private Optional<ItemStack> resultFallback = Optional.empty();

    public ConjunctureCrystallariumRecipeBuilder(Ingredient input, CrystalPropertyModifier crystalToGrow, MalumSizedChanceResult result, StoredInSoulstoneMetal metalData, int processingTime) {
        this.input = input;
        this.crystalToGrow = crystalToGrow;
        this.metalData = metalData;
        this.additionalResults.add(result);
        this.processingTime = processingTime;
    }

    public ConjunctureCrystallariumRecipeBuilder addAdditionalResult(Item item, int count, float chance) {
        ItemStack stack = new ItemStack(item, count);
        additionalResults.add(new MalumSizedChanceResult(stack, chance));
        return this;
    }

    public ConjunctureCrystallariumRecipeBuilder addAdditionalResult(Item item, float chance) {
        return addAdditionalResult(item, 1, chance);
    }

    public ConjunctureCrystallariumRecipeBuilder addAdditionalResult(Item item) {
        return addAdditionalResult(item, 1, 1.0F);
    }

    public ConjunctureCrystallariumRecipeBuilder addAdditionalResult(Item item, int count) {
        return addAdditionalResult(item, count, 1.0F);
    }

    public ConjunctureCrystallariumRecipeBuilder addResultFallback(Item item, int count) {
        ItemStack stack = new ItemStack(item, count);
        resultFallback = Optional.of(stack);
        return this;
    }

    public ConjunctureCrystallariumRecipeBuilder addResultFallback(Item item) {
        return addResultFallback(item, 1);
    }

    public void save(RecipeOutput recipeOutput) {
        this.save(recipeOutput, this.additionalResults.getFirst().result().getItem());
    }

    @Override
    public ConjunctureCrystallariumRecipe buildRecipe(ResourceLocation id) {
        return new ConjunctureCrystallariumRecipe(input, crystalToGrow, additionalResults, metalData, processingTime, resultFallback);
    }

    @Override
    public String getRecipeSubfolder() {
        return "conjuncture_crystallarium";
    }
}
