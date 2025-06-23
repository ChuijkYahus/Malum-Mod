package com.sammy.malum.compat.kubejs;

import com.sammy.malum.client.screen.codex.pages.recipe.*;
import com.sammy.malum.common.recipe.*;
import com.sammy.malum.compat.kubejs.component.*;
import com.sammy.malum.core.systems.recipe.*;
import dev.latvian.mods.kubejs.recipe.*;
import dev.latvian.mods.kubejs.recipe.component.*;
import dev.latvian.mods.kubejs.recipe.schema.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.neoforged.neoforge.common.crafting.*;

import java.util.*;

public interface SpiritInfusionRecipeJS {
    RecipeKey<Ingredient> INPUT = IngredientComponent.NON_EMPTY_INGREDIENT.inputKey("ingredient");
    RecipeKey<ItemStack> OUTPUT = ItemStackComponent.ITEM_STACK.outputKey("output");
    RecipeKey<List<SizedIngredient>> EXTRA_INGREDIENTS = SizedIngredientComponent.FLAT.asList().inputKey("extraIngredients");
    RecipeKey<List<SpiritIngredient>> SPIRITS = SpiritJSComponent.SPIRIT_LIST_INGREDIENT.inputKey("spirits");
    RecipeKey<Boolean> CARRY = BooleanComponent.BOOLEAN.otherKey("carryOverComponentData");

    RecipeKey<Integer> TIME = NumberComponent.INT.otherKey("time");
    RecipeKey<Integer> DURABILITY_COST = NumberComponent.INT.otherKey("durability_cost");

    RecipeSchema SPIRIT_INFUSION = new RecipeSchema(INPUT, OUTPUT, EXTRA_INGREDIENTS, SPIRITS, CARRY);
    RecipeSchema SPIRIT_FOCUSING = new RecipeSchema(TIME, DURABILITY_COST, INPUT, OUTPUT, SPIRITS);

}
