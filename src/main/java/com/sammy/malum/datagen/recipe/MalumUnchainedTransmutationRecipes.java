package com.sammy.malum.datagen.recipe;

import com.mojang.datafixers.util.*;
import com.sammy.malum.*;
import com.sammy.malum.datagen.recipe.builder.*;
import com.sammy.malum.registry.common.MalumContent.Blight;
import com.sammy.malum.registry.common.MalumContent.BlockSets;
import com.sammy.malum.registry.common.MalumContent.Totemancy;
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
            new Pair<>(Totemancy.RUNEWOOD_TOTEM_BASE, Totemancy.SOULWOOD_TOTEM_BASE),
            new Pair<>(BlockSets.RUNEWOOD_SAPLING, BlockSets.SOULWOOD_SAPLING),
            new Pair<>(BlockSets.RUNEWOOD_LEAVES, BlockSets.SOULWOOD_LEAVES),
            new Pair<>(RUNEWOOD_SET.getStrippedLog(), SOULWOOD_SET.getStrippedLog()),
            new Pair<>(RUNEWOOD_SET.getLog(), SOULWOOD_SET.getLog()),
            new Pair<>(RUNEWOOD_SET.getStrippedWood(), SOULWOOD_SET.getStrippedWood()),
            new Pair<>(RUNEWOOD_SET.getWood(), SOULWOOD_SET.getWood()),
            new Pair<>(RUNEWOOD_SET.getStrippedSappyLog(), SOULWOOD_SET.getStrippedSappyLog()),
            new Pair<>(RUNEWOOD_SET.getSappyLog(), SOULWOOD_SET.getSappyLog()),

            new Pair<>(RUNEWOOD_SET.getBoards(), SOULWOOD_SET.getBoards()),
            new Pair<>(RUNEWOOD_SET.getBoardsSlab(), SOULWOOD_SET.getBoardsSlab()),
            new Pair<>(RUNEWOOD_SET.getBoardsStairs(), SOULWOOD_SET.getBoardsStairs()),
            new Pair<>(RUNEWOOD_SET.getVerticalBoards(), SOULWOOD_SET.getVerticalBoards()),
            new Pair<>(RUNEWOOD_SET.getVerticalBoardsSlab(), SOULWOOD_SET.getVerticalBoardsSlab()),
            new Pair<>(RUNEWOOD_SET.getVerticalBoardsStairs(), SOULWOOD_SET.getVerticalBoardsStairs()),

            new Pair<>(RUNEWOOD_SET.getPlanks(), SOULWOOD_SET.getPlanks()),
            new Pair<>(RUNEWOOD_SET.getPlanksSlab(), SOULWOOD_SET.getPlanksSlab()),
            new Pair<>(RUNEWOOD_SET.getPlanksStairs(), SOULWOOD_SET.getPlanksStairs()),

            new Pair<>(RUNEWOOD_SET.getRusticPlanks(), SOULWOOD_SET.getRusticPlanks()),
            new Pair<>(RUNEWOOD_SET.getRusticPlanksSlab(), SOULWOOD_SET.getRusticPlanksSlab()),
            new Pair<>(RUNEWOOD_SET.getRusticPlanksStairs(), SOULWOOD_SET.getRusticPlanksStairs()),

            new Pair<>(RUNEWOOD_SET.getVerticalPlanks(), SOULWOOD_SET.getVerticalPlanks()),
            new Pair<>(RUNEWOOD_SET.getVerticalPlanksSlab(), SOULWOOD_SET.getVerticalPlanksSlab()),
            new Pair<>(RUNEWOOD_SET.getVerticalPlanksStairs(), SOULWOOD_SET.getVerticalPlanksStairs()),

            new Pair<>(RUNEWOOD_SET.getVerticalRusticPlanks(), SOULWOOD_SET.getVerticalRusticPlanks()),
            new Pair<>(RUNEWOOD_SET.getVerticalRusticPlanksSlab(), SOULWOOD_SET.getVerticalRusticPlanksSlab()),
            new Pair<>(RUNEWOOD_SET.getVerticalRusticPlanksStairs(), SOULWOOD_SET.getVerticalRusticPlanksStairs()),

            new Pair<>(RUNEWOOD_SET.getTiles(), SOULWOOD_SET.getTiles()),
            new Pair<>(RUNEWOOD_SET.getTilesSlab(), SOULWOOD_SET.getTilesSlab()),
            new Pair<>(RUNEWOOD_SET.getTilesStairs(), SOULWOOD_SET.getTilesStairs()),

            new Pair<>(RUNEWOOD_SET.getPanel(), SOULWOOD_SET.getPanel()),
            new Pair<>(RUNEWOOD_SET.getCutPlanks(), SOULWOOD_SET.getCutPlanks()),
            new Pair<>(RUNEWOOD_SET.getBeam(), SOULWOOD_SET.getBeam()),

            new Pair<>(RUNEWOOD_SET.getDoor(), SOULWOOD_SET.getDoor()),
            new Pair<>(SOULWOOD_SET.getDoor(), SOULWOOD_SET.getBoltedDoor()),

            new Pair<>(RUNEWOOD_SET.getTrapdoor(), SOULWOOD_SET.getTrapdoor()),
            new Pair<>(RUNEWOOD_SET.getBoltedTrapdoor(), SOULWOOD_SET.getBoltedTrapdoor()),

            new Pair<>(RUNEWOOD_SET.getButton(), SOULWOOD_SET.getButton()),
            new Pair<>(RUNEWOOD_SET.getPressurePlate(), SOULWOOD_SET.getPressurePlate()),

            new Pair<>(RUNEWOOD_SET.getFence(), SOULWOOD_SET.getFence()),
            new Pair<>(RUNEWOOD_SET.getFenceGate(), SOULWOOD_SET.getFenceGate()),
            new Pair<>(RUNEWOOD_SET.getBoardsWall(), SOULWOOD_SET.getBoardsWall()),

            new Pair<>(RUNEWOOD_SET.getItemStand(), SOULWOOD_SET.getItemStand()),
            new Pair<>(RUNEWOOD_SET.getItemPedestal(), SOULWOOD_SET.getItemPedestal()),

            new Pair<>(RUNEWOOD_SET.getSign(), SOULWOOD_SET.getSign()) // Wall sign already handled by this. Is it??? Wire? Huh ? How
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
