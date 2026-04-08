package com.sammy.malum.datagen.recipe.crafting;

import com.sammy.malum.datagen.recipe.RecipeDatagenCommons;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.item.*;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.*;

import java.util.ArrayList;

import static net.minecraft.data.recipes.RecipeBuilder.*;
import static net.minecraft.data.recipes.ShapedRecipeBuilder.*;
import static net.minecraft.data.recipes.ShapelessRecipeBuilder.*;
import static net.minecraft.data.recipes.SingleItemRecipeBuilder.*;

public class MalumRockSetDatagen {

    private static final ArrayList<MalumDatagenRockSet> SETS = new ArrayList<>();

    static {
        registerSet(new MalumDatagenRockSet(
                "tainted_rock",
                MalumItems.TAINTED_ROCK.get(), MalumItems.POLISHED_TAINTED_ROCK.get(), MalumItems.TAINTED_ROCK_BRICKS.get(), MalumItems.TAINTED_ROCK_TILES.get(), MalumItems.TAINTED_ROCK_MOSAIC.get(),

                MalumItems.TAINTED_ROCK_SLAB.get(), MalumItems.POLISHED_TAINTED_ROCK_SLAB.get(), MalumItems.TAINTED_ROCK_BRICKS_SLAB.get(), MalumItems.TAINTED_ROCK_TILES_SLAB.get(), MalumItems.TAINTED_ROCK_MOSAIC_SLAB.get(),

                MalumItems.TAINTED_ROCK_STAIRS.get(), MalumItems.POLISHED_TAINTED_ROCK_STAIRS.get(), MalumItems.TAINTED_ROCK_BRICKS_STAIRS.get(), MalumItems.TAINTED_ROCK_TILES_STAIRS.get(), MalumItems.TAINTED_ROCK_MOSAIC_STAIRS.get(),

                MalumItems.TAINTED_ROCK_WALL.get(), MalumItems.POLISHED_TAINTED_ROCK_WALL.get(), MalumItems.TAINTED_ROCK_BRICKS_WALL.get(), MalumItems.TAINTED_ROCK_TILES_WALL.get(), MalumItems.TAINTED_ROCK_MOSAIC_WALL.get(),

                MalumItems.TAINTED_ROCK_COLUMN.get(), MalumItems.TAINTED_ROCK_ALTAR.get(),

                MalumItems.CUT_TAINTED_ROCK.get(), MalumItems.CHISELED_TAINTED_ROCK.get(),

                MalumItems.TAINTED_ROCK_PRESSURE_PLATE.get(), MalumItems.TAINTED_ROCK_BUTTON.get(),

                MalumItems.TAINTED_ROCK_ITEM_STAND.get(), MalumItems.TAINTED_ROCK_ITEM_PEDESTAL.get(),

                MalumTags.ItemTags.TAINTED_ROCK, MalumTags.ItemTags.TAINTED_ROCK_BLOCKS, MalumTags.ItemTags.TAINTED_ROCK_STAIRS, MalumTags.ItemTags.TAINTED_ROCK_SLABS, MalumTags.ItemTags.TAINTED_ROCK_WALLS
        ));

        registerSet(new MalumDatagenRockSet(
                "twisted_rock",
                MalumItems.TWISTED_ROCK.get(), MalumItems.POLISHED_TWISTED_ROCK.get(), MalumItems.TWISTED_ROCK_BRICKS.get(), MalumItems.TWISTED_ROCK_TILES.get(), MalumItems.TWISTED_ROCK_MOSAIC.get(),

                MalumItems.TWISTED_ROCK_SLAB.get(), MalumItems.POLISHED_TWISTED_ROCK_SLAB.get(), MalumItems.TWISTED_ROCK_BRICKS_SLAB.get(), MalumItems.TWISTED_ROCK_TILES_SLAB.get(), MalumItems.TWISTED_ROCK_MOSAIC_SLAB.get(),

                MalumItems.TWISTED_ROCK_STAIRS.get(), MalumItems.POLISHED_TWISTED_ROCK_STAIRS.get(), MalumItems.TWISTED_ROCK_BRICKS_STAIRS.get(), MalumItems.TWISTED_ROCK_TILES_STAIRS.get(), MalumItems.TWISTED_ROCK_MOSAIC_STAIRS.get(),

                MalumItems.TWISTED_ROCK_WALL.get(), MalumItems.POLISHED_TWISTED_ROCK_WALL.get(), MalumItems.TWISTED_ROCK_BRICKS_WALL.get(), MalumItems.TWISTED_ROCK_TILES_WALL.get(), MalumItems.TWISTED_ROCK_MOSAIC_WALL.get(),

                MalumItems.TWISTED_ROCK_COLUMN.get(), MalumItems.TWISTED_ROCK_ALTAR.get(),

                MalumItems.CUT_TWISTED_ROCK.get(), MalumItems.CHISELED_TWISTED_ROCK.get(),

                MalumItems.TWISTED_ROCK_PRESSURE_PLATE.get(), MalumItems.TWISTED_ROCK_BUTTON.get(),

                MalumItems.TWISTED_ROCK_ITEM_STAND.get(), MalumItems.TWISTED_ROCK_ITEM_PEDESTAL.get(),

                MalumTags.ItemTags.TWISTED_ROCK, MalumTags.ItemTags.TWISTED_ROCK_BLOCKS, MalumTags.ItemTags.TWISTED_ROCK_STAIRS, MalumTags.ItemTags.TWISTED_ROCK_SLABS, MalumTags.ItemTags.TWISTED_ROCK_WALLS
        ));

        registerSet(new MalumDatagenRockSet(
                "dross_stone",
                MalumItems.DROSS_STONE.get(), MalumItems.POLISHED_DROSS_STONE.get(), MalumItems.DROSS_STONE_BRICKS.get(), MalumItems.DROSS_STONE_TILES.get(), MalumItems.DROSS_STONE_MOSAIC.get(),

                MalumItems.DROSS_STONE_SLAB.get(), MalumItems.POLISHED_DROSS_STONE_SLAB.get(), MalumItems.DROSS_STONE_BRICKS_SLAB.get(), MalumItems.DROSS_STONE_TILES_SLAB.get(), MalumItems.DROSS_STONE_MOSAIC_SLAB.get(),

                MalumItems.DROSS_STONE_STAIRS.get(), MalumItems.POLISHED_DROSS_STONE_STAIRS.get(), MalumItems.DROSS_STONE_BRICKS_STAIRS.get(), MalumItems.DROSS_STONE_TILES_STAIRS.get(), MalumItems.DROSS_STONE_MOSAIC_STAIRS.get(),

                MalumItems.DROSS_STONE_WALL.get(), MalumItems.POLISHED_DROSS_STONE_WALL.get(), MalumItems.DROSS_STONE_BRICKS_WALL.get(), MalumItems.DROSS_STONE_TILES_WALL.get(), MalumItems.DROSS_STONE_MOSAIC_WALL.get(),

                MalumItems.DROSS_STONE_COLUMN.get(), MalumItems.DROSS_STONE_ALTAR.get(),

                MalumItems.CUT_DROSS_STONE.get(), MalumItems.CHISELED_DROSS_STONE.get(),

                MalumItems.DROSS_STONE_PRESSURE_PLATE.get(), MalumItems.DROSS_STONE_BUTTON.get(),

                MalumItems.DROSS_STONE_ITEM_STAND.get(), MalumItems.DROSS_STONE_ITEM_PEDESTAL.get(),

                MalumTags.ItemTags.DROSS_STONE, MalumTags.ItemTags.DROSS_STONE_BLOCKS, MalumTags.ItemTags.DROSS_STONE_STAIRS, MalumTags.ItemTags.DROSS_STONE_SLABS, MalumTags.ItemTags.DROSS_STONE_WALLS
        ));
    }

    public static void buildRecipes(RecipeOutput consumer) {
        for (MalumDatagenRockSet set : SETS) {
            set.buildRecipes(consumer);
        }
    }

    public static MalumDatagenRockSet registerSet(MalumDatagenRockSet set) {
        SETS.add(set);
        return set;
    }

    public record MalumDatagenRockSet(
            String prefix,
            Item rock, Item polishedRock, Item bricks, Item tiles, Item mosaic,
            Item rockSlab, Item polishedRockSlab, Item bricksSlab, Item tilesSlab, Item mosaicSlab,
            Item rockStairs, Item polishedRockStairs, Item bricksStairs, Item tilesStairs, Item mosaicStairs,
            Item rockWall, Item polishedRockWall, Item bricksWall, Item tilesWall, Item mosaicWall,
            Item column, Item altar, Item cutRock, Item chiseledRock,
            Item pressurePlate, Item button,
            Item itemStand, Item itemPedestal,
            TagKey<Item> setEncompassingTag, TagKey<Item> blockTag, TagKey<Item> stairTag,
            TagKey<Item> slabTag, TagKey<Item> wallTag
    ) {
        public void buildRecipes(RecipeOutput recipeOutput) {
            var condition = RecipeDatagenCommons.has(rock);
            slabRecipe(recipeOutput, rock, rockSlab);
            slabRecipe(recipeOutput, polishedRock, polishedRockSlab);
            slabRecipe(recipeOutput, bricks, bricksSlab);
            slabRecipe(recipeOutput, tiles, tilesSlab);
            slabRecipe(recipeOutput, mosaic, mosaicSlab);
            stairsRecipe(recipeOutput, rock, rockStairs);
            stairsRecipe(recipeOutput, polishedRock, polishedRockStairs);
            stairsRecipe(recipeOutput, bricks, bricksStairs);
            stairsRecipe(recipeOutput, tiles, tilesStairs);
            stairsRecipe(recipeOutput, mosaic, mosaicStairs);
            wallRecipe(recipeOutput, rock, rockWall);
            wallRecipe(recipeOutput, polishedRock, polishedRockWall);
            wallRecipe(recipeOutput, bricks, bricksWall);
            wallRecipe(recipeOutput, tiles, tilesWall);
            wallRecipe(recipeOutput, mosaic, mosaicWall);

            bricksLikeRecipe(recipeOutput, rock, polishedRock);
            bricksLikeRecipe(recipeOutput, polishedRock, bricks);
            bricksLikeRecipe(recipeOutput, bricks, tiles);
            bricksLikeRecipe(recipeOutput, tiles, mosaic);

            stoneCutting(recipeOutput, blockTag, rock);
            stoneCutting(recipeOutput, blockTag, polishedRock);
            stoneCutting(recipeOutput, blockTag, bricks);
            stoneCutting(recipeOutput, blockTag, tiles);
            stoneCutting(recipeOutput, blockTag, mosaic);

            stoneCutting(recipeOutput, blockTag, chiseledRock, 2);
            stoneCutting(recipeOutput, blockTag, cutRock, 2);
            stoneCutting(recipeOutput, blockTag, column, 2);
            stoneCutting(recipeOutput, blockTag, altar, 2);

            stoneCutting(recipeOutput, blockTag, itemPedestal);
            stoneCutting(recipeOutput, blockTag, itemStand);

            shapelessButton(recipeOutput, button, rock);
            shapedPressurePlate(recipeOutput, pressurePlate, rock);

            shaped(RecipeCategory.MISC, chiseledRock, 1)
                    .define('X', slabTag)
                    .pattern("X")
                    .pattern("X")
                    .unlockedBy("has_input", condition)
                    .save(recipeOutput);
            shaped(RecipeCategory.MISC, cutRock, 2)
                    .define('X', slabTag)
                    .define('Y', blockTag)
                    .pattern("X")
                    .pattern("Y")
                    .unlockedBy("has_input", condition)
                    .save(recipeOutput);
            shaped(RecipeCategory.MISC, column, 3)
                    .define('X', slabTag)
                    .pattern("X")
                    .pattern("X")
                    .pattern("X")
                    .unlockedBy("has_input", condition)
                    .save(recipeOutput);
            shaped(RecipeCategory.MISC, altar, 3)
                    .define('X', slabTag)
                    .pattern("XXX")
                    .unlockedBy("has_input", condition)
                    .save(recipeOutput);

            shaped(RecipeCategory.MISC, itemStand, 2)
                    .define('X', rock)
                    .define('Y', rockSlab)
                    .pattern("YYY")
                    .pattern("XXX")
                    .unlockedBy("has_input", condition)
                    .save(recipeOutput);
            shaped(RecipeCategory.MISC, itemPedestal)
                    .define('X', rock)
                    .define('Y', rockSlab)
                    .pattern("YYY")
                    .pattern(" X ")
                    .pattern("YYY")
                    .unlockedBy("has_input", condition)
                    .save(recipeOutput);
        }
     
        public void bricksLikeRecipe(RecipeOutput recipeOutput, ItemLike input, ItemLike output) {
            var recipeID = getDefaultRecipeId(output).withSuffix("_from_" + getDefaultRecipeId(input).getPath());
            shaped(RecipeCategory.MISC, output, 4)
                    .define('#', input)
                    .pattern("##")
                    .pattern("##")
                    .unlockedBy("has_input", RecipeDatagenCommons.has(input))
                    .save(recipeOutput, recipeID);
            stoneCutting(recipeOutput, input, output);
        }

        public void shapelessButton(RecipeOutput recipeOutput, ItemLike button, Item input) {
            shapeless(RecipeCategory.MISC, button)
                    .requires(input)
                    .unlockedBy("has_input", RecipeDatagenCommons.has(input))
                    .save(recipeOutput);
            stoneCutting(recipeOutput, input, button);
        }

        public void shapedPressurePlate(RecipeOutput recipeOutput, ItemLike pressurePlate, Item input) {
            shaped(RecipeCategory.MISC, pressurePlate)
                    .define('#', input)
                    .pattern("##")
                    .unlockedBy("has_input", RecipeDatagenCommons.has(input))
                    .save(recipeOutput);
        }

        public void slabRecipe(RecipeOutput recipeOutput, Item input, ItemLike slab) {
            shaped(RecipeCategory.MISC, slab, 6)
                    .define('#', input)
                    .pattern("###")
                    .unlockedBy("has_input", RecipeDatagenCommons.has(input))
                    .save(recipeOutput);
            stoneCutting(recipeOutput, blockTag, slab);
        }

        public void stairsRecipe(RecipeOutput recipeOutput, Item input, ItemLike stairs) {
            shaped(RecipeCategory.MISC, stairs, 4)
                    .define('#', input)
                    .pattern("#  ")
                    .pattern("## ")
                    .pattern("###")
                    .unlockedBy("has_input", RecipeDatagenCommons.has(input))
                    .save(recipeOutput);
            stoneCutting(recipeOutput, blockTag, stairs);
        }

        public void wallRecipe(RecipeOutput recipeOutput, Item input, ItemLike wall) {
            shaped(RecipeCategory.MISC, wall, 6)
                    .define('#', input)
                    .pattern("###")
                    .pattern("###")
                    .unlockedBy("has_input", RecipeDatagenCommons.has(input))
                    .save(recipeOutput);
            stoneCutting(recipeOutput, blockTag, wall);
        }

        public void stoneCutting(RecipeOutput recipeOutput, ItemLike input, ItemLike output) {
            stoneCutting(recipeOutput, input, output, 1);
        }

        public void stoneCutting(RecipeOutput recipeOutput, ItemLike input, ItemLike output, int outputCount) {
            var defaultID = getDefaultRecipeId(output);
            var recipeID = defaultID.withSuffix(getDefaultRecipeId(input).getPath() + "_stonecutting");
            stonecutting(Ingredient.of(input), RecipeCategory.MISC, output, outputCount)
                    .unlockedBy("has_input", RecipeDatagenCommons.has(input))
                    .save(recipeOutput, recipeID);
        }

        public void stoneCutting(RecipeOutput recipeOutput, TagKey<Item> input, ItemLike output) {
            stoneCutting(recipeOutput, input, output, 1);
        }

        public void stoneCutting(RecipeOutput recipeOutput, TagKey<Item> input, ItemLike output, int outputCount) {
            var defaultID = getDefaultRecipeId(output);
            var recipeID = defaultID.withSuffix(input.location().getPath() + "_stonecutting");
            stonecutting(Ingredient.of(input), RecipeCategory.MISC, output, outputCount)
                    .unlockedBy("has_input", RecipeDatagenCommons.has(input))
                    .save(recipeOutput, recipeID);
        }
    }
}