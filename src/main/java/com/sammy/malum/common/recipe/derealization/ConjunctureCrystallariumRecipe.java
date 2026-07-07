package com.sammy.malum.common.recipe.derealization;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sammy.malum.common.data.component.soulstone.StoredInSoulstoneMetal;
import com.sammy.malum.registry.common.recipe.MalumRecipeSerializers;
import com.sammy.malum.registry.common.recipe.MalumRecipeTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import java.util.List;

public class ConjunctureCrystallariumRecipe implements Recipe<SingleRecipeInput> {

    public static final MapCodec<ConjunctureCrystallariumRecipe> CODEC = RecordCodecBuilder.mapCodec((obj) -> obj.group(
            Ingredient.CODEC.fieldOf("input").forGetter(recipe -> recipe.input),
            CrystalPropertyModifier.CODEC.fieldOf("crystal_to_grow").forGetter(recipe -> recipe.crystalToGrow),
            NonNullList.codecOf(MalumSizedChanceResult.CODEC).validate(l -> {
                return !l.isEmpty() && l.size() <= 3
                        ? DataResult.success(l)
                        : DataResult.error(() -> "The recipe must have at least 1 result but no more than 3");
            }).fieldOf("results").forGetter(recipe -> recipe.additionalResults),
            StoredInSoulstoneMetal.CODEC.fieldOf("metal_data").forGetter(recipe -> recipe.metalData),
            Codec.INT.optionalFieldOf("processing_time", 0).forGetter(recipe -> recipe.processingTime)
    ).apply(obj, ConjunctureCrystallariumRecipe::new));

    public static final String NAME = "conjuncture_crystallarium";
    private final Ingredient input;
    private final CrystalPropertyModifier crystalToGrow;
    private final NonNullList<MalumSizedChanceResult> additionalResults;
    private final StoredInSoulstoneMetal metalData;
    private final int processingTime;

    public ConjunctureCrystallariumRecipe(Ingredient input, CrystalPropertyModifier crystalToGrow, NonNullList<MalumSizedChanceResult> additionalResults, StoredInSoulstoneMetal metalData, int processingTime) {
        this.input = input;
        this.crystalToGrow = crystalToGrow;
        this.additionalResults = additionalResults;
        this.metalData = metalData;
        this.processingTime = processingTime;
    }

    @Override
    public boolean matches(SingleRecipeInput singleRecipeInput, Level level) {
        return this.input.test(singleRecipeInput.item());
    }

    @Override
    public ItemStack assemble(SingleRecipeInput singleRecipeInput, HolderLookup.Provider provider) {
        return getResultItem(provider).copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return this.additionalResults.getFirst().result();
    }

    public NonNullList<MalumSizedChanceResult> getResults() {
        return additionalResults;
    }

    public Ingredient getInput() {
        return input;
    }

    public CrystalPropertyModifier getCrystalToGrow() {
        return crystalToGrow;
    }

    public StoredInSoulstoneMetal getMetalData() {
        return metalData;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return MalumRecipeSerializers.CONJUNCTURE_CRYSTALLARIUM_RECIPE_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return MalumRecipeTypes.CONJUNCTURE_CRYSTALLARIUM.get();
    }
}
