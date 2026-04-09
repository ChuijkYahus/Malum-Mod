package com.sammy.malum.datagen.recipe.crafting;

import com.sammy.malum.datagen.recipe.RecipeDatagenCommons;
import com.sammy.malum.datagen.tag.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.content.MalumContent;
import com.sammy.malum.registry.common.content.item.MalumItemProperties;

import net.minecraft.data.recipes.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.neoforged.neoforge.common.*;
import net.neoforged.neoforge.common.conditions.*;

import static net.minecraft.data.recipes.RecipeBuilder.*;
import static net.minecraft.data.recipes.ShapedRecipeBuilder.*;
import static net.minecraft.data.recipes.ShapelessRecipeBuilder.*;

public class MalumWoodSetDatagen implements IConditionBuilder {

    private static final MalumDatagenWoodSet RUNEWOOD = new MalumDatagenWoodSet(
            "runewood",
            MalumItemProperties.RUNEWOOD_LOG.get(), MalumItemProperties.RUNEWOOD.get(),
            MalumItemProperties.STRIPPED_RUNEWOOD_LOG.get(), MalumItemProperties.STRIPPED_RUNEWOOD.get(),
            MalumItemProperties.STRIPPED_SAPPY_RUNEWOOD_LOG.get(), MalumItemProperties.SAPPY_RUNEWOOD_LOG.get(),
            MalumItemProperties.RUNEWOOD_BOARDS.get(), MalumItemProperties.VERTICAL_RUNEWOOD_BOARDS.get(),
            MalumItemProperties.RUNEWOOD_BOARDS_SLAB.get(), MalumItemProperties.VERTICAL_RUNEWOOD_BOARDS_SLAB.get(),
            MalumItemProperties.RUNEWOOD_BOARDS_STAIRS.get(), MalumItemProperties.VERTICAL_RUNEWOOD_BOARDS_STAIRS.get(),
            MalumItemProperties.RUNEWOOD_PLANKS.get(), MalumItemProperties.VERTICAL_RUNEWOOD_PLANKS.get(), MalumItemProperties.RUNEWOOD_TILES.get(),
            MalumItemProperties.RUSTIC_RUNEWOOD_PLANKS.get(), MalumItemProperties.VERTICAL_RUSTIC_RUNEWOOD_PLANKS.get(), MalumItemProperties.RUSTIC_RUNEWOOD_TILES.get(),
            MalumItemProperties.RUNEWOOD_PLANKS_SLAB.get(), MalumItemProperties.VERTICAL_RUNEWOOD_PLANKS_SLAB.get(), MalumItemProperties.RUNEWOOD_TILES_SLAB.get(),
            MalumItemProperties.RUSTIC_RUNEWOOD_PLANKS_SLAB.get(), MalumItemProperties.VERTICAL_RUSTIC_RUNEWOOD_PLANKS_SLAB.get(), MalumItemProperties.RUSTIC_RUNEWOOD_TILES_SLAB.get(),
            MalumItemProperties.RUNEWOOD_PLANKS_STAIRS.get(), MalumItemProperties.VERTICAL_RUNEWOOD_PLANKS_STAIRS.get(), MalumItemProperties.RUNEWOOD_TILES_STAIRS.get(),
            MalumItemProperties.RUSTIC_RUNEWOOD_PLANKS_STAIRS.get(), MalumItemProperties.VERTICAL_RUSTIC_RUNEWOOD_PLANKS_STAIRS.get(), MalumItemProperties.RUSTIC_RUNEWOOD_TILES_STAIRS.get(),
            MalumItemProperties.RUNEWOOD_PANEL.get(), MalumItemProperties.CUT_RUNEWOOD_PLANKS.get(), MalumItemProperties.RUNEWOOD_BEAM.get(),
            MalumItemProperties.RUNEWOOD_BUTTON.get(), MalumItemProperties.RUNEWOOD_PRESSURE_PLATE.get(),
            MalumItemProperties.RUNEWOOD_DOOR.get(), MalumItemProperties.BOLTED_RUNEWOOD_DOOR.get(),
            MalumItemProperties.RUNEWOOD_BOARDS_DOOR.get(), MalumItemProperties.BOLTED_RUNEWOOD_BOARDS_DOOR.get(),
            MalumItemProperties.RUNEWOOD_TRAPDOOR.get(), MalumItemProperties.BOLTED_RUNEWOOD_TRAPDOOR.get(),
            MalumItemProperties.RUNEWOOD_BOARDS_TRAPDOOR.get(), MalumItemProperties.BOLTED_RUNEWOOD_BOARDS_TRAPDOOR.get(),
            MalumItemProperties.RUNEWOOD_FENCE.get(), MalumItemProperties.RUNEWOOD_FENCE_GATE.get(),
            MalumItemProperties.RUNEWOOD_BOARDS_WALL.get(),
            MalumItemProperties.RUNEWOOD_SIGN.get(), MalumItemProperties.RUNEWOOD_SIGN.get(),
            MalumItemProperties.RUNEWOOD_ITEM_STAND.get(), MalumItemProperties.RUNEWOOD_ITEM_PEDESTAL.get(),
            MalumItemProperties.GILDED_RUNEWOOD_ITEM_STAND.get(), MalumItemProperties.GILDED_RUNEWOOD_ITEM_PEDESTAL.get(),
            MalumTags.Items.RUNEWOOD_LOGS, MalumTags.Items.RUNEWOOD_BOARD_INGREDIENT, MalumTags.Items.RUNEWOOD_PLANKS, MalumTags.Items.RUNEWOOD_BOARDS, MalumTags.Items.RUNEWOOD_STAIRS, MalumTags.Items.RUNEWOOD_SLABS,
            MalumItemProperties.RUNEWOOD_BOAT.get(), MalumItemProperties.RUNEWOOD_CHEST_BOAT.get(),
            MalumContent.Materials.HALLOWED_GOLD_NUGGET.get()
    );

    private static final MalumDatagenWoodSet SOULWOOD = new MalumDatagenWoodSet(
            "soulwood",
            MalumItemProperties.SOULWOOD_LOG.get(), MalumItemProperties.SOULWOOD.get(),
            MalumItemProperties.STRIPPED_SOULWOOD_LOG.get(), MalumItemProperties.STRIPPED_SOULWOOD.get(),
            MalumItemProperties.STRIPPED_SAPPY_SOULWOOD_LOG.get(), MalumItemProperties.SAPPY_SOULWOOD_LOG.get(),
            MalumItemProperties.SOULWOOD_BOARDS.get(), MalumItemProperties.VERTICAL_SOULWOOD_BOARDS.get(),
            MalumItemProperties.SOULWOOD_BOARDS_SLAB.get(), MalumItemProperties.VERTICAL_SOULWOOD_BOARDS_SLAB.get(),
            MalumItemProperties.SOULWOOD_BOARDS_STAIRS.get(), MalumItemProperties.VERTICAL_SOULWOOD_BOARDS_STAIRS.get(),
            MalumItemProperties.SOULWOOD_PLANKS.get(), MalumItemProperties.VERTICAL_SOULWOOD_PLANKS.get(), MalumItemProperties.SOULWOOD_TILES.get(),
            MalumItemProperties.RUSTIC_SOULWOOD_PLANKS.get(), MalumItemProperties.VERTICAL_RUSTIC_SOULWOOD_PLANKS.get(), MalumItemProperties.RUSTIC_SOULWOOD_TILES.get(),
            MalumItemProperties.SOULWOOD_PLANKS_SLAB.get(), MalumItemProperties.VERTICAL_SOULWOOD_PLANKS_SLAB.get(), MalumItemProperties.SOULWOOD_TILES_SLAB.get(),
            MalumItemProperties.RUSTIC_SOULWOOD_PLANKS_SLAB.get(), MalumItemProperties.VERTICAL_RUSTIC_SOULWOOD_PLANKS_SLAB.get(), MalumItemProperties.RUSTIC_SOULWOOD_TILES_SLAB.get(),
            MalumItemProperties.SOULWOOD_PLANKS_STAIRS.get(), MalumItemProperties.VERTICAL_SOULWOOD_PLANKS_STAIRS.get(), MalumItemProperties.SOULWOOD_TILES_STAIRS.get(),
            MalumItemProperties.RUSTIC_SOULWOOD_PLANKS_STAIRS.get(), MalumItemProperties.VERTICAL_RUSTIC_SOULWOOD_PLANKS_STAIRS.get(), MalumItemProperties.RUSTIC_SOULWOOD_TILES_STAIRS.get(),
            MalumItemProperties.SOULWOOD_PANEL.get(), MalumItemProperties.CUT_SOULWOOD_PLANKS.get(), MalumItemProperties.SOULWOOD_BEAM.get(),
            MalumItemProperties.SOULWOOD_BUTTON.get(), MalumItemProperties.SOULWOOD_PRESSURE_PLATE.get(),
            MalumItemProperties.SOULWOOD_DOOR.get(), MalumItemProperties.BOLTED_SOULWOOD_DOOR.get(),
            MalumItemProperties.SOULWOOD_BOARDS_DOOR.get(), MalumItemProperties.BOLTED_SOULWOOD_BOARDS_DOOR.get(),
            MalumItemProperties.SOULWOOD_TRAPDOOR.get(), MalumItemProperties.BOLTED_SOULWOOD_TRAPDOOR.get(),
            MalumItemProperties.SOULWOOD_BOARDS_TRAPDOOR.get(), MalumItemProperties.BOLTED_SOULWOOD_BOARDS_TRAPDOOR.get(),
            MalumItemProperties.SOULWOOD_FENCE.get(), MalumItemProperties.SOULWOOD_FENCE_GATE.get(),
            MalumItemProperties.SOULWOOD_BOARDS_WALL.get(),
            MalumItemProperties.SOULWOOD_SIGN.get(), MalumItemProperties.SOULWOOD_SIGN.get(),
            MalumItemProperties.SOULWOOD_ITEM_STAND.get(), MalumItemProperties.SOULWOOD_ITEM_PEDESTAL.get(),
            MalumItemProperties.ORNATE_SOULWOOD_ITEM_STAND.get(), MalumItemProperties.ORNATE_SOULWOOD_ITEM_PEDESTAL.get(),
            MalumTags.Items.SOULWOOD_LOGS, MalumTags.Items.SOULWOOD_BOARD_INGREDIENT, MalumTags.Items.SOULWOOD_PLANKS, MalumTags.Items.SOULWOOD_BOARDS, MalumTags.Items.SOULWOOD_STAIRS, MalumTags.Items.SOULWOOD_SLABS,
            MalumItemProperties.SOULWOOD_BOAT.get(), MalumItemProperties.SOULWOOD_CHEST_BOAT.get(),
            MalumContent.Materials.SOUL_STAINED_STEEL_NUGGET.get()
    );

    public static void addTags(MalumItemTagDatagen provider) {
        addTags(provider, RUNEWOOD);
        addTags(provider, SOULWOOD);
    }

    public static void buildRecipes(RecipeOutput recipeOutput) {
        buildRecipes(recipeOutput, RUNEWOOD);
        buildRecipes(recipeOutput, SOULWOOD);
    }

    protected static void addTags(MalumItemTagDatagen provider, MalumDatagenWoodSet woodSet) {
        provider.tag(woodSet.logTag).add(
                        woodSet.log, woodSet.strippedLog, woodSet.wood,
                        woodSet.strippedWood, woodSet.sapFilledLog, woodSet.strippedSapFilledLog);

        provider.tag(woodSet.boardIngredientTag).add(woodSet.log, woodSet.wood);

        provider.tag(woodSet.planksTag).add(
                woodSet.planks, woodSet.verticalPlanks,
                woodSet.rusticPlanks, woodSet.verticalRusticPlanks,
                woodSet.tiles, woodSet.rusticTiles
        );

        provider.tag(woodSet.boardsTag).add(
                woodSet.boards, woodSet.verticalBoards
        );

        provider.tag(woodSet.stairsTag).add(
                woodSet.boardsStairs, woodSet.verticalBoardsStairs,
                woodSet.planksStairs, woodSet.verticalPlanksStairs,
                woodSet.rusticPlanksStairs, woodSet.verticalRusticPlanksStairs,
                woodSet.tilesStairs, woodSet.rusticTilesStairs
        );

        provider.tag(woodSet.slabTag).add(
                woodSet.boardsSlab, woodSet.verticalBoardsSlab,
                woodSet.planksSlab, woodSet.verticalPlanksSlab,
                woodSet.rusticPlanksSlab, woodSet.verticalRusticPlanksSlab,
                woodSet.tilesSlab, woodSet.rusticTilesSlab
        );
    }

    protected static void buildRecipes(RecipeOutput recipeOutput, MalumDatagenWoodSet woodSet) {
        shapelessPlanks(recipeOutput, woodSet.planks, woodSet.logTag);

        rusticExchange(recipeOutput, woodSet.rusticPlanks, woodSet.planks);
        rusticExchange(recipeOutput, woodSet.verticalRusticPlanks, woodSet.verticalPlanks);
        rusticExchange(recipeOutput, woodSet.rusticTiles, woodSet.tiles);

        shapedBoards(recipeOutput, woodSet.boards, woodSet.boardIngredientTag);

        shapedSlab(recipeOutput, woodSet.boardsSlab, woodSet.boards);
        shapedStairs(recipeOutput, woodSet.boardsStairs, woodSet.boards);
        shapedSlab(recipeOutput, woodSet.verticalBoardsSlab, woodSet.verticalBoards);
        shapedStairs(recipeOutput, woodSet.verticalBoardsStairs, woodSet.verticalBoards);

        planksExchange(recipeOutput, woodSet.boards, woodSet.verticalBoards);
        planksExchange(recipeOutput, woodSet.verticalBoards, woodSet.boards);

        shapedSlab(recipeOutput, woodSet.planksSlab, woodSet.planks);
        shapedStairs(recipeOutput, woodSet.planksStairs, woodSet.planks);
        shapedSlab(recipeOutput, woodSet.verticalPlanksSlab, woodSet.verticalPlanks);
        shapedStairs(recipeOutput, woodSet.verticalPlanksStairs, woodSet.verticalPlanks);
        shapedSlab(recipeOutput, woodSet.tilesSlab, woodSet.tiles);
        shapedStairs(recipeOutput, woodSet.tilesStairs, woodSet.tiles);

        shapedSlab(recipeOutput, woodSet.rusticPlanksSlab, woodSet.rusticPlanks);
        shapedStairs(recipeOutput, woodSet.rusticPlanksStairs, woodSet.rusticPlanks);
        shapedSlab(recipeOutput, woodSet.verticalRusticPlanksSlab, woodSet.verticalRusticPlanks);
        shapedStairs(recipeOutput, woodSet.verticalRusticPlanksStairs, woodSet.verticalRusticPlanks);
        shapedSlab(recipeOutput, woodSet.rusticTilesSlab, woodSet.rusticTiles);
        shapedStairs(recipeOutput, woodSet.rusticTilesStairs, woodSet.rusticTiles);

        shapelessWood(recipeOutput, woodSet.wood, woodSet.log);
        shapelessWood(recipeOutput, woodSet.strippedWood, woodSet.strippedLog);

        shapelessButton(recipeOutput, woodSet.button, woodSet.planksTag);
        shapedPressurePlate(recipeOutput, woodSet.pressurePlate, woodSet.planksTag);

        shapedDoor(recipeOutput, woodSet.door, woodSet.planksTag);
        shapedDoor(recipeOutput, woodSet.boardsDoor, woodSet.boardsTag);
        shapedTrapdoor(recipeOutput, woodSet.trapdoor, woodSet.planksTag);
        shapedTrapdoor(recipeOutput, woodSet.boardsTrapdoor, woodSet.boardsTag);

        bolting(recipeOutput, woodSet.boltedDoor, woodSet.door);
        bolting(recipeOutput, woodSet.boltedBoardsDoor, woodSet.boardsDoor);
        bolting(recipeOutput, woodSet.boltedTrapdoor, woodSet.trapdoor);
        bolting(recipeOutput, woodSet.boltedBoardsTrapdoor, woodSet.boardsTrapdoor);

        shapedFence(recipeOutput, woodSet.fence, woodSet.planksTag);
        shapedFenceGate(recipeOutput, woodSet.fenceGate, woodSet.planksTag);

        shapedSign(recipeOutput, woodSet.sign, woodSet.planksTag);

        planksExchange(recipeOutput, woodSet.planks, woodSet.verticalPlanks);
        planksExchange(recipeOutput, woodSet.verticalPlanks, woodSet.tiles);
        planksExchange(recipeOutput, woodSet.tiles, woodSet.planks);

        planksExchange(recipeOutput, woodSet.rusticPlanks, woodSet.verticalRusticPlanks);
        planksExchange(recipeOutput, woodSet.verticalRusticPlanks, woodSet.rusticTiles);
        planksExchange(recipeOutput, woodSet.rusticTiles, woodSet.rusticPlanks);

        shapedBoat(recipeOutput, woodSet.boat, woodSet.planksTag);

        shapedPanel(recipeOutput, woodSet.panel, woodSet.planksTag);

        var condition = RecipeDatagenCommons.has(woodSet.planksTag);

        shaped(RecipeCategory.MISC, woodSet.boardWall, 6)
                .define('X', woodSet.boardsTag)
                .define('Y', net.minecraft.world.item.Items.STICK)
                .pattern("XYX")
                .pattern("XYX")
                .unlockedBy("has_input", condition)
                .save(recipeOutput);

        shaped(RecipeCategory.MISC, woodSet.cutPlanks, 2)
                .define('X', woodSet.panel)
                .define('Y', woodSet.planksTag)
                .pattern("X").pattern("Y")
                .unlockedBy("has_input", condition)
                .save(recipeOutput);
        shaped(RecipeCategory.MISC, woodSet.beam, 3)
                .define('#', woodSet.planksTag)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .unlockedBy("has_input", condition)
                .save(recipeOutput);

        shaped(RecipeCategory.MISC, woodSet.itemStand, 2)
                .define('X', woodSet.planksTag)
                .define('Y', woodSet.slabTag)
                .pattern("YYY")
                .pattern("XXX")
                .unlockedBy("has_input", condition)
                .save(recipeOutput);
        shaped(RecipeCategory.MISC, woodSet.itemPedestal)
                .define('X', woodSet.planksTag)
                .define('Y', woodSet.slabTag)
                .pattern("YYY")
                .pattern(" X ")
                .pattern("YYY")
                .unlockedBy("has_input", condition)
                .save(recipeOutput);

        shaped(RecipeCategory.MISC, woodSet.decoratedItemStand)
                .define('X', woodSet.itemStand)
                .define('Y', woodSet.metalNugget)
                .pattern("YXY")
                .unlockedBy("has_input", condition)
                .save(recipeOutput);

        shaped(RecipeCategory.MISC, woodSet.decoratedItemPedestal)
                .define('X', woodSet.itemPedestal)
                .define('Y', woodSet.metalNugget)
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

    public record MalumDatagenWoodSet(
            String prefix,

            Item log, Item wood,
            Item strippedLog, Item strippedWood,

            Item sapFilledLog, Item strippedSapFilledLog,

            Item boards, Item verticalBoards,
            Item boardsSlab, Item verticalBoardsSlab,
            Item boardsStairs, Item verticalBoardsStairs,

            Item planks, Item verticalPlanks, Item tiles,
            Item rusticPlanks, Item verticalRusticPlanks, Item rusticTiles,
            Item planksSlab, Item verticalPlanksSlab, Item tilesSlab,
            Item rusticPlanksSlab, Item verticalRusticPlanksSlab, Item rusticTilesSlab,
            Item planksStairs, Item verticalPlanksStairs, Item tilesStairs,
            Item rusticPlanksStairs, Item verticalRusticPlanksStairs, Item rusticTilesStairs,

            Item panel, Item cutPlanks, Item beam,

            Item button, Item pressurePlate,

            Item door, Item boltedDoor,
            Item boardsDoor, Item boltedBoardsDoor,

            Item trapdoor, Item boltedTrapdoor,
            Item boardsTrapdoor, Item boltedBoardsTrapdoor,

            Item fence, Item fenceGate,

            Item boardWall,

            Item sign, Item hangingSign,

            Item itemStand, Item itemPedestal,
            Item decoratedItemStand, Item decoratedItemPedestal,

            TagKey<Item> logTag, TagKey<Item> boardIngredientTag, TagKey<Item> planksTag, TagKey<Item> boardsTag, TagKey<Item> stairsTag, TagKey<Item> slabTag,

            Item boat, Item chestBoat,

            Item metalNugget
    ) { }
}