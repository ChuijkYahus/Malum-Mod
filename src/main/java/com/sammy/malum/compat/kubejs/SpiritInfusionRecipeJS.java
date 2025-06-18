package com.sammy.malum.compat.kubejs;

import com.sammy.malum.core.systems.recipe.*;
import dev.latvian.mods.kubejs.recipe.*;
import dev.latvian.mods.kubejs.recipe.component.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;

import java.util.*;

public interface SpiritInfusionRecipeJS {
    RecipeKey<Ingredient> INPUT = IngredientComponent.NON_EMPTY_INGREDIENT.inputKey("ingredient");
    RecipeKey<ItemStack> OUTPUT = ItemStackComponent.ITEM_STACK.outputKey("output");
    RecipeKey<List<Ingredient>> EXTRA_INGREDIENTS = IngredientComponent.UNWRAPPED_INGREDIENT_LIST.inputKey("extraIngredients");
//    RecipeKey<List<SpiritIngredient>> SPIRITS = IngredientComponent.UNWRAPPED_INGREDIENT_LIST.inputKey("spirits");


}
