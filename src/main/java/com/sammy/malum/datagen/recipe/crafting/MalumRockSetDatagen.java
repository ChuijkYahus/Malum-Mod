package com.sammy.malum.datagen.recipe.crafting;

import com.sammy.malum.datagen.recipe.RecipeDatagenCommons;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.MalumContent.BlockSets;
import com.sammy.malum.registry.common.util.RockBlockSet;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.*;

import static net.minecraft.data.recipes.RecipeBuilder.*;
import static net.minecraft.data.recipes.ShapedRecipeBuilder.*;
import static net.minecraft.data.recipes.ShapelessRecipeBuilder.*;
import static net.minecraft.data.recipes.SingleItemRecipeBuilder.*;

public class MalumRockSetDatagen {


    public static void buildRecipes(RecipeOutput consumer) {
        buildRecipes(consumer, BlockSets.TAINTED_ROCK_SET);
        buildRecipes(consumer, BlockSets.TWISTED_ROCK_SET);
    }

    public static void buildRecipes(RecipeOutput recipeOutput, RockBlockSet set) {
        var condition = RecipeDatagenCommons.has(set.getRock());

        var blockTag = ItemTags.create(set.getBlocksTag().location());
        var stairTag = ItemTags.create(set.getStairsTag().location());
        var wallTag = ItemTags.create(set.getWallsTag().location());
        var slabTag = ItemTags.create(set.getSlabsTag().location());


        bricksLikeRecipe(recipeOutput, set.getRock(), set.getPolishedRock());
        bricksLikeRecipe(recipeOutput, set.getPolishedRock(), set.getBricks());
        bricksLikeRecipe(recipeOutput, set.getBricks(), set.getTiles());
        bricksLikeRecipe(recipeOutput, set.getTiles(), set.getMosaic());

        stoneCutting(recipeOutput, blockTag, set.getRock());
        stoneCutting(recipeOutput, blockTag, set.getPolishedRock());
        stoneCutting(recipeOutput, blockTag, set.getBricks());
        stoneCutting(recipeOutput, blockTag, set.getTiles());
        stoneCutting(recipeOutput, blockTag, set.getMosaic());

        slabRecipe(recipeOutput, set.getRock(), set.getRockSlab(), blockTag);
        slabRecipe(recipeOutput, set.getPolishedRock(), set.getPolishedRockSlab(), blockTag);
        slabRecipe(recipeOutput, set.getBricks(), set.getBricksSlab(), blockTag);
        slabRecipe(recipeOutput, set.getTiles(), set.getTilesSlab(), blockTag);
        slabRecipe(recipeOutput, set.getMosaic(), set.getMosaicSlab(), blockTag);

        stairsRecipe(recipeOutput, set.getRock(), set.getRockStairs(), stairTag);
        stairsRecipe(recipeOutput, set.getPolishedRock(), set.getPolishedRockStairs(), stairTag);
        stairsRecipe(recipeOutput, set.getBricks(), set.getBricksStairs(), stairTag);
        stairsRecipe(recipeOutput, set.getTiles(), set.getTilesStairs(), stairTag);
        stairsRecipe(recipeOutput, set.getMosaic(), set.getMosaicStairs(), stairTag);

        wallRecipe(recipeOutput, set.getRock(), set.getRockWall(), wallTag);
        wallRecipe(recipeOutput, set.getPolishedRock(), set.getPolishedRockWall(), wallTag);
        wallRecipe(recipeOutput, set.getBricks(), set.getBricksWall(), wallTag);
        wallRecipe(recipeOutput, set.getTiles(), set.getTilesWall(), wallTag);
        wallRecipe(recipeOutput, set.getMosaic(), set.getMosaicWall(), wallTag);

        stoneCutting(recipeOutput, blockTag, set.getChiseled(), 2);
        stoneCutting(recipeOutput, blockTag, set.getCut(), 2);
        stoneCutting(recipeOutput, blockTag, set.getColumn(), 2);
        stoneCutting(recipeOutput, blockTag, set.getAltar(), 2);

        stoneCutting(recipeOutput, blockTag, set.getItemPedestal());
        stoneCutting(recipeOutput, blockTag, set.getItemStand());

        shapelessButton(recipeOutput, set.getButton(), set.getRock());
        shapedPressurePlate(recipeOutput, set.getPressurePlate(), set.getRock());

        shaped(RecipeCategory.MISC, set.getChiseled(), 1)
                .define('X', slabTag)
                .pattern("X")
                .pattern("X")
                .unlockedBy("has_input", condition)
                .save(recipeOutput);

        shaped(RecipeCategory.MISC, set.getCut(), 2)
                .define('X', slabTag)
                .define('Y', blockTag)
                .pattern("X")
                .pattern("Y")
                .unlockedBy("has_input", condition)
                .save(recipeOutput);

        shaped(RecipeCategory.MISC, set.getColumn(), 3)
                .define('X', slabTag)
                .pattern("X")
                .pattern("X")
                .pattern("X")
                .unlockedBy("has_input", condition)
                .save(recipeOutput);

        shaped(RecipeCategory.MISC, set.getAltar(), 3)
                .define('X', slabTag)
                .pattern("XXX")
                .unlockedBy("has_input", condition)
                .save(recipeOutput);

        shaped(RecipeCategory.MISC, set.getItemStand(), 2)
                .define('X', set.getRock())
                .define('Y', set.getRockSlab())
                .pattern("YYY")
                .pattern("XXX")
                .unlockedBy("has_input", condition)
                .save(recipeOutput);

        shaped(RecipeCategory.MISC, set.getItemPedestal())
                .define('X', set.getRock())
                .define('Y', set.getRockSlab())
                .pattern("YYY")
                .pattern(" X ")
                .pattern("YYY")
                .unlockedBy("has_input", condition)
                .save(recipeOutput);
    }

    public static void bricksLikeRecipe(RecipeOutput recipeOutput, ItemLike input, ItemLike output) {
        var recipeID = getDefaultRecipeId(output).withSuffix("_from_" + getDefaultRecipeId(input).getPath());
        shaped(RecipeCategory.MISC, output, 4)
                .define('#', input)
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_input", RecipeDatagenCommons.has(input))
                .save(recipeOutput, recipeID);
        stoneCutting(recipeOutput, input, output);
    }

    public static void shapelessButton(RecipeOutput recipeOutput, ItemLike button, ItemLike input) {
        shapeless(RecipeCategory.MISC, button)
                .requires(input)
                .unlockedBy("has_input", RecipeDatagenCommons.has(input))
                .save(recipeOutput);
        stoneCutting(recipeOutput, input, button);
    }

    public static void shapedPressurePlate(RecipeOutput recipeOutput, ItemLike pressurePlate, ItemLike input) {
        shaped(RecipeCategory.MISC, pressurePlate)
                .define('#', input)
                .pattern("##")
                .unlockedBy("has_input", RecipeDatagenCommons.has(input))
                .save(recipeOutput);
    }

    public static void slabRecipe(RecipeOutput recipeOutput, ItemLike input, ItemLike slab, TagKey<Item> blockTag) {
        shaped(RecipeCategory.MISC, slab, 6)
                .define('#', input)
                .pattern("###")
                .unlockedBy("has_input", RecipeDatagenCommons.has(input))
                .save(recipeOutput);
        stoneCutting(recipeOutput, blockTag, slab);
    }

    public static void stairsRecipe(RecipeOutput recipeOutput, ItemLike input, ItemLike stairs, TagKey<Item> blockTag) {
        shaped(RecipeCategory.MISC, stairs, 4)
                .define('#', input)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .unlockedBy("has_input", RecipeDatagenCommons.has(input))
                .save(recipeOutput);
        stoneCutting(recipeOutput, blockTag, stairs);
    }

    public static void wallRecipe(RecipeOutput recipeOutput, ItemLike input, ItemLike wall, TagKey<Item> blockTag) {
        shaped(RecipeCategory.MISC, wall, 6)
                .define('#', input)
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_input", RecipeDatagenCommons.has(input))
                .save(recipeOutput);
        stoneCutting(recipeOutput, blockTag, wall);
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
}