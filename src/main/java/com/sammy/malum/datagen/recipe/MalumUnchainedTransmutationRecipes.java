package com.sammy.malum.datagen.recipe;

import com.mojang.datafixers.util.*;
import com.sammy.malum.*;
import com.sammy.malum.datagen.recipe.builder.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.MalumContent.Blight;
import com.sammy.malum.registry.common.MalumContent.BlockSets;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.*;
import team.lodestar.lodestone.modules.toolkit.block.BlockBlockItemHolder;

import java.util.*;

import static com.sammy.malum.registry.common.MalumContent.BlockSets.RUNEWOOD_SET;
import static com.sammy.malum.registry.common.MalumContent.BlockSets.SOULWOOD_SET;

public class MalumUnchainedTransmutationRecipes {

    private static final List<Pair<BlockBlockItemHolder<Block, BlockItem>, BlockBlockItemHolder<Block, BlockItem>>> SOULWOOD_TRANSMUTATIONS = List.of(
            new Pair<>(MalumContent.Totemancy.RUNEWOOD_TOTEM_BASE, MalumContent.Totemancy.SOULWOOD_TOTEM_BASE),
            new Pair<>(BlockSets.RUNEWOOD_SAPLING, BlockSets.SOULWOOD_SAPLING),
            new Pair<>(BlockSets.RUNEWOOD_LEAVES, BlockSets.SOULWOOD_LEAVES),
            new Pair<>(RUNEWOOD_SET.strippedLog, SOULWOOD_SET.strippedLog),
            new Pair<>(RUNEWOOD_SET.strippedWood, SOULWOOD_SET.strippedWood),
            new Pair<>(RUNEWOOD_SET.strippedSappyLog, SOULWOOD_SET.strippedSappyLog),
            new Pair<>(RUNEWOOD_SET.log, SOULWOOD_SET.log),
            new Pair<>(RUNEWOOD_SET.wood, SOULWOOD_SET.wood),
            new Pair<>(RUNEWOOD_SET.sappyLog, SOULWOOD_SET.sappyLog),

            new Pair<>(RUNEWOOD_SET.boards.block, SOULWOOD_SET.boards.block),
            new Pair<>(RUNEWOOD_SET.boards.stairs, SOULWOOD_SET.boards.stairs),
            new Pair<>(RUNEWOOD_SET.boards.slab, SOULWOOD_SET.boards.slab),
            new Pair<>(RUNEWOOD_SET.boards.wall, SOULWOOD_SET.boards.wall),

            new Pair<>(RUNEWOOD_SET.verticalBoards.block, SOULWOOD_SET.verticalBoards.block),
            new Pair<>(RUNEWOOD_SET.verticalBoards.stairs, SOULWOOD_SET.verticalBoards.stairs),
            new Pair<>(RUNEWOOD_SET.verticalBoards.slab, SOULWOOD_SET.verticalBoards.slab),
            new Pair<>(RUNEWOOD_SET.verticalBoards.wall, SOULWOOD_SET.verticalBoards.wall),

            new Pair<>(RUNEWOOD_SET.blocks.block, SOULWOOD_SET.blocks.block),
            new Pair<>(RUNEWOOD_SET.blocks.stairs, SOULWOOD_SET.blocks.stairs),
            new Pair<>(RUNEWOOD_SET.blocks.slab, SOULWOOD_SET.blocks.slab),
            new Pair<>(RUNEWOOD_SET.blocks.wall, SOULWOOD_SET.blocks.wall),

            new Pair<>(RUNEWOOD_SET.planks.block, SOULWOOD_SET.planks.block),
            new Pair<>(RUNEWOOD_SET.planks.stairs, SOULWOOD_SET.planks.stairs),
            new Pair<>(RUNEWOOD_SET.planks.slab, SOULWOOD_SET.planks.slab),

            new Pair<>(RUNEWOOD_SET.verticalPlanks.block, SOULWOOD_SET.verticalPlanks.block),
            new Pair<>(RUNEWOOD_SET.verticalPlanks.stairs, SOULWOOD_SET.verticalPlanks.stairs),
            new Pair<>(RUNEWOOD_SET.verticalPlanks.slab, SOULWOOD_SET.verticalPlanks.slab),

            new Pair<>(RUNEWOOD_SET.tiles.block, SOULWOOD_SET.tiles.block),
            new Pair<>(RUNEWOOD_SET.tiles.stairs, SOULWOOD_SET.tiles.stairs),
            new Pair<>(RUNEWOOD_SET.tiles.slab, SOULWOOD_SET.tiles.slab),

            new Pair<>(RUNEWOOD_SET.steps, SOULWOOD_SET.steps),
            new Pair<>(RUNEWOOD_SET.beam, SOULWOOD_SET.beam),

            new Pair<>(RUNEWOOD_SET.door, SOULWOOD_SET.door),
            new Pair<>(RUNEWOOD_SET.heavyDoor, SOULWOOD_SET.heavyDoor),

            new Pair<>(RUNEWOOD_SET.trapdoor, SOULWOOD_SET.trapdoor),
            new Pair<>(RUNEWOOD_SET.heavyTrapdoor, SOULWOOD_SET.heavyTrapdoor),

            new Pair<>(RUNEWOOD_SET.button, SOULWOOD_SET.button),
            new Pair<>(RUNEWOOD_SET.pressurePlate, SOULWOOD_SET.pressurePlate),

            new Pair<>(RUNEWOOD_SET.fence, SOULWOOD_SET.fence),
            new Pair<>(RUNEWOOD_SET.fenceGate, SOULWOOD_SET.fenceGate),

            new Pair<>(RUNEWOOD_SET.itemStand, SOULWOOD_SET.itemStand),
            new Pair<>(RUNEWOOD_SET.itemPedestal, SOULWOOD_SET.itemPedestal),

            new Pair<>(RUNEWOOD_SET.sign, SOULWOOD_SET.sign) // Wall sign already handled by this. Is it??? Wire? Huh ? How
    );

    protected static void buildRecipes(RecipeOutput recipeOutput) {
        for (var transmutation : SOULWOOD_TRANSMUTATIONS) {
            var input = transmutation.getFirst();
            var output = transmutation.getSecond();
            var id = output.getBlockHolder().getId().withPath(p -> "soulwood/" + p).withSuffix("_soulwood_transmutation");
            new UnchainedTransmutationRecipeBuilder(input.get(), output.get())
                    .group("soulwood")
                    .save(recipeOutput, id);
        }

        createUnchainedRecipeTree(recipeOutput, "dirt", Blocks.DIRT, Blocks.ROOTED_DIRT, Blocks.GRASS_BLOCK, Blocks.MOSS_BLOCK, Blight.BLIGHTED_EARTH.get());

        createUnchainedRecipeTree(recipeOutput, "stone", Blocks.STONE, Blocks.COBBLESTONE, Blocks.GRAVEL, Blocks.SAND, Blight.BLIGHTED_EARTH.get());

        createUnchainedRecipeTree(recipeOutput, "basalt", Blocks.BASALT, Blocks.MAGMA_BLOCK, Blocks.NETHERRACK, Blocks.SOUL_SAND, Blight.BLIGHTED_EARTH.get());

        createUnchainedRecipeTree(recipeOutput, "mud", Blocks.MUD, Blocks.CLAY, Blocks.PRISMARINE, Blocks.ICE, Blight.BLIGHTED_EARTH.get());

        createUnchainedRecipeTree(recipeOutput, "packed_mud", Blocks.PACKED_MUD, Blocks.DRIPSTONE_BLOCK, Blocks.GRANITE, Blocks.GRAVEL);

        createUnchainedRecipeTree(recipeOutput, "snow", Blocks.SNOW_BLOCK, Blocks.CALCITE, Blocks.DIORITE, Blocks.GRAVEL);

        createUnchainedRecipeTree(recipeOutput, "polished_basalt", Blocks.DEEPSLATE, Blocks.TUFF, Blocks.ANDESITE, Blocks.GRAVEL);
    }

    public static void createUnchainedRecipeTree(RecipeOutput recipeOutput, String group, Block... blocks) {
        for (int i = 0; i < blocks.length-1; i++) {
            Block input = blocks[i];
            Block output = blocks[i+1];
            new UnchainedTransmutationRecipeBuilder(input, output)
                    .group(group)
                    .save(recipeOutput, MalumMod.malumPath(BuiltInRegistries.BLOCK.getKey(output).getPath() + "_from_" + BuiltInRegistries.BLOCK.getKey(input).getPath()));
        }
    }
}
