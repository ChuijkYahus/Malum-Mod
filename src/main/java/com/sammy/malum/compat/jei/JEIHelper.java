package com.sammy.malum.compat.jei;

import mezz.jei.api.gui.builder.*;
import mezz.jei.api.recipe.*;
import net.minecraft.world.item.crafting.*;
import net.neoforged.neoforge.common.crafting.*;

import java.util.*;

public class JEIHelper {
    public static void addItemsToJei(IRecipeLayoutBuilder iRecipeLayout, RecipeIngredientRole role, int left, int top, boolean isVertical, List<? extends Ingredient> components) {
        int slots = components.size();
        int startingOffset = 9 * (slots - 1);
        if (isVertical) {
            top -= startingOffset;
        } else {
            left -= startingOffset;
        }
        for (int i = 0; i < slots; i++) {
            int offset = i * 18;
            int oLeft = left + (isVertical ? 0 : offset);
            int oTop = top + (isVertical ? offset : 0);
            iRecipeLayout.addSlot(role, oLeft, oTop).addItemStacks(List.of(components.get(i).getItems()));
        }
    }

    public static void addCustomIngredientToJei(IRecipeLayoutBuilder iRecipeLayout, RecipeIngredientRole role, int left, int top, boolean isVertical, List<? extends ICustomIngredient> components) {
        int slots = components.size();
        int startingOffset = 9 * (slots - 1);
        if (isVertical) {
            top -= startingOffset;
        } else {
            left -= startingOffset;
        }
        for (int i = 0; i < slots; i++) {
            int offset = i * 18;
            int oLeft = left + (isVertical ? 0 : offset);
            int oTop = top + (isVertical ? offset : 0);
            iRecipeLayout.addSlot(role, oLeft, oTop).addItemStacks(components.get(i).getItems().toList());
        }
    }

    public static void addSizedIngredientsToJei(IRecipeLayoutBuilder iRecipeLayout, RecipeIngredientRole role, int left, int top, boolean isVertical, List<SizedIngredient> components) {
        int slots = components.size();
        int startingOffset = 9 * (slots - 1);
        if (isVertical) {
            top -= startingOffset;
        } else {
            left -= startingOffset;
        }
        for (int i = 0; i < slots; i++) {
            int offset = i * 18;
            int oLeft = left + (isVertical ? 0 : offset);
            int oTop = top + (isVertical ? offset : 0);
            iRecipeLayout.addSlot(role, oLeft, oTop).addItemStacks(List.of(components.get(i).getItems()));
        }
    }
}
