package com.sammy.malum.datagen.recipe.crafting;

import com.mojang.datafixers.util.Pair;
import com.sammy.malum.MalumMod;
import com.sammy.malum.datagen.recipe.RecipeDatagenCommons;
import com.sammy.malum.registry.common.MalumContent;

import com.sammy.malum.registry.common.util.WoodBlockSet;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.*;
import net.neoforged.neoforge.common.*;
import net.neoforged.neoforge.common.conditions.*;

import static com.sammy.malum.datagen.recipe.RecipeDatagenCommons.*;
import static net.minecraft.data.recipes.ShapedRecipeBuilder.*;
import static net.minecraft.data.recipes.ShapelessRecipeBuilder.*;

public class MalumWoodSetDatagen implements IConditionBuilder {

    public static void buildRecipes(RecipeOutput recipeOutput) {
        buildRecipes(recipeOutput, MalumContent.BlockSets.RUNEWOOD_SET, MalumContent.BlockSets.RUNEWOOD_BOAT, MalumContent.Materials.HALLOWED_GOLD_NUGGET);
        buildRecipes(recipeOutput, MalumContent.BlockSets.SOULWOOD_SET, MalumContent.BlockSets.SOULWOOD_BOAT, MalumContent.Materials.HALLOWED_GOLD_NUGGET);
    }

    protected static void buildRecipes(
            RecipeOutput recipeOutput,
            WoodBlockSet set,
            ItemLike boat,
            ItemLike metalNugget
    ) {
        var allLogsTag = set.allLogsTag.itemTag();
        var logsTag = set.logsTag.itemTag();

        var boardsTag = set.boardsTag.itemTag();
        var planksTag = set.planksTag.itemTag();
        var plankSlabTag = set.plankSlabsTag.itemTag();

        shapelessPlanks(recipeOutput, set.planks.block, allLogsTag);
        shapedBoards(recipeOutput, set.boards.block, logsTag);

        exchange(recipeOutput, set.boards, set.verticalBoards, set.blocks);
        RecipeDatagenCommons.blockBundle(recipeOutput, set.boards);
        RecipeDatagenCommons.blockBundle(recipeOutput, set.verticalBoards);
        RecipeDatagenCommons.blockBundle(recipeOutput, set.blocks);

        exchange(recipeOutput, set.planks, set.verticalPlanks, set.tiles);
        RecipeDatagenCommons.blockBundle(recipeOutput, set.planks);
        RecipeDatagenCommons.blockBundle(recipeOutput, set.verticalPlanks);
        RecipeDatagenCommons.blockBundle(recipeOutput, set.tiles);

        shapelessWood(recipeOutput, set.wood, set.log);
        shapelessWood(recipeOutput, set.strippedWood, set.strippedLog);

        shapelessButton(recipeOutput, set.button, planksTag);
        shapedPressurePlate(recipeOutput, set.pressurePlate, planksTag);

        shapedDoor(recipeOutput, set.heavyDoor, boardsTag);
        shapedTrapdoor(recipeOutput, set.heavyTrapdoor, boardsTag);

        shapedDoor(recipeOutput, set.door, planksTag);
        shapedTrapdoor(recipeOutput, set.trapdoor, planksTag);

        shapedFence(recipeOutput, set.fence, planksTag);
        shapedFenceGate(recipeOutput, set.fenceGate, planksTag);

        shapedSign(recipeOutput, set.sign, planksTag);

        shapedBoat(recipeOutput, boat, planksTag);

        RecipeDatagenCommons.stepsOrAltar(recipeOutput, plankSlabTag, set.steps);
        RecipeDatagenCommons.beamOrColumn(recipeOutput, plankSlabTag, set.beam);

        RecipeDatagenCommons.pedestalRecipe(recipeOutput, planksTag, plankSlabTag, set.itemPedestal);
        RecipeDatagenCommons.standRecipe(recipeOutput, planksTag, plankSlabTag, set.itemStand);

        decoratedHolderRecipe(recipeOutput, set.itemPedestal, metalNugget, set.decoratedItemPedestal);
        decoratedHolderRecipe(recipeOutput, set.itemStand, metalNugget, set.decoratedItemStand);

        RecipeDatagenCommons.smelting(recipeOutput, MalumMod.malumPath(set.name("arcane_charcoal_from_%s")),
                Ingredient.of(logsTag), RecipeCategory.MISC, Pair.of("has_log", has(logsTag)), MalumContent.Materials.ARCANE_CHARCOAL, 1, 0.25f);

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

    private static void shapelessWood(RecipeOutput recipeOutput, ItemLike stripped, ItemLike input) {
        shaped(RecipeCategory.MISC, stripped, 3)
                .define('#', input)
                .pattern("##")
                .pattern("##")
                .group("bark")
                .unlockedBy("has_log", RecipeDatagenCommons.has(input))
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