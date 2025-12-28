package com.sammy.malum.datagen.block;

import com.sammy.malum.*;
import com.sammy.malum.datagen.item.*;
import net.minecraft.data.*;
import net.minecraft.resources.*;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.*;
import team.lodestar.lodestone.systems.datagen.*;
import team.lodestar.lodestone.systems.datagen.providers.*;
import team.lodestar.lodestone.systems.datagen.statesmith.*;

import javax.annotation.*;
import java.util.*;
import java.util.function.*;

import static com.sammy.malum.MalumMod.*;
import static com.sammy.malum.registry.common.block.MalumBlocks.*;

public class MalumBlockStateDatagen extends LodestoneBlockStateProvider {

    public MalumBlockStateDatagen(PackOutput output, ExistingFileHelper exFileHelper, LodestoneItemModelProvider itemModelProvider) {
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

        AbstractBlockStateSmith.StateSmithData data = new AbstractBlockStateSmith.StateSmithData(this, blocks::remove);

        setTexturePath("banners/");
        MalumBlockStateSmithTypes.SOULWOVEN_BANNER.act(data, SOULWOVEN_BANNER);
        setTexturePath("spirited_glass/");
        BlockStateSmithTypes.FULL_BLOCK.act(data,
                SACRED_SPIRITED_GLASS, WICKED_SPIRITED_GLASS, ARCANE_SPIRITED_GLASS, ELDRITCH_SPIRITED_GLASS,
                AERIAL_SPIRITED_GLASS, AQUEOUS_SPIRITED_GLASS, EARTHEN_SPIRITED_GLASS, INFERNAL_SPIRITED_GLASS,
                NULL_SPIRITED_GLASS);

        setTexturePath("terracotta/");
        BlockStateSmithTypes.HORIZONTAL_BLOCK.act(data,
                SACRED_VARNISHED_TERRACOTTA, WICKED_VARNISHED_TERRACOTTA, ARCANE_VARNISHED_TERRACOTTA, ELDRITCH_VARNISHED_TERRACOTTA,
                AERIAL_VARNISHED_TERRACOTTA, AQUEOUS_VARNISHED_TERRACOTTA, EARTHEN_VARNISHED_TERRACOTTA, INFERNAL_VARNISHED_TERRACOTTA,
                NULL_VARNISHED_TERRACOTTA);

        setTexturePath("arcane_rock/tainted/");
        BlockStateSmithTypes.FULL_BLOCK.act(data,
                TAINTED_ROCK, POLISHED_TAINTED_ROCK, TAINTED_ROCK_BRICKS, TAINTED_ROCK_TILES, TAINTED_ROCK_MOSAIC, CHISELED_TAINTED_ROCK);

        BlockStateSmithTypes.SLAB_BLOCK.act(data,
                TAINTED_ROCK_SLAB, POLISHED_TAINTED_ROCK_SLAB, TAINTED_ROCK_BRICKS_SLAB, TAINTED_ROCK_TILES_SLAB, TAINTED_ROCK_MOSAIC_SLAB);

        BlockStateSmithTypes.STAIRS_BLOCK.act(data,
                TAINTED_ROCK_STAIRS, POLISHED_TAINTED_ROCK_STAIRS, TAINTED_ROCK_BRICKS_STAIRS, TAINTED_ROCK_TILES_STAIRS, TAINTED_ROCK_MOSAIC_STAIRS);

        BlockStateSmithTypes.WALL_BLOCK.act(data,
                TAINTED_ROCK_WALL, POLISHED_TAINTED_ROCK_WALL, TAINTED_ROCK_BRICKS_WALL, TAINTED_ROCK_TILES_WALL, TAINTED_ROCK_MOSAIC_WALL);

        MalumBlockStateSmithTypes.COLUMN.act(data, TAINTED_ROCK_COLUMN);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, this::cubeBottomTop, TAINTED_ROCK_ALTAR);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, this::cutRockBlockModel, CUT_TAINTED_ROCK);

        BlockStateSmithTypes.BUTTON_BLOCK.act(data, TAINTED_ROCK_BUTTON);
        BlockStateSmithTypes.PRESSURE_PLATE_BLOCK.act(data, TAINTED_ROCK_PRESSURE_PLATE);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, this::rockItemPedestalModel, TAINTED_ROCK_ITEM_PEDESTAL);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::directionalBlock, this::itemStandModel, TAINTED_ROCK_ITEM_STAND);

        setTexturePath("arcane_rock/twisted/");
        BlockStateSmithTypes.FULL_BLOCK.act(data,
                TWISTED_ROCK, POLISHED_TWISTED_ROCK, TWISTED_ROCK_BRICKS, TWISTED_ROCK_TILES, TWISTED_ROCK_MOSAIC, CHISELED_TWISTED_ROCK);

        BlockStateSmithTypes.SLAB_BLOCK.act(data,
                TWISTED_ROCK_SLAB, POLISHED_TWISTED_ROCK_SLAB, TWISTED_ROCK_BRICKS_SLAB, TWISTED_ROCK_TILES_SLAB, TWISTED_ROCK_MOSAIC_SLAB);

        BlockStateSmithTypes.STAIRS_BLOCK.act(data,
                TWISTED_ROCK_STAIRS, POLISHED_TWISTED_ROCK_STAIRS, TWISTED_ROCK_BRICKS_STAIRS, TWISTED_ROCK_TILES_STAIRS, TWISTED_ROCK_MOSAIC_STAIRS);

        BlockStateSmithTypes.WALL_BLOCK.act(data,
                TWISTED_ROCK_WALL, POLISHED_TWISTED_ROCK_WALL, TWISTED_ROCK_BRICKS_WALL, TWISTED_ROCK_TILES_WALL, TWISTED_ROCK_MOSAIC_WALL);

        MalumBlockStateSmithTypes.COLUMN.act(data, TWISTED_ROCK_COLUMN);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, this::cubeBottomTop, TWISTED_ROCK_ALTAR);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, this::cutRockBlockModel, CUT_TWISTED_ROCK);

        BlockStateSmithTypes.BUTTON_BLOCK.act(data, TWISTED_ROCK_BUTTON);
        BlockStateSmithTypes.PRESSURE_PLATE_BLOCK.act(data, TWISTED_ROCK_PRESSURE_PLATE);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, this::rockItemPedestalModel, TWISTED_ROCK_ITEM_PEDESTAL);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::directionalBlock, this::itemStandModel, TWISTED_ROCK_ITEM_STAND);

        setTexturePath("arcane_rock/dross/");
        BlockStateSmithTypes.FULL_BLOCK.act(data,
                DROSS_STONE, POLISHED_DROSS_STONE, DROSS_STONE_BRICKS, DROSS_STONE_TILES, DROSS_STONE_MOSAIC, CHISELED_DROSS_STONE,
                GRAY_DROSS_TILES, DARK_DROSS_TILES);

        BlockStateSmithTypes.SLAB_BLOCK.act(data,
                DROSS_STONE_SLAB, POLISHED_DROSS_STONE_SLAB, DROSS_STONE_BRICKS_SLAB, DROSS_STONE_TILES_SLAB, DROSS_STONE_MOSAIC_SLAB,
                GRAY_DROSS_TILES_SLAB, DARK_DROSS_TILES_SLAB);

        BlockStateSmithTypes.STAIRS_BLOCK.act(data,
                DROSS_STONE_STAIRS, POLISHED_DROSS_STONE_STAIRS, DROSS_STONE_BRICKS_STAIRS, DROSS_STONE_TILES_STAIRS, DROSS_STONE_MOSAIC_STAIRS,
                GRAY_DROSS_TILES_STAIRS, DARK_DROSS_TILES_STAIRS);

        BlockStateSmithTypes.WALL_BLOCK.act(data,
                DROSS_STONE_WALL, POLISHED_DROSS_STONE_WALL, DROSS_STONE_BRICKS_WALL, DROSS_STONE_TILES_WALL, DROSS_STONE_MOSAIC_WALL,
                GRAY_DROSS_TILES_WALL, DARK_DROSS_TILES_WALL);

        MalumBlockStateSmithTypes.COLUMN.act(data, DROSS_STONE_COLUMN);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, this::cubeBottomTop, DROSS_STONE_ALTAR);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, this::cutRockBlockModel, CUT_DROSS_STONE);

        BlockStateSmithTypes.BUTTON_BLOCK.act(data, DROSS_STONE_BUTTON);
        BlockStateSmithTypes.PRESSURE_PLATE_BLOCK.act(data, DROSS_STONE_PRESSURE_PLATE);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, this::rockItemPedestalModel, DROSS_STONE_ITEM_PEDESTAL);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::directionalBlock, this::itemStandModel, DROSS_STONE_ITEM_STAND);

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

        BlockStateSmithTypes.LOG_BLOCK.act(data, RUNEWOOD_BEAM, RUNEWOOD_LOG, STRIPPED_RUNEWOOD_LOG, EXPOSED_RUNEWOOD_LOG, REVEALED_RUNEWOOD_LOG);
        BlockStateSmithTypes.WOOD_BLOCK.act(data, RUNEWOOD, STRIPPED_RUNEWOOD);
        BlockStateSmithTypes.LEAVES_BLOCK.act(data, RUNEWOOD_LEAVES, AZURE_RUNEWOOD_LEAVES);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.BLOCK_TEXTURE_ITEM, this::simpleBlock, this::hangingLeavesModel,
                HANGING_RUNEWOOD_LEAVES, HANGING_AZURE_RUNEWOOD_LEAVES);

        MalumBlockStateSmithTypes.POTTED_PLANT.act(data, POTTED_RUNEWOOD_SAPLING, POTTED_AZURE_RUNEWOOD_SAPLING);
        BlockStateSmithTypes.CROSS_MODEL_BLOCK.act(data, RUNEWOOD_SAPLING, AZURE_RUNEWOOD_SAPLING);
        BlockStateSmithTypes.BUTTON_BLOCK.act(data, RUNEWOOD_BUTTON);
        BlockStateSmithTypes.PRESSURE_PLATE_BLOCK.act(data, RUNEWOOD_PRESSURE_PLATE);
        BlockStateSmithTypes.DOOR_BLOCK.act(data, RUNEWOOD_DOOR, BOLTED_RUNEWOOD_DOOR, RUNEWOOD_BOARDS_DOOR, BOLTED_RUNEWOOD_BOARDS_DOOR);
        BlockStateSmithTypes.TRAPDOOR_BLOCK.act(data, RUNEWOOD_TRAPDOOR, BOLTED_RUNEWOOD_TRAPDOOR, RUNEWOOD_BOARDS_TRAPDOOR, BOLTED_RUNEWOOD_BOARDS_TRAPDOOR);
        BlockStateSmithTypes.WOODEN_SIGN_BLOCK.act(data, RUNEWOOD_SIGN, RUNEWOOD_WALL_SIGN);
        BlockStateSmithTypes.FENCE_BLOCK.act(data, RUNEWOOD_FENCE);
        BlockStateSmithTypes.FENCE_GATE_BLOCK.act(data, RUNEWOOD_FENCE_GATE);
        BlockStateSmithTypes.WALL_BLOCK.act(data, RUNEWOOD_BOARDS_WALL);

        MalumBlockStateSmithTypes.TOTEM_POLE.act(data, RUNEWOOD_TOTEM_POLE);
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

        BlockStateSmithTypes.LOG_BLOCK.act(data, SOULWOOD_BEAM, SOULWOOD_LOG, STRIPPED_SOULWOOD_LOG, EXPOSED_SOULWOOD_LOG, REVEALED_SOULWOOD_LOG);
        BlockStateSmithTypes.WOOD_BLOCK.act(data, SOULWOOD, STRIPPED_SOULWOOD);
        BlockStateSmithTypes.LEAVES_BLOCK.act(data, SOULWOOD_LEAVES);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.BLOCK_TEXTURE_ITEM, this::simpleBlock, this::hangingLeavesModel, HANGING_SOULWOOD_LEAVES);

        MalumBlockStateSmithTypes.POTTED_PLANT.act(data, POTTED_SOULWOOD_SAPLING);
        BlockStateSmithTypes.CROSS_MODEL_BLOCK.act(data, SOULWOOD_SAPLING);
        BlockStateSmithTypes.BUTTON_BLOCK.act(data, SOULWOOD_BUTTON);
        BlockStateSmithTypes.PRESSURE_PLATE_BLOCK.act(data, SOULWOOD_PRESSURE_PLATE);
        BlockStateSmithTypes.DOOR_BLOCK.act(data, SOULWOOD_DOOR, BOLTED_SOULWOOD_DOOR, SOULWOOD_BOARDS_DOOR, BOLTED_SOULWOOD_BOARDS_DOOR);
        BlockStateSmithTypes.TRAPDOOR_BLOCK.act(data, SOULWOOD_TRAPDOOR, BOLTED_SOULWOOD_TRAPDOOR, SOULWOOD_BOARDS_TRAPDOOR, BOLTED_SOULWOOD_BOARDS_TRAPDOOR);
        BlockStateSmithTypes.WOODEN_SIGN_BLOCK.act(data, SOULWOOD_SIGN, SOULWOOD_WALL_SIGN);
        BlockStateSmithTypes.FENCE_BLOCK.act(data, SOULWOOD_FENCE);
        BlockStateSmithTypes.FENCE_GATE_BLOCK.act(data, SOULWOOD_FENCE_GATE);
        BlockStateSmithTypes.WALL_BLOCK.act(data, SOULWOOD_BOARDS_WALL);

        MalumBlockStateSmithTypes.TOTEM_POLE.act(data, SOULWOOD_TOTEM_POLE);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, this::cutWoodBlockModel, CUT_SOULWOOD_PLANKS);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, this::woodenItemPedestalModel, SOULWOOD_ITEM_PEDESTAL);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, this::decoratedItemPedestalModel, ORNATE_SOULWOOD_ITEM_PEDESTAL);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::directionalBlock, this::itemStandModel, SOULWOOD_ITEM_STAND);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::directionalBlock, this::decoratedItemStandModel, ORNATE_SOULWOOD_ITEM_STAND);

        setTexturePath("ores/");
        BlockStateSmithTypes.FULL_BLOCK.act(data, CTHONIC_GOLD_ORE, NATURAL_QUARTZ_ORE, DEEPSLATE_QUARTZ_ORE, SOULSTONE_ORE, DEEPSLATE_SOULSTONE_ORE);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, this::layeredBlockModel, BLAZING_QUARTZ_ORE, BRILLIANT_STONE, BRILLIANT_DEEPSLATE);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.GENERATED_ITEM, this::directionalBlock, fromFunction(models()::cross), NATURAL_QUARTZ_CLUSTER, CTHONIC_GOLD_CLUSTER, BLAZING_QUARTZ_CLUSTER);

        setTexturePath("storage_blocks/");
        BlockStateSmithTypes.FULL_BLOCK.act(data,
                BLOCK_OF_RAW_SOULSTONE, BLOCK_OF_SOULSTONE, BLOCK_OF_CTHONIC_GOLD, BLOCK_OF_BRILLIANCE,
                BLOCK_OF_SOUL_STAINED_STEEL, BLOCK_OF_HALLOWED_GOLD, BLOCK_OF_MALIGNANT_PEWTER,
                BLOCK_OF_NULL_SLATE, BLOCK_OF_VOID_SALTS, BLOCK_OF_MNEMONIC_FRAGMENT, BLOCK_OF_MALIGNANT_LEAD,
                BLOCK_OF_BLAZING_QUARTZ,
                BLOCK_OF_AURIC_EMBERS);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::directionalBlock, this::cubeBottomTop,
                BLOCK_OF_GRIM_TALC, BLOCK_OF_ROTTING_ESSENCE, BLOCK_OF_EERIE_WEAVE, BLOCK_OF_WARP_FLUX);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::directionalBlock, this::cubeBottomTop,
                BLOCK_OF_WIND_NUCLEI, BLOCK_OF_PYRE_NUCLEI);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::directionalBlock, this::cubeBottomTop,
                BLOCK_OF_HEX_ASH, BLOCK_OF_LIVING_FLESH, BLOCK_OF_ALCHEMICAL_CALX, BLOCK_OF_ARCANE_CHARCOAL);

        setTexturePath("blight/");
        MalumBlockStateSmithTypes.COLUMN.act(data, COLUMNAR_BLIGHT);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, this::cubeBottomTop, BLIGHTED_EARTH);
        MalumBlockStateSmithTypes.COVERING_BLOCK.act(data, BLIGHT);
        MalumBlockStateSmithTypes.BLIGHTED_GROWTH.act(data, BLIGHTED_GROWTH);
        MalumBlockStateSmithTypes.CREEPING_BLIGHT.act(data, CLINGING_BLIGHT);
        MalumBlockStateSmithTypes.POTTED_PLANT.act(data, POTTED_BLIGHTPEARL, POTTED_BLIGHTROOT);
        BlockStateSmithTypes.CROSS_MODEL_BLOCK.act(data, BLIGHTPEARL, BLIGHTROOT);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.BLOCK_MODEL_ITEM, this::simpleBlock, this::blightedSoulwoodModel, BLIGHTED_SOULWOOD);

        setTexturePath("blight/scarstone/");
        MalumBlockStateSmithTypes.LARGE_STRANGE_CRYSTAL.act(data, LARGE_STRANGE_CRYSTAL);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.GENERATED_ITEM, this::simpleBlock, this::crossModel, STRANGE_CRYSTAL);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.BLOCK_MODEL_ITEM, this::simpleBlock, this::cubeBottomTop, SCARSTONE);
        BlockStateSmithTypes.CROSS_MODEL_BLOCK.act(data, STRANGEROOT);
        MalumBlockStateSmithTypes.POTTED_PLANT.act(data, POTTED_STRANGEROOT);

        setTexturePath("dungeon/flesh/");
        MalumBlockStateSmithTypes.COLUMN.act(data, COLUMNAR_FLESH);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::directionalBlock, this::cubeBottomTop, FLESHBULB);
        MalumBlockStateSmithTypes.WRITHING_FLESH.act(data, WRITHING_FLESH);

        setTexturePath("dungeon/effigy/");
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.BLOCK_MODEL_ITEM, this::horizontalBlock, this::meditatingEffigy, VEILED_EFFIGY, CORRUPT_EFFIGY, CRACKED_EFFIGY);

        setTexturePath("waveform_artifice/");
        MalumBlockStateSmithTypes.SPIRIT_DIODE.act(data, WAVECHARGER, WAVEBANKER, WAVEMAKER, WAVEBREAKER);
        MalumBlockStateSmithTypes.GUST_TECH_BLOCK.act(data, GUST_IGNITER, WIND_TUNNEL);

        setTexturePath("ether/");
        itemModelProvider.setTexturePath("ether/");
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.GENERATED_ITEM,
                this::simpleBlock, this::etherModel, ETHER);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, MalumItemModelSmithTypes.GENERATED_OVERLAY_ITEM,
                this::simpleBlock, this::etherModel, IRIDESCENT_ETHER);

        MalumBlockStateSmithTypes.ETHER_CANDLE_BLOCK.act(data,
                ETHER_CANDLE, IRIDESCENT_ETHER_CANDLE);
        MalumBlockStateSmithTypes.ETHER_TORCH_BLOCK.act(data,
                ETHER_TORCH, IRIDESCENT_ETHER_TORCH);
        MalumBlockStateSmithTypes.ETHER_WALL_TORCH_BLOCK.act(data,
                WALL_ETHER_TORCH, IRIDESCENT_WALL_ETHER_TORCH);
        MalumBlockStateSmithTypes.ETHER_BRAZIER_BLOCK.act(data,
                TAINTED_ETHER_BRAZIER, TWISTED_ETHER_BRAZIER, DROSS_ETHER_BRAZIER,
                TAINTED_IRIDESCENT_ETHER_BRAZIER, TWISTED_IRIDESCENT_ETHER_BRAZIER, DROSS_IRIDESCENT_ETHER_BRAZIER);
        MalumBlockStateSmithTypes.ETHER_CRESSET_BLOCK.act(data,
                TAINTED_ETHER_CRESSET, TWISTED_ETHER_CRESSET, DROSS_ETHER_CRESSET,
                TAINTED_IRIDESCENT_ETHER_CRESSET, TWISTED_IRIDESCENT_ETHER_CRESSET, DROSS_IRIDESCENT_ETHER_CRESSET);
        itemModelProvider.setTexturePath("");
        setTexturePath("");

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.BLOCK_MODEL_ITEM, this::simpleBlock, this::cubeBottomTop,
                RITE_ANCHOR, RITE_UNWEAVER);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.BLOCK_MODEL_ITEM, this::simpleBlock, this::predefinedModel,
                SPIRIT_ALTAR, SOUL_BRAZIER, RITUAL_PLINTH);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.BUILTIN_ENTITY_ITEM, this::simpleBlock, this::predefinedModel,
                SPIRIT_JAR);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.BLOCK_MODEL_ITEM, this::horizontalBlock, this::predefinedModel,
                WEAVERS_WORKBENCH, RUNIC_WORKBENCH);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.NO_DATAGEN, this::simpleBlock, this::predefinedModel,
                RUNEWOOD_OBELISK, RUNEWOOD_OBELISK_COMPONENT, BRILLIANT_OBELISK, BRILLIANT_OBELISK_COMPONENT, ARCANA_PYLON, ARCANA_PYLON_COMPONENT, SPIRIT_CRUCIBLE, SPIRIT_CRUCIBLE_COMPONENT, REPAIR_PYLON);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.BLOCK_MODEL_ITEM, this::simpleBlock, this::totemBaseModel, RUNEWOOD_TOTEM_BASE, SOULWOOD_TOTEM_BASE, WAVEFORM_RUNEWOOD_TOTEM_BASE, WAVEFORM_SOULWOOD_TOTEM_BASE);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.NO_DATAGEN, this::horizontalBlock, this::predefinedModel,
                SPIRIT_CATALYZER, SPIRIT_CATALYZER_COMPONENT);

        MalumBlockStateSmithTypes.REPAIR_PYLON_COMPONENT.act(data, REPAIR_PYLON_COMPONENT);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.BLOCK_MODEL_ITEM, this::simpleBlock, this::predefinedModel,
                VOID_CONDUIT, VOID_DEPOT);

        MalumBlockStateSmithTypes.WEEPING_WELL_LAYERED_BLOCK.act(data, WEEPING_WELL_CENTER, WEEPING_WELL_SIDE, WEEPING_WELL_SIDE_MIRROR, WEEPING_WELL_CORNER);
        MalumBlockStateSmithTypes.WEEPING_WELL_BLOCK.act(data, WEEPING_WELL_FLAGSTONE);
        MalumBlockStateSmithTypes.WEEPING_WELL_DIRECTIONAL_BLOCK.act(data, WEEPING_WELL_COLUMN_BASE, WEEPING_WELL_COLUMN, WEEPING_WELL_COLUMN_CAP);

        MalumBlockStateSmithTypes.PRIMORDIAL_SOUP.act(data, PRIMORDIAL_SOUP);

        BlockStateSmithTypes.FULL_BLOCK.act(data, THE_DEVICE, THE_VESSEL);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.NO_DATAGEN, this::simpleBlock, this::cubeModelAirTexture, SPIRIT_MOTE);
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

    public ModelFile layeredBlockModel(Block block) {
        String name = getBlockName(block);
        ResourceLocation parent = malumPath("block/templates/template_glowing_block");
        ResourceLocation texture = getBlockTexture(name);
        ResourceLocation glowingTexture = getBlockTexture(name + "_glow");
        return models().withExistingParent(name, parent).texture("all", texture).texture("glow", glowingTexture).texture("particle", texture);
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
        ResourceLocation top = getStaticBlockTexture("soulwood/soulwood_log_top");
        return models().cubeBottomTop(name, side, bottom, top);
    }

    public ModelFile meditatingEffigy(Block block) {
        String name = getBlockName(block);
        ResourceLocation effigy = getBlockTexture(name);
        return models().withExistingParent(name, malumPath("block/templates/dungeon/template_meditating_effigy")).texture("effigy", effigy);
    }

    public ModelFile cubeBottomTop(Block block) {
        String name = getBlockName(block);
        ResourceLocation side = getBlockTexture(name + "_side");
        ResourceLocation bottom = getBlockTexture(name + "_bottom");
        ResourceLocation top = getBlockTexture(name + "_top");
        return models().cubeBottomTop(name, side, bottom, top);
    }

    public ModelFile crossModel(Block block) {
        String name = getBlockName(block);
        return models().cross(name, getBlockTexture(name));
    }
}
