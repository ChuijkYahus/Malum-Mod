package com.sammy.malum.datagen.block;

import com.sammy.malum.*;
import com.sammy.malum.datagen.MalumMetallicsDatagen;
import com.sammy.malum.datagen.item.*;
import com.sammy.malum.registry.common.content.*;
import net.minecraft.data.*;
import net.minecraft.resources.*;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.*;
import team.lodestar.lodestone.modules.datagen.BlockStateSmithTypes;
import team.lodestar.lodestone.modules.datagen.ItemModelSmithTypes;
import team.lodestar.lodestone.modules.datagen.providers.block.LodestoneBlockStateSystem;
import team.lodestar.lodestone.modules.datagen.providers.item.LodestoneItemModelSystem;
import team.lodestar.lodestone.modules.datagen.smith.blockstate.BlockStateSystemData;

import javax.annotation.*;
import java.util.*;
import java.util.function.*;

import static com.sammy.malum.MalumMod.*;
import static com.sammy.malum.registry.common.content.block.MalumBlocks.*;

public class MalumBlockStateDatagen extends LodestoneBlockStateSystem {

    public MalumBlockStateDatagen(PackOutput output, ExistingFileHelper exFileHelper, LodestoneItemModelSystem itemModelProvider) {
        super(output, MALUM, exFileHelper, itemModelProvider);
    }

    @Nonnull
    @Override
    public String getName() {
        return "Malum BlockStates";
    }

    @Override
    protected void registerStatesAndModels() {
        Set<Supplier<? extends Block>> blocks = new HashSet<>(BLOCKS.getEntries());

        BlockStateSystemData data = new BlockStateSystemData(this, blocks::remove);


        setTexturePath("banners/");
        MalumBlockStateSmithTypes.SOULWOVEN_BANNER.act(data, MalumContent.BlockSets.SOULWOVEN_BANNER);
        setTexturePath("spirited_glass/");
        BlockStateSmithTypes.FULL_BLOCK.act(data,
                MalumContent.BlockSets.SACRED_SPIRITED_GLASS, MalumContent.BlockSets.WICKED_SPIRITED_GLASS, MalumContent.BlockSets.ARCANE_SPIRITED_GLASS, MalumContent.BlockSets.ELDRITCH_SPIRITED_GLASS,
                MalumContent.BlockSets.AERIAL_SPIRITED_GLASS, MalumContent.BlockSets.AQUEOUS_SPIRITED_GLASS, MalumContent.BlockSets.EARTHEN_SPIRITED_GLASS, MalumContent.BlockSets.INFERNAL_SPIRITED_GLASS,
                MalumContent.BlockSets.NULL_SPIRITED_GLASS);

        setTexturePath("terracotta/");
        BlockStateSmithTypes.GLAZED_TERRACOTTA_BLOCK.act(data,
                MalumContent.BlockSets.SACRED_VARNISHED_TERRACOTTA, MalumContent.BlockSets.WICKED_VARNISHED_TERRACOTTA, MalumContent.BlockSets.ARCANE_VARNISHED_TERRACOTTA, MalumContent.BlockSets.ELDRITCH_VARNISHED_TERRACOTTA,
                MalumContent.BlockSets.AERIAL_VARNISHED_TERRACOTTA, MalumContent.BlockSets.AQUEOUS_VARNISHED_TERRACOTTA, MalumContent.BlockSets.EARTHEN_VARNISHED_TERRACOTTA, MalumContent.BlockSets.INFERNAL_VARNISHED_TERRACOTTA,
                MalumContent.BlockSets.NULL_VARNISHED_TERRACOTTA);

        setTexturePath("arcane_rock/tainted/");
        BlockStateSmithTypes.FULL_BLOCK.act(data,
                MalumContent.BlockSets.TAINTED_ROCK, MalumContent.BlockSets.POLISHED_TAINTED_ROCK, MalumContent.BlockSets.TAINTED_ROCK_BRICKS, MalumContent.BlockSets.TAINTED_ROCK_TILES, MalumContent.BlockSets.TAINTED_ROCK_MOSAIC, MalumContent.BlockSets.CHISELED_TAINTED_ROCK);

        BlockStateSmithTypes.SLAB_BLOCK.act(data,
                MalumContent.BlockSets.TAINTED_ROCK_SLAB, MalumContent.BlockSets.POLISHED_TAINTED_ROCK_SLAB, MalumContent.BlockSets.TAINTED_ROCK_BRICKS_SLAB, MalumContent.BlockSets.TAINTED_ROCK_TILES_SLAB, MalumContent.BlockSets.TAINTED_ROCK_MOSAIC_SLAB);

        BlockStateSmithTypes.STAIRS_BLOCK.act(data,
                MalumContent.BlockSets.TAINTED_ROCK_STAIRS, MalumContent.BlockSets.POLISHED_TAINTED_ROCK_STAIRS, MalumContent.BlockSets.TAINTED_ROCK_BRICKS_STAIRS, MalumContent.BlockSets.TAINTED_ROCK_TILES_STAIRS, MalumContent.BlockSets.TAINTED_ROCK_MOSAIC_STAIRS);

        BlockStateSmithTypes.WALL_BLOCK.act(data,
                MalumContent.BlockSets.TAINTED_ROCK_WALL, MalumContent.BlockSets.POLISHED_TAINTED_ROCK_WALL, MalumContent.BlockSets.TAINTED_ROCK_BRICKS_WALL, MalumContent.BlockSets.TAINTED_ROCK_TILES_WALL, MalumContent.BlockSets.TAINTED_ROCK_MOSAIC_WALL);

        MalumBlockStateSmithTypes.COLUMN.act(data, MalumContent.BlockSets.TAINTED_ROCK_COLUMN);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, models()::cubeBottomTop, MalumContent.BlockSets.TAINTED_ROCK_ALTAR);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, this::cutRockBlockModel, MalumContent.BlockSets.CUT_TAINTED_ROCK);

        BlockStateSmithTypes.BUTTON_BLOCK.act(data, MalumContent.BlockSets.TAINTED_ROCK_BUTTON);
        BlockStateSmithTypes.PRESSURE_PLATE_BLOCK.act(data, MalumContent.BlockSets.TAINTED_ROCK_PRESSURE_PLATE);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, this::rockItemPedestalModel, MalumContent.BlockSets.TAINTED_ROCK_ITEM_PEDESTAL);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::directionalBlock, this::itemStandModel, MalumContent.BlockSets.TAINTED_ROCK_ITEM_STAND);

        setTexturePath("arcane_rock/twisted/");
        BlockStateSmithTypes.FULL_BLOCK.act(data,
                MalumContent.BlockSets.TWISTED_ROCK, MalumContent.BlockSets.POLISHED_TWISTED_ROCK, MalumContent.BlockSets.TWISTED_ROCK_BRICKS, MalumContent.BlockSets.TWISTED_ROCK_TILES, MalumContent.BlockSets.TWISTED_ROCK_MOSAIC, MalumContent.BlockSets.CHISELED_TWISTED_ROCK);

        BlockStateSmithTypes.SLAB_BLOCK.act(data,
                MalumContent.BlockSets.TWISTED_ROCK_SLAB, MalumContent.BlockSets.POLISHED_TWISTED_ROCK_SLAB, MalumContent.BlockSets.TWISTED_ROCK_BRICKS_SLAB, MalumContent.BlockSets.TWISTED_ROCK_TILES_SLAB, MalumContent.BlockSets.TWISTED_ROCK_MOSAIC_SLAB);

        BlockStateSmithTypes.STAIRS_BLOCK.act(data,
                MalumContent.BlockSets.TWISTED_ROCK_STAIRS, MalumContent.BlockSets.POLISHED_TWISTED_ROCK_STAIRS, MalumContent.BlockSets.TWISTED_ROCK_BRICKS_STAIRS, MalumContent.BlockSets.TWISTED_ROCK_TILES_STAIRS, MalumContent.BlockSets.TWISTED_ROCK_MOSAIC_STAIRS);

        BlockStateSmithTypes.WALL_BLOCK.act(data,
                MalumContent.BlockSets.TWISTED_ROCK_WALL, MalumContent.BlockSets.POLISHED_TWISTED_ROCK_WALL, MalumContent.BlockSets.TWISTED_ROCK_BRICKS_WALL, MalumContent.BlockSets.TWISTED_ROCK_TILES_WALL, MalumContent.BlockSets.TWISTED_ROCK_MOSAIC_WALL);

        MalumBlockStateSmithTypes.COLUMN.act(data, MalumContent.BlockSets.TWISTED_ROCK_COLUMN);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, models()::cubeBottomTop, MalumContent.BlockSets.TWISTED_ROCK_ALTAR);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, this::cutRockBlockModel, MalumContent.BlockSets.CUT_TWISTED_ROCK);

        BlockStateSmithTypes.BUTTON_BLOCK.act(data, MalumContent.BlockSets.TWISTED_ROCK_BUTTON);
        BlockStateSmithTypes.PRESSURE_PLATE_BLOCK.act(data, MalumContent.BlockSets.TWISTED_ROCK_PRESSURE_PLATE);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, this::rockItemPedestalModel, MalumContent.BlockSets.TWISTED_ROCK_ITEM_PEDESTAL);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::directionalBlock, this::itemStandModel, MalumContent.BlockSets.TWISTED_ROCK_ITEM_STAND);

        setTexturePath("arcane_rock/dross/");
        BlockStateSmithTypes.FULL_BLOCK.act(data,
                MalumContent.DungeonBlockSets.DROSS_STONE, MalumContent.DungeonBlockSets.POLISHED_DROSS_STONE, MalumContent.DungeonBlockSets.DROSS_STONE_BRICKS, MalumContent.DungeonBlockSets.DROSS_STONE_TILES, MalumContent.DungeonBlockSets.DROSS_STONE_MOSAIC, MalumContent.DungeonBlockSets.CHISELED_DROSS_STONE,
                MalumContent.DungeonBlockSets.GRAY_DROSS_TILES, MalumContent.DungeonBlockSets.DARK_DROSS_TILES);

        BlockStateSmithTypes.SLAB_BLOCK.act(data,
                MalumContent.DungeonBlockSets.DROSS_STONE_SLAB, MalumContent.DungeonBlockSets.POLISHED_DROSS_STONE_SLAB, MalumContent.DungeonBlockSets.DROSS_STONE_BRICKS_SLAB, MalumContent.DungeonBlockSets.DROSS_STONE_TILES_SLAB, MalumContent.DungeonBlockSets.DROSS_STONE_MOSAIC_SLAB,
                MalumContent.DungeonBlockSets.GRAY_DROSS_TILES_SLAB, MalumContent.DungeonBlockSets.DARK_DROSS_TILES_SLAB);

        BlockStateSmithTypes.STAIRS_BLOCK.act(data,
                MalumContent.DungeonBlockSets.DROSS_STONE_STAIRS, MalumContent.DungeonBlockSets.POLISHED_DROSS_STONE_STAIRS, MalumContent.DungeonBlockSets.DROSS_STONE_BRICKS_STAIRS, MalumContent.DungeonBlockSets.DROSS_STONE_TILES_STAIRS, MalumContent.DungeonBlockSets.DROSS_STONE_MOSAIC_STAIRS,
                MalumContent.DungeonBlockSets.GRAY_DROSS_TILES_STAIRS, MalumContent.DungeonBlockSets.DARK_DROSS_TILES_STAIRS);

        BlockStateSmithTypes.WALL_BLOCK.act(data,
                MalumContent.DungeonBlockSets.DROSS_STONE_WALL, MalumContent.DungeonBlockSets.POLISHED_DROSS_STONE_WALL, MalumContent.DungeonBlockSets.DROSS_STONE_BRICKS_WALL, MalumContent.DungeonBlockSets.DROSS_STONE_TILES_WALL, MalumContent.DungeonBlockSets.DROSS_STONE_MOSAIC_WALL,
                MalumContent.DungeonBlockSets.GRAY_DROSS_TILES_WALL, MalumContent.DungeonBlockSets.DARK_DROSS_TILES_WALL);

        MalumBlockStateSmithTypes.COLUMN.act(data, MalumContent.DungeonBlockSets.DROSS_STONE_COLUMN);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, models()::cubeBottomTop, MalumContent.DungeonBlockSets.DROSS_STONE_ALTAR);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, this::cutRockBlockModel, MalumContent.DungeonBlockSets.CUT_DROSS_STONE);

        BlockStateSmithTypes.BUTTON_BLOCK.act(data, MalumContent.DungeonBlockSets.DROSS_STONE_BUTTON);
        BlockStateSmithTypes.PRESSURE_PLATE_BLOCK.act(data, MalumContent.DungeonBlockSets.DROSS_STONE_PRESSURE_PLATE);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, this::rockItemPedestalModel, MalumContent.DungeonBlockSets.DROSS_STONE_ITEM_PEDESTAL);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::directionalBlock, this::itemStandModel, MalumContent.DungeonBlockSets.DROSS_STONE_ITEM_STAND);

        setTexturePath("runewood/");
        BlockStateSmithTypes.FULL_BLOCK.act(data,
                RUNEWOOD_BOARDS, VERTICAL_RUNEWOOD_BOARDS,
                RUNEWOOD_PLANKS, VERTICAL_RUNEWOOD_PLANKS, RUNEWOOD_TILES,
                RUSTIC_RUNEWOOD_PLANKS, VERTICAL_RUSTIC_RUNEWOOD_PLANKS, RUSTIC_RUNEWOOD_TILES,
                RUNEWOOD_PANEL);
        BlockStateSmithTypes.SLAB_BLOCK.act(data,
                RUNEWOOD_BOARDS_SLAB, VERTICAL_RUNEWOOD_BOARDS_SLAB,
                RUNEWOOD_PLANKS_SLAB, VERTICAL_RUNEWOOD_PLANKS_SLAB, RUNEWOOD_TILES_SLAB,
                RUSTIC_RUNEWOOD_PLANKS_SLAB, VERTICAL_RUSTIC_RUNEWOOD_PLANKS_SLAB, RUSTIC_RUNEWOOD_TILES_SLAB);
        BlockStateSmithTypes.STAIRS_BLOCK.act(data,
                RUNEWOOD_BOARDS_STAIRS, VERTICAL_RUNEWOOD_BOARDS_STAIRS,
                RUNEWOOD_PLANKS_STAIRS, VERTICAL_RUNEWOOD_PLANKS_STAIRS, RUNEWOOD_TILES_STAIRS,
                RUSTIC_RUNEWOOD_PLANKS_STAIRS, VERTICAL_RUSTIC_RUNEWOOD_PLANKS_STAIRS, RUSTIC_RUNEWOOD_TILES_STAIRS);

        BlockStateSmithTypes.LOG_BLOCK.act(data, RUNEWOOD_BEAM, RUNEWOOD_LOG, STRIPPED_RUNEWOOD_LOG, SAPPY_RUNEWOOD_LOG, STRIPPED_SAPPY_RUNEWOOD_LOG);
        BlockStateSmithTypes.WOOD_BLOCK.act(data, RUNEWOOD, STRIPPED_RUNEWOOD);
        BlockStateSmithTypes.LEAVES_BLOCK.act(data, RUNEWOOD_LEAVES, AZURE_RUNEWOOD_LEAVES);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.BLOCK_TEXTURE_ITEM, this::simpleBlock, this::hangingLeavesModel,
                HANGING_RUNEWOOD_LEAVES, HANGING_AZURE_RUNEWOOD_LEAVES);

        BlockStateSmithTypes.POTTED_PLANT.act(data, POTTED_RUNEWOOD_SAPLING, POTTED_AZURE_RUNEWOOD_SAPLING);
        BlockStateSmithTypes.CROSS_MODEL_BLOCK.act(data, RUNEWOOD_SAPLING, AZURE_RUNEWOOD_SAPLING);
        BlockStateSmithTypes.BUTTON_BLOCK.act(data, RUNEWOOD_BUTTON);
        BlockStateSmithTypes.PRESSURE_PLATE_BLOCK.act(data, RUNEWOOD_PRESSURE_PLATE);
        BlockStateSmithTypes.DOOR_BLOCK.act(data, RUNEWOOD_DOOR, BOLTED_RUNEWOOD_DOOR, RUNEWOOD_BOARDS_DOOR, BOLTED_RUNEWOOD_BOARDS_DOOR);
        BlockStateSmithTypes.TRAPDOOR_BLOCK.act(data, RUNEWOOD_TRAPDOOR, BOLTED_RUNEWOOD_TRAPDOOR, RUNEWOOD_BOARDS_TRAPDOOR, BOLTED_RUNEWOOD_BOARDS_TRAPDOOR);
        BlockStateSmithTypes.WOODEN_SIGN_BLOCK.act(data, RUNEWOOD_SIGN, RUNEWOOD_WALL_SIGN);
        BlockStateSmithTypes.FENCE_BLOCK.act(data, RUNEWOOD_FENCE);
        BlockStateSmithTypes.FENCE_GATE_BLOCK.act(data, RUNEWOOD_FENCE_GATE);
        BlockStateSmithTypes.WALL_BLOCK.act(data, RUNEWOOD_BOARDS_WALL);

        MalumBlockStateSmithTypes.TOTEM_POLE.act(data, MalumContent.Progression.RUNEWOOD_TOTEM_POLE);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, this::cutWoodBlockModel, CUT_RUNEWOOD_PLANKS);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, this::woodenItemPedestalModel, RUNEWOOD_ITEM_PEDESTAL);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, this::decoratedItemPedestalModel, GILDED_RUNEWOOD_ITEM_PEDESTAL);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::directionalBlock, this::itemStandModel, RUNEWOOD_ITEM_STAND);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::directionalBlock, this::decoratedItemStandModel, GILDED_RUNEWOOD_ITEM_STAND);

        setTexturePath("soulwood/");
        BlockStateSmithTypes.FULL_BLOCK.act(data,
                SOULWOOD_BOARDS, VERTICAL_SOULWOOD_BOARDS,
                SOULWOOD_PLANKS, VERTICAL_SOULWOOD_PLANKS, SOULWOOD_TILES,
                RUSTIC_SOULWOOD_PLANKS, VERTICAL_RUSTIC_SOULWOOD_PLANKS, RUSTIC_SOULWOOD_TILES,
                SOULWOOD_PANEL);
        BlockStateSmithTypes.SLAB_BLOCK.act(data,
                SOULWOOD_BOARDS_SLAB, VERTICAL_SOULWOOD_BOARDS_SLAB,
                SOULWOOD_PLANKS_SLAB, VERTICAL_SOULWOOD_PLANKS_SLAB, SOULWOOD_TILES_SLAB,
                RUSTIC_SOULWOOD_PLANKS_SLAB, VERTICAL_RUSTIC_SOULWOOD_PLANKS_SLAB, RUSTIC_SOULWOOD_TILES_SLAB);
        BlockStateSmithTypes.STAIRS_BLOCK.act(data,
                SOULWOOD_BOARDS_STAIRS, VERTICAL_SOULWOOD_BOARDS_STAIRS,
                SOULWOOD_PLANKS_STAIRS, VERTICAL_SOULWOOD_PLANKS_STAIRS, SOULWOOD_TILES_STAIRS,
                RUSTIC_SOULWOOD_PLANKS_STAIRS, VERTICAL_RUSTIC_SOULWOOD_PLANKS_STAIRS, RUSTIC_SOULWOOD_TILES_STAIRS);

        BlockStateSmithTypes.LOG_BLOCK.act(data, SOULWOOD_BEAM, SOULWOOD_LOG, STRIPPED_SOULWOOD_LOG, SAPPY_SOULWOOD_LOG, STRIPPED_SAPPY_SOULWOOD_LOG);
        BlockStateSmithTypes.WOOD_BLOCK.act(data, SOULWOOD, STRIPPED_SOULWOOD);
        BlockStateSmithTypes.LEAVES_BLOCK.act(data, SOULWOOD_LEAVES);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.BLOCK_TEXTURE_ITEM, this::simpleBlock, this::hangingLeavesModel, HANGING_SOULWOOD_LEAVES);

        BlockStateSmithTypes.POTTED_PLANT.act(data, POTTED_SOULWOOD_SAPLING);
        BlockStateSmithTypes.CROSS_MODEL_BLOCK.act(data, SOULWOOD_SAPLING);
        BlockStateSmithTypes.BUTTON_BLOCK.act(data, SOULWOOD_BUTTON);
        BlockStateSmithTypes.PRESSURE_PLATE_BLOCK.act(data, SOULWOOD_PRESSURE_PLATE);
        BlockStateSmithTypes.DOOR_BLOCK.act(data, SOULWOOD_DOOR, BOLTED_SOULWOOD_DOOR, SOULWOOD_BOARDS_DOOR, BOLTED_SOULWOOD_BOARDS_DOOR);
        BlockStateSmithTypes.TRAPDOOR_BLOCK.act(data, SOULWOOD_TRAPDOOR, BOLTED_SOULWOOD_TRAPDOOR, SOULWOOD_BOARDS_TRAPDOOR, BOLTED_SOULWOOD_BOARDS_TRAPDOOR);
        BlockStateSmithTypes.WOODEN_SIGN_BLOCK.act(data, SOULWOOD_SIGN, SOULWOOD_WALL_SIGN);
        BlockStateSmithTypes.FENCE_BLOCK.act(data, SOULWOOD_FENCE);
        BlockStateSmithTypes.FENCE_GATE_BLOCK.act(data, SOULWOOD_FENCE_GATE);
        BlockStateSmithTypes.WALL_BLOCK.act(data, SOULWOOD_BOARDS_WALL);

        MalumBlockStateSmithTypes.TOTEM_POLE.act(data, MalumContent.Progression.SOULWOOD_TOTEM_POLE);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, this::cutWoodBlockModel, CUT_SOULWOOD_PLANKS);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, this::woodenItemPedestalModel, SOULWOOD_ITEM_PEDESTAL);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, this::decoratedItemPedestalModel, ORNATE_SOULWOOD_ITEM_PEDESTAL);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::directionalBlock, this::itemStandModel, SOULWOOD_ITEM_STAND);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::directionalBlock, this::decoratedItemStandModel, ORNATE_SOULWOOD_ITEM_STAND);

        setTexturePath("ores/");
        BlockStateSmithTypes.FULL_BLOCK.act(data, MalumContent.Materials.SOULSTONE_ORE, MalumContent.Materials.DEEPSLATE_SOULSTONE_ORE, MalumContent.Materials.BRILLIANT_STONE, MalumContent.Materials.BRILLIANT_DEEPSLATE, MalumContent.Materials.NATURAL_QUARTZ_ORE, MalumContent.Materials.DEEPSLATE_QUARTZ_ORE, MalumContent.Materials.CTHONIC_GOLD_ORE, MalumContent.Materials.BLAZING_QUARTZ_ORE);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.GENERATED_ITEM, this::directionalBlock, fromFunction(models()::cross), MalumContent.Materials.NATURAL_QUARTZ, MalumContent.Materials.CTHONIC_GOLD_FRAGMENT, MalumContent.Materials.BLAZING_QUARTZ_CLUSTER);

        setTexturePath("storage_blocks/");
        BlockStateSmithTypes.FULL_BLOCK.act(data,
                MalumContent.Materials.BLOCK_OF_SOUL_STAINED_STEEL, MalumContent.Materials.BLOCK_OF_HALLOWED_GOLD, MalumContent.Materials.BLOCK_OF_MALIGNANT_PEWTER,
                MalumContent.Materials.BLOCK_OF_NULL_SLATE, MalumContent.Materials.BLOCK_OF_VOID_SALTS, MalumContent.Materials.BLOCK_OF_MNEMONIC_FRAGMENT, MalumContent.Materials.BLOCK_OF_MALIGNANT_LEAD, MalumContent.Materials.BLOCK_OF_AURIC_EMBERS);

        MalumBlockStateSmithTypes.STORAGE_BLOCK.act(data, MalumContent.Materials.BLOCK_OF_SOULSTONE, MalumContent.Materials.BLOCK_OF_RAW_SOULSTONE, MalumContent.Materials.BLOCK_OF_BRILLIANCE, MalumContent.Materials.BLOCK_OF_RAW_BRILLIANCE);
        MalumBlockStateSmithTypes.STORAGE_BLOCK.act(data, MalumContent.Materials.BLOCK_OF_BLAZING_QUARTZ, MalumContent.Materials.BLOCK_OF_NATURAL_QUARTZ, MalumContent.Materials.BLOCK_OF_CTHONIC_GOLD);

        MalumBlockStateSmithTypes.STORAGE_BLOCK.act(data, MalumContent.Materials.BLOCK_OF_ROTTING_ESSENCE, MalumContent.Materials.BLOCK_OF_GRIM_TALC, MalumContent.Materials.BLOCK_OF_EERIE_WEAVE, MalumContent.Materials.BLOCK_OF_WARP_FLUX);
        MalumBlockStateSmithTypes.STORAGE_BLOCK.act(data, MalumContent.Materials.BLOCK_OF_WIND_NUCLEI, MalumContent.Materials.BLOCK_OF_PYRE_NUCLEI);

        MalumBlockStateSmithTypes.STORAGE_BLOCK.act(data, MalumContent.Materials.BLOCK_OF_HEX_ASH, MalumContent.Materials.BLOCK_OF_LIVING_FLESH, MalumContent.Materials.BLOCK_OF_ALCHEMICAL_CALX, MalumContent.Materials.BLOCK_OF_ARCANE_CHARCOAL);

        MalumBlockStateSmithTypes.STORAGE_BLOCK.act(data, MalumContent.Materials.BLOCK_OF_EBONY);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, models()::cubeBottomTop, MalumContent.Materials.CRATE_OF_WITCHHAZEL);

        setTexturePath("storage_blocks/metallics");
        MalumMetallicsDatagen.MALUM.addBlockStates(data);

        setTexturePath("flora/");
        BlockStateSmithTypes.CROSS_MODEL_BLOCK.act(data, ItemModelSmithTypes.NO_DATAGEN, MalumContent.Materials.EBONY_SAPLING);
        MalumBlockStateSmithTypes.EBONY.act(data, MalumContent.Materials.EBONY_STALK);


        BlockStateSmithTypes.CROSS_MODEL_BLOCK.act(data, ItemModelSmithTypes.GENERATED_ITEM, MalumContent.Materials.WILD_WITCHHAZEL);
        BlockStateSmithTypes.CROSS_CROP_MODEL_BLOCK.act(data, ItemModelSmithTypes.GENERATED_ITEM, MalumContent.Materials.WITCHHAZEL);


        setTexturePath("blight/");
        MalumBlockStateSmithTypes.COLUMN.act(data, MalumContent.BlockSets.COLUMNAR_BLIGHT);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, models()::cubeBottomTop, MalumContent.BlockSets.BLIGHTED_EARTH);
        MalumBlockStateSmithTypes.COVERING_BLOCK.act(data, MalumContent.BlockSets.BLIGHT);
        MalumBlockStateSmithTypes.BLIGHTED_GROWTH.act(data, MalumContent.BlockSets.BLIGHTED_GUNK);
        MalumBlockStateSmithTypes.CREEPING_BLIGHT.act(data, MalumContent.BlockSets.CLINGING_BLIGHT);
        BlockStateSmithTypes.POTTED_PLANT.act(data, POTTED_BLIGHTPEARL, POTTED_BLIGHTROOT);
        BlockStateSmithTypes.CROSS_MODEL_BLOCK.act(data, MalumContent.BlockSets.BLIGHTPEARL, MalumContent.BlockSets.BLIGHTROOT);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.BLOCK_MODEL_ITEM, this::simpleBlock, this::blightedSoulwoodModel, BLIGHTED_SOULWOOD);

        setTexturePath("blight/scarstone/");
        MalumBlockStateSmithTypes.LARGE_STRANGE_CRYSTAL.act(data, MalumContent.BlockSets.LARGE_STRANGE_CRYSTAL);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.GENERATED_ITEM, this::simpleBlock, models()::crossModel, MalumContent.BlockSets.STRANGE_CRYSTAL);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.BLOCK_MODEL_ITEM, this::simpleBlock, models()::cubeBottomTop, MalumContent.BlockSets.SCARSTONE);
        BlockStateSmithTypes.CROSS_MODEL_BLOCK.act(data, MalumContent.BlockSets.STRANGEROOT);
        BlockStateSmithTypes.POTTED_PLANT.act(data, POTTED_STRANGEROOT);

        setTexturePath("dungeon/flesh/");
        MalumBlockStateSmithTypes.COLUMN.act(data, MalumContent.DungeonBlockSets.COLUMNAR_FLESH);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::directionalBlock, models()::cubeBottomTop, MalumContent.DungeonBlockSets.FLESHBULB);
        MalumBlockStateSmithTypes.WRITHING_FLESH.act(data, MalumContent.DungeonBlockSets.WRITHING_FLESH);

        setTexturePath("dungeon/odd_scriptures/");
        BlockStateSmithTypes.GLAZED_TERRACOTTA_BLOCK.act(data,
                MalumContent.DungeonBlockSets.ODD_SCRIPTURES_I, MalumContent.DungeonBlockSets.ODD_SCRIPTURES_II, MalumContent.DungeonBlockSets.ODD_SCRIPTURES_III, MalumContent.DungeonBlockSets.ODD_SCRIPTURES_IV, MalumContent.DungeonBlockSets.ODD_SCRIPTURES_V, MalumContent.DungeonBlockSets.ODD_SCRIPTURES_VI, MalumContent.DungeonBlockSets.ODD_SCRIPTURES_VII, MalumContent.DungeonBlockSets.ODD_SCRIPTURES_VIII, MalumContent.DungeonBlockSets.ODD_SCRIPTURES_IX);

        setTexturePath("dungeon/effigy/");
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.BLOCK_MODEL_ITEM, this::horizontalBlock, this::meditatingEffigy, MalumContent.DungeonBlockSets.VEILED_EFFIGY, MalumContent.DungeonBlockSets.CORRUPT_EFFIGY, MalumContent.DungeonBlockSets.CRACKED_EFFIGY);

        setTexturePath("waveform_artifice/");
        MalumBlockStateSmithTypes.SPIRIT_DIODE.act(data, MalumContent.Progression.WAVECHARGER, MalumContent.Progression.WAVEBANKER, MalumContent.Progression.WAVEMAKER, MalumContent.Progression.WAVEBREAKER);
        MalumBlockStateSmithTypes.GUST_TECH_BLOCK.act(data, MalumContent.Progression.GUST_IGNITER, MalumContent.Progression.WIND_TUNNEL);

        setTexturePath("ether/");
        itemModelProvider.setTexturePath("ether/");
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.GENERATED_ITEM,
                this::simpleBlock, this::etherModel, MalumContent.BlockSets.ETHER);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, MalumItemModelSmithTypes.GENERATED_OVERLAY_ITEM,
                this::simpleBlock, this::etherModel, MalumContent.BlockSets.IRIDESCENT_ETHER);

        MalumBlockStateSmithTypes.ETHER_CANDLE_BLOCK.act(data,
                MalumContent.BlockSets.ETHER_CANDLE, MalumContent.BlockSets.IRIDESCENT_ETHER_CANDLE);
        MalumBlockStateSmithTypes.ETHER_TORCH_BLOCK.act(data,
                MalumContent.BlockSets.ETHER_TORCH, MalumContent.BlockSets.IRIDESCENT_ETHER_TORCH);
        MalumBlockStateSmithTypes.ETHER_WALL_TORCH_BLOCK.act(data,
                MalumContent.BlockSets.WALL_ETHER_TORCH, MalumContent.BlockSets.IRIDESCENT_WALL_ETHER_TORCH);
        MalumBlockStateSmithTypes.ETHER_BRAZIER_BLOCK.act(data,
                MalumContent.BlockSets.TAINTED_ETHER_BRAZIER, MalumContent.BlockSets.TWISTED_ETHER_BRAZIER, MalumContent.BlockSets.DROSS_ETHER_BRAZIER,
                MalumContent.BlockSets.TAINTED_IRIDESCENT_ETHER_BRAZIER, MalumContent.BlockSets.TWISTED_IRIDESCENT_ETHER_BRAZIER, MalumContent.BlockSets.DROSS_IRIDESCENT_ETHER_BRAZIER);
        MalumBlockStateSmithTypes.ETHER_CRESSET_BLOCK.act(data,
                MalumContent.BlockSets.TAINTED_ETHER_CRESSET, MalumContent.BlockSets.TWISTED_ETHER_CRESSET, MalumContent.BlockSets.DROSS_ETHER_CRESSET,
                MalumContent.BlockSets.TAINTED_IRIDESCENT_ETHER_CRESSET, MalumContent.BlockSets.TWISTED_IRIDESCENT_ETHER_CRESSET, MalumContent.BlockSets.DROSS_IRIDESCENT_ETHER_CRESSET);
        itemModelProvider.setTexturePath("");
        setTexturePath("");

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.BLOCK_MODEL_ITEM, this::simpleBlock, models()::cubeBottomTop,
                MalumContent.Progression.RITE_ANCHOR, MalumContent.Progression.RITE_UNWEAVER);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.BLOCK_MODEL_ITEM, this::directionalBlock, models()::cubeBottomTop,
                MalumContent.Progression.RITE_SPREADER);
        MalumBlockStateSmithTypes.RITE_CHANNEL.act(data,
                MalumContent.Progression.RITE_CHANNEL);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.BLOCK_MODEL_ITEM, this::simpleBlock, models()::predefinedModel,
                MalumContent.Progression.SPIRIT_ALTAR, MalumContent.Progression.SOUL_BRAZIER, MalumContent.Progression.RITUAL_PLINTH,
                MalumContent.DungeonBlockSets.OMINOUS_ALTAR);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.BUILTIN_ENTITY_ITEM, this::simpleBlock, models()::predefinedModel,
                MalumContent.Progression.SPIRIT_JAR);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.BLOCK_MODEL_ITEM, this::horizontalBlock, models()::predefinedModel,
                MalumContent.Progression.WEAVERS_WORKBENCH, MalumContent.Progression.RUNIC_WORKBENCH);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.NO_DATAGEN, this::simpleBlock, models()::predefinedModel,
                MalumContent.Progression.RUNEWOOD_OBELISK, MalumContent.Progression.RUNEWOOD_OBELISK_COMPONENT,
                MalumContent.Progression.BRILLIANT_OBELISK, MalumContent.Progression.BRILLIANT_OBELISK_COMPONENT,
                MalumContent.Progression.ARCANA_PYLON, MalumContent.Progression.ARCANA_PYLON_COMPONENT,
                MalumContent.Progression.SPIRIT_CRUCIBLE, MalumContent.Progression.SPIRIT_CRUCIBLE_COMPONENT, MalumContent.Progression.REPAIR_PYLON,
                MalumContent.DungeonBlockSets.OMINOUS_OBELISK, MalumContent.DungeonBlockSets.OMINOUS_OBELISK_COMPONENT);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.BLOCK_MODEL_ITEM, this::simpleBlock, this::totemBaseModel,
                MalumContent.Progression.RUNEWOOD_TOTEM_BASE, MalumContent.Progression.SOULWOOD_TOTEM_BASE, MalumContent.Progression.WAVEFORM_RUNEWOOD_TOTEM_BASE, MalumContent.Progression.WAVEFORM_SOULWOOD_TOTEM_BASE);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.NO_DATAGEN, this::horizontalBlock, models()::predefinedModel,
                MalumContent.Progression.SPIRIT_CATALYZER, MalumContent.Progression.SPIRIT_CATALYZER_COMPONENT);

        MalumBlockStateSmithTypes.REPAIR_PYLON_COMPONENT.act(data, MalumContent.Progression.REPAIR_PYLON_COMPONENT);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.BLOCK_MODEL_ITEM, this::simpleBlock, models()::predefinedModel,
                MalumContent.Progression.VOID_CONDUIT, MalumContent.Progression.VOID_DEPOT);

        MalumBlockStateSmithTypes.WEEPING_WELL_LAYERED_BLOCK.act(data, MalumContent.Progression.WEEPING_WELL_CENTER, MalumContent.Progression.WEEPING_WELL_SIDE, MalumContent.Progression.WEEPING_WELL_SIDE_MIRROR, MalumContent.Progression.WEEPING_WELL_CORNER);
        MalumBlockStateSmithTypes.WEEPING_WELL_BLOCK.act(data, MalumContent.Progression.WEEPING_WELL_FLAGSTONE);
        MalumBlockStateSmithTypes.WEEPING_WELL_DIRECTIONAL_BLOCK.act(data, MalumContent.Progression.WEEPING_WELL_COLUMN_BASE, MalumContent.Progression.WEEPING_WELL_COLUMN, MalumContent.Progression.WEEPING_WELL_COLUMN_CAP);

        MalumBlockStateSmithTypes.PRIMORDIAL_SOUP.act(data, MalumContent.Progression.PRIMORDIAL_SOUP);

        BlockStateSmithTypes.FULL_BLOCK.act(data, THE_DEVICE, THE_VESSEL);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.NO_DATAGEN, this::simpleBlock, this::cubeModelAirTexture, MalumContent.Progression.SPIRIT_MOTE);
    }

    public ModelFile cubeModelAirTexture(Block block) {
        String name = getBlockName(block);
        return models().cubeAll(name, MalumMod.malumPath("block/air")).texture("particle", getBlockTexture(name));
    }

    public ModelFile cutRockBlockModel(Block block) {
        String name = getBlockName(block);
        int index = name.indexOf("_");
        String substring = name.substring(index + 1);
        ResourceLocation top = getBlockTexture(substring);
        ResourceLocation bottom = getBlockTexture(substring);
        ResourceLocation side = getBlockTexture(name);
        return models().cubeBottomTop(name, side, bottom, top);
    }

    public ModelFile cutWoodBlockModel(Block block) {
        String name = getBlockName(block);
        ResourceLocation top = getBlockTexture(name.replace("cut_", ""));
        ResourceLocation side = getBlockTexture(name);
        return models().cubeBottomTop(name, side, top, top);
    }

    public ModelFile rockItemPedestalModel(Block block) {
        return itemPedestalModel(block, "template_item_pedestal_rock");
    }

    public ModelFile woodenItemPedestalModel(Block block) {
        return itemPedestalModel(block, "template_item_pedestal_wooden");
    }

    public ModelFile decoratedItemPedestalModel(Block block) {
        return itemPedestalModel(block, "template_item_pedestal_wooden_decorated", s -> s.substring(s.indexOf("_") + 1) + "_" + s.split("_")[0]);
    }

    public ModelFile itemPedestalModel(Block block, String template) {
        return itemPedestalModel(block, template, s -> s);
    }

    public ModelFile itemPedestalModel(Block block, String template, Function<String, String> pathFunction) {
        String name = getBlockName(block);
        ResourceLocation parent = malumPath("block/templates/" + template);
        ResourceLocation pedestal = getBlockTexture(pathFunction.apply(name));
        return models().withExistingParent(name, parent).texture("pedestal", pedestal);
    }

    public ModelFile itemStandModel(Block block) {
        return itemStandModel(block, "template_item_stand", s -> s);
    }

    public ModelFile decoratedItemStandModel(Block block) {
        return itemStandModel(block, "template_item_stand_decorated", s -> s.substring(s.indexOf("_") + 1) + "_" + s.split("_")[0]);
    }

    public ModelFile itemStandModel(Block block, String template, Function<String, String> pathFunction) {
        String name = getBlockName(block);
        ResourceLocation parent = malumPath("block/templates/" + template);
        ResourceLocation stand = getBlockTexture(pathFunction.apply(name));
        return models().withExistingParent(name, parent).texture("stand", stand);
    }

    public ModelFile etherModel(Block block) {
        String name = getBlockName(block);
        return models().withExistingParent(name, ResourceLocation.withDefaultNamespace("block/air")).texture("particle", itemModelProvider.getItemTexture("ether"));
    }

    public ModelFile totemBaseModel(Block block) {
        String name = getBlockName(block);
        ResourceLocation texture = getBlockTexture(name);
        return models().withExistingParent(name, malumPath("block/templates/template_totem_base")).texture("totem_base", texture);
    }

    public ModelFile hangingLeavesModel(Block block) {
        String name = getBlockName(block);
        ResourceLocation texture = getBlockTexture(name);
        return models().withExistingParent(name, malumPath("block/templates/template_hanging_leaves")).texture("hanging_leaves", texture).texture("particle", texture);
    }

    public ModelFile blightedSoulwoodModel(Block block) {
        String name = getBlockName(block);
        ResourceLocation side = getBlockTexture(name);
        ResourceLocation bottom = getBlockTexture("blighted_earth_bottom");
        ResourceLocation top = getAbsoluteBlockTexture("soulwood/soulwood_log_top");
        return models().cubeBottomTop(name, side, bottom, top);
    }

    public ModelFile meditatingEffigy(Block block) {
        String name = getBlockName(block);
        ResourceLocation effigy = getBlockTexture(name);
        return models().withExistingParent(name, malumPath("block/templates/dungeon/template_meditating_effigy")).texture("effigy", effigy);
    }
}
