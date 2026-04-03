package com.sammy.malum.compat.jei;

import com.sammy.malum.config.*;
import com.sammy.malum.core.handlers.hiding.*;
import mezz.jei.api.constants.*;
import mezz.jei.api.recipe.*;
import mezz.jei.api.runtime.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;

import java.util.*;

public class HiddenRecipeHandler {

	private static final Set<ItemStack> HIDDEN_ITEMS = new LinkedHashSet<>();
	private static final Map<RecipeType<?>, HiddenRecipeSet<?>> HIDDEN_RECIPE_SETS = new HashMap<>();
	private static final List<UUID> LISTENER_KEYS = new ArrayList<>();

	public static void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
		var key = HiddenTagHandler.registerHiddenItemListener(() -> {
			var tagsToHide = HiddenTagHandler.getTagsToHide();
			hideItems(jeiRuntime, tagsToHide);
			hideRecipes(jeiRuntime, tagsToHide);
		});
		LISTENER_KEYS.add(key);
	}

	public static void onRuntimeUnavailable() {
		LISTENER_KEYS.forEach(HiddenTagHandler::removeListener);
		LISTENER_KEYS.clear();
		HIDDEN_RECIPE_SETS.clear();
		HIDDEN_ITEMS.clear();
	}

	public static void hideItems(IJeiRuntime jeiRuntime, List<TagKey<Item>> tagsToHide) {
		var ingredientManager = jeiRuntime.getIngredientManager();

		if (!HIDDEN_ITEMS.isEmpty()) {
			ingredientManager.addIngredientsAtRuntime(VanillaTypes.ITEM_STACK, HIDDEN_ITEMS);
			HIDDEN_ITEMS.clear();
		}
		if (!CommonConfig.HIDE_RECIPES.getConfigValue()) {
			return;
		}
		if (!tagsToHide.isEmpty()) {
			Collection<ItemStack> ingredients = ingredientManager.getAllIngredients(VanillaTypes.ITEM_STACK);
			for (ItemStack stack : ingredients) {
				if (HiddenTagHandler.isHiddenItem(stack)) {
					HIDDEN_ITEMS.add(stack);
				}
			}

			if (!HIDDEN_ITEMS.isEmpty())
				ingredientManager.removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK, HIDDEN_ITEMS);
		}
	}

	public static void hideRecipes(IJeiRuntime jeiRuntime, List<TagKey<Item>> tagsToHide) {
		var recipeRegistry = jeiRuntime.getRecipeManager();
		var helpers = jeiRuntime.getJeiHelpers();
		var focusFactory = helpers.getFocusFactory();
		helpers.getAllRecipeTypes().forEach(it -> {
			HiddenRecipeSet<?> hiddenRecipes = HIDDEN_RECIPE_SETS.computeIfAbsent(it, HiddenRecipeSet::createSet);

			hiddenRecipes.unhidePreviouslyHiddenRecipes(recipeRegistry);
			if (!tagsToHide.isEmpty())
				hiddenRecipes.scanAndHideRecipes(recipeRegistry, focusFactory, tagsToHide);
		});
	}
}
