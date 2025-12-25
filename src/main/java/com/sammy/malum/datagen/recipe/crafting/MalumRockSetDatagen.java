package com.sammy.malum.datagen.recipe.crafting;

import com.sammy.malum.datagen.tag.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.item.*;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.*;

import static com.sammy.malum.datagen.recipe.MalumVanillaRecipes.*;
import static net.minecraft.data.recipes.RecipeBuilder.*;
import static net.minecraft.data.recipes.ShapedRecipeBuilder.*;
import static net.minecraft.data.recipes.ShapelessRecipeBuilder.*;
import static net.minecraft.data.recipes.SingleItemRecipeBuilder.*;

public class MalumRockSetDatagen {

    private static final MalumDatagenRockSet TAINTED_ROCK = new MalumDatagenRockSet(
            "tainted_rock",
            // base variants
            // base
            MalumItems.TAINTED_ROCK.get(), MalumItems.POLISHED_TAINTED_ROCK.get(), MalumItems.TAINTED_ROCK_BRICKS.get(), MalumItems.TAINTED_ROCK_TILES.get(), MalumItems.TAINTED_ROCK_MOSAIC.get(),

            // slabs
            MalumItems.TAINTED_ROCK_SLAB.get(), MalumItems.POLISHED_TAINTED_ROCK_SLAB.get(), MalumItems.TAINTED_ROCK_BRICKS_SLAB.get(), MalumItems.TAINTED_ROCK_TILES_SLAB.get(), MalumItems.TAINTED_ROCK_MOSAIC_SLAB.get(),

            // stairs
            MalumItems.TAINTED_ROCK_STAIRS.get(), MalumItems.POLISHED_TAINTED_ROCK_STAIRS.get(), MalumItems.TAINTED_ROCK_BRICKS_STAIRS.get(), MalumItems.TAINTED_ROCK_TILES_STAIRS.get(), MalumItems.TAINTED_ROCK_MOSAIC_STAIRS.get(),

            MalumItems.TAINTED_ROCK_WALL.get(), MalumItems.POLISHED_TAINTED_ROCK_WALL.get(), MalumItems.TAINTED_ROCK_BRICKS_WALL.get(), MalumItems.TAINTED_ROCK_TILES_WALL.get(), MalumItems.TAINTED_ROCK_MOSAIC_WALL.get(),

            MalumItems.TAINTED_ROCK_COLUMN.get(), MalumItems.CUT_TAINTED_ROCK.get(), MalumItems.CHISELED_TAINTED_ROCK.get(),

            MalumItems.TAINTED_ROCK_PRESSURE_PLATE.get(), MalumItems.TAINTED_ROCK_BUTTON.get(),

            MalumItems.TAINTED_ROCK_ITEM_STAND.get(), MalumItems.TAINTED_ROCK_ITEM_PEDESTAL.get(),
            MalumTags.ItemTags.TAINTED_ROCK, MalumTags.ItemTags.TAINTED_ROCK_BLOCKS, MalumTags.ItemTags.TAINTED_ROCK_STAIRS, MalumTags.ItemTags.TAINTED_ROCK_SLABS, MalumTags.ItemTags.TAINTED_ROCK_WALLS
    );

    private static final MalumDatagenRockSet TWISTED_ROCK = new MalumDatagenRockSet(
            "twisted_rock",
            // base variants
            // base
            MalumItems.TWISTED_ROCK.get(), MalumItems.POLISHED_TWISTED_ROCK.get(), MalumItems.TWISTED_ROCK_BRICKS.get(), MalumItems.TWISTED_ROCK_TILES.get(), MalumItems.TWISTED_ROCK_MOSAIC.get(),

            // slabs
            MalumItems.TWISTED_ROCK_SLAB.get(), MalumItems.POLISHED_TWISTED_ROCK_SLAB.get(), MalumItems.TWISTED_ROCK_BRICKS_SLAB.get(), MalumItems.TWISTED_ROCK_TILES_SLAB.get(), MalumItems.TWISTED_ROCK_MOSAIC_SLAB.get(),

            // stairs
            MalumItems.TWISTED_ROCK_STAIRS.get(), MalumItems.POLISHED_TWISTED_ROCK_STAIRS.get(), MalumItems.TWISTED_ROCK_BRICKS_STAIRS.get(), MalumItems.TWISTED_ROCK_TILES_STAIRS.get(), MalumItems.TWISTED_ROCK_MOSAIC_STAIRS.get(),

            MalumItems.TWISTED_ROCK_WALL.get(), MalumItems.POLISHED_TWISTED_ROCK_WALL.get(), MalumItems.TWISTED_ROCK_BRICKS_WALL.get(), MalumItems.TWISTED_ROCK_TILES_WALL.get(), MalumItems.TWISTED_ROCK_MOSAIC_WALL.get(),

            MalumItems.TWISTED_ROCK_COLUMN.get(), MalumItems.CUT_TWISTED_ROCK.get(), MalumItems.CHISELED_TWISTED_ROCK.get(),

            MalumItems.TWISTED_ROCK_PRESSURE_PLATE.get(), MalumItems.TWISTED_ROCK_BUTTON.get(),

            MalumItems.TWISTED_ROCK_ITEM_STAND.get(), MalumItems.TWISTED_ROCK_ITEM_PEDESTAL.get(),
            MalumTags.ItemTags.TWISTED_ROCK, MalumTags.ItemTags.TWISTED_ROCK_BLOCKS, MalumTags.ItemTags.TWISTED_ROCK_STAIRS, MalumTags.ItemTags.TWISTED_ROCK_SLABS, MalumTags.ItemTags.TWISTED_ROCK_WALLS
    );

    private static MalumRockSetDatagen.MalumDatagenRockSet cachedRockSet;

    public static void addTags(MalumItemTagDatagen provider) {
        addTags(provider, TAINTED_ROCK);
        addTags(provider, TWISTED_ROCK);
    }

    public static void buildRecipes(RecipeOutput consumer) {
        buildRecipes(consumer, TAINTED_ROCK);
        buildRecipes(consumer, TWISTED_ROCK);
    }

    protected static void addTags(MalumItemTagDatagen provider, MalumRockSetDatagen.MalumDatagenRockSet rockSet) {
        provider.safeCopy(rockSet.setEncompassingTag);
        provider.safeCopy(rockSet.blockTag);
        provider.safeCopy(rockSet.stairTag);
        provider.safeCopy(rockSet.slabTag);
        provider.safeCopy(rockSet.wallTag);
    }

    protected static void buildRecipes(RecipeOutput recipeOutput, MalumRockSetDatagen.MalumDatagenRockSet rockSet) {
        var condition = has(rockSet.rock);
        cachedRockSet = rockSet;
        slabRecipe(recipeOutput, rockSet.rock, rockSet.rockSlab);
        slabRecipe(recipeOutput, rockSet.polishedRock, rockSet.polishedRockSlab);
        slabRecipe(recipeOutput, rockSet.bricks, rockSet.bricksSlab);
        slabRecipe(recipeOutput, rockSet.tiles, rockSet.tilesSlab);
        slabRecipe(recipeOutput, rockSet.mosaic, rockSet.mosaicSlab);
        stairsRecipe(recipeOutput, rockSet.rock, rockSet.rockStairs);
        stairsRecipe(recipeOutput, rockSet.polishedRock, rockSet.polishedRockStairs);
        stairsRecipe(recipeOutput, rockSet.bricks, rockSet.bricksStairs);
        stairsRecipe(recipeOutput, rockSet.tiles, rockSet.tilesStairs);
        stairsRecipe(recipeOutput, rockSet.mosaic, rockSet.mosaicStairs);
        wallRecipe(recipeOutput, rockSet.rock, rockSet.rockWall);
        wallRecipe(recipeOutput, rockSet.polishedRock, rockSet.polishedRockWall);
        wallRecipe(recipeOutput, rockSet.bricks, rockSet.bricksWall);
        wallRecipe(recipeOutput, rockSet.tiles, rockSet.tilesWall);
        wallRecipe(recipeOutput, rockSet.mosaic, rockSet.mosaicWall);

        bricksLikeRecipe(recipeOutput, rockSet.rock, rockSet.polishedRock);
        bricksLikeRecipe(recipeOutput, rockSet.polishedRock, rockSet.bricks);
        bricksLikeRecipe(recipeOutput, rockSet.bricks, rockSet.tiles);
        bricksLikeRecipe(recipeOutput, rockSet.tiles, rockSet.mosaic);

        shapelessButton(recipeOutput, rockSet.button, rockSet.rock);
        shapedPressurePlate(recipeOutput, rockSet.pressurePlate, rockSet.rock);

        shaped(RecipeCategory.MISC, rockSet.chiseledRock, 1)
                .define('#', rockSet.slabTag)
                .pattern("#")
                .pattern("#")
                .unlockedBy("has_input", condition)
                .save(recipeOutput);
        shaped(RecipeCategory.MISC, rockSet.cutRock, 2)
                .define('X', rockSet.slabTag)
                .define('Y', rockSet.blockTag)
                .pattern("X")
                .pattern("Y")
                .unlockedBy("has_input", condition)
                .save(recipeOutput);
        shaped(RecipeCategory.MISC, rockSet.column, 3)
                .define('#', rockSet.slabTag)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .unlockedBy("has_input", condition)
                .save(recipeOutput);
        shaped(RecipeCategory.MISC, rockSet.itemStand, 2)
                .define('X', rockSet.rock)
                .define('Y', rockSet.rockSlab)
                .pattern("YYY")
                .pattern("XXX")
                .unlockedBy("has_input", condition)
                .save(recipeOutput);
        shaped(RecipeCategory.MISC, rockSet.itemPedestal)
                .define('X', rockSet.rock)
                .define('Y', rockSet.rockSlab)
                .pattern("YYY")
                .pattern(" X ")
                .pattern("YYY")
                .unlockedBy("has_input", condition)
                .save(recipeOutput);
    }

    private static void bricksLikeRecipe(RecipeOutput recipeOutput, ItemLike input, ItemLike output) {
        var recipeID = getDefaultRecipeId(output).withSuffix("_from_" + getDefaultRecipeId(input).getPath());
        shaped(RecipeCategory.MISC, output, 4)
                .define('#', input)
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_input", has(input))
                .save(recipeOutput, recipeID);
        stoneCutting(recipeOutput, input, output);
    }

    private static void shapelessButton(RecipeOutput recipeOutput, ItemLike button, Item input) {
        shapeless(RecipeCategory.MISC, button)
                .requires(input)
                .unlockedBy("has_input", has(input))
                .save(recipeOutput);
        stoneCutting(recipeOutput, input, button);
    }

    private static void shapedPressurePlate(RecipeOutput recipeOutput, ItemLike pressurePlate, Item input) {
        shaped(RecipeCategory.MISC, pressurePlate)
                .define('#', input)
                .pattern("##")
                .unlockedBy("has_input", has(input))
                .save(recipeOutput);
    }

    private static void slabRecipe(RecipeOutput recipeOutput, Item input, ItemLike slab) {
        shaped(RecipeCategory.MISC, slab, 6)
                .define('#', input)
                .pattern("###")
                .unlockedBy("has_input", has(input))
                .save(recipeOutput);
        stoneCutting(recipeOutput, cachedRockSet.blockTag, slab);
    }

    private static void stairsRecipe(RecipeOutput recipeOutput, Item input, ItemLike stairs) {
        shaped(RecipeCategory.MISC, stairs, 4)
                .define('#', input)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .unlockedBy("has_input", has(input))
                .save(recipeOutput);
        stoneCutting(recipeOutput, cachedRockSet.blockTag, stairs);
    }

    private static void wallRecipe(RecipeOutput recipeOutput, Item input, ItemLike wall) {
        shaped(RecipeCategory.MISC, wall, 6)
                .define('#', input)
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_input", has(input))
                .save(recipeOutput);
        stoneCutting(recipeOutput, cachedRockSet.blockTag, wall);
    }

    private static void stoneCutting(RecipeOutput recipeOutput, ItemLike input, ItemLike output) {
        stoneCutting(recipeOutput, input, output, 1);
    }

    private static void stoneCutting(RecipeOutput recipeOutput, ItemLike input, ItemLike output, int outputCount) {
        var defaultID = getDefaultRecipeId(output);
        var recipeID = defaultID.withSuffix(getDefaultRecipeId(input).getPath() + "_stonecutting");
        stonecutting(Ingredient.of(input), RecipeCategory.MISC, output, outputCount)
                .unlockedBy("has_input", has(input))
                .save(recipeOutput, recipeID);
    }

    private static void stoneCutting(RecipeOutput recipeOutput, TagKey<Item> input, ItemLike output) {
        stoneCutting(recipeOutput, input, output, 1);
    }

    private static void stoneCutting(RecipeOutput recipeOutput, TagKey<Item> input, ItemLike output, int outputCount) {
        var defaultID = getDefaultRecipeId(output);
        var recipeID = defaultID.withSuffix(input.location().getPath() + "_stonecutting");
        stonecutting(Ingredient.of(input), RecipeCategory.MISC, output, outputCount)
                .unlockedBy("has_input", has(input))
                .save(recipeOutput, recipeID);
    }

    public record MalumDatagenRockSet(
            String prefix,
            Item rock, Item polishedRock, Item bricks, Item tiles, Item mosaic,
            Item rockSlab, Item polishedRockSlab, Item bricksSlab, Item tilesSlab, Item mosaicSlab,
            Item rockStairs, Item polishedRockStairs, Item bricksStairs, Item tilesStairs, Item mosaicStairs,
            Item rockWall, Item polishedRockWall, Item bricksWall, Item tilesWall, Item mosaicWall,
            Item column, Item cutRock, Item chiseledRock,
            Item pressurePlate, Item button,
            Item itemStand, Item itemPedestal,
            TagKey<Item> setEncompassingTag, TagKey<Item> blockTag, TagKey<Item> stairTag,
            TagKey<Item> slabTag, TagKey<Item> wallTag
    ) {
    }
}