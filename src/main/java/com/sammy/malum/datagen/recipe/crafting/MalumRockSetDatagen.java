package com.sammy.malum.datagen.recipe.crafting;

import com.sammy.malum.datagen.recipe.RecipeDatagenCommons;
import com.sammy.malum.registry.common.MalumContent.BlockSets;
import com.sammy.malum.registry.common.util.building.RockBlockSet;
import net.minecraft.data.recipes.*;
import net.minecraft.world.level.*;

import static com.sammy.malum.datagen.recipe.RecipeDatagenCommons.*;
import static net.minecraft.data.recipes.RecipeBuilder.*;
import static net.minecraft.data.recipes.ShapedRecipeBuilder.*;

public class MalumRockSetDatagen {


    public static void buildRecipes(RecipeOutput consumer) {
        buildRecipes(consumer, BlockSets.TAINTED_ROCK_SET);
        buildRecipes(consumer, BlockSets.TWISTED_ROCK_SET);
    }

    public static void buildRecipes(RecipeOutput recipeOutput, RockBlockSet set) {
        var condition = RecipeDatagenCommons.has(set.rock.block);

        var blockTag = set.blocksTag.itemTag();
        var stairTag = set.stairsTag.itemTag();
        var wallTag = set.wallsTag.itemTag();
        var slabTag = set.slabsTag.itemTag();

        exchange(recipeOutput, (i, o) -> bricksLikeRecipe(recipeOutput, i, o), set.rock, set.polishedRock, set.bricks, set.tiles, set.mosaic);

        blockBundleCraftingAndStonecutting(recipeOutput, set.rock, blockTag, stairTag, slabTag, wallTag);
        blockBundleCraftingAndStonecutting(recipeOutput, set.polishedRock, blockTag, stairTag, slabTag, wallTag);
        blockBundleCraftingAndStonecutting(recipeOutput, set.bricks, blockTag, stairTag, slabTag, wallTag);
        blockBundleCraftingAndStonecutting(recipeOutput, set.tiles, blockTag, stairTag, slabTag, wallTag);
        blockBundleCraftingAndStonecutting(recipeOutput, set.mosaic, blockTag, stairTag, slabTag, wallTag);

        stoneCutting(recipeOutput, blockTag, set.column, 2);
        stoneCutting(recipeOutput, blockTag, set.altar, 2);

        stoneCutting(recipeOutput, blockTag, set.itemPedestal);
        stoneCutting(recipeOutput, blockTag, set.itemStand);

        shapelessButton(recipeOutput, set.button, blockTag);
        shapedPressurePlate(recipeOutput, set.pressurePlate, blockTag);

        RecipeDatagenCommons.stepsOrAltar(recipeOutput, slabTag, set.altar);
        RecipeDatagenCommons.beamOrColumn(recipeOutput, slabTag, set.column);

        RecipeDatagenCommons.pedestalRecipe(recipeOutput, blockTag, slabTag, set.itemPedestal);
        RecipeDatagenCommons.standRecipe(recipeOutput, blockTag, slabTag, set.itemStand);
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
}