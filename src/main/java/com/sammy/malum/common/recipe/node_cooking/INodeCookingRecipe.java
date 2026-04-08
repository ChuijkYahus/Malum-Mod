package com.sammy.malum.common.recipe.node_cooking;

import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.Arrays;
import java.util.Comparator;

public interface INodeCookingRecipe {

    ItemStack getOutput();

    int getOutputCount();

    Ingredient getRawOutput();

    Ingredient getInput();

    default ItemStack bakeOutput() {
        var items = getRawOutput().getItems();
        var optional = Arrays.stream(items).min(Comparator.comparing(c -> c.getItem().getDescriptionId()));
        if (optional.isPresent()) {
            var stack = optional.get();
            stack.setCount(getOutputCount());
            return stack;
        }
        return new ItemStack(Items.BARRIER);
    }
}
