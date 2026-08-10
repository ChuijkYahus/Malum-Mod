package com.sammy.malum.datagen.recipe.builder;

import com.google.common.collect.*;
import com.sammy.malum.common.recipe.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.core.systems.recipe.*;
import com.sammy.malum.core.systems.registry.*;
import com.sammy.malum.core.systems.spirit.SpiritArcanaType;
import net.minecraft.core.*;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.crafting.*;
import team.lodestar.lodestone.modules.toolkit.recipe.LodestoneRecipeBuilder;
import team.lodestar.lodestone.modules.toolkit.recipe.*;

import java.util.*;

public class SoulBindingRecipeBuilder implements LodestoneRecipeBuilder<SoulBindingRecipe> {
    private final SizedIngredient input;
    private final GeasEffectType geas;

    private final List<SpiritIngredient> spirits = Lists.newArrayList();
    private final List<SizedIngredient> extraIngredients = Lists.newArrayList();

    public SoulBindingRecipeBuilder(Ingredient input, Holder<GeasEffectType> geas) {
        this(new SizedIngredient(input, 1), geas);
    }

    public SoulBindingRecipeBuilder(SizedIngredient input, Holder<GeasEffectType> geas) {
        this.input = input;
        this.geas = geas.value();
    }

    public SoulBindingRecipeBuilder(Ingredient input, int inputCount, Holder<GeasEffectType> geas) {
        this(new SizedIngredient(input, inputCount), geas);
    }

    public SoulBindingRecipeBuilder(ItemLike input, int inputCount, Holder<GeasEffectType> geas) {
        this(SizedIngredient.of(input, inputCount), geas);
    }

    public SoulBindingRecipeBuilder addExtraItem(SizedIngredient ingredient) {
        extraIngredients.add(ingredient);
        return this;
    }

    public SoulBindingRecipeBuilder addExtraItem(ItemLike input, int amount) {
        extraIngredients.add(SizedIngredient.of(input, amount));
        return this;
    }

    public SoulBindingRecipeBuilder addExtraItem(TagKey<Item> input, int amount) {
        extraIngredients.add(SizedIngredient.of(input, amount));
        return this;
    }

    public SoulBindingRecipeBuilder addSpirit(SpiritHolder<SpiritArcanaType> spirit, int count) {
        spirits.add(new SpiritIngredient(spirit, count));
        return this;
    }

    public void save(RecipeOutput recipeOutput) {
        this.save(recipeOutput, geas.getRegistryName());
    }

    @Override
    public SoulBindingRecipe buildRecipe(ResourceLocation id) {
        return new SoulBindingRecipe(input, geas, extraIngredients, spirits);
    }

    @Override
    public String getRecipeSubfolder() {
        return "soul_binding";
    }
}
