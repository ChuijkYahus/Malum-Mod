package com.sammy.malum.datagen.recipe;

import com.mojang.datafixers.util.*;
import com.sammy.malum.*;
import com.sammy.malum.datagen.recipe.builder.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.*;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.*;

import static com.sammy.malum.registry.common.block.MalumBlocks.*;

public class MalumUnchainedTransmutationRecipes {

    private static final List<Pair<DeferredHolder<Block, Block>, DeferredHolder<Block, Block>>> SOULWOOD_TRANSMUTATIONS = List.of(
            new Pair<>(RUNEWOOD_TOTEM_BASE, SOULWOOD_TOTEM_BASE),
            new Pair<>(RUNEWOOD_SAPLING, SOULWOOD_SAPLING),
            new Pair<>(RUNEWOOD_LEAVES, SOULWOOD_LEAVES),
            new Pair<>(STRIPPED_RUNEWOOD_LOG, STRIPPED_SOULWOOD_LOG),
            new Pair<>(RUNEWOOD_LOG, SOULWOOD_LOG),
            new Pair<>(STRIPPED_RUNEWOOD, STRIPPED_SOULWOOD),
            new Pair<>(RUNEWOOD, SOULWOOD),
            new Pair<>(STRIPPED_SAPPY_RUNEWOOD_LOG, STRIPPED_SAPPY_SOULWOOD_LOG),
            new Pair<>(SAPPY_RUNEWOOD_LOG, SAPPY_SOULWOOD_LOG),
            new Pair<>(RUNEWOOD_BOARDS, SOULWOOD_BOARDS),
            new Pair<>(RUNEWOOD_BOARDS_SLAB, SOULWOOD_BOARDS_SLAB),
            new Pair<>(RUNEWOOD_BOARDS_STAIRS, SOULWOOD_BOARDS_STAIRS),
            new Pair<>(VERTICAL_RUNEWOOD_BOARDS, VERTICAL_SOULWOOD_BOARDS),
            new Pair<>(VERTICAL_RUNEWOOD_BOARDS_SLAB, VERTICAL_SOULWOOD_BOARDS_SLAB),
            new Pair<>(VERTICAL_RUNEWOOD_BOARDS_STAIRS, VERTICAL_SOULWOOD_BOARDS_STAIRS),
            new Pair<>(RUNEWOOD_PLANKS, SOULWOOD_PLANKS),
            new Pair<>(RUNEWOOD_PLANKS_SLAB, SOULWOOD_PLANKS_SLAB),
            new Pair<>(RUNEWOOD_PLANKS_STAIRS, SOULWOOD_PLANKS_STAIRS),
            new Pair<>(RUSTIC_RUNEWOOD_PLANKS, RUSTIC_SOULWOOD_PLANKS),
            new Pair<>(RUSTIC_RUNEWOOD_PLANKS_SLAB, RUSTIC_SOULWOOD_PLANKS_SLAB),
            new Pair<>(RUSTIC_RUNEWOOD_PLANKS_STAIRS, RUSTIC_SOULWOOD_PLANKS_STAIRS),
            new Pair<>(VERTICAL_RUNEWOOD_PLANKS, VERTICAL_SOULWOOD_PLANKS),
            new Pair<>(VERTICAL_RUNEWOOD_PLANKS_SLAB, VERTICAL_SOULWOOD_PLANKS_SLAB),
            new Pair<>(VERTICAL_RUNEWOOD_PLANKS_STAIRS, VERTICAL_SOULWOOD_PLANKS_STAIRS),
            new Pair<>(VERTICAL_RUSTIC_RUNEWOOD_PLANKS, VERTICAL_RUSTIC_SOULWOOD_PLANKS),
            new Pair<>(VERTICAL_RUSTIC_RUNEWOOD_PLANKS_SLAB, VERTICAL_RUSTIC_SOULWOOD_PLANKS_SLAB),
            new Pair<>(VERTICAL_RUSTIC_RUNEWOOD_PLANKS_STAIRS, VERTICAL_RUSTIC_SOULWOOD_PLANKS_STAIRS),
            new Pair<>(RUNEWOOD_TILES, SOULWOOD_TILES),
            new Pair<>(RUNEWOOD_TILES_SLAB, SOULWOOD_TILES_SLAB),
            new Pair<>(RUNEWOOD_TILES_STAIRS, SOULWOOD_TILES_STAIRS),
            new Pair<>(RUNEWOOD_PANEL, SOULWOOD_PANEL),
            new Pair<>(CUT_RUNEWOOD_PLANKS, CUT_SOULWOOD_PLANKS),
            new Pair<>(RUNEWOOD_BEAM, SOULWOOD_BEAM),
            new Pair<>(RUNEWOOD_DOOR, SOULWOOD_DOOR),
            new Pair<>(SOULWOOD_DOOR, BOLTED_SOULWOOD_DOOR),
            new Pair<>(RUNEWOOD_TRAPDOOR, SOULWOOD_TRAPDOOR),
            new Pair<>(BOLTED_RUNEWOOD_TRAPDOOR, BOLTED_SOULWOOD_TRAPDOOR),
            new Pair<>(RUNEWOOD_BUTTON, SOULWOOD_BUTTON),
            new Pair<>(RUNEWOOD_PRESSURE_PLATE, SOULWOOD_PRESSURE_PLATE),
            new Pair<>(RUNEWOOD_FENCE, SOULWOOD_FENCE),
            new Pair<>(RUNEWOOD_FENCE_GATE, SOULWOOD_FENCE_GATE),
            new Pair<>(RUNEWOOD_BOARDS_WALL, SOULWOOD_BOARDS_WALL),
            new Pair<>(RUNEWOOD_ITEM_STAND, SOULWOOD_ITEM_STAND),
            new Pair<>(RUNEWOOD_ITEM_PEDESTAL, SOULWOOD_ITEM_PEDESTAL),
            new Pair<>(RUNEWOOD_SIGN, SOULWOOD_SIGN) // Wall sign already handled by this. Is it??? Wire? Huh ? How
    );

    protected static void buildRecipes(RecipeOutput recipeOutput) {
        for (var transmutation : SOULWOOD_TRANSMUTATIONS) {
            var input = transmutation.getFirst();
            var output = transmutation.getSecond();
            var id = output.getId().withPath(p -> "soulwood/" + p).withSuffix("_soulwood_transmutation");
            new UnchainedTransmutationRecipeBuilder(input.get(), output.get())
                    .group("soulwood")
                    .save(recipeOutput, id);
        }

        createUnchainedRecipeTree(recipeOutput, "dirt", Blocks.DIRT, Blocks.ROOTED_DIRT, Blocks.GRASS_BLOCK, Blocks.MOSS_BLOCK, BLIGHTED_EARTH.get());

        createUnchainedRecipeTree(recipeOutput, "stone", Blocks.STONE, Blocks.COBBLESTONE, Blocks.GRAVEL, Blocks.SAND, BLIGHTED_EARTH.get());

        createUnchainedRecipeTree(recipeOutput, "basalt", Blocks.BASALT, Blocks.MAGMA_BLOCK, Blocks.NETHERRACK, Blocks.SOUL_SAND, BLIGHTED_EARTH.get());

        createUnchainedRecipeTree(recipeOutput, "mud", Blocks.MUD, Blocks.CLAY, Blocks.PRISMARINE, Blocks.ICE, BLIGHTED_EARTH.get());

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
