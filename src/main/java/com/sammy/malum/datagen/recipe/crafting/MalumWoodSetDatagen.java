package com.sammy.malum.datagen.recipe.crafting;

import com.sammy.malum.datagen.tag.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.item.*;

import net.minecraft.data.recipes.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.neoforged.neoforge.common.*;
import net.neoforged.neoforge.common.conditions.*;

import static net.minecraft.data.recipes.RecipeBuilder.*;
import static com.sammy.malum.datagen.recipe.MalumVanillaRecipes.*;
import static net.minecraft.data.recipes.ShapedRecipeBuilder.*;
import static net.minecraft.data.recipes.ShapelessRecipeBuilder.*;

public class MalumWoodSetDatagen implements IConditionBuilder {

    private static final MalumDatagenWoodSet RUNEWOOD = new MalumDatagenWoodSet(
            "runewood",
            MalumItems.RUNEWOOD_LOG.get(), MalumItems.RUNEWOOD.get(),
            MalumItems.STRIPPED_RUNEWOOD_LOG.get(), MalumItems.STRIPPED_RUNEWOOD.get(),
            MalumItems.REVEALED_RUNEWOOD_LOG.get(), MalumItems.EXPOSED_RUNEWOOD_LOG.get(),
            MalumItems.RUNEWOOD_BOARDS.get(), MalumItems.VERTICAL_RUNEWOOD_BOARDS.get(),
            MalumItems.RUNEWOOD_BOARDS_SLAB.get(), MalumItems.VERTICAL_RUNEWOOD_BOARDS_SLAB.get(),
            MalumItems.RUNEWOOD_BOARDS_STAIRS.get(), MalumItems.VERTICAL_RUNEWOOD_BOARDS_STAIRS.get(),
            MalumItems.RUNEWOOD_PLANKS.get(), MalumItems.VERTICAL_RUNEWOOD_PLANKS.get(), MalumItems.RUNEWOOD_TILES.get(),
            MalumItems.RUSTIC_RUNEWOOD_PLANKS.get(), MalumItems.VERTICAL_RUSTIC_RUNEWOOD_PLANKS.get(), MalumItems.RUSTIC_RUNEWOOD_TILES.get(),
            MalumItems.RUNEWOOD_PLANKS_SLAB.get(), MalumItems.VERTICAL_RUNEWOOD_PLANKS_SLAB.get(), MalumItems.RUNEWOOD_TILES_SLAB.get(),
            MalumItems.RUSTIC_RUNEWOOD_PLANKS_SLAB.get(), MalumItems.VERTICAL_RUSTIC_RUNEWOOD_PLANKS_SLAB.get(), MalumItems.RUSTIC_RUNEWOOD_TILES_SLAB.get(),
            MalumItems.RUNEWOOD_PLANKS_STAIRS.get(), MalumItems.VERTICAL_RUNEWOOD_PLANKS_STAIRS.get(), MalumItems.RUNEWOOD_TILES_STAIRS.get(),
            MalumItems.RUSTIC_RUNEWOOD_PLANKS_STAIRS.get(), MalumItems.VERTICAL_RUSTIC_RUNEWOOD_PLANKS_STAIRS.get(), MalumItems.RUSTIC_RUNEWOOD_TILES_STAIRS.get(),
            MalumItems.RUNEWOOD_PANEL.get(), MalumItems.CUT_RUNEWOOD_PLANKS.get(), MalumItems.RUNEWOOD_BEAM.get(),
            MalumItems.RUNEWOOD_BUTTON.get(), MalumItems.RUNEWOOD_PRESSURE_PLATE.get(),
            MalumItems.RUNEWOOD_DOOR.get(), MalumItems.BOLTED_RUNEWOOD_DOOR.get(),
            MalumItems.RUNEWOOD_BOARDS_DOOR.get(), MalumItems.BOLTED_RUNEWOOD_BOARDS_DOOR.get(),
            MalumItems.RUNEWOOD_TRAPDOOR.get(), MalumItems.BOLTED_RUNEWOOD_TRAPDOOR.get(),
            MalumItems.RUNEWOOD_BOARDS_TRAPDOOR.get(), MalumItems.BOLTED_RUNEWOOD_BOARDS_TRAPDOOR.get(),
            MalumItems.RUNEWOOD_FENCE.get(), MalumItems.RUNEWOOD_FENCE_GATE.get(),
            MalumItems.RUNEWOOD_BOARDS_WALL.get(),
            MalumItems.RUNEWOOD_SIGN.get(), MalumItems.RUNEWOOD_SIGN.get(),
            MalumItems.RUNEWOOD_ITEM_STAND.get(), MalumItems.RUNEWOOD_ITEM_PEDESTAL.get(),
            MalumItems.GILDED_RUNEWOOD_ITEM_STAND.get(), MalumItems.GILDED_RUNEWOOD_ITEM_PEDESTAL.get(),
            MalumTags.ItemTags.RUNEWOOD_LOGS, MalumTags.ItemTags.RUNEWOOD_BOARD_INGREDIENT, MalumTags.ItemTags.RUNEWOOD_PLANKS, MalumTags.ItemTags.RUNEWOOD_BOARDS, MalumTags.ItemTags.RUNEWOOD_STAIRS, MalumTags.ItemTags.RUNEWOOD_SLABS,
            MalumItems.RUNEWOOD_BOAT.get(),
            MalumItems.HALLOWED_GOLD_NUGGET.get()
    );

    private static final MalumDatagenWoodSet SOULWOOD = new MalumDatagenWoodSet(
            "soulwood",
            MalumItems.SOULWOOD_LOG.get(), MalumItems.SOULWOOD.get(),
            MalumItems.STRIPPED_SOULWOOD_LOG.get(), MalumItems.STRIPPED_SOULWOOD.get(),
            MalumItems.REVEALED_SOULWOOD_LOG.get(), MalumItems.EXPOSED_SOULWOOD_LOG.get(),
            MalumItems.SOULWOOD_BOARDS.get(), MalumItems.VERTICAL_SOULWOOD_BOARDS.get(),
            MalumItems.SOULWOOD_BOARDS_SLAB.get(), MalumItems.VERTICAL_SOULWOOD_BOARDS_SLAB.get(),
            MalumItems.SOULWOOD_BOARDS_STAIRS.get(), MalumItems.VERTICAL_SOULWOOD_BOARDS_STAIRS.get(),
            MalumItems.SOULWOOD_PLANKS.get(), MalumItems.VERTICAL_SOULWOOD_PLANKS.get(), MalumItems.SOULWOOD_TILES.get(),
            MalumItems.RUSTIC_SOULWOOD_PLANKS.get(), MalumItems.VERTICAL_RUSTIC_SOULWOOD_PLANKS.get(), MalumItems.RUSTIC_SOULWOOD_TILES.get(),
            MalumItems.SOULWOOD_PLANKS_SLAB.get(), MalumItems.VERTICAL_SOULWOOD_PLANKS_SLAB.get(), MalumItems.SOULWOOD_TILES_SLAB.get(),
            MalumItems.RUSTIC_SOULWOOD_PLANKS_SLAB.get(), MalumItems.VERTICAL_RUSTIC_SOULWOOD_PLANKS_SLAB.get(), MalumItems.RUSTIC_SOULWOOD_TILES_SLAB.get(),
            MalumItems.SOULWOOD_PLANKS_STAIRS.get(), MalumItems.VERTICAL_SOULWOOD_PLANKS_STAIRS.get(), MalumItems.SOULWOOD_TILES_STAIRS.get(),
            MalumItems.RUSTIC_SOULWOOD_PLANKS_STAIRS.get(), MalumItems.VERTICAL_RUSTIC_SOULWOOD_PLANKS_STAIRS.get(), MalumItems.RUSTIC_SOULWOOD_TILES_STAIRS.get(),
            MalumItems.SOULWOOD_PANEL.get(), MalumItems.CUT_SOULWOOD_PLANKS.get(), MalumItems.SOULWOOD_BEAM.get(),
            MalumItems.SOULWOOD_BUTTON.get(), MalumItems.SOULWOOD_PRESSURE_PLATE.get(),
            MalumItems.SOULWOOD_DOOR.get(), MalumItems.BOLTED_SOULWOOD_DOOR.get(),
            MalumItems.SOULWOOD_BOARDS_DOOR.get(), MalumItems.BOLTED_SOULWOOD_BOARDS_DOOR.get(),
            MalumItems.SOULWOOD_TRAPDOOR.get(), MalumItems.BOLTED_SOULWOOD_TRAPDOOR.get(),
            MalumItems.SOULWOOD_BOARDS_TRAPDOOR.get(), MalumItems.BOLTED_SOULWOOD_BOARDS_TRAPDOOR.get(),
            MalumItems.SOULWOOD_FENCE.get(), MalumItems.SOULWOOD_FENCE_GATE.get(),
            MalumItems.SOULWOOD_BOARDS_WALL.get(),
            MalumItems.SOULWOOD_SIGN.get(), MalumItems.SOULWOOD_SIGN.get(),
            MalumItems.SOULWOOD_ITEM_STAND.get(), MalumItems.SOULWOOD_ITEM_PEDESTAL.get(),
            MalumItems.ORNATE_SOULWOOD_ITEM_STAND.get(), MalumItems.ORNATE_SOULWOOD_ITEM_PEDESTAL.get(),
            MalumTags.ItemTags.SOULWOOD_LOGS, MalumTags.ItemTags.SOULWOOD_BOARD_INGREDIENT, MalumTags.ItemTags.SOULWOOD_PLANKS, MalumTags.ItemTags.SOULWOOD_BOARDS, MalumTags.ItemTags.SOULWOOD_STAIRS, MalumTags.ItemTags.SOULWOOD_SLABS,
            MalumItems.SOULWOOD_BOAT.get(),
            MalumItems.SOUL_STAINED_STEEL_NUGGET.get()
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

        var condition = has(woodSet.planksTag);

        shaped(RecipeCategory.MISC, woodSet.boardWall, 6)
                .define('X', woodSet.boardsTag)
                .define('Y', Items.STICK)
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
                .requires(Items.IRON_NUGGET)
                .unlockedBy("has_input", has(input))
                .save(recipeOutput, recipeID);
    }

    private static void planksExchange(RecipeOutput recipeOutput, ItemLike input, ItemLike planks) {
        final ResourceLocation recipeID = getDefaultRecipeId(planks).withSuffix("_from_" + getDefaultRecipeId(input).getPath());
        shaped(RecipeCategory.MISC, planks, 4)
                .define('#', input)
                .pattern(" # ")
                .pattern("# #")
                .pattern(" # ")
                .unlockedBy("has_input", has(input))
                .save(recipeOutput, recipeID);
    }

    private static void rusticExchange(RecipeOutput recipeOutput, ItemLike output, ItemLike input) {
        final ResourceLocation recipeID = getDefaultRecipeId(output).withSuffix("_from_" + getDefaultRecipeId(input).getPath());
        shaped(RecipeCategory.MISC, output, 5)
                .define('#', input)
                .pattern(" # ")
                .pattern("###")
                .pattern(" # ")
                .unlockedBy("has_input", has(input)).save(recipeOutput, recipeID);
    }

    private static void shapelessPlanks(RecipeOutput recipeOutput, ItemLike planks, TagKey<net.minecraft.world.item.Item> input) {
        shapeless(RecipeCategory.MISC, planks, 4)
                .requires(input)
                .group("planks")
                .unlockedBy("has_logs", has(input))
                .save(recipeOutput);
    }

    private static void shapedBoards(RecipeOutput recipeOutput, ItemLike output, TagKey<net.minecraft.world.item.Item> input) {
        shaped(RecipeCategory.MISC, output, 20)
                .define('#', input)
                .pattern(" # ")
                .pattern("###")
                .pattern(" # ")
                .unlockedBy("has_input", has(input))
                .save(recipeOutput);
    }

    private static void shapedPanel(RecipeOutput recipeOutput, ItemLike output, TagKey<net.minecraft.world.item.Item> input) {
        shaped(RecipeCategory.MISC, output, 9)
                .define('#', input)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_input", has(input))
                .save(recipeOutput);
    }

    private static void shapelessWood(RecipeOutput recipeOutput, ItemLike stripped, ItemLike input) {
        shaped(RecipeCategory.MISC, stripped, 3)
                .define('#', input)
                .pattern("##")
                .pattern("##")
                .group("bark")
                .unlockedBy("has_log", has(input))
                .save(recipeOutput);
    }

    private static void shapelessButton(RecipeOutput recipeOutput, ItemLike button, TagKey<net.minecraft.world.item.Item> input) {
        shapeless(RecipeCategory.MISC, button)
                .requires(input)
                .unlockedBy("has_input", has(input))
                .save(recipeOutput);
    }

    private static void shapedDoor(RecipeOutput recipeOutput, ItemLike door, TagKey<net.minecraft.world.item.Item> input) {
        shaped(RecipeCategory.MISC, door, 3)
                .define('#', input)
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_input", has(input))
                .save(recipeOutput);
    }

    private static void shapedFence(RecipeOutput recipeOutput, ItemLike fence, TagKey<net.minecraft.world.item.Item> input) {
        shaped(RecipeCategory.MISC, fence, 3)
                .define('#', Tags.Items.RODS_WOODEN)
                .define('W', input)
                .pattern("W#W")
                .pattern("W#W")
                .unlockedBy("has_input", has(input))
                .save(recipeOutput);
    }

    private static void shapedFenceGate(RecipeOutput recipeOutput, ItemLike fenceGate, TagKey<net.minecraft.world.item.Item> input) {
        shaped(RecipeCategory.MISC, fenceGate)
                .define('#', Tags.Items.RODS_WOODEN)
                .define('W', input)
                .pattern("#W#")
                .pattern("#W#")
                .unlockedBy("has_input", has(input))
                .save(recipeOutput);
    }

    private static void shapedPressurePlate(RecipeOutput recipeOutput, ItemLike pressurePlate, TagKey<net.minecraft.world.item.Item> input) {
        shaped(RecipeCategory.MISC, pressurePlate)
                .define('#', input)
                .pattern("##")
                .unlockedBy("has_input", has(input))
                .save(recipeOutput);
    }

    private static void shapedSlab(RecipeOutput recipeOutput, ItemLike slab, ItemLike input) {
        shaped(RecipeCategory.MISC, slab, 6)
                .define('#', input)
                .pattern("###")
                .unlockedBy("has_input", has(input))
                .save(recipeOutput);
    }

    private static void shapedStairs(RecipeOutput recipeOutput, ItemLike stairs, ItemLike input) {
        shaped(RecipeCategory.MISC, stairs, 4)
                .define('#', input)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .unlockedBy("has_input", has(input))
                .save(recipeOutput);
    }

    private static void shapedTrapdoor(RecipeOutput recipeOutput, ItemLike trapdoor, TagKey<net.minecraft.world.item.Item> input) {
        shaped(RecipeCategory.MISC, trapdoor, 2)
                .define('#', input)
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_input", has(input))
                .save(recipeOutput);
    }

    private static void shapedSign(RecipeOutput recipeOutput, ItemLike sign, TagKey<net.minecraft.world.item.Item> input) {
        shaped(RecipeCategory.MISC, sign, 3)
                .group("sign")
                .define('#', input)
                .define('X', Tags.Items.RODS_WOODEN)
                .pattern("###")
                .pattern("###")
                .pattern(" X ")
                .unlockedBy("has_input", has(input))
                .save(recipeOutput);
    }

    private static void shapedBoat(RecipeOutput recipeOutput, ItemLike boat, TagKey<net.minecraft.world.item.Item> input) {
        shaped(RecipeCategory.MISC, boat)
                .define('#', input)
                .pattern("# #")
                .pattern("###")
                .unlockedBy("has_input", has(input)).save(recipeOutput);
    }

    public record MalumDatagenWoodSet(
            String prefix,

            net.minecraft.world.item.Item log, net.minecraft.world.item.Item wood,
            net.minecraft.world.item.Item strippedLog, net.minecraft.world.item.Item strippedWood,

            net.minecraft.world.item.Item sapFilledLog, net.minecraft.world.item.Item strippedSapFilledLog,

            net.minecraft.world.item.Item boards, net.minecraft.world.item.Item verticalBoards,
            net.minecraft.world.item.Item boardsSlab, net.minecraft.world.item.Item verticalBoardsSlab,
            net.minecraft.world.item.Item boardsStairs, net.minecraft.world.item.Item verticalBoardsStairs,

            net.minecraft.world.item.Item planks, net.minecraft.world.item.Item verticalPlanks, net.minecraft.world.item.Item tiles,
            net.minecraft.world.item.Item rusticPlanks, net.minecraft.world.item.Item verticalRusticPlanks, net.minecraft.world.item.Item rusticTiles,
            net.minecraft.world.item.Item planksSlab, net.minecraft.world.item.Item verticalPlanksSlab, net.minecraft.world.item.Item tilesSlab,
            net.minecraft.world.item.Item rusticPlanksSlab, net.minecraft.world.item.Item verticalRusticPlanksSlab, net.minecraft.world.item.Item rusticTilesSlab,
            net.minecraft.world.item.Item planksStairs, net.minecraft.world.item.Item verticalPlanksStairs, net.minecraft.world.item.Item tilesStairs,
            net.minecraft.world.item.Item rusticPlanksStairs, net.minecraft.world.item.Item verticalRusticPlanksStairs, net.minecraft.world.item.Item rusticTilesStairs,

            net.minecraft.world.item.Item panel, net.minecraft.world.item.Item cutPlanks, net.minecraft.world.item.Item beam,

            net.minecraft.world.item.Item button, net.minecraft.world.item.Item pressurePlate,

            net.minecraft.world.item.Item door, net.minecraft.world.item.Item boltedDoor,
            net.minecraft.world.item.Item boardsDoor, net.minecraft.world.item.Item boltedBoardsDoor,

            net.minecraft.world.item.Item trapdoor, net.minecraft.world.item.Item boltedTrapdoor,
            net.minecraft.world.item.Item boardsTrapdoor, net.minecraft.world.item.Item boltedBoardsTrapdoor,

            net.minecraft.world.item.Item fence, net.minecraft.world.item.Item fenceGate,

            net.minecraft.world.item.Item boardWall,

            net.minecraft.world.item.Item sign, net.minecraft.world.item.Item hangingSign,

            net.minecraft.world.item.Item itemStand, net.minecraft.world.item.Item itemPedestal,
            net.minecraft.world.item.Item decoratedItemStand, net.minecraft.world.item.Item decoratedItemPedestal,

            TagKey<net.minecraft.world.item.Item> logTag, TagKey<net.minecraft.world.item.Item> boardIngredientTag, TagKey<net.minecraft.world.item.Item> planksTag, TagKey<net.minecraft.world.item.Item> boardsTag, TagKey<net.minecraft.world.item.Item> stairsTag, TagKey<net.minecraft.world.item.Item> slabTag,

            net.minecraft.world.item.Item boat,

            net.minecraft.world.item.Item metalNugget
    ) { }
}