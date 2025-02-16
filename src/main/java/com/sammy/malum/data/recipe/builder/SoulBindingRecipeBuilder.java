package com.sammy.malum.data.recipe.builder;

import com.google.common.collect.*;
import com.sammy.malum.*;
import com.sammy.malum.common.recipe.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.core.systems.recipe.*;
import com.sammy.malum.core.systems.spirit.*;
import net.minecraft.core.*;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.*;
import net.neoforged.neoforge.common.crafting.*;
import team.lodestar.lodestone.recipe.builder.*;

import java.util.*;

public class SoulBindingRecipeBuilder implements AutonamedRecipeBuilder<SoulBindingRecipe> {
    private final SizedIngredient input;
    private final Holder<GeasEffectType> geas;

    private final List<SpiritIngredient> spirits = Lists.newArrayList();
    private final List<SizedIngredient> extraIngredients = Lists.newArrayList();

    public SoulBindingRecipeBuilder(Ingredient input, Holder<GeasEffectType> geas) {
        this.input = new SizedIngredient(input, 1);
        this.geas = geas;
    }

    public SoulBindingRecipeBuilder(SizedIngredient input, Holder<GeasEffectType> geas) {
        this.input = input;
        this.geas = geas;
    }

    public SoulBindingRecipeBuilder(Ingredient input, int inputCount, Holder<GeasEffectType> geas) {
        this(new SizedIngredient(input, inputCount), geas);
    }

    public SoulBindingRecipeBuilder(Item input, int inputCount, Holder<GeasEffectType> geas) {
        this(SizedIngredient.of(input, inputCount), geas);
    }

    public SoulBindingRecipeBuilder addExtraItem(SizedIngredient ingredient) {
        extraIngredients.add(ingredient);
        return this;
    }

    public SoulBindingRecipeBuilder addExtraItem(Item input, int amount) {
        extraIngredients.add(SizedIngredient.of(input, amount));
        return this;
    }

    public SoulBindingRecipeBuilder addExtraItem(TagKey<Item> input, int amount) {
        extraIngredients.add(SizedIngredient.of(input, amount));
        return this;
    }

    public SoulBindingRecipeBuilder addSpirit(MalumSpiritType type, int count) {
        spirits.add(new SpiritIngredient(type, count));
        return this;
    }

    @Override
    public Item getResult() {
        return geas.value().createDefaultStack().getItem();
    }

    @Override
    public SoulBindingRecipe build(ResourceLocation resourceLocation) {
        return new SoulBindingRecipe(input, geas.value(), extraIngredients, spirits, false);
    }

    @Override
    public void save(RecipeOutput recipeOutput, ResourceLocation id) {
        defaultSaveFunc(recipeOutput, MalumMod.malumPath(id.getPath()));
    }

    @Override
    public String getRecipeSubfolder() {
        return "soul_binding";
    }
}
