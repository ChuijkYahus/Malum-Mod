package com.sammy.malum.datagen.recipe;

import com.mojang.datafixers.util.Pair;
import com.sammy.malum.datagen.recipe.builder.vanilla.IngredientBasedCookingRecipeBuilder;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import static com.sammy.malum.MalumMod.malumPath;
import static net.minecraft.data.recipes.ShapedRecipeBuilder.shaped;
import static net.minecraft.data.recipes.ShapelessRecipeBuilder.shapeless;

public class RecipeDatagenCommons {

    public static void smeltAndBlast(RecipeOutput recipeOutput, ResourceLocation recipeName, Ingredient ingredient, RecipeCategory category, Pair<String, Criterion<?>> condition, Item result, float experience) {
        smeltAndBlast(recipeOutput, recipeName, ingredient, category, condition, result, 1, experience);
    }

    public static void smeltAndBlast(RecipeOutput recipeOutput, ResourceLocation recipeName, Ingredient ingredient, RecipeCategory category, Pair<String, Criterion<?>> condition, Item result, int resultCount, float experience) {
        smelting(recipeOutput, recipeName, ingredient, category, condition, result, resultCount, experience, 200);
        blasting(recipeOutput, recipeName, ingredient, category, condition, result, resultCount, experience, 100);
    }

    public static void smelting(RecipeOutput recipeOutput, ResourceLocation recipeName, Ingredient ingredient, RecipeCategory category, Pair<String, Criterion<?>> condition, Item result, int resultCount, float experience, int time) {
        SimpleCookingRecipeBuilder.smelting(ingredient, category, new ItemStack(result, resultCount), experience, time)
                .unlockedBy(condition.getFirst(), condition.getSecond())
                .save(recipeOutput, recipeName.withSuffix("_smelting"));
    }

    public static void blasting(RecipeOutput recipeOutput, ResourceLocation recipeName, Ingredient ingredient, RecipeCategory category, Pair<String, Criterion<?>> condition, Item result, int resultCount, float experience, int time) {
        SimpleCookingRecipeBuilder.blasting(ingredient, category, new ItemStack(result, resultCount), experience, time)
                .unlockedBy(condition.getFirst(), condition.getSecond())
                .save(recipeOutput, recipeName.withSuffix("_blasting"));
    }

    public static void smeltAndBlast(RecipeOutput recipeOutput, ResourceLocation recipeName, Ingredient ingredient, RecipeCategory category, Pair<String, Criterion<?>> condition, Ingredient result, float experience, int time) {
        smeltAndBlast(recipeOutput, recipeName, ingredient, category, condition, result, 1, experience);
    }

    public static void smeltAndBlast(RecipeOutput recipeOutput, ResourceLocation recipeName, Ingredient ingredient, RecipeCategory category, Pair<String, Criterion<?>> condition, Ingredient result, int resultCount, float experience) {
        smelting(recipeOutput, recipeName, ingredient, category, condition, result, resultCount, experience, 200);
        blasting(recipeOutput, recipeName, ingredient, category, condition, result, resultCount, experience, 100);
    }

    public static void smelting(RecipeOutput recipeOutput, ResourceLocation recipeName, Ingredient ingredient, RecipeCategory category, Pair<String, Criterion<?>> condition, Ingredient result, int resultCount, float experience, int time) {
        IngredientBasedCookingRecipeBuilder.smelting(ingredient, category, result, resultCount, experience, time)
                .unlockedBy(condition.getFirst(), condition.getSecond())
                .save(recipeOutput, recipeName.withSuffix("_smelting"));
    }

    public static void blasting(RecipeOutput recipeOutput, ResourceLocation recipeName, Ingredient ingredient, RecipeCategory category, Pair<String, Criterion<?>> condition, Ingredient result, int resultCount, float experience, int time) {
        IngredientBasedCookingRecipeBuilder.blasting(ingredient, category, result, resultCount, experience, time)
                .unlockedBy(condition.getFirst(), condition.getSecond())
                .save(recipeOutput, recipeName.withSuffix("_blasting"));
    }

    public static Criterion<?> has(MinMaxBounds.Ints count, ItemLike item) {
        return inventoryTrigger(ItemPredicate.Builder.item().of(item).withCount(count));
    }

    public static Criterion<?> has(ItemLike itemLike) {
        return inventoryTrigger(ItemPredicate.Builder.item().of(itemLike));
    }

    public static Criterion<?> has(TagKey<Item> tag) {
        return inventoryTrigger(ItemPredicate.Builder.item().of(tag));
    }

    public static Criterion<?> inventoryTrigger(ItemPredicate.Builder... items) {
        return inventoryTrigger(Arrays.stream(items).map(ItemPredicate.Builder::build).toArray(ItemPredicate[]::new));
    }

    public static Criterion<?> inventoryTrigger(ItemPredicate... predicates) {
        return CriteriaTriggers.INVENTORY_CHANGED
                .createCriterion(new InventoryChangeTrigger.TriggerInstance(Optional.empty(), InventoryChangeTrigger.TriggerInstance.Slots.ANY, List.of(predicates)));
    }

    public static void ingotNuggetExchange(RecipeOutput consumer, Supplier<? extends Item> nuggetForm, Supplier<? extends Item> ingotForm) {
        compacting(consumer, nuggetForm, ingotForm, "nugget");
    }

    public static void blockIngotExchange(RecipeOutput consumer, Supplier<? extends Item> itemForm, Supplier<? extends Item> blockForm) {
        compacting(consumer, itemForm, blockForm, "block");
    }

    public static void compacting(RecipeOutput consumer, Supplier<? extends Item> smallForm, Supplier<? extends Item> bigForm, String type) {
        var small = smallForm.get();
        var big = bigForm.get();
        String blockName = BuiltInRegistries.ITEM.getKey(big).getPath();
        String itemName = BuiltInRegistries.ITEM.getKey(small).getPath();
        shaped(RecipeCategory.MISC, big)
                .define('#', small)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_" + itemName, has(small))
                .save(consumer, malumPath(blockName));
        shapeless(RecipeCategory.MISC, small, 9)
                .requires(big)
                .unlockedBy("has_" + itemName, has(small))
                .save(consumer, malumPath(itemName + "_from_" + type));
    }
}
