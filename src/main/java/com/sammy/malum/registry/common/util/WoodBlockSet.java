package com.sammy.malum.registry.common.util;

import com.sammy.malum.common.block.flora.wood.*;
import com.sammy.malum.common.block.storage.pedestal.DecoratedItemPedestalBlock;
import com.sammy.malum.common.block.storage.pedestal.WoodItemPedestalBlock;
import com.sammy.malum.common.block.storage.stand.ItemStandBlock;
import com.sammy.malum.registry.common.MalumContent;
import com.sammy.malum.registry.common.block.MalumBlockEntities;
import com.sammy.malum.registry.common.block.MalumWoodTypes;
import com.sammy.malum.registry.common.magic.MalumSpiritTypes;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import team.lodestar.lodestone.modules.toolkit.block.BlockBlockItemHolder;
import team.lodestar.lodestone.modules.toolkit.block.LodestoneBlockProperties;
import team.lodestar.lodestone.modules.toolkit.block.LodestoneStairBlock;
import team.lodestar.lodestone.modules.toolkit.creative_tab.CreativeTabCategoryBuilder;

import java.util.function.Supplier;

import static com.sammy.malum.registry.common.MalumContent.registerBlock;
import static com.sammy.malum.registry.common.MalumContent.registerBlockNoItem;

public class WoodBlockSet {

    private final String id;

    private String name(String name) {
        if (!name.contains("%s")) {
            return id + "_" + name;
        }
        return name.replace("%s", id);
    }

    protected final BlockBlockItemHolder<Block, BlockItem> strippedLog;
    protected final BlockBlockItemHolder<Block, BlockItem> strippedWood;
    protected final BlockBlockItemHolder<Block, BlockItem> strippedSappyLog;

    protected final BlockBlockItemHolder<Block, BlockItem> log;
    protected final BlockBlockItemHolder<Block, BlockItem> wood;
    protected final BlockBlockItemHolder<Block, BlockItem> sappyLog;

    protected final BlockBlockItemHolder<Block, BlockItem> boards;
    protected final BlockBlockItemHolder<Block, BlockItem> boardsSlab;
    protected final BlockBlockItemHolder<Block, BlockItem> boardsStairs;

    protected final BlockBlockItemHolder<Block, BlockItem> verticalBoards;
    protected final BlockBlockItemHolder<Block, BlockItem> verticalBoardsSlab;
    protected final BlockBlockItemHolder<Block, BlockItem> verticalBoardsStairs;

    protected final BlockBlockItemHolder<Block, BlockItem> planks;
    protected final BlockBlockItemHolder<Block, BlockItem> planksSlab;
    protected final BlockBlockItemHolder<Block, BlockItem> planksStairs;

    protected final BlockBlockItemHolder<Block, BlockItem> rusticPlanks;
    protected final BlockBlockItemHolder<Block, BlockItem> rusticPlanksSlab;
    protected final BlockBlockItemHolder<Block, BlockItem> rusticPlanksStairs;

    protected final BlockBlockItemHolder<Block, BlockItem> verticalPlanks;
    protected final BlockBlockItemHolder<Block, BlockItem> verticalPlanksSlab;
    protected final BlockBlockItemHolder<Block, BlockItem> verticalPlanksStairs;

    protected final BlockBlockItemHolder<Block, BlockItem> verticalRusticPlanks;
    protected final BlockBlockItemHolder<Block, BlockItem> verticalRusticPlanksSlab;
    protected final BlockBlockItemHolder<Block, BlockItem> verticalRusticPlanksStairs;

    protected final BlockBlockItemHolder<Block, BlockItem> tiles;
    protected final BlockBlockItemHolder<Block, BlockItem> tilesSlab;
    protected final BlockBlockItemHolder<Block, BlockItem> tilesStairs;

    protected final BlockBlockItemHolder<Block, BlockItem> rusticTiles;
    protected final BlockBlockItemHolder<Block, BlockItem> rusticTilesSlab;
    protected final BlockBlockItemHolder<Block, BlockItem> rusticTilesStairs;

    protected final BlockBlockItemHolder<Block, BlockItem> panel;
    protected final BlockBlockItemHolder<Block, BlockItem> cutPlanks;
    protected final BlockBlockItemHolder<Block, BlockItem> beam;

    protected final BlockBlockItemHolder<Block, BlockItem> door;
    protected final BlockBlockItemHolder<Block, BlockItem> heavyDoor;
    protected final BlockBlockItemHolder<Block, BlockItem> trapdoor;
    protected final BlockBlockItemHolder<Block, BlockItem> heavyTrapdoor;

    protected final BlockBlockItemHolder<Block, BlockItem> boltedDoor;
    protected final BlockBlockItemHolder<Block, BlockItem> heavyBoltedDoor;
    protected final BlockBlockItemHolder<Block, BlockItem> boltedTrapdoor;
    protected final BlockBlockItemHolder<Block, BlockItem> heavyBoltedTrapdoor;

    protected final BlockBlockItemHolder<Block, BlockItem> button;
    protected final BlockBlockItemHolder<Block, BlockItem> pressurePlate;

    protected final BlockBlockItemHolder<Block, BlockItem> boardsWall;
    protected final BlockBlockItemHolder<Block, BlockItem> fence;
    protected final BlockBlockItemHolder<Block, BlockItem> fenceGate;

    protected final BlockBlockItemHolder<Block, BlockItem> itemStand;
    protected final BlockBlockItemHolder<Block, BlockItem> decoratedItemPedestal;
    protected final BlockBlockItemHolder<Block, BlockItem> itemPedestal;
    protected final BlockBlockItemHolder<Block, BlockItem> decoratedItemStand;

    protected final BlockBlockItemHolder<Block, BlockItem> sign;
    protected final DeferredBlock<Block> wallSign;

    public WoodBlockSet(String id, String decoratedPrefix, Supplier<BlockSetType> blockSetType, LodestoneBlockProperties logProperties, LodestoneBlockProperties woodProperties) {
        this.id = id;

        var bottledSap = MalumContent.Materials.RUNIC_SAP_BOTTLE;
        var doorProperties = LodestoneBlockProperties.copy(woodProperties).setCutoutRenderType();
        var itemHolderProperties = LodestoneBlockProperties.copy(woodProperties).noOcclusion();


        strippedLog = registerBlock(name("stripped_%s_log"), () -> new RotatedPillarBlock(logProperties));
        strippedWood = registerBlock(name("stripped_%s"), () -> new RotatedPillarBlock(logProperties));
        strippedSappyLog = registerBlock(name("stripped_sappy_%s_log"), () -> new SappyLogBlock(logProperties, bottledSap, MalumSpiritTypes.INFERNAL_COLORS().primaryColor()));

        log = registerBlock(name("log"), () -> new MalumLogBlock(woodProperties));
        wood = registerBlock(id, () -> new RotatedPillarBlock(woodProperties));
        sappyLog = registerBlock(name("sappy_%s_log"), () -> new RotatedPillarBlock(woodProperties));

        boards = registerBlock(name("boards"), () -> new Block(woodProperties));
        boardsSlab = registerBlock(name("boards_slab"), () -> new SlabBlock(woodProperties));
        boardsStairs = registerBlock(name("boards_stairs"), () -> new LodestoneStairBlock(woodProperties));

        verticalBoards = registerBlock(name("vertical_%s_boards"), () -> new Block(woodProperties));
        verticalBoardsSlab = registerBlock(name("vertical_%s_boards_slab"), () -> new SlabBlock(woodProperties));
        verticalBoardsStairs = registerBlock(name("vertical_%s_boards_stairs"), () -> new LodestoneStairBlock(woodProperties));

        planks = registerBlock(name("planks"), () -> new Block(woodProperties));
        planksSlab = registerBlock(name("planks_slab"), () -> new SlabBlock(woodProperties));
        planksStairs = registerBlock(name("planks_stairs"), () -> new LodestoneStairBlock(woodProperties));

        rusticPlanks = registerBlock(name("rustic_%s_planks"), () -> new Block(woodProperties));
        rusticPlanksSlab = registerBlock(name("rustic_%s_planks_slab"), () -> new SlabBlock(woodProperties));
        rusticPlanksStairs = registerBlock(name("rustic_%s_planks_stairs"), () -> new LodestoneStairBlock(woodProperties));

        verticalPlanks = registerBlock(name("vertical_%s_planks"), () -> new Block(woodProperties));
        verticalPlanksSlab = registerBlock(name("vertical_%s_planks_slab"), () -> new SlabBlock(woodProperties));
        verticalPlanksStairs = registerBlock(name("vertical_%s_planks_stairs"), () -> new LodestoneStairBlock(woodProperties));

        verticalRusticPlanks = registerBlock(name("vertical_rustic_%s_planks"), () -> new Block(woodProperties));
        verticalRusticPlanksSlab = registerBlock(name("vertical_rustic_%s_planks_slab"), () -> new SlabBlock(woodProperties));
        verticalRusticPlanksStairs = registerBlock(name("vertical_rustic_%s_planks_stairs"), () -> new LodestoneStairBlock(woodProperties));

        tiles = registerBlock(name("tiles"), () -> new Block(woodProperties));
        tilesSlab = registerBlock(name("tiles_slab"), () -> new SlabBlock(woodProperties));
        tilesStairs = registerBlock(name("tiles_stairs"), () -> new LodestoneStairBlock(woodProperties));

        rusticTiles = registerBlock(name("rustic_%s_tiles"), () -> new Block(woodProperties));
        rusticTilesSlab = registerBlock(name("rustic_%s_tiles_slab"), () -> new SlabBlock(woodProperties));
        rusticTilesStairs = registerBlock(name("rustic_%s_tiles_stairs"), () -> new LodestoneStairBlock(woodProperties));

        panel = registerBlock(name("panel"), () -> new Block(woodProperties));
        cutPlanks = registerBlock(name("cut_%s_planks"), () -> new Block(woodProperties));
        beam = registerBlock(name("beam"), () -> new RotatedPillarBlock(woodProperties));

        door = registerBlock(name("door"), () -> new DoorBlock(blockSetType.get(), doorProperties));
        heavyDoor = registerBlock(name("heavy_door"), () -> new DoorBlock(blockSetType.get(), doorProperties));
        trapdoor = registerBlock(name("trapdoor"), () -> new TrapDoorBlock(blockSetType.get(), doorProperties));
        heavyTrapdoor = registerBlock(name("heavy_trapdoor"), () -> new TrapDoorBlock(blockSetType.get(), doorProperties));

        boltedDoor = registerBlock(name("bolted_%s_door"), () -> new DoorBlock(blockSetType.get(), doorProperties));
        heavyBoltedDoor = registerBlock(name("heavy_bolted_%s_door"), () -> new DoorBlock(blockSetType.get(), doorProperties));
        boltedTrapdoor = registerBlock(name("bolted_trapdoor"), () -> new TrapDoorBlock(blockSetType.get(), doorProperties));
        heavyBoltedTrapdoor = registerBlock(name("bolted_boards_trapdoor"), () -> new TrapDoorBlock(blockSetType.get(), doorProperties));

        button = registerBlock(name("planks_button"), () -> new ButtonBlock(blockSetType.get(), 20, woodProperties.noCollission()));
        pressurePlate = registerBlock(name("planks_pressure_plate"), () -> new PressurePlateBlock(blockSetType.get(), woodProperties.noCollission()));

        boardsWall = registerBlock(name("boards_wall"), () -> new WallBlock(woodProperties));
        fence = registerBlock(name("planks_fence"), () -> new FenceBlock(woodProperties));
        fenceGate = registerBlock(name("planks_fence_gate"), () -> new FenceGateBlock(MalumWoodTypes.RUNEWOOD, woodProperties));

        itemStand = registerBlock(name("item_stand"), () -> new ItemStandBlock<>(itemHolderProperties).setBlockEntity(MalumBlockEntities.ITEM_STAND));
        decoratedItemPedestal = registerBlock(name(decoratedPrefix + "_%s_item_pedestal"), () -> new DecoratedItemPedestalBlock<>(itemHolderProperties).setBlockEntity(MalumBlockEntities.ITEM_PEDESTAL));
        itemPedestal = registerBlock(name("item_pedestal"), () -> new WoodItemPedestalBlock<>(itemHolderProperties).setBlockEntity(MalumBlockEntities.ITEM_PEDESTAL));
        decoratedItemStand = registerBlock(name(decoratedPrefix + "_%s_item_stand"), () -> new ItemStandBlock<>(itemHolderProperties).setBlockEntity(MalumBlockEntities.ITEM_STAND));

        sign = registerBlock(name("sign"), () -> new StandingSignBlock(MalumWoodTypes.RUNEWOOD, itemHolderProperties.noCollission()));
        wallSign = registerBlockNoItem(name("wall_sign"), () -> new WallSignBlock(MalumWoodTypes.RUNEWOOD, itemHolderProperties.noCollission()));
    }

    public void addToCreativeTab(CreativeTabCategoryBuilder builder) {
        builder.addItems(
                log,
                strippedLog,
                wood,
                strippedWood,
                sappyLog,
                strippedSappyLog
        ).nextLine()
                .addItems(
                        boards,
                        verticalBoards,
                        planks,
                        verticalPlanks,
                        tiles,
                        rusticPlanks,
                        verticalRusticPlanks,
                        rusticTiles
                ).nextLine()
                .addItems(
                        boardsSlab,
                        verticalBoardsSlab,
                        planksSlab,
                        verticalPlanksSlab,
                        tilesSlab,
                        rusticPlanksSlab,
                        verticalRusticPlanksSlab,
                        rusticTilesSlab
                ).nextLine()
                .addItems(
                        boardsStairs,
                        verticalBoardsStairs,
                        planksStairs,
                        verticalPlanksStairs,
                        tilesStairs,
                        rusticPlanksStairs,
                        verticalRusticPlanksStairs,
                        rusticTilesStairs
                ).nextLine()
                .addItems(
                        panel,
                        cutPlanks,
                        beam,
                        decoratedItemPedestal,
                        itemPedestal,
                        decoratedItemStand,
                        itemStand
                ).nextLine()
                .addItems(
                        door,
                        boltedDoor,
                        trapdoor,
                        boltedTrapdoor,
                        heavyDoor,
                        heavyBoltedDoor,
                        heavyTrapdoor,
                        heavyBoltedTrapdoor
                ).nextLine()
                .addItems(
                        pressurePlate,
                        button,
                        boardsWall,
                        fence,
                        fenceGate,
                        sign
                );
    }

    public void bindSigns(BlockEntityTypeAddBlocksEvent event) {
        event.modify(BlockEntityType.SIGN, sign.get(), wallSign.get());
    }

    public BlockBlockItemHolder<Block, BlockItem> getStrippedLog() {
        return strippedLog;
    }

    public BlockBlockItemHolder<Block, BlockItem> getStrippedWood() {
        return strippedWood;
    }

    public BlockBlockItemHolder<Block, BlockItem> getStrippedSappyLog() {
        return strippedSappyLog;
    }

    public BlockBlockItemHolder<Block, BlockItem> getLog() {
        return log;
    }

    public BlockBlockItemHolder<Block, BlockItem> getWood() {
        return wood;
    }

    public BlockBlockItemHolder<Block, BlockItem> getSappyLog() {
        return sappyLog;
    }

    public BlockBlockItemHolder<Block, BlockItem> getBoards() {
        return boards;
    }

    public BlockBlockItemHolder<Block, BlockItem> getBoardsSlab() {
        return boardsSlab;
    }

    public BlockBlockItemHolder<Block, BlockItem> getBoardsStairs() {
        return boardsStairs;
    }

    public BlockBlockItemHolder<Block, BlockItem> getVerticalBoards() {
        return verticalBoards;
    }

    public BlockBlockItemHolder<Block, BlockItem> getVerticalBoardsSlab() {
        return verticalBoardsSlab;
    }

    public BlockBlockItemHolder<Block, BlockItem> getVerticalBoardsStairs() {
        return verticalBoardsStairs;
    }

    public BlockBlockItemHolder<Block, BlockItem> getPlanks() {
        return planks;
    }

    public BlockBlockItemHolder<Block, BlockItem> getPlanksSlab() {
        return planksSlab;
    }

    public BlockBlockItemHolder<Block, BlockItem> getPlanksStairs() {
        return planksStairs;
    }

    public BlockBlockItemHolder<Block, BlockItem> getRusticPlanks() {
        return rusticPlanks;
    }

    public BlockBlockItemHolder<Block, BlockItem> getRusticPlanksSlab() {
        return rusticPlanksSlab;
    }

    public BlockBlockItemHolder<Block, BlockItem> getRusticPlanksStairs() {
        return rusticPlanksStairs;
    }

    public BlockBlockItemHolder<Block, BlockItem> getVerticalPlanks() {
        return verticalPlanks;
    }

    public BlockBlockItemHolder<Block, BlockItem> getVerticalPlanksSlab() {
        return verticalPlanksSlab;
    }

    public BlockBlockItemHolder<Block, BlockItem> getVerticalPlanksStairs() {
        return verticalPlanksStairs;
    }

    public BlockBlockItemHolder<Block, BlockItem> getVerticalRusticPlanks() {
        return verticalRusticPlanks;
    }

    public BlockBlockItemHolder<Block, BlockItem> getVerticalRusticPlanksSlab() {
        return verticalRusticPlanksSlab;
    }

    public BlockBlockItemHolder<Block, BlockItem> getVerticalRusticPlanksStairs() {
        return verticalRusticPlanksStairs;
    }

    public BlockBlockItemHolder<Block, BlockItem> getTiles() {
        return tiles;
    }

    public BlockBlockItemHolder<Block, BlockItem> getTilesSlab() {
        return tilesSlab;
    }

    public BlockBlockItemHolder<Block, BlockItem> getTilesStairs() {
        return tilesStairs;
    }

    public BlockBlockItemHolder<Block, BlockItem> getRusticTiles() {
        return rusticTiles;
    }

    public BlockBlockItemHolder<Block, BlockItem> getRusticTilesSlab() {
        return rusticTilesSlab;
    }

    public BlockBlockItemHolder<Block, BlockItem> getRusticTilesStairs() {
        return rusticTilesStairs;
    }

    public BlockBlockItemHolder<Block, BlockItem> getPanel() {
        return panel;
    }

    public BlockBlockItemHolder<Block, BlockItem> getCutPlanks() {
        return cutPlanks;
    }

    public BlockBlockItemHolder<Block, BlockItem> getBeam() {
        return beam;
    }

    public BlockBlockItemHolder<Block, BlockItem> getDoor() {
        return door;
    }

    public BlockBlockItemHolder<Block, BlockItem> getHeavyDoor() {
        return heavyDoor;
    }

    public BlockBlockItemHolder<Block, BlockItem> getTrapdoor() {
        return trapdoor;
    }

    public BlockBlockItemHolder<Block, BlockItem> getHeavyTrapdoor() {
        return heavyTrapdoor;
    }

    public BlockBlockItemHolder<Block, BlockItem> getBoltedDoor() {
        return boltedDoor;
    }

    public BlockBlockItemHolder<Block, BlockItem> getHeavyBoltedDoor() {
        return heavyBoltedDoor;
    }

    public BlockBlockItemHolder<Block, BlockItem> getBoltedTrapdoor() {
        return boltedTrapdoor;
    }

    public BlockBlockItemHolder<Block, BlockItem> getHeavyBoltedTrapdoor() {
        return heavyBoltedTrapdoor;
    }

    public BlockBlockItemHolder<Block, BlockItem> getButton() {
        return button;
    }

    public BlockBlockItemHolder<Block, BlockItem> getPressurePlate() {
        return pressurePlate;
    }

    public BlockBlockItemHolder<Block, BlockItem> getBoardsWall() {
        return boardsWall;
    }

    public BlockBlockItemHolder<Block, BlockItem> getFence() {
        return fence;
    }

    public BlockBlockItemHolder<Block, BlockItem> getFenceGate() {
        return fenceGate;
    }

    public BlockBlockItemHolder<Block, BlockItem> getItemStand() {
        return itemStand;
    }

    public BlockBlockItemHolder<Block, BlockItem> getDecoratedItemPedestal() {
        return decoratedItemPedestal;
    }

    public BlockBlockItemHolder<Block, BlockItem> getItemPedestal() {
        return itemPedestal;
    }

    public BlockBlockItemHolder<Block, BlockItem> getDecoratedItemStand() {
        return decoratedItemStand;
    }

    public BlockBlockItemHolder<Block, BlockItem> getSign() {
        return sign;
    }

    public DeferredBlock<Block> getWallSign() {
        return wallSign;
    }
}