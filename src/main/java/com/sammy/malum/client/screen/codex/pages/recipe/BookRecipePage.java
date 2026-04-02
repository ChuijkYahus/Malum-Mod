package com.sammy.malum.client.screen.codex.pages.recipe;

import com.sammy.malum.MalumMod;
import com.sammy.malum.client.screen.codex.pages.BookPage;
import com.sammy.malum.common.recipe.SpiritInfusionRecipe;
import com.sammy.malum.core.systems.recipe.SpiritBasedRecipeInput;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeType;
import team.lodestar.lodestone.modules.toolkit.recipe.LodestoneRecipeSearch;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

public abstract class BookRecipePage<T extends RecipeInput, K extends Recipe<T>> extends BookPage {

    protected final K recipe;

    public BookRecipePage(T input) {
        recipe = makeSearch().map(s -> s.findRecipe(input)).orElse(null);
        tryGatherRecipeData();
    }

    public BookRecipePage(String id) {
        this(MalumMod.malumPath(id));
    }

    public BookRecipePage(ResourceLocation id) {
        recipe = makeSearch().map(s -> s.findRecipe(id)).orElse(null);
        tryGatherRecipeData();
    }

    public BookRecipePage(Predicate<K> filter) {
        recipe = makeSearch().map(s -> s.findRecipe(filter)).orElse(null);
        tryGatherRecipeData();
    }

    public BookRecipePage(K recipe) {
        this.recipe = recipe;
        tryGatherRecipeData();
    }

    @Override
    public boolean isValid() {
        return recipe != null;
    }

    private void tryGatherRecipeData() {
        if (isValid()) {
            gatherRecipeData();
        }
    }

    public void gatherRecipeData() {

    }

    public abstract RecipeType<K> getRecipeType();

    public Optional<LodestoneRecipeSearch<T, K>> makeSearch() {
        var level = Minecraft.getInstance().level;
        if (level == null) {
            return Optional.empty();
        }
        return Optional.of(LodestoneRecipeSearch.search(level, getRecipeType()));
    }

    @Override
    public ResourceLocation getBackground(boolean isRightSide) {
        var recipeType = getRecipeType();
        var id = BuiltInRegistries.RECIPE_TYPE.getKey(recipeType);
        assert id != null;
        return getBackground(id.getPath());
    }
}