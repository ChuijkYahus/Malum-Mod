package com.sammy.malum.registry.common.content.block;

import com.sammy.malum.common.block.blight.*;
import com.sammy.malum.common.block.flora.soulwood.SoulwoodSaplingBlock;
import com.sammy.malum.common.block.flora.wood.*;
import com.sammy.malum.common.block.storage.pedestal.*;
import com.sammy.malum.common.block.storage.stand.*;
import com.sammy.malum.common.block.the_device.*;
import com.sammy.malum.registry.common.content.MalumContent;
import com.sammy.malum.registry.common.content.item.MalumItemProperties;
import com.sammy.malum.registry.common.content.block.properties.*;
import com.sammy.malum.registry.common.magic.*;
import com.sammy.malum.registry.common.worldgen.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.neoforged.fml.event.lifecycle.*;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import team.lodestar.lodestone.modules.toolkit.block.*;
import team.lodestar.lodestone.modules.toolkit.item.LodestoneItemProperties;
import team.lodestar.lodestone.modules.toolkit.multiblock.MultiBlockItem;
import team.lodestar.lodestone.modules.toolkit.multiblock.MultiBlockStructure;

import java.awt.*;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import static com.sammy.malum.MalumMod.*;
import static com.sammy.malum.registry.common.MalumTags.Blocks.*;
import static net.minecraft.tags.BlockTags.*;
import static net.neoforged.neoforge.common.Tags.Blocks.FENCE_GATES_WOODEN;


public class MalumBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MALUM);

    //region runewood
    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_SAPLING = registerBlock("runewood_sapling", () -> new MalumSaplingBlock(MalumTreeGrowers.RUNEWOOD, MalumBlockProperties.RUNEWOOD_SAPLING()));
    public static final BlockBlockItemHolder<Block, BlockItem> AZURE_RUNEWOOD_SAPLING = registerBlock("azure_runewood_sapling", () -> new MalumSaplingBlock(MalumTreeGrowers.AZURE_RUNEWOOD, MalumBlockProperties.RUNEWOOD_SAPLING()));

    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_LEAVES = registerBlock("runewood_leaves", () -> new MalumLeavesBlock(MalumBlockProperties.RUNEWOOD_LEAVES(), MalumBlockProperties.RUNEWOOD_LEAVES_ORANGE, MalumBlockProperties.RUNEWOOD_LEAVES_YELLOW));
    public static final BlockBlockItemHolder<Block, BlockItem> AZURE_RUNEWOOD_LEAVES = registerBlock("azure_runewood_leaves", () -> new MalumLeavesBlock(MalumBlockProperties.RUNEWOOD_LEAVES(), MalumBlockProperties.AZURE_RUNEWOOD_LEAVES_BLUE, MalumBlockProperties.AZURE_RUNEWOOD_LEAVES_CYAN));

    public static final BlockBlockItemHolder<Block, BlockItem> HANGING_RUNEWOOD_LEAVES = registerBlock("hanging_runewood_leaves", () -> new MalumHangingLeavesBlock(MalumBlockProperties.HANGING_RUNEWOOD_LEAVES().setCutoutRenderType().noOcclusion().noCollission(), MalumBlockProperties.RUNEWOOD_LEAVES_ORANGE, MalumBlockProperties.RUNEWOOD_LEAVES_YELLOW));
    public static final BlockBlockItemHolder<Block, BlockItem> HANGING_AZURE_RUNEWOOD_LEAVES = registerBlock("hanging_azure_runewood_leaves", () -> new MalumHangingLeavesBlock(MalumBlockProperties.HANGING_RUNEWOOD_LEAVES().setCutoutRenderType().noOcclusion().noCollission(), MalumBlockProperties.AZURE_RUNEWOOD_LEAVES_BLUE, MalumBlockProperties.AZURE_RUNEWOOD_LEAVES_CYAN));

    public static final BlockBlockItemHolder<Block, BlockItem> STRIPPED_RUNEWOOD_LOG = registerBlock("stripped_runewood_log", () -> new RotatedPillarBlock(MalumBlockProperties.RUNEWOOD_LOGS().addTags(STRIPPED_LOGS)));
    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_LOG = registerBlock("runewood_log", () -> new MalumLogBlock(MalumBlockProperties.RUNEWOOD(), STRIPPED_RUNEWOOD_LOG));

    public static final BlockBlockItemHolder<Block, BlockItem> STRIPPED_RUNEWOOD = registerBlock("stripped_runewood", () -> new RotatedPillarBlock(MalumBlockProperties.RUNEWOOD_LOGS().addTags(STRIPPED_WOODS)));
    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD = registerBlock("runewood", () -> new LodestoneLogBlock(MalumBlockProperties.RUNEWOOD(), STRIPPED_RUNEWOOD));

    public static final BlockBlockItemHolder<Block, BlockItem> STRIPPED_SAPPY_RUNEWOOD_LOG = registerBlock("stripped_sappy_runewood_log", () -> new SappyLogBlock(MalumBlockProperties.RUNEWOOD_LOGS(), STRIPPED_RUNEWOOD_LOG, MalumContent.Materials.RUNIC_SAP_BOTTLE, MalumSpiritTypes.INFERNAL_COLORS().primaryColor()));
    public static final BlockBlockItemHolder<Block, BlockItem> SAPPY_RUNEWOOD_LOG = registerBlock("sappy_runewood_log", () -> new LodestoneLogBlock(MalumBlockProperties.RUNEWOOD().addTags(STRIPPED_LOGS), STRIPPED_SAPPY_RUNEWOOD_LOG));

    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_BOARDS = registerBlock("runewood_boards", () -> new Block(MalumBlockProperties.RUNEWOOD_PLANKS()));
    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_BOARDS_SLAB = registerBlock("runewood_boards_slab", () -> new SlabBlock(MalumBlockProperties.RUNEWOOD_SLABS()));
    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_BOARDS_STAIRS = registerBlock("runewood_boards_stairs", () -> new LodestoneStairBlock(MalumBlockProperties.RUNEWOOD_STAIRS()));

    public static final BlockBlockItemHolder<Block, BlockItem> VERTICAL_RUNEWOOD_BOARDS = registerBlock("vertical_runewood_boards", () -> new Block(MalumBlockProperties.RUNEWOOD_PLANKS()));
    public static final BlockBlockItemHolder<Block, BlockItem> VERTICAL_RUNEWOOD_BOARDS_SLAB = registerBlock("vertical_runewood_boards_slab", () -> new SlabBlock(MalumBlockProperties.RUNEWOOD_SLABS()));
    public static final BlockBlockItemHolder<Block, BlockItem> VERTICAL_RUNEWOOD_BOARDS_STAIRS = registerBlock("vertical_runewood_boards_stairs", () -> new LodestoneStairBlock(MalumBlockProperties.RUNEWOOD_STAIRS()));

    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_PLANKS = registerBlock("runewood_planks", () -> new Block(MalumBlockProperties.RUNEWOOD_PLANKS()));
    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_PLANKS_SLAB = registerBlock("runewood_planks_slab", () -> new SlabBlock(MalumBlockProperties.RUNEWOOD_SLABS()));
    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_PLANKS_STAIRS = registerBlock("runewood_planks_stairs", () -> new LodestoneStairBlock(MalumBlockProperties.RUNEWOOD_STAIRS()));

    public static final BlockBlockItemHolder<Block, BlockItem> RUSTIC_RUNEWOOD_PLANKS = registerBlock("rustic_runewood_planks", () -> new Block(MalumBlockProperties.RUNEWOOD_PLANKS()));
    public static final BlockBlockItemHolder<Block, BlockItem> RUSTIC_RUNEWOOD_PLANKS_SLAB = registerBlock("rustic_runewood_planks_slab", () -> new SlabBlock(MalumBlockProperties.RUNEWOOD_SLABS()));
    public static final BlockBlockItemHolder<Block, BlockItem> RUSTIC_RUNEWOOD_PLANKS_STAIRS = registerBlock("rustic_runewood_planks_stairs", () -> new LodestoneStairBlock(MalumBlockProperties.RUNEWOOD_STAIRS()));

    public static final BlockBlockItemHolder<Block, BlockItem> VERTICAL_RUNEWOOD_PLANKS = registerBlock("vertical_runewood_planks", () -> new Block(MalumBlockProperties.RUNEWOOD_PLANKS()));
    public static final BlockBlockItemHolder<Block, BlockItem> VERTICAL_RUNEWOOD_PLANKS_SLAB = registerBlock("vertical_runewood_planks_slab", () -> new SlabBlock(MalumBlockProperties.RUNEWOOD_SLABS()));
    public static final BlockBlockItemHolder<Block, BlockItem> VERTICAL_RUNEWOOD_PLANKS_STAIRS = registerBlock("vertical_runewood_planks_stairs", () -> new LodestoneStairBlock(MalumBlockProperties.RUNEWOOD_STAIRS()));

    public static final BlockBlockItemHolder<Block, BlockItem> VERTICAL_RUSTIC_RUNEWOOD_PLANKS = registerBlock("vertical_rustic_runewood_planks", () -> new Block(MalumBlockProperties.RUNEWOOD_PLANKS()));
    public static final BlockBlockItemHolder<Block, BlockItem> VERTICAL_RUSTIC_RUNEWOOD_PLANKS_SLAB = registerBlock("vertical_rustic_runewood_planks_slab", () -> new SlabBlock(MalumBlockProperties.RUNEWOOD_SLABS()));
    public static final BlockBlockItemHolder<Block, BlockItem> VERTICAL_RUSTIC_RUNEWOOD_PLANKS_STAIRS = registerBlock("vertical_rustic_runewood_planks_stairs", () -> new LodestoneStairBlock(MalumBlockProperties.RUNEWOOD_STAIRS()));

    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_TILES = registerBlock("runewood_tiles", () -> new Block(MalumBlockProperties.RUNEWOOD_PLANKS()));
    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_TILES_SLAB = registerBlock("runewood_tiles_slab", () -> new SlabBlock(MalumBlockProperties.RUNEWOOD_SLABS()));
    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_TILES_STAIRS = registerBlock("runewood_tiles_stairs", () -> new LodestoneStairBlock(MalumBlockProperties.RUNEWOOD_STAIRS()));

    public static final BlockBlockItemHolder<Block, BlockItem> RUSTIC_RUNEWOOD_TILES = registerBlock("rustic_runewood_tiles", () -> new Block(MalumBlockProperties.RUNEWOOD_PLANKS()));
    public static final BlockBlockItemHolder<Block, BlockItem> RUSTIC_RUNEWOOD_TILES_SLAB = registerBlock("rustic_runewood_tiles_slab", () -> new SlabBlock(MalumBlockProperties.RUNEWOOD_SLABS()));
    public static final BlockBlockItemHolder<Block, BlockItem> RUSTIC_RUNEWOOD_TILES_STAIRS = registerBlock("rustic_runewood_tiles_stairs", () -> new LodestoneStairBlock(MalumBlockProperties.RUNEWOOD_STAIRS()));

    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_PANEL = registerBlock("runewood_panel", () -> new Block(MalumBlockProperties.RUNEWOOD()));
    public static final BlockBlockItemHolder<Block, BlockItem> CUT_RUNEWOOD_PLANKS = registerBlock("cut_runewood_planks", () -> new Block(MalumBlockProperties.RUNEWOOD_PLANKS()));
    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_BEAM = registerBlock("runewood_beam", () -> new RotatedPillarBlock(MalumBlockProperties.RUNEWOOD()));

    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_DOOR = registerBlock("runewood_door", () -> new DoorBlock(MalumBlockSetTypes.RUNEWOOD, MalumBlockProperties.RUNEWOOD_DOOR()));
    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_BOARDS_DOOR = registerBlock("runewood_boards_door", () -> new DoorBlock(MalumBlockSetTypes.RUNEWOOD, MalumBlockProperties.RUNEWOOD_DOOR()));
    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_TRAPDOOR = registerBlock("runewood_trapdoor", () -> new TrapDoorBlock(MalumBlockSetTypes.RUNEWOOD, MalumBlockProperties.RUNEWOOD_TRAPDOOR()));
    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_BOARDS_TRAPDOOR = registerBlock("runewood_boards_trapdoor", () -> new TrapDoorBlock(MalumBlockSetTypes.RUNEWOOD, MalumBlockProperties.RUNEWOOD_TRAPDOOR()));

    public static final BlockBlockItemHolder<Block, BlockItem> BOLTED_RUNEWOOD_DOOR = registerBlock("bolted_runewood_door", () -> new DoorBlock(MalumBlockSetTypes.RUNEWOOD, MalumBlockProperties.RUNEWOOD_DOOR()));
    public static final BlockBlockItemHolder<Block, BlockItem> BOLTED_RUNEWOOD_BOARDS_DOOR = registerBlock("bolted_runewood_boards_door", () -> new DoorBlock(MalumBlockSetTypes.RUNEWOOD, MalumBlockProperties.RUNEWOOD_DOOR()));
    public static final BlockBlockItemHolder<Block, BlockItem> BOLTED_RUNEWOOD_TRAPDOOR = registerBlock("bolted_runewood_trapdoor", () -> new TrapDoorBlock(MalumBlockSetTypes.RUNEWOOD, MalumBlockProperties.RUNEWOOD_TRAPDOOR()));
    public static final BlockBlockItemHolder<Block, BlockItem> BOLTED_RUNEWOOD_BOARDS_TRAPDOOR = registerBlock("bolted_runewood_boards_trapdoor", () -> new TrapDoorBlock(MalumBlockSetTypes.RUNEWOOD, MalumBlockProperties.RUNEWOOD_TRAPDOOR()));

    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_BUTTON = registerBlock("runewood_planks_button", () -> new ButtonBlock(MalumBlockSetTypes.RUNEWOOD, 20, MalumBlockProperties.RUNEWOOD().noCollission().addTags(BUTTONS, WOODEN_BUTTONS).addTags(BUTTONS, WOODEN_BUTTONS)));
    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_PRESSURE_PLATE = registerBlock("runewood_planks_pressure_plate", () -> new PressurePlateBlock(MalumBlockSetTypes.RUNEWOOD, MalumBlockProperties.RUNEWOOD().noCollission().addTags(PRESSURE_PLATES, WOODEN_PRESSURE_PLATES)));

    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_BOARDS_WALL = registerBlock("runewood_boards_wall", () -> new WallBlock(MalumBlockProperties.RUNEWOOD().addTags(WALLS)));
    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_FENCE = registerBlock("runewood_planks_fence", () -> new FenceBlock(MalumBlockProperties.RUNEWOOD().addTags(FENCES, WOODEN_FENCES)));
    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_FENCE_GATE = registerBlock("runewood_planks_fence_gate", () -> new FenceGateBlock(MalumWoodTypes.RUNEWOOD, MalumBlockProperties.RUNEWOOD().addTags(FENCE_GATES, FENCE_GATES_WOODEN)));

    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_ITEM_STAND = registerBlock("runewood_item_stand", () -> new ItemStandBlock<>(MalumBlockProperties.RUNEWOOD().noOcclusion()).setBlockEntity(MalumBlockEntities.ITEM_STAND));
    public static final BlockBlockItemHolder<Block, BlockItem> GILDED_RUNEWOOD_ITEM_PEDESTAL = registerBlock("gilded_runewood_item_pedestal", () -> new DecoratedItemPedestalBlock<>(MalumBlockProperties.RUNEWOOD().setCutoutRenderType().noOcclusion()).setBlockEntity(MalumBlockEntities.ITEM_PEDESTAL));
    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_ITEM_PEDESTAL = registerBlock("runewood_item_pedestal", () -> new WoodItemPedestalBlock<>(MalumBlockProperties.RUNEWOOD().noOcclusion()).setBlockEntity(MalumBlockEntities.ITEM_PEDESTAL));
    public static final BlockBlockItemHolder<Block, BlockItem> GILDED_RUNEWOOD_ITEM_STAND = registerBlock("gilded_runewood_item_stand", () -> new ItemStandBlock<>(MalumBlockProperties.RUNEWOOD().noOcclusion()).setBlockEntity(MalumBlockEntities.ITEM_STAND));

    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_SIGN = registerBlock("runewood_sign", () -> new StandingSignBlock(MalumWoodTypes.RUNEWOOD, MalumBlockProperties.RUNEWOOD().addTags(SIGNS, STANDING_SIGNS).noOcclusion().noCollission()));
    public static final DeferredBlock<Block> RUNEWOOD_WALL_SIGN = registerBlockNoItem("runewood_wall_sign", () -> new WallSignBlock(MalumWoodTypes.RUNEWOOD, MalumBlockProperties.RUNEWOOD().addTags(SIGNS, WALL_SIGNS).noOcclusion().noCollission()));
    //endregion

    //region soulwood
    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_SAPLING = registerBlock("soulwood_sapling", () -> new SoulwoodSaplingBlock(MalumTreeGrowers.SOULWOOD, MalumBlockProperties.SOULWOOD_SAPLING()));

    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_LEAVES = registerBlock("soulwood_leaves", () -> new MalumLeavesBlock(MalumBlockProperties.SOULWOOD_LEAVES().setCutoutRenderType(), new Color(213, 8, 63), new Color(255, 61, 243)));
    public static final BlockBlockItemHolder<Block, BlockItem> HANGING_SOULWOOD_LEAVES = registerBlock("hanging_soulwood_leaves", () -> new MalumHangingLeavesBlock(MalumBlockProperties.HANGING_SOULWOOD_LEAVES().setCutoutRenderType().noOcclusion().noCollission(), new Color(213, 8, 63), new Color(255, 61, 243)));

    public static final BlockBlockItemHolder<Block, BlockItem> BLIGHTED_SOULWOOD = registerBlock("blighted_soulwood", () -> new BlightedSoulwoodBlock(MalumBlockProperties.SOULWOOD_LOGS()));

    public static final BlockBlockItemHolder<Block, BlockItem> STRIPPED_SOULWOOD_LOG = registerBlock("stripped_soulwood_log", () -> new RotatedPillarBlock(MalumBlockProperties.SOULWOOD_LOGS().addTags(STRIPPED_LOGS)));
    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_LOG = registerBlock("soulwood_log", () -> new MalumLogBlock(MalumBlockProperties.SOULWOOD_LOGS(), STRIPPED_SOULWOOD_LOG));

    public static final BlockBlockItemHolder<Block, BlockItem> STRIPPED_SOULWOOD = registerBlock("stripped_soulwood", () -> new RotatedPillarBlock(MalumBlockProperties.SOULWOOD_LOGS().addTags(STRIPPED_WOODS)));
    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD = registerBlock("soulwood", () -> new LodestoneLogBlock(MalumBlockProperties.SOULWOOD_LOGS(), STRIPPED_SOULWOOD));

    public static final BlockBlockItemHolder<Block, BlockItem> STRIPPED_SAPPY_SOULWOOD_LOG = registerBlock("stripped_sappy_soulwood_log", () -> new SappyLogBlock(MalumBlockProperties.SOULWOOD_LOGS(), STRIPPED_SOULWOOD_LOG, MalumContent.Materials.CURSED_SAP_BOTTLE, MalumSpiritTypes.ELDRITCH_COLORS().primaryColor(), new Color(255, 61, 106)));
    public static final BlockBlockItemHolder<Block, BlockItem> SAPPY_SOULWOOD_LOG = registerBlock("sappy_soulwood_log", () -> new LodestoneLogBlock(MalumBlockProperties.SOULWOOD_LOGS().addTags(STRIPPED_LOGS), STRIPPED_SAPPY_SOULWOOD_LOG));

    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_BOARDS = registerBlock("soulwood_boards", () -> new Block(MalumBlockProperties.SOULWOOD_PLANKS()));
    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_BOARDS_SLAB = registerBlock("soulwood_boards_slab", () -> new SlabBlock(MalumBlockProperties.SOULWOOD_SLABS()));
    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_BOARDS_STAIRS = registerBlock("soulwood_boards_stairs", () -> new LodestoneStairBlock(MalumBlockProperties.SOULWOOD_STAIRS()));

    public static final BlockBlockItemHolder<Block, BlockItem> VERTICAL_SOULWOOD_BOARDS = registerBlock("vertical_soulwood_boards", () -> new Block(MalumBlockProperties.SOULWOOD_PLANKS()));
    public static final BlockBlockItemHolder<Block, BlockItem> VERTICAL_SOULWOOD_BOARDS_SLAB = registerBlock("vertical_soulwood_boards_slab", () -> new SlabBlock(MalumBlockProperties.SOULWOOD_SLABS()));
    public static final BlockBlockItemHolder<Block, BlockItem> VERTICAL_SOULWOOD_BOARDS_STAIRS = registerBlock("vertical_soulwood_boards_stairs", () -> new LodestoneStairBlock(MalumBlockProperties.SOULWOOD_STAIRS()));

    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_PLANKS = registerBlock("soulwood_planks", () -> new Block(MalumBlockProperties.SOULWOOD_PLANKS()));
    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_PLANKS_SLAB = registerBlock("soulwood_planks_slab", () -> new SlabBlock(MalumBlockProperties.SOULWOOD_SLABS()));
    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_PLANKS_STAIRS = registerBlock("soulwood_planks_stairs", () -> new LodestoneStairBlock(MalumBlockProperties.SOULWOOD_STAIRS()));

    public static final BlockBlockItemHolder<Block, BlockItem> RUSTIC_SOULWOOD_PLANKS = registerBlock("rustic_soulwood_planks", () -> new Block(MalumBlockProperties.SOULWOOD_PLANKS()));
    public static final BlockBlockItemHolder<Block, BlockItem> RUSTIC_SOULWOOD_PLANKS_SLAB = registerBlock("rustic_soulwood_planks_slab", () -> new SlabBlock(MalumBlockProperties.SOULWOOD_SLABS()));
    public static final BlockBlockItemHolder<Block, BlockItem> RUSTIC_SOULWOOD_PLANKS_STAIRS = registerBlock("rustic_soulwood_planks_stairs", () -> new LodestoneStairBlock(MalumBlockProperties.SOULWOOD_STAIRS()));

    public static final BlockBlockItemHolder<Block, BlockItem> VERTICAL_SOULWOOD_PLANKS = registerBlock("vertical_soulwood_planks", () -> new Block(MalumBlockProperties.SOULWOOD_PLANKS()));
    public static final BlockBlockItemHolder<Block, BlockItem> VERTICAL_SOULWOOD_PLANKS_SLAB = registerBlock("vertical_soulwood_planks_slab", () -> new SlabBlock(MalumBlockProperties.SOULWOOD_SLABS()));
    public static final BlockBlockItemHolder<Block, BlockItem> VERTICAL_SOULWOOD_PLANKS_STAIRS = registerBlock("vertical_soulwood_planks_stairs", () -> new LodestoneStairBlock(MalumBlockProperties.SOULWOOD_STAIRS()));

    public static final BlockBlockItemHolder<Block, BlockItem> VERTICAL_RUSTIC_SOULWOOD_PLANKS = registerBlock("vertical_rustic_soulwood_planks", () -> new Block(MalumBlockProperties.SOULWOOD_PLANKS()));
    public static final BlockBlockItemHolder<Block, BlockItem> VERTICAL_RUSTIC_SOULWOOD_PLANKS_SLAB = registerBlock("vertical_rustic_soulwood_planks_slab", () -> new SlabBlock(MalumBlockProperties.SOULWOOD_SLABS()));
    public static final BlockBlockItemHolder<Block, BlockItem> VERTICAL_RUSTIC_SOULWOOD_PLANKS_STAIRS = registerBlock("vertical_rustic_soulwood_planks_stairs", () -> new LodestoneStairBlock(MalumBlockProperties.SOULWOOD_STAIRS()));

    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_TILES = registerBlock("soulwood_tiles", () -> new Block(MalumBlockProperties.SOULWOOD_PLANKS()));
    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_TILES_SLAB = registerBlock("soulwood_tiles_slab", () -> new SlabBlock(MalumBlockProperties.SOULWOOD_SLABS()));
    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_TILES_STAIRS = registerBlock("soulwood_tiles_stairs", () -> new LodestoneStairBlock(MalumBlockProperties.SOULWOOD_STAIRS()));

    public static final BlockBlockItemHolder<Block, BlockItem> RUSTIC_SOULWOOD_TILES = registerBlock("rustic_soulwood_tiles", () -> new Block(MalumBlockProperties.SOULWOOD_PLANKS()));
    public static final BlockBlockItemHolder<Block, BlockItem> RUSTIC_SOULWOOD_TILES_SLAB = registerBlock("rustic_soulwood_tiles_slab", () -> new SlabBlock(MalumBlockProperties.SOULWOOD_SLABS()));
    public static final BlockBlockItemHolder<Block, BlockItem> RUSTIC_SOULWOOD_TILES_STAIRS = registerBlock("rustic_soulwood_tiles_stairs", () -> new LodestoneStairBlock(MalumBlockProperties.SOULWOOD_STAIRS()));

    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_PANEL = registerBlock("soulwood_panel", () -> new Block(MalumBlockProperties.SOULWOOD()));
    public static final BlockBlockItemHolder<Block, BlockItem> CUT_SOULWOOD_PLANKS = registerBlock("cut_soulwood_planks", () -> new Block(MalumBlockProperties.SOULWOOD_PLANKS()));
    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_BEAM = registerBlock("soulwood_beam", () -> new RotatedPillarBlock(MalumBlockProperties.SOULWOOD()));

    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_DOOR = registerBlock("soulwood_door", () -> new DoorBlock(MalumBlockSetTypes.SOULWOOD, MalumBlockProperties.SOULWOOD_DOOR()));
    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_BOARDS_DOOR = registerBlock("soulwood_boards_door", () -> new DoorBlock(MalumBlockSetTypes.SOULWOOD, MalumBlockProperties.SOULWOOD_DOOR()));
    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_TRAPDOOR = registerBlock("soulwood_trapdoor", () -> new TrapDoorBlock(MalumBlockSetTypes.SOULWOOD, MalumBlockProperties.SOULWOOD_TRAPDOOR()));
    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_BOARDS_TRAPDOOR = registerBlock("soulwood_boards_trapdoor", () -> new TrapDoorBlock(MalumBlockSetTypes.SOULWOOD, MalumBlockProperties.SOULWOOD_TRAPDOOR()));

    public static final BlockBlockItemHolder<Block, BlockItem> BOLTED_SOULWOOD_DOOR = registerBlock("bolted_soulwood_door", () -> new DoorBlock(MalumBlockSetTypes.SOULWOOD, MalumBlockProperties.SOULWOOD_DOOR()));
    public static final BlockBlockItemHolder<Block, BlockItem> BOLTED_SOULWOOD_BOARDS_DOOR = registerBlock("bolted_soulwood_boards_door", () -> new DoorBlock(MalumBlockSetTypes.SOULWOOD, MalumBlockProperties.SOULWOOD_DOOR()));
    public static final BlockBlockItemHolder<Block, BlockItem> BOLTED_SOULWOOD_TRAPDOOR = registerBlock("bolted_soulwood_trapdoor", () -> new TrapDoorBlock(MalumBlockSetTypes.SOULWOOD, MalumBlockProperties.SOULWOOD_TRAPDOOR()));
    public static final BlockBlockItemHolder<Block, BlockItem> BOLTED_SOULWOOD_BOARDS_TRAPDOOR = registerBlock("bolted_soulwood_boards_trapdoor", () -> new TrapDoorBlock(MalumBlockSetTypes.SOULWOOD, MalumBlockProperties.SOULWOOD_TRAPDOOR()));

    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_BUTTON = registerBlock("soulwood_planks_button", () -> new ButtonBlock(MalumBlockSetTypes.SOULWOOD, 20, MalumBlockProperties.SOULWOOD().noCollission().addTags(BUTTONS, WOODEN_BUTTONS).addTags(BUTTONS, WOODEN_BUTTONS)));
    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_PRESSURE_PLATE = registerBlock("soulwood_planks_pressure_plate", () -> new PressurePlateBlock(MalumBlockSetTypes.SOULWOOD, MalumBlockProperties.SOULWOOD().noCollission().addTags(PRESSURE_PLATES, WOODEN_PRESSURE_PLATES)));

    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_BOARDS_WALL = registerBlock("soulwood_boards_wall", () -> new WallBlock(MalumBlockProperties.SOULWOOD().addTags(WALLS)));
    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_FENCE = registerBlock("soulwood_planks_fence", () -> new FenceBlock(MalumBlockProperties.SOULWOOD().addTags(FENCES, WOODEN_FENCES)));
    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_FENCE_GATE = registerBlock("soulwood_planks_fence_gate", () -> new FenceGateBlock(MalumWoodTypes.SOULWOOD, MalumBlockProperties.SOULWOOD().addTags(FENCE_GATES, FENCE_GATES_WOODEN)));


    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_ITEM_PEDESTAL = registerBlock("soulwood_item_pedestal", () -> new WoodItemPedestalBlock<>(MalumBlockProperties.SOULWOOD().noOcclusion()).setBlockEntity(MalumBlockEntities.ITEM_PEDESTAL));
    public static final BlockBlockItemHolder<Block, BlockItem> ORNATE_SOULWOOD_ITEM_PEDESTAL = registerBlock("ornate_soulwood_item_pedestal", () -> new DecoratedItemPedestalBlock<>(MalumBlockProperties.SOULWOOD().setCutoutRenderType().noOcclusion()).setBlockEntity(MalumBlockEntities.ITEM_PEDESTAL));
    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_ITEM_STAND = registerBlock("soulwood_item_stand", () -> new ItemStandBlock<>(MalumBlockProperties.SOULWOOD().noOcclusion()).setBlockEntity(MalumBlockEntities.ITEM_STAND));
    public static final BlockBlockItemHolder<Block, BlockItem> ORNATE_SOULWOOD_ITEM_STAND = registerBlock("ornate_soulwood_item_stand", () -> new ItemStandBlock<>(MalumBlockProperties.SOULWOOD().noOcclusion()).setBlockEntity(MalumBlockEntities.ITEM_STAND));

    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_SIGN = registerBlock("soulwood_sign", () -> new StandingSignBlock(MalumWoodTypes.SOULWOOD, MalumBlockProperties.SOULWOOD().addTags(SIGNS, STANDING_SIGNS).noOcclusion().noCollission()));
    public static final DeferredBlock<Block> SOULWOOD_WALL_SIGN = registerBlockNoItem("soulwood_wall_sign", () -> new WallSignBlock(MalumWoodTypes.SOULWOOD, MalumBlockProperties.SOULWOOD().addTags(SIGNS, WALL_SIGNS).noOcclusion().noCollission()));
    //endregion

    //endregion

    //endregion


    //endregion
    public static final DeferredBlock<Block> POTTED_RUNEWOOD_SAPLING = registerBlockNoItem("potted_runewood_sapling", () -> flowerPot(RUNEWOOD_SAPLING));
    public static final DeferredBlock<Block> POTTED_AZURE_RUNEWOOD_SAPLING = registerBlockNoItem("potted_azure_runewood_sapling", () -> flowerPot(AZURE_RUNEWOOD_SAPLING));
    public static final DeferredBlock<Block> POTTED_SOULWOOD_SAPLING = registerBlockNoItem("potted_soulwood_sapling", () -> flowerPot(SOULWOOD_SAPLING));
    public static final DeferredBlock<Block> POTTED_BLIGHTROOT = registerBlockNoItem("potted_blightroot", () -> flowerPot(MalumContent.Blight.BLIGHTROOT));
    public static final DeferredBlock<Block> POTTED_BLIGHTPEARL = registerBlockNoItem("potted_blightpearl", () -> flowerPot(MalumContent.Blight.BLIGHTPEARL));
    public static final DeferredBlock<Block> POTTED_STRANGEROOT = registerBlockNoItem("potted_strangeroot", () -> flowerPot(MalumContent.Blight.STRANGEROOT));


    public static final BlockBlockItemHolder<Block, BlockItem> THE_DEVICE = registerBlock("the_device", () -> new TheDevice(MalumBlockProperties.TAINTED_ROCK()));
    public static final BlockBlockItemHolder<Block, BlockItem> THE_VESSEL = registerBlock("the_vessel", () -> new TheVessel(MalumBlockProperties.TWISTED_ROCK()));


    public static <T extends Block> BlockBlockItemHolder<T, BlockItem> registerBlock(String name, Supplier<T> supplier) {
        return registerBlock(name, name, supplier, BlockItem::new);
    }

    public static <T extends Block> BlockBlockItemHolder<T, MultiBlockItem> registerMultiBlock(String name, Supplier<T> supplier, Supplier<? extends MultiBlockStructure> structure) {
        return registerBlock(name, name, supplier, (b, p) -> new MultiBlockItem(b, p, structure));
    }

    public static <T extends Block> BlockBlockItemHolder<T, BlockItem> registerItemNameBlock(String blockName, String itemName, Supplier<T> supplier) {
        return registerBlock(blockName, itemName, supplier, ItemNameBlockItem::new);
    }

    public static <T extends Block, K extends BlockItem> BlockBlockItemHolder<T, K> registerBlock(String name, Supplier<T> blockSupplier, BiFunction<Block, LodestoneItemProperties, K> itemSupplier) {
        return registerBlock(name, name, blockSupplier, itemSupplier);
    }

    public static <T extends Block, K extends BlockItem> BlockBlockItemHolder<T, K> registerBlock(String blockName, String itemName, Supplier<T> blockSupplier, BiFunction<Block, LodestoneItemProperties, K> itemSupplier) {
        var block = BLOCKS.register(blockName, blockSupplier);
        var item = MalumItemProperties.register(itemName, MalumItemProperties::DEFAULT_PROPERTIES, p -> itemSupplier.apply(block.get(), p));
        return new BlockBlockItemHolder<>(block, item);
    }

    public static <T extends Block> DeferredBlock<T> registerBlockNoItem(String name, Supplier<T> supplier) {
        return BLOCKS.register(name, supplier);
    }

    private static Block flowerPot(BlockBlockItemHolder<Block, BlockItem> potted) {
        return new FlowerPotBlock(() -> (FlowerPotBlock) net.minecraft.world.level.block.Blocks.FLOWER_POT, potted, MalumBlockProperties.POTTED_PLANT());
    }

    public static void addPottedBlocks(FMLCommonSetupEvent event) {
        FlowerPotBlock flowerPot = (FlowerPotBlock) net.minecraft.world.level.block.Blocks.FLOWER_POT;
        flowerPot.addPlant(RUNEWOOD_SAPLING.block().getId(), POTTED_RUNEWOOD_SAPLING);
        flowerPot.addPlant(AZURE_RUNEWOOD_SAPLING.block().getId(), POTTED_AZURE_RUNEWOOD_SAPLING);
        flowerPot.addPlant(SOULWOOD_SAPLING.block().getId(), POTTED_SOULWOOD_SAPLING);
        flowerPot.addPlant(MalumContent.Blight.BLIGHTROOT.block().getId(), POTTED_BLIGHTROOT);
        flowerPot.addPlant(MalumContent.Blight.BLIGHTPEARL.block().getId(), POTTED_BLIGHTPEARL);
        flowerPot.addPlant(MalumContent.Blight.STRANGEROOT.block().getId(), POTTED_STRANGEROOT);
    }
}