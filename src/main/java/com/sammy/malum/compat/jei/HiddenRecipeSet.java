package com.sammy.malum.compat.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.recipe.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.*;
import java.util.stream.Stream;

public class HiddenRecipeSet<T> {

	private final RecipeType<T> recipeType;
	private final Set<T> hiddenRecipes = new HashSet<>();

	@SuppressWarnings({"unchecked", "rawtypes"})
	public static HiddenRecipeSet<?> createSet(RecipeType<?> recipeType) {
		return new HiddenRecipeSet(recipeType);
	}

	public HiddenRecipeSet(RecipeType<T> recipeType) {
		this.recipeType = recipeType;
	}

	public void unhidePreviouslyHiddenRecipes(IRecipeManager manager) {
		manager.unhideRecipes(recipeType, hiddenRecipes);
		hiddenRecipes.clear();
	}

	public void scanAndHideRecipes(IRecipeManager manager, IFocusFactory focusFactory, Collection<TagKey<Item>> nowHidden) {
		List<IFocus<ItemStack>> foci = nowHidden.stream()
				.map(BuiltInRegistries.ITEM::getTag)
				.filter(Optional::isPresent)
				.map(o -> o.get().contents)
				.flatMap(Collection::stream)
				.map(Holder::value)
				.distinct()
				.flatMap(item -> {
					ItemStack stack = item.getDefaultInstance();
					IFocus<ItemStack> asIngredient = focusFactory.createFocus(RecipeIngredientRole.INPUT, VanillaTypes.ITEM_STACK, stack);
					IFocus<ItemStack> asResult = focusFactory.createFocus(RecipeIngredientRole.OUTPUT, VanillaTypes.ITEM_STACK, stack);
					IFocus<ItemStack> asCatalyst = focusFactory.createFocus(RecipeIngredientRole.CATALYST, VanillaTypes.ITEM_STACK, stack);
					return Stream.of(asIngredient, asResult, asCatalyst);
				}).toList();
		manager.createRecipeLookup(recipeType)
				.limitFocus(foci)
				.get()
				.forEach(hiddenRecipes::add);

		manager.hideRecipes(recipeType, hiddenRecipes);
	}

}
