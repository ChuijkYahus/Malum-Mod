package com.sammy.malum.registry.common.util;

import com.sammy.malum.MalumMod;
import com.sammy.malum.common.block.decor.ColumnBlock;
import com.sammy.malum.common.block.flora.wood.*;
import com.sammy.malum.common.block.storage.pedestal.DecoratedItemPedestalBlock;
import com.sammy.malum.common.block.storage.pedestal.WoodItemPedestalBlock;
import com.sammy.malum.common.block.storage.stand.ItemStandBlock;
import com.sammy.malum.registry.common.MalumContent;
import com.sammy.malum.registry.common.block.MalumWoodTypes;
import com.sammy.malum.registry.common.magic.MalumSpiritTypes;
import com.sammy.malum.registry.common.util.data.BlockBundle;
import com.sammy.malum.registry.common.util.data.BlockBundleWithWall;
import com.sammy.malum.registry.common.util.data.ItemlessBlockBundle;
import com.sammy.malum.registry.common.util.data.ItemlessBlockBundleWithWall;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import team.lodestar.lodestone.modules.toolkit.block.BlockBlockItemHolder;
import team.lodestar.lodestone.modules.toolkit.block.LodestoneBlockProperties;
import team.lodestar.lodestone.modules.toolkit.creative_tab.CreativeTabCategoryBuilder;

import java.util.function.Supplier;

import static com.sammy.malum.registry.common.MalumContent.registerBlock;
import static com.sammy.malum.registry.common.MalumContent.registerBlockNoItem;
import static team.lodestar.lodestone.modules.toolkit.block.LodestoneBlockProperties.copy;

public class WoodBlockSet {

    public final String id;

    public String name(String name) {
        return name.replace("%s", id);
    }

    public final TagKey<Block> allLogsTag, logsTag, strippedLogsTag;

    public final TagKey<Block> boardsTag, boardStairsTag, boardSlabsTag, boardWallsTag;
    public final TagKey<Block> planksTag, plankStairsTag, plankSlabsTag;

    public final BlockBlockItemHolder<Block, BlockItem> strippedLog, strippedWood, strippedSappyLog;

    public final BlockBlockItemHolder<Block, BlockItem> log, wood, sappyLog;

    public final BlockBundleWithWall boards, verticalBoards, blocks;
    public final BlockBundle planks, verticalPlanks, tiles;

    public final ItemlessBlockBundleWithWall carvedBoards, carvedVerticalBoards, carvedBlocks;
    public final ItemlessBlockBundle carvedPlanks, carvedVerticalPlanks, carvedTiles;

    public final BlockBlockItemHolder<Block, BlockItem> steps, beam;

    public final BlockBlockItemHolder<Block, BlockItem> door, heavyDoor;
    public final BlockBlockItemHolder<Block, BlockItem> trapdoor, heavyTrapdoor;

    public final BlockBlockItemHolder<Block, BlockItem> button;
    public final BlockBlockItemHolder<Block, BlockItem> pressurePlate;

    public final BlockBlockItemHolder<Block, BlockItem> fence;
    public final BlockBlockItemHolder<Block, BlockItem> fenceGate;

    public final BlockBlockItemHolder<Block, BlockItem> itemPedestal;
    public final BlockBlockItemHolder<Block, BlockItem> itemStand;
    public final BlockBlockItemHolder<Block, BlockItem> decoratedItemPedestal;
    public final BlockBlockItemHolder<Block, BlockItem> decoratedItemStand;

    public final BlockBlockItemHolder<Block, BlockItem> sign;
    public final DeferredBlock<Block> wallSign;

    public WoodBlockSet(String id, String decoratedPrefix, Supplier<BlockSetType> blockSetType,
                        Supplier<LodestoneBlockProperties> properties) {
        this.id = id;

        allLogsTag = createTag("all_logs");
        logsTag = createTag("logs");
        strippedLogsTag = createTag("stripped_logs");

        boardsTag = createTag("boards");
        boardStairsTag = createTag("board_stairs");
        boardSlabsTag = createTag("board_slabs");
        boardWallsTag = createTag("board_walls");

        planksTag = createTag("planks");
        plankStairsTag = createTag("plank_stairs");
        plankSlabsTag = createTag("plank_slabs");

        var bottledSap = MalumContent.Materials.RUNIC_SAP_BOTTLE;
        var doorProperties = properties.get().setCutout();
        var itemHolderProperties = properties.get().noOcclusion();
        var decoratedItemHolderProperties = properties.get().setCutout().noOcclusion();


        strippedLog = registerBlock(name("stripped_%s_log"), () -> new RotatedPillarBlock(properties.get().addTags(strippedLogsTag)));
        strippedWood = registerBlock(name("stripped_%s"), () -> new RotatedPillarBlock(properties.get().addTags(strippedLogsTag)));
        strippedSappyLog = registerBlock(name("stripped_sappy_%s_log"), () -> new StrippedSappyLogBlock(properties.get().addTags(strippedLogsTag), bottledSap, MalumSpiritTypes.INFERNAL_COLORS().primaryColor()));

        log = registerBlock(name("%s_log"), () -> new MalumLogBlock(properties.get().addTags(logsTag)));
        wood = registerBlock(id, () -> new RotatedPillarBlock(properties.get().addTags(logsTag)));
        sappyLog = registerBlock(name("sappy_%s_log"), () -> new RotatedPillarBlock(properties.get().addTags(logsTag)));

        boards = new BlockBundleWithWall(name("%s_boards"), properties, boardsTag, boardSlabsTag, boardStairsTag, boardWallsTag);
        verticalBoards = new BlockBundleWithWall(name("vertical_%s_boards"), properties, boardsTag, boardSlabsTag, boardStairsTag, boardWallsTag);
        blocks = new BlockBundleWithWall(name("%s_blocks"), properties, boardsTag, boardSlabsTag, boardStairsTag, boardWallsTag);

        planks = new BlockBundle(name("%s_planks"), properties, planksTag, plankSlabsTag, plankStairsTag);
        verticalPlanks = new BlockBundle(name("vertical_%s_planks"), properties, planksTag, plankSlabsTag, plankStairsTag);
        tiles = new BlockBundle(name("%s_tiles"), properties, planksTag, plankSlabsTag, plankStairsTag);

        carvedBoards = new ItemlessBlockBundleWithWall(name("carved_%s_boards"), properties, boardsTag, boardSlabsTag, boardStairsTag, boardWallsTag);
        carvedVerticalBoards = new ItemlessBlockBundleWithWall(name("carved_vertical_%s_boards"), properties, boardsTag, boardSlabsTag, boardStairsTag, boardWallsTag);
        carvedBlocks = new ItemlessBlockBundleWithWall(name("carved_%s_blocks"), properties, boardsTag, boardSlabsTag, boardStairsTag, boardWallsTag);

        carvedPlanks = new ItemlessBlockBundle(name("carved_%s_planks"), properties, planksTag, plankSlabsTag, plankStairsTag);
        carvedVerticalPlanks = new ItemlessBlockBundle(name("carved_vertical_%s_planks"), properties, planksTag, plankSlabsTag, plankStairsTag);
        carvedTiles = new ItemlessBlockBundle(name("carved_%s_tiles"), properties, planksTag, plankSlabsTag, plankStairsTag);

        steps = registerBlock(name("%s_steps"), () -> new Block(properties.get()));
        beam = registerBlock(name("%s_beam"), () -> new ColumnBlock(properties.get()));

        door = registerBlock(name("%s_door"), () -> new DoorBlock(blockSetType.get(), doorProperties));
        heavyDoor = registerBlock(name("heavy_%s_door"), () -> new DoorBlock(blockSetType.get(), doorProperties));
        trapdoor = registerBlock(name("%s_trapdoor"), () -> new TrapDoorBlock(blockSetType.get(), doorProperties));
        heavyTrapdoor = registerBlock(name("heavy_%s_trapdoor"), () -> new TrapDoorBlock(blockSetType.get(), doorProperties));

        button = registerBlock(name("%s_planks_button"), () -> new ButtonBlock(blockSetType.get(), 20, properties.get().noCollission()));
        pressurePlate = registerBlock(name("%s_planks_pressure_plate"), () -> new PressurePlateBlock(blockSetType.get(), properties.get().noCollission()));

        fence = registerBlock(name("%s_planks_fence"), () -> new FenceBlock(properties.get()));
        fenceGate = registerBlock(name("%s_planks_fence_gate"), () -> new FenceGateBlock(MalumWoodTypes.RUNEWOOD, properties.get()));

        itemStand = registerBlock(name("%s_item_stand"), () -> new ItemStandBlock<>(itemHolderProperties));
        decoratedItemPedestal = registerBlock(name(decoratedPrefix + "_%s_item_pedestal"), () -> new DecoratedItemPedestalBlock<>(decoratedItemHolderProperties));
        itemPedestal = registerBlock(name("%s_item_pedestal"), () -> new WoodItemPedestalBlock<>(itemHolderProperties));
        decoratedItemStand = registerBlock(name(decoratedPrefix + "_%s_item_stand"), () -> new ItemStandBlock<>(decoratedItemHolderProperties));

        sign = registerBlock(name("%s_sign"), () -> new StandingSignBlock(MalumWoodTypes.RUNEWOOD, itemHolderProperties.noCollission()));
        wallSign = registerBlockNoItem(name("%s_wall_sign"), () -> new WallSignBlock(MalumWoodTypes.RUNEWOOD, itemHolderProperties.noCollission()));
    }

    protected TagKey<Block> createTag(String tag) {
        return BlockTags.create(MalumMod.malumPath(id + "_" + tag));
    }

    public void addToCreativeTab(CreativeTabCategoryBuilder builder) {
        builder.addItems(
                        log,
                        wood,
                        sappyLog,
                        strippedLog,
                        strippedWood,
                        strippedSappyLog
                ).nextLine()
                .addItems(
                        boards.block,
                        verticalBoards.block,
                        blocks.block,
                        planks.block,
                        verticalPlanks.block,
                        tiles.block
                ).nextLine()
                .addItems(
                        boards.stairs,
                        verticalBoards.stairs,
                        blocks.stairs,
                        planks.stairs,
                        verticalPlanks.stairs,
                        tiles.stairs
                ).nextLine()
                .addItems(
                        boards.slab,
                        verticalBoards.slab,
                        blocks.slab,
                        planks.slab,
                        verticalPlanks.slab,
                        tiles.slab
                ).nextLine()
                .addItems(
                        boards.wall,
                        verticalBoards.wall,
                        blocks.wall,
                        fence,
                        fenceGate,
                        sign
                ).nextLine()
                .addItems(
                        decoratedItemPedestal,
                        itemPedestal,
                        decoratedItemStand,
                        itemStand
                ).nextLine()
                .addItems(
                        door,
                        trapdoor,
                        heavyDoor,
                        heavyTrapdoor
                ).nextLine()
                .addItems(
                        pressurePlate,
                        button
                );
    }

    public void bindSigns(BlockEntityTypeAddBlocksEvent event) {
        event.modify(BlockEntityType.SIGN, sign.get(), wallSign.get());
    }
}