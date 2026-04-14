package com.sammy.malum.datagen.recipe;

import com.mojang.datafixers.util.Pair;
import com.sammy.malum.datagen.recipe.builder.vanilla.IngredientBasedCookingRecipeBuilder;
import com.sammy.malum.registry.common.util.data.BlockBundle;
import com.sammy.malum.registry.common.util.data.BlockBundleWithWall;
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
import java.util.function.BiConsumer;

import static com.sammy.malum.MalumMod.malumPath;
import static net.minecraft.data.recipes.RecipeBuilder.getDefaultRecipeId;
import static net.minecraft.data.recipes.ShapedRecipeBuilder.shaped;
import static net.minecraft.data.recipes.ShapelessRecipeBuilder.shapeless;
import static net.minecraft.data.recipes.SingleItemRecipeBuilder.stonecutting;

public class RecipeDatagenCommons {


    public static void blockBundle(RecipeOutput recipeOutput, BlockBundle bundle) {
        slabRecipe(recipeOutput, bundle.block, bundle.slab);
        stairsRecipe(recipeOutput, bundle.block, bundle.stairs);
        if (bundle instanceof BlockBundleWithWall wall) {
            wallRecipe(recipeOutput, bundle.block, wall.wall);
        }
    }

    public static void blockBundleStonecutting(RecipeOutput recipeOutput, BlockBundle bundle, TagKey<Item> blockTag, TagKey<Item> stairTag, TagKey<Item> slabTag) {
        assert !(bundle instanceof BlockBundleWithWall);
        blockBundleStonecutting(recipeOutput, bundle, blockTag, slabTag, stairTag, null);
    }

    public static void blockBundleStonecutting(RecipeOutput recipeOutput, BlockBundle bundle, TagKey<Item> blockTag, TagKey<Item> stairTag, TagKey<Item> slabTag, TagKey<Item> wallTag) {
        blockBundle(recipeOutput, bundle);

        stoneCutting(recipeOutput, blockTag, bundle.stairs, 1);
        stoneCutting(recipeOutput, stairTag, bundle.stairs, 1);

        stoneCutting(recipeOutput, blockTag, bundle.slab, 2);
        stoneCutting(recipeOutput, slabTag, bundle.slab, 1);

        if (bundle instanceof BlockBundleWithWall wall) {
            stoneCutting(recipeOutput, blockTag, wall.wall, 1);
            stoneCutting(recipeOutput, wallTag, wall.wall, 1);
        }
    }

    public static void exchange(RecipeOutput recipeOutput, BlockBundle... bundles) {
        exchange(recipeOutput, (i, o) -> {}, bundles);
    }

    public static void exchange(RecipeOutput recipeOutput, BiConsumer<ItemLike, ItemLike> actor, BlockBundle... bundles) {
        int length = bundles.length;
        for (int i = 0; i < length; i++) {
            var input = bundles[i].block;
            var output = bundles[(i + 1) % length].block;
            var recipeID = getDefaultRecipeId(output).withPrefix("exchanging_").withSuffix("_from_" + getDefaultRecipeId(input).getPath());
            shaped(RecipeCategory.MISC, output, 4)
                    .define('#', input)
                    .pattern(" # ")
                    .pattern("# #")
                    .pattern(" # ")
                    .unlockedBy("has_input", RecipeDatagenCommons.has(input))
                    .save(recipeOutput, recipeID);
            actor.accept(input, output);
        }
    }

    public static void stoneCutting(RecipeOutput recipeOutput, ItemLike input, ItemLike output) {
        stoneCutting(recipeOutput, input, output, 1);
    }

    public static void stoneCutting(RecipeOutput recipeOutput, ItemLike input, ItemLike output, int outputCount) {
        var defaultID = getDefaultRecipeId(output);
        var recipeID = defaultID.withPrefix("stonecutting_" + getDefaultRecipeId(input).getPath() + "_to_");
        stonecutting(Ingredient.of(input), RecipeCategory.MISC, output, outputCount)
                .unlockedBy("has_input", RecipeDatagenCommons.has(input))
                .save(recipeOutput, recipeID);
    }

    public static void stoneCutting(RecipeOutput recipeOutput, TagKey<Item> input, ItemLike output) {
        stoneCutting(recipeOutput, input, output, 1);
    }

    public static void stoneCutting(RecipeOutput recipeOutput, TagKey<Item> input, ItemLike output, int outputCount) {
        var defaultID = getDefaultRecipeId(output);
        var recipeID = defaultID.withPrefix("stonecutting_" + input.location().getPath() + "_to_");
        stonecutting(Ingredient.of(input), RecipeCategory.MISC, output, outputCount)
                .unlockedBy("has_input", RecipeDatagenCommons.has(input))
                .save(recipeOutput, recipeID);
    }

    public static void slabRecipe(RecipeOutput recipeOutput, ItemLike input, ItemLike slab) {
        shaped(RecipeCategory.MISC, slab, 6)
                .define('#', input)
                .pattern("###")
                .unlockedBy("has_input", RecipeDatagenCommons.has(input))
                .save(recipeOutput);
    }

    public static void stairsRecipe(RecipeOutput recipeOutput, ItemLike input, ItemLike stairs) {
        shaped(RecipeCategory.MISC, stairs, 4)
                .define('#', input)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .unlockedBy("has_input", RecipeDatagenCommons.has(input))
                .save(recipeOutput);
    }

    public static void wallRecipe(RecipeOutput recipeOutput, ItemLike input, ItemLike wall) {
        shaped(RecipeCategory.MISC, wall, 6)
                .define('#', input)
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_input", RecipeDatagenCommons.has(input))
                .save(recipeOutput);
    }

    public static void stepsOrAltar(RecipeOutput recipeOutput, TagKey<Item> slab, ItemLike cutAltar) {
        shaped(RecipeCategory.MISC, cutAltar, 3)
                .define('#', slab)
                .pattern("###")
                .unlockedBy("has_input", RecipeDatagenCommons.has(slab))
                .save(recipeOutput);
    }

    public static void beamOrColumn(RecipeOutput recipeOutput, TagKey<Item> slab, ItemLike beamColumn) {
        shaped(RecipeCategory.MISC, beamColumn, 3)
                .define('#', slab)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .unlockedBy("has_input", RecipeDatagenCommons.has(slab))
                .save(recipeOutput);
    }

    public static void pedestalRecipe(RecipeOutput recipeOutput, TagKey<Item> block, TagKey<Item> slab, ItemLike pedestal) {
        shaped(RecipeCategory.MISC, pedestal)
                .define('X', block)
                .define('Y', slab)
                .pattern("YYY")
                .pattern(" X ")
                .pattern("YYY")
                .unlockedBy("has_input", RecipeDatagenCommons.has(block))
                .save(recipeOutput);
    }

    public static void standRecipe(RecipeOutput recipeOutput, TagKey<Item> block, TagKey<Item> slab, ItemLike stand) {
        shaped(RecipeCategory.MISC, stand)
                .define('X', block)
                .define('Y', slab)
                .pattern("YY")
                .pattern("XX")
                .unlockedBy("has_input", RecipeDatagenCommons.has(block))
                .save(recipeOutput);
    }

    public static void decoratedHolderRecipe(RecipeOutput recipeOutput, ItemLike input, ItemLike nugget, ItemLike decorated) {
        shapeless(RecipeCategory.MISC, decorated)
                .requires(nugget).requires(input).requires(nugget)
                .unlockedBy("has_input", RecipeDatagenCommons.has(input))
                .save(recipeOutput);
    }

    public static void shapelessButton(RecipeOutput recipeOutput, ItemLike button, TagKey<Item> input) {
        shapeless(RecipeCategory.MISC, button)
                .requires(input)
                .unlockedBy("has_input", RecipeDatagenCommons.has(input))
                .save(recipeOutput);
    }

    public static void shapedPressurePlate(RecipeOutput recipeOutput, ItemLike pressurePlate, TagKey<Item> input) {
        shaped(RecipeCategory.MISC, pressurePlate)
                .define('#', input)
                .pattern("##")
                .unlockedBy("has_input", RecipeDatagenCommons.has(input))
                .save(recipeOutput);
    }

    public static void smeltAndBlast(RecipeOutput recipeOutput, ResourceLocation recipeName, Ingredient ingredient, RecipeCategory category, Pair<String, Criterion<?>> condition, ItemLike result, float experience) {
        smeltAndBlast(recipeOutput, recipeName, ingredient, category, condition, result, 1, experience);
    }

    public static void smeltAndBlast(RecipeOutput recipeOutput, ResourceLocation recipeName, Ingredient ingredient, RecipeCategory category, Pair<String, Criterion<?>> condition, ItemLike result, int resultCount, float experience) {
        smelting(recipeOutput, recipeName, ingredient, category, condition, result, resultCount, experience);
        blasting(recipeOutput, recipeName, ingredient, category, condition, result, resultCount, experience);
    }

    public static void smelting(RecipeOutput recipeOutput, ResourceLocation recipeName, Ingredient ingredient, RecipeCategory category, Pair<String, Criterion<?>> condition, ItemLike result, int resultCount, float experience) {
        smelting(recipeOutput, recipeName, ingredient, category, condition, result, resultCount, experience, 200);
    }

    public static void smelting(RecipeOutput recipeOutput, ResourceLocation recipeName, Ingredient ingredient, RecipeCategory category, Pair<String, Criterion<?>> condition, ItemLike result, int resultCount, float experience, int time) {
        SimpleCookingRecipeBuilder.smelting(ingredient, category, new ItemStack(result, resultCount), experience, time)
                .unlockedBy(condition.getFirst(), condition.getSecond())
                .save(recipeOutput, recipeName.withSuffix("_smelting"));
    }

    public static void blasting(RecipeOutput recipeOutput, ResourceLocation recipeName, Ingredient ingredient, RecipeCategory category, Pair<String, Criterion<?>> condition, ItemLike result, int resultCount, float experience) {
        blasting(recipeOutput, recipeName, ingredient, category, condition, result, resultCount, experience, 100);
    }

    public static void blasting(RecipeOutput recipeOutput, ResourceLocation recipeName, Ingredient ingredient, RecipeCategory category, Pair<String, Criterion<?>> condition, ItemLike result, int resultCount, float experience, int time) {
        SimpleCookingRecipeBuilder.blasting(ingredient, category, new ItemStack(result, resultCount), experience, time)
                .unlockedBy(condition.getFirst(), condition.getSecond())
                .save(recipeOutput, recipeName.withSuffix("_blasting"));
    }

    public static void smeltAndBlast(RecipeOutput recipeOutput, ResourceLocation recipeName, Ingredient ingredient, RecipeCategory category, Pair<String, Criterion<?>> condition, Ingredient result, float experience) {
        smeltAndBlast(recipeOutput, recipeName, ingredient, category, condition, result, 1, experience);
    }

    public static void smeltAndBlast(RecipeOutput recipeOutput, ResourceLocation recipeName, Ingredient ingredient, RecipeCategory category, Pair<String, Criterion<?>> condition, Ingredient result, int resultCount, float experience) {
        smelting(recipeOutput, recipeName, ingredient, category, condition, result, resultCount, experience);
        blasting(recipeOutput, recipeName, ingredient, category, condition, result, resultCount, experience);
    }

    public static void smelting(RecipeOutput recipeOutput, ResourceLocation recipeName, Ingredient ingredient, RecipeCategory category, Pair<String, Criterion<?>> condition, Ingredient result, int resultCount, float experience) {
        smelting(recipeOutput, recipeName, ingredient, category, condition, result, resultCount, experience, 200);
    }

    public static void smelting(RecipeOutput recipeOutput, ResourceLocation recipeName, Ingredient ingredient, RecipeCategory category, Pair<String, Criterion<?>> condition, Ingredient result, int resultCount, float experience, int time) {
        IngredientBasedCookingRecipeBuilder.smelting(ingredient, category, result, resultCount, experience, time)
                .unlockedBy(condition.getFirst(), condition.getSecond())
                .save(recipeOutput, recipeName.withSuffix("_smelting"));
    }

    public static void blasting(RecipeOutput recipeOutput, ResourceLocation recipeName, Ingredient ingredient, RecipeCategory category, Pair<String, Criterion<?>> condition, Ingredient result, int resultCount, float experience) {
        blasting(recipeOutput, recipeName, ingredient, category, condition, result, resultCount, experience, 100);
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

    public static void ingotNuggetExchange(RecipeOutput consumer, ItemLike nuggetForm, ItemLike ingotForm) {
        compacting(consumer, nuggetForm, ingotForm, "nugget");
    }

    public static void blockIngotExchange(RecipeOutput consumer, ItemLike itemForm, ItemLike blockForm) {
        compacting(consumer, itemForm, blockForm, "block");
    }

    public static void compacting(RecipeOutput consumer, ItemLike smallForm, ItemLike bigForm, String type) {
        String blockName = BuiltInRegistries.ITEM.getKey(bigForm.asItem()).getPath();
        String itemName = BuiltInRegistries.ITEM.getKey(smallForm.asItem()).getPath();
        shaped(RecipeCategory.MISC, bigForm)
                .define('#', smallForm)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_" + itemName, has(smallForm))
                .save(consumer, malumPath(blockName));
        shapeless(RecipeCategory.MISC, smallForm, 9)
                .requires(bigForm)
                .unlockedBy("has_" + itemName, has(smallForm))
                .save(consumer, malumPath(itemName + "_from_" + type));
    }
}
