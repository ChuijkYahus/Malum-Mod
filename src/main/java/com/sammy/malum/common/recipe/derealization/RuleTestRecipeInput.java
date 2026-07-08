package com.sammy.malum.common.recipe.derealization;

import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

public record RuleTestRecipeInput(BlockState state, RandomSource random) implements RecipeInput {

    public boolean test(RuleTest condition) {
        return condition.test(state, random);
    }

    @Override
    public ItemStack getItem(int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public int size() {
        return 0;
    }
}
