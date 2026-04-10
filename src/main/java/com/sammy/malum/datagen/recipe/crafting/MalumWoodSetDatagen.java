package com.sammy.malum.datagen.recipe.crafting;

import com.sammy.malum.datagen.recipe.RecipeDatagenCommons;
import com.sammy.malum.registry.common.MalumContent;

import com.sammy.malum.registry.common.util.WoodBlockSet;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.neoforged.neoforge.common.*;
import net.neoforged.neoforge.common.conditions.*;

import static com.sammy.malum.registry.common.MalumTags.Items.*;
import static net.minecraft.data.recipes.RecipeBuilder.*;
import static net.minecraft.data.recipes.ShapedRecipeBuilder.*;
import static net.minecraft.data.recipes.ShapelessRecipeBuilder.*;

public class MalumWoodSetDatagen implements IConditionBuilder {

    public static void buildRecipes(RecipeOutput recipeOutput) {
        buildRecipes(recipeOutput, MalumContent.BlockSets.RUNEWOOD_SET, MalumContent.BlockSets.RUNEWOOD_BOAT, MalumContent.Materials.HALLOWED_GOLD_NUGGET, 
                RUNEWOOD_LOGS, RUNEWOOD_PLANKS, RUNEWOOD_BOARDS, RUNEWOOD_SLABS, RUNEWOOD_BOARD_INGREDIENT);
        buildRecipes(recipeOutput, MalumContent.BlockSets.SOULWOOD_SET, MalumContent.BlockSets.SOULWOOD_BOAT, MalumContent.Materials.HALLOWED_GOLD_NUGGET,
                SOULWOOD_LOGS, SOULWOOD_PLANKS, SOULWOOD_BOARDS, SOULWOOD_SLABS, SOULWOOD_BOARD_INGREDIENT);
    }

    protected static void buildRecipes(
            RecipeOutput recipeOutput,
            WoodBlockSet set,
            ItemLike boat,
            ItemLike metalNugget,
            TagKey<Item> logTag,
            TagKey<Item> planksTag,
            TagKey<Item> boardsTag,
            TagKey<Item> slabTag,
            TagKey<Item> boardIngredientTag
    ) {
        shapelessPlanks(recipeOutput, set.getPlanks(), logTag);

        rusticExchange(recipeOutput, set.getRusticPlanks(), set.getPlanks());
        rusticExchange(recipeOutput, set.getVerticalRusticPlanks(), set.getVerticalPlanks());
        rusticExchange(recipeOutput, set.getRusticTiles(), set.getTiles());

        shapedBoards(recipeOutput, set.getBoards(), boardIngredientTag);

        shapedSlab(recipeOutput, set.getBoardsSlab(), set.getBoards());
        shapedStairs(recipeOutput, set.getBoardsStairs(), set.getBoards());
        shapedSlab(recipeOutput, set.getVerticalBoardsSlab(), set.getVerticalBoards());
        shapedStairs(recipeOutput, set.getVerticalBoardsStairs(), set.getVerticalBoards());

        planksExchange(recipeOutput, set.getBoards(), set.getVerticalBoards());
        planksExchange(recipeOutput, set.getVerticalBoards(), set.getBoards());

        shapedSlab(recipeOutput, set.getPlanksSlab(), set.getPlanks());
        shapedStairs(recipeOutput, set.getPlanksStairs(), set.getPlanks());
        shapedSlab(recipeOutput, set.getVerticalPlanksSlab(), set.getVerticalPlanks());
        shapedStairs(recipeOutput, set.getVerticalPlanksStairs(), set.getVerticalPlanks());
        shapedSlab(recipeOutput, set.getTilesSlab(), set.getTiles());
        shapedStairs(recipeOutput, set.getTilesStairs(), set.getTiles());

        shapedSlab(recipeOutput, set.getRusticPlanksSlab(), set.getRusticPlanks());
        shapedStairs(recipeOutput, set.getRusticPlanksStairs(), set.getRusticPlanks());
        shapedSlab(recipeOutput, set.getVerticalRusticPlanksSlab(), set.getVerticalRusticPlanks());
        shapedStairs(recipeOutput, set.getVerticalRusticPlanksStairs(), set.getVerticalRusticPlanks());
        shapedSlab(recipeOutput, set.getRusticTilesSlab(), set.getRusticTiles());
        shapedStairs(recipeOutput, set.getRusticTilesStairs(), set.getRusticTiles());

        shapelessWood(recipeOutput, set.getWood(), set.getLog());
        shapelessWood(recipeOutput, set.getStrippedWood(), set.getStrippedLog());

        shapelessButton(recipeOutput, set.getButton(), planksTag);
        shapedPressurePlate(recipeOutput, set.getPressurePlate(), planksTag);

        shapedDoor(recipeOutput, set.getDoor(), planksTag);
        shapedDoor(recipeOutput, set.getHeavyDoor(), boardsTag);
        shapedTrapdoor(recipeOutput, set.getTrapdoor(), planksTag);
        shapedTrapdoor(recipeOutput, set.getHeavyTrapdoor(), boardsTag);

        bolting(recipeOutput, set.getBoltedDoor(), set.getDoor());
        bolting(recipeOutput, set.getHeavyBoltedDoor(), set.getHeavyDoor());
        bolting(recipeOutput, set.getBoltedTrapdoor(), set.getTrapdoor());
        bolting(recipeOutput, set.getHeavyBoltedTrapdoor(), set.getHeavyTrapdoor());

        shapedFence(recipeOutput, set.getFence(), planksTag);
        shapedFenceGate(recipeOutput, set.getFenceGate(), planksTag);

        shapedSign(recipeOutput, set.getSign(), planksTag);

        planksExchange(recipeOutput, set.getPlanks(), set.getVerticalPlanks());
        planksExchange(recipeOutput, set.getVerticalPlanks(), set.getTiles());
        planksExchange(recipeOutput, set.getTiles(), set.getPlanks());

        planksExchange(recipeOutput, set.getRusticPlanks(), set.getVerticalRusticPlanks());
        planksExchange(recipeOutput, set.getVerticalRusticPlanks(), set.getRusticTiles());
        planksExchange(recipeOutput, set.getRusticTiles(), set.getRusticPlanks());

        shapedBoat(recipeOutput, boat, planksTag);

        shapedPanel(recipeOutput, set.getPanel(), planksTag);

        var condition = RecipeDatagenCommons.has(planksTag);

        shaped(RecipeCategory.MISC, set.getBoardsWall(), 6)
                .define('X', boardsTag)
                .define('Y', net.minecraft.world.item.Items.STICK)
                .pattern("XYX")
                .pattern("XYX")
                .unlockedBy("has_input", condition)
                .save(recipeOutput);

        shaped(RecipeCategory.MISC, set.getCutPlanks(), 2)
                .define('X', set.getPanel())
                .define('Y', planksTag)
                .pattern("X").pattern("Y")
                .unlockedBy("has_input", condition)
                .save(recipeOutput);

        shaped(RecipeCategory.MISC, set.getBeam(), 3)
                .define('#', planksTag)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .unlockedBy("has_input", condition)
                .save(recipeOutput);

        shaped(RecipeCategory.MISC, set.getItemStand(), 2)
                .define('X', planksTag)
                .define('Y', slabTag)
                .pattern("YYY")
                .pattern("XXX")
                .unlockedBy("has_input", condition)
                .save(recipeOutput);

        shaped(RecipeCategory.MISC, set.getItemPedestal())
                .define('X', planksTag)
                .define('Y', slabTag)
                .pattern("YYY")
                .pattern(" X ")
                .pattern("YYY")
                .unlockedBy("has_input", condition)
                .save(recipeOutput);

        shaped(RecipeCategory.MISC, set.getDecoratedItemStand())
                .define('X', set.getItemStand())
                .define('Y', metalNugget)
                .pattern("YXY")
                .unlockedBy("has_input", condition)
                .save(recipeOutput);

        shaped(RecipeCategory.MISC, set.getDecoratedItemPedestal())
                .define('X', set.getItemPedestal())
                .define('Y', metalNugget)
                .pattern("YXY")
                .unlockedBy("has_input", condition)
                .save(recipeOutput);
    }

    private static void bolting(RecipeOutput recipeOutput, ItemLike output, ItemLike input) {
        final ResourceLocation recipeID = getDefaultRecipeId(output).withSuffix("_bolting");
        shapeless(RecipeCategory.MISC, output)
                .requires(input)
                .requires(net.minecraft.world.item.Items.IRON_NUGGET)
                .unlockedBy("has_input", RecipeDatagenCommons.has(input))
                .save(recipeOutput, recipeID);
    }

    private static void planksExchange(RecipeOutput recipeOutput, ItemLike input, ItemLike planks) {
        final ResourceLocation recipeID = getDefaultRecipeId(planks).withSuffix("_from_" + getDefaultRecipeId(input).getPath());
        shaped(RecipeCategory.MISC, planks, 4)
                .define('#', input)
                .pattern(" # ")
                .pattern("# #")
                .pattern(" # ")
                .unlockedBy("has_input", RecipeDatagenCommons.has(input))
                .save(recipeOutput, recipeID);
    }

    private static void rusticExchange(RecipeOutput recipeOutput, ItemLike output, ItemLike input) {
        final ResourceLocation recipeID = getDefaultRecipeId(output).withSuffix("_from_" + getDefaultRecipeId(input).getPath());
        shaped(RecipeCategory.MISC, output, 5)
                .define('#', input)
                .pattern(" # ")
                .pattern("###")
                .pattern(" # ")
                .unlockedBy("has_input", RecipeDatagenCommons.has(input)).save(recipeOutput, recipeID);
    }

    private static void shapelessPlanks(RecipeOutput recipeOutput, ItemLike planks, TagKey<Item> input) {
        shapeless(RecipeCategory.MISC, planks, 4)
                .requires(input)
                .group("planks")
                .unlockedBy("has_logs", RecipeDatagenCommons.has(input))
                .save(recipeOutput);
    }

    private static void shapedBoards(RecipeOutput recipeOutput, ItemLike output, TagKey<Item> input) {
        shaped(RecipeCategory.MISC, output, 20)
                .define('#', input)
                .pattern(" # ")
                .pattern("###")
                .pattern(" # ")
                .unlockedBy("has_input", RecipeDatagenCommons.has(input))
                .save(recipeOutput);
    }

    private static void shapedPanel(RecipeOutput recipeOutput, ItemLike output, TagKey<Item> input) {
        shaped(RecipeCategory.MISC, output, 9)
                .define('#', input)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_input", RecipeDatagenCommons.has(input))
                .save(recipeOutput);
    }

    private static void shapelessWood(RecipeOutput recipeOutput, ItemLike stripped, ItemLike input) {
        shaped(RecipeCategory.MISC, stripped, 3)
                .define('#', input)
                .pattern("##")
                .pattern("##")
                .group("bark")
                .unlockedBy("has_log", RecipeDatagenCommons.has(input))
                .save(recipeOutput);
    }

    private static void shapelessButton(RecipeOutput recipeOutput, ItemLike button, TagKey<Item> input) {
        shapeless(RecipeCategory.MISC, button)
                .requires(input)
                .unlockedBy("has_input", RecipeDatagenCommons.has(input))
                .save(recipeOutput);
    }

    private static void shapedDoor(RecipeOutput recipeOutput, ItemLike door, TagKey<Item> input) {
        shaped(RecipeCategory.MISC, door, 3)
                .define('#', input)
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_input", RecipeDatagenCommons.has(input))
                .save(recipeOutput);
    }

    private static void shapedFence(RecipeOutput recipeOutput, ItemLike fence, TagKey<Item> input) {
        shaped(RecipeCategory.MISC, fence, 3)
                .define('#', Tags.Items.RODS_WOODEN)
                .define('W', input)
                .pattern("W#W")
                .pattern("W#W")
                .unlockedBy("has_input", RecipeDatagenCommons.has(input))
                .save(recipeOutput);
    }

    private static void shapedFenceGate(RecipeOutput recipeOutput, ItemLike fenceGate, TagKey<Item> input) {
        shaped(RecipeCategory.MISC, fenceGate)
                .define('#', Tags.Items.RODS_WOODEN)
                .define('W', input)
                .pattern("#W#")
                .pattern("#W#")
                .unlockedBy("has_input", RecipeDatagenCommons.has(input))
                .save(recipeOutput);
    }

    private static void shapedPressurePlate(RecipeOutput recipeOutput, ItemLike pressurePlate, TagKey<Item> input) {
        shaped(RecipeCategory.MISC, pressurePlate)
                .define('#', input)
                .pattern("##")
                .unlockedBy("has_input", RecipeDatagenCommons.has(input))
                .save(recipeOutput);
    }

    private static void shapedSlab(RecipeOutput recipeOutput, ItemLike slab, ItemLike input) {
        shaped(RecipeCategory.MISC, slab, 6)
                .define('#', input)
                .pattern("###")
                .unlockedBy("has_input", RecipeDatagenCommons.has(input))
                .save(recipeOutput);
    }

    private static void shapedStairs(RecipeOutput recipeOutput, ItemLike stairs, ItemLike input) {
        shaped(RecipeCategory.MISC, stairs, 4)
                .define('#', input)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .unlockedBy("has_input", RecipeDatagenCommons.has(input))
                .save(recipeOutput);
    }

    private static void shapedTrapdoor(RecipeOutput recipeOutput, ItemLike trapdoor, TagKey<Item> input) {
        shaped(RecipeCategory.MISC, trapdoor, 2)
                .define('#', input)
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_input", RecipeDatagenCommons.has(input))
                .save(recipeOutput);
    }

    private static void shapedSign(RecipeOutput recipeOutput, ItemLike sign, TagKey<Item> input) {
        shaped(RecipeCategory.MISC, sign, 3)
                .group("sign")
                .define('#', input)
                .define('X', Tags.Items.RODS_WOODEN)
                .pattern("###")
                .pattern("###")
                .pattern(" X ")
                .unlockedBy("has_input", RecipeDatagenCommons.has(input))
                .save(recipeOutput);
    }

    private static void shapedBoat(RecipeOutput recipeOutput, ItemLike boat, TagKey<Item> input) {
        shaped(RecipeCategory.MISC, boat)
                .define('#', input)
                .pattern("# #")
                .pattern("###")
                .unlockedBy("has_input", RecipeDatagenCommons.has(input)).save(recipeOutput);
    }
}