package com.sammy.malum.registry.common.util;

import com.sammy.malum.common.block.flora.wood.MalumHangingLeavesBlock;
import com.sammy.malum.common.block.flora.wood.MalumLeavesBlock;
import com.sammy.malum.common.block.flora.wood.MalumLogBlock;
import com.sammy.malum.common.block.flora.wood.MalumSaplingBlock;
import com.sammy.malum.common.block.storage.pedestal.WoodItemPedestalBlock;
import com.sammy.malum.common.block.storage.stand.ItemStandBlock;
import com.sammy.malum.registry.common.content.block.MalumBlockEntities;
import com.sammy.malum.registry.common.content.block.MalumBlockSetTypes;
import com.sammy.malum.registry.common.content.block.MalumWoodTypes;
import com.sammy.malum.registry.common.content.block.properties.MalumBlockProperties;
import com.sammy.malum.registry.common.worldgen.MalumTreeGrowers;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.*;
import team.lodestar.lodestone.modules.toolkit.block.BlockBlockItemHolder;
import team.lodestar.lodestone.modules.toolkit.block.LodestoneLogBlock;

import static com.sammy.malum.registry.common.MalumTags.Blocks.STRIPPED_LOGS;
import static com.sammy.malum.registry.common.MalumTags.Blocks.STRIPPED_WOODS;
import static com.sammy.malum.registry.common.content.block.MalumBlocks.registerBlock;
import static net.minecraft.tags.BlockTags.*;
import static net.neoforged.neoforge.common.Tags.Blocks.FENCE_GATES_WOODEN;

public class WoodBlockSet {

    private final String baseName;

    private String name(String suffix) {
        return baseName + "_" + suffix;
    }

    public final BlockBlockItemHolder<Block, BlockItem> sapling;
    public final BlockBlockItemHolder<Block, BlockItem> leaves;
    public final BlockBlockItemHolder<Block, BlockItem> hangingLeaves;

    public final BlockBlockItemHolder<Block, BlockItem> strippedLog;
    public final BlockBlockItemHolder<Block, BlockItem> log;

    public final BlockBlockItemHolder<Block, BlockItem> strippedWood;
    public final BlockBlockItemHolder<Block, BlockItem> wood;

    public final BlockBlockItemHolder<Block, BlockItem> planks;
    public final BlockBlockItemHolder<Block, BlockItem> planksSlab;
    public final BlockBlockItemHolder<Block, BlockItem> planksStairs;

    public final BlockBlockItemHolder<Block, BlockItem> boards;
    public final BlockBlockItemHolder<Block, BlockItem> boardsSlab;
    public final BlockBlockItemHolder<Block, BlockItem> boardsStairs;

    public final BlockBlockItemHolder<Block, BlockItem> verticalBoards;
    public final BlockBlockItemHolder<Block, BlockItem> verticalBoardsSlab;
    public final BlockBlockItemHolder<Block, BlockItem> verticalBoardsStairs;

    public final BlockBlockItemHolder<Block, BlockItem> tiles;
    public final BlockBlockItemHolder<Block, BlockItem> tilesSlab;
    public final BlockBlockItemHolder<Block, BlockItem> tilesStairs;

    public final BlockBlockItemHolder<Block, BlockItem> door;
    public final BlockBlockItemHolder<Block, BlockItem> trapdoor;

    public final BlockBlockItemHolder<Block, BlockItem> button;
    public final BlockBlockItemHolder<Block, BlockItem> pressurePlate;

    public final BlockBlockItemHolder<Block, BlockItem> fence;
    public final BlockBlockItemHolder<Block, BlockItem> fenceGate;

    public final BlockBlockItemHolder<Block, BlockItem> itemStand;
    public final BlockBlockItemHolder<Block, BlockItem> itemPedestal;

    public WoodBlockSet(String baseName) {
        this.baseName = baseName;

        sapling = registerBlock(name("sapling"),
                () -> new MalumSaplingBlock(MalumTreeGrowers.RUNEWOOD, MalumBlockProperties.RUNEWOOD_SAPLING()));

        leaves = registerBlock(name("leaves"),
                () -> new MalumLeavesBlock(MalumBlockProperties.RUNEWOOD_LEAVES(),
                        MalumBlockProperties.RUNEWOOD_LEAVES_ORANGE,
                        MalumBlockProperties.RUNEWOOD_LEAVES_YELLOW));

        hangingLeaves = registerBlock(name("hanging_leaves"),
                () -> new MalumHangingLeavesBlock(
                        MalumBlockProperties.HANGING_RUNEWOOD_LEAVES().setCutoutRenderType().noOcclusion().noCollission(),
                        MalumBlockProperties.RUNEWOOD_LEAVES_ORANGE,
                        MalumBlockProperties.RUNEWOOD_LEAVES_YELLOW));

        strippedLog = registerBlock(name("stripped_log"),
                () -> new RotatedPillarBlock(MalumBlockProperties.RUNEWOOD_LOGS().addTags(STRIPPED_LOGS)));

        log = registerBlock(name("log"),
                () -> new MalumLogBlock(MalumBlockProperties.RUNEWOOD(), strippedLog));

        strippedWood = registerBlock(name("stripped_wood"),
                () -> new RotatedPillarBlock(MalumBlockProperties.RUNEWOOD_LOGS().addTags(STRIPPED_WOODS)));

        wood = registerBlock(name("wood"),
                () -> new LodestoneLogBlock(MalumBlockProperties.RUNEWOOD(), strippedWood));

        planks = registerBlock(name("planks"),
                () -> new Block(MalumBlockProperties.RUNEWOOD_PLANKS()));

        planksSlab = registerBlock(name("planks_slab"),
                () -> new SlabBlock(MalumBlockProperties.RUNEWOOD_SLABS()));

        planksStairs = registerBlock(name("planks_stairs"),
                () -> new StairBlock(planks.get().defaultBlockState(), MalumBlockProperties.RUNEWOOD_STAIRS()));

        boards = registerBlock(name("boards"),
                () -> new Block(MalumBlockProperties.RUNEWOOD_PLANKS()));

        boardsSlab = registerBlock(name("boards_slab"),
                () -> new SlabBlock(MalumBlockProperties.RUNEWOOD_SLABS()));

        boardsStairs = registerBlock(name("boards_stairs"),
                () -> new StairBlock(boards.get().defaultBlockState(), MalumBlockProperties.RUNEWOOD_STAIRS()));

        verticalBoards = registerBlock(name("vertical_boards"),
                () -> new Block(MalumBlockProperties.RUNEWOOD_PLANKS()));

        verticalBoardsSlab = registerBlock(name("vertical_boards_slab"),
                () -> new SlabBlock(MalumBlockProperties.RUNEWOOD_SLABS()));

        verticalBoardsStairs = registerBlock(name("vertical_boards_stairs"),
                () -> new StairBlock(verticalBoards.get().defaultBlockState(), MalumBlockProperties.RUNEWOOD_STAIRS()));

        tiles = registerBlock(name("tiles"),
                () -> new Block(MalumBlockProperties.RUNEWOOD_PLANKS()));

        tilesSlab = registerBlock(name("tiles_slab"),
                () -> new SlabBlock(MalumBlockProperties.RUNEWOOD_SLABS()));

        tilesStairs = registerBlock(name("tiles_stairs"),
                () -> new StairBlock(tiles.get().defaultBlockState(), MalumBlockProperties.RUNEWOOD_STAIRS()));

        door = registerBlock(name("door"),
                () -> new DoorBlock(MalumBlockSetTypes.RUNEWOOD, MalumBlockProperties.RUNEWOOD_DOOR()));

        trapdoor = registerBlock(name("trapdoor"),
                () -> new TrapDoorBlock(MalumBlockSetTypes.RUNEWOOD, MalumBlockProperties.RUNEWOOD_TRAPDOOR()));

        button = registerBlock(name("button"),
                () -> new ButtonBlock(MalumBlockSetTypes.RUNEWOOD, 20,
                        MalumBlockProperties.RUNEWOOD().noCollission().addTags(BUTTONS, WOODEN_BUTTONS)));

        pressurePlate = registerBlock(name("pressure_plate"),
                () -> new PressurePlateBlock(MalumBlockSetTypes.RUNEWOOD,
                        MalumBlockProperties.RUNEWOOD().noCollission().addTags(PRESSURE_PLATES, WOODEN_PRESSURE_PLATES)));

        fence = registerBlock(name("fence"),
                () -> new FenceBlock(MalumBlockProperties.RUNEWOOD().addTags(FENCES, WOODEN_FENCES)));

        fenceGate = registerBlock(name("fence_gate"),
                () -> new FenceGateBlock(MalumWoodTypes.RUNEWOOD,
                        MalumBlockProperties.RUNEWOOD().addTags(FENCE_GATES, FENCE_GATES_WOODEN)));

        itemStand = registerBlock(name("item_stand"),
                () -> new ItemStandBlock<>(MalumBlockProperties.RUNEWOOD().noOcclusion())
                        .setBlockEntity(MalumBlockEntities.ITEM_STAND));

        itemPedestal = registerBlock(name("item_pedestal"),
                () -> new WoodItemPedestalBlock<>(MalumBlockProperties.RUNEWOOD().noOcclusion())
                        .setBlockEntity(MalumBlockEntities.ITEM_PEDESTAL));
    }
}